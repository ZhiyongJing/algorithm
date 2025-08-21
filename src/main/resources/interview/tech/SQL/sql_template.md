##1. SQL思维技巧

好的 Jimmy 👍，下面是完整的 **40 招 SQL 思维技巧宝典**，我已经整理成了一个 **Markdown 文件格式**，可以直接保存为 `sql_thinking_cheatsheet.md` 使用。

------

# 🧠 SQL 思维技巧宝典（40 招全解）

> 每招包含：应用场景 / 详细解释 / 示例 SQL / 输入输出表格  
> 示例基于 PostgreSQL（MySQL 8 基本等价，差异已注明）

---

## 一、分组与聚合

---

### 1) 条件聚合 (Conditional Aggregation)
**场景**：统计每个用户完成/取消订单数。  
**解释**：CASE WHEN 条件 → 数值，再用 SUM/COUNT 聚合。  

```sql
SELECT user_id,
       SUM(CASE WHEN status='completed' THEN 1 ELSE 0 END) AS completed_cnt,
       SUM(CASE WHEN status='cancelled' THEN 1 ELSE 0 END) AS cancelled_cnt
FROM Orders
GROUP BY user_id;
```

**输入**

| user_id | order_id | status    |
| ------- | -------- | --------- |
| 1       | 101      | completed |
| 1       | 102      | cancelled |
| 2       | 103      | completed |

**输出**

| user_id | completed_cnt | cancelled_cnt |
| ------- | ------------- | ------------- |
| 1       | 1             | 1             |
| 2       | 1             | 0             |

------

### 2) 分组锚值法 (连续区间识别)

**场景**：找出用户连续下单段。
**解释**：日期与行号差固定时为同一连续段。

```sql
WITH d AS (
  SELECT DISTINCT user_id, order_date::date AS d FROM Orders
),
seq AS (
  SELECT user_id, d,
         ROW_NUMBER() OVER(PARTITION BY user_id ORDER BY d) rn
  FROM d
),
g AS (
  SELECT user_id, d, d - rn*INTERVAL '1 day' AS gk FROM seq
)
SELECT user_id, MIN(d) AS start_d, MAX(d) AS end_d
FROM g
GROUP BY user_id, gk;
```

**输入**

| user_id | order_date |
| ------- | ---------- |
| 1       | 2025-08-01 |
| 1       | 2025-08-02 |
| 1       | 2025-08-05 |

**输出**

| user_id | start_d    | end_d      |
| ------- | ---------- | ---------- |
| 1       | 2025-08-01 | 2025-08-02 |
| 1       | 2025-08-05 | 2025-08-05 |

------

### 3) 集合对比 HAVING COUNT(DISTINCT …)

**场景**：找出买过 A 和 B 的用户。
**解释**：用 HAVING 检查分组内的不同值数量。

```sql
SELECT user_id
FROM Orders
WHERE product IN ('A','B')
GROUP BY user_id
HAVING COUNT(DISTINCT product)=2;
```

**输入**

| user_id | product |
| ------- | ------- |
| 1       | A       |
| 1       | B       |
| 2       | A       |

**输出**

| user_id |
| ------- |
| 1       |

------

### 4) Top-N (ROW_NUMBER)

**场景**：取每个用户消费最高的前 2 单。
**解释**：分组排序编号，过滤前 N。

```sql
WITH r AS (
  SELECT o.*, ROW_NUMBER() OVER(PARTITION BY user_id ORDER BY amount DESC) rn
  FROM Orders o
)
SELECT * FROM r WHERE rn<=2;
```

**输入**

| user_id | order_id | amount |
| ------- | -------- | ------ |
| 1       | 101      | 50     |
| 1       | 102      | 120    |
| 1       | 103      | 80     |

**输出**

| user_id | order_id | amount | rn   |
| ------- | -------- | ------ | ---- |
| 1       | 102      | 120    | 1    |
| 1       | 103      | 80     | 2    |

------

### 5) 差分与环比 (LAG/LEAD)

**场景**：月度销售环比。
**解释**：LAG/LEAD 提取前一行数据，做差。

```sql
SELECT month, sales,
       sales - LAG(sales) OVER(ORDER BY month) AS diff
FROM Sales;
```

**输入**

| month | sales |
| ----- | ----- |
| Jan   | 100   |
| Feb   | 120   |
| Mar   | 90    |

**输出**

| month | sales | diff |
| ----- | ----- | ---- |
| Jan   | 100   | NULL |
| Feb   | 120   | 20   |
| Mar   | 90    | -30  |

------

### 6) 累计与滑动窗口

**场景**：累计金额与 7 日均值。

```sql
SELECT day, value,
       SUM(value) OVER(ORDER BY day ROWS BETWEEN UNBOUNDED PRECEDING AND CURRENT ROW) AS run_sum,
       AVG(value) OVER(ORDER BY day ROWS BETWEEN 6 PRECEDING AND CURRENT ROW) AS ma7
FROM Series;
```

**输入**

| day        | value |
| ---------- | ----- |
| 2025-08-01 | 10    |
| 2025-08-02 | 20    |
| 2025-08-03 | 15    |

**输出**

| day        | value | run_sum | ma7  |
| ---------- | ----- | ------- | ---- |
| 2025-08-01 | 10    | 10      | 10   |
| 2025-08-02 | 20    | 30      | 15   |
| 2025-08-03 | 15    | 45      | 15   |

------

### 7) 分组取最新

**场景**：取用户最新设备。

```sql
WITH t AS (
  SELECT d.*, ROW_NUMBER() OVER(PARTITION BY user_id ORDER BY updated_at DESC) rn
  FROM Devices d
)
SELECT * FROM t WHERE rn=1;
```

**输入**

| user_id | device_id | updated_at |
| ------- | --------- | ---------- |
| 1       | 201       | 2025-08-01 |
| 1       | 202       | 2025-08-05 |

**输出**

| user_id | device_id | updated_at |
| ------- | --------- | ---------- |
| 1       | 202       | 2025-08-05 |

------

### 8) DISTINCT + 聚合

**场景**：统计独立用户数。

```sql
SELECT COUNT(DISTINCT user_id) AS dau
FROM Logins
WHERE login_date=CURRENT_DATE;
```

------

### 9) 日期序列补齐

**场景**：补齐每日活跃用户数。

```sql
WITH d AS (
  SELECT GENERATE_SERIES(DATE '2025-08-01', DATE '2025-08-07', INTERVAL '1 day')::date AS day
)
SELECT d.day, COUNT(DISTINCT o.user_id) AS active
FROM d
LEFT JOIN Orders o ON d.day=o.order_date::date
GROUP BY d.day;
```

------

### 10) 缺口检测 (LAG)

**场景**：发现断档日期。

```sql
SELECT user_id, d,
       LAG(d) OVER(PARTITION BY user_id ORDER BY d) prev,
       d - LAG(d) OVER(PARTITION BY user_id ORDER BY d) gap
FROM Orders;
```

## 二、集合与对比

---

### 11) 集合运算 (UNION / INTERSECT / EXCEPT)
**场景**：对比两批用户，求并集、交集、差集。  
**解释**：集合运算能简洁表达数学集合逻辑。MySQL 用 JOIN/NOT EXISTS 替代。  

```sql
-- A ∪ B
SELECT user_id FROM A
UNION
SELECT user_id FROM B;

-- A ∩ B
SELECT user_id FROM A
INTERSECT
SELECT user_id FROM B;

-- A \ B
SELECT user_id FROM A
EXCEPT
SELECT user_id FROM B;
```

**输入**

A

| user_id |
| ------- |
| 1       |
| 2       |

B

| user_id |
| ------- |
| 2       |
| 3       |

**输出**

- A∪B → 1,2,3
- A∩B → 2
- A\B → 1

------

### 12) 半连接 / 反连接 (EXISTS / NOT EXISTS)

**场景**：买过 A 但没买过 B 的用户。
**解释**：EXISTS 判断是否存在关联记录，比 IN 更直观高效。

```sql
SELECT DISTINCT u.user_id
FROM Users u
WHERE EXISTS (
    SELECT 1 FROM Orders o WHERE o.user_id=u.user_id AND o.product='A'
)
AND NOT EXISTS (
    SELECT 1 FROM Orders o WHERE o.user_id=u.user_id AND o.product='B'
);
```

**输入**

Users

| user_id |
| ------- |
| 1       |
| 2       |

Orders

| user_id | product |
| ------- | ------- |
| 1       | A       |
| 1       | B       |
| 2       | A       |

**输出**

| user_id |
| ------- |
| 2       |

------

### 13) 行转列 (Pivot via CASE)

**场景**：统计每个用户不同设备数。
**解释**：用 SUM(CASE WHEN …) 实现条件聚合。

```sql
SELECT user_id,
       SUM(CASE WHEN device_type='mobile'  THEN 1 ELSE 0 END) AS mobile,
       SUM(CASE WHEN device_type='desktop' THEN 1 ELSE 0 END) AS desktop
FROM Devices
GROUP BY user_id;
```

**输入**

| user_id | device_type |
| ------- | ----------- |
| 1       | mobile      |
| 1       | desktop     |
| 2       | mobile      |

**输出**

| user_id | mobile | desktop |
| ------- | ------ | ------- |
| 1       | 1      | 1       |
| 2       | 1      | 0       |

------

### 14) 列转行 (Unpivot)

**场景**：把字段拆成行，便于后续聚合。
**解释**：用 UNION ALL 拼接多列。

```sql
SELECT user_id, 'mobile' AS type, mobile AS cnt FROM DeviceAgg
UNION ALL
SELECT user_id, 'desktop', desktop FROM DeviceAgg;
```

**输入**

| user_id | mobile | desktop |
| ------- | ------ | ------- |
| 1       | 1      | 2       |

**输出**

| user_id | type    | cnt  |
| ------- | ------- | ---- |
| 1       | mobile  | 1    |
| 1       | desktop | 2    |

------

### 15) 递归 CTE (树/层级结构)

**场景**：组织架构、品类树。
**解释**：递归 CTE 从根开始，逐层展开。

```sql
WITH RECURSIVE tree AS (
    SELECT id, parent_id, 1 AS lvl
    FROM Category WHERE parent_id IS NULL
  UNION ALL
    SELECT c.id, c.parent_id, t.lvl+1
    FROM Category c
    JOIN tree t ON c.parent_id=t.id
)
SELECT * FROM tree;
```

**输入**

| id   | parent_id |
| ---- | --------- |
| 1    | NULL      |
| 2    | 1         |
| 3    | 2         |

**输出**

| id   | parent_id | lvl  |
| ---- | --------- | ---- |
| 1    | NULL      | 1    |
| 2    | 1         | 2    |
| 3    | 2         | 3    |

