1. 2025(8-9)

   > ```sql
   > /* 
   > 【第一步：详细解题思路】
   > 
   > 目标：统计满足以下全部条件的订单数量（返回一行一列 total_orders）
   > 1) 送货单（delivery），而不是自取单 → is_pickup_flg = 0
   > 2) 订单已完成 → status = 'COMPLETED'（为避免大小写问题，统一用 UPPER 比较）
   > 3) 订单评分为“最高评分” → rating 等于在满足前两条过滤条件后的最大 rating
   > 
   > 关键点与边界：
   > - rating 可能为 NULL：MAX() 会自动忽略 NULL，但等号比较需要排除 NULL（加 rating IS NOT NULL）。
   > - “最高评分”通常是全局最大值（不是分组内、也不是每商家），题干也给了全局总数输出，因此用子查询求全局最大 rating。
   > - 为了更严谨，子查询与外层过滤条件保持一致（只在已完成的送货单里找最大评分）。
   > - is_pickup_flg 字段通常 0/1，0 表示非自取（即配送）；如平台定义相反，可按实际字段语义调整。
   > 
   > 输出：一行一列 total_orders。
   > 
   > 
   > /* ==============================
   >    MySQL 版本（8.0+）
   >    ============================== */
   > SELECT COUNT(*) AS total_orders
   > FROM orders o
   > WHERE o.is_pickup_flg = 0                                  -- 送货单（不是自取）
   >   AND UPPER(o.status) = 'COMPLETED'                        -- 已完成
   >   AND o.rating IS NOT NULL                                 -- 排除空评分
   >   AND o.rating = (
   >         SELECT MAX(i.rating)
   >         FROM orders i
   >         WHERE i.is_pickup_flg = 0
   >           AND UPPER(i.status) = 'COMPLETED'
   >           AND i.rating IS NOT NULL
   >       );
   > -- 说明：
   > -- 1) 子查询先在“完成且为送货单”的记录中求最大 rating。
   > -- 2) 外层再过滤 rating 等于该最大值的订单，并计数。
   > -- 3) 若题目确认 status 固定大小写，可去掉 UPPER()；这里为保险起见保留。
   > 
   > 
   > /* ==============================
   >    Snowflake 版本
   >    ============================== */
   > SELECT COUNT(*) AS total_orders
   > FROM orders o
   > WHERE o.is_pickup_flg = 0
   >   AND UPPER(o.status) = 'COMPLETED'
   >   AND o.rating IS NOT NULL
   >   AND o.rating = (
   >         SELECT MAX(i.rating)
   >         FROM orders i
   >         WHERE i.is_pickup_flg = 0
   >           AND UPPER(i.status) = 'COMPLETED'
   >           AND i.rating IS NOT NULL
   >       );
   > -- 说明与 MySQL 相同；Snowflake 对大小写不敏感的字符串比较通常更宽松，但为规避脏数据仍显式使用 UPPER()。
   > */
   > 
   > /* 
   > 【第一步：详细解题思路】
   > 
   > 目标：找出符合以下任意一个条件的 dasher 的 ID（输出列为 dasher_id）：
   > 1. 从未完成过任何配送（orders 表中没有出现）
   > 2. 做过配送但所有订单都没有 rating（即 rating 全为 NULL）
   > 
   > 思路分解：
   > ① 所有 dashers 的全集 → 来自 dashers 表
   > ② 每个 dasher 的配送记录 → 来自 orders 表中的 dasher_id
   > ③ rating 字段中 NULL 表示“未评分” ⇒ 可用聚合方式判断（COUNT(rating) = 0）
   > 
   > 实现方式：
   > 使用两个 LEFT JOIN 或子查询过滤方式：
   > - 找出没有配送记录的 dasher ⇒ NOT EXISTS (SELECT 1 FROM orders o WHERE o.dasher_id = d.id)
   > - 或有配送但评分全为 NULL ⇒ EXISTS(SELECT 1 FROM orders o WHERE o.dasher_id = d.id) AND
   >                                        NOT EXISTS(SELECT 1 FROM orders o WHERE o.dasher_id = d.id AND o.rating IS NOT NULL)
   > 
   > 也可用聚合方式统计每个 dasher 的配送总数与非空评分数：
   > - GROUP BY dasher_id，HAVING COUNT(*) > 0 AND COUNT(rating) = 0
   > 
   > 建议使用 UNION 拼接两类 dasher（从未配送，配送但没评分）
   > 
   > */
   > 
   > /* ==============================
   >    MySQL / Snowflake 通用版本
   >    ============================== */
   > -- 第一类：从未配送过
   > SELECT d.id AS dasher_id
   > FROM dashers d
   > LEFT JOIN orders o ON d.id = o.dasher_id
   > WHERE o.id IS NULL
   > 
   > UNION
   > 
   > -- 第二类：配送过但没有评分（所有 rating 为 NULL）
   > SELECT o.dasher_id
   > FROM orders o
   > GROUP BY o.dasher_id
   > HAVING COUNT(*) > 0                -- 确保配送过
   >    AND COUNT(o.rating) = 0;       -- 所有 rating 都是 NULL（COUNT 忽略 NULL）
   > 
   > -- 说明：
   > -- - LEFT JOIN + o.id IS NULL 得到“完全没有配送记录”的 dasher
   > -- - 第二部分用 GROUP BY + COUNT 实现“配送但全为无评分”判断
   > -- - UNION 自动去重，返回所有符合任一条件的 dasher_id
   > 
   > /* 
   > 【第一步：详细解题思路】
   > 
   > 目标：找出某商品在某 merchant 店内，连续 3 天都满足以下条件：
   >   1. 单日销量排名第一（即 merchant_id, order_date 下销量最多）；
   >   2. 该商品当天销量 ≥ 2；
   >   3. 连续 3 天都是上述第一且符合销量条件。
   > 
   > 输出字段：
   > merchant_id、merchant_name、item_name
   > 
   > 涉及表：
   > - merchants(id, name)
   > - items(id, name)
   > - orders(id, merchant_id, order_date)
   > - order_items(order_id, item_id, quantity)
   > 
   > 步骤拆解：
   > ① 按 merchant_id + item_id + order_date 聚合出每天每个商品的销量（SUM(quantity))。
   > ② 用窗口函数计算 merchant 内每天销量排名（RANK() OVER (PARTITION BY merchant_id, order_date ORDER BY SUM(quantity) DESC)）。
   > ③ 过滤出「当天销量排名 = 1 且销量 ≥ 2」的记录。
   > ④ 对满足条件的 (merchant_id, item_id) + order_date，进行连续性判断：
   >     → 用 DENSE_RANK() 生成 order_date 的行号，然后用 date_diff - row_num trick 找出连续区间
   >     → 最后 GROUP BY 判断是否连续 ≥ 3 天（同一 merchant_id + item_id + 差值 group）
   > 
   > 最终 JOIN 回 merchants 和 items 表拿到名字。
   > 
   > */
   > 
   > /* ==============================
   >    MySQL 8+ 或 Snowflake 通用解法
   >    ============================== */
   > WITH daily_sales AS (
   >   -- 第一步：计算每天每个商品销量
   >   SELECT 
   >     o.merchant_id,
   >     oi.item_id,
   >     o.order_date,
   >     SUM(oi.quantity) AS total_sold
   >   FROM orders o
   >   JOIN order_items oi ON o.id = oi.order_id
   >   GROUP BY o.merchant_id, oi.item_id, o.order_date
   > ),
   > ranked_sales AS (
   >   -- 第二步：对每天每个商家商品进行销量排名
   >   SELECT *,
   >     RANK() OVER (PARTITION BY merchant_id, order_date ORDER BY total_sold DESC) AS sales_rank
   >   FROM daily_sales
   > ),
   > top_selling_items AS (
   >   -- 第三步：只保留每天排名第1且销量>=2的商品
   >   SELECT *
   >   FROM ranked_sales
   >   WHERE sales_rank = 1 AND total_sold >= 2
   > ),
   > with_sequence AS (
   >   -- 第四步：为每个 merchant + item 的有效记录做连续性分组（用差值法）
   >   SELECT *,
   >     DENSE_RANK() OVER (PARTITION BY merchant_id, item_id ORDER BY order_date) 
   >       - DATEDIFF(order_date, DATE '2000-01-01') AS gap_group
   >   FROM top_selling_items
   > ),
   > grouped_consecutive_days AS (
   >   -- 第五步：按 merchant + item + gap_group 分组，统计连续天数
   >   SELECT merchant_id, item_id, COUNT(*) AS consecutive_days
   >   FROM with_sequence
   >   GROUP BY merchant_id, item_id, gap_group
   >   HAVING COUNT(*) >= 3
   > )
   > -- 第六步：JOIN 回名字
   > SELECT 
   >   g.merchant_id,
   >   m.name AS merchant_name,
   >   i.name AS item_name
   > FROM grouped_consecutive_days g
   > JOIN merchants m ON g.merchant_id = m.id
   > JOIN items i ON g.item_id = i.id;
   > 
   > -- 说明：
   > -- - gap_group 差值法用于识别连续自然日；
   > -- - COUNT(*) >= 3 表示连续3天都排名第一且销量≥2；
   > -- - 最终输出符合条件的 merchant + item 名称。
   > 
   > ```
   >
   > 

