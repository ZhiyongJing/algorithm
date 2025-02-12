1. 然后设计一个挂单竞价系统
2. HR: Why Bloomberg. Most challenging problem.EM: Most challenging problem. 简历。简历上的project是如何design的，目前的design有没有问题，为什么不用另外一种design
3. 给一系列股票操作，比如[[9.1, BUY, 100 (shares), $50], [9.3, SELL, 50 (shares), $80]，但是30天内sell不能超过buy price（不能赚钱）问怎么判断是否valid；先假设只有buy，followup是如果有的有sell怎么判断
4. 给一些fail的节点，每个节点知道自己的children，找到最开始fail的那个节点 类似 耳零零，但是找number of lakes
5. how to handle storage and processing of thousands of realtime stock prices (market data) and design alert notification for stock prices. Pay attention to high volume handling and low latency
6. Team hiring. 一轮coding, 一轮System Design， 一轮domain knowledge. 最后一轮HM.System Design 设计一个job scheduler。面试官重点问了很多monitoring 和Capacity planning的东西，比如应该monitor哪些metrics, 用哪些metrics来做autoscaling，还有怎么做capacity planning。这一轮答的一般般吧。Domain knowledge问了一些distributed system相关的问题, 因为这个组是infrastructure 相关。感觉team hiring很看重experience match不match。这轮还可以，因为我这方面比较熟
7. 系统设计：设计一个检查 是否能够交易的系统， 假设你有10w个银行 彼此之间会有交易的限额。银行在进行交易的时候，会发request 给这个交易系统， 如果还有额度可以交易。系统设计还可以。Design 可以归类为一个event counter吗
   不是的 两个银行之间的交易有具体的数额 你要保证不超过 限额算法一做的非常不好，看似很简单，看到地里的同学很多都挂在这道题上了。我面的面试官一直在challenge你。
8. em面试官是一位在bbg干了20年的印度大叔, 人非常sweet, 也很聪明的感觉. 他考了我一道开放式的设计题: 有三个数据源, 每天产生很多股票交易, 设计一个系统，从每个数据源返回特定股票的最新股价。每支股票都有一个ID和一个价格，数据规模庞大，确保可扩展性和准确性。我的回答(用chatgpt概括了一下lol):创建包含5列的表，包括“主键”、“股票ID”、“股票价格”、“时间戳”和“数据来源”。建立joint index在股票ID和数据来源上以提高查询效率。使用缓存存储常更新的股票数据，包括股票价格、时间戳和评估分数。更新缓存中的数据并计算评估分数，考虑最新时间戳和频率。当缓存接近满时，淘汰评分最低的数据并存储到数据库。定期备份数据库以保障数据，如果缓存丢失可回滚数据库。根据流量负载的情况，动态管理缓存大小。当需要时，将读写操作转向数据库，确保数据准确‍‍‌‌‌‍‌‍‌‌‍‍‌‌‍‍‍‍‌‌性。为了满足未来高流量需求，准备多个备用数据库。
9. 一道沙漠加油站，其他面经里也提到过。问了why bb，how to collaborate，hardest project。
10. 设计一个系统，从100个交易所接受实时的交易数据，储存+处理之后，输送给不同的应用场景，比如其它的计算服务引擎，或者显示终端

    考点在典型的multi-producer / multi-consumer messaging system，capacity，caching，partitionning等等
    需要back of envelop计算，画图（用的hack‍‍‌‌‌‍‌‍‌‌‍‍‌‌‍‍‍‍‌‌errank），讨论tradeoff
    两个面试官都是纽约的，都很nice
11. 第二轮系统设计。先讨论过去的project，侃侃而谈了20分钟。然后设计聊天系统，也是高频面试题，准备的比较充分。一个小时很快过去。
12. 15min各自介绍，15min浅挖project，问了遇到什么困难，怎么解决。然后上算法题。这轮没写出来，说真的，我到最后也没理解到底怎么判断。题目大概是这样的，说一堆学生做project，大家都有想组队的小伙伴，但老师给随机分配了。最后求，有多少学生不开心。但这个不开心的定义我没看太太懂。好像说，A如果被分给D，如果A在D想组队小伙伴的优先列表里排序比其他的小伙伴高，A就开心。如果C排得比A前，那么A就不开心。（这个记不清楚，基本没看懂）两个都挺nice的，尤其是国人大哥，几次试图拯救，但真的没懂题目。这题感觉考阅读理解呀艹
13. 第三個 hr interview (30分鐘)

   一些基本的 bq，可以去搜搜其他帖子，可以找到其他人整理的。然後 hr 語速不是普通的快。

   她自我介紹，沒有要我自我介紹，直接開始 bq。問題我就直接用英文描述
   How did the previous interview go?
   What did you learn about Bloomberg from the previous interview?
   Most successful project & follow up about the project
   Why bloomberg
   Tell me about a time you deal with conflict.
   Since I graduated in May '24, she asked me what I did during the gap.
   Some visa sponsorship questions.
   最後問他問題

14. vo2 system design 设计一个系统， 可以实时获取股票信息并且反馈到客户那里去
15. HR：一些常规bq（只记得一个实习中的positive/negative feedback）+ why bb + 选择公司的三个要素， 结束约EM
16. how was your previous interview
17. introduce your self
18. BQ:
    Biggest challenge

    What are you looking for
19. how your groupmate / teammate describe you
    experience:
20. most challenging proj
21. another most challenging proj
22. what you can improve for these projects
    company:
23. why bloomberg
24. what factors would you consider when choosing the company
    反向bq
25. 。第二题是OOD，设计一个刷卡上下班的系统，满简单的
26. 设计一个飞机雷达？要求如下：可以显示所有的飞机；highligh那些靠的很近，有可能会撞上的飞机；可以track飞机的进入和离开airspace
    偏OOD
    别问我怎么答的，我答得稀烂= =，我被告知这一轮是SD，然后面试官说OOD,SD都来点。。。。我就被打乱了节奏，展现了我的魔鬼步伐。。。
27. 正常bq，why bb，why cs，challenge project，这些都很‍‍‌‌‌‍‌‍‌‌‍‍‌‌‍‍‍‍‌‌正常，vo也会涉及
28. `crontab` 是 Linux/Unix 系统下用来设置周期性任务（定时任务）的一个工具/配置文件，其名称是 “cron table” 的简写。它与系统中的 `cron` 服务配合使用，可以让操作系统在指定的时间或周期自动执行某些脚本或命令。
29. ood 类似于小蓝车，在起点和终点刷卡计时，计算平均用时。题目理解了比较久，只写了几个cla‍‍‌‌‌‍‌‍‌‌‍‍‌‌‍‍‍‍‌‌ss就到时间了。
30. What is react?
    Why react is popular?

   What is virtual dom?
   What is jsx?

31. 三轮 SD： 应该是tech leader，国人大哥
    乍看一眼我还以为是第一轮的大哥，发际线就能看出tech能力很强。
    SD我花了挺多时间准备的，感觉还是面的不咋地。

   题目大哥手敲出来的，
   说不同的fund manage公司管理不同的ETF（portfolio）
   每个ETF会有不同 company的股票和share
   eg
   fidelity：
   ticket  name  stock share
   fih    hp100.   IBM   1000
   fih    hp100.    apple. 2000
   fiw.  wp200.   google 500

   blackstone:
   ticket  name  stock share
   blh  hp500.   IBM   1000
   blh  hp500.    apple. 2000
   blw  wp200.   google 500

   输入类似上面这种csv格式
   输出有是一个 API 可以输入一个股票的名字 显示所有有这个股票的company， portfolio 还有share
   没遇到过这种类型的SD，看上去挺straight forward，但有点不知道怎么下手
   我姑且按照套路 从database到load balancer到 server慢慢扯，大哥会问题比较具体或者问why
   感觉对这一块还是比较浑浑噩噩‍‍‌‌‌‍‌‍‌‌‍‍‌‌‍‍‍‍‌‌ 有基本的概念，但离开模板就不太会答了

32. em面：楼主运气不好，遇到了system design。简单聊了聊‍‍‌‌‌‍‌‍‌‌‍‍‌‌‍‍‍‍‌‌简历，花了半小时要求设计一个超市购物系统。
33. Why bloomberg?
    What's the most challenging thing for your intern project
    反向 BQ
34. sd：notification system
35. Tech 2
    系统设计
    需求很vague，面试官一上来就问他想要我设计一个系统，去让开花堡员工可以选社群参加。聊了很久，才知道他要的是类似meetup的软件。philanthropy & data license
36. 让我把当前的project 给他画了个图 然后问题几个system design 相关的问题 比如 如果data 突然增加了10倍怎么办 我说的是要对每个component scale 然后load balancing 啥的 总之就是那些基础知识。 基本上算是给他讲明白了 然后让我反向bq， 一共持续的有差不多45 分钟吧 我说暂时没有问题了。 然后他说回去 collect 下feedback 再给我消息。
37. 中间穿插了常规bq，比如team有conflict怎么办。中间讲项目的时候她问如果数据库需要放特别多data怎么办，我一下没想出来扯了一个in‍‍‌‌‌‍‌‍‌‌‍‍‌‌‍‍‍‍‌‌ memory datastore的方法，结果立马就被她否定了，因为内存会爆炸，然后我又扯了个别的，她说makes sense。但很明显她一点也不满意，也觉得我答不出来别的了。
38. 第二轮系统设计
    设计一个文件处理系统，
    TU100M，W traffic 50 QPS， R traffic 250 QPS
39. 第二轮，设计停车场，没准备过OOD，完全不知道怎么答，这轮跪了。。建议要面BB还是要好好准备下经典的几道OOD题
40. 第二轮design，但是没有要求System design那么详细，就是设计一个股票价格显示系统，不要求支持股票交易。 主要聊了数据库设计和怎么partition replication。
41. 系统设计。设计一个search system, 每天处理来自不同data source的新闻，保存到metadata storage里面，然后有一个search service处理前端的search请求，要考虑related words，比如Space X发射火箭的新闻，通篇没提马斯克，但是用户搜索马斯克，也得返回。问的太杂了，哪儿都得插一脚，让你详细解释一下。我觉得没有5-8年以上工作经验，很难答好。
42. 第一轮 SD: 活动 booking system
    讨论db table, CRUD 流程
    有点像design calendar
43. 1) why BB, how does ur background fit BB。 2) what do you think about going from academia t‍‍‌‌‌‍‌‍‌‌‍‍‌‌‍‍‍‍‌‌o industry
44. 1-2，vo，蠡口伊伊遛旧和已而偲偲变形，设计instagram
45. 给一堆莎士比亚的文件, 保存在本地，然后搜索某个单词频率 的行数
46. system design， 让你设置一个类似健康码的系统， 问了db schema还有 bottleneck,还有优化 cache kafka aws之类的问题
47. 第二轮是个三姐，出了地铁题，很快就秒了。然后就开始follow-up corner case，以及如果旅客量很大的情况下该如何解决，如果进站出站间隔时间很久如何解决，进站和出站无法匹配上如何解决，多线程情况下该如何处理，如何加锁，加锁之后对性能的影响等等贼多问题。感觉都答上了，她也没啥不同见解，基本都是赞同，但是全程就冷漠脸。最后面完和我说今天到此结束，我就知道这估计是凉了。
48. 2题 停车场，要求park和retrieve车
49. OOD：设计一个售票系统，如果买的人比票的数量多，需要把多余的人queue起来，前面如果有人withdraw，把票顺位给下一位。大概说了什么数据结构，有几个method。没什么算法，代码干净清楚就行。
50. OOD: 写一个file reader, load by chunk, 用户提供offset和length, 返回相应的file内容
    *
