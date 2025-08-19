1. 2025(4-6月) DataEng 本科 全职@doordash - 内推 - 技术电面  | 😐 Neutral 😣 Hard | Other | 在职跳槽

   > python 20 分钟
   > 1道题目 sliding window 的题目
   > 陆思伞的变种，是和不是average，还要给出所有的start
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
   > 
   >
   > 4. 给定一个表
   >    Orders(id, order_date)，要求对每个id，计算连续下单天数，并返回：id, 
   >    segment_start：该连续段的起始日期
   >    segment_end：该连续段的结束日期
   >
   > 总的来讲别人家第三题或者第四题他们直接第一题，我以为第一轮phone screen 不会考这么难的，估计直接挂。。。。

2. 2025(1-3月) DataEng 硕士 全职@DoorDash - 猎头 - 技术电面  | 😐 Neutral 😐 Average | Fail | 在职跳槽

   > 今早刚面的
   > 1 python and 4 sql
   >
   > python是 给一个integer array，每一个算当天的earning，返回连续7天的和的最大值，比如说从那一天到之后的连续的7天的和的最大值，返回的是[higest_earning, [从哪一天开始算的]]，如果有相同天的话，需要都返回
   >
   > 同样的数据库， 有merchant, consumer，order, dasher, menu, item
   > 第一题是根据餐厅的距离来创建bucket
   > 第二题是根据餐厅的评分，要取top5，现根据评分然后根据餐厅名排列
   >
   > 第三题是算vegetarian餐厅在所有餐厅的percentage，menu和餐厅都要active
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
   > Python是一个ETL 的graph，如果上游fail了下游也会fail，输出下游fail了以后哪些上游job不能run 了
   >
   > 借楼，上周同样面试了DD，他们的SQL题目的Schema也是一样的，也是这几个表。各位要投DD的要好好想想这个结构了
   >
   > 但是有很多细节，比如有很多status和active_status这一类的需要注意
   >
   > 时间还是挺紧张的，祝大家都能找到理想的工作

3. 2024(10-12月) DataEng 硕士 全职@doordash - Other - 其他  | 😃 Positive 😐 Average | Fail | 其他

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

4. 2024(4-6月) DataEng 硕士 合同工@doordash - 猎头 - 技术电面  | 😃 Positive 😣 Hard | Fail | 应届毕业生

   > 新鲜DoorDash面经 总共4轮，面到第三轮挂了。
   > 第一轮 猎头phone call，相关背景询问
   > 第二轮 doordash ds phone call，主要是背景调查，问了一个 转换率降低的话怎么办的问题
   > 第三轮 sql zoom面试，三道sql题
   >
   > 第一题：
   >     找到一个用户的第一单和第二单的间隔时间。
   >     计算用户两个订单之间向差的平均需要天数，我理解的是找到第一单跟第二单相差的天数，第二单跟第三单相差的天数，求和然后除以总单数。
   >     每个月订单超过30单的是高频用户，找到每个月高频用户的百分比。
   >
   > 前两次的面试体验蛮好的，都是国人。尤其是第二轮的小哥还说你是ng 我理解可能没什么经验 还讲了团队成长之类的。
   > 第二轮的sql面感觉是个abc，就是着急finish的感觉。后边想要跟他交流一些条件的定义 他直接说 你不用写了就是把大概的思路给我说一下就好。我说完了 他就说 oh that makes sense 就完了 也是全程无交流。 面试挂了很难受，求加米 求安慰。

5. 2024(1-3月) DataEng 硕士 全职@doordash - 内推 - Onsite  | 😐 Neutral 😣 Hard | Fail | 在职跳槽

   > 数据工程师onsite 面筋
   > 1. case study - 送餐eta 这个metric怎么衡量，我说就是搞个别的metrics比如真实到达时间和eta的差距。然后问如果你这个metric最近不断下降怎么办。
   > 2. system design - 设计一个customer portal 可以看cloud service的billing information。 比如s3 是怎么产生各种费用然后如何report给user。 需要从源头开始设计，我只会设计etl pipeline，源头数据怎么产生的完全乱说了。希望有大佬讲讲。。。
   >
   > 3. lp， 没什么好说的，印度哥全程哈欠
   > 4. data modeling。 一个app用来看健身视频的，里面有什么class， video， customer， trainner， 这个比较简单，最后给了一个图表问怎么写个query支持这个图标，但是不用跑的，大概对了就行
   >
   > 求米！！！
   >
   > 请问是 data engineer 这个title吗？ 还是software engineer in data platform? 谢谢
   > 他叫software engineer - data engineer, 实际上就是data engineer，和data platform无关

6. 2022(4-6月) DataEng 硕士 全职@doordash - 内推 - Onsite  | 😃 Positive 😐 Average | Pass | 在职跳槽

   > 两周前面的VO，一共4轮：
   > 以下内容需要积分高于 100 您已经可以浏览
   >
   > 1. Data Modeling case study: 围绕一个app。有很多小问，app是一个健身软件，用户付费之后可以看里面的视频。问设计metrics条件判断怎样才算是daily active users。然后data modeling entity design。最后sql统计各个 category的一周的 daliy active users统计，大概是这样
   > 2. ETL Design。设计一个pipeline汇总各个AWS service的billing，好制作report
   > 3. Hiring Manager: BQ
   > 4. Data scientist team的人来面，基本上也是bq。
   >
   > 不到一个星期就出结果了，说是过了，然后第二天约了一个组的manager team match。结果没match上。然后recruiter说我这个级别(E3, E4)只有那个组有空位，所以就没有offer了。
   > 祝好运，求加米！

7. 2022(4-6月) DataEng 硕士 全职@doordash - 猎头 - 技术电面  | 😐 Neutral 😐 Average | WaitList | 在职跳槽

   > L C 要而丝，有两个extension。1个是终结点只能是打了星号的node，而且path只能有首位两个打星号的。2是把path也返回出来。

8. 2021(10-12月) DataEng 硕士 全职@doordash - 猎头 - Onsite 在线笔试  | 😐 Neutral 😐 Average | WaitList | 在职跳槽

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

9. 2019(10-12月) DataEng 硕士 全职@doordash - 网上海投 - 技术电面  | | Other | 在职跳槽

   > 貌似是个abc，讲话好快，聊天时信号也一般，基本听词猜意。。题不难。
   > 以下内容需要积分高于 200 您已经可以浏览
   >
   > 1. 买卖股票只交易一次
   > 2. 类似三舅舅，给出a=b, b=c, c=d, 判断a==d?, 返回True;a==z?, 返回False