2. 2025(4-6月) DataEng 本科 全职@doordash - 内推 - 技术电面  | 😐 Neutral 😣 Hard | Other | 在职跳槽

   > python 20 分钟
   > 1道题目 sliding window 的题目
   > 陆思伞的变种，是和不是average，还要给出所有的start
   >
   > ```java
   > /*
   > 第一步：题目描述
   > 给定一个整数数组，每个元素表示当天的earning（收入）。
   > 我们需要计算所有连续7天的区间的总和，并找出最大值。
   > 最终返回：
   > 1. 最大的7天总和（highest_earning）
   > 2. 所有达到最大值的起始下标（从哪一天开始算的）。
   > 
   > 例如：
   > 输入：[2, 1, 5, 6, 2, 3, 4, 8, 1]
   > 连续7天的区间和有：
   > [2..8] -> sum
   > 然后取最大值。
   > 如果多个区间和一样大，就都返回。
   > */
   > 
   > /*
   > 第二步：解题思路（举例）
   > 1. 使用滑动窗口（sliding window），窗口大小为7。
   > 2. 首先计算前7天的和，作为初始最大值。
   >    举例：[2,1,5,6,2,3,4] -> sum = 23
   > 3. 然后每天往后移动一位，减去窗口最左边的值，加上新进入窗口的值。
   >    比如第2天到第8天：sum = 23 - 2 + 8 = 29。
   > 4. 在移动过程中，随时比较是否比当前最大值大：
   >    - 如果更大，就更新最大值，并清空并加入新的起始下标。
   >    - 如果等于最大值，就把新的起始下标也加入结果。
   > 5. 遍历到最后，返回 [最大值, 所有起始下标]。
   > 
   > 举例：
   > [2,1,5,6,2,3,4,8,1]
   > 前7天 sum = 23（index=0）
   > 第二个窗口 sum = 29（index=1） → 更新最大值=29，起点=[1]
   > 第三个窗口 sum = 29（index=2） → 和最大值相等，起点加上 [2]
   > 返回结果：[29, [1,2]]
   > */
   > 
   > /*
   > 第三步：时间和空间复杂度分析
   > - 时间复杂度：O(n)，其中 n 是数组长度。因为滑动窗口只需要一次遍历。
   > - 空间复杂度：O(k)，k是存放结果起点的数量。整体额外空间只用来存储结果下标。
   > */
   > 
   > /*
   > 第四步：Java代码（含中文注释）
   > */
   > 
   > import java.util.*;
   > 
   > public class MaxEarning7Days {
   >     public static List<Object> highest7DayEarning(int[] earnings) {
   >         List<Object> result = new ArrayList<>();
   >         if (earnings == null || earnings.length < 7) {
   >             return result; // 不足7天，返回空
   >         }
   > 
   >         int n = earnings.length;
   >         int windowSum = 0;
   > 
   >         // 计算前7天的和
   >         for (int i = 0; i < 7; i++) {
   >             windowSum += earnings[i];
   >         }
   > 
   >         int maxSum = windowSum;
   >         List<Integer> startDays = new ArrayList<>();
   >         startDays.add(0);
   > 
   >         // 滑动窗口，从第8天开始
   >         for (int i = 7; i < n; i++) {
   >             // 移动窗口：减去最左边，加上新一天
   >             windowSum = windowSum - earnings[i - 7] + earnings[i];
   > 
   >             // 如果更大，更新最大值，清空之前的起点
   >             if (windowSum > maxSum) {
   >                 maxSum = windowSum;
   >                 startDays.clear();
   >                 startDays.add(i - 6); // 新窗口起点
   >             } 
   >             // 如果相等，加入新的起点
   >             else if (windowSum == maxSum) {
   >                 startDays.add(i - 6);
   >             }
   >         }
   > 
   >         result.add(maxSum);
   >         result.add(startDays);
   >         return result;
   >     }
   > 
   >     public static void main(String[] args) {
   >         int[] earnings = {2, 1, 5, 6, 2, 3, 4, 8, 1};
   >         System.out.println(highest7DayEarning(earnings));
   >         // 输出：[29, [1, 2]]
   >     }
   > }
   > 
   > ```
   >
   > 
   >
   > sql 总共4 道题目一共30 分钟 不是原题全是变种
   >
   > 1. 统计并输出每个区间标签下的员工 总数。
   >    Employees(
   >      employee_id   INT,      -- 员工唯一标识  
   >      hire_date     DATE      -- 入职日期
   >    );任务：先计算出每位员工截至今天的 工作年限（向下取整年数）。
   >    根据工作年限，将员工分成若干区间标签（例如：0-2年 ,3-5年, , 6-10年,10年以上）
   >
   >    ```sql
   >    select e.employee_id,
   >        e.hire_date,
   >        TIMESTAMPDIFF(year, e.hire_date, current_date) as seniority_years,
   >        case 
   >          when TIMESTAMPDIFF(year, e.hire_date, current_date) between 0 and 2 then '0-2 years'
   >          when TIMESTAMPDIFF(year, e.hire_date, current_date) between 3 and 5 then '3-5 years'
   >          when TIMESTAMPDIFF(year, e.hire_date, current_date) between 6 and 10 then '6-10 years'
   >          when TIMESTAMPDIFF(year, e.hire_date, current_date) > 10 then '10+ years'
   >        end as seniority_level
   >    from employees e;
   >    ```
   >
   > 2. 表结构：
   >    订单表：记录每笔订单的客户、金额及订单状态
   >    Orders (  order_id     INT,
   >      customer_id  INT,
   >      amount       DECIMAL(10,2),
   >      order_date   DATE,
   >      status       VARCHAR(20)    -- 如 'completed'、'canceled' 等
   >    );
   >
   >    Customers ( -- 客户表：记录每个客户所属的分组（如区域、用户类型等）
   >      customer_id      INT,
   >      customer_segment VARCHAR(50)
   >    );
   >    任务：只考虑status = 'completed' 且 order_date在 2023 年 6 月份内的订单。
   >    按customer_id聚合，计算当月 总消费total_spent。将结果与Customers表按customer_id连接，获得每个客户的customer_segment。在每个customer_segment内，按total_spent降序排序，用ROW_NUMBER() OVER (PARTITION BY customer_segment ORDER BY total_spent DESC)标记排名，取 前 N 名（例如 N=3）。
   >
   >    ```sql
   >    WITH customer_total_spent AS (
   >      SELECT
   >        o.customer_id,
   >        SUM(o.amount) AS total_amount
   >      FROM Orders AS o
   >      WHERE o.status = 'completed'
   >        AND o.order_date >= '2023-06-01'   -- inclusive
   >        AND o.order_date <  '2023-07-01'   -- exclusive (avoid including 7/1)
   >      GROUP BY o.customer_id
   >    ),
   >    customer_total_spent_ranked AS (
   >      SELECT
   >        s.customer_id,
   >        s.total_amount,
   >        c.customer_segment,
   >        ROW_NUMBER() OVER (
   >          PARTITION BY c.customer_segment
   >          ORDER BY s.total_amount DESC, s.customer_id
   >        ) AS rn
   >      FROM customer_total_spent AS s
   >      JOIN Customers AS c
   >        ON c.customer_id = s.customer_id   -- use JOIN if every customer must have a segment
   >    )
   >    SELECT
   >      customer_segment,customer_id,total_amount,rn
   >    FROM customer_total_spent_ranked
   >    WHERE rn <= 1
   >    ORDER BY customer_segment, rn;
   >    ```
   >
   > 3. 表结构：
   >    Devices(
   >      user_id      INT,       -- 用户ID
   >      device_id    INT,       -- 设备ID
   >      is_active    TINYINT,   -- 1 表示该设备最近有使用，0 表示未使用
   >      device_type  VARCHAR    -- 设备类型，例如 'mobile'、'desktop'、'tablet' 等
   >    );任务：
   >    定义 “活跃用户”：在Devices表中至少有一条is_active = 1的记录
   >    定义 “仅用手机的用户”：对于每个活跃用户，其所有is_active = 1的device_type都 等于'mobile'
   >    计算 “仅用手机的用户” 在 “活跃用户” 总数中的占比（百分比，保留两位小数）
   >    
   >    ```sql
   >    WITH active_users AS (
   >        SELECT DISTINCT user_id
   >        FROM Devices
   >        WHERE is_active = 1
   >    ),
   >    only_mobile_users AS (
   >        SELECT user_id
   >        FROM Devices
   >        WHERE is_active = 1
   >        GROUP BY user_id
   >        HAVING COUNT(DISTINCT CASE WHEN device_type <> 'mobile' THEN device_type END) = 0
   >    )
   >    SELECT 
   >        ROUND(
   >            COUNT(DISTINCT om.user_id) * 100.0 / COUNT(DISTINCT au.user_id),
   >            2
   >        ) AS mobile_only_ratio_percent
   >    FROM active_users au
   >    LEFT JOIN only_mobile_users om
   >           ON au.user_id = om.user_id;
   >    ```
   >
   > 4. 给定一个表
   >    Orders(id, order_date)，要求对每个id，计算连续下单天数，并返回：id, 
   >    segment_start：该连续段的起始日期
   >    segment_end：该连续段的结束日期
   >
   >    ```sql
   >    with unique_id_order_date as (
   >        select DISTINCT id, order_date
   >        from orders2
   >    ),
   >    seq as (
   >        select id, order_date,
   >               row_number() over (partition by id order by order_date) as rk
   >        from unique_id_order_date
   >    ),
   >    group_key as (
   >        select id, order_date, rk,
   >               date_sub(order_date, interval rk day) as grp
   >        from seq
   >    )
   >    select id, 
   >           min(order_date) as start_date,
   >           max(order_date) as end_date
   >           from group_key
   >    group by id, grp
   >    ```
   >
   > 
   >
   > 总的来讲别人家第三题或者第四题他们直接第一题，我以为第一轮phone screen 不会考这么难的，估计直接挂。。。。

