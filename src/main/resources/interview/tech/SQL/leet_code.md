

------

# 📘 LeetCode MySQL 模版全集

------

## 1️⃣ 基础查询（SELECT + WHERE + DISTINCT）

```sql
-- 查询特定列
SELECT col1, col2
FROM table
WHERE condition;

-- 去重查询
SELECT DISTINCT col
FROM table;
```

------

## 2️⃣ 聚合统计（GROUP BY + HAVING）

```sql
/* ============================================================
   聚合统计（GROUP BY + HAVING）常用模版全集
   ============================================================
   聚合函数：COUNT / SUM / AVG / MAX / MIN
   使用场景：分组统计、筛选分组、Top-N 分组
============================================================ */


/* ============================================================
 1. 基本分组统计
   - 每个用户的订单数
============================================================ */
SELECT user_id, COUNT(*) AS order_count
FROM Orders
GROUP BY user_id;


/* ============================================================
 2. 分组 + 多个聚合
   - 每个用户的订单数和总金额
============================================================ */
SELECT user_id,
       COUNT(*) AS order_count,
       SUM(amount) AS total_amount,
       AVG(amount) AS avg_amount
FROM Orders
GROUP BY user_id;


/* ============================================================
 3. HAVING 过滤分组
   - 找订单数 >= 3 的用户
============================================================ */
SELECT user_id, COUNT(*) AS order_count
FROM Orders
GROUP BY user_id
HAVING COUNT(*) >= 3;


/* ============================================================
 4. GROUP BY 多列
   - 每个城市、每个用户的订单数
============================================================ */
SELECT city, user_id, COUNT(*) AS order_count
FROM Orders
GROUP BY city, user_id;


/* ============================================================
 5. GROUP BY + ORDER BY
   - 按城市统计订单数，并按数量降序排序
============================================================ */
SELECT city, COUNT(*) AS order_count
FROM Orders
GROUP BY city
ORDER BY order_count DESC;


/* ============================================================
 6. 计算百分比（窗口函数版）
   - 每个城市订单数占比
============================================================ */
--option1:
SELECT city,
       COUNT(*) AS order_count,
       ROUND(COUNT(*) * 1.0 / (SELECT COUNT(*) FROM Orders), 2) AS pct
FROM Orders
GROUP BY city;

--option2:
SELECT city,
       COUNT(*) AS order_count,
       COUNT(*) * 1.0 / SUM(COUNT(*)) OVER() AS pct
FROM Orders
GROUP BY city;


/* ============================================================
 7. 条件聚合 (CASE WHEN)
   - 每个用户完成订单数、取消订单数
============================================================ */
SELECT user_id,
       SUM(CASE WHEN status='completed' THEN 1 ELSE 0 END) AS completed_orders,
       SUM(CASE WHEN status='canceled' THEN 1 ELSE 0 END) AS canceled_orders
FROM Orders
GROUP BY user_id;


/* ============================================================
 8. HAVING + 多个条件
   - 找平均金额 > 50 且订单数 > 5 的用户
============================================================ */
SELECT user_id,
       COUNT(*) AS order_count,
       AVG(amount) AS avg_amount
FROM Orders
GROUP BY user_id
HAVING COUNT(*) > 5 AND AVG(amount) > 50;


/* ============================================================
 9. 找出每个分组的最大/最小值
   - 每个部门的最高工资
============================================================ */
SELECT department_id, MAX(salary) AS max_salary
FROM Employee
GROUP BY department_id;


/* ============================================================
 10. 分组后筛选 Top-N（子查询或窗口函数）
   - 每个部门工资最高的员工
============================================================ */
-- 窗口函数
SELECT name, department, salary
FROM (
  SELECT name, department, salary,
         RANK() OVER(PARTITION BY department ORDER BY salary DESC) rk
  FROM Employee
) t
WHERE rk = 1;

-- 子查询
SELECT e.*
FROM Employee e
WHERE salary = (
    SELECT MAX(salary)
    FROM Employee
    WHERE department_id = e.department_id
);

-- 子查询
SELECT e1.name,  e1.salary
FROM Employee e1
WHERE NOT EXISTS (
    SELECT 1
    FROM Employee e2
    WHERE e2.department_id = e1.department_id
      AND e2.salary > e1.salary
);

```

------

## 3️⃣ 排序 & Top-N（ORDER BY + LIMIT + OFFSET）

```sql
/* ============================================================
   排序 & Top-N（ORDER BY + LIMIT + OFFSET）常用模版全集
   ============================================================
   关键点：
   1) ORDER BY 指定排序字段（ASC升序 / DESC降序）
   2) LIMIT 限制行数
   3) OFFSET 跳过前几行
============================================================ */


/* ============================================================
 1. 基础排序
   - 按 salary 降序排列
============================================================ */
SELECT *
FROM Employee
ORDER BY salary DESC;


/* ============================================================
 2. Top-N 查询
   - 找工资最高的前 3 名
============================================================ */
SELECT *
FROM Employee
ORDER BY salary DESC
LIMIT 3;


/* ============================================================
 3. Nth 高（OFFSET）
   - 找工资第二高
============================================================ */
SELECT DISTINCT salary
FROM Employee
ORDER BY salary DESC
LIMIT 1 OFFSET 1;

-- Nth 高（通用版）
SELECT DISTINCT salary
FROM Employee
ORDER BY salary DESC
LIMIT 1 OFFSET N-1;


/* ============================================================
 4. 排序 + LIMIT + OFFSET (分页查询)
   - 每页 10 条，取第 2 页
============================================================ */
SELECT *
FROM Orders
ORDER BY order_date DESC
LIMIT 10 OFFSET 10;


/* ============================================================
 5. 去重 + 排序 + LIMIT
   - 找第二高的唯一工资
============================================================ */
SELECT DISTINCT salary
FROM Employee
ORDER BY salary DESC
LIMIT 1 OFFSET 1;


/* ============================================================
 6. 分组 Top-1（子查询法）
   - 每个部门工资最高的人
============================================================ */
SELECT e.*
FROM Employee e
WHERE salary = (
    SELECT MAX(salary)
    FROM Employee
    WHERE department_id = e.department_id
);


/* ============================================================
 7. 分组 Top-N（窗口函数法，MySQL 8+）
   - 每个部门工资前 3 名
============================================================ */
SELECT name, department_id, salary
FROM (
  SELECT name, department_id, salary,
         RANK() OVER(PARTITION BY department_id ORDER BY salary DESC) rk
  FROM Employee
) t
WHERE rk <= 3;


/* ============================================================
 8. DENSE_RANK 模版（避免跳级）
   - LeetCode 176, 177 典型
============================================================ */
SELECT salary,
       DENSE_RANK() OVER(ORDER BY salary DESC) AS rnk
FROM Employee;


/* ============================================================
 9. 百分比排名（PERCENT_RANK）
   - 统计每个员工工资在全体中的百分位
============================================================ */
SELECT name, salary,
       PERCENT_RANK() OVER(ORDER BY salary) AS pct_rank
FROM Employee;

```

------

## 4️⃣ 窗口函数（RANK, ROW_NUMBER, LAG, LEAD）

