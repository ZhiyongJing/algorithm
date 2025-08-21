好！下面把“窗口函数”讲清楚、讲透一点（以 **MySQL 8+** 为主，其它主流方言大同小异）。

------

# 1. 窗口函数是什么？

**一句话**：窗口函数（Window Function）是在**不减少行数**的前提下，按你定义的“窗口（分组 + 排序 + 范围）”对多行做计算，并把结果**写回每一行**。

对比：

- **聚合函数**（`SUM/AVG/...` + `GROUP BY`）会把多行**压成一行**。
- **窗口函数**会**保留每一行**，只是多出若干“分析列”（累计、排名、占比、前后行值等）。

------

# 2. 基本语法（OVER 子句）

```sql
<窗口函数>(expr) OVER (
  PARTITION BY ...   -- 可选：像“分组”，但不合并行
  ORDER BY ...       -- 常用：定义行内顺序
  ROWS/RANGE ...     -- 可选：窗口帧（当前行前后多少行/值的范围）
)
```

- **PARTITION BY**：把数据切成若干“分区”（类似按客户分组），每个分区独立计算。
- **ORDER BY**：定义“行内顺序”（如按日期）；很多窗口计算依赖顺序。
- **窗口帧（FRAME）**：
  - `ROWS BETWEEN ... AND ...`：按**行数**定义窗口（最直观的滚动 N 行）。
  - `RANGE BETWEEN ... AND ...`：按**排序值的范围**定义窗口（会把“相同排序值”的行视作同一“同位组/peers”一并计算）。
  - 常用锚点：`UNBOUNDED PRECEDING`（分区开头）、`CURRENT ROW`、`N PRECEDING/FOLLOWING`。

> **默认帧**（有 `ORDER BY` 时）通常是
> `RANGE BETWEEN UNBOUNDED PRECEDING AND CURRENT ROW`
> 它会把**同位（排序值相等）的所有行**一起纳入窗口，容易让“累计”在相同排序值上显示**同一个结果**。
> 想要**逐行推进**的累计/移动窗口，请显式用 **`ROWS`**。

------

# 3. 分类 + 代表函数

- **排名类**：`ROW_NUMBER`, `RANK`, `DENSE_RANK`, `NTILE`
- **累计/聚合类**：`SUM`, `AVG`, `MIN`, `MAX`, `COUNT` … `OVER`
- **偏移类**：`LAG`, `LEAD`（取前/后几行的值）
- **分布类**：`PERCENT_RANK`, `CUME_DIST`
- **取值类**：`FIRST_VALUE`, `LAST_VALUE`, `NTH_VALUE`
- **统计类**：`VARIANCE/STDDEV`（也可窗口化）
- **占比/总体**：`SUM(...) OVER()` 组合计算“每行占总体的比例”（RATIO-TO-REPORT）

------

# 4. 直观小例子

示例表 `sales`：

| order_date | amount |
| ---------- | ------ |
| 2023-01-01 | 100    |
| 2023-01-02 | 200    |
| 2023-01-03 | 150    |
| 2023-01-04 | 300    |

## 4.1) 运行累计（逐行推进）

```sql
SELECT order_date, amount,
       SUM(amount) OVER (
         ORDER BY order_date
         ROWS BETWEEN UNBOUNDED PRECEDING AND CURRENT ROW
       ) AS running_sum
FROM sales;
```

结果：

| date | amt  | running_sum |
| ---- | ---- | ----------- |
| 01   | 100  | 100         |
| 02   | 200  | 300         |
| 03   | 150  | 450         |
| 04   | 300  | 750         |

> 关键点：用了 **ROWS** 才是“逐行推进”的累计。

## 4.2) 近 3 行移动平均

```sql
SELECT order_date, amount,
       AVG(amount) OVER (
         ORDER BY order_date
         ROWS BETWEEN 2 PRECEDING AND CURRENT ROW
       ) AS ma3
FROM sales;
```