3. 2025(1-3月) DataEng 硕士 全职@DoorDash - 猎头 - 技术电面  | 😐 Neutral 😐 Average | Fail | 在职跳槽

   > 今早刚面的
   > 1 python and 4 sql
   >
   > python是 给一个integer array，每一个算当天的earning，返回连续7天的和的最大值，比如说从那一天到之后的连续的7天的和的最大值，返回的是[higest_earning, [从哪一天开始算的]]，如果有相同天的话，需要都返回
   >
   > 同样的数据库， 有merchant, consumer，order, dasher, menu, item
   > 第一题是根据餐厅的距离来创建bucket
   >
   > ```sql
   > SELECT
   >   CASE
   >     WHEN m.distance_km < 1  THEN '[0,1)km'
   >     WHEN m.distance_km < 3  THEN '[1,3)km'
   >     WHEN m.distance_km < 5  THEN '[3,5)km'
   >     ELSE '>=5km'
   >   END AS distance_bucket,
   >   COUNT(*) AS merchant_count
   > FROM merchant m
   > WHERE m.is_active = 1
   > GROUP BY 1
   > ORDER BY 1;
   > 
   > ```
   >
   > 
   >
   > 第二题是根据餐厅的评分，要取top5，现根据评分然后根据餐厅名排列
   >
   > ```sql
   > SELECT merchant_id, name, city, rating
   > FROM (
   >   SELECT
   >     m.*,
   >     ROW_NUMBER() OVER (PARTITION BY m.city ORDER BY m.rating DESC, m.name ASC) AS rn
   >   FROM merchant m
   >   WHERE m.is_active = 1
   > ) t
   > WHERE rn <= 5
   > ORDER BY city, rating DESC, name;
   > 
   > ```
   >
   > 
   >
   > 第三题是算vegetarian餐厅在所有餐厅的percentage，menu和餐厅都要active
   >
   > ```sql
   > -- 认为“素食餐厅”= 其活跃菜单上所有活跃菜品都是素食；且商家、菜单均 active
   > WITH all_active_merchants AS (
   >   SELECT DISTINCT m.merchant_id
   >   FROM merchant m
   >   JOIN menu mn ON mn.merchant_id = m.merchant_id AND mn.is_active = 1
   >   WHERE m.is_active = 1
   > ),
   > merchant_item_stat AS (
   >   SELECT
   >     m.merchant_id,
   >     SUM(CASE WHEN i.is_active = 1 THEN 1 ELSE 0 END) AS active_items,
   >     SUM(CASE WHEN i.is_active = 1 AND i.is_vegetarian = 1 THEN 1 ELSE 0 END) AS active_veg_items
   >   FROM merchant m
   >   JOIN menu mn ON mn.merchant_id = m.merchant_id AND mn.is_active = 1
   >   JOIN item i  ON i.menu_id = mn.menu_id
   >   WHERE m.is_active = 1
   >   GROUP BY m.merchant_id
   > ),
   > strict_veg_merchants AS (
   >   SELECT merchant_id
   >   FROM merchant_item_stat
   >   WHERE active_items > 0 AND active_items = active_veg_items
   > )
   > SELECT
   >   (SELECT COUNT(*) FROM strict_veg_merchants) AS veg_cnt,
   >   (SELECT COUNT(*) FROM all_active_merchants) AS total_cnt,
   >   ROUND((SELECT COUNT(*) FROM strict_veg_merchants) / (SELECT COUNT(*) FROM all_active_merchants) * 100, 2) AS veg_pct
   > ;
   > 
   > ```
   >
   > 
   >
   > 第四题是如果一个consumer连续几天order food，把records compress
   > 比如
   > Consumer ｜ Order_Date
   > 1                    01-01
   > 1                    01-01
   > 1                    01-02
   > 1                    01-03
   > 1                    01-08
   > 1                    01-09
   >
   > output:
   > Consumer ｜Start_Date | End_Date
   > 1                    01-01          01-03
   > 1                    01-08          01-09
   >
   > ```sql
   > -- 先把订单按“天”去重（同一天多单算一天）
   > WITH days AS (
   >   SELECT DISTINCT
   >     o.consumer_id,
   >     DATE(o.order_time) AS d
   >   FROM `order` o
   >   WHERE o.status = 'completed'      -- 如需
   > ),
   > 
   > -- 连续段分组核心：用 row_number 和日期做“等差”对齐
   > rnk AS (
   >   SELECT
   >     consumer_id,
   >     d,
   >     ROW_NUMBER() OVER (PARTITION BY consumer_id ORDER BY d) AS rn
   >   FROM days
   > ),
   > 
   > -- key = d - rn 天；对同一消费者，连续日期会得到相同的 key
   > grp AS (
   >   SELECT
   >     consumer_id,
   >     d,
   >     DATE_SUB(d, INTERVAL rn DAY) AS grp_key
   >   FROM rnk
   > )
   > 
   > -- 聚合得到每段的起止
   > SELECT
   >   consumer_id,
   >   MIN(d) AS start_date,
   >   MAX(d) AS end_date
   > FROM grp
   > GROUP BY consumer_id, grp_key
   > ORDER BY consumer_id, start_date;
   > 
   > ```
   >
   > 
   >
   > Python是一个ETL 的graph，如果上游fail了下游也会fail，输出下游fail了以后哪些上游job不能run 了
   >
   > ```java
   > /*
   > 第一步：题目描述
   > 给定一个有向无环图（DAG）表示ETL依赖：边 u -> v 表示 v 依赖 u（u 是上游，v 是下游）。
   > 规则：若某个作业失败，则其所有可达的下游作业都无法继续运行（被“阻断”）。
   > 输入：边列表（依赖关系）、初始失败作业集合 failedJobs。
   > 输出：所有“无法运行”的作业集合（包含所有初始失败作业及其所有下游可达作业）。
   > 说明：如果需要，也可以输出每个被阻断作业对应的“首个导致阻断的失败祖先”。
   > 
   > 第二步：解题思路（举例说明）
   > 1) 建图
   >    - 用邻接表表示：adj[u] 存放所有直接下游节点 v（存在边 u -> v）。
   > 2) 多源遍历
   >    - 以所有初始失败作业为起点，进行 BFS/DFS 沿边方向向下游扩散。
   >    - 所有被访问到的节点（含起点）均标记为“不能运行”。
   > 3) 去重与停止条件
   >    - 用 visited 集合避免重复遍历。
   > 4) 可选：记录阻断来源
   >    - 在入队时携带“来源失败作业”，首次访问某被阻断节点时记录其来源。
   > 5) 举例
   >    - 依赖：A->B, A->C, B->D, C->D, D->E
   >    - 初始失败：B
   >    - 从 B 出发能达 D, E，因此“不能运行”={B, D, E}。
   >    - 若初始失败为 {C, B}，则“不能运行”={B, C, D, E}。
   > 
   > 第三步：时间与空间复杂度
   > - 设节点数为 N、边数为 M、失败源数为 F。
   > - 时间复杂度：O(N + M)，每条边至多被遍历一次（多源BFS/DFS）。
   > - 空间复杂度：O(N + M)，邻接表与 visited / 队列存储。
   > 
   > 第四步：Java 实现（含详细中文注释）
   > */
   > 
   > import java.util.*;
   > 
   > public class EtlFailurePropagation {
   >     /**
   >      * 计算所有“不能运行”的作业（包含初始失败作业及其所有下游）
   >      * @param edges 依赖边列表，边 [u, v] 表示 v 依赖 u（u->v）
   >      * @param failedJobs 初始失败作业集合
   >      * @return 一个结果对象，包含：
   >      *   - blocked: Set<String> 所有不能运行的作业（含初始失败）
   >      *   - blockerOf: Map<String,String> 每个被阻断作业 -> 首个导致阻断的失败祖先（初始失败之一）
   >      */
   >     public static Result computeBlockedJobs(List<String[]> edges, Set<String> failedJobs) {
   >         // 1) 建立邻接表：u -> list of v（v 是 u 的直接下游）
   >         Map<String, List<String>> adj = new HashMap<>();
   >         // 收集所有出现过的节点，避免遗漏无出边/无入边节点
   >         Set<String> allNodes = new HashSet<>();
   > 
   >         for (String[] e : edges) {
   >             String u = e[0], v = e[1];
   >             allNodes.add(u);
   >             allNodes.add(v);
   >             adj.computeIfAbsent(u, k -> new ArrayList<>()).add(v);
   >         }
   >         // 确保所有节点都有键
   >         for (String node : allNodes) {
   >             adj.computeIfAbsent(node, k -> new ArrayList<>());
   >         }
   > 
   >         // 2) 多源BFS：从所有初始失败作业出发，向下游扩散
   >         Set<String> blocked = new HashSet<>();          // 不能运行的集合
   >         Map<String, String> blockerOf = new HashMap<>(); // 记录被阻断作业 -> 首个失败祖先
   >         Deque<String> dq = new ArrayDeque<>();
   > 
   >         // 初始化：把所有初始失败作业入队，且它们的阻断来源就是自己
   >         for (String f : failedJobs) {
   >             if (!allNodes.contains(f)) {
   >                 // 如果失败作业未在图中出现，也加入集合，使结果可见
   >                 allNodes.add(f);
   >                 adj.putIfAbsent(f, new ArrayList<>());
   >             }
   >             if (blocked.add(f)) {
   >                 blockerOf.put(f, f); // 自身失败的来源是自己
   >                 dq.offer(f);
   >             }
   >         }
   > 
   >         // BFS
   >         while (!dq.isEmpty()) {
   >             String cur = dq.poll();
   >             String originFailed = blockerOf.get(cur); // 当前节点的失败来源（初始失败之一）
   >             for (String nxt : adj.getOrDefault(cur, Collections.emptyList())) {
   >                 if (!blocked.contains(nxt)) {
   >                     blocked.add(nxt);
   >                     // 该下游第一次被访问，记录其阻断来源为当前节点的来源
   >                     blockerOf.put(nxt, originFailed);
   >                     dq.offer(nxt);
   >                 }
   >             }
   >         }
   > 
   >         return new Result(blocked, blockerOf);
   >     }
   > 
   >     // 结果封装
   >     public static class Result {
   >         public final Set<String> blocked;
   >         public final Map<String, String> blockerOf;
   > 
   >         public Result(Set<String> blocked, Map<String, String> blockerOf) {
   >             this.blocked = blocked;
   >             this.blockerOf = blockerOf;
   >         }
   >     }
   > 
   >     // 演示
   >     public static void main(String[] args) {
   >         // 图：A->B, A->C, B->D, C->D, D->E
   >         List<String[]> edges = Arrays.asList(
   >             new String[]{"A", "B"},
   >             new String[]{"A", "C"},
   >             new String[]{"B", "D"},
   >             new String[]{"C", "D"},
   >             new String[]{"D", "E"}
   >         );
   > 
   >         // 初始失败：B
   >         Set<String> failed = new HashSet<>(Arrays.asList("B"));
   > 
   >         Result r1 = computeBlockedJobs(edges, failed);
   >         System.out.println("Blocked (fail=B): " + r1.blocked);         // 期望: [B, D, E]
   >         System.out.println("BlockerOf: " + r1.blockerOf);               // D/E 的来源应为 B
   > 
   >         // 初始失败：B, C
   >         Set<String> failed2 = new HashSet<>(Arrays.asList("B", "C"));
   >         Result r2 = computeBlockedJobs(edges, failed2);
   >         System.out.println("Blocked (fail=B,C): " + r2.blocked);        // 期望: [B, C, D, E]
   >         System.out.println("BlockerOf: " + r2.blockerOf);               // D/E 的来源可能为 B 或 C（取决于首次访问）
   >     }
   > }
   > 
   > ```
   >
   > 
   >
   > 借楼，上周同样面试了DD，他们的SQL题目的Schema也是一样的，也是这几个表。各位要投DD的要好好想想这个结构了
   >
   > 但是有很多细节，比如有很多status和active_status这一类的需要注意
   >
   > 时间还是挺紧张的，祝大家都能找到理想的工作