```sql
/* ============================================================
   窗口函数（RANK, ROW_NUMBER, LAG, LEAD）常用模版全集
   ============================================================
   示例表：Employee
   +----+---------+-------------+--------+
   | id | name    | department  | salary |
   +----+---------+-------------+--------+
   |  1 | Alice   | HR          | 6000   |
   |  2 | Bob     | HR          | 7000   |
   |  3 | Carol   | HR          | 7000   |
   |  4 | David   | IT          | 8000   |
   |  5 | Eva     | IT          | 9000   |
   |  6 | Frank   | IT          | 7500   |
   +----+---------+-------------+--------+
============================================================ */


/* ============================================================
 1. ROW_NUMBER() → 分组内排序，唯一序号
   应用：每组取前 N 条
============================================================ */
SELECT name, department, salary,
       ROW_NUMBER() OVER(PARTITION BY department ORDER BY salary DESC) AS rn
FROM Employee;

-- 预期输出：
-- +-------+------------+--------+----+
-- | name  | department | salary | rn |
-- +-------+------------+--------+----+
-- | Bob   | HR         | 7000   |  1 |
-- | Carol | HR         | 7000   |  2 |
-- | Alice | HR         | 6000   |  3 |
-- | Eva   | IT         | 9000   |  1 |
-- | David | IT         | 8000   |  2 |
-- | Frank | IT         | 7500   |  3 |
-- +-------+------------+--------+----+


/* ============================================================
 2. RANK() → 分组内排名（相同值并列，后面会跳号）
   应用：工资排名（允许并列）
============================================================ */
SELECT name, department, salary,
       RANK() OVER(PARTITION BY department ORDER BY salary DESC) AS rk
FROM Employee;

-- 预期输出：
-- +-------+------------+--------+----+
-- | name  | department | salary | rk |
-- +-------+------------+--------+----+
-- | Bob   | HR         | 7000   |  1 |
-- | Carol | HR         | 7000   |  1 |
-- | Alice | HR         | 6000   |  3 |
-- | Eva   | IT         | 9000   |  1 |
-- | David | IT         | 8000   |  2 |
-- | Frank | IT         | 7500   |  3 |
-- +-------+------------+--------+----+


/* ============================================================
 3. DENSE_RANK() → 分组内排名（相同值并列，不跳号）
   应用：找第 N 高工资（不会空缺）
============================================================ */
SELECT name, department, salary,
       DENSE_RANK() OVER(PARTITION BY department ORDER BY salary DESC) AS drk
FROM Employee;

-- 预期输出：
-- +-------+------------+--------+-----+
-- | name  | department | salary | drk |
-- +-------+------------+--------+-----+
-- | Bob   | HR         | 7000   |  1  |
-- | Carol | HR         | 7000   |  1  |
-- | Alice | HR         | 6000   |  2  |
-- | Eva   | IT         | 9000   |  1  |
-- | David | IT         | 8000   |  2  |
-- | Frank | IT         | 7500   |  3  |
-- +-------+------------+--------+-----+


/* ============================================================
 4. LAG() → 取前一行的值
   应用：对比当前工资和上一个工资
============================================================ */
SELECT name, department, salary,
       LAG(salary,1) OVER(PARTITION BY department ORDER BY salary DESC) AS prev_salary
FROM Employee;

-- 预期输出：
-- +-------+------------+--------+-------------+
-- | name  | department | salary | prev_salary |
-- +-------+------------+--------+-------------+
-- | Bob   | HR         | 7000   | NULL        |
-- | Carol | HR         | 7000   | 7000        |
-- | Alice | HR         | 6000   | 7000        |
-- | Eva   | IT         | 9000   | NULL        |
-- | David | IT         | 8000   | 9000        |
-- | Frank | IT         | 7500   | 8000        |
-- +-------+------------+--------+-------------+


/* ============================================================
 5. LEAD() → 取后一行的值
   应用：对比当前工资和下一位工资
============================================================ */
SELECT name, department, salary,
       LEAD(salary,1) OVER(PARTITION BY department ORDER BY salary DESC) AS next_salary
FROM Employee;

-- 预期输出：
-- +-------+------------+--------+-------------+
-- | name  | department | salary | next_salary |
-- +-------+------------+--------+-------------+
-- | Bob   | HR         | 7000   | 7000        |
-- | Carol | HR         | 7000   | 6000        |
-- | Alice | HR         | 6000   | NULL        |
-- | Eva   | IT         | 9000   | 8000        |
-- | David | IT         | 8000   | 7500        |
-- | Frank | IT         | 7500   | NULL        |
-- +-------+------------+--------+-------------+


/* ============================================================
 6. 累积和（SUM() OVER）
   应用：工资累计值+
   如果你想要「真正的逐行累计和」，必须 写 ROWS，不能只写 ORDER BY：
============================================================ */
SELECT name, department, salary,
       SUM(salary) OVER(PARTITION BY department ORDER BY salary DESC) AS running_total
FROM Employee;

-- 预期输出：
-- +-------+------------+--------+---------------+
-- | name  | department | salary | running_total |
-- +-------+------------+--------+---------------+
-- | Bob   | HR         | 7000   | 7000          |
-- | Carol | HR         | 7000   | 14000         |
-- | Alice | HR         | 6000   | 20000         |
-- | Eva   | IT         | 9000   | 9000          |
-- | David | IT         | 8000   | 17000         |
-- | Frank | IT         | 7500   | 24500         |
-- +-------+------------+--------+---------------+


/* ============================================================
 7. 移动平均（AVG() OVER with ROWS）
   应用：取当前行及前1行、后1行的平均工资
============================================================ */
SELECT name, department, salary,
       AVG(salary) OVER(PARTITION BY department ORDER BY salary
                        ROWS BETWEEN 1 PRECEDING AND 1 FOLLOWING) AS moving_avg
FROM Employee;

-- 预期输出 (HR 部门举例)：
-- +-------+------------+--------+-------------+
-- | name  | department | salary | moving_avg  |
-- +-------+------------+--------+-------------+
-- | Alice | HR         | 6000   | 6500.0      |
-- | Bob   | HR         | 7000   | 6666.7      |
-- | Carol | HR         | 7000   | 6500.0      |
-- (IT 部门类似计算结果)

```

------

## 5️⃣ JOIN（多表连接）

```sql
-- 内连接
SELECT a.col, b.col
FROM A a
JOIN B b ON a.id = b.id;

-- 左连接
SELECT a.col, b.col
FROM A a
LEFT JOIN B b ON a.id = b.id;

-- 自连接
SELECT e.name
FROM Employee e
JOIN Employee m ON e.managerId = m.id
WHERE e.salary > m.salary;
```

------

## 6️⃣ 子查询（Subquery, EXISTS, NOT EXISTS）

