### 1. 窗口函数

```sql
/* ====================== 常用窗口函数总结 ======================

【1. 排序与排名类】
--------------------------------------------------------------
1) ROW_NUMBER()
   - 作用：按分组排序后，为每行生成唯一递增编号（无并列）
   - 用途：Top-N、每组取最新/最早一条记录
   示例：
   SELECT customer_id, order_id, amount,
          ROW_NUMBER() OVER(PARTITION BY customer_id ORDER BY amount DESC) AS rn
   FROM orders;

2) RANK()
   - 作用：并列时共享同一个排名，下一名跳过
   - 用途：有并列时需要“跳名次”的场景（竞赛排名）
   示例：
   SELECT customer_id, amount,
          RANK() OVER(ORDER BY amount DESC) AS rk
   FROM orders;

3) DENSE_RANK()
   - 作用：并列时共享同一个排名，但不跳名次
   - 用途：有并列但要求“连续名次”的场景
   示例：
   SELECT customer_id, amount,
          DENSE_RANK() OVER(ORDER BY amount DESC) AS drk
   FROM orders;

4) NTILE(N)
   - 作用：把结果分成 N 个桶，返回桶号
   - 用途：分位数（如四分位、十分位）
   示例：
   SELECT customer_id, amount,
          NTILE(4) OVER(ORDER BY amount) AS quartile
   FROM orders;

【2. 累计与聚合类】
--------------------------------------------------------------
5) SUM() OVER
   - 作用：分组内按照顺序进行累计求和
   - 用途：累计销量、累计金额
   示例：
   SELECT order_date, amount,
          SUM(amount) OVER(ORDER BY order_date) AS running_total
   FROM sales;

6) AVG() OVER
   - 作用：分组内平均值
   - 用途：累计平均、滑动平均
   示例：
   SELECT order_date, amount,
          AVG(amount) OVER(ORDER BY order_date ROWS BETWEEN 6 PRECEDING AND CURRENT ROW) AS ma7
   FROM sales;

7) MIN()/MAX() OVER
   - 作用：窗口内最小/最大值
   - 用途：查找区间内最高/最低点
   示例：
   SELECT order_date, amount,
          MAX(amount) OVER(PARTITION BY customer_id ORDER BY order_date ROWS BETWEEN UNBOUNDED PRECEDING AND CURRENT ROW) AS peak
   FROM sales;

【3. 偏移类】
--------------------------------------------------------------
8) LAG(expr, offset, default)
   - 作用：取前 offset 行的值
   - 用途：环比、增长率计算
   示例：
   SELECT order_date, amount,
          amount - LAG(amount,1,0) OVER(ORDER BY order_date) AS diff
   FROM sales;

9) LEAD(expr, offset, default)
   - 作用：取后 offset 行的值
   - 用途：预测下期、找下一个值
   示例：
   SELECT order_date, amount,
          LEAD(amount) OVER(ORDER BY order_date) AS next_amt
   FROM sales;

【4. 分布类】
--------------------------------------------------------------
10) PERCENT_RANK()
    - 作用：百分比排名，范围 [0,1]
    - 用途：计算相对位置（例：成绩百分位）
    示例：
    SELECT student_id, score,
           PERCENT_RANK() OVER(ORDER BY score DESC) AS pct_rank
    FROM exam;

11) CUME_DIST()
    - 作用：累计分布，当前值及以下的比例
    - 用途：查找分布位置（例如 80% 分位线）
    示例：
    SELECT student_id, score,
           CUME_DIST() OVER(ORDER BY score DESC) AS cume
    FROM exam;

12) FIRST_VALUE(expr) / LAST_VALUE(expr)
    - 作用：窗口内的第一个/最后一个值
    - 用途：对比首尾变化
    示例：
    SELECT order_date, amount,
           FIRST_VALUE(amount) OVER(ORDER BY order_date) AS first_amt,
           LAST_VALUE(amount) OVER(ORDER BY order_date ROWS BETWEEN UNBOUNDED PRECEDING AND UNBOUNDED FOLLOWING) AS last_amt
    FROM sales;

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

### 2. 聚合函数

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



### 3. 聚合函数+窗口函数模版

```sql
/* ========== 聚合函数 + 窗口函数 常用模板 ==========

【1. 总体 vs 分组 vs 分组内排名】
--------------------------------------------------
场景：既要总数/平均值，又要每组内的排名
示例：
SELECT customer_id,
       order_id,
       amount,
       COUNT(*)  OVER()                          AS total_orders,    -- 全部订单总数
       SUM(amount) OVER(PARTITION BY customer_id) AS cust_total,     -- 每个客户的总消费
       RANK()      OVER(PARTITION BY customer_id ORDER BY amount DESC) AS order_rank -- 客户内排名
FROM orders;


【2. 累计统计（Running Total / Running Avg）】
--------------------------------------------------
场景：随着时间累计统计，做趋势分析
示例：
SELECT order_date,
       amount,
       SUM(amount) OVER(ORDER BY order_date)            AS running_total, -- 累计和
       AVG(amount) OVER(ORDER BY order_date)            AS running_avg,   -- 累计均值
       COUNT(*)    OVER(ORDER BY order_date)            AS running_count  -- 累计数量
FROM sales;


【3. 分组内比例分析】
--------------------------------------------------
场景：算占比、渗透率、贡献度
示例：
SELECT customer_id,
       amount,
       SUM(amount) OVER(PARTITION BY customer_id)        AS cust_total,
       amount / SUM(amount) OVER(PARTITION BY customer_id) AS pct_within_customer
FROM orders;


【4. 排名前N（Top-N with total context）】
--------------------------------------------------
场景：既要取Top-N，又要带总量/平均对比
示例：
SELECT order_id,
       customer_id,
       amount,
       ROW_NUMBER() OVER(PARTITION BY customer_id ORDER BY amount DESC) AS rn,
       SUM(amount) OVER(PARTITION BY customer_id)                        AS cust_total,
       AVG(amount) OVER(PARTITION BY customer_id)                        AS cust_avg
FROM orders
WHERE rn <= 3;  -- 每个客户前3单


【5. 窗口聚合 + 移动分析】
--------------------------------------------------
场景：移动平均、环比、同比
示例：
SELECT order_date,
       amount,
       SUM(amount) OVER(ORDER BY order_date ROWS BETWEEN 6 PRECEDING AND CURRENT ROW) AS rolling_7days_sum,  -- 7日移动和
       amount - LAG(amount,1,0) OVER(ORDER BY order_date) AS day_diff                -- 昨日差值
FROM sales;


【6. 窗口聚合 + 分位数】
--------------------------------------------------
场景：分组内分布分析（四分位、十分位）
示例：
SELECT customer_id,
       amount,
       NTILE(4) OVER(PARTITION BY customer_id ORDER BY amount) AS quartile,  -- 四分位
       PERCENT_RANK() OVER(PARTITION BY customer_id ORDER BY amount) AS pct_rank
FROM orders;


================================================================
总结：
- 聚合函数 + GROUP BY → 汇总到一行
- 窗口函数 + 聚合函数 → 保留明细 + 增加统计列
- 常见场景：累计统计、占比、Top-N、移动分析、分位数
================================================================
*/