------

### 16) 多维聚合 (ROLLUP / CUBE)

**场景**：销售数据多维统计。
**解释**：ROLLUP 生成明细 + 小计 + 总计。

```sql
SELECT region, product, SUM(sales) AS total_sales
FROM Sales
GROUP BY ROLLUP(region, product);
```

**输入**

| region | product | sales |
| ------ | ------- | ----- |
| East   | A       | 100   |
| East   | B       | 50    |

**输出**

| region | product | total_sales |
| ------ | ------- | ----------- |
| East   | A       | 100         |
| East   | B       | 50          |
| East   | NULL    | 150         |
| NULL   | NULL    | 150         |

------

### 17) 布尔转数值

**场景**：通过率计算。
**解释**：布尔条件 → 0/1，加总后求比例。

```sql
SELECT SUM(CASE WHEN passed THEN 1 ELSE 0 END)::float / COUNT(*) AS pass_rate
FROM Exams;
```

**输入**

| id   | passed |
| ---- | ------ |
| 1    | true   |
| 2    | false  |
| 3    | true   |

**输出**

| pass_rate |
| --------- |
| 0.6667    |

------

### 18) 分组布尔逻辑 (MIN/MAX 实现 ALL/ANY)

**场景**：判断一组记录是否全满足/部分满足条件。
**解释**：MIN=1 → 全满足；MAX=1 → 至少一条满足。

```sql
SELECT user_id,
       MIN(CASE WHEN status='completed' THEN 1 ELSE 0 END)=1 AS all_completed,
       MAX(CASE WHEN status='refunded'  THEN 1 ELSE 0 END)=1 AS any_refunded
FROM Orders
GROUP BY user_id;
```

**输入**

| user_id | status    |
| ------- | --------- |
| 1       | completed |
| 1       | completed |
| 2       | completed |
| 2       | refunded  |

**输出**

| user_id | all_completed | any_refunded |
| ------- | ------------- | ------------ |
| 1       | true          | false        |
| 2       | false         | true         |

------

### 19) EXISTS vs IN

**场景**：判断用户是否下过单。
**解释**：EXISTS 适合大表，避免 IN 性能问题。

```sql
SELECT u.*
FROM Users u
WHERE EXISTS (SELECT 1 FROM Orders o WHERE o.user_id=u.user_id);
```

**输入**

Users

| user_id |
| ------- |
| 1       |
| 2       |

Orders

| user_id | order_id |
| ------- | -------- |
| 1       | 101      |

**输出**

| user_id |
| ------- |
| 1       |

------

### 20) 子查询下推 (Predicate Pushdown)

**场景**：查询 8 月订单金额汇总。
**解释**：先过滤再聚合，提高效率。

```sql
WITH f AS (
  SELECT * FROM Orders WHERE order_date >= DATE '2025-08-01'
)
SELECT customer_id, SUM(amount) AS total_amount
FROM f
GROUP BY customer_id;
```

**输入**

| customer_id | order_date | amount |
| ----------- | ---------- | ------ |
| 1           | 2025-08-01 | 100    |
| 1           | 2025-07-15 | 50     |

**输出**

| customer_id | total_amount |
| ----------- | ------------ |
| 1           | 100          |

好的 Jimmy，下面是**第 21–30 招**的完整重生版（修正了第 26 招 NTILE 的分桶示例等问题），按你的要求提供**应用场景＋详细解释＋示例 SQL＋输入输出表格**，可直接粘成 Markdown 手册的一部分。

------

## 三、性能与优化 & 统计分析（21–30）

---

### 21) 索引友好写法 (SARGable)
**场景**：查询某天的数据，同时利用到 `created_at` 的索引。  
**解释**：避免在索引列上包裹函数（如 `DATE(created_at)`），改写为可被索引利用的**半开区间**范围谓词。

```sql
-- ❌ 不佳：函数在列上，难以使用索引
-- SELECT * FROM Events WHERE DATE(created_at) = DATE '2025-08-01';

-- ✅ 推荐：半开区间写法（SARGable）
SELECT *
FROM Events
WHERE created_at >= TIMESTAMP '2025-08-01 00:00:00'
  AND created_at <  TIMESTAMP '2025-08-02 00:00:00';
```

**输入**

| event_id | created_at          |
| -------- | ------------------- |
| 1        | 2025-08-01 10:00:00 |
| 2        | 2025-08-02 09:00:00 |

**输出**

| event_id | created_at          |
| -------- | ------------------- |
| 1        | 2025-08-01 10:00:00 |

------

### 22) Top-N（含并列）/ per 分组 (RANK / DENSE_RANK)

**场景**：每个部门取成绩 Top 3，**保留并列**名次。
**解释**：`ROW_NUMBER()` 不保留并列；`RANK()` 和 `DENSE_RANK()` 可以。`RANK()` 在并列后会跳名次，`DENSE_RANK()` 不跳名次。

```sql
SELECT dept, emp, score, rnk
FROM (
  SELECT dept, emp, score,
         RANK() OVER (PARTITION BY dept ORDER BY score DESC) AS rnk
  FROM Scores
) t
WHERE rnk <= 3
ORDER BY dept, rnk, emp;
```

**输入**

| dept | emp  | score |
| ---- | ---- | ----- |
| A    | e1   | 90    |
| A    | e2   | 90    |
| A    | e3   | 85    |
| A    | e4   | 80    |
| B    | e5   | 95    |
| B    | e6   | 90    |

**输出**

| dept | emp  | score | rnk  |
| ---- | ---- | ----- | ---- |
| A    | e1   | 90    | 1    |
| A    | e2   | 90    | 1    |
| A    | e3   | 85    | 3    |
| B    | e5   | 95    | 1    |
| B    | e6   | 90    | 2    |

------

### 23) 分组累计百分比 (Cumulative Percentage / Pareto)

**场景**：按商品销量从高到低累计，计算累计占比（帕累托 80/20 分析）。
**解释**：先按商品聚合，再用窗口函数计算**运行和**与**总和**，最后算累计占比。

```sql
WITH s AS (
  SELECT product, SUM(amount) AS total
  FROM Orders
  GROUP BY product
),
ranked AS (
  SELECT product, total,
         SUM(total) OVER (ORDER BY total DESC) AS running_sum,
         SUM(total) OVER ()                      AS grand_total
  FROM s
)
SELECT product, total, running_sum,
       ROUND(running_sum * 100.0 / grand_total, 2) AS cum_pct
FROM ranked
ORDER BY total DESC, product;
```

**输入**

| product | amount |
| ------- | ------ |
| A       | 100    |
| B       | 50     |
| C       | 30     |

**输出**

| product | total | running_sum | cum_pct |
| ------- | ----- | ----------- | ------- |
| A       | 100   | 100         | 55.56   |
| B       | 50    | 150         | 83.33   |
| C       | 30    | 180         | 100.00  |

------

### 24) 中位数 / 分位数 (PERCENTILE_CONT)

**场景**：计算 KPI 的中位数或自定义分位点（如 P90）。
**解释**：`PERCENTILE_CONT(p) WITHIN GROUP (ORDER BY x)` 以**连续分布**计算分位数（必要时插值）。

```sql
-- 中位数（P50）
SELECT PERCENTILE_CONT(0.5) WITHIN GROUP (ORDER BY amount) AS median_amount
FROM Orders;

-- P90
SELECT PERCENTILE_CONT(0.9) WITHIN GROUP (ORDER BY amount) AS p90_amount
FROM Orders;
```

**输入**

| order_id | amount |
| -------- | ------ |
| 1        | 10     |
| 2        | 20     |
| 3        | 30     |

**输出（示例：P50）**

| median_amount |
| ------------- |
| 20            |

------

### 25) 首尾值：FIRST_VALUE / LAST_VALUE

**场景**：取用户**首单**与**末单**日期。
**解释**：`LAST_VALUE` 需指定完整窗口帧，否则默认到“当前行”为止；可再 `SELECT DISTINCT` 收敛为每用户一行。

```sql
WITH x AS (
  SELECT
    user_id,
    FIRST_VALUE(order_date) OVER (
      PARTITION BY user_id ORDER BY order_date
    ) AS first_order,
    LAST_VALUE(order_date) OVER (
      PARTITION BY user_id ORDER BY order_date
      ROWS BETWEEN UNBOUNDED PRECEDING AND UNBOUNDED FOLLOWING
    ) AS last_order
  FROM Orders
)
SELECT DISTINCT user_id, first_order, last_order
FROM x
ORDER BY user_id;
```

**输入**

| user_id | order_date |
| ------- | ---------- |
| 1       | 2025-08-01 |
| 1       | 2025-08-05 |
| 1       | 2025-08-07 |
| 2       | 2025-07-01 |

**输出**

| user_id | first_order | last_order |
| ------- | ----------- | ---------- |
| 1       | 2025-08-01  | 2025-08-07 |
| 2       | 2025-07-01  | 2025-07-01 |

------

### 26) 等频分桶/分层 (NTILE)

**场景**：根据金额把用户分为 4 档（四分位数分桶）。
**解释**：`NTILE(4)` 将排序后的数据**尽量平均**分为 4 组；数据量不是 4 的倍数时，前若干组多 1 条。

```sql
SELECT user_id, amount,
       NTILE(4) OVER (ORDER BY amount DESC) AS quartile
FROM Orders
ORDER BY amount DESC;
```

**输入（8 行，便于平均示例）**

| user_id | amount |
| ------- | ------ |
| 1       | 100    |
| 2       | 90     |
| 3       | 80     |
| 4       | 70     |
| 5       | 60     |
| 6       | 50     |
| 7       | 40     |
| 8       | 30     |

**输出**

| user_id | amount | quartile |
| ------- | ------ | -------- |
| 1       | 100    | 1        |
| 2       | 90     | 1        |
| 3       | 80     | 2        |
| 4       | 70     | 2        |
| 5       | 60     | 3        |
| 6       | 50     | 3        |
| 7       | 40     | 4        |
| 8       | 30     | 4        |

------

### 27) 反连接（差集）：EXCEPT / NOT EXISTS

**场景**：找出表 A 有而表 B 没有的用户。
**解释**：PostgreSQL 可用 `EXCEPT`；MySQL 使用 `NOT EXISTS`（或 `LEFT JOIN … IS NULL`）实现。

```sql
-- PostgreSQL：差集
SELECT user_id FROM A
EXCEPT
SELECT user_id FROM B;

-- MySQL/通用：NOT EXISTS
SELECT a.user_id
FROM A a
WHERE NOT EXISTS (
  SELECT 1 FROM B b WHERE b.user_id = a.user_id
);
```

