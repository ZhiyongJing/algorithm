1. Bloomberg Onsite Coding

   > 2个小时的share screen pair coding 。
   > 首先给了一个链接下载一堆file, 然后在自己的ide 里implement 一个cmd line tool， 在file path 中search word，要求print out match 的file name， matched count， 以及match 的line。
   > followup ‍‍‌‌‌‍‌‍‌‌‍‍‌‌‍‍‍‍‌‌是如何提升search performance，单机器的情况下，加cache
   >
   > 根据file path, recursively load file, convert 成token 进行比较。再update cnt 和line 就好了。 4月初直接官网海投的。有用就加点分哦！

   

2. 彭博 K8S 全套 4*2 8位面试官轮番拷打

   > 店面
   > coins 无线数量 【1,5,10,25】
   > 给定一个target
   > 按照给定格式
   > 返回最小硬币数量的所有组合
   >
   > VO
   > 1. coding
   >   散酒
   >   要求优化 好像意思是memorization 没搞出来
   > 2. Linux/networking knowledge and system troubleshooting
   > 3. k8s/docker/CS knowledge
   > 4. BQ
   >
   > 彭博很有特点，每一轮都是两位面试官
   > 感觉挺不错的fault-tolerance， more reliable
   > 避免因为面试官的一些有意无意的失误
   >
   > 单方面导致对面试者的不良影响
   >
   > 单这一点我就对彭博非常满意
   >
   > ------------------------------
   > 比如第三轮一个面试官要我列举一些network metric
   > 我就说了qps/throughput/latency/response code, etc
   > 但他还是不满意，一直追问还有呢，是不是漏了什么
   > 我想了老久，最后他说难道你就没有想到考虑到时间吗 latency怎么漏了
   > 我说我明明提到了呀
   >
   > 这时候另一位面试官声援了一下，他确实说了
   >
   > 当然两位面试官也是有一些副作用的
   > 比如coding的时候要求优化 我没有思路
   > 两个人你一言我一语地 方向好像又不一样 超出了我的消化吸收能力
   > 让我更没有思路了
   > --------‍‍‌‌‌‍‌‍‌‌‍‍‌‌‍‍‍‍‌‌-------------------
   > 最后bq面了半小时一位面试官突然不辞而别，另一位解释说他有点急事，稍稍有一点影响面试体验

3. 蓬勃店面 - senior

   > 刷题网 第二高频题 Linkedlist 那个。
   >
   > 刷过的话，挺简单的！
   > 但是会问时间和space

4. 开花堡 oa一轮

   > hr大概聊过之后约时间 available的都至少一个星期后
   >
   > oa的话 是zoom开着 hackrack是个canvas的形式 题是lc 亿三舅刘 本身不算难 做完问了一些拓展的问题 比如如果有人只刷进站不刷出站怎么办 反着怎么办 不知为何两天后拒了 原因没有
   > ‍‍‌‌‌‍‌‍‌‌‍‍‌‌‍‍‍‍‌‌我觉得最亏的是很在意细节 但其实根本不真的run 所以思路吹牛更重要

5. 2024(1-3月) 码农类General 硕士 全职@bloomberg - 猎头 - 技术电面  | 😐 Neutral 😣 HardWaitList | 在职跳槽

   > 输入 stream of data, 有id, value 和 时间，
   >
   > id       value  time
   >
   > 5149，agru,  4
   >
   > 4349,   sret,   12
   > 5663，t46u,  7
   > 1549，a4y5, 13
   >
   > 依次输出 id   ‍‍‌‌‌‍‌‍‌‌‍‍‌‌‍‍‍‍‌‌    value  time其实就是留舞流 和 药散就榴 的合体
   > 但是是分成两个部分问的，千万不要被第一个迷惑了
   >
   > 就是地铁系统那道题
   > 第一问很简单，第二问算是follow up， 需要按时间顺序
   > 所以第一问给的解法基本得推翻重来
   >
   > 求米，求米