结果：

| date | amt  | ma3   |
| ---- | ---- | ----- |
| 01   | 100  | 100.0 |
| 02   | 200  | 150.0 |
| 03   | 150  | 150.0 |
| 04   | 300  | 216.7 |

## 4.3) 取前一行 / 后一行

```sql
SELECT order_date, amount,
       LAG(amount)  OVER (ORDER BY order_date) AS prev_amt,
       LEAD(amount) OVER (ORDER BY order_date) AS next_amt
FROM sales;
```

结果：

| date | amt  | prev | next |
| ---- | ---- | ---- | ---- |
| 01   | 100  | NULL | 200  |
| 02   | 200  | 100  | 150  |
| 03   | 150  | 200  | 300  |
| 04   | 300  | 150  | NULL |

------

# 5. 窗口函数与聚合函数的区别

| 对比点   | 聚合函数（+ GROUP BY） | 窗口函数（+ OVER）                                  |
| -------- | ---------------------- | --------------------------------------------------- |
| 行数     | 多行 → 一行            | 多行 → 多行（保留明细）                             |
| 场景     | 横向汇总（报表总量）   | 明细 + 统计并存（累计、排名、占比、前后行等）       |
| 语法位置 | `SELECT/HAVING`        | `SELECT/ORDER BY`（**不能直接在 WHERE/HAVING 用**） |

> **不能在 WHERE 用窗口函数**：
> 需要先在子查询/CTE 里算好，再外层过滤。
> （MySQL 尚不支持 `QUALIFY` 语法）

**示例（每组 Top-1）**：

```sql
WITH ranked AS (
  SELECT o.*,
         ROW_NUMBER() OVER(PARTITION BY customer_id ORDER BY amount DESC, order_id DESC) AS rn
  FROM orders o
)
SELECT *
FROM ranked
WHERE rn = 1;
```

------

# 6. “RANGE vs ROWS”的坑

假设 `amount` 有并列：`100, 100, 200`，你写：

```sql
SUM(amount) OVER (ORDER BY amount)         -- 默认 RANGE ... CURRENT ROW
```

默认会把“同位值（100/100）”**一起**计算，得到：

- 第一行（100）：把两个 100 都算进去 ⇒ 200
- 第二行（100）：同 200
- 第三行（200）：200 + 200 = 400

如果你的期望是**逐行**滚动（第一行=100，第二行=200，第三行=400），请改成：

```sql
SUM(amount) OVER (
  ORDER BY amount
  ROWS BETWEEN UNBOUNDED PRECEDING AND CURRENT ROW
)
```

**记忆**：

- **RANGE**：按“值的范围”，同位值同进同出。
- **ROWS**：按“行数”，严格逐行推进。

------

# 7. 评价顺序 & 可用位置（MySQL）

1. `FROM/JOIN`
2. `WHERE`（此处**不可**用窗口函数）
3. `GROUP BY`（可与普通聚合并存）
4. `HAVING`（也**不可**直接用窗口函数）
5. **SELECT**（窗口函数在这里计算）
6. `ORDER BY`（可再次用窗口函数）
7. `LIMIT/OFFSET`

> 想按窗口结果过滤 → 用**子查询/CTE** 包一层。

------

# 8. 常见用途清单（给你抓重点）

- **Top-N/每组取最新**：`ROW_NUMBER()` + 外层过滤 `rn<=N`/`rn=1`
- **竞赛排名/不跳号排名**：`RANK` / `DENSE_RANK`
- **四分位/分桶**：`NTILE(4|10)`
- **累计/滚动**：`SUM/AVG/MIN/MAX/COUNT OVER(...)` + `ROWS` 帧
- **环比/差值**：`LAG/LEAD`
- **分布位置**：`PERCENT_RANK / CUME_DIST`
- **首尾/第 N 值**：`FIRST_VALUE / LAST_VALUE / NTH_VALUE`
- **总体占比**：`amount / SUM(amount) OVER ()`

