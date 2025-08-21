下面把**聚合函数（Aggregate Functions）**讲清楚：它们的概念、用法细节（`GROUP BY` / `HAVING` / `DISTINCT` / `NULL` 处理）、常见模板、易踩的坑，并配上能直接拿去改的 SQL 例子（以 **MySQL 8+** 为主，其他主流方言大同小异）。

------

# 1）聚合函数是什么？

**定义**：把**多行**数据**汇总**为**一行**（或每组一行）的函数。
典型有：`COUNT / SUM / AVG / MIN / MAX`，以及 MySQL 里的 `GROUP_CONCAT / JSON_ARRAYAGG / JSON_OBJECTAGG / VAR_SAMP / STDDEV_SAMP` 等。

- **没有 `GROUP BY`** → 汇总**全表**（筛完后的结果集）成一行。
- **有 `GROUP BY`** → **按组**汇总，每组一行。
- 与**窗口函数**相反：窗口函数**不减少行数**，而是在明细行上新增“汇总/排名/累计”等“分析列”。

------

# 2）评价顺序 & 放在哪里用

1. `FROM/JOIN`
2. `WHERE`（此时还**不能**用聚合结果）
3. `GROUP BY`（在这里决定分组）
4. **聚合函数计算**
5. `HAVING`（这里**可以**用聚合结果过滤分组）
6. `SELECT` 输出
7. `ORDER BY` / `LIMIT`

> **口诀**：行过滤**用 `WHERE`**；**聚合后再过滤用 `HAVING`**。

------

# 3）常见聚合函数 + 行为差异

| 函数                           | 说明                | 对 NULL 的处理           | 常见坑/要点                                                |
| ------------------------------ | ------------------- | ------------------------ | ---------------------------------------------------------- |
| `COUNT(*)`                     | 统计行数            | 统计所有行（含 NULL 列） | 空结果集返回 0                                             |
| `COUNT(expr)`                  | 统计**非 NULL**的行 | 忽略 NULL                | 如 `COUNT(col)` 与 `COUNT(*)` 结果可能不同                 |
| `COUNT(DISTINCT col)`          | 去重计数            | 忽略 NULL                | MySQL 支持 `COUNT(DISTINCT col1, col2)` 统计**组合**去重数 |
| `SUM(expr)` / `AVG(expr)`      | 求和/均值           | 忽略 NULL                | 空组：`SUM/AVG` 返回 **NULL**（与 `COUNT` 的 0 不同）      |
| `MIN/MAX(expr)`                | 最小/最大           | 忽略 NULL                | 字符/日期同样可比较                                        |
| `GROUP_CONCAT(expr)`           | 同组拼接            | 忽略 NULL                | MySQL 专属；可 `ORDER BY`、`SEPARATOR`自定义分隔符         |
| `JSON_ARRAYAGG/JSON_OBJECTAGG` | 同组聚成 JSON       | 忽略 NULL 键值（视实现） | MySQL 8+；便于给上游 API                                   |
| `VAR_SAMP/STDDEV_SAMP`         | 样本方差/样本标准差 | 忽略 NULL                | 空组返回 NULL；单样本标准差为 NULL                         |

**空组返回值**（重要）：

- `COUNT(*)` → 0
- 其他（`SUM/AVG/MIN/MAX/...`）→ 通常 **NULL**

------

# 4）最小可跑示例（理解核心差异）

假设：

```sql
CREATE TABLE orders (
  customer_id VARCHAR(10),
  order_id    INT,
  amount      INT,
  order_date  DATE
);

INSERT INTO orders VALUES
('A', 101, 300, '2023-01-01'),
('A', 102, 200, '2023-01-02'),
('A', 103, 200, '2023-01-03'),
('B', 201, 500, '2023-01-01'),
('B', 202, 100, '2023-01-02');
```

## 4.1 全表汇总（无 GROUP BY）

```sql
SELECT
  COUNT(*)        AS rows_all,          -- 5
  SUM(amount)     AS sum_all,           -- 1300
  AVG(amount)     AS avg_all,           -- 260
  MIN(amount)     AS min_all,           -- 100
  MAX(amount)     AS max_all            -- 500
FROM orders;
```

## 4.2 分组汇总（有 GROUP BY）

```sql
SELECT
  customer_id,
  COUNT(*)     AS order_cnt,            -- 每个客户的订单数
  SUM(amount)  AS total_amount,         -- 每个客户总金额
  AVG(amount)  AS avg_amount
FROM orders
GROUP BY customer_id
ORDER BY total_amount DESC;
```

**输出**（按上面数据）：

- A：3 单，总额 700，均值 ~233.33
- B：2 单，总额 600，均值 300

## 4.3 WHERE vs HAVING

