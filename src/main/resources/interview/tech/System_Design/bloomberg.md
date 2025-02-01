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
10.