------

# 9. 性能与可维护性小贴士

- **排序与分区一定要索引**（例如 `(partition_cols, order_col)`），否则容易全表排序。
- **明确 tie-breaker**：`ORDER BY` 里加稳定次序（如 `, id`），避免并列行顺序不确定。
- **显式窗口帧**：把默认帧写清楚（特别是 `LAST_VALUE` / `NTH_VALUE` / 累计），减少认知陷阱。
- **分步调试**：先写“明细版” SELECT 看数据，再逐步加窗口函数与外层过滤。
- **MySQL 没有 QUALIFY**：要“按窗口条件过滤”，就**包一层**。



# 10. 常用窗口函数总结

```sql
/* =======================================================================
   常用窗口函数总结（MySQL 8+ 可直接验证）
   格式：函数 → 作用 / 用途 → 示例 SQL → 输入 → 期望输出
   说明：先执行统一示例数据；输出为示意对账，以数据库实际计算为准。
   ======================================================================= */

-- ================== 统一示例数据（一次执行，后续复用） ==================
DROP TABLE IF EXISTS orders;
DROP TABLE IF EXISTS orders_ntile;
DROP TABLE IF EXISTS sales;
DROP TABLE IF EXISTS exam;

-- 订单明细：排名、占比、分组计数等
CREATE TABLE orders (
  customer_id VARCHAR(10),
  order_id    INT,
  amount      INT,
  order_date  DATE,
  PRIMARY KEY (customer_id, order_id)
);
INSERT INTO orders (customer_id, order_id, amount, order_date) VALUES
('A', 101, 300, '2023-01-01'),
('A', 102, 200, '2023-01-02'),
('A', 103, 200, '2023-01-03'),
('B', 201, 500, '2023-01-01'),
('B', 202, 100, '2023-01-02');

-- 分桶演示：8 行等差数据（便于 NTILE(4) 平均分桶）
CREATE TABLE orders_ntile (
  id INT PRIMARY KEY,
  amount INT
);
INSERT INTO orders_ntile (id, amount) VALUES
(1,10),(2,20),(3,30),(4,40),(5,50),(6,60),(7,70),(8,80);

-- 时间序列：累计/移动/偏移/首尾/方差等
CREATE TABLE sales (
  order_date  DATE PRIMARY KEY,
  amount      INT
);
INSERT INTO sales (order_date, amount) VALUES
('2023-01-01', 100),
('2023-01-02', 200),
('2023-01-03', 150),
('2023-01-04', 300);

-- 分布数据：百分比排名与累计分布
CREATE TABLE exam (
  student_id  VARCHAR(10) PRIMARY KEY,
  score       INT
);
INSERT INTO exam (student_id, score) VALUES
('S1', 95),
('S2', 85),
('S3', 85),
('S4', 70),
('S5', 60);

-- ==========================【1】排序与排名类 ==========================

/* 1) ROW_NUMBER()
 * 作用：在分组内按指定顺序为每行生成唯一递增编号（不考虑并列，绝对唯一）
 * 用途：Top-N（每组前 N）、“每组取最新/最早一条”、去重保留一条（rn=1）
 */
SELECT customer_id, order_id, amount,
       ROW_NUMBER() OVER (
         PARTITION BY customer_id
         ORDER BY amount DESC, order_id
       ) AS rn
FROM orders
ORDER BY customer_id, rn;
/* 输入：orders（见统一示例数据）
   期望输出：
   customer_id | order_id | amount | rn
   A           | 101      | 300    | 1
   A           | 102      | 200    | 2
   A           | 103      | 200    | 3
   B           | 201      | 500    | 1
   B           | 202      | 100    | 2
*/




/* 2) RANK()
 * 作用：有并列时共享名次；并列之后“跳名次”（例如 1,2,2,4）
 * 用途：竞赛排名、需要体现“名次断层”的业务口径
 */
SELECT customer_id, order_id, amount,
       RANK() OVER (ORDER BY amount DESC) AS rk
FROM orders
ORDER BY amount DESC, customer_id, order_id;
/* 期望输出：
   customer_id | order_id | amount | rk
   B           | 201      | 500    | 1
   A           | 101      | 300    | 2
   A           | 102      | 200    | 3
   A           | 103      | 200    | 3
   B           | 202      | 100    | 5
*/




/* 3) DENSE_RANK()
 * 作用：有并列时共享名次；名次不跳号（例如 1,2,2,3）
 * 用途：并列但希望名次连续（不出现空档）
 */
SELECT customer_id, order_id, amount,
       DENSE_RANK() OVER (ORDER BY amount DESC) AS drk
FROM orders
ORDER BY amount DESC, customer_id, order_id;
/* 期望输出：
   customer_id | order_id | amount | drk
   B           | 201      | 500    | 1
   A           | 101      | 300    | 2
   A           | 102      | 200    | 3
   A           | 103      | 200    | 3
   B           | 202      | 100    | 4
*/




/* 4) NTILE(N)
 * 作用：把有序结果等分为 N 个桶（尽量均匀），返回桶号 1..N
 * 用途：四分位/十分位、做群组分层（例如客户按贡献度分层）
 */
SELECT id, amount,
       NTILE(4) OVER (ORDER BY amount) AS quartile
FROM orders_ntile
ORDER BY amount;
/* 输入：orders_ntile
   期望输出：
   amount | quartile
   10     | 1
   20     | 1
   30     | 2
   40     | 2
   50     | 3
   60     | 3
   70     | 4
   80     | 4
*/

-- ==========================【2】累计与聚合类 ==========================

/* 5) SUM() OVER
 * 作用：沿排序方向做“运行累计和”（Running Total）
 * 用途：累计销量/金额/人数等趋势线
 */
SELECT order_date, amount,
       SUM(amount) OVER (ORDER BY order_date) AS running_total
FROM sales
ORDER BY order_date;
/* 输入：sales
   期望输出：
   order_date  | amount | running_total
   2023-01-01  | 100    | 100
   2023-01-02  | 200    | 300
   2023-01-03  | 150    | 450
   2023-01-04  | 300    | 750
*/




/* 6) AVG() OVER（滑动平均）
 * 作用：在滚动窗口内求平均（示例：7 天窗口；数据不足时自动按已有行计算）
 * 用途：平滑波动、建立趋势均线（如 7 日/30 日均线）
 */
SELECT order_date, amount,
       AVG(amount) OVER (
         ORDER BY order_date
         ROWS BETWEEN 6 PRECEDING AND CURRENT ROW
       ) AS ma7
FROM sales
ORDER BY order_date;
/* 期望输出（按现有 4 天数据计算）：
   order_date  | amount | ma7
   2023-01-01  | 100    | 100.0000
   2023-01-02  | 200    | 150.0000
   2023-01-03  | 150    | 150.0000
   2023-01-04  | 300    | 187.5000
*/




/* 7) MAX() OVER
 * 作用：窗口内最大值；配帧“到当前行为止”即“历史最高”
 * 用途：峰值监控、阶段最高纪录
 */
SELECT order_date, amount,
       MAX(amount) OVER (
         ORDER BY order_date
         ROWS BETWEEN UNBOUNDED PRECEDING AND CURRENT ROW
       ) AS peak
FROM sales
ORDER BY order_date;
/* 期望输出：
   order_date  | amount | peak
   2023-01-01  | 100    | 100
   2023-01-02  | 200    | 200
   2023-01-03  | 150    | 200
   2023-01-04  | 300    | 300
*/




/* 8) MIN() OVER
 * 作用：窗口内最小值；配帧“到当前行为止”即“历史最低”
 * 用途：谷值监控、阶段最低纪录
 */
SELECT order_date, amount,
       MIN(amount) OVER (
         ORDER BY order_date
         ROWS BETWEEN UNBOUNDED PRECEDING AND CURRENT ROW
       ) AS running_min
FROM sales
ORDER BY order_date;
/* 期望输出：
   order_date  | amount | running_min
   2023-01-01  | 100    | 100
   2023-01-02  | 200    | 100
   2023-01-03  | 150    | 100
   2023-01-04  | 300    | 100
*/




/* 9) COUNT() OVER
 * 作用：窗口内计数；可做“运行行数”或“分组大小”，且不压缩明细
 * 用途：时间到目前为止共有几天/几条；每个客户共几单
 */
-- 运行计数（时间序列）
SELECT order_date, amount,
       COUNT(*) OVER (ORDER BY order_date) AS running_count
FROM sales
ORDER BY order_date;
/* 期望输出：
   2023-01-01 | 1
   2023-01-02 | 2
   2023-01-03 | 3
   2023-01-04 | 4
*/

-- 分组计数（保留明细）
SELECT customer_id, order_id, amount,
       COUNT(*) OVER (PARTITION BY customer_id) AS cnt_in_customer
FROM orders
ORDER BY customer_id, order_id;
/* 期望输出：A 的每行 cnt=3；B 的每行 cnt=2 */

-- ==========================【3】偏移类 ==========================

/* 10) LAG(expr, offset, default)
 * 作用：取前 offset 行的值（默认 1 行）；可设默认值
 * 用途：环比/差值/增长率等（当前 - 前一值）
 */
SELECT order_date, amount,
       amount - LAG(amount, 1, 0) OVER (ORDER BY order_date) AS diff
FROM sales
ORDER BY order_date;
/* 期望输出：
   order_date  | amount | diff
   2023-01-01  | 100    | 100
   2023-01-02  | 200    | 100
   2023-01-03  | 150    | -50
   2023-01-04  | 300    | 150
*/




/* 11) LEAD(expr, offset, default)
 * 作用：取后 offset 行的值（默认 1 行）；可设默认值
 * 用途：查看下一期值、预测下期
 */
SELECT order_date, amount,
       LEAD(amount) OVER (ORDER BY order_date) AS next_amt
FROM sales
ORDER BY order_date;
/* 期望输出：
   order_date  | amount | next_amt
   2023-01-01  | 100    | 200
   2023-01-02  | 200    | 150
   2023-01-03  | 150    | 300
   2023-01-04  | 300    | NULL
*/

-- ==========================【4】分布类 ==========================

/* 12) PERCENT_RANK()
 * 作用：百分比排名，范围 [0,1]；计算：(RANK - 1) / (N - 1)
 * 用途：相对位置、百分位档
 */
SELECT student_id, score,
       PERCENT_RANK() OVER (ORDER BY score DESC) AS pct_rank
FROM exam
ORDER BY score DESC, student_id;
/* 期望输出（N=5）：
   student_id | score | pct_rank
   S1         | 95    | 0.00
   S2         | 85    | 0.25
   S3         | 85    | 0.25
   S4         | 70    | 0.75
   S5         | 60    | 1.00
*/




/* 13) CUME_DIST()
 * 作用：累计分布比例；到当前值（含并列）为止的累计样本数 / 总样本数
 * 用途：分布位置、阈值判断（如 80% 分位的档位）
 */
SELECT student_id, score,
       CUME_DIST() OVER (ORDER BY score DESC) AS cume
FROM exam
ORDER BY score DESC, student_id;
/* 期望输出（N=5；含并列）：
   student_id | score | cume
   S1         | 95    | 0.20   -- 1/5
   S2         | 85    | 0.60   -- 3/5
   S3         | 85    | 0.60
   S4         | 70    | 0.80   -- 4/5
   S5         | 60    | 1.00   -- 5/5
*/

-- ==========================【5】取值类 ==========================

/* 14) FIRST_VALUE(expr) / LAST_VALUE(expr)
 * 作用：窗口内首值/尾值；LAST_VALUE 若未显式帧，默认到当前行为止，易误解
 * 用途：首尾对比、阶段变化对比
 */
SELECT order_date, amount,
       FIRST_VALUE(amount) OVER (ORDER BY order_date) AS first_amt,
       LAST_VALUE(amount)  OVER (
         ORDER BY order_date
         ROWS BETWEEN UNBOUNDED PRECEDING AND UNBOUNDED FOLLOWING
       ) AS last_amt
FROM sales
ORDER BY order_date;
/* 期望输出：
   order_date  | amount | first_amt | last_amt
   2023-01-01  | 100    | 100       | 300
   2023-01-02  | 200    | 100       | 300
   2023-01-03  | 150    | 100       | 300
   2023-01-04  | 300    | 100       | 300
*/




/* 15) NTH_VALUE(expr, N)
 * 作用：窗口帧内按排序后的第 N 个值（不足 N 行为 NULL）
 * 用途：阈值线（如“到当前为止第 2 值”）、基准对照
 * 注意：务必显式指定帧（否则默认帧易误解）
 */
SELECT order_date, amount,
       NTH_VALUE(amount, 2) OVER (
         ORDER BY order_date
         ROWS BETWEEN UNBOUNDED PRECEDING AND CURRENT ROW
       ) AS nth2_amt
FROM sales
ORDER BY order_date;
/* 期望输出：
   order_date  | amount | nth2_amt
   2023-01-01  | 100    | NULL
   2023-01-02  | 200    | 200
   2023-01-03  | 150    | 200
   2023-01-04  | 300    | 200
*/

-- ==========================【6】统计与占比类 ==========================

/* 16) “总体占比”（RATIO-TO-REPORT 常见写法）
 * 作用：用 SUM(...) OVER() 得到全表总量，再算每行占比
 * 用途：贡献度、帕累托分析前置
 */
SELECT customer_id, order_id, amount,
       SUM(amount) OVER () AS grand_total,
       ROUND(amount / NULLIF(SUM(amount) OVER (), 0), 4) AS pct_of_total
FROM orders
ORDER BY amount DESC, customer_id, order_id;
/* 总额：500 + 300 + 200 + 200 + 100 = 1300
   期望输出：
   customer_id | order_id | amount | pct_of_total
   B           | 201      | 500    | 0.3846
   A           | 101      | 300    | 0.2308
   A           | 102      | 200    | 0.1538
   A           | 103      | 200    | 0.1538
   B           | 202      | 100    | 0.0769
*/




/* 17) STDDEV/VARIANCE 作为窗口（以样本标准差为例）
 * 作用：窗口内波动度；观察“到当前为止”的离散程度变化
 * 用途：稳定性监控、波动告警
 * 说明：不同版本/舍入方式可能略有差异，以实际计算为准
 */
SELECT order_date, amount,
       STDDEV_SAMP(amount) OVER (
         ORDER BY order_date
         ROWS BETWEEN UNBOUNDED PRECEDING AND CURRENT ROW
       ) AS running_stddev
FROM sales
ORDER BY order_date;
/* 示例输出（仅供参考）：
   order_date  | amount | running_stddev
   2023-01-01  | 100    | NULL
   2023-01-02  | 200    | 70.7107  或 100.0000（以数据库计算为准）
   2023-01-03  | 150    | ~50.0000
   2023-01-04  | 300    | ~95.7427
*/



【5. 特殊场景总结】
--------------------------------------------------------------
- ROW_NUMBER() → 去重、Top-N、每组取最新
- RANK()/DENSE_RANK() → 有并列的排名
- NTILE(N) → 分组分桶（分位数分析）
- SUM()/AVG() → 累计值/移动平均
- LAG()/LEAD() → 环比/增长率、趋势比较
- PERCENT_RANK()/CUME_DIST() → 百分位数分析
- FIRST_VALUE()/LAST_VALUE() → 首尾对比、区间变化

================================================================
*/
```

