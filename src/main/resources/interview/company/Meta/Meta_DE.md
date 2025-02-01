



1. 2024 Meta DE Phone Screen

   > 

2. 2024(7-9月) DataEng 硕士 全职@meta - 内推 - 技术电面  | 😃 Positive 😣 HardFail | 在职跳槽

   > 应该是挂了，超时没做完，分享给大家求加米，谢谢。
   >
   > SQL 题 table是一样的，题目微调，top3改成top5，total sales改total customer，找出人数改成找出百分比。
   >
   > Python 完全变形：
   > 1. 找出一个数里面奇数组成的最小数 多谢楼主,  是说一个数中的奇数digits组成的数吗,  e.g. 1234返回13
   > 2. dictionary里面找出最frequent的
   > 3. 开会那题变形，要求找出连续两年最多class的，具体有点忘了，但是跟开会的很类似。

3. 2023(7-9月) DataEng 硕士 全职@meta - 内推 - 技术电面  | 😐 Neutral 😐 AverageFail | 在职跳槽

   > SQL：
   >
   > Table: `books`
   >
   > | Column               | Type     | Description              |
   > | -------------------- | -------- | ------------------------ |
   > | **book_id**          | INT (PK) | Primary key              |
   > | **title**            | VARCHAR  | Book title               |
   > | **author_id**        | INT (FK) | Foreign key to `authors` |
   > | **publication_date** | DATE     | Date of publication      |
   > | **category**         | VARCHAR  | Book category            |
   > | **price**            | DOUBLE   | Book price               |
   >
   > Table: `authors`
   >
   > | Column          | Type     | Description         |
   > | --------------- | -------- | ------------------- |
   > | **author_id**   | INT (PK) | Primary key         |
   > | **first_name**  | VARCHAR  | Author's first name |
   > | **last_name**   | VARCHAR  | Author's last name  |
   > | **birthday**    | DATE     | Author's birth date |
   > | **website_url** | VARCHAR  | Author's website    |
   >
   > Table: `transactions`
   >
   > | Column               | Type     | Description                        |
   > | -------------------- | -------- | ---------------------------------- |
   > | **transaction_id**   | INT (PK) | Primary key                        |
   > | **book_id**          | INT (FK) | Foreign key to `books`             |
   > | **customer_id**      | INT (FK) | Foreign key to `customers`         |
   > | **payment_amount**   | DOUBLE   | Total payment amount               |
   > | **book_count**       | INT      | Number of books in the transaction |
   > | **tax_rate**         | DOUBLE   | Applicable tax rate                |
   > | **discount_rate**    | DOUBLE   | Discount rate applied              |
   > | **transaction_date** | DATE     | Date of the transaction            |
   > | **payment_type**     | VARCHAR  | Payment method (e.g., credit card) |
   >
   > Table: `customers`
   >
   > | Column                       | Type     | Description                              |
   > | ---------------------------- | -------- | ---------------------------------------- |
   > | **customer_id**              | INT (PK) | Primary key                              |
   > | **first_name**               | VARCHAR  | Customer's first name                    |
   > | **last_name**                | VARCHAR  | Customer's last name                     |
   > | **registration_date**        | DATE     | Date of registration                     |
   > | **interested_in_categories** | VARCHAR  | Categories the customer is interested in |
   > | **is_rewards_member**        | BOOLEAN  | Whether the customer is a rewards member |
   > | **invited_by_customer_id**   | INT (FK) | Referring customer ID (if any)           |
   >
   > different payment type total sales，要top 3 sale value.
   >
   > find the top 5 customers, sales value ordered by the total sales value of the people they directly invited.(类似这样，好像是算一个什么比率，实在记不清了）
   > 求url網址像“%.com%”的author個數，percentage，author没有销售记录的percentage，总author数，差不多是这样
   > hint用case when，不要用where clause。
   > Python：
   > given int，find smallest number from all odd number ，
   > 123-13, -4 - none, -1717 - 1177
   > 給一個dictionary，｛bookname1：［theme 1， theme 2，theme3］，bookname2:［‍‍‍‌‍‍‌‍‍‍‍‌‍‌‌‌‍‍‌‍theme 4， theme2，theme5］。return出現次數最多的theme，也就是theme2。变形题，要求duplicate location 不算，loop要用set 去重！！！
   > 只答对3sql，2python，已经跪了，只能来年再战了，遗憾......
   >
   > 面试题和地里最近的面经差不太多，稍微有一些变形。sql之前求总数，现在求percentage。

4. 2023(1-3月) DataEng 硕士 全职@meta - Other - 其他  | 😃 Positive 😐 AveragePass | 在职跳槽

   > 最近面了meta DE phone screen， 25 min 5 SQL 25 min 5 Coding/python
   >
   > PYTHON：
   > 给一个int 求这个int里digital能产生的最大的 int .Python第一题的意思是说比如给个数字123， 要求返回321
   > 给了一个 dict， values是一个list， list里面是书的theme， 找到做多的theme， 如果有多个， 随便返回一个就行
   > 给了会议室， 每个会议室开始， 结束以及参加的人数， 找到最多的参加会议的人。给的是一个list， list里面是一个meeting的class， class 里面包含 人数， 开始， 结束三个filed
   > 两个list， 一个是书的厚度， 一个是书架的厚度， 每本书只能放一个书架， 判断是否所有的书都可以放到书架上
   >
   > python做出来就行， 方法越简单越好， 第四题我和面试官说用heap 他说你想复杂了，我就想了一个简单的， 其实就是两个都排序（降序）然后 做差看看shelf是不是比book大就行
   >
   > SQL：authors｜ books ｜customers ｜ transactions｜
   >
   > 1. 找某3个payment type下买了多少本书
   > 2. 找author url 类似某个pattern且没有卖出去书的数量 和全部authors 的数量 （两列输出）
   > 3. 有一个customers是被别人invite来的， 找top 3 invite别人的customer（top 指的是交易额）
   > 4. 找customer who purchase from same author with two different genres 的交易额
   >
   >
   > 注意sql 是使用postgresql ， 有一些语法上面的不同自己要提前熟悉下， 不然run会报错
   >
   > 新人求大米。 谢谢！
   >
   > 请问sql 第四题指的是有两个genre的author还是customer从同一个author 买了两个genre呀 谢谢 已加米
   >
   > customer 买了同一个作者 至少两个genre的 消费总量

5. 2024(1-3月) DataEng 硕士 全职@meta - 内推 - 在线笔试  | 😐 Neutral 😐 AverageOther | 在职跳槽

   > 有人最近要面Meta的DE Analysis Product吗 想问问是不是都是最近的面筋题
   >
   > shide

6. 2023(10-12月) DataEng 硕士 全职@meta - 内推 - HR筛选  | 😃 Positive 😐 AveragePass | 在职跳槽

   > I just finished the technica screening and learnt that I cleared it.
   >
   > Some of the quesions asked.
   > 1. get the max of the given number
   > 1. 2. Frequencies of dict values
   >
   > SQL:
   > A schema was given and there were couple of questions based of it.
   > 1. Get the sum and distinct count of the particular payment type.
   > 2. Patters like select statements inside of the case statements.
   > 3. Self Joins with the table to find if customer purchased the previous month and current month as well.

7. 2023(10-12月) DataEng 硕士 全职@meta - 网上海投 - 技术电面  | 😐 Neutral 😐 AverageFail | 在职跳槽

   > SQL有一題是一個author table，一個book table 還有一個transaction table。把這三個table join起來，求有url網址但沒有銷售紀錄的author個數，以及總author個數。
   > python有一題是給一個string比如‘1001’，return一個數字1100。或者’12789‘return98721（這個數是我編的，但意思是return最大數）。
   >
   > 還有一題是給一個dictionary，｛bookname1：［theme 1， theme 2，theme3］，bookname2:［theme 4， theme2，theme5］。return出現次數最多的theme，也就是theme2。
   > 祝大家好運  
   >
   > 还是老套路： 5 Python + 5 SQL，可以选择先做Python还是SQL，每个部分25分钟。
   >
   > Python：
   > 1. 给一个只包括digital的string，比如“10001”，返回这个string可能对应的最大的数字，11000。
   > 2. 给定一个dictionary，key是书名，value是对应的themes，比如 ｛book1：［theme 1， theme‍‍‌‍‍‌‍‌‍‌‌‌‌‍‌‍‍‍‌ 2，theme3］，book2:［theme 3， theme4]}，返回出现次数最多的theme: theme3 书架问题是一层可以放多个书
   > 3. 给定一个list of Meetings， e.g. [Meeting(audience=10, starttime=1, endtime=4), Meeting[audience=30, starttime=3, endtime=6), ...],  求max number of audiences who are in meetings at the same time.
   > 4. 给定一个list shelves; [2, 4, 3, 6], value 表示对应层数的shelf 的width，然后给定一个list of books: [3, 1，2], value对应的是书的厚度， 求是否可以把所有的书都放到shelves上， True or False BFS
   > 5. 给定一个dictionary: {1: [2,3, 4], 2: [4, 5]....}, 每一对key-value pair表示某个人给其他人发了friend invite, 比如 user 1 给 user 2，3， 4发了invite，问题： find the users who are N edges away from the customers who didn't recieve any invite. BFS
   >
   >
   > SQL：
   >
   > 给了四个table： author，customer，book，transaction，围绕这四个table写query，时间久了，问题的顺序有可能不完全对
   >
   > 1. Find customers who purchased from the same author with at least two categories and the total sales of these books
   > 2. Rank customers by the sales by who they invited. (这个问题不好理解）
   > 3. For each customer, find the total number of books and total number of unique books they purchased
   > 4. Find authors who have a url starting with "https//" but have no sales over the total number of authors
   > 5. Find top two months with unique customers who made purchases this month and the previous month
   >    第五题没有说是不是一年的，有一些date 相关的function可以用，但是面试的人不关心这些语法之类的，只要能说出大概思路就好
   >
   > 请问python第三题list里面是什么数据结构呀？是一个个tuple吗？(audience,start time,end time) 这样吗？谢 ...
   >
   > It is a class like this. Each element of the list is of type Meeting class
   >
   > ````py
   > class Meeting():
   >     def __init__(self, title, audience, start, end):
   >         self.title = title
   >         self.audience = audience
   >         self.start = start
   >         self.end = end
   >    
   >     def __str__(self):
   >         return "Title: " + self.title + "\t\tAudience: " + str(self.audience) + "\tStart: " + str(self.start) + "\tEnd: " + str(self.end)
   > ````

8. 2023(4-6月) DataEng 硕士 全职@meta - Other - 视频面试  | 😐 Neutral 😐 AverageFail | 在职跳槽

   > 2月份店面过了以后，接到通知说职位取消。7月份的新HR突然联系，说现在由她负责，虽然没有职位，但是可以开始面试流程。
   > 两个半天的时间，DS+Algorithm，SQL。 难度中等，可惜挂了。
   >
   > 教训： 第一轮的时候没有加extra screen，导致几分钟没看到题目.
   >
   > 只记得一道题了: Have a session table with session_id, step, and timestamp
   > Session_id, step, timestamp
   > [1001, 1, 100]
   > [1002, 1, 200]
   > [1001, 2, 150]
   >
   > 要求: Using SQL and then Python to have average  consumed time per step.

9. 2023(4-6月) DataEng 硕士 全职@meta - Other - 技术电面  | 😐 Neutral 😐 AveragePass | 其他

   > Question: find users in the relationship table who have above average friends.
   >
   > Relationship table:
   > schema: Userid1, Userid2, created_at
   >
   > -- insert into "relationships" values( 1, 2, '2023-01-12')
   > -- insert into "relationships" values( 2, 3, '2023-01-11');
   > -- insert into "relationships" values( 1, 4, '2023-01-13');
   >
   > -- insert into "relationships" values( 1, 5, '2023-01-14');
   > -- insert into "relationships" values( 3, 5, '2023-01-14');
   >
   > 

10. 2023(10-12月) DataEng 硕士 全职@meta - 内推 - 技术电面  | 😃 Positive 🙂 EasyOther | 在职跳槽

   > 最新四月技术店面 题没变 流程是：1. 对方先介绍自己
   > 2. 让我简单介绍一下背景
   > 3. 开始答题 让我选先sql还是先python
   > 4. 最后留一两分钟回答我的任何问题
   >
   > 论坛上已经很全面了 就不重复一样的内容了
   > 四个table: author, book, sales, customer
   > 前一两题很简单， 第三题大概是 求全部购买书超过一本， 第一天和最后一天买书总和超过3本的顾客, 占总交易比例
   > 这题注意
   >
   > 之前帖子说不能用window function， 但是我问了recruiter是可以用的 所以我直接用两个window function找第一和最后一天， 算买书总合， 比写subquery求max date再join方便很多
   > 第四题 求没有买自己interest genre的顾客比例。补充细节是如果顾客有[1，2，3] 三个genre, 只买了[1,2]两个genre的书， 也算没有买自己interest的genre， 算作此题分母
   >
   > Python
   > 第一题 求avg of a list 补充细节是 如果list为空 则return 0 这个是test case给出的
   > 第二题 给一个budget 一个price list求能买几本书
   > 第三题  给一个list 里面有很多书名 和他们的不同的edition， 给出所有非原版的书。 注意这里不止一类书， 所以可以是 [a,a 2nd, b, b 2nd]
   > 第四题 一个list of tuples, (价格， 销量), 给一个销量排名N， 让你return 这个tuple. 这题注意如果并列的话 给出价格低的，
   >
   > 但是另一个细节别的po没提的是，如果比如有两个并列第三， 那如果N是4的话 return None，因为第四名轮空。这题官方答案用的是set
   >
   > 就是最後一個 要return tuple， 我當時沒看清面經，只return了price
   >
   > recruiter不到六小時就聯係過了。

11. 2023(1-3月) DataEng 硕士 全职@meta - 网上海投 - 技术电面 视频面试  | 😃 Positive 😐 AveragePass | 在职跳槽

    > 5题sql 5题python
    >
    > sql是author题，和这儿一样
    >
    > 第一次发帖献给fb。。。今天下午面了fb的data engineer岗位，题目跟https://www.1point3acres.com/bbs ... b%2Bdata%2Bengineer 这里的是一样的，但是因为我还有fresh memory所以我补充了一些
    >
    > Books, sales, customer, author
    >
    > 1. Author who wrote more than 5 books, order by # of books written from smallest to largest (顺序是group by， having， order by。。。我这个syntax没记清楚，最后写了个cte。。。)
    > 2. 算registration和sales在同一天的ratio
    > 3. 第三题是算customer的first purchase day和last purchase day一共买了多少书（要求customer不止一天消费了，而且第一天和最后一天加起来买了超过3本书
    > 4. 第四题让算没有买自己感兴趣的genre的书的customer的%. 每个customer有genre_interests是一个char[]，每个book有genre是char，所以需要cross unnest() join
    >
    > Python
    > 1. Avg of numbers in a list. Edge cases: len(list) == 0 or list == None
    > 2. Given budget and list of book prices, return the maximum numbers of books that you can buy
    > 3. 给了一个list叫Books，让找里面是edition的book, eg. ['Python', 'Python 2nd edition’,’Python in a nutshell’], 'Python 2nd edition' 和 ‘Python in a nutshell’ 就是要找的因为第一本书Python是他的prefix.
    >     这一题我在看地里面经的时候以为以edition结尾也是一个特征，结果发现‘Python in a nutshell’ 也算edition。。最后写的比较复杂但是写出来了。。面试官还说了句he is surprised that this works.....
    >
    > 说说python题目
    > 1. list mean
    > 2. max books to buy with budget
    > 3. find prefix,一个list里面会有大雨一个的prefix，e.g. [ 'python', 'python v1', 'python v2', 'green' , 'green v1'] ，output是[python v1, python v2, green v1]
    > 4. a list of tuple (prices, sales), return nth max purchased book, If there is a tie, return the lowest book price. 用dictionary可以做
    > 5. friendship shares，friendship的matrix, e.g. [[0,0,1], [0,0,1], [1,1,0]], matrix[0]=[0,0,1]说明user0和user2是friend. 给depth: D, user: N, 假设他发了一条post, 所有的friend都去reshare且每个friend只能 reshare一次，总共share的多少次，最多到D的depth., 就是说user0发了一条，他的friend去转发，这是depth 0, 然后他friend的friend 接着转发， 这是depth 1，以此类推。用bfs
    >
    > 真的太贫穷了，卑微恳求加米，如果感兴趣哪一题，可以分享自己的解法

12. 2023(10-12月) DataEng 硕士 全职@meta - 网上海投 - 技术电面  | 😐 Neutral 😐 AverageFail | 在职跳槽

    > SQL有一題是一個author table，一個book table 還有一個transaction table。把這三個table join起來，求有url網址但沒有銷售紀錄的author個數，以及總author個數。
    > python有一題是給一個string比如‘1001’，return一個數字1100。或者’12789‘return98721（這個數是我編的，但意思是return最大數）。
    >
    > 還有一題是給一個dictionary，｛bookname1：［theme 1， theme 2，theme3］，bookname2:［theme 4， theme2，theme5］。return出現次數最多的theme，也就是theme2。
    > 祝大家好運 

13. 2022(4-6月) DataEng 硕士 全职@facebook - 猎头 - 技术电面  | 😃 Positive 🙂 EasyPass | 在职跳槽

    > FB DE 面试 伦敦
    > 跟地里的题差不多但是有一点变化，建议不要背题，每个题都当作新题去思考
    > 3个SQL， SQL很简单就是一些基本的join case sum order 求percentage
    >
    > 3个Python
    > 第一题非常简单 在字符串里找字符出现次数
    > 第二题 两个list 包含一些书名，两个书名如果开头相同 则认为是一个系列的，找出一个系列的
    > 第三题 有点复杂 用户a 转发一个post 所有用户a follow的用户可以看到，以此类推，给出一个n*n矩阵代表用户的关系，求一个post最多能被多少人看到，比如a只有b一个fo， b只有a一个fo，c只有d一个fo 那么a转发后只有b看到。

14. 2022(4-6月) DataEng 硕士 全职@facebook - 网上海投 - 技术电面  | 😃 Positive 😐 AveragePass | 在职跳槽

    >  最新四月技术店面 题没变 流程是：1. 对方先介绍自己
    > 2. 让我简单介绍一下背景
    > 3. 开始答题 让我选先sql还是先python
    > 4. 最后留一两分钟回答我的任何问题
    >
    > SQL：
    >
    > 论坛上已经很全面了 就不重复一样的内容了
    > 四个table: author, book, sales, customer
    > 前一两题很简单， 第三题大概是 求全部购买书超过一本， 第一天和最后一天买书总和超过3本的顾客, 占总交易比例
    > 这题注意
    >
    > 之前帖子说不能用window function， 但是我问了recruiter是可以用的 所以我直接用两个window function找第一和最后一天， 算买书总合， 比写subquery求max date再join方便很多
    > 第四题 求没有买自己interest genre的顾客比例。补充细节是如果顾客有[1，2，3] 三个genre, 只买了[1,2]两个genre的书， 也算没有买自己interest的genre， 算作此题分母
    >
    > Python
    > 第一题 求avg of a list 补充细节是 如果list为空 则return 0 这个是test case给出的
    > 第二题 给一个budget 一个price list求能买几本书
    > 第三题  给一个list 里面有很多书名 和他们的不同的edition， 给出所有非原版的书。 注意这里不止一类书， 所以可以是 [a,a 2nd, b, b 2nd]
    > 第四题 一个list of tuples, (价格， 销量), 给一个销量排名N， 让你return 这个tuple. 这题注意如果并列的话 给出价格低的
    >
    > 但是另一个细节别的po没提的是，如果比如有两个并列第三， 那如果N是4的话 return None，因为第四名轮空。这题官方答案用的是set

15. 2022(4-6月) DataEng 硕士 全职@facebook - 网上海投 - 技术电面  | 😃 Positive 😐 AveragePass | 在职跳槽

    > 面试总共分两部分 25分钟sql 还有25分钟coding， coding的部分可以自己选择语言， 我选择的是python。每个部分分别是5到题， 难度适中， 但是个人感觉时间很紧，稍微在一道题上耽搁了后面的时间可能就不够了， 在整个过程中面试官会给一些小提示之类的， 这个帮助很大。
    > q1.
    > sales table， 里面有作者，书名， 销售时间 。。
    > 然后要求找出有至少5 个著作的作者。
    >
    > q2
    > sales table， 交易id，顾客id， 交易时间
    > customer table， 顾客 id， 顾客姓名，注册时间
    > 求在顾客注册当天的交易占总交易的比例
    >
    > q3
    > sales table， 交易id，顾客id， 交易时间
    > customer table， 顾客 id， 顾客姓名，注册时间
    > 找出至少在两个不同日期内购物， 并且第一天和最后一天的加起来买了三次以上东西的顾客。
    >
    > q4.
    > 忘记了
    >
    > q5。
    > sales table， 交易id，顾客id， 交易时间，图书种类
    > customer table， 顾客 id， 顾客姓名，注册时间，喜好总类（和sales table里的图书种类对应，但是是一个concatenate的string 比如说 喜欢 a,b,c 三种，这个column的值就是‘a,b,c’）
    > 找出没有购买任何自己喜欢的种类的书籍的顾客
    >
    > coding题， 总共五题， 我就记得起4 题
    > q1.
    > 找出 一个书籍list里面的version 2 版本, 新版本的名字就是旧版本名字后面加上一些其他的词
    > 比如说 ['python','python new','python 3'，'java', 'new java'],  'python new'和'python 3'就算是python的新版本,  但是‘new java’ 不是 ‘java’ 的新版本， 因为string并不是以'java‘ 开头
    >
    > q2
    > 求一个list的平均数， 就是要注意handle edge case.
    >
    >
    > q3
    > 给一个list, 里面是价格和销售量的tupl, [(3.99,4),(4.99,1),(2.99, 1)] ,  找出销售量排名第n的产品的价格， 如果并列第n， 就return更便宜的那个
    >
    > q4
    > 给一个matrix代表好友关系， 求一个任何一个人发一个post， 在转发n次之后， 最多能有多少人看见
    > [0,0,1]
    > [0,0,0]
    > [1,0,0]

16. 2022(4-6月) DataEng 硕士 全职@facebook - 内推 - 技术电面  | 😐 Neutral 😐 AveragePass | 在职跳槽

    > 面完了3天以后才给我说过了, 之前看地里的经验都是当天出结果, 我本来以为我凉了....
    >
    > 以下是总结:
    >
    > sql:
    > table: books, authors, sales,customers
    > 1. 找出写了超过3本书的author_id,按count 排序
    >     只需要books table就可以, 不需要join, 注意别忘了最后的order by
    >
    > 2. 客户注册当天就有消费的百分比
    >     需要books和customers join, sum case when可以解决. 注意把数据类型换成浮点数, 最后别忘了乘以100
    >
    > 3. 客户第一天和最后一天一共买了多少书, 要求客户消费多于1天, 并且一共买了超过3本书.
    >     思路: 先把每个人消费的第一天和最后一天找出来放到cte里, 然后join回sales找要求的数据.
    >
    > 4. 没有买过自己感兴趣书的人的百分比. (需要unnest())
    > 在customers里有一个genre_interests, 是char[],所以需要先"打平"这一列
    > 思路: 先通过books, customers, sales这三个表join起来找出所有买过自己感兴趣书的customer, 再除以customer总数, 再用1减去这个数.
    >
    >
    > python:
    > 1. 计算列表平均数
    > 很简单, 注意空列表, None这些edge case. 并且列表中有str的数字.
    >
    > 2. 给一个list书的价格和budget, 算最多能买多少书
    > 排序+跑一遍
    >
    > 3. 给一个list, 里面都是书名, 找出所有书的衍生书.['python','python 2nd edition', 'greed tree', 'greed tree 2nd edition'] -> ['python 2nd edition', 'greed tree 2nd edition']
    > 思路: 排序 + 跑一遍. 在跑的时候实时记录一下parent的书名, 如果当前书名是parent的prefix, 就是我们要的. 如果不是prefix, 就更新parent
    >
    > 4. 给一个列表和n, 列表里面每一项是tuple, tuple[0]是书的价格, tuple[1]是销量. 找出销量第n个的书. 如果销量重复就返回最便宜的书.
    > 思路: 自定义排序, 然后放到字典里 {rank:[books]}.  返回dict[n][0].
    > 本来是想排序完然后返回list[n], 但是销量有重复.
    >
    > 如果有一起在准备VO的小伙伴欢迎联系我啊, 大家一起组队练习啊~