4. 2024(10-12月) DataEng 硕士 全职@doordash - Other - 其他  | 😃 Positive 😐 Average | Fail | 其他

   > 楼主是之前在职的时候收到过他们家HR对于DE岗的邀请，无奈那个时候刚入职实在没精力弄面试，但是6月份被裁了所以腆着脸去reach out并且要到了一个面试lol
   >
   > 电面是在4sql 和 1python，我的面试官是一个还不错的天竺小哥，他还好心问我想先做sql还是先做python嘞
   > 以下内容需要积分高于 120 您已经可以浏览
   >
   > 先说sql吧，是一个有着5张表的schema，然后要你根据这个schema以及他们问你的问题来写query
   > schema里面有merchant, order, dasher, menu, item
   > 第一题是要根据餐厅的距离来创建bucket，很简单用case when就可以
   > 第二题是根据餐厅的评分，要取top5，这个也很简单
   > 我卡在了第三题，是要你算vegetarian餐厅在所有餐厅的percentage，但是这个vetegarian的flag在item里面，也就是说要这个餐厅的所有menu里面的item都是vegetarian才能qualify这个条件然后我就在这里卡了好一会。。。其实是太紧张了脑袋卡住了现在想想也没有那么难TAT
   > python也是和dasher相关的题目，其实不难，处理下edge case以及建个词典就可以。
   >
   > 
   >
   > 天竺小哥在我写代码的时候走神了一会，但是还是提供了一些帮助的，感觉应该挂了但是目前还没有收到消息，总之求米求米！！