```sql
/* ============================================================
   子查询（Subquery, EXISTS, NOT EXISTS）常用模版全集
   ============================================================
   示例表：
   Users
   +----+-------+
   | id | name  |
   +----+-------+
   |  1 | Alice |
   |  2 | Bob   |
   |  3 | Carol |
   |  4 | David |
   +----+-------+

   Orders
   +----+---------+----------+
   | id | user_id | amount   |
   +----+---------+----------+
   |  1 |    1    |   100    |
   |  2 |    1    |   200    |
   |  3 |    2    |   150    |
   |  4 |    3    |   50     |
   +----+---------+----------+
============================================================ */


/* ============================================================
 1. 子查询 in WHERE
   应用：找出有下过订单的用户
============================================================ */
SELECT name
FROM Users
WHERE id IN (SELECT DISTINCT user_id FROM Orders);

-- 预期输出：
-- +-------+
-- | name  |
-- +-------+
-- | Alice |
-- | Bob   |
-- | Carol |
-- +-------+


/* ============================================================
 2. 子查询 with NOT IN
   应用：找出没有下过订单的用户
============================================================ */
SELECT name
FROM Users
WHERE id NOT IN (SELECT user_id FROM Orders);

-- 预期输出：
-- +-------+
-- | name  |
-- +-------+
-- | David |
-- +-------+


/* ============================================================
 3. 子查询 in SELECT 子句
   应用：查询用户及其订单数
============================================================ */
SELECT name,
       (SELECT COUNT(*) FROM Orders o WHERE o.user_id=u.id) AS order_count
FROM Users u;

-- 预期输出：
-- +-------+--------------+
-- | name  | order_count  |
-- +-------+--------------+
-- | Alice |      2       |
-- | Bob   |      1       |
-- | Carol |      1       |
-- | David |      0       |
-- +-------+--------------+


/* ============================================================
 4. 子查询 in FROM 子句 (派生表)
   应用：统计每个用户的订单金额，并筛选金额>200的用户
============================================================ */
SELECT name, total_amount
FROM (
    SELECT u.id, u.name, SUM(o.amount) AS total_amount
    FROM Users u
    LEFT JOIN Orders o ON u.id=o.user_id
    GROUP BY u.id, u.name
) t
WHERE total_amount > 200;

-- 预期输出：
-- +-------+--------------+
-- | name  | total_amount |
-- +-------+--------------+
-- | Alice |    300       |
-- +-------+--------------+


/* ============================================================
 5. EXISTS 子查询
   应用：找出有订单的用户
============================================================ */
SELECT name
FROM Users u
WHERE EXISTS (
    SELECT 1 FROM Orders o WHERE o.user_id=u.id
);

-- 预期输出：
-- +-------+
-- | name  |
-- +-------+
-- | Alice |
-- | Bob   |
-- | Carol |
-- +-------+


/* ============================================================
 6. NOT EXISTS 子查询
   应用：找出没有订单的用户
============================================================ */
SELECT name
FROM Users u
WHERE NOT EXISTS (
    SELECT 1 FROM Orders o WHERE o.user_id=u.id
);

-- 预期输出：
-- +-------+
-- | name  |
-- +-------+
-- | David |
-- +-------+


/* ============================================================
 7. 相关子查询（Correlated Subquery）
   应用：找出比自己平均订单金额高的用户
============================================================ */
SELECT name, amount
FROM Orders o1
WHERE amount > (
    SELECT AVG(amount)
    FROM Orders o2
    WHERE o2.user_id=o1.user_id
);

-- 预期输出：
-- +----+-------+
-- |name |amount|
-- +----+-------+
-- |Alice| 200  |
-- +----+-------+

```

------

## 7️⃣ 去重 / 重复处理

```sql
/* ============================================================
   去重 / 重复处理 常用模版全集
   ============================================================
   示例表：Person
   +----+---------+--------------------+
   | id | name    | email              |
   +----+---------+--------------------+
   |  1 | Alice   | alice@leetcode.com |
   |  2 | Bob     | bob@leetcode.com   |
   |  3 | Carol   | alice@leetcode.com |
   |  4 | David   | david@leetcode.com |
   |  5 | Eva     | bob@leetcode.com   |
   +----+---------+--------------------+
============================================================ */


/* ============================================================
 1. DISTINCT 去重查询
   应用：去重后的邮箱列表
============================================================ */
SELECT DISTINCT email
FROM Person;

-- 预期输出：
-- +--------------------+
-- | email              |
-- +--------------------+
-- | alice@leetcode.com |
-- | bob@leetcode.com   |
-- | david@leetcode.com |
-- +--------------------+


/* ============================================================
 2. GROUP BY 找重复值
   应用：找出重复出现的邮箱
============================================================ */
SELECT email
FROM Person
GROUP BY email
HAVING COUNT(*) > 1;

-- 预期输出：
-- +--------------------+
-- | email              |
-- +--------------------+
-- | alice@leetcode.com |
-- | bob@leetcode.com   |
-- +--------------------+


/* ============================================================
 3. GROUP BY + COUNT（统计每个值出现次数）
============================================================ */
SELECT email, COUNT(*) AS cnt
FROM Person
GROUP BY email;

-- 预期输出：
-- +--------------------+-----+
-- | email              | cnt |
-- +--------------------+-----+
-- | alice@leetcode.com |  2  |
-- | bob@leetcode.com   |  2  |
-- | david@leetcode.com |  1  |
-- +--------------------+-----+


/* ============================================================
 4. 删除重复数据（保留最小 id）
   应用：LeetCode 196 Delete Duplicate Emails
============================================================ */
DELETE p1
FROM Person p1
JOIN Person p2
ON p1.email = p2.email AND p1.id > p2.id;

-- 预期执行后 Person 表：
-- +----+---------+--------------------+
-- | id | name    | email              |
-- +----+---------+--------------------+
-- |  1 | Alice   | alice@leetcode.com |
-- |  2 | Bob     | bob@leetcode.com   |
-- |  4 | David   | david@leetcode.com |
-- +----+---------+--------------------+


/* ============================================================
 5. 保留最新记录（按 id 或时间戳）
============================================================ */
SELECT p.*
FROM Person p
WHERE p.id = (
    SELECT MIN(id) 
    FROM Person
    WHERE email = p.email
);

-- 预期输出（每个邮箱保留 id 最小的记录）：
-- +----+---------+--------------------+
-- | id | name    | email              |
-- +----+---------+--------------------+
-- |  1 | Alice   | alice@leetcode.com |
-- |  2 | Bob     | bob@leetcode.com   |
-- |  4 | David   | david@leetcode.com |
-- +----+---------+--------------------+


/* ============================================================
 6. 用窗口函数去重 (ROW_NUMBER)
   应用：保留每个邮箱第一条记录
============================================================ */
WITH cte AS (
  SELECT id, name, email,
         ROW_NUMBER() OVER(PARTITION BY email ORDER BY id) rn
  FROM Person
)
SELECT id, name, email
FROM cte
WHERE rn = 1;

-- 预期输出（同上）：
-- +----+---------+--------------------+
-- | id | name    | email              |
-- +----+---------+--------------------+
-- |  1 | Alice   | alice@leetcode.com |
-- |  2 | Bob     | bob@leetcode.com   |
-- |  4 | David   | david@leetcode.com |
-- +----+---------+--------------------+


/* ============================================================
 7. 仅选取重复数据（不去重）
   应用：列出所有重复邮箱的记录
============================================================ */
SELECT *
FROM Person
WHERE email IN (
    SELECT email
    FROM Person
    GROUP BY email
    HAVING COUNT(*) > 1
);

-- 预期输出：
-- +----+---------+--------------------+
-- | id | name    | email              |
-- +----+---------+--------------------+
-- |  1 | Alice   | alice@leetcode.com |
-- |  3 | Carol   | alice@leetcode.com |
-- |  2 | Bob     | bob@leetcode.com   |
-- |  5 | Eva     | bob@leetcode.com   |
-- +----+---------+--------------------+

```

------

## 8️⃣ 连续区间 / 序列问题