```

### 4. SQL 模版

```sql

/* ============================================================
   MySQL Demo Script: Common SQL Patterns & JOIN Scenarios
   --------------------------------------------------------
   内容：
   1) 基础表结构与示例数据
   2) Top-N（全局/分组）
   3) 去重（Distinct、按时间取最新、规则去重）
   4) 缺失数据（补齐日期、找缺失、找断档）
   5) 累计统计（Running Total、移动平均、同比/环比）
   6) 经典 JOIN 模板（INNER/LEFT/RIGHT/FULL/CROSS/SELF/NON-EQUI/SEMI/ANTI/LATERAL）
   说明：
   - 兼容 MySQL 8+（含窗口函数），并在可能处提供无窗口的兼容写法。
   - 本脚本仅为演示，数据量小，便于直接运行对比结果。
   ============================================================ */

-- 建议使用一个干净的 Schema（可按需修改）
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

-- 2.1 全局 Top-N：订单金额前 3 的客户（聚合→排序→LIMIT）
SELECT c.id, c.name, SUM(o.amount) AS total_amount
FROM customers c
JOIN orders o ON o.customer_id = c.id
GROUP BY c.id, c.name
ORDER BY total_amount DESC
LIMIT 3;

-- 2.2 分组 Top-N（MySQL 8+）：每个客户金额最高的前 2 个商品
WITH agg AS (
  SELECT customer_id, product, SUM(amount) AS product_amount
  FROM orders
  GROUP BY customer_id, product
),
ranked AS (
  SELECT *,
         ROW_NUMBER() OVER (
           PARTITION BY customer_id
           ORDER BY product_amount DESC
         ) AS rn
  FROM agg
)
SELECT *
FROM ranked
WHERE rn <= 2;