5. 2024(4-6月) DataEng 硕士 合同工@doordash - 猎头 - 技术电面  | 😃 Positive 😣 Hard | Fail | 应届毕业生

   > 新鲜DoorDash面经 总共4轮，面到第三轮挂了。
   > 第一轮 猎头phone call，相关背景询问
   > 第二轮 doordash ds phone call，主要是背景调查，问了一个 转换率降低的话怎么办的问题
   > 第三轮 sql zoom面试，三道sql题
   >
   > 第一题：
   >  找到一个用户的第一单和第二单的间隔时间。
   >
   > ```sql
   > /*
   > 第一步：题目描述
   > 给定订单表 Orders(user_id, order_id, order_time, status, ...)，请计算每个用户“第一单”和“第二单”之间的时间间隔。
   > 若某用户订单数 < 2，则不输出该用户。时间单位可按需返回（秒/小时/天）。
   > 
   > 第二步：解题思路
   > 1) 先用窗口函数按 user_id 分区、按 order_time（必要时再加 order_id）排序，给每笔订单打序号 rn。
   > 2) 取 rn=1 与 rn=2 的两笔订单，做同一用户内的聚合/自连接，计算第二单时间 - 第一单时间。
   > 3) 实务中常需要过滤无效订单（如取消），可在 WHERE status='completed' 先过滤。
   > 4) 若同一时间戳有并列，建议用 (order_time, order_id) 作为确定性排序键。
   > 
   > 第三步：时间/空间复杂度
   > - 时间：O(N log N)（排序主导，按每个 user 分区排序）；窗口打标本身 O(N)。
   > - 空间：O(N)（窗口帧与中间结果）。
   > 
   > 第四步：SQL 方案
   > -- 下面分别给出 MySQL 8+（窗口函数）、MySQL 5.x（无窗口函数）、PostgreSQL、BigQuery 四种写法。
   > */
   > 
   > -- =========================
   > -- A. MySQL 8+（推荐）
   > -- =========================
   > WITH ranked AS (
   >   SELECT
   >     user_id,
   >     order_id,
   >     order_time,
   >     ROW_NUMBER() OVER (
   >       PARTITION BY user_id
   >       ORDER BY order_time ASC, order_id ASC
   >     ) AS rn
   >   FROM Orders
   >   WHERE status = 'completed'  -- 如需
   > ),
   > first_two AS (
   >   SELECT user_id,
   >          MAX(CASE WHEN rn = 1 THEN order_time END) AS first_order_time,
   >          MAX(CASE WHEN rn = 2 THEN order_time END) AS second_order_time
   >   FROM ranked
   >   WHERE rn IN (1, 2)
   >   GROUP BY user_id
   > )
   > SELECT
   >   user_id,
   >   first_order_time,
   >   second_order_time,
   >   TIMESTAMPDIFF(SECOND, first_order_time, second_order_time) AS diff_seconds,
   >   TIMESTAMPDIFF(HOUR,   first_order_time, second_order_time) AS diff_hours,
   >   TIMESTAMPDIFF(DAY,    first_order_time, second_order_time) AS diff_days
   > FROM first_two
   > WHERE second_order_time IS NOT NULL
   > ORDER BY user_id;
   > 
   > -- 说明：
   > -- 1) ranked 给每个用户的订单按时间排 rn=1,2,...
   > -- 2) 聚合到 first_two 后，仅保留有第二单的用户（second_order_time IS NOT NULL）。
   > -- 3) TIMESTAMPDIFF 可自由选择单位（SECOND/HOUR/DAY）。
   > 
   > ```
   >
   > 
   >
   >  计算用户两个订单之间向差的平均需要天数，我理解的是找到第一单跟第二单相差的天数，第二单跟第三单相差的天数，求和然后除以总单数。
   >  每个月订单超过30单的是高频用户，找到每个月高频用户的百分比。
   >
   > ```sql
   > -- 题意拆解：
   > -- A) 计算每个用户“相邻两单之间”的平均间隔天数：
   > --    正确做法是：按时间给订单排序，计算相邻两单的差值（第二单-第一单、第三单-第二单...），
   > --    然后对这些差值取平均。分母是“相邻对数 = 订单数 - 1”，而不是“总订单数”。
   > 
   > -- B) 定义“高频用户”：某月下单次数 > 30。
   > --    计算“每个月高频用户的百分比” = （该月高频用户数 / 该月有下单的活跃用户数）* 100%。
   > --    注：分母仅统计当月有下单的用户（活跃用户）。
   > 
   > /* ================================
   >    A) 每个用户相邻订单的平均间隔天数（MySQL 8+）
   >    表：Orders(user_id, order_id, order_time, status, ...)
   >    如需只算有效订单，可在 WHERE 里加上 status='completed'
   > =================================*/
   > WITH ordered AS (
   >   SELECT
   >     user_id,
   >     order_id,
   >     order_time,
   >     LAG(order_time) OVER (
   >       PARTITION BY user_id
   >       ORDER BY order_time ASC, order_id ASC
   >     ) AS prev_time
   >   FROM Orders
   >   -- WHERE status = 'completed'
   > ),
   > gaps AS (
   >   SELECT
   >     user_id,
   >     TIMESTAMPDIFF(DAY, prev_time, order_time) AS gap_days
   >   FROM ordered
   >   WHERE prev_time IS NOT NULL
   > )
   > SELECT
   >   user_id,
   >   ROUND(AVG(gap_days), 2)        AS avg_gap_days,  -- 平均相邻间隔（天）
   >   COUNT(*)                        AS gap_count,     -- 相邻对数（分母）
   >   MIN(gap_days)                   AS min_gap_days,  -- 可选：最短间隔
   >   MAX(gap_days)                   AS max_gap_days   -- 可选：最长间隔
   > FROM gaps
   > GROUP BY user_id
   > ORDER BY user_id;
   > 
   > -- 说明：
   > -- 1) LAG 拿到“上一单”的时间，TIMESTAMPDIFF(DAY, prev, curr) 计算天数差。
   > -- 2) 对每个 user_id 聚合即可得到平均间隔天数。
   > 
   > 
   > /* ============================================
   >    B) 每个月高频用户（>30单）占比（MySQL 8+）
   >    月份分桶：用 DATE_FORMAT(order_time, '%Y-%m-01') 代表当月第一天
   > =============================================*/
   > WITH monthly_counts AS (
   >   SELECT
   >     DATE_FORMAT(order_time, '%Y-%m-01') AS month_start,  -- 或者使用 DATE(order_time) 再归一到月初
   >     user_id,
   >     COUNT(*) AS monthly_orders
   >   FROM Orders
   >   -- WHERE status = 'completed'
   >   GROUP BY 1, 2
   > ),
   > agg AS (
   >   SELECT
   >     month_start,
   >     COUNT(*) AS active_users,                                              -- 当月有下单的用户数
   >     SUM(CASE WHEN monthly_orders > 30 THEN 1 ELSE 0 END) AS high_freq_users -- 当月>30单的用户数
   >   FROM monthly_counts
   >   GROUP BY month_start
   > )
   > SELECT
   >   month_start,
   >   active_users,
   >   high_freq_users,
   >   ROUND(high_freq_users / active_users * 100, 2) AS high_freq_pct
   > FROM agg
   > ORDER BY month_start;
   > 
   > -- 备注与优化建议：
   > -- - 并列时间戳：排序键中加入 order_id，确保顺序稳定（已在窗口里处理）。
   > -- - 时区统一：order_time 建议统一存 UTC，展示时再转换。
   > -- - 性能：给 (user_id, order_time) 建联合索引；若量大，按日期做分区表。
   > -- - 口径可调：如需仅统计“完成订单”，在两段查询的 WHERE 注释处解开 status 过滤。
   > 
   > ```
   >
   > 
   >
   > 前两次的面试体验蛮好的，都是国人。尤其是第二轮的小哥还说你是ng 我理解可能没什么经验 还讲了团队成长之类的。
   > 第二轮的sql面感觉是个abc，就是着急finish的感觉。后边想要跟他交流一些条件的定义 他直接说 你不用写了就是把大概的思路给我说一下就好。我说完了 他就说 oh that makes sense 就完了 也是全程无交流。 面试挂了很难受，求加米 求安慰。