```sql
/* ============================================================
   示例数据表：Logins
   ------------------------------------------------------------
   +---------+------------+
   | user_id | login_date |
   +---------+------------+
   |   1     | 2023-01-01 |
   |   1     | 2023-01-02 |
   |   1     | 2023-01-03 |
   |   1     | 2023-01-05 |
   |   1     | 2023-01-06 |
   |   2     | 2023-01-01 |
   |   2     | 2023-01-04 |
   |   2     | 2023-01-05 |
   |   2     | 2023-01-06 |
   +---------+------------+
============================================================ */


/* ============================================================
 方法一：Anchor Key 法
 ------------------------------------------------------------
 思路：利用 (日期 - row_number) 分组，差值相同表示连续
 适用场景：连续日期区间 / 连续 ID 区间 / 连续登录天数
 优点：简洁高效，可读性强
 缺点：依赖 MySQL 8+ 窗口函数
============================================================ */
WITH cte AS (
  SELECT user_id,
         login_date,
         ROW_NUMBER() OVER(PARTITION BY user_id ORDER BY login_date) rn
  FROM Logins
)
SELECT user_id,
       MIN(login_date) AS start_date,
       MAX(login_date) AS end_date
FROM cte
GROUP BY user_id, DATE_SUB(login_date, INTERVAL rn DAY);

-- 预期输出：
-- +---------+------------+------------+
-- | user_id | start_date | end_date   |
-- +---------+------------+------------+
-- |   1     | 2023-01-01 | 2023-01-03 |
-- |   1     | 2023-01-05 | 2023-01-06 |
-- |   2     | 2023-01-01 | 2023-01-01 |
-- |   2     | 2023-01-04 | 2023-01-06 |
-- +---------+------------+------------+


/* ============================================================
 方法二：LAG() 差值法
 ------------------------------------------------------------
 思路：用 LAG() 比较当前行与前一行日期差，如果差值>1则断开
 适用场景：找断点（非连续位置）
 优点：直观清晰
 缺点：只能找到断点，还需二次处理区间；依赖 MySQL 8+
============================================================ */
WITH cte AS (
  SELECT user_id,
         login_date,
         LAG(login_date) OVER(PARTITION BY user_id ORDER BY login_date) pre_date
  FROM Logins
)
SELECT user_id, login_date, pre_date
FROM cte
WHERE DATEDIFF(login_date, pre_date) > 1;

-- 预期输出：
-- +---------+------------+------------+
-- | user_id | login_date | pre_date   |
-- +---------+------------+------------+
-- |   1     | 2023-01-05 | 2023-01-03 |
-- |   2     | 2023-01-04 | 2023-01-01 |
-- +---------+------------+------------+


/* ============================================================
 方法三：连续 N 次相同值（LeetCode 180）
 ------------------------------------------------------------
 思路：用多次 LAG() 检查当前行与前 N-1 行是否相同
 适用场景：连续 N 次相同值（如连续3天相同）
 优点：简单直接，适合固定 N
 缺点：N 越大写法越繁琐；依赖 MySQL 8+
============================================================ */
-- 示例数据：Logs(num)
-- +----+-----+
-- | id | num |
-- +----+-----+
-- |  1 | 1   |
-- |  2 | 1   |
-- |  3 | 1   |
-- |  4 | 2   |
-- |  5 | 2   |
-- |  6 | 3   |
-- |  7 | 3   |
-- |  8 | 3   |
-- +----+-----+

SELECT DISTINCT num
FROM (
  SELECT num,
         LAG(num,1) OVER(ORDER BY id) pre1,
         LAG(num,2) OVER(ORDER BY id) pre2
  FROM Logs
) t
WHERE num=pre1 AND num=pre2;

-- 预期输出：
-- +-----+
-- | num |
-- +-----+
-- |  1  |
-- |  3  |
-- +-----+


/* ============================================================
 方法四：Gaps and Islands
 ------------------------------------------------------------
 思路：利用 (id - row_number) 给序列分组，分组内即连续区间
 适用场景：连续 ID 序列分段 / 订单号区间
 优点：经典通用，适合连续数列
 缺点：实现较复杂；依赖 MySQL 8+
============================================================ */
WITH cte AS (
  SELECT id, num,
         ROW_NUMBER() OVER(ORDER BY id) rn
  FROM Logs
)
SELECT MIN(id) start_id, MAX(id) end_id, num
FROM cte
GROUP BY num, id-rn;

-- 预期输出 (基于上面 Logs 表)：
-- +----------+--------+-----+
-- | start_id | end_id | num |
-- +----------+--------+-----+
-- |    1     |   3    |  1  |
-- |    4     |   5    |  2  |
-- |    6     |   8    |  3  |
-- +----------+--------+-----+


/* ============================================================
 方法五：变量法 (MySQL 5.x)
 ------------------------------------------------------------
 思路：用 @grp 用户变量模拟窗口函数分组
 适用场景：MySQL 5.x 无窗口函数
 优点：兼容老版本
 缺点：写法复杂，可读性差
============================================================ */
SET @grp := 0;
SET @prev_date := NULL;

SELECT user_id,
       MIN(login_date) start_date,
       MAX(login_date) end_date
FROM (
  SELECT user_id, login_date,
         @grp := IF(@prev_date IS NULL 
                     OR DATEDIFF(login_date,@prev_date)>1, 
                    @grp+1, @grp) AS grp,
         @prev_date := login_date
  FROM Logins
  ORDER BY user_id, login_date
) t
GROUP BY user_id, grp;

-- 预期输出（同方法一）：
-- +---------+------------+------------+
-- | user_id | start_date | end_date   |
-- +---------+------------+------------+
-- |   1     | 2023-01-01 | 2023-01-03 |
-- |   1     | 2023-01-05 | 2023-01-06 |
-- |   2     | 2023-01-01 | 2023-01-01 |
-- |   2     | 2023-01-04 | 2023-01-06 |
-- +---------+------------+------------+


/* ============================================================
 方法六：递归 CTE 法
 ------------------------------------------------------------
 思路：递归逐天扩展，直到断开为止
 适用场景：需要链式递归判断的复杂情况
 优点：灵活，可处理特殊需求
 缺点：性能差，递归层数深开销大；MySQL 8+ 支持
============================================================ */
WITH RECURSIVE seq AS (
  SELECT user_id, login_date, login_date AS start_date
  FROM Logins
  UNION ALL
  SELECT l.user_id, l.login_date, s.start_date
  FROM Logins l
  JOIN seq s
    ON l.user_id=s.user_id
   AND l.login_date = DATE_ADD(s.login_date, INTERVAL 1 DAY)
)
SELECT user_id, start_date, MAX(login_date) AS end_date
FROM seq
GROUP BY user_id, start_date;

-- 预期输出：
-- +---------+------------+------------+
-- | user_id | start_date | end_date   |
-- +---------+------------+------------+
-- |   1     | 2023-01-01 | 2023-01-03 |
-- |   1     | 2023-01-05 | 2023-01-06 |
-- |   2     | 2023-01-01 | 2023-01-01 |
-- |   2     | 2023-01-04 | 2023-01-06 |
-- +---------+------------+------------+


/* ============================================================
 方法七：自连接法
 ------------------------------------------------------------
 思路：JOIN 前一天/前两天，检查是否存在连续 N 天
 适用场景：检测是否存在连续 N 天（固定 N）
 优点：简单直观，不依赖窗口函数
 缺点：N 越大 JOIN 越多，可扩展性差
============================================================ */
SELECT DISTINCT l1.user_id
FROM Logins l1
JOIN Logins l2 ON l2.user_id=l1.user_id 
              AND l2.login_date=DATE_ADD(l1.login_date,INTERVAL 1 DAY)
JOIN Logins l3 ON l3.user_id=l1.user_id 
              AND l3.login_date=DATE_ADD(l1.login_date,INTERVAL 2 DAY);

-- 预期输出：
-- +---------+
-- | user_id |
-- +---------+
-- |    1    |
-- |    2    |
-- +---------+

```