-- 2.3 分组 Top-N（兼容写法，无窗口函数）：自连接计数法
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
HAVING COUNT(b.product) < 2;

/* ============================================================
   3) 去重模板
   ============================================================ */

-- 3.1 结果去重：去除重复的 (customer_id, product)
SELECT DISTINCT customer_id, product
FROM orders;

-- 3.2 按时间保留最新一条（MySQL 8+）：每个客户最新订单
SELECT t.*
FROM (
  SELECT o.*,
         ROW_NUMBER() OVER (
           PARTITION BY customer_id ORDER BY order_date DESC, id DESC
         ) AS rn
  FROM orders o
) t
WHERE t.rn = 1;

-- 3.3 按时间保留最新一条（兼容写法）：子查询找最大时间
SELECT o.*
FROM orders o
JOIN (
  SELECT customer_id, MAX(order_date) AS max_dt
  FROM orders
  GROUP BY customer_id
) m ON o.customer_id = m.customer_id AND o.order_date = m.max_dt;

-- 3.4 规则去重示例（窗口 + 时间差，MySQL 8+）
-- 模拟：若同一客户订单在 2 天内重复出现相同 product，只保留第一条
WITH w AS (
  SELECT o.*,
         LAG(order_date) OVER (
           PARTITION BY customer_id, product ORDER BY order_date, id
         ) AS prev_dt
  FROM orders o
)
SELECT *
FROM w
WHERE prev_dt IS NULL OR DATEDIFF(order_date, prev_dt) > 2;

/* ============================================================
   4) 缺失数据模板
   ============================================================ */

-- 4.1 补齐日期：无交易日显示 0（日期维表 LEFT JOIN 交易表）
SELECT d.day,
       COALESCE(SUM(s.amount), 0) AS daily_amount
FROM dates d
LEFT JOIN sales s ON s.day = d.day
GROUP BY d.day
ORDER BY d.day;

-- 4.2 找缺失：从未下单的用户（NOT EXISTS 推荐）
SELECT u.*
FROM users u
WHERE NOT EXISTS (
  SELECT 1 FROM orders o WHERE o.customer_id = u.user_id
);

-- 4.3 找断档：用户签到的中断点（MySQL 8+）
WITH w AS (
  SELECT user_id, sign_date,
         LAG(sign_date) OVER (PARTITION BY user_id ORDER BY sign_date) AS prev_dt
  FROM sign_log
)
SELECT *
FROM w
WHERE prev_dt IS NOT NULL AND DATEDIFF(sign_date, prev_dt) > 1;

/* ============================================================
   5) 累计统计模板
   ============================================================ */

-- 5.1 Running Total：按天累计销售额（MySQL 8+）
SELECT day,
       SUM(amount) AS daily_amt,
       SUM(SUM(amount)) OVER (ORDER BY day) AS running_amt
FROM sales
GROUP BY day
ORDER BY day;

-- 5.2 分组累计：每个用户的累计销售额（MySQL 8+）
SELECT user_id, day,
       SUM(amount) AS daily_amt,
       SUM(SUM(amount)) OVER (PARTITION BY user_id ORDER BY day) AS running_amt_user