**输入**

A

| user_id |
| ------- |
| 1       |
| 2       |

B

| user_id |
| ------- |
| 2       |

**输出**

| user_id |
| ------- |
| 1       |

------

### 28) 分组“全集判断” (Set Division)

**场景**：找出“买过所有在售商品”的用户。
**解释**：分组去重计数 = 商品全集大小。实际业务中推荐以**商品维表**为准（避免只看有订单的商品）。

```sql
-- 商品全集大小
WITH prod_all AS (
  SELECT COUNT(*) AS n_all FROM Products  -- 商品维表
)
SELECT o.user_id
FROM Orders o
JOIN Products p ON o.product_id = p.product_id
GROUP BY o.user_id
HAVING COUNT(DISTINCT o.product_id) = (SELECT n_all FROM prod_all)
ORDER BY o.user_id;
```

**输入**

Products

| product_id | name |
| ---------- | ---- |
| 10         | A    |
| 11         | B    |

Orders

| user_id | product_id |
| ------- | ---------- |
| 1       | 10         |
| 1       | 11         |
| 2       | 10         |

**输出**

| user_id |
| ------- |
| 1       |

------

### 29) 缓慢变化维度 (SCD Type-2) 时间段推导

**场景**：根据每次状态的 `start_date` 推导有效区间的 `end_date`。
**解释**：对同一用户按开始时间排序，用 `LEAD(start_date)` 取下一段开始，当前段 `end_date = 下一段开始 - 1 天`；最后一段 `end_date` 为 `NULL`（仍有效）。

```sql
SELECT
  user_id,
  status,
  start_date,
  LEAD(start_date) OVER (PARTITION BY user_id ORDER BY start_date)
    - INTERVAL '1 day' AS end_date
FROM StatusHistory
ORDER BY user_id, start_date;
```

**输入**

| user_id | status | start_date |
| ------- | ------ | ---------- |
| 1       | A      | 2025-08-01 |
| 1       | B      | 2025-08-05 |

**输出**

| user_id | status | start_date | end_date   |
| ------- | ------ | ---------- | ---------- |
| 1       | A      | 2025-08-01 | 2025-08-04 |
| 1       | B      | 2025-08-05 | NULL       |

------

### 30) 多行拼接为字符串 (STRING_AGG / GROUP_CONCAT)

**场景**：把每个用户买过的商品名拼成逗号分隔字符串，**去重且按字母序**。
**解释**：PostgreSQL 的 `STRING_AGG(expr, ',' ORDER BY expr)` 支持排序；若需去重，可先在子查询中 `DISTINCT`再聚合。

```sql
-- PostgreSQL（去重 + 排序）
SELECT user_id,
       STRING_AGG(product_name, ',' ORDER BY product_name) AS products
FROM (
  SELECT DISTINCT o.user_id, p.product_name
  FROM Orders o
  JOIN Products p ON p.product_id = o.product_id
) s
GROUP BY user_id
ORDER BY user_id;

-- MySQL：GROUP_CONCAT(DISTINCT product_name ORDER BY product_name SEPARATOR ',')
```

**输入**

Products

| product_id | product_name |
| ---------- | ------------ |
| 10         | Apple        |
| 11         | Banana       |
| 12         | Apple        |

Orders

| user_id | product_id |
| ------- | ---------- |
| 1       | 10         |
| 1       | 11         |
| 1       | 10         |

**输出**

| user_id | products     |
| ------- | ------------ |
| 1       | Apple,Banana |



## 四、报表与复杂技巧（31–40）

### 31) 覆盖索引查询 (Covering Index)
**场景**：日志/事件大表上只查询少量列，尽量避免“回表”。  
**解释**：当查询只使用到某个复合索引包含的列（如 `(user_id, event_date)`），存储引擎可直接从索引返回结果，减少随机 I/O。需要**设计合适的复合索引**使 SELECT 只读到索引页。

```sql
-- 设计层面（示意）：为 Events(user_id, event_date) 建复合索引
-- CREATE INDEX idx_events_user_date ON Events(user_id, event_date);

-- 查询仅命中索引中的列，避免回表
SELECT user_id, event_date
FROM Events
WHERE user_id = 42
  AND event_date >= DATE '2025-08-01'
  AND event_date <  DATE '2025-09-01'
ORDER BY event_date;
```

**输入**

| user_id | event_date | payload(jsonb) |
| ------- | ---------- | -------------- |
| 42      | 2025-08-02 | {...}          |
| 42      | 2025-09-02 | {...}          |

**输出**

| user_id | event_date |
| ------- | ---------- |
| 42      | 2025-08-02 |

------

### 32) 分区表与查询裁剪 (Partition Pruning)

**场景**：按日期/区域分区的超大表，提高按分区键过滤的查询效率。
**解释**：把大表按分区键（如 `created_date`）拆成多个分区；当 WHERE 命中分区范围时，优化器只扫描相关分区。

```sql
-- PostgreSQL 示例：按月范围分区（示意）
-- 主表
-- CREATE TABLE Events (
--   id bigserial, created_date date, user_id int, ...
-- ) PARTITION BY RANGE (created_date);

-- 子分区（2025-08）
-- CREATE TABLE Events_202508 PARTITION OF Events
--   FOR VALUES FROM ('2025-08-01') TO ('2025-09-01');

-- 查询仅扫描 2025-08 分区
SELECT COUNT(*) AS cnt
FROM Events
WHERE created_date >= DATE '2025-08-01'
  AND created_date <  DATE '2025-09-01';
```

**输入**

| id   | created_date | user_id |
| ---- | ------------ | ------- |
| 1    | 2025-08-02   | 7       |
| 2    | 2025-09-03   | 8       |

**输出**

| cnt  |
| ---- |
| 1    |

------

### 33) 多维透视矩阵 (Multi-dim Pivot)

**场景**：按地区 × 季度统计销售额，列出 Q1/Q2/Q3/Q4 列。
**解释**：用 `SUM(CASE WHEN …)` 进行多维度的行转列透视（比 13) 更复杂的多列矩阵）。

```sql
SELECT region,
       SUM(CASE WHEN quarter = 1 THEN sales ELSE 0 END) AS q1,
       SUM(CASE WHEN quarter = 2 THEN sales ELSE 0 END) AS q2,
       SUM(CASE WHEN quarter = 3 THEN sales ELSE 0 END) AS q3,
       SUM(CASE WHEN quarter = 4 THEN sales ELSE 0 END) AS q4
FROM Sales
GROUP BY region
ORDER BY region;
```

**输入**

| region | quarter | sales |
| ------ | ------- | ----- |
| East   | 1       | 100   |
| East   | 2       | 120   |
| West   | 1       | 80    |

**输出**

| region | q1   | q2   | q3   | q4   |
| ------ | ---- | ---- | ---- | ---- |
| East   | 100  | 120  | 0    | 0    |
| West   | 80   | 0    | 0    | 0    |

------

### 34) 留存分析 (Cohort Retention)

**场景**：按注册日分 cohort，计算注册后第 N 天的留存人数。
**解释**：将 `activity_date - register_date` 映射为 `day_offset`，按 `(register_date, day_offset)` 统计独立活跃用户。

```sql
WITH acts AS (
  SELECT u.user_id,
         u.register_date::date AS cohort,
         a.activity_date::date AS act_day,
         (a.activity_date::date - u.register_date::date) AS day_offset
  FROM Users u
  JOIN UserActivity a ON a.user_id = u.user_id
)
SELECT cohort, day_offset, COUNT(DISTINCT user_id) AS retained_users
FROM acts
WHERE day_offset BETWEEN 0 AND 7
GROUP BY cohort, day_offset
ORDER BY cohort, day_offset;
```

**输入**

Users

| user_id | register_date |
| ------- | ------------- |
| 1       | 2025-08-01    |

UserActivity

| user_id | activity_date |
| ------- | ------------- |
| 1       | 2025-08-01    |
| 1       | 2025-08-03    |

**输出**

| cohort     | day_offset | retained_users |
| ---------- | ---------- | -------------- |
| 2025-08-01 | 0          | 1              |
| 2025-08-01 | 2          | 1              |

------

### 35) 动态透视 (Dynamic Pivot via SQL 拼接)

**场景**：需要把“动态枚举值”转成列（列名随数据变化）。
**解释**：标准 SQL 不支持完全动态列构造，常用做法：先查询 distinct 值并拼接成 SQL（在存储过程/应用层生成），再执行。

```sql
-- 伪代码（PostgreSQL 思路）：
-- 1) SELECT DISTINCT status FROM Orders;  -- 得到动态列集合
-- 2) 拼接：SELECT user_id, 
--             SUM(CASE WHEN status='A' THEN 1 ELSE 0 END) AS "A",
--             SUM(CASE WHEN status='B' THEN 1 ELSE 0 END) AS "B", ...
--          FROM Orders GROUP BY user_id;
-- 3) EXECUTE 动态 SQL
```

**输入**

| user_id | status |
| ------- | ------ |
| 1       | A      |
| 1       | B      |
| 2       | A      |

**输出（动态）**

| user_id | A    | B    |
| ------- | ---- | ---- |
| 1       | 1    | 1    |
| 2       | 1    | 0    |

------

### 36) 快照差异比对 (Full Outer Join Diff)

**场景**：两期快照对比，找新增/删除/变更记录。
**解释**：用 `FULL JOIN`（或 `UNION` + 去重）对齐键，比较字段差异，并标识变化类型。

```sql
SELECT 
  COALESCE(a.id, b.id) AS id,
  a.value AS old_val,
  b.value AS new_val,
  CASE
    WHEN a.id IS NULL THEN 'added'
    WHEN b.id IS NULL THEN 'removed'
    WHEN a.value <> b.value THEN 'changed'
    ELSE 'same'
  END AS diff_type
FROM Snapshot_A a
FULL JOIN Snapshot_B b ON a.id = b.id
WHERE a.id IS NULL 
   OR b.id IS NULL 
   OR a.value <> b.value
ORDER BY id;
```

**输入**

Snapshot_A

| id   | value |
| ---- | ----- |
| 1    | foo   |
| 2    | bar   |

Snapshot_B

| id   | value |
| ---- | ----- |
| 1    | foo   |
| 3    | baz   |

**输出**

| id   | old_val | new_val | diff_type |
| ---- | ------- | ------- | --------- |
| 2    | bar     | NULL    | removed   |
| 3    | NULL    | baz     | added     |

------

### 37) 无窗口函数的行号模拟 (User Variables / 变量法)

**场景**：在不支持窗口函数的环境（如 MySQL 5.x）生成组内行号。
**解释**：利用用户变量在排序后的扫描中累加；注意该技巧依赖扫描顺序与实现细节，推荐升级到支持窗口函数的版本。