| 方法               | 依赖版本  | 优点             | 缺点                   | 适用场景     |
| ------------------ | --------- | ---------------- | ---------------------- | ------------ |
| **Anchor Key**     | MySQL 8+  | 简洁高效，可扩展 | 需窗口函数             | 连续区间划分 |
| **LAG/LEAD**       | MySQL 8+  | 判断断点直观     | 只能找断点，需二次聚合 | 找序列断点   |
| **连续 N 次相同**  | MySQL 8+  | 模板化，易写     | 仅适用固定 N           | 连续 N 值    |
| **Gaps & Islands** | MySQL 8+  | 理论经典，通用   | 写法稍复杂             | 连续数列分段 |
| **变量法**         | MySQL 5.x | 无窗口时唯一选择 | 写法复杂，可读性差     | 老版本       |
| **递归 CTE**       | MySQL 8+  | 灵活，可逐行递归 | 性能一般               | 复杂连续问题 |
| **自连接**         | MySQL 5+  | 直观，不需窗口   | N 越大 JOIN 越多       | 连续 N 天    |

------

## 9️⃣ 条件聚合（CASE WHEN）

```sql
/* ============================================================
   条件聚合（CASE WHEN）常用模版全集
   ============================================================
   示例表：Orders
   +----+---------+----------+------------+
   | id | user_id | status   | amount     |
   +----+---------+----------+------------+
   |  1 |    1    | completed|   100      |
   |  2 |    1    | canceled |   200      |
   |  3 |    1    | completed|   150      |
   |  4 |    2    | completed|   300      |
   |  5 |    2    | completed|   250      |
   |  6 |    3    | canceled |   400      |
   +----+---------+----------+------------+
============================================================ */


/* ============================================================
 1. 按条件计数
   应用：每个用户完成/取消订单数
============================================================ */
SELECT user_id,
       SUM(CASE WHEN status='completed' THEN 1 ELSE 0 END) AS completed_orders,
       SUM(CASE WHEN status='canceled' THEN 1 ELSE 0 END) AS canceled_orders
FROM Orders
GROUP BY user_id;

-- 预期输出：
-- +---------+----------------+----------------+
-- | user_id | completed_orders | canceled_orders |
-- +---------+----------------+----------------+
-- |    1    |        2       |        1       |
-- |    2    |        2       |        0       |
-- |    3    |        0       |        1       |
-- +---------+----------------+----------------+


/* ============================================================
 2. 按条件求和
   应用：每个用户完成/取消订单金额
============================================================ */
SELECT user_id,
       SUM(CASE WHEN status='completed' THEN amount ELSE 0 END) AS completed_amount,
       SUM(CASE WHEN status='canceled' THEN amount ELSE 0 END) AS canceled_amount
FROM Orders
GROUP BY user_id;

-- 预期输出：
-- +---------+----------------+----------------+
-- | user_id | completed_amount | canceled_amount |
-- +---------+----------------+----------------+
-- |    1    |      250        |      200       |
-- |    2    |      550        |        0       |
-- |    3    |        0        |      400       |
-- +---------+----------------+----------------+


/* ============================================================
 3. 按条件求平均
   应用：每个用户完成订单的平均金额
============================================================ */
SELECT user_id,
       AVG(CASE WHEN status='completed' THEN amount END) AS avg_completed_amount
FROM Orders
GROUP BY user_id;

-- 预期输出：
-- +---------+----------------------+
-- | user_id | avg_completed_amount |
-- +---------+----------------------+
-- |    1    |        125.0         |
-- |    2    |        275.0         |
-- |    3    |        NULL          |
-- +---------+----------------------+


/* ============================================================
 4. 多条件分类统计
   应用：每个用户订单金额，按区间分类
============================================================ */
SELECT user_id,
       SUM(CASE WHEN amount < 200 THEN 1 ELSE 0 END) AS small_orders,
       SUM(CASE WHEN amount BETWEEN 200 AND 300 THEN 1 ELSE 0 END) AS medium_orders,
       SUM(CASE WHEN amount > 300 THEN 1 ELSE 0 END) AS large_orders
FROM Orders
GROUP BY user_id;

-- 预期输出：
-- +---------+--------------+---------------+--------------+
-- | user_id | small_orders | medium_orders | large_orders |
-- +---------+--------------+---------------+--------------+
-- |    1    |      2       |       1       |      0       |
-- |    2    |      0       |       2       |      0       |
-- |    3    |      0       |       0       |      1       |
-- +---------+--------------+---------------+--------------+


/* ============================================================
 5. 与 HAVING 搭配
   应用：筛选出取消订单数 > 0 的用户
============================================================ */
SELECT user_id,
       SUM(CASE WHEN status='canceled' THEN 1 ELSE 0 END) AS canceled_orders
FROM Orders
GROUP BY user_id
HAVING canceled_orders > 0;

-- 预期输出：
-- +---------+----------------+
-- | user_id | canceled_orders|
-- +---------+----------------+
-- |    1    |       1        |
-- |    3    |       1        |
-- +---------+----------------+


/* ============================================================
 6. 与 DISTINCT 搭配
   应用：每个用户完成订单涉及的不同金额种类数
============================================================ */
SELECT user_id,
       COUNT(DISTINCT CASE WHEN status='completed' THEN amount END) AS diff_completed_amounts
FROM Orders
GROUP BY user_id;

-- 预期输出：
-- +---------+----------------------+
-- | user_id | diff_completed_amounts |
-- +---------+----------------------+
-- |    1    |          2           |
-- |    2    |          2           |
-- |    3    |          0           |
-- +---------+----------------------+

```

------

## 🔟 更新 / 替换（UPDATE + CASE）

```sql
-- 条件更新
UPDATE table
SET col = CASE
            WHEN col='A' THEN 'B'
            ELSE 'A'
          END;

-- Swap 值
UPDATE Salary
SET sex=CASE WHEN sex='m' THEN 'f' ELSE 'm' END;
```

------

## 1️⃣1️⃣ 删除（DELETE）

```sql
-- 删除不需要的数据
DELETE FROM table
WHERE condition;

-- 删除重复
DELETE t1
FROM table t1
JOIN table t2
ON t1.col=t2.col
WHERE t1.id>t2.id;
```

------

## 1️⃣2️⃣ 日期函数（DATEDIFF, YEAR, MONTH）