6. 2023(1-3月) 码农类General 硕士 全职@bloomberg - 内推 - 其他  | 😃 Positive 😐 AveragePass | 应届毕业生

   > 几周前收到了开花堡的offer 在这里回馈一下地里
   >
   > timeline:
   >
   > 11月底：官网投递
   >
   > 12月初：店面 刷题网芭
   >
   > 12月底: vo1+vo2
   >
   > vo1: 久矣，溢流物流
   > vo2: 斯乌凌，然后出了一道自己的题具体记不大清了 medium-hard 2d dp
   >
   > 1月底：hr (30mins) + em (1hr)
   > 常规bq，实习project deep dive，然后问了一下有哪里可以improve，最后20分钟和我分享了他在bbg 10几年的工作经验 整体聊的蛮开心的
   > 感觉em面很玄学 看之前的面经有考到sys design的所以也准备了一下 但p‍‍‌‌‌‍‌‍‌‌‍‍‌‌‍‍‍‍‌‌roject感觉大概率会问到 所以建议好好准备
   >
   > em面完4天后offer call
   >
   > 楼主目前没有其他的offer 所以大概率接了 希望可以帮助到大家！

7. 2023(10-12月) 工程类 硕士 全职@bloomberg - 网上海投 - 技术电面  | 🙁 Negative 😣 HardFail | 在职跳槽

   > 电面，题是LC 428 ，因为面试官电脑有问题，耽误了些时间，最后时间不够，只要我写deserialize的部分，我用了BFS，代码：
   >
   > public Node deserialize(String str) {         Scanner scanner = new Scanner(str);         String rootValue = scanner.nextLine();         Node root = new Node(rootValue);         List<Node> layer = new ArrayList<>();         layer.add(root);         while (scanner.hasNextLine()) {             String line = scanner.nextLine();             List<Node> next = new ArrayList<>();             String[] split = line.split("\\|", Integer.MAX_VALUE);             for(int i = 0; i < split.length-1; i++) {                 Node node = layer.get(i);                 String[] splitNode = split[i].split(" ");                 for(String s : splitNode) {                     if(!s.isEmpty()) {                         Node n = new Node(s);                         node.children.add(n);                         next.add(n);                     }                 }             }             layer = next;         }         return root;     }
   >
   > 以上代码是后来调试过后，确定可以运行的版本。
   >
   > 实际现场写的略有不同，因为现场不让运行，所以写出来的程序可能有几个小地方需要修改才能正确运行，但现场的代码跟这个比没有大改动 （只改了两个小地方）。
   >
   > 我觉得作为一个不让运行调试的‍‍‌‌‌‍‌‍‌‌‍‍‌‌‍‍‍‍‌‌伪代码已经非常接近可以直接运行出正确结果的代码了，最后还是挂了。
   >
   > 难道还有follow up没有触发？或者当前形势下，面试的bar提高了？move on了

8. 2024(1-3月) 码农类General 本科 全职@bloomberg - Other - 技术电面  | 😐 Neutral 😐 AveragePass | 在职跳槽

   > BB面试挺速度的，跟recruiter call完了以后给我安排了两个组，两个组立马跟我安排了店面。
   > 上周一口气面了两个组
   >
   > 分别是这两题：
   > 1. 利口 two city scheduling 衣领二舅
   > 这题压中题了，轻松过
   > 2. 要你设计一个解决方案 -药饵丝丝 input是股票的名字和交易数量，设计一个方程来储存这个input，会不停地被call到；然后设计另一个方程输出所有股票的名字和总交易数量，按交易量从大到小排序
   > 这题我用一个priority queu‍‍‌‌‌‍‌‍‌‌‍‍‌‌‍‍‍‍‌‌e做的，但是不是最优解，估计没有过
   >
   > 希望能帮到大家一点！
   > 最后求米！
   >
   > follow up：和其中一个组进入下轮full round了
   > 2轮coding每轮2题 2hr
   > 1轮 behavior+Q&A 30 min
   > 1轮 system design 1hr
   >
   > 然后还有3轮
   > hr round
   > manager round
   > team matching round
   >
   > 我天bloomberg面试这么冗长的吗？