```sql
-- MySQL 5.x 示例
SET @prev_user := NULL, @rn := 0;
SELECT user_id, order_id, order_date,
       @rn := IF(@prev_user = user_id, @rn + 1, 1) AS rn,
       @prev_user := user_id AS _
FROM Orders
ORDER BY user_id, order_date;
```

**输入**

| user_id | order_id | order_date |
| ------- | -------- | ---------- |
| 1       | 101      | 2025-08-01 |
| 1       | 102      | 2025-08-03 |

**输出**

| user_id | order_id | order_date | rn   |
| ------- | -------- | ---------- | ---- |
| 1       | 101      | 2025-08-01 | 1    |
| 1       | 102      | 2025-08-03 | 2    |

------

### 38) 分组 Top-N 并带出其他字段 (Join Back)

**场景**：每组 Top-N 记录，且需要带出其他非排序字段（如备注）。
**解释**：先用窗口函数求 rn，再在同一层或子查询中筛选 `rn<=N`，保留所有列；或将 Top-N keys 回连主表取全量字段。

```sql
WITH ranked AS (
  SELECT o.*,
         ROW_NUMBER() OVER (PARTITION BY user_id ORDER BY amount DESC) AS rn
  FROM Orders o
)
SELECT *
FROM ranked
WHERE rn <= 2
ORDER BY user_id, rn;
```

**输入**

| user_id | order_id | amount | note   |
| ------- | -------- | ------ | ------ |
| 1       | 101      | 50     | small  |
| 1       | 102      | 120    | big    |
| 1       | 103      | 80     | medium |

**输出**

| user_id | order_id | amount | note   | rn   |
| ------- | -------- | ------ | ------ | ---- |
| 1       | 102      | 120    | big    | 1    |
| 1       | 103      | 80     | medium | 2    |

------

### 39) 递归累计（无窗口函数备选）(Recursive Running Sum)

**场景**：旧环境不支持窗口函数时计算累计和。
**解释**：用递归 CTE 逐行累加；性能不如窗口函数，但通用性强（适用 PostgreSQL/SQL Server）。

```sql
WITH ordered AS (
  SELECT t.*, ROW_NUMBER() OVER (ORDER BY day) AS rn
  FROM Series t
),
rec AS (
  SELECT rn, day, value, value AS running_sum
  FROM ordered
  WHERE rn = 1
  UNION ALL
  SELECT o.rn, o.day, o.value, r.running_sum + o.value
  FROM rec r
  JOIN ordered o ON o.rn = r.rn + 1
)
SELECT day, value, running_sum
FROM rec
ORDER BY rn;
```

**输入**

| day        | value |
| ---------- | ----- |
| 2025-08-01 | 10    |
| 2025-08-02 | 20    |
| 2025-08-03 | 15    |

**输出**

| day        | value | running_sum |
| ---------- | ----- | ----------- |
| 2025-08-01 | 10    | 10          |
| 2025-08-02 | 20    | 30          |
| 2025-08-03 | 15    | 45          |

------

### 40) 组内条件占比筛选 (Window + 外层过滤)

**场景**：筛出“在每个用户的所有订单里，‘高额’订单占比 ≥ 50% 的用户”。
**解释**：先用窗口函数计算分母（组内总数）与分子（条件计数），再在外层按占比过滤。

```sql
WITH base AS (
  SELECT
    user_id,
    order_id,
    amount,
    COUNT(*) OVER (PARTITION BY user_id) AS total_cnt,
    SUM(CASE WHEN amount >= 100 THEN 1 ELSE 0 END)
      OVER (PARTITION BY user_id) AS hi_cnt
  FROM Orders
)
SELECT DISTINCT user_id
FROM base
WHERE hi_cnt * 1.0 / NULLIF(total_cnt, 0) >= 0.5
ORDER BY user_id;
```

**输入**

| user_id | order_id | amount |
| ------- | -------- | ------ |
| 1       | 101      | 120    |
| 1       | 102      | 80     |
| 1       | 103      | 150    |
| 2       | 201      | 50     |

**输出**

| user_id |
| ------- |
| 1       |

```
--- 

如果你需要，我也可以把 **11–40 招** 整理为一个**完整的单一 Markdown 文件**或 **PDF 小册子**，并加上目录与交叉链接，方便检索 🔎。
```

------