```sql
/* ============================================================
   日期函数（MySQL）常用模版全集
   ============================================================
   示例表：Orders
   +----+---------+------------+
   | id | user_id | order_date |
   +----+---------+------------+
   |  1 |    1    | 2023-01-01 |
   |  2 |    1    | 2023-01-05 |
   |  3 |    2    | 2023-01-07 |
   |  4 |    2    | 2023-02-01 |
   |  5 |    3    | 2023-02-15 |
   |  6 |    3    | 2023-03-10 |
   +----+---------+------------+
============================================================ */


/* ============================================================
 1. 当前日期和时间
============================================================ */
SELECT CURDATE() AS today, NOW() AS current_time;

-- 预期输出：
-- +------------+---------------------+
-- | today      | current_time        |
-- +------------+---------------------+
-- | 2025-08-27 | 2025-08-27 22:00:00 |
-- +------------+---------------------+


/* ============================================================
 2. 提取年份、月份、日
============================================================ */
SELECT id, user_id, order_date,
       YEAR(order_date) AS year_val,
       MONTH(order_date) AS month_val,
       DAY(order_date) AS day_val
FROM Orders;

-- 预期输出：
-- +----+---------+------------+----------+-----------+--------+
-- | id | user_id | order_date | year_val | month_val | day_val|
-- +----+---------+------------+----------+-----------+--------+
-- |  1 |    1    | 2023-01-01 | 2023     |    1      |   1    |
-- |  2 |    1    | 2023-01-05 | 2023     |    1      |   5    |
-- |  3 |    2    | 2023-01-07 | 2023     |    1      |   7    |
-- |  4 |    2    | 2023-02-01 | 2023     |    2      |   1    |
-- |  5 |    3    | 2023-02-15 | 2023     |    2      |   15   |
-- |  6 |    3    | 2023-03-10 | 2023     |    3      |   10   |
-- +----+---------+------------+----------+-----------+--------+


/* ============================================================
 3. 提取星期几
============================================================ */
SELECT id, order_date,
       DAYNAME(order_date) AS day_name,
       WEEKDAY(order_date) AS weekday_index  -- 周一=0
FROM Orders;

-- 预期输出 (可能因系统不同略有差异)：
-- +----+------------+-----------+---------------+
-- | id | order_date | day_name  | weekday_index |
-- +----+------------+-----------+---------------+
-- |  1 | 2023-01-01 | Sunday    | 6             |
-- |  2 | 2023-01-05 | Thursday  | 3             |
-- |  3 | 2023-01-07 | Saturday  | 5             |
-- |  4 | 2023-02-01 | Wednesday | 2             |
-- |  5 | 2023-02-15 | Wednesday | 2             |
-- |  6 | 2023-03-10 | Friday    | 4             |
-- +----+------------+-----------+---------------+


/* ============================================================
 4. DATEDIFF（日期差）
============================================================ */
SELECT o1.id AS order1, o2.id AS order2,
       DATEDIFF(o2.order_date, o1.order_date) AS days_diff
FROM Orders o1
JOIN Orders o2 ON o1.user_id=o2.user_id AND o2.id>o1.id;

-- 预期输出：
-- +--------+--------+-----------+
-- | order1 | order2 | days_diff |
-- +--------+--------+-----------+
-- |   1    |   2    |     4     |
-- |   2    |   4    |    27     |
-- |   4    |   5    |    14     |
-- |   5    |   6    |    23     |
-- +--------+--------+-----------+


/* ============================================================
 5. DATE_ADD / DATE_SUB（加减日期）
============================================================ */
SELECT id, order_date,
       DATE_ADD(order_date, INTERVAL 7 DAY) AS plus_7days,
       DATE_SUB(order_date, INTERVAL 7 DAY) AS minus_7days
FROM Orders;

-- 预期输出：
-- +----+------------+------------+------------+
-- | id | order_date | plus_7days | minus_7days|
-- +----+------------+------------+------------+
-- |  1 | 2023-01-01 | 2023-01-08 | 2022-12-25 |
-- |  2 | 2023-01-05 | 2023-01-12 | 2022-12-29 |
-- |  3 | 2023-01-07 | 2023-01-14 | 2022-12-31 |
-- |  4 | 2023-02-01 | 2023-02-08 | 2023-01-25 |
-- |  5 | 2023-02-15 | 2023-02-22 | 2023-02-08 |
-- |  6 | 2023-03-10 | 2023-03-17 | 2023-03-03 |
-- +----+------------+------------+------------+


/* ============================================================
 6. DATE_FORMAT（日期格式化）
============================================================ */
SELECT id, order_date,
       DATE_FORMAT(order_date, '%Y-%m') AS year_month,
       DATE_FORMAT(order_date, '%b %d, %Y') AS pretty_format
FROM Orders;

-- 预期输出：
-- +----+------------+-----------+----------------+
-- | id | order_date | year_month| pretty_format  |
-- +----+------------+-----------+----------------+
-- |  1 | 2023-01-01 | 2023-01   | Jan 01, 2023   |
-- |  2 | 2023-01-05 | 2023-01   | Jan 05, 2023   |
-- |  3 | 2023-01-07 | 2023-01   | Jan 07, 2023   |
-- |  4 | 2023-02-01 | 2023-02   | Feb 01, 2023   |
-- |  5 | 2023-02-15 | 2023-02   | Feb 15, 2023   |
-- |  6 | 2023-03-10 | 2023-03   | Mar 10, 2023   |
-- +----+------------+-----------+----------------+


/* ============================================================
 7. TIMESTAMPDIFF（两个日期的差，单位可选）
============================================================ */
SELECT o1.id AS order1, o2.id AS order2,
       TIMESTAMPDIFF(DAY, o1.order_date, o2.order_date) AS diff_days,
       TIMESTAMPDIFF(MONTH, o1.order_date, o2.order_date) AS diff_months
FROM Orders o1
JOIN Orders o2 ON o1.user_id=o2.user_id AND o2.id>o1.id;

-- 预期输出：
-- +--------+--------+-----------+-------------+
-- | order1 | order2 | diff_days | diff_months |
-- +--------+--------+-----------+-------------+
-- |   1    |   2    |     4     |     0       |
-- |   2    |   4    |    27     |     0       |
-- |   4    |   5    |    14     |     0       |
-- |   5    |   6    |    23     |     0       |
-- +--------+--------+-----------+-------------+

```

------

## 1️⃣3️⃣ 字符串函数（LIKE, SUBSTRING, CONCAT）