9. 2023(10-12月) 码农类General 硕士 全职@bloomberg - 校园招聘会 - 视频面试  | 😃 Positive 😐 AveragePass | 应届毕业生

   > 收到bbg offer好几周了，在此回馈地里。
   >
   >
   > 时间线记不太清了，大体如下：
   > 1. 九月中旬通过学校cf投递了简历（楼主在九月初也用另外的邮箱海投了简历）
   >
   > 2. 九月下旬收到recruiter邮件，说简历正在under consideration。等了几天没消息，楼主便email recruiter询问。于是马上收到第一轮phone invite，时间在10月初。
   >
   > 3. Phone (10月初)： 一个小时phone，一个在bbg工作了很多年的天竺面试官。面试官不苟言笑，让我一度以为凶多吉少。参考地里的经验，大概简单问了自己的兴趣以外就开始做题。第一题应该属于easy-medium，给定起点和终点，找到有向图上的所有路径。后来还有个follow-up，但是当时时间还剩半小时，楼主只是口述了思路，并没有写。后面半个小时是刷题网 药耳陆的变体, 是我见过最长的hard题。幸好楼主半个月前做过，基本做到了bug free做出来了。最后基本没时间了，简单问了一个问题就匆匆结束了。面试完成后楼主并无把我，尤其是第一个题目的follow-up只是说了下。bbg效率很高，第二天上午马上收到预约下一轮，email告知要预留3-4个小时。
   >
   > 4. VO(十月底)：
   >   4.1 (VO1):  两个年轻面试官。也是简单问了下why bbg和challenging project，马上进入coding。第一个题是 伞久撕， 第二题是 沏久。这一轮比较容易，基本做好clarification，确认一些条件， 然后讲思路，跑case就行了。这一轮比较快，提前了5分钟结束大概。然后primary interviewer当场告知一个小时后下一轮。
   >   4.2 (VO2): 一个资深的白人大叔，一个不修边幅的小哥。这一轮的两个题目都不在刷题网上。第一轮小哥的题目是他自创的，只要把题目理解清楚了大概是easy。第二轮大叔的题是gas station那个的变体，也是楼主整个面试不确定的题目，一开始遇到些困难，后来在大叔的提示下换了种方法草草结束。最后大叔也告知楼主半小时后hr面。
   >   4.3 (HR): 此轮只有半小时，都是简单的bq和一些假设性问题，没有遇到需要STAR回答的。面试结束的时候，反问hr啥时候知道结果。hr说会安排final EM面。
   >
   > 5. Final (十一月初)
   >   EM final: 白人大叔，之前在其他金融公司也干过。基本就是各种常见bq，还有围绕实习的问题展开。最后20分钟反问了三个问题结束。
   >
   > 6. 被ghost了大概快一个月，后面忍不住email最初的recruiter问了下，被告知另一个recruiter会联系我offer ca‍‍‌‌‌‍‌‍‌‌‍‍‌‌‍‍‍‍‌‌ll。
   >
   >   虽然拿了offer，但是综合各方面信息看，开花堡的面试和offer很玄学。很多简历很强的朋友被简历挂或者vo挂，也不乏很多人final挂。所以可能运气成分还是最大吧。

10. Bloomberg法兰senior software developer新鲜挂经【08.12.2023】

    > 背景： 博士毕业5年 5年计算机视觉相关工作经验
    >
    > 职位： Senior Software Engineer - Market Oversight
    > 流程： 09.11网上海投 -》16.11 hr面试 -》08.12 视频面试 （面完觉得不挂不科学）
    >
    > 面试经历：
    > 1. 聊项目： 额 只有机器学习相关项目 感觉跟面试官是鸡同鸭讲
    > 2. 写代码： 题目： 设计一个sequecer类 输入id和content， 按照id顺序输出， 假设id不连续就暂时缓存， Example： [1, "aaa"], [2, "bbb"] [4, "ddd"], [3, "ccc"] etc，
    > 输出  aaa
    >          bbb
    >          ccc ddd
    > 代码其实很简单 中间穿插问了unordered_map, map, stack的插入查找复杂度。
    > followup Q： 如果missing id一直不出现该怎么办？（buffer size, timeout, curr_id ‍‍‌‌‌‍‌‍‌‌‍‍‌‌‍‍‍‍‌‌- missing_id > some_threshold， etc）
    >
    > 人生中第一次面developer的职位 一点经验没有，重在参与
    > 面试最后对着小姐姐一顿彩虹屁：你是老夫面试多年遇到的最好的面试官 整个面试流程是最smooth的一次 感谢你非常有用的建议 祝你有个愉快的周末