51. hring manager BQ+system design: 设计job scheduler
    *
52. 设计题：TinyURL
53. 设计一个ticket分发系统，感觉没啥好说的。ticket一道就要分出去。就说维持个最小堆，永远把ticket给目前任务最少的那个人
54. 2 系统设计
    设计yelp 订餐桌系统 如何确保没有race condition. 如何给大家看一堆附近可以订的餐厅 cache 餐厅的空座信息 如何cache确保数据的一致性 然后如何scale
    3 扯皮 如何跟组员互动 跟pm互动 解决冲突 blah...
55. 面试官烙印，本人就是data license组里的。问了巨多问题，其中算法就三道剩下的全是Python问题，因为这个组用C++和python而这两个里面我只会用python。。。

    算法1：
    给两个巨大的file， 各100TB。file里面都存的股票交易数据，每行的数据format是“stock1, A, 32.21”. 给一台只有1mb ram的电脑，让找出没有同时存在在两个file里的数据。
    e.g.
    fileA:
    “stock1, A, 32.21”
    “stock3, B, 22.21”
    “stock4, A, 42.21”

    fileB:
    “stock1, A, 32.21”

    “stock2, T, 62.21”
    “stock5, B, 52.21”

    输出result [“stock3, B, 22.21”, “stock2, T, 62.21”, “stock5, B, 52.21”]