```sql
/* ============================================================
   字符串函数（MySQL）常用模版全集
   ============================================================
   示例表：Users
   +----+----------+----------------------+
   | id | name     | email                |
   +----+----------+----------------------+
   |  1 | Alice    | alice@leetcode.com   |
   |  2 | Bob      | bob_smith@company.com|
   |  3 | Carol    | carol@leetcode.com   |
   |  4 | David    | david@test.org       |
   |  5 | Eva      | eva@company.com      |
   +----+----------+----------------------+
============================================================ */


/* ============================================================
 1. LENGTH / CHAR_LENGTH (字符串长度)
============================================================ */
SELECT name, email,
       LENGTH(email) AS byte_len,
       CHAR_LENGTH(email) AS char_len
FROM Users;

-- 预期输出：
-- +-------+----------------------+----------+----------+
-- | name  | email                | byte_len | char_len |
-- +-------+----------------------+----------+----------+
-- | Alice | alice@leetcode.com   |   18     |   18     |
-- | Bob   | bob_smith@company.com|   22     |   22     |
-- | Carol | carol@leetcode.com   |   18     |   18     |
-- | David | david@test.org       |   15     |   15     |
-- | Eva   | eva@company.com      |   17     |   17     |
-- +-------+----------------------+----------+----------+


/* ============================================================
 2. UPPER / LOWER (大小写转换)
============================================================ */
SELECT name,
       UPPER(name) AS upper_name,
       LOWER(name) AS lower_name
FROM Users;

-- 预期输出：
-- +-------+------------+------------+
-- | name  | upper_name | lower_name |
-- +-------+------------+------------+
-- | Alice | ALICE      | alice      |
-- | Bob   | BOB        | bob        |
-- | Carol | CAROL      | carol      |
-- | David | DAVID      | david      |
-- | Eva   | EVA        | eva        |
-- +-------+------------+------------+


/* ============================================================
 3. SUBSTRING (截取子串)
============================================================ */
SELECT email,
       SUBSTRING(email, 1, 5) AS first5,
       SUBSTRING(email, -3) AS last3
FROM Users;

-- 预期输出：
-- +----------------------+---------+-------+
-- | email                | first5  | last3 |
-- +----------------------+---------+-------+
-- | alice@leetcode.com   | alice   | com   |
-- | bob_smith@company.com| bob_s   | com   |
-- | carol@leetcode.com   | carol   | com   |
-- | david@test.org       | david   | org   |
-- | eva@company.com      | eva@c   | com   |
-- +----------------------+---------+-------+


/* ============================================================
 4. LEFT / RIGHT (截取左边/右边)
============================================================ */
SELECT email,
       LEFT(email, 5) AS left5,
       RIGHT(email, 3) AS right3
FROM Users;

-- 预期输出：
-- +----------------------+-------+--------+
-- | email                | left5 | right3 |
-- +----------------------+-------+--------+
-- | alice@leetcode.com   | alice | com    |
-- | bob_smith@company.com| bob_s | com    |
-- | carol@leetcode.com   | carol | com    |
-- | david@test.org       | david | org    |
-- | eva@company.com      | eva@c | com    |
-- +----------------------+-------+--------+


/* ============================================================
 5. CONCAT / CONCAT_WS (拼接字符串)
============================================================ */
SELECT name, email,
       CONCAT(name, '-', email) AS combined,
       CONCAT_WS(' | ', name, email) AS combined_ws
FROM Users;

-- 预期输出：
-- +-------+----------------------+----------------------------+----------------------------+
-- | name  | email                | combined                   | combined_ws                |
-- +-------+----------------------+----------------------------+----------------------------+
-- | Alice | alice@leetcode.com   | Alice-alice@leetcode.com   | Alice | alice@leetcode.com |
-- | Bob   | bob_smith@company.com| Bob-bob_smith@company.com  | Bob | bob_smith@company.com|
-- | Carol | carol@leetcode.com   | Carol-carol@leetcode.com   | Carol | carol@leetcode.com |
-- | David | david@test.org       | David-david@test.org       | David | david@test.org     |
-- | Eva   | eva@company.com      | Eva-eva@company.com        | Eva | eva@company.com      |
-- +-------+----------------------+----------------------------+----------------------------+


/* ============================================================
 6. REPLACE (替换字符串)
============================================================ */
SELECT email,
       REPLACE(email, 'leetcode', 'gmail') AS replaced
FROM Users;

-- 预期输出：
-- +----------------------+--------------------+
-- | email                | replaced           |
-- +----------------------+--------------------+
-- | alice@leetcode.com   | alice@gmail.com    |
-- | bob_smith@company.com| bob_smith@company.com |
-- | carol@leetcode.com   | carol@gmail.com    |
-- | david@test.org       | david@test.org     |
-- | eva@company.com      | eva@company.com    |
-- +----------------------+--------------------+


/* ============================================================
 7. INSTR / LOCATE (查找子串位置)
============================================================ */
SELECT email,
       INSTR(email, '@') AS at_pos,
       LOCATE('com', email) AS com_pos
FROM Users;

-- 预期输出：
-- +----------------------+--------+---------+
-- | email                | at_pos | com_pos |
-- +----------------------+--------+---------+
-- | alice@leetcode.com   |   6    |   15    |
-- | bob_smith@company.com|   9    |   20    |
-- | carol@leetcode.com   |   6    |   15    |
-- | david@test.org       |   6    |   11    |
-- | eva@company.com      |   4    |   13    |
-- +----------------------+--------+---------+


/* ============================================================
 8. TRIM / LTRIM / RTRIM (去除空格)
============================================================ */
SELECT '   hello   ' AS raw_str,
       TRIM('   hello   ') AS trimmed,
       LTRIM('   hello   ') AS left_trimmed,
       RTRIM('   hello   ') AS right_trimmed;

-- 预期输出：
-- +-------------+---------+--------------+---------------+
-- | raw_str     | trimmed | left_trimmed | right_trimmed |
-- +-------------+---------+--------------+---------------+
-- |    hello    | hello   | hello        |    hello      |
-- +-------------+---------+--------------+---------------+


/* ============================================================
 9. REVERSE (字符串反转)
============================================================ */
SELECT name, REVERSE(name) AS reversed
FROM Users;

-- 预期输出：
-- +-------+----------+
-- | name  | reversed |
-- +-------+----------+
-- | Alice | ecilA    |
-- | Bob   | boB      |
-- | Carol | loraC    |
-- | David | divaD    |
-- | Eva   | avE      |
-- +-------+----------+


/* ============================================================
 10. LPAD / RPAD (字符串填充)
============================================================ */
SELECT name,
       LPAD(name, 10, '*') AS left_padded,
       RPAD(name, 10, '_') AS right_padded
FROM Users;

-- 预期输出：
-- +-------+------------+-------------+
-- | name  | left_padded| right_padded|
-- +-------+------------+-------------+
-- | Alice | *****Alice | Alice_____  |
-- | Bob   | *******Bob | Bob_______  |
-- | Carol | *****Carol | Carol_____  |
-- | David | *****David | David_____  |
-- | Eva   | *******Eva | Eva_______  |
-- +-------+------------+-------------+

```

------

## 1️⃣4️⃣ 综合统计（百分比、占比）