```sql
# SQL 思维技巧大全（21 招）
—— 每招含：解释 / 应用场景 / 示例 SQL / 输入与输出表格  
（示例以 PostgreSQL 语法为主；MySQL 8.0 基本等价，少数处在注释给出替代）

────────────────────────────────────────────────────────

1) 条件聚合 (Conditional Aggregation)
【解释】用 CASE 把条件转成数值，再用 SUM/COUNT 聚合，完成“分组内条件计数/求和/比例”。
【场景】每个用户完成/取消订单数、合规率等。
【SQL】
SELECT user_id,
       SUM(CASE WHEN status='completed' THEN 1 ELSE 0 END) AS completed_cnt,
       SUM(CASE WHEN status='cancelled' THEN 1 ELSE 0 END) AS cancelled_cnt
FROM Orders
GROUP BY user_id;
【输入】
| user_id | order_id | status     |
|--------:|---------:|------------|
| 1       | 101      | completed  |
| 1       | 102      | cancelled  |
| 2       | 103      | completed  |
【输出】
| user_id | completed_cnt | cancelled_cnt |
|--------:|---------------:|--------------:|
| 1       | 1              | 1             |
| 2       | 1              | 0             |

────────────────────────────────────────────────────────

2) 分组锚值法：连续区间 (Grouping by Anchor Key)
【解释】对分组排序后编号 rn；用 (date - rn*1day) 作为“锚值”，锚值相同的行即为同一连续段。
【场景】连续登录/下单/打卡区间识别。
【SQL】
WITH d AS (
  SELECT DISTINCT user_id, order_date::date AS d
  FROM Orders
),
seq AS (
  SELECT user_id, d,
         ROW_NUMBER() OVER(PARTITION BY user_id ORDER BY d) AS rn
  FROM d
),
g AS (
  SELECT user_id, d, d - (rn * INTERVAL '1 day') AS gk
  FROM seq
)
SELECT user_id, MIN(d) AS segment_start, MAX(d) AS segment_end
FROM g
GROUP BY user_id, gk
ORDER BY user_id, segment_start;
【输入】
| user_id | order_date  |
|--------:|-------------|
| 1       | 2025-08-01  |
| 1       | 2025-08-02  |
| 1       | 2025-08-05  |
【输出】
| user_id | segment_start | segment_end |
|--------:|---------------|-------------|
| 1       | 2025-08-01    | 2025-08-02  |
| 1       | 2025-08-05    | 2025-08-05  |

────────────────────────────────────────────────────────

3) 集合对比：HAVING COUNT(DISTINCT …)
【解释】先限定候选集合，再按用户聚合，使用 DISTINCT 计数判断“是否同时包含多个元素”。
【场景】既买过 A 又买过 B、同时访问多个模块的用户。
【SQL】
SELECT user_id
FROM Orders
WHERE product IN ('A','B')
GROUP BY user_id
HAVING COUNT(DISTINCT product)=2;
【输入】
| user_id | product |
|--------:|---------|
| 1       | A       |
| 1       | B       |
| 2       | A       |
【输出】
| user_id |
|--------:|
| 1       |

────────────────────────────────────────────────────────

4) Top-N：窗口排名 (ROW_NUMBER/RANK/DENSE_RANK)
【解释】分组排序并标号，过滤 rn<=N。
【场景】每个用户消费金额最高的前 3 单。
【SQL】
WITH ranked AS (
  SELECT o.*,
         ROW_NUMBER() OVER(PARTITION BY user_id ORDER BY amount DESC) AS rn
  FROM Orders o
)
SELECT * FROM ranked WHERE rn<=3;
【输入】
| user_id | order_id | amount |
|--------:|---------:|-------:|
| 1       | 101      | 50     |
| 1       | 102      | 120    |
| 1       | 103      | 80     |
【输出】(Top2 示例)
| user_id | order_id | amount | rn |
|--------:|---------:|-------:|---:|
| 1       | 102      | 120    | 1  |
| 1       | 103      | 80     | 2  |

────────────────────────────────────────────────────────

5) 差分与环比 (LAG/LEAD)
【解释】用 LAG/LEAD 取前/后一行，做差/比率。
【场景】月度销售环比、时间序列缺口定位。
【SQL】
SELECT month, sales,
       sales - LAG(sales) OVER(ORDER BY month) AS mom_diff,
       ROUND( (sales*1.0 / NULLIF(LAG(sales) OVER(ORDER BY month),0) - 1)*100, 2) AS mom_pct
FROM MonthlySales;
【输入】
| month | sales |
|------:|------:|
| 1     | 100   |
| 2     | 120   |
| 3     | 90    |
【输出】
| month | sales | mom_diff | mom_pct |
|------:|------:|---------:|--------:|
| 1     | 100   | NULL     | NULL    |
| 2     | 120   | 20       | 20.00   |
| 3     | 90    | -30      | -25.00  |

────────────────────────────────────────────────────────

6) 累计与滑动窗口 (Running Total / Moving Average)
【解释】使用窗口框架 ROWS BETWEEN … 指定累计或移动范围。
【场景】累计 GMV、7 日移动平均。
【SQL】
SELECT day, value,
       SUM(value) OVER(ORDER BY day ROWS BETWEEN UNBOUNDED PRECEDING AND CURRENT ROW) AS running_sum,
       AVG(value) OVER(ORDER BY day ROWS BETWEEN 6 PRECEDING AND CURRENT ROW) AS ma7
FROM Series;
【输入】
| day        | value |
|------------|------:|
| 2025-08-01 | 10    |
| 2025-08-02 | 20    |
| 2025-08-03 | 15    |
【输出】
| day        | value | running_sum | ma7  |
|------------|------:|------------:|-----:|
| 2025-08-01 | 10    | 10          | 10.0 |
| 2025-08-02 | 20    | 30          | 15.0 |
| 2025-08-03 | 15    | 45          | 15.0 |

────────────────────────────────────────────────────────

7) 分组去重取最新 (Row-wise Dedup by ROW_NUMBER)
【解释】分组排序，保留 rn=1 即可“按组取最新”。
【场景】用户最新设备/最近一次登录/订单最新状态。
【SQL】
WITH t AS (
  SELECT d.*,
         ROW_NUMBER() OVER(PARTITION BY user_id ORDER BY updated_at DESC) AS rn
  FROM Devices d
)
SELECT user_id, device_id, updated_at
FROM t
WHERE rn=1;
【输入】
| user_id | device_id | updated_at  |
|--------:|----------:|-------------|
| 1       | 201       | 2025-08-01  |
| 1       | 202       | 2025-08-05  |
【输出】
| user_id | device_id | updated_at  |
|--------:|----------:|-------------|
| 1       | 202       | 2025-08-05  |

────────────────────────────────────────────────────────

8) DISTINCT + 聚合（独立数/去重计数）
【解释】COUNT(DISTINCT col) 统计独立对象数。
【场景】DAU/MAU、独立设备数。
【SQL】
SELECT COUNT(DISTINCT user_id) AS dau
FROM Logins
WHERE login_date = CURRENT_DATE;
【输入】
| user_id | login_date  |
|--------:|-------------|
| 1       | 2025-08-19  |
| 1       | 2025-08-19  |
| 2       | 2025-08-19  |
【输出】
| dau |
|----:|
| 2   |

────────────────────────────────────────────────────────

9) 日期序列补齐 (Generate Series & Left Join)
【解释】生成连续日期，再与事实表 LEFT JOIN，填补空白天。
【场景】日报/周报需要连续日期轴。
【SQL】-- PostgreSQL
WITH dates AS (
  SELECT GENERATE_SERIES(DATE '2025-08-01', DATE '2025-08-07', INTERVAL '1 day')::date AS d
)
SELECT d.d, COALESCE(COUNT(DISTINCT o.user_id),0) AS active_users
FROM dates d
LEFT JOIN Orders o ON o.order_date::date = d.d
GROUP BY d.d
ORDER BY d.d;
-- MySQL 8 可用递归 CTE 或数字表产生日期
【输入】
| order_id | user_id | order_date  |
|---------:|--------:|-------------|
| 1        | 10      | 2025-08-01  |
| 2        | 11      | 2025-08-03  |
【输出】
| d          | active_users |
|------------|--------------|
| 2025-08-01 | 1            |
| 2025-08-02 | 0            |
| 2025-08-03 | 1            |
| …          | …            |

────────────────────────────────────────────────────────

10) 缺口/断档检测 (Gaps via LAG)
【解释】同组内取前一行日期，比较间隔发现缺口。
【场景】识别非连续登录、内容发布断更。
【SQL】
SELECT user_id, d,
       LAG(d) OVER(PARTITION BY user_id ORDER BY d) AS prev_d,
       (d - LAG(d) OVER(PARTITION BY user_id ORDER BY d)) AS gap
FROM (
  SELECT DISTINCT user_id, order_date::date AS d
  FROM Orders
) s
ORDER BY user_id, d;
【输入】
| user_id | d          |
|--------:|------------|
| 1       | 2025-08-01 |
| 1       | 2025-08-03 |
【输出】
| user_id | d          | prev_d     | gap   |
|--------:|------------|------------|-------|
| 1       | 2025-08-01 | NULL       | NULL  |
| 1       | 2025-08-03 | 2025-08-01 | 2 days|

────────────────────────────────────────────────────────

11) 集合运算 (UNION/INTERSECT/EXCEPT)
【解释】对两个结果集进行并/交/差；MySQL 可用 JOIN/NOT EXISTS 替代 INTERSECT/EXCEPT。
【场景】对比两批用户群。
【SQL】(PostgreSQL)
-- A∪B
SELECT user_id FROM A
UNION
SELECT user_id FROM B;
-- A∩B
SELECT user_id FROM A
INTERSECT
SELECT user_id FROM B;
-- A\B
SELECT user_id FROM A
EXCEPT
SELECT user_id FROM B;
【输入】
A: | user_id |
   |--------:|
   | 1       |
   | 2       |
B: | user_id |
   |--------:|
   | 2       |
   | 3       |
【输出】
A∪B: 1,2,3   A∩B: 2   A\B: 1

────────────────────────────────────────────────────────

12) 半连接/反连接 (EXISTS / NOT EXISTS)
【解释】判断“是否存在关联行”更高效且表达清晰。
【场景】买过 A 但没买过 B、用户是否有有效订阅。
【SQL】
SELECT DISTINCT u.user_id
FROM Users u
WHERE EXISTS (
  SELECT 1 FROM Orders o
  WHERE o.user_id=u.user_id AND o.product='A'
)
AND NOT EXISTS (
  SELECT 1 FROM Orders o
  WHERE o.user_id=u.user_id AND o.product='B'
);
【输入】
Users: | user_id |
       |--------:|
       | 1       |
       | 2       |
Orders:| user_id | product |
       |--------:|---------|
       | 1       | A       |
       | 1       | B       |
       | 2       | A       |
【输出】
| user_id |
|--------:|
| 2       |

────────────────────────────────────────────────────────

13) 行转列 (Pivot via CASE)
【解释】用 SUM(CASE WHEN …) 把行映射到列。
【场景】渠道/设备次数字段化报表。
【SQL】
SELECT user_id,
       SUM(CASE WHEN device_type='mobile'  THEN 1 ELSE 0 END) AS mobile_cnt,
       SUM(CASE WHEN device_type='desktop' THEN 1 ELSE 0 END) AS desktop_cnt
FROM Devices
GROUP BY user_id;
【输入】
| user_id | device_type |
|--------:|-------------|
| 1       | mobile      |
| 1       | desktop     |
| 2       | mobile      |
【输出】
| user_id | mobile_cnt | desktop_cnt |
|--------:|-----------:|------------:|
| 1       | 1          | 1           |
| 2       | 1          | 0           |

────────────────────────────────────────────────────────

14) 列转行 (Unpivot)
【解释】把多列度量拍平为多行，便于下游聚合。
【场景】指标名称/数值对的统一处理。
【SQL】(简单 UNION ALL 版本)
SELECT user_id, 'mobile' AS device_type, mobile_cnt AS cnt FROM DeviceAgg
UNION ALL
SELECT user_id, 'desktop', desktop_cnt FROM DeviceAgg;
【输入】
DeviceAgg:
| user_id | mobile_cnt | desktop_cnt |
|--------:|-----------:|------------:|
| 1       | 1          | 1           |
【输出】
| user_id | device_type | cnt |
|--------:|-------------|----:|
| 1       | mobile      | 1   |
| 1       | desktop     | 1   |

────────────────────────────────────────────────────────

15) 递归 CTE (树/路径/层级)
【解释】起始层 UNION ALL 子层，直到无更多子节点。
【场景】组织架构、品类树、路径展开。
【SQL】
WITH RECURSIVE tree AS (
  SELECT id, parent_id, 1 AS lvl
  FROM Category WHERE parent_id IS NULL
  UNION ALL
  SELECT c.id, c.parent_id, t.lvl+1
  FROM Category c
  JOIN tree t ON c.parent_id = t.id
)
SELECT * FROM tree ORDER BY lvl, id;
【输入】
| id | parent_id |
|---:|-----------|
| 1  | NULL      |
| 2  | 1         |
| 3  | 2         |
【输出】
| id | parent_id | lvl |
|---:|-----------|----:|
| 1  | NULL      | 1   |
| 2  | 1         | 2   |
| 3  | 2         | 3   |

────────────────────────────────────────────────────────

16) 多维聚合 (GROUPING SETS / ROLLUP / CUBE)
【解释】一次 SQL 产出多层粒度聚合（小计/合计）。
【场景】地域 × 品类 × 合计报表。
【SQL】(ROLLUP 示例：region, product 明细+按 region 小计+总计)
SELECT region, product, SUM(sales) AS sales
FROM Sales
GROUP BY ROLLUP(region, product)
ORDER BY region NULLS LAST, product NULLS LAST;
【输入】
| region | product | sales |
|-------:|--------:|------:|
| East   | A       | 100   |
| East   | B       | 50    |
【输出】(含小计/总计行)
| region | product | sales |
|--------|---------|------:|
| East   | A       | 100   |
| East   | B       | 50    |
| East   | NULL    | 150   |
| NULL   | NULL    | 150   |

────────────────────────────────────────────────────────

17) 布尔转数值 (Boolean-as-Number)
【解释】CASE WHEN 条件 THEN 1 ELSE 0，便于加总/求率。
【场景】通过率/转化率/告警率。
【SQL】
SELECT
  SUM(CASE WHEN passed THEN 1 ELSE 0 END)::float / COUNT(*) AS pass_rate
FROM Exams;
【输入】
| id | passed |
|---:|:------:|
| 1  | true   |
| 2  | false  |
| 3  | true   |
【输出】
| pass_rate |
|----------:|
| 0.6667    |

────────────────────────────────────────────────────────

18) 分组布尔逻辑：ALL / ANY（用 MIN/MAX 实现）
【解释】组内“是否全满足/是否存在满足”：MIN/MAX 布尔标记实现。
【场景】某用户所有订单都完成？是否存在退款？
【SQL】
SELECT user_id,
       CASE WHEN MIN(CASE WHEN status='completed' THEN 1 ELSE 0 END)=1
            THEN true ELSE false END AS all_completed,
       CASE WHEN MAX(CASE WHEN status='refunded'  THEN 1 ELSE 0 END)=1
            THEN true ELSE false END AS any_refunded
FROM Orders
GROUP BY user_id;
【输入】
| user_id | status     |
|--------:|------------|
| 1       | completed  |
| 1       | completed  |
| 2       | completed  |
| 2       | refunded   |
【输出】
| user_id | all_completed | any_refunded |
|--------:|:-------------:|:------------:|
| 1       | true          | false        |
| 2       | false         | true         |

────────────────────────────────────────────────────────

19) EXISTS vs IN 的选择（性能/正确性思维）
【解释】EXISTS 对大表常更高效；IN 在子查询去重或 NULL 语义上可能带来差异。
【场景】过滤“存在关联记录”的主表行。
【SQL】(推荐 EXISTS)
SELECT u.*
FROM Users u
WHERE EXISTS (SELECT 1 FROM Orders o WHERE o.user_id=u.user_id AND o.amount>0);
-- 对比：WHERE user_id IN (SELECT user_id FROM Orders WHERE amount>0)
【输入】略
【输出】返回所有有正额订单的用户

────────────────────────────────────────────────────────

20) 子查询下推与谓词位置 (Predicate Pushdown)
【解释】能越早过滤越好；在聚合/连接前就缩小数据量。
【场景】复杂多表查询的性能优化。
【SQL】(先过滤再聚合)
WITH f AS (
  SELECT * FROM Orders WHERE order_date >= DATE '2025-08-01'
)
SELECT customer_id, SUM(amount)
FROM f
GROUP BY customer_id;
【输入】略
【输出】按客户汇总 8 月后的订单金额，更快

────────────────────────────────────────────────────────

21) 索引友好（SARGable）写法：范围而非函数封装
【解释】避免在索引列上包函数（如 DATE(col)）；改用可利用索引的范围谓词。
【场景】按天/周/月过滤明细表。
【SQL】
-- ❌ 不佳：函数在列上，难走索引
-- WHERE DATE(created_at) = DATE '2025-08-01'
-- ✅ 推荐：半开区间
WHERE created_at >= TIMESTAMP '2025-08-01 00:00:00'
  AND created_at <  TIMESTAMP '2025-08-02 00:00:00';
【输入】略
【输出】同样筛选 8/1 当天记录，但更易走索引

────────────────────────────────────────────────────────

附：MySQL/PG 小差异速记
- 生成日期：PG 用 GENERATE_SERIES；MySQL 8 可用递归 CTE 或数字表。
- 日期运算：PG 用 INTERVAL '1 day'；MySQL 用 INTERVAL 1 DAY。
- NULL 排序：PG 支持 NULLS FIRST/LAST；MySQL 通过排序表达式变通。

—— 完 —

```