11. 开花堡 bquant quant dev 一面

    > 刚面了一轮，收到了virtualonsite 的邀请。攒个人品分享一下面试经历，面试官迟到了一会，但是整体面试都比较和谐，很多时间抠简历的细节，最后问了幺林额酒，改了提下场景但基本一样。

12. 2023(10-12月) 码农类General 硕士 全职@bloomberg - 内推 - 技术电面  | 😐 Neutral 😣 HardWaitList | 在职跳槽

    > 新鲜出炉，刷题网幺珥遛。面试体验一般，白人大哥除了开始介绍业务之外就没说什么话，除了回答我的问题，非必要不开口，中途还离开了一会。
    >
    > 



















# 1. 2023-20-01

> 收到bbg offer好几周了，在此回馈地里。


时间线记不太清了，大体如下：
1. 九月中旬通过学校cf投递了简历（楼主在九月初也用另外的邮箱海投了简历）

2. 九月下旬收到recruiter邮件，说简历正在under consideration。等了几天没消息，楼主便email recruiter询问。于是马上收到第一轮phone invite，时间在10月初。

3. Phone (10月初)： 一个小时phone，一个在bbg工作了很多年的天竺面试官。面试官不苟言笑，让我一度以为凶多吉少。参考地里的经验，大概简单问了自己的兴趣以外就开始做题。第一题应该属于easy-medium，给定起点和终点，找到有向图上的所有路径。后来还有个follow-up，但是当时时间还剩半小时，楼主只是口述了思路，并没有写。后面半个小时是刷题网 药耳陆的变体, 是我见过最长的hard题。幸好楼主半个月前做过，基本做到了bug free做出来了。最后基本没时间了，简单问了一个问题就匆匆结束了。面试完成后楼主并无把我，尤其是第一个题目的follow-up只是说了下。bbg效率很高，第二天上午马上收到预约下一轮，email告知要预留3-4个小时。

4. VO(十月底)：
   广告

4.1 (VO1):  两个年轻面试官。也是简单问了下why bbg和challenging project，马上进入coding。第一个题是 伞久撕， 第二题是 沏久。这一轮比较容易，基本做好clarification，确认一些条件， 然后讲思路，跑case就行了。这一轮比较快，提前了5分钟结束大概。然后primary interviewer当场告知一个小时后下一轮。
4.2 (VO2): 一个资深的白人大叔，一个不修边幅的小哥。这一轮的两个题目都不在刷题网上。第一轮小哥的题目是他自创的，只要把题目理解清楚了大概是easy。第二轮大叔的题是gas station那个的变体，也是楼主整个面试不确定的题目，一开始遇到些困难，后来在大叔的提示下换了种方法草草结束。最后大叔也告知楼主半小时后hr面。
4.3 (HR): 此轮只有半小时，都是简单的bq和一些假设性问题，没有遇到需要STAR回答的。面试结束的时候，反问hr啥时候知道结果。hr说会安排final EM面。

5. Final (十一月初)
   EM final: 白人大叔，之前在其他金融公司也干过。基本就是各种常见bq，还有围绕实习的问题展开。最后20分钟反问了三个问题结束。

被ghost了大概快一个月，后面忍不住email最初的recruiter问了下，被告知另一个recruiter会联系我offer call。