FROM sales
GROUP BY user_id, day
ORDER BY user_id, day;

-- 5.3 移动平均（7 日窗口，MySQL 8+；数据较少仅演示写法）
SELECT day,
       AVG(SUM(amount)) OVER (
         ORDER BY day
         ROWS BETWEEN 6 PRECEDING AND CURRENT ROW
       ) AS ma7
FROM sales
GROUP BY day
ORDER BY day;

-- 5.4 环比（MoM，MySQL 8+）
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
            ELSE ROUND( (amt - LAG(amt) OVER (ORDER BY mon))
                        / LAG(amt) OVER (ORDER BY mon), 4)
       END AS mom_rate
FROM m
ORDER BY mon;

/* ============================================================
   6) 经典 JOIN 模板（含示例）
   ============================================================ */

-- 6.1 INNER JOIN：只要匹配的行
SELECT c.name, o.product, o.amount
FROM customers c
INNER JOIN orders o ON c.id = o.customer_id;

-- 6.2 LEFT JOIN：保留左表全部
SELECT c.name, o.product, o.amount
FROM customers c
LEFT JOIN orders o ON c.id = o.customer_id
ORDER BY c.id, o.id;

-- 6.3 RIGHT JOIN：保留右表全部
SELECT c.name, o.product, o.amount
FROM customers c
RIGHT JOIN orders o ON c.id = o.customer_id
ORDER BY o.id;

-- 6.4 FULL OUTER JOIN（MySQL 模拟）：保留两边全部
SELECT c.id AS c_id, c.name, o.id AS o_id, o.product, o.amount
FROM customers c
LEFT JOIN orders o ON c.id = o.customer_id
UNION
SELECT c.id AS c_id, c.name, o.id AS o_id, o.product, o.amount
FROM customers c
RIGHT JOIN orders o ON c.id = o.customer_id;

-- 6.5 CROSS JOIN：笛卡尔积（所有组合）
SELECT u.user_id, p.product_id
FROM users u
CROSS JOIN products p
ORDER BY u.user_id, p.product_id;

-- 6.6 SELF JOIN：员工与上级
SELECT e.emp_id, e.name AS employee, m.name AS manager
FROM employees e
LEFT JOIN employees m ON e.manager_id = m.emp_id
ORDER BY e.emp_id;

-- 6.7 NON-EQUI JOIN：工资落入等级区间
SELECT e.emp_id, e.name, e.salary, g.grade
FROM employees e
JOIN salary_grade g
  ON e.salary BETWEEN g.min_salary AND g.max_salary
ORDER BY e.emp_id;

-- 6.8 SEMI JOIN（用 EXISTS）：有下单的客户
SELECT c.*
FROM customers c
WHERE EXISTS (SELECT 1 FROM orders o WHERE o.customer_id = c.id);

-- 6.9 ANTI JOIN（用 NOT EXISTS）：从未下单的客户
SELECT c.*
FROM customers c
WHERE NOT EXISTS (SELECT 1 FROM orders o WHERE o.customer_id = c.id);

-- 6.10 LATERAL（MySQL 8+，每个客户最近一单；需要 8.0.14+ 支持 LATERAL 派生表语义）
-- 某些 MySQL 版本可用相关子查询或窗口函数替代
SELECT c.id, c.name, t.id AS last_order_id, t.order_date, t.amount
FROM customers c
LEFT JOIN LATERAL (
  SELECT o.*
  FROM orders o
  WHERE o.customer_id = c.id
  ORDER BY o.order_date DESC, o.id DESC
  LIMIT 1
) AS t ON TRUE;

-- 6.11 反连接的另一种写法（LEFT JOIN ... IS NULL）
SELECT c.*
FROM customers c
LEFT JOIN orders o ON o.customer_id = c.id
WHERE o.id IS NULL;

-- 6.12 STRAIGHT_JOIN（调优用：强制按书写顺序连接）
SELECT STRAIGHT_JOIN c.name, o.product, o.amount
FROM customers c
JOIN orders o ON o.customer_id = c.id;

/* ===================== 结束 ===================== */

```