6. 2024(1-3月) DataEng 硕士 全职@doordash - 内推 - Onsite  | 😐 Neutral 😣 Hard | Fail | 在职跳槽

   > 数据工程师onsite 面筋
   > 1. case study - 送餐eta 这个metric怎么衡量，我说就是搞个别的metrics比如真实到达时间和eta的差距。然后问如果你这个metric最近不断下降怎么办。
   >    ```java
   >    /*
   >    ===============================================================
   >    Case Study: 送餐 ETA Metric 衡量 & 指标下降的应对方案
   >    ===============================================================
   >    
   >    1. 如何衡量 ETA 准确性 (Metrics)
   >    ---------------------------------------------------------------
   >    - ETA Error = | Actual_Arrival_Time – Predicted_ETA |
   >    - Mean Absolute Error (MAE): 平均误差
   >    - P90 / P95 Error: 检查长尾订单的预测效果
   >    - On-time Rate: 误差 ≤ 5 分钟的订单占比
   >    - Bias: 判断预测是偏早还是偏晚
   >    
   >    作为 Data Engineer 的职责：
   >    - 确保 Actual_Arrival_Time 来源准确（GPS/订单完成时间）。
   >    - 确保 Predicted_ETA 有版本信息（模型版本追踪）。
   >    - 增加 Data Quality Check（时间戳缺失、丢单、时区错误）。
   >    
   >    2. 如果 ETA Metric 持续下降怎么办
   >    ---------------------------------------------------------------
   >    Step 1: 检查数据质量问题
   >    - GPS 是否缺失或延迟？
   >    - 订单完成事件是否延迟进入 Kafka？
   >    - 是否有数据分布倾斜（某些城市数据缺失）？
   >    
   >    Step 2: 考虑外部环境变化
   >    - 天气：暴雨、下雪 → 普遍送餐延迟。
   >    - 交通：节假日、道路封闭。
   >    - 供需：高峰期 Dasher 不足。
   >    
   >    Step 3: 检查模型或特征问题
   >    - 模型 Drift（训练数据 vs 实际分布）。
   >    - 特征缺失（traffic、餐厅 prep time 没有写入 pipeline）。
   >    - 模型版本切换后误差增加。
   >    
   >    3. 解决方案
   >    ---------------------------------------------------------------
   >    短期：
   >    - 回滚到旧 ETA 模型。
   >    - 临时增加 buffer，例如 ETA + 5 分钟。
   >    
   >    长期：
   >    - 加强监控：数据新鲜度、完整性、分布。
   >    - 特征改进：引入实时交通、天气、餐厅负载。
   >    - 定期 retrain 模型，避免 drift。
   >    
   >    ===============================================================
   >    总结：
   >    作为 Data Engineer：
   >    - 先定义清楚指标 (MAE / P95 / On-time Rate)。
   >    - 建立数据管道监控 (质量 & 延迟)。
   >    - 分层排查 (数据 → 外部环境 → 模型)。
   >    - 提供短期补救方案 + 长期优化策略。
   >    ===============================================================
   >    */
   >    
   >    ```
   >
   >    
   > 2. system design - 设计一个customer portal 可以看cloud service的billing information。 比如s3 是怎么产生各种费用然后如何report给user。 需要从源头开始设计，我只会设计etl pipeline，源头数据怎么产生的完全乱说了。希望有大佬讲讲。。。
   >
   > 3. lp， 没什么好说的，印度哥全程哈欠
   > 4. data modeling。 一个app用来看健身视频的，里面有什么class， video， customer， trainner， 这个比较简单，最后给了一个图表问怎么写个query支持这个图标，但是不用跑的，大概对了就行
   >
   > 求米！！！
   >
   > 请问是 data engineer 这个title吗？ 还是software engineer in data platform? 谢谢
   > 他叫software engineer - data engineer, 实际上就是data engineer，和data platform无关