### 2. 聚合函数+窗口函数模版

```sql
/* ================ 示例数据（一次执行，后续模板复用） ================ */
DROP TABLE IF EXISTS orders;
DROP TABLE IF EXISTS sales;

CREATE TABLE orders (
  customer_id VARCHAR(10),
  order_id    INT,
  amount      INT,
  order_date  DATE,
  PRIMARY KEY (customer_id, order_id)
);
-- A 客户放 4 单，便于分桶/分位数演示
INSERT INTO orders (customer_id, order_id, amount, order_date) VALUES
('A', 101, 300, '2023-01-01'),
('A', 102, 180, '2023-01-03'),
('A', 103, 220, '2023-01-05'),
('A', 104,  50, '2023-01-06'),
('B', 201, 500, '2023-01-02'),
('B', 202, 120, '2023-01-04'),
('B', 203, 500, '2023-01-06'), -- 与 201 并列，演示 rank/row_number 差异
('C', 301, 250, '2023-01-01');

CREATE TABLE sales (
  order_date  DATE PRIMARY KEY,
  amount      INT
);
INSERT INTO sales (order_date, amount) VALUES
('2023-01-01', 100),
('2023-01-02', 200),
('2023-01-03', 150),
('2023-01-04', 300),
('2023-01-05', 250);

/* ========== 说明 ==========
- 所有输出为“期望结果”以便对账；以数据库实际计算为准（四舍五入差异可能存在）。
- 需要“逐行推进”的累计/移动窗口时，请优先使用 ROWS 帧，避免默认 RANGE 帧的同位合并效应。
================================ */

/* ===========================================================
【1. 总体 vs 分组 vs 分组内排名】
场景与用途：
- 同时展示“全局上下文”（总行数/总金额）与“分组上下文”（每客户总额等）
- 在**不压缩明细**的前提下，给出**组内排名**（如客户内订单金额排名）
关键点：
- 全局：OVER()   分组：OVER(PARTITION BY ...)
- 排名要稳定：ORDER BY 后建议加次序键（如 order_id）
=========================================================== */
SELECT
  customer_id,
  order_id,
  amount,
  COUNT(*)  OVER()                              AS total_orders,     -- 全部订单总数
  SUM(amount) OVER()                            AS grand_total,      -- 全部订单总金额
  SUM(amount) OVER(PARTITION BY customer_id)    AS cust_total,       -- 每客户总消费
  RANK()     OVER(PARTITION BY customer_id ORDER BY amount DESC) AS order_rank -- 客户内排名（并列跳名次）
FROM orders
ORDER BY customer_id, order_rank, order_id;
/* 输入：orders
   期望输出（节选）：
   customer_id | order_id | amount | total_orders | grand_total | cust_total | order_rank
   A           | 101      | 300    | 8            | 2320        | 750        | 1
   A           | 103      | 220    | 8            | 2320        | 750        | 2
   A           | 102      | 180    | 8            | 2320        | 750        | 3
   A           | 104      |  50    | 8            | 2320        | 750        | 4
   B           | 201      | 500    | 8            | 2320        | 1120       | 1
   B           | 203      | 500    | 8            | 2320        | 1120       | 1
   B           | 202      | 120    | 8            | 2320        | 1120       | 3
   C           | 301      | 250    | 8            | 2320        | 250        | 1
*/


/* ===========================================================
【2. 累计统计（Running Total / Running Avg / Running Count）】
场景与用途：
- 时间序列趋势（累计和/累计均值/累计个数）
关键点：
- 逐行推进：显式 ROWS 帧（否则默认 RANGE 会合并同位值）
=========================================================== */
SELECT
  order_date,
  amount,
  SUM(amount)   OVER(ORDER BY order_date
                    ROWS BETWEEN UNBOUNDED PRECEDING AND CURRENT ROW) AS running_total,
  AVG(amount)   OVER(ORDER BY order_date
                    ROWS BETWEEN UNBOUNDED PRECEDING AND CURRENT ROW) AS running_avg,
  COUNT(*)      OVER(ORDER BY order_date
                    ROWS BETWEEN UNBOUNDED PRECEDING AND CURRENT ROW) AS running_count
FROM sales
ORDER BY order_date;
/* 输入：sales
   期望输出：
   date       | amt | running_total | running_avg | running_count
   2023-01-01 | 100 | 100           | 100.0       | 1
   2023-01-02 | 200 | 300           | 150.0       | 2
   2023-01-03 | 150 | 450           | 150.0       | 3
   2023-01-04 | 300 | 750           | 187.5       | 4
   2023-01-05 | 250 | 1000          | 200.0       | 5
*/


/* ===========================================================
【3. 分组内比例分析（占比/渗透/贡献度）】
场景与用途：
- 在每个“分组上下文”中给出当前行的占比（如客户内订单贡献度）
关键点：
- 用 SUM(...) OVER(PARTITION BY ...) 获取分母；注意除零
=========================================================== */
SELECT
  customer_id,
  order_id,
  amount,
  SUM(amount) OVER(PARTITION BY customer_id)                                     AS cust_total,
  ROUND(amount / NULLIF(SUM(amount) OVER(PARTITION BY customer_id),0), 4)         AS pct_within_customer
FROM orders
ORDER BY customer_id, amount DESC, order_id;
/* 输入：orders
   期望输出（A 组示例，cust_total=750）：
   customer_id | order_id | amount | pct_within_customer
   A           | 101      | 300    | 0.4000
   A           | 103      | 220    | 0.2933
   A           | 102      | 180    | 0.2400
   A           | 104      |  50    | 0.0667
*/


/* ===========================================================
【4. 排名前 N（Top-N with total/avg context）】
场景与用途：
- 每个分组取 Top-N，同时保留组内总额/均值作对比
关键点：
- MySQL 无 QUALIFY；需用子查询/CTE 先计算 rn，再外层 WHERE 过滤
=========================================================== */
WITH ranked AS (
  SELECT
    customer_id,
    order_id,
    amount,
    ROW_NUMBER() OVER(
      PARTITION BY customer_id
      ORDER BY amount DESC, order_id DESC  -- 并列时用 id 稳定顺序
    ) AS rn,
    SUM(amount) OVER(PARTITION BY customer_id) AS cust_total,
    AVG(amount) OVER(PARTITION BY customer_id) AS cust_avg
  FROM orders
)
SELECT customer_id, order_id, amount, cust_total, cust_avg, rn
FROM ranked
WHERE rn <= 2
ORDER BY customer_id, rn, order_id;
/* 输入：orders
   期望输出（每客户前 2 单）：
   A: (101, 300, rn=1), (103, 220, rn=2)   cust_total=750, cust_avg=250.0000
   B: (203, 500, rn=1), (201, 500, rn=2)   cust_total=1120, cust_avg=373.3333
   C: (301, 250, rn=1)                     cust_total=250,  cust_avg=250.0000
*/


/* ===========================================================
【5. 窗口聚合 + 移动分析（滚动窗口 & 环比）】
场景与用途：
- 移动累计/均值（如 7 日/30 日）
- 环比差值/增长率（当前 vs 上一期）
关键点：
- 移动窗口用 ROWS BETWEEN N PRECEDING AND CURRENT ROW
=========================================================== */
SELECT
  order_date,
  amount,
  SUM(amount) OVER(ORDER BY order_date ROWS BETWEEN 2 PRECEDING AND CURRENT ROW) AS roll3_sum, -- 近3天滚动和
  AVG(amount) OVER(ORDER BY order_date ROWS BETWEEN 2 PRECEDING AND CURRENT ROW) AS roll3_avg, -- 近3天滚动均值
  amount - LAG(amount,1,0) OVER(ORDER BY order_date)                              AS day_diff  -- 昨日差值
FROM sales
ORDER BY order_date;
/* 输入：sales
   期望输出（roll3_sum / roll3_avg）：
   date       | amt | roll3_sum | roll3_avg | day_diff
   2023-01-01 | 100 | 100       | 100.0     | 100
   2023-01-02 | 200 | 300       | 150.0     | 100
   2023-01-03 | 150 | 450       | 150.0     | -50
   2023-01-04 | 300 | 650       | 216.7     | 150
   2023-01-05 | 250 | 700       | 233.3     | -50
*/


/* ===========================================================
【6. 窗口聚合 + 分位数（组内分桶 & 百分位）】
场景与用途：
- 分层（四分位、十分位）、评分分布、客户分级
关键点：
- NTILE(4) 更适合行数≥4的分组；这里演示 A 客户
=========================================================== */
SELECT
  customer_id,
  order_id,
  amount,
  NTILE(4)       OVER(PARTITION BY customer_id ORDER BY amount)        AS quartile,
  PERCENT_RANK() OVER(PARTITION BY customer_id ORDER BY amount)         AS pct_rank
FROM orders
WHERE customer_id = 'A'
ORDER BY amount, order_id;
/* 输入：A 组 amounts：50, 180, 220, 300
   期望输出（A 组）：
   order_id | amount | quartile | pct_rank
   104      |  50    | 1        | 0.0000
   102      | 180    | 2        | 0.3333
   103      | 220    | 3        | 0.6667
   101      | 300    | 4        | 1.0000
*/


/* =====================【新增高频模板 1】总体占比 =====================
场景与用途：
- 每行占全局总量的比例（RATIO-TO-REPORT）
关键点：
- 分母可用 SUM(...) OVER()；注意除零；保留明细
================================================================= */
SELECT
  customer_id, order_id, amount,
  SUM(amount) OVER() AS grand_total,
  ROUND(amount / NULLIF(SUM(amount) OVER(),0), 4) AS pct_of_total
FROM orders
ORDER BY amount DESC, customer_id, order_id;
/* 输入：orders；grand_total = 2320
   期望输出（节选）：
   500 → 0.2155（两行）
   300 → 0.1293
   250 → 0.1078
   220 → 0.0948
   180 → 0.0776
   120 → 0.0517
   50  → 0.0216
*/


/* =====================【新增高频模板 2】帕累托累计占比 =====================
场景与用途：
- 订单按金额从高到低累积贡献占比（80/20 分析）
关键点：
- 先按金额降序计算 running_total，再 / grand_total
===================================================================== */
WITH ranked AS (
  SELECT
    customer_id, order_id, amount,
    SUM(amount) OVER() AS grand_total,
    SUM(amount) OVER(ORDER BY amount DESC, customer_id, order_id) AS running_total_desc
  FROM orders
)
SELECT
  customer_id, order_id, amount,
  running_total_desc,
  ROUND(running_total_desc / grand_total, 4) AS cumulative_share
FROM ranked
ORDER BY amount DESC, customer_id, order_id;
/* 输入：orders
   期望输出（节选，金额降序累积占比）：
   500 → 0.2155 → 0.4310（两行累计）
   300 → 0.5603
   250 → 0.6681
   220 → 0.7629
   180 → 0.8405
   120 → 0.8922
   50  → 0.9138
*/


/* =====================【新增高频模板 3】同/环比率 =====================
场景与用途：
- 环比增幅、增长率
关键点：
- LAG 获取上一期；注意除零
================================================================= */
SELECT
  order_date,
  amount,
  LAG(amount) OVER(ORDER BY order_date) AS prev_amt,
  ROUND((amount - LAG(amount) OVER(ORDER BY order_date))
        / NULLIF(LAG(amount) OVER(ORDER BY order_date),0), 4) AS mom_rate
FROM sales
ORDER BY order_date;
/* 输入：sales
   期望输出（mom_rate 环比率）：
   2023-01-01 | 100 | NULL | NULL
   2023-01-02 | 200 | 100  | 1.0000
   2023-01-03 | 150 | 200  | -0.2500
   2023-01-04 | 300 | 150  | 1.0000
   2023-01-05 | 250 | 300  | -0.1667
*/

```