```sql
-- 先筛明细，再汇总
SELECT customer_id, SUM(amount) AS total_amount
FROM orders
WHERE order_date >= '2023-01-02'     -- 明细条件（不允许聚合）
GROUP BY customer_id
HAVING SUM(amount) >= 300;           -- 聚合后过滤条件
```

------

# 5）`DISTINCT` 聚合与“条件聚合”模板

## 5.1 DISTINCT 聚合

```sql
-- 去重计数：有多少个不同的订单日期
SELECT COUNT(DISTINCT order_date) AS d_dates FROM orders;

-- 组合去重：不同(客户, 日期)的对数
SELECT COUNT(DISTINCT customer_id, order_date) AS d_pairs FROM orders;

-- 去重求和/均值（单表达式）
SELECT
  SUM(DISTINCT amount) AS sum_distinct_amount,
  AVG(DISTINCT amount) AS avg_distinct_amount
FROM orders;
```

## 5.2 条件聚合（超高频）

**计数/分布**：

```sql
SELECT
  customer_id,
  COUNT(*) AS order_cnt,
  SUM(CASE WHEN amount >= 300 THEN 1 ELSE 0 END) AS cnt_ge_300,
  SUM(CASE WHEN amount BETWEEN 200 AND 299 THEN 1 ELSE 0 END) AS cnt_200_299,
  SUM(CASE WHEN amount < 200  THEN 1 ELSE 0 END) AS cnt_lt_200
FROM orders
GROUP BY customer_id;
```

**小型透视表（行转列经典写法）**：

```sql
SELECT
  customer_id,
  SUM(CASE WHEN order_date = '2023-01-01' THEN amount ELSE 0 END) AS amt_d1,
  SUM(CASE WHEN order_date = '2023-01-02' THEN amount ELSE 0 END) AS amt_d2,
  SUM(CASE WHEN order_date = '2023-01-03' THEN amount ELSE 0 END) AS amt_d3
FROM orders
GROUP BY customer_id;
```

**条件去重计数**：

```sql
SELECT
  customer_id,
  COUNT(DISTINCT CASE WHEN amount >= 200 THEN order_id END) AS uniq_orders_ge200
FROM orders
GROUP BY customer_id;
```

------

# 6）字符串/JSON 聚合（MySQL 常用）

```sql
-- 同组拼接（可排序 & 自定义分隔符）
SELECT
  customer_id,
  GROUP_CONCAT(order_id ORDER BY order_date SEPARATOR ',') AS order_ids
FROM orders
GROUP BY customer_id;

-- 同组聚成 JSON 数组 / 对象（MySQL 8+）
SELECT
  customer_id,
  JSON_ARRAYAGG(order_id ORDER BY order_date)                        AS order_ids_json,
  JSON_OBJECTAGG(order_id, amount)                                   AS order_amount_map
FROM orders
GROUP BY customer_id;
```

------

# 7）小计/总计：`GROUP BY ... WITH ROLLUP`（MySQL）

```sql
SELECT customer_id, SUM(amount) AS total_amount
FROM orders
GROUP BY customer_id WITH ROLLUP;
```

**输出**（多出一行总计，`customer_id = NULL`）：

- A | 700
- B | 600
- NULL | 1300 ← **总计**（注意：若真实数据里 `customer_id` 可能为 NULL，会混淆。MySQL 8 提供 `GROUPING()` 可区分，不同版本支持度略异，若无则用额外字段标记。）

------

# 8）连接（JOIN）与“重复行”的坑

聚合前 JOIN 容易**重复放大**明细（多对多/一对多），导致**总额翻倍**。**两种避免思路**：

### 方式 A：先在子查询里**预聚合**，再 JOIN

```sql
-- 订单行表按 order_id 先求小计，再与订单头表汇总
WITH items_agg AS (
  SELECT order_id, SUM(line_amount) AS item_total
  FROM order_items
  GROUP BY order_id
)
SELECT o.customer_id, SUM(a.item_total) AS total_amount
FROM orders o
JOIN items_agg a USING(order_id)
GROUP BY o.customer_id;
```

### 方式 B：JOIN 之后**立刻**只取必要键去重/预聚合

```sql
SELECT o.customer_id, SUM(t.item_total) AS total_amount
FROM orders o
JOIN (
  SELECT order_id, SUM(line_amount) AS item_total
  FROM order_items
  GROUP BY order_id
) AS t ON t.order_id = o.order_id
GROUP BY o.customer_id;
```

------

# 9）与窗口函数的关系（别混）

- **聚合**：**缩行**（多行 → 一行/每组一行），做**报表汇总**。
- **窗口**：**不缩行**，在明细上加**累计/排名/同环比**等分析列。
- 有时需要**两者配合**：例如“每组 Top-N 订单 + 组内总额” → 子查询用窗口函数打标 `rn<=N`，外层再聚合。

------

# 10）常用模板（收藏）

### 10.1 全表/分组汇总