7. 2022(4-6月) DataEng 硕士 全职@doordash - 内推 - Onsite  | 😃 Positive 😐 Average | Pass | 在职跳槽

   > 两周前面的VO，一共4轮：
   > 以下内容需要积分高于 100 您已经可以浏览
   >
   > 1. Data Modeling case study: 围绕一个app。有很多小问，app是一个健身软件，用户付费之后可以看里面的视频。问设计metrics条件判断怎样才算是daily active users。然后data modeling entity design。如何满足实时或者batch查询， 最后sql统计各个 category的一周的 daliy active users统计，大概是这样
   >    ```sql
   >    /*
   >    ============================================================
   >    Data Modeling Case Study: 健身 App (Fitness Video Platform)
   >    ============================================================
   >          
   >    1. 场景描述
   >    ------------------------------------------------------------
   >    - App：健身视频平台。
   >    - 用户：注册用户，可以付费订阅。
   >    - 功能：用户付费后可以观看视频。
   >    - 分析目标：统计 DAU (Daily Active Users)，并做 category 维度的活跃分析。
   >          
   >    ------------------------------------------------------------
   >          
   >    2. DAU 定义 (Daily Active Users)
   >    ------------------------------------------------------------
   >    条件判断：
   >    - 用户在某一天有以下行为之一：
   >      * 登录 app
   >      * 播放视频 (>=1 次)
   >      * 其他可选 engagement 行为 (like/comment/share)
   >    - 因为这是付费内容平台，"active" 更核心的定义通常为
   >      “在当天有观看视频行为的用户数”。
   >          
   >    所以 DAU 判定逻辑：
   >    - DISTINCT(user_id) WHERE action_type IN ('login', 'video_play') ON that date。
   >          
   >    ------------------------------------------------------------
   >          
   >    3. Data Modeling (Entity Design)
   >    ------------------------------------------------------------
   >    主要实体：
   >    - User (用户)
   >    - Subscription (订阅)
   >    - Video (视频)
   >    - Category (视频分类)
   >    - UserAction (用户行为事件)
   >          
   >    表结构设计：
   >          
   >    User
   >    - user_id (PK)
   >    - name
   >    - email
   >    - signup_date
   >          
   >    Subscription
   >    - sub_id (PK)
   >    - user_id (FK -> User)
   >    - plan_type (monthly/annual)
   >    - start_date
   >    - end_date
   >    - status (active/expired)
   >          
   >    Video
   >    - video_id (PK)
   >    - title
   >    - category_id (FK -> Category)
   >    - duration
   >          
   >    Category
   >    - category_id (PK)
   >    - category_name
   >          
   >    UserAction
   >    - action_id (PK)
   >    - user_id (FK -> User)
   >    - video_id (FK -> Video, nullable if action=login)
   >    - action_type (login, video_play, like, share)
   >    - action_time (timestamp)
   >          
   >    ------------------------------------------------------------
   >          
   >    4. SQL 题目：统计各个 category 的一周的 Daily Active Users
   >    ------------------------------------------------------------
   >    目标：
   >    - 输出表：每天每个 category 的 DAU (去重用户数)。
   >          
   >    SQL (MySQL 8+ 示例)：
   >          
   >    SELECT 
   >        DATE(ua.action_time) AS action_date,
   >        v.category_id,
   >        c.category_name,
   >        COUNT(DISTINCT ua.user_id) AS daily_active_users
   >    FROM UserAction ua
   >    JOIN Video v ON ua.video_id = v.video_id
   >    JOIN Category c ON v.category_id = c.category_id
   >    WHERE ua.action_type = 'video_play'
   >      AND ua.action_time >= CURDATE() - INTERVAL 7 DAY
   >    GROUP BY DATE(ua.action_time), v.category_id, c.category_name
   >    ORDER BY action_date, category_name;
   >          
   >    ------------------------------------------------------------
   >          
   >    5. 结果示例 (预期输出)
   >    ------------------------------------------------------------
   >    +------------+-------------+---------------+-------------------+
   >    | action_date| category_id | category_name | daily_active_users|
   >    +------------+-------------+---------------+-------------------+
   >    | 2023-08-01 | 1           | Yoga          | 150               |
   >    | 2023-08-01 | 2           | HIIT          |  80               |
   >    | 2023-08-01 | 3           | Pilates       |  45               |
   >    | 2023-08-02 | 1           | Yoga          | 160               |
   >    | 2023-08-02 | 2           | HIIT          |  95               |
   >    | ...        | ...         | ...           | ...               |
   >    +------------+-------------+---------------+-------------------+
   >          
   >    ------------------------------------------------------------
   >          
   >    6. 总结
   >    ------------------------------------------------------------
   >    - DAU 定义需要结合业务目标 (健身 app → 核心是 video play)。
   >    - Data Modeling 实体：User, Subscription, Video, Category, UserAction。
   >    - SQL：用 DISTINCT user_id 按日期 + category 聚合。
   >    - 可扩展：
   >      * 加维度：设备类型、会员类型。
   >      * 加指标：WAU/MAU，付费转化率。
   >    ============================================================
   >    */
   >          
   >    ```
   >
   > 
   >
   > 2. ETL Design。设计一个pipeline汇总各个AWS service的billing，好制作report
   >    ```java 
   >    /*
   >    ============================================================
   >    System Design: ETL Pipeline for AWS Billing
   >    ============================================================
   >          
   >    用户登录 Customer Portal 查看自己的 cloud billing。
   >    支持多种 cloud service（S3, EC2, RDS, Lambda …）。
   >    显示费用细节：
   >    存储容量（GB/月）
   >    请求次数（GET/PUT/DELETE）
   >    数据传输带宽（GB 出站流量）
   >    支持 汇总 + 明细报表（daily / monthly）。
   >    用户可以下载账单 (CSV/PDF)。
   >    可扩展：未来支持多云 (AWS, GCP, Azure)。
   >          
   >    ------------------------------------------------------------
   >          
   >    2. Non-Functional Requirements 非功能需求
   >    Scalability：支持百万级用户查询账单。
   >    Reliability：保证账单计算准确，不丢数据。
   >    Security：账单涉及用户隐私，需隔离和加密。
   >    Freshness：账单延迟不超过 1 小时（near real-time）。
   >    Cost efficiency：ETL/计算必须在合理成本内运行。
   >          
   >    ------------------------------------------------------------
   >          
   >    3. Data Sources 数据源
   >    以 AWS S3 为例：
   >    存储费用：按 GB/月计费 → 每小时采样存储容量。
   >    请求费用：按请求次数 (GET, PUT, DELETE) 计费 → 来自 CloudTrail / access log。
   >    流量费用：按出站流量 GB 计费 → 来自 VPC Flow Log / CloudWatch Metrics。
   >    👉 AWS 内部会有 Metering Service，不断产生日志和计量事件。
   >          
   >    QPS 估算：
   >    假设 1M 用户，每个用户每天 1K API 调用。
   >    日请求量 = 1B events/day ≈ 11.5K events/s。
   >    存储估算：
   >    每条 event ~ 200 bytes。
   >    每天 ~ 200 GB → 一年 ~ 73 TB。
   >          
   >    ------------------------------------------------------------
   >          
   >    4. ETL Pipeline 设计
   >          
   >    Data Ingestion (数据采集)
   >    	Cloud service (S3, EC2) → 事件写入 Kafka / Kinesis。
   >    Billing Processor (实时计费引擎)
   >    	使用 Flink / Spark Streaming → 聚合 usage metrics。
   >    	生成 (user_id, service_id, usage_type, amount, timestamp)。
   >    Billing Database (账单存储)
   >      OLTP：存储用户账单明细（Postgres / Aurora）。
   >      OLAP：存储聚合账单（Snowflake / BigQuery / Redshift）。
   >    API Gateway
   >    	提供 REST/GraphQL API 给 Customer Portal。
   >    Customer Portal (前端)
   >    	React/Next.js + Auth Service (IAM/SSO)。
   >          
   >    ------------------------------------------------------------
   >          
   >    5. Data Model 表设计
   >          
   >    BillingEvent (原始事件)
   >    - event_id | account_id | service | usage_type | usage_amount | unit | cost | timestamp
   >          
   >    BillingDailySummary
   >    - account_id | service | date | total_usage | total_cost
   >          
   >    BillingMonthlySummary
   >    - account_id | service | year_month | total_cost | breakdown_json
   >          
   >    AccountBudget
   >    - account_id | budget | threshold_pct | notify_email
   >          
   >    ------------------------------------------------------------
   >          
   >    6. Workflow 数据流 (ASCII 图)
   >    [Cloud Service (S3/EC2 Logs)]
   >              │
   >              ▼
   >       [Kafka / Kinesis]  ← ingestion
   >              │
   >              ▼
   >       [Flink / Spark Streaming] ← 实时聚合 usage
   >              │
   >              ├── [OLTP DB: Aurora] ← 存储账单明细 (实时查询)
   >              └── [OLAP DB: Snowflake/BigQuery] ← 汇总报表 (BI)
   >              │
   >              ▼
   >       [API Gateway + Billing Service]
   >              │
   >              ▼
   >       [Customer Portal (React/Next.js)]
   >        7. Scaling / Optimization 扩展策略
   >          
   >       - 数据量大 → 日志写 S3，Glue ETL → Redshift Spectrum 查询。
   >          - 实时性 → Kinesis + Flink 流式聚合，Portal 查询 DynamoDB。
   >       - 成本优化 → 冷数据归档 S3 Glacier；Redshift 仅存聚合。
   >       - 可靠性 → Glue Job Bookmark 防重复；CUR 分区加载。
   >       
   >    ------------------------------------------------------------
   >       
   >       8. 面试 Follow-up 问答
   >       Q: 如何支持实时账单？
   >          A: CUR 批处理保证权威，流式 ETL 提供实时预览。
   >       
   >       Q: 如何保证和 AWS 官方账单一致？
   >       A: 定期 reconcile，CUR vs 内部计算，写审计日志。
   >          
   >       Q: 如何实现多租户隔离？
   >       A: account_id 强制过滤；Redshift/Snowflake 启用 Row-Level Security。
   >    
   > 3. Hiring Manager: BQ
   > 
   >    4. Data scientist team的人来面，基本上也是bq。
   > 
   > 不到一个星期就出结果了，说是过了，然后第二天约了一个组的manager team match。结果没match上。然后recruiter说我这个级别(E3, E4)只有那个组有空位，所以就没有offer了。
   >    祝好运，求加米！
   
8. 2022(4-6月) DataEng 硕士 全职@doordash - 猎头 - 技术电面  | 😐 Neutral 😐 Average | WaitList | 在职跳槽

   > L C 要而丝，有两个extension。1个是终结点只能是打了星号的node，而且path只能有首位两个打星号的。2是把path也返回出来。

9. 2021(10-12月) DataEng 硕士 全职@doordash - 猎头 - Onsite 在线笔试  | 😐 Neutral 😐 Average | WaitList | 在职跳槽

   > 约了两轮VO,
   > 第一轮先是Project Deep Dive, 介绍自己曾经做过的项目，项目的难点，delivery 的状态，总结教训，如果重新做此项目你会如何避免之前犯的那些问题。
   > 第二轮是是sit down coding, 题目跟刷题网 额以灵，额灵漆 几乎一样
   >
   > 首先给了一组dependencies，为了确认我已经理解题目， 让我口头说出期望的输出结果
   > 然后介绍思路用Topological  Sort实现
   >
   > 运行，自己输入input，打印output，出结果
   > 然后问如何让那些不依赖于其他科目的项目先输出，接着实现
   > 然后问如何知道无法执行给定的input, 就是判断是否存在Cycle dependency，接着实现
   >
   > 
   >
   > 目前还不知道面试结果
   >
   > 希望能帮到大家，如果有米请打米，很多帖子看不全，谢谢！

10. 2019(10-12月) DataEng 硕士 全职@doordash - 网上海投 - 技术电面  | | Other | 在职跳槽

   > 貌似是个abc，讲话好快，聊天时信号也一般，基本听词猜意。。题不难。
   > 以下内容需要积分高于 200 您已经可以浏览
   >
   > 1. 买卖股票只交易一次
   > 2. 类似三舅舅，给出a=b, b=c, c=d, 判断a==d?, 返回True;a==z?, 返回False