虽然拿了offer，但是综合各方面信息看，开花堡的面试和offer很玄学。很多简历很强的朋友被简历挂或者vo挂，也不乏很多人final挂。所以可能运气成分还是最大吧。

# 2  2023-10 Senior Software Engineer - Market Oversight
> 背景： 博士毕业5年 5年计算机视觉相关工作经验

职位： Senior Software Engineer - Market Oversight
流程： 09.11网上海投 -》16.11 hr面试 -》08.12 视频面试 （面完觉得不挂不科学）

面试经历：
1. 聊项目： 额 只有机器学习相关项目 感觉跟面试官是鸡同鸭讲
   广告

2. 写代码： 题目： 设计一个sequecer类 输入id和content， 按照id顺序输出， 假设id不连续就暂时缓存， Example： [1, "aaa"], [2, "bbb"] [4, "ddd"], [3, "ccc"] etc，
   输出  aaa
   bbb
   ccc ddd
   代码其实很简单 中间穿插问了unordered_map, map, stack的插入查找复杂度。
   followup Q： 如果missing id一直不出现该怎么办？（buffer size, timeout, curr_id - missing_id > some_threshold， etc）

人生中第一次面developer的职位 一点经验没有，重在参与
面试最后对着小姐姐一顿彩虹屁：你是老夫面试多年遇到的最好的面试官 整个面试流程是最smooth的一次 感谢你非常有用的建议 祝你有个愉快的周末

# 3  2023-12 开花堡 bquant quant dev 一面
> 刚面了一轮，收到了virtualonsite 的邀请。攒个人品分享一下面试经历，面试官迟到了一会，但是整体面试都比较和谐，很多时间抠简历的细节，最后问了幺林额酒，改了提下场景但基本一样。

# 4 2023-12-07
> 新鲜出炉，刷题网幺珥遛。面试体验一般，白人大哥除了开始介绍业务之外就没说什么话，除了回答我的问题，非必要不开口，中途还离开了一会。

# 5  2023-12-07 bloomberg phone screen
> 算pascal三角形的的第n行第k列的值。就是(n k) ，但是不让用这个解法。面的一般，等跪

# 6  2023-11-16 Bloomberg 11月SWE实习面经
> 第一轮30分钟BQ面试，照着简历一个个问，做了什么经验，为什么选Bloomberg
第二轮Technical Interview, 面试题目leetcode其中一题，还详细地问了project做了什么，有什么challenge
> 79. Word Search, 有一个board有所有字母，找出单词是否能在board里面连出来
      然后我就被拒绝了，加油吧，小伙伴们
# 7  2023-11-16 BQL组
> coding 1: 打印最深层次括号里的字符串。 没见过。
> 
> ![img.png](Bloomberg.assets/img.png) 
> 
>我的思路是找到最深层次的括号，然后标记下位置。然后再倒序查找，找到字符串。
>明显不是他想要的。不过也写了。
>然后说太麻烦，要求只loop一遍。
>Coding 2: 三八零
> 
> ![img_1.png](Bloomberg.assets/img_1.png)
> 
> follow up: 三八一。
我觉得这货绝对有大病。我用的就是经典的map<String, List<Integer>> 结构。他不满意，说这样不行。不能达到O(1). 然后我说我们可以用PriorityQueue.
还是不满意。说是要存一个index 然后每次能很精准的找到位置，就达到O(1)
问题是你找到index 删除index 的值不占时间的呀。神经病嘛。 这是给的解，自己看吧。
明显感觉就是他从知道我不会BQL 就不想让我过。然后各种找茬。
尼码 又不是我要去的。 猎头推的。 关键是description里没写必须要会呀。 只说了有java, python这种经典语言的经验。

# 8
>![img_2.png](Bloomberg.assets/img_2.png)

# 9
> ![img_3.png](Bloomberg.assets/img_3.png)

# 10
> ![img_4.png](Bloomberg.assets/img_4.png)

# 11
> ![img_5.png](Bloomberg.assets/img_5.png)

# 12
> ![img_6.png](Bloomberg.assets/img_6.png)