```sql
-- 全表
SELECT SUM(amount) AS total FROM orders;

-- 分组
SELECT customer_id, SUM(amount) AS total
FROM orders
GROUP BY customer_id;
```

### 10.2 条件聚合

```sql
SELECT
  customer_id,
  SUM(CASE WHEN amount>=300 THEN 1 ELSE 0 END) AS cnt_high,
  SUM(CASE WHEN amount<300  THEN 1 ELSE 0 END) AS cnt_low
FROM orders
GROUP BY customer_id;
```

### 10.3 去重计数

```sql
SELECT COUNT(DISTINCT customer_id) AS uniq_customers FROM orders;
```

### 10.4 取 “有数据的组”（与 HAVING 配合）

```sql
SELECT customer_id, SUM(amount) AS total
FROM orders
GROUP BY customer_id
HAVING SUM(amount) > 500;   -- 聚合后过滤
```

### 10.5 小计 + 总计

```sql
SELECT customer_id, SUM(amount) AS total
FROM orders
GROUP BY customer_id WITH ROLLUP;
```

### 10.6 字符/JSON 聚合

```sql
SELECT customer_id, GROUP_CONCAT(order_id ORDER BY order_date) AS order_ids
FROM orders GROUP BY customer_id;

SELECT customer_id, JSON_ARRAYAGG(order_id ORDER BY order_date) AS order_ids_json
FROM orders GROUP BY customer_id;
```

------

# 11）易错点 Checklist

- `COUNT(*)` 与 `COUNT(col)` 不同：后者**不数 NULL**。
- 空组：`COUNT(*)=0`，`SUM/AVG/MIN/MAX` 多为 **NULL**。
- `HAVING` 是聚合后的过滤；别把聚合条件写进 `WHERE`。
- `DISTINCT` 可能很贵（去重）；必要时先**预聚合**或用合适索引。
- JOIN 后再聚合易**双倍计数**：先预聚合/去重再 JOIN。
- `GROUP_CONCAT` 有长度限制（`group_concat_max_len`），过长会被截断。
- 打开 `ONLY_FULL_GROUP_BY` 时，`SELECT` 中非聚合列必须在 `GROUP BY`（或函数依赖）。

------

# 12）常用聚合函数

```sql
/* ================= 常用聚合函数模板 =================

【1. COUNT() - 计数】
--------------------------------------------------
场景：
- 统计总行数、去重数量
- 判断是否有数据
示例：
-- 统计所有订单数
SELECT COUNT(*) AS total_orders FROM orders;

-- 统计不同客户数
SELECT COUNT(DISTINCT customer_id) AS unique_customers FROM orders;


【2. SUM() - 求和】
--------------------------------------------------
场景：
- 计算金额、销量、分数总和
示例：
-- 总销售额
SELECT SUM(amount) AS total_sales FROM orders;

-- 每个客户的总消费
SELECT customer_id, SUM(amount) AS total_spent
FROM orders
GROUP BY customer_id;


【3. AVG() - 平均值】
--------------------------------------------------
场景：
- 计算平均消费、平均成绩
示例：
-- 平均订单金额
SELECT AVG(amount) AS avg_order_amount FROM orders;

-- 每个客户的平均订单金额
SELECT customer_id, AVG(amount) AS avg_spent
FROM orders
GROUP BY customer_id;


【4. MIN() - 最小值】
--------------------------------------------------
场景：
- 找到最小金额、最早日期、最低分
示例：
-- 最早下单时间
SELECT MIN(order_date) AS first_order FROM orders;

-- 每个客户的最低消费金额
SELECT customer_id, MIN(amount) AS min_amount
FROM orders
GROUP BY customer_id;


【5. MAX() - 最大值】
--------------------------------------------------
场景：
- 找到最大金额、最晚日期、最高分
示例：
-- 最大订单金额
SELECT MAX(amount) AS max_order_amount FROM orders;

-- 每个客户的最大单笔消费
SELECT customer_id, MAX(amount) AS max_amount
FROM orders
GROUP BY customer_id;


【6. GROUP_CONCAT() - 拼接字符串（MySQL 专用）】
--------------------------------------------------
场景：
- 把分组内的值拼接成一个字符串
示例：
-- 每个客户的所有订单号拼成一行
SELECT customer_id, GROUP_CONCAT(order_id) AS order_list
FROM orders
GROUP BY customer_id;


【7. HAVING 与聚合函数结合】
--------------------------------------------------
场景：
- 在聚合结果上做过滤
示例：
-- 筛选出消费总额超过 1000 的客户
SELECT customer_id, SUM(amount) AS total_spent
FROM orders
GROUP BY customer_id
HAVING SUM(amount) > 1000;


================================================================
总结：
- COUNT() → 数量
- SUM()   → 总和
- AVG()   → 平均
- MIN()   → 最小
- MAX()   → 最大
- GROUP_CONCAT() → 拼接字符串
================================================================
*/

```