56. Friendly天竺男两枚
    (1). 给一个list的trade info, 格式是(名字，交易时间(900=09:00, 1310=13:10)，交易价格，交易数量):
    ("GOOGLE", "900", "130.1", "120")
    ("GOOGLE", "901", "130.1", "100")
    ("APPLE", "901", "798.4", "400")
    ("GOOGLE", "902", "130.1", "90")
    ("GOOGLE", "904", "130.1", "150")
    ("GOOGLE", "1310", "130.1", "300")

让求从一天的开盘到收盘(开盘收盘时间给定)的5分钟window内的某个股票的最大交易数量，比如上个输入的输出就是：
("GOOGLE", "900", "130.1", "120"), ("GOOGLE", "901", "130.1", "100"), ("GOOGLE", "902", "130.1", "90"), ("GOOGLE", "904", "130.1", "150") = 120 + 100 + 90 + 150 = 460

做法sliding window用queue维护5分钟区间内的该股票的trade info，每循环到新的trade加入queue然后从后面剔除交易时间大于五分钟的，meantime更新result sum。

第一题，不用写代码，讲思路，求时间消耗。只有1MB的内存，有两个input files, file A和file B,每个都有100TB这么大，找出两个文件中不同的行，写在一个result文件中
第二题，Input是一个Integer array和一个常数K， 找出所有 a - b = K的(a, b) pair, 时间消耗尽可能少，空间无所谓
第三题，Tree level order traverse, 问了时间和空间消耗

Hacker rank链接，没有寒暄，直接做题，
给一个Interval 数组，代表一些running process的开始和结束时间戳。
比如[[1,5],[2,5],[3,6]]返回所有没有任何job running的interval.

我是用的把时间点按起始时间排序，扫描线找count 为0 的区间并记录。
follow up, 如果是返回最少的区间呢？我就先扫一遍找最小的，然后返回所有match这个最小的区间。

第二题，新人入职培训，公司组选人，每一个新人都有一个自己心中想去的组的排名，每一个公司组都有一个自己心中想选的新人的排名，让设计一个函数，返回最好的Match
在一路提示下，给出基本思路，‍‍‌‌‌‍‌‍‌‌‍‍‌‌‍‍‍‍‌‌最后感觉也不是很清楚，但面试官表示这个难度较大，所以没有深究。最后问题搞定。当天收到过了的消息，希望可以帮助大家。