# 13
> ![img_7.png](Bloomberg.assets/img_7.png)
> sweep line

# 14
> ![img_8.png](Bloomberg.assets/img_8.png)

# 15
> ![img_9.png](Bloomberg.assets/img_9.png)

# 16
> ![img_10.png](Bloomberg.assets/img_10.png)
> ![img_11.png](Bloomberg.assets/img_11.png)

# 17
> ![img_12.png](Bloomberg.assets/img_12.png)

# 18
> ![img_13.png](Bloomberg.assets/img_13.png)

# 19
> ![img_14.png](Bloomberg.assets/img_14.png)

# 20
> ![img_15.png](Bloomberg.assets/img_15.png)
> ![img_16.png](Bloomberg.assets/img_16.png)

# 21
> ![img_17.png](Bloomberg.assets/img_17.png)

# 22
> ![img_18.png](Bloomberg.assets/img_18.png)
> 
> Leetcode 253

# 23
> ![img_19.png](Bloomberg.assets/img_19.png)
> ![img_20.png](Bloomberg.assets/img_20.png)

# 24
> ![img_21.png](Bloomberg.assets/img_21.png)
> ![img_22.png](Bloomberg.assets/img_22.png)

# 25
> ![img_23.png](Bloomberg.assets/img_23.png)

# 26
> ![img_24.png](Bloomberg.assets/img_24.png)
> ![img_25.png](Bloomberg.assets/img_25.png)

# 27
> ![img_26.png](Bloomberg.assets/img_26.png)

# 28
> ![img_27.png](Bloomberg.assets/img_27.png)

# 29
> ![img_28.png](Bloomberg.assets/img_28.png)

# 30
> ![img_29.png](Bloomberg.assets/img_29.png)
> lc739?

# 31
> ![img_30.png](Bloomberg.assets/img_30.png)

# 32
> ![img_31.png](Bloomberg.assets/img_31.png)
> ![img_32.png](Bloomberg.assets/img_32.png) 
> ![img_33.png](Bloomberg.assets/img_33.png) world break II

# 33
> ![img_34.png](Bloomberg.assets/img_34.png)
> ![img_37.png](Bloomberg.assets/img_37.png)
> ![img_36.png](Bloomberg.assets/img_36.png)

# 34
> ![img_38.png](Bloomberg.assets/img_38.png)

# 35
> ![img_39.png](Bloomberg.assets/img_39.png)
> ![img_40.png](Bloomberg.assets/img_40.png)

# 36
> ![img_41.png](Bloomberg.assets/img_41.png)
> ![img_42.png](Bloomberg.assets/img_42.png)

# 37
> ![img_43.png](Bloomberg.assets/img_43.png)

# 38
> ![img_44.png](Bloomberg.assets/img_44.png)

# 39
> ![img_45.png](Bloomberg.assets/img_45.png)
> ![img_46.png](Bloomberg.assets/img_46.png)

# 40
> ![img_47.png](Bloomberg.assets/img_47.png)

# 41
> ![img_48.png](Bloomberg.assets/img_48.png)
> ![img_49.png](Bloomberg.assets/img_49.png)

# 42
> ![img_50.png](Bloomberg.assets/img_50.png)

# 43
> ![img_51.png](Bloomberg.assets/img_51.png)

# 44
> ![img_52.png](Bloomberg.assets/img_52.png)

# 45
> ![img_53.png](Bloomberg.assets/img_53.png)

# 46
> LC 146

# 47
> ![img_54.png](Bloomberg.assets/img_54.png)

# 48
> ![img_56.png](Bloomberg.assets/img_56.png)
> ![img_57.png](Bloomberg.assets/img_57.png)

# 49
> ![img_58.png](Bloomberg.assets/img_58.png)

# 50
> ![img_59.png](Bloomberg.assets/img_59.png)

# 51
> ![img_60.png](Bloomberg.assets/img_60.png)

# 52
> ![img_61.png](Bloomberg.assets/img_61.png)

# 53
> ![img_62.png](Bloomberg.assets/img_62.png)