### 3. SQL 模版

```sql
/* ============================================================
   MySQL Demo Script: Common SQL Patterns & JOIN Scenarios (Plus)
   --------------------------------------------------------------
   内容增强点：
   - 每个模板都补充【适用场景/用途】
   - 如原 SQL 不完善，已给出【改进版 SQL】
   - 为每个 SQL 都标注【输入表】与【期望输出】（注释内，便于核对）
   - 新增实战模板：总体占比、帕累托累计占比、缺失段落定位、反连接计数、
     反重叠区间连接、窗口与非窗口对照写法等。
   说明：
   - 兼容 MySQL 8+（含窗口函数）；在可行处给出无窗口替代。
   - 结果以数据库实际计算为准；注释输出按本示例数据推导。
   ============================================================ */

-- 建议使用干净 Schema（可按需启用）
-- CREATE SCHEMA IF NOT EXISTS demo;
-- USE demo;

SET sql_mode = '';

-- ========== 0. 清理旧表 ==========
DROP TABLE IF EXISTS sign_log;
DROP TABLE IF EXISTS sales;
DROP TABLE IF EXISTS salary_grade;
DROP TABLE IF EXISTS employees;
DROP TABLE IF EXISTS products;
DROP TABLE IF EXISTS orders;
DROP TABLE IF EXISTS customers;
DROP TABLE IF EXISTS users;
DROP TABLE IF EXISTS dates;

-- ========== 1. 基础表结构与示例数据 ==========

-- 1.1 客户/订单
CREATE TABLE customers (
  id INT PRIMARY KEY,
  name VARCHAR(50)
);

CREATE TABLE orders (
  id INT PRIMARY KEY,
  customer_id INT,
  product VARCHAR(50),
  amount DECIMAL(10,2),
  order_date DATE,
  FOREIGN KEY (customer_id) REFERENCES customers(id)
);

INSERT INTO customers (id, name) VALUES
(1, 'Tom'),
(2, 'Alice'),
(3, 'Bob'),
(4, 'Carol');

INSERT INTO orders (id, customer_id, product, amount, order_date) VALUES
(1, 1, 'Bike',   300.00, '2025-07-01'),
(2, 1, 'Lock',    20.00, '2025-07-05'),
(3, 2, 'Book',    15.00, '2025-07-03'),
(4, 2, 'Lamp',    25.00, '2025-07-10'),
(5, 2, 'Lamp',    25.00, '2025-07-12'),
(6, 4, 'Phone',  500.00, '2025-07-02');

-- 1.2 用户表（演示缺失/存在性）
CREATE TABLE users (
  user_id INT PRIMARY KEY,
  city VARCHAR(50)
);

INSERT INTO users (user_id, city) VALUES
(1, 'NY'),
(2, 'SF'),
(3, 'LA'),
(5, 'SEA'); -- 注意：用户 5 在订单中不存在

-- 1.3 产品表（用于 CROSS JOIN 演示）
CREATE TABLE products (
  product_id INT PRIMARY KEY,
  product_name VARCHAR(50)
);

INSERT INTO products (product_id, product_name) VALUES
(10, 'A'),
(11, 'B'),
(12, 'C');

-- 1.4 员工/工资等级（NON-EQUI JOIN 与 SELF JOIN 演示）
CREATE TABLE employees (
  emp_id INT PRIMARY KEY,
  name VARCHAR(50),
  manager_id INT NULL,
  salary INT
);

INSERT INTO employees (emp_id, name, manager_id, salary) VALUES
(100, 'CEO',   NULL, 30000),
(101, 'M1',    100,  20000),
(102, 'M2',    100,  19000),
(201, 'E1',    101,  12000),
(202, 'E2',    101,  11000),
(203, 'E3',    102,  13000);

CREATE TABLE salary_grade (
  grade CHAR(1) PRIMARY KEY,
  min_salary INT,
  max_salary INT
);

INSERT INTO salary_grade (grade, min_salary, max_salary) VALUES
('A', 25000, 999999),
('B', 18000, 24999),
('C', 12000, 17999),
('D',  8000, 11999);

-- 1.5 销售/签到/日期维表（累计、缺失、断档）
CREATE TABLE sales (
  day DATE,
  user_id INT,
  amount DECIMAL(10,2)
);

INSERT INTO sales (day, user_id, amount) VALUES
('2025-07-01', 1, 100.00),
('2025-07-02', 1, 120.00),
('2025-07-04', 1,  80.00),  -- 故意缺 7/03
('2025-07-01', 2,  50.00),
('2025-07-03', 2, 200.00);

-- 简单的连续日期表（演示用途，可自行扩展）
CREATE TABLE dates (day DATE PRIMARY KEY);
INSERT INTO dates (day) VALUES
('2025-07-01'),('2025-07-02'),('2025-07-03'),('2025-07-04'),('2025-07-05');

-- 签到表：用于“断档”检查
CREATE TABLE sign_log (
  user_id INT,
  sign_date DATE
);
INSERT INTO sign_log (user_id, sign_date) VALUES
(1, '2025-07-01'),
(1, '2025-07-02'),
(1, '2025-07-05'), -- 中间断 7/03~7/04
(2, '2025-07-02'),
(2, '2025-07-03');

/* ============================================================
   2) Top-N 模板
   ============================================================ */

-- 2.1【全局 Top-N 客户】
-- 场景/用途：找到消费最高的 N 个客户（榜单、资源倾斜）
-- 思路：按客户聚合 → 排序 → LIMIT N
SELECT c.id, c.name, SUM(o.amount) AS total_amount
FROM customers c
JOIN orders o ON o.customer_id = c.id
GROUP BY c.id, c.name
ORDER BY total_amount DESC
LIMIT 3;
/* 输入：customers + orders
   期望输出（按本数据）：
   id | name  | total_amount
   4  | Carol | 500.00
   1  | Tom   | 320.00
   2  | Alice | 65.00
*/

-- 2.2【分组 Top-N：每客户消费最高的前 2 个商品】（MySQL 8+）
-- 场景/用途：客户内做 Top-N 商品推荐/复购分析
WITH agg AS (
  SELECT customer_id, product, SUM(amount) AS product_amount
  FROM orders
  GROUP BY customer_id, product
),
ranked AS (
  SELECT *,
         ROW_NUMBER() OVER (
           PARTITION BY customer_id
           ORDER BY product_amount DESC, product
         ) AS rn
  FROM agg
)
SELECT *
FROM ranked
WHERE rn <= 2
ORDER BY customer_id, rn;
/* 期望输出：
   customer_id | product | product_amount | rn
   1           | Bike    | 300.00         | 1
   1           | Lock    | 20.00          | 2
   2           | Lamp    | 50.00          | 1
   2           | Book    | 15.00          | 2
   4           | Phone   | 500.00         | 1
*/

-- 2.3【分组 Top-N（兼容写法，无窗口）】
-- 思路：同组内“比我大的个数 < N”即 Top-N
SELECT a.customer_id, a.product, a.product_amount
FROM (
  SELECT customer_id, product, SUM(amount) AS product_amount
  FROM orders
  GROUP BY customer_id, product
) a
LEFT JOIN (
  SELECT customer_id, product, SUM(amount) AS product_amount
  FROM orders
  GROUP BY customer_id, product
) b
ON a.customer_id = b.customer_id
AND b.product_amount > a.product_amount
GROUP BY a.customer_id, a.product, a.product_amount
HAVING COUNT(b.product) < 2
ORDER BY a.customer_id, a.product;
/* 期望输出：与 2.2 逻辑一致（可能顺序不同） */




/* ============================================================
   3) 去重模板
   ============================================================ */

-- 3.1【结果去重】去重 (customer_id, product)
SELECT DISTINCT customer_id, product
FROM orders
ORDER BY customer_id, product;
/* 输出（节选）：(1,Bike),(1,Lock),(2,Book),(2,Lamp),(4,Phone) */

-- 3.2【每客户最新订单】（MySQL 8+，窗口法，稳定次序）
WITH w AS (
  SELECT o.*,
         ROW_NUMBER() OVER (
           PARTITION BY customer_id
           ORDER BY order_date DESC, id DESC
         ) AS rn
  FROM orders o
)
SELECT *
FROM w
WHERE rn = 1
ORDER BY customer_id;
/* 期望输出：
   id | customer_id | product | amount | order_date
   2  | 1           | Lock    | 20.00  | 2025-07-05
   5  | 2           | Lamp    | 25.00  | 2025-07-12
   6  | 4           | Phone   | 500.00 | 2025-07-02
*/

-- 3.3【每客户最新订单】（兼容写法，子查询最大日期 + tie-break）
SELECT o.*
FROM orders o
JOIN (
  SELECT customer_id, MAX(order_date) AS max_dt
  FROM orders
  GROUP BY customer_id
) m
  ON o.customer_id = m.customer_id
 AND o.order_date  = m.max_dt
WHERE NOT EXISTS (
  SELECT 1
  FROM orders t
  WHERE t.customer_id = o.customer_id
    AND t.order_date  = o.order_date
    AND t.id          > o.id  -- 同日多单，取 id 最大
);
/* 同 3.2 输出 */

-- 3.4【规则去重】两天内重复同商品仅保留第一条（MySQL 8+）
WITH w AS (
  SELECT o.*,
         LAG(order_date) OVER (
           PARTITION BY customer_id, product ORDER BY order_date, id
         ) AS prev_dt
  FROM orders o
)
SELECT *
FROM w
WHERE prev_dt IS NULL OR DATEDIFF(order_date, prev_dt) > 2
ORDER BY customer_id, order_date;
/* 输出：去掉 Alice 在 7/10 与 7/12 连续 Lamp 的第二条（间隔<=2） */




/* ============================================================
   4) 缺失数据模板
   ============================================================ */

-- 4.1【补齐日期】无交易日显示 0
SELECT d.day,
       COALESCE(SUM(s.amount), 0) AS daily_amount
FROM dates d
LEFT JOIN sales s ON s.day = d.day
GROUP BY d.day
ORDER BY d.day;
/* 期望输出（节选）:
   2025-07-01 | 150.00
   2025-07-02 | 120.00
   2025-07-03 | 200.00
   2025-07-04 |  80.00
   2025-07-05 |  0.00
*/

-- 4.2【找缺失】从未下单的用户（NOT EXISTS，推荐）
SELECT u.*
FROM users u
WHERE NOT EXISTS (SELECT 1 FROM orders o WHERE o.customer_id = u.user_id)
ORDER BY u.user_id;
/* 输出：user_id = 3, 5 */

-- 4.3【找断档】签到断点（两次签到相差>1天）
WITH w AS (
  SELECT user_id, sign_date,
         LAG(sign_date) OVER (PARTITION BY user_id ORDER BY sign_date) AS prev_dt
  FROM sign_log
)
SELECT *
FROM w
WHERE prev_dt IS NOT NULL AND DATEDIFF(sign_date, prev_dt) > 1
ORDER BY user_id, sign_date;
/* 期望输出：user_id=1 在 2025-07-05 有断档（与前次相差>1天） */

-- 4.4【缺失段落定位】基于“岛屿与间隙”法标出连续段（MySQL 8+）
WITH w AS (
  SELECT user_id, sign_date,
         ROW_NUMBER() OVER (PARTITION BY user_id ORDER BY sign_date) AS rn
  FROM sign_log
),
grp AS (
  SELECT user_id, sign_date, DATE_SUB(sign_date, INTERVAL rn DAY) AS grp_key
  FROM w
)
SELECT user_id,
       MIN(sign_date) AS span_start,
       MAX(sign_date) AS span_end,
       DATEDIFF(MAX(sign_date), MIN(sign_date)) + 1 AS span_days,
       COUNT(*) AS signed_days
FROM grp
GROUP BY user_id, grp_key
ORDER BY user_id, span_start;
/* 期望输出（user_id=1）两段：2025-07-01~2025-07-02 与 2025-07-05~2025-07-05 */




/* ============================================================
   5) 累计统计模板
   ============================================================ */

-- 5.1【累计按天】Running Total（MySQL 8+）
SELECT day,
       SUM(amount) AS daily_amt,
       SUM(SUM(amount)) OVER (ORDER BY day) AS running_amt
FROM sales
GROUP BY day
ORDER BY day;
/* 输出（节选）：
   07-01 daily=150  running=150
   07-02 daily=120  running=270
   07-03 daily=200  running=470
   07-04 daily=80   running=550
*/

-- 5.2【分组累计】每用户累计（MySQL 8+）
SELECT user_id, day,
       SUM(amount) AS daily_amt,
       SUM(SUM(amount)) OVER (PARTITION BY user_id ORDER BY day) AS running_amt_user
FROM sales
GROUP BY user_id, day
ORDER BY user_id, day;
/* 输出（节选）：user 1: 100→220→300；user 2: 50→250 */

-- 5.3【移动平均】7 日窗口（演示写法）
SELECT day,
       AVG(SUM(amount)) OVER (
         ORDER BY day
         ROWS BETWEEN 6 PRECEDING AND CURRENT ROW
       ) AS ma7
FROM sales
GROUP BY day
ORDER BY day;
/* 输出：逐日滚动平均，前期因不足 7 日按现有行平均 */

-- 5.4【环比（MoM）】（月级）
WITH m AS (
  SELECT DATE_FORMAT(day, '%Y-%m-01') AS mon,
         SUM(amount) AS amt
  FROM sales
  GROUP BY DATE_FORMAT(day, '%Y-%m-01')
)
SELECT mon,
       amt,
       LAG(amt) OVER (ORDER BY mon) AS prev_mon_amt,
       CASE WHEN LAG(amt) OVER (ORDER BY mon) IS NULL THEN NULL
            ELSE ROUND((amt - LAG(amt) OVER (ORDER BY mon))
                       / LAG(amt) OVER (ORDER BY mon), 4)
       END AS mom_rate
FROM m
ORDER BY mon;
/* 数据全在 2025-07，示例输出仅示意结构 */

-- 5.5【总体占比】RATIO-TO-REPORT（新增）
SELECT o.*,
       SUM(amount) OVER () AS grand_total,
       ROUND(amount / NULLIF(SUM(amount) OVER (), 0), 4) AS pct_of_total
FROM orders o
ORDER BY amount DESC, id;
/* 输出（节选）：500→占比最高，20→占比最低 */

-- 5.6【帕累托累计占比】（新增）
WITH ranked AS (
  SELECT o.*,
         SUM(amount) OVER() AS grand_total,
         SUM(amount) OVER(ORDER BY amount DESC, id DESC) AS running_desc
  FROM orders o
)
SELECT id, customer_id, product, amount,
       running_desc,
       ROUND(running_desc / grand_total, 4) AS cumulative_share
FROM ranked
ORDER BY amount DESC, id DESC;
/* 输出：金额降序的累计贡献占比，可观察 80/20 拐点 */




/* ============================================================
   6) 经典 JOIN 模板（含示例）
   ============================================================ */

-- 6.1【INNER JOIN】只要匹配的行
SELECT c.name, o.product, o.amount
FROM customers c
INNER JOIN orders o ON c.id = o.customer_id
ORDER BY c.id, o.id;
/* 输入：customers + orders
   输出：仅 1(Tom)、2(Alice)、4(Carol) 的订单；3(Bob) 无（未下单） */

-- 6.2【LEFT JOIN】保留左表全部（常用于查“有/无”）
SELECT c.name, o.product, o.amount
FROM customers c
LEFT JOIN orders o ON c.id = o.customer_id
ORDER BY c.id, o.id;
/* 输出：含 Bob 的行（其订单列为 NULL） */

-- 6.3【RIGHT JOIN】保留右表全部
SELECT c.name, o.product, o.amount
FROM customers c
RIGHT JOIN orders o ON c.id = o.customer_id
ORDER BY o.id;
/* 输出：与 INNER + 左侧缺失客户的组合 */

-- 6.4【FULL OUTER JOIN（模拟）】保留两边全部
SELECT c.id AS c_id, c.name, o.id AS o_id, o.product, o.amount
FROM customers c
LEFT JOIN orders o ON c.id = o.customer_id
UNION
SELECT c.id AS c_id, c.name, o.id AS o_id, o.product, o.amount
FROM customers c
RIGHT JOIN orders o ON c.id = o.customer_id;
/* 输出：等价 FULL OUTER；如需区分来源可加来源标识列 */

-- 6.5【CROSS JOIN】笛卡尔积（所有组合）
SELECT u.user_id, p.product_id
FROM users u
CROSS JOIN products p
ORDER BY u.user_id, p.product_id;
/* 输出：4*3=12 行 */

-- 6.6【SELF JOIN】员工与上级
SELECT e.emp_id, e.name AS employee, m.name AS manager
FROM employees e
LEFT JOIN employees m ON e.manager_id = m.emp_id
ORDER BY e.emp_id;
/* 输出：CEO 的 manager 为 NULL */

-- 6.7【NON-EQUI JOIN】工资映射到区间等级
SELECT e.emp_id, e.name, e.salary, g.grade
FROM employees e
JOIN salary_grade g
  ON e.salary BETWEEN g.min_salary AND g.max_salary
ORDER BY e.emp_id;
/* 输出：CEO→A，M1/M2→B，E1/E3→C，E2→D */

-- 6.8【SEMI JOIN】有下单的客户（EXISTS）
SELECT c.*
FROM customers c
WHERE EXISTS (SELECT 1 FROM orders o WHERE o.customer_id = c.id)
ORDER BY c.id;
/* 输出：id = 1,2,4 */

-- 6.9【ANTI JOIN】从未下单的客户（NOT EXISTS）
SELECT c.*
FROM customers c
WHERE NOT EXISTS (SELECT 1 FROM orders o WHERE o.customer_id = c.id)
ORDER BY c.id;
/* 输出：id = 3 */

-- 6.10【LATERAL 子查询】每客户最近一单（MySQL 8.0.14+ 派生表可相关）
-- 若版本不支持，可用窗口函数或相关子查询替代
SELECT c.id, c.name, t.id AS last_order_id, t.order_date, t.amount
FROM customers c
LEFT JOIN LATERAL (
  SELECT o.*
  FROM orders o
  WHERE o.customer_id = c.id
  ORDER BY o.order_date DESC, o.id DESC
  LIMIT 1
) AS t ON TRUE
ORDER BY c.id;
/* 输出：与 3.2“最新订单”效果一致 */

-- 6.11【ANTI JOIN（另一种写法）】LEFT JOIN ... IS NULL
SELECT c.*
FROM customers c
LEFT JOIN orders o ON o.customer_id = c.id
WHERE o.id IS NULL;
/* 输出：id = 3 */

-- 6.12【STRAIGHT_JOIN（调优）】强制按书写顺序连接
SELECT STRAIGHT_JOIN c.name, o.product, o.amount
FROM customers c
JOIN orders o ON o.customer_id = c.id
ORDER BY c.id, o.id;
/* 对小数据无差异；大查询可用于优化执行计划 */


-- ===================== THE END =====================

```