```sql
/* ============================================================
   综合统计（百分比、占比）常用模版全集
   ============================================================
   示例表：Orders
   +----+---------+---------+--------+
   | id | user_id | product | amount |
   +----+---------+---------+--------+
   |  1 |    1    |   A     |  100   |
   |  2 |    1    |   B     |  200   |
   |  3 |    2    |   A     |  150   |
   |  4 |    2    |   C     |  300   |
   |  5 |    3    |   A     |  100   |
   |  6 |    3    |   B     |  100   |
   |  7 |    3    |   C     |  200   |
   +----+---------+---------+--------+
============================================================ */


/* ============================================================
 1. 各分组占比（相对总数百分比）
   应用：每个产品订单数占比
============================================================ */
SELECT product,
       COUNT(*) AS order_count,
       ROUND(COUNT(*) * 100.0 / (SELECT COUNT(*) FROM Orders), 2) AS pct
FROM Orders
GROUP BY product;

-- 预期输出：
-- +---------+-------------+-------+
-- | product | order_count | pct   |
-- +---------+-------------+-------+
-- | A       |     3       | 42.86 |
-- | B       |     2       | 28.57 |
-- | C       |     2       | 28.57 |
-- +---------+-------------+-------+


/* ============================================================
 2. 各分组金额占比
   应用：每个用户消费金额占比
============================================================ */
SELECT user_id,
       SUM(amount) AS total_amount,
       ROUND(SUM(amount) * 100.0 / (SELECT SUM(amount) FROM Orders), 2) AS pct
FROM Orders
GROUP BY user_id;

-- 预期输出：
-- +---------+--------------+-------+
-- | user_id | total_amount | pct   |
-- +---------+--------------+-------+
-- |    1    |    300       | 25.00 |
-- |    2    |    450       | 37.50 |
-- |    3    |    400       | 37.50 |
-- +---------+--------------+-------+


/* ============================================================
 3. 各分组占比（窗口函数版，MySQL 8+）
   应用：每个产品订单数占比
============================================================ */
SELECT product,
       COUNT(*) AS order_count,
       ROUND(COUNT(*) * 100.0 / SUM(COUNT(*)) OVER(), 2) AS pct
FROM Orders
GROUP BY product;

-- 预期输出（同方法1）：
-- +---------+-------------+-------+
-- | product | order_count | pct   |
-- +---------+-------------+-------+
-- | A       |     3       | 42.86 |
-- | B       |     2       | 28.57 |
-- | C       |     2       | 28.57 |
-- +---------+-------------+-------+


/* ============================================================
 4. 分组内占比
   应用：每个用户在各产品的消费占比
============================================================ */
SELECT user_id, product,
       SUM(amount) AS total_amount,
       ROUND(SUM(amount) * 100.0 / SUM(SUM(amount)) OVER(PARTITION BY user_id), 2) AS pct_in_user
FROM Orders
GROUP BY user_id, product;

-- 预期输出：
-- +---------+---------+--------------+-------------+
-- | user_id | product | total_amount | pct_in_user |
-- +---------+---------+--------------+-------------+
-- |    1    |   A     |    100       |   33.33     |
-- |    1    |   B     |    200       |   66.67     |
-- |    2    |   A     |    150       |   33.33     |
-- |    2    |   C     |    300       |   66.67     |
-- |    3    |   A     |    100       |   25.00     |
-- |    3    |   B     |    100       |   25.00     |
-- |    3    |   C     |    200       |   50.00     |
-- +---------+---------+--------------+-------------+


/* ============================================================
 5. 累计占比 (Running Percentage)
   应用：按消费金额排序，累计百分比
============================================================ */
SELECT user_id, SUM(amount) AS total_amount,
       ROUND(SUM(amount) * 100.0 / SUM(SUM(amount)) OVER(), 2) AS pct,
       ROUND(SUM(SUM(amount)) OVER(ORDER BY SUM(amount) DESC) * 100.0 / SUM(SUM(amount)) OVER(), 2) AS cum_pct
FROM Orders
GROUP BY user_id
ORDER BY total_amount DESC;

-- 预期输出：
-- +---------+--------------+-------+---------+
-- | user_id | total_amount | pct   | cum_pct |
-- +---------+--------------+-------+---------+
-- |    2    |    450       | 37.50 | 37.50   |
-- |    3    |    400       | 33.33 | 70.83   |
-- |    1    |    300       | 25.00 | 95.83   |
-- +---------+--------------+-------+---------+

```

------

## 1️⃣5️⃣ 复杂场景（多表 + 聚合 + 窗口）

```sql
/* ============================================================
   复杂场景（多表 + 聚合 + 窗口函数）常用模版全集
   ============================================================
   示例表：
   Employee
   +----+---------+-------------+--------+
   | id | name    | department  | salary |
   +----+---------+-------------+--------+
   |  1 | Alice   | HR          | 6000   |
   |  2 | Bob     | HR          | 7000   |
   |  3 | Carol   | HR          | 7000   |
   |  4 | David   | IT          | 8000   |
   |  5 | Eva     | IT          | 9000   |
   |  6 | Frank   | IT          | 7500   |
   +----+---------+-------------+--------+

   Department
   +-------------+--------------+
   | dept_id     | dept_name    |
   +-------------+--------------+
   | HR          | HumanRes     |
   | IT          | Tech         |
   +-------------+--------------+
============================================================ */


/* ============================================================
 1. 多表 JOIN + 聚合
   应用：每个部门的平均工资
============================================================ */
SELECT d.dept_name,
       AVG(e.salary) AS avg_salary
FROM Employee e
JOIN Department d ON e.department = d.dept_id
GROUP BY d.dept_name;

-- 预期输出：
-- +-----------+------------+
-- | dept_name | avg_salary |
-- +-----------+------------+
-- | HumanRes  |   6666.7   |
-- | Tech      |   8166.7   |
-- +-----------+------------+


/* ============================================================
 2. 多表 JOIN + 聚合 + HAVING
   应用：找平均工资 > 7000 的部门
============================================================ */
SELECT d.dept_name,
       AVG(e.salary) AS avg_salary
FROM Employee e
JOIN Department d ON e.department = d.dept_id
GROUP BY d.dept_name
HAVING AVG(e.salary) > 7000;

-- 预期输出：
-- +-----------+------------+
-- | dept_name | avg_salary |
-- +-----------+------------+
-- | Tech      |   8166.7   |
-- +-----------+------------+


/* ============================================================
 3. 多表 JOIN + 窗口函数
   应用：找出每个部门工资最高的员工 (RANK)
============================================================ */
SELECT name, department, salary
FROM (
  SELECT e.name, e.department, e.salary,
         RANK() OVER(PARTITION BY e.department ORDER BY e.salary DESC) rk
  FROM Employee e
) t
WHERE rk = 1;

-- 预期输出：
-- +-------+------------+--------+
-- | name  | department | salary |
-- +-------+------------+--------+
-- | Bob   | HR         | 7000   |
-- | Carol | HR         | 7000   |
-- | Eva   | IT         | 9000   |
-- +-------+------------+--------+


/* ============================================================
 4. 多表 JOIN + 条件聚合 (CASE WHEN)
   应用：每个部门完成条件的统计
============================================================ */
SELECT d.dept_name,
       SUM(CASE WHEN e.salary >= 8000 THEN 1 ELSE 0 END) AS high_salary_count,
       SUM(CASE WHEN e.salary < 8000 THEN 1 ELSE 0 END) AS low_salary_count
FROM Employee e
JOIN Department d ON e.department = d.dept_id
GROUP BY d.dept_name;

-- 预期输出：
-- +-----------+-------------------+------------------+
-- | dept_name | high_salary_count | low_salary_count |
-- +-----------+-------------------+------------------+
-- | HumanRes  |        0          |        3         |
-- | Tech      |        2          |        1         |
-- +-----------+-------------------+------------------+


/* ============================================================
 5. 多表 JOIN + 窗口函数 + 聚合
   应用：按部门工资降序，统计累计工资
============================================================ */
SELECT d.dept_name, e.name, e.salary,
       SUM(e.salary) OVER(PARTITION BY d.dept_id ORDER BY e.salary DESC) AS running_total
FROM Employee e
JOIN Department d ON e.department = d.dept_id;

-- 预期输出：
-- +-----------+-------+--------+---------------+
-- | dept_name | name  | salary | running_total |
-- +-----------+-------+--------+---------------+
-- | HumanRes  | Bob   | 7000   | 7000          |
-- | HumanRes  | Carol | 7000   | 14000         |
-- | HumanRes  | Alice | 6000   | 20000         |
-- | Tech      | Eva   |

```

------

# 📑 高频口诀（终极总结）

- **Top-N / Nth 高**：`LIMIT OFFSET` / `DENSE_RANK()`
- **分组聚合筛选**：`GROUP BY + HAVING`
- **窗口函数**：`RANK / ROW_NUMBER / LAG / LEAD`
- **连续区间**：`ROW_NUMBER + anchor key`
- **去重删除**：`GROUP BY HAVING COUNT>1` 或 `DELETE self-join`
- **条件聚合**：`SUM(CASE WHEN … END)`
- **存在性**：`EXISTS / NOT EXISTS`
- **字符串**：`LIKE / SUBSTRING / CONCAT / REPLACE`
- **日期**：`DATEDIFF / DATE_ADD / YEAR / MONTH`
- **百分比**：`COUNT(*) / SUM(COUNT(*)) OVER()`

