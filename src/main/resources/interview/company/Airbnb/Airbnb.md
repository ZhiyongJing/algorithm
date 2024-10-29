1. 2024(7-9月) 码农类General 硕士 全职@airbnb - 网上海投 - Onsite  | 😐 Neutral 😐 AverageOther | 在职跳槽

   > 店面
   > iterator of list of list of integer 只有两层
   >
   > Vo
   > 1 coding
   > 就是retryer 地里提到过多次了 注意leetcode是多层嵌套，面试的题目只有2层
   > 这个秒了
   >
   > 2 系统设计
   > 就是上面帖子里提到的group chat system
   > 一个中国面试官全程非常严肃不怎么说话
   > 这轮心里没底
   > 3 code review
   > 我选的Java 内容大致就是上面帖子里
   > 基本上平时多review code就没什么问题
   > 这轮不用说话，自己review就行
   > 问了一个问题面试官也说没啥background...
   > 4 experience
   > 就是问以前做过的project以及impact
   > 建议事先画个图准备一下，问的挺深入的
   > 面试官人很好
   >
   > Recruiter说正在gathering feedback 然后team matches
   > 目前还在等消息
   > 有问题请回复
   > 同时求加米

2. 2024(10-12月) 码农类General 硕士 全职@airbnb - 猎头 - 技术电面  | 😐 Neutral 😐 AverageOther | 在职跳槽

   > 刚刚电面了airbnb，一道题目函数多次retry：retry 一次，n次，指数级backoff。地理面经看的一头雾水。
   >
   > 面试时候给你一个模版，里面定义了Retry class你只是需要在class里实现retry的方法就行了，大约方法是
   > class Retry {
   > // 就是实现这个函数：其实很简单的，不过题目略绕，刚开始不知道要干嘛。
   > public <T> execute(Supplier<T> callback) {
   >
   > }
   >
   > // 比如n次retry
   > public Supplier<T> execute(Supplier<T> callback, int maxRetry, int backoffMs) {
   >   int counter = 0;
   >
   > while (true) {
   >       try {
   >           Supplier<T> ans = callback.get();
   >           return ans;
   >       } catch(.... e) {
   >           if (counter <maxRetry) {
   >               ++counter;
   >               try {...sleep(backoffMs)} catch(... ) {...}
   >           } else {
   >                throw e;
   >           }
   >       }
   > }
   > }
   > }
   >
   > ```java
   > public <T> Supplier<T> execute(Supplier<T> callback, int maxRetry, int backoffMs) {
   >     int counter = 0;
   > 
   >     while (true) {
   >         try {
   >             Supplier<T> ans = callback.get();
   >             return ans;
   >         } catch (Exception e) { // Replace with the appropriate exception type
   >             if (counter < maxRetry) {
   >                 counter++;
   >                 try {
   >                     Thread.sleep(backoffMs); // Replace with the actual sleep function
   >                 } catch (InterruptedException ie) { // Replace with the appropriate exception type
   >                     Thread.currentThread().interrupt();
   >                 }
   >             } else {
   >                 throw e;
   >             }
   >         }
   >     }
   > }
   > 
   > ```
   >
   > 
   >
   > 实现3个函数，其中有大量的overlap代码，要求简化。我简单实现了一个helper function，另外三个executor就去调用那个function就行了。
   >
   > 这题容易，但是跟普通算法题不一样，刚开始有点儿懵！
   >
   > 面试官挺有趣儿，说了几句话后，大约1分钟吧直接说来做题吧，做完题再聊天。这点挺好，不耽误你的时间。

3. 2024(7-9月) 码农类General 硕士 全职@airbnb - 猎头 - 技术电面 Onsite  | 😐 Neutral 😐 AverageFail | 在职跳槽

   > Phone Coding 45 minutes
   > 亚裔女生 利口 39
   >
   > VO1 System Design 60 minutes
   > 貌似伊朗大哥，design listing wail list feature. Users can add or remove themselves to or from a wait list of a host listing. Send notification to the user when the host listing got open during the desired time range. Don’t send notifications at once to all users in the waitlist. 大哥总打断我主动给我很多提示，我也不知道这样是好还是不好。
   >
   > 假设已经有一个booking system，现在想允许用户把自己加到已被别人预定的房子的waitlist里。
   >
   > VO 2 Coding 45 minutes
   > 中国老哥，说活声音很轻柔。Function retryer. Single retry, fixed interval retry, exponential backoff retry。
   >
   > 谢谢！差不多就是你说的样子。catch exception and retry就可以。具体的retry wait time can be passed in by caller as lambda function.
   >
   > VO 3 Code Review 45 minutes
   > 中国老哥，我选的Java。PR 1 input validation check. PR 2 dependency call. PR 3 job queue, batch processing and multi-threading.
   >
   > VO 4 Past Experience 60 minutes
   > 白人大哥，同样说话声音很轻柔。问了两个项目，难点在哪里，有没有造成什么事故，stakeholder是谁，如果重做的话哪些地方你会做得不一样。
   >
   > 感觉应该是寄了

4. 2024(7-9月) 码农类General 硕士 全职@airbnb - 网上海投 - Onsite  | 😐 Neutral 😐 AverageFail | 其他

   > Coding轮：设计retryer，之前的帖子里有看到过。
   > SD第一轮：设计group chat。这一轮的面试官除了backend的设计，也很注意前端的设计，最后他很想问一些前端设计的细节但是没时间了。
   > SD第二轮：设计一个experience calendar，比较常规，会问一下怎么scale，db replica之类的
   > BQ：past project deep dive。自己选一个过去的project来讲，面试官中间会打断问一些clarify的问题。

5. 2024(7-9月) 码农类General 硕士 全职@airbnb - 网上海投 - Onsite  | 😐 Neutral 😐 AverageOther | 在职跳槽

   > 1. coding 滑雪
   > 2. experience
   > 3. coding Text Justification （跟leetcode 不一样，不需要考虑每行space均匀分布）
   > 4. SD event aggration， with query support， eg：返回过去1hour event per user

6. 2023(7-9月) 码农类General 硕士 全职@airbnb - 内推 - 技术电面  | 😐 Neutral 😐 AverageOther | 在职跳槽

   > 之前地理看到过的combination sum变种 给你多少钱，买吃的要求刚好花完这些钱
   > 因为做过这道题 一上来就说要做一个decision tree，然后subtree里面会有duplicates，所以subtree里面要exclude之前的subtree选的食物，用backtracking。然后开始写代码。做完还剩下15分钟，有点担心一开始的解释会不会太少了。大家觉得呢？
   >
   > 这题就是backtracking/dfs，和利口combination sum一样的

7. 2024(7-9月) 码农类General 硕士 全职@airbnb - 网上海投 - 技术电面  | 😃 Positive 😐 AveragePass | 在职跳槽

   > 刚前几天的电面 sde2 题目是 edit distance lc原题…

8. 2024(7-9月) 码农类General 硕士 全职@airbnb - Other - 技术电面  | 😃 Positive 😐 AverageOther | 在职跳槽

   > 设计并实现一个retryer，蛮好的一个题，没什么trick，比较贴近实际工作

9. 2024(7-9月) 码农类General 硕士 全职@airbnb - 内推 - Onsite  | 😐 Neutral 😣 HardFail | 在职跳槽

   > 面完其实就知道自己99%挂了
   > code review: 一共3个PR，时间满紧的，第一次考code review，也没怎么准备
   >
   > SD：wallet service
   > coding: 这一轮我遇到了完全没见过的一道题，之前就听说它家换题库了，看来是真的：给一个矩阵，两个player轮流drop到矩阵的column里面， 如果column满了或者某一个player赢了就退出。。。code没写完，我觉得如果完全没见过这道题，要在40分钟内写完并且通过所有test cases相当难, 刷题网叁肆八? 不是。比这个48难
   > experience: 其实就是BQ

10. 2024(4-6月) 码农类General 硕士 全职@airbnb - 网上海投 - Onsite  | 😐 Neutral 😣 HardFail | 在职跳槽

    > Coding
    > input是机票票价以及最多允许的转机次数，给出起点和终点求最少花费的机票行程。比如:
    > [London, Japan, 500], [Japan, Beijing, 100], [London, Beijing, 1000]
    > 如果能转机一次那就是[London, Japan, 500], [Japan, Beijing, 100] = 600
    > 如果不允许转机那就是[London, Beijing, 1000] = 1000
    >
    > 可以Dijkstra也可以dfs暴力搜索吧， Dijkstra是点到其他所有点的最短路径，难点是traverse的时候还得记挺多额外的信息来reconstruct path。DFS可以记录一个step，这样当step > k的时候就不用继续搜索了，dfs得到路径更简单。我面试的时候后来给的dfs solution感觉方向是对的，但是写的code有点bug所以没handle不允许转机的情况。 
    >
    > 算带权重的最短路径
    >
    > 
    >
    > Code Review
    >
    > 嗯，dfs是可以的， 给定service的context，有3个PR要review，PR不是context里提到的API实现，而是已有的实现基础上有些新feature或者bug fix。没有面过code review但是我感觉应该先大致看一眼每个PR是关于什么的然后问面试官从哪个开始，我当时就直接一个接一个的review发现花了很多时间在两个小的PR上，最后有个大PR应该是可以多花点时间的。应该跟平时review一样抓大放小，先解决主要实现问题然后再看具体细节。不过在面试压力下看一堆不熟悉的code还是有点难度的。
    >
    > System Design
    > Metric logging, 统计一些business需要的metric，比如对于一个listing的view count，click count之类的，用户主要是internal eng查看。感觉对data integrity要求比较高。
    >
    > BQ
    > 从最impactful项目开始问，问得非常细节比如说多少人做的花了多长时间，项目idea谁提出的，有什么conflict，milestone怎么定的等等。
    >
    > 感觉是code review和bq没怎么表现好所以挂了

11. 2024(4-6月) 码农类General 博士 全职@airbnb - 猎头 - HR筛选 技术电面 Onsite 视频面试  | 😐 Neutral 😣 HardOther | 在职跳槽

    > 刚VO结束，结果未知。Coding应该是hard难度，没写完，估计要挂。
    >
    > technical screen： HM问了几个BQ问题
    >
    > VO：
    >
    > 1. SD：Design Wallet system。要求支持GetBalance，GetTransactionHistory，ProcessPayment（通过call 3rd party platform）
    > 2. SD：Design covid vaccination rollout system。要求支持， user registration，user update demographic info， provide eligibility based on some rules （比如，年龄限制，职业限制等等）。
    >           政府可以update those rules
    > 3. 刚面完的电面，准备了一大堆的面经都没能够用上，和菜单题相似但是改了一下，应该还是DFS，但是硬写DFS还是生疏了一些
    >
    >       给你一堆菜单有一系列套餐，比如
    >       [
    >           [5.00, "pizza"],
    >
    >       [8.00, "sandwich,coke"],
    >           [4.00, "pasta"],
    >           [2.00, "coke"],
    >           [6.00, "pasta,coke,pizza"],
    >           [8.00, "burger,coke,pizza"],
    >           [5.00, "sandwich"]
    >       ]
    >
    >       然后有一个wishlist  ["burger", "pasta"]，买各种套餐的组合来cover所有想要的需求，求最小开销
    >
    > 应该是稍微简化一些的利口 衣衣而舞，DP + bitmask（如果菜品数不超过64）
    > 用bit mask表示wishilist和每一个套餐的菜品。dp数组dp[i]表示bit mask为i时候的最小花销，从i=0开始推导每次在所有套餐中选择和wishlist重叠并且总花销最小的套餐，然后更新dp[i]。状态转换方程和换零钱问题相似
    >
    > 衣衣而舞 难度还要高一些，因为最后要得出具体的最优组合
    >
    > 4. Experience：project deep dive
    >
    > 对 是面staff level，9+yoe。是recruiter reach out的。我自己也觉得背景跟职位的确非常match

12. 2024(4-6月) 码农类General 硕士 全职@airbnb - 网上海投 - 技术电面  | 😃 Positive 😣 HardFail | 在职跳槽

    > 楼主工作6年，5月官网投递的国内senior，6月初约的电面，面试官国人小哥，面试前没见过的题目，要求从一堆排好序房间list中分页，并且每页host尽量不重复 如果没填满一页再按照原来的顺序，解出来了bug free，面试官说不是最优解，想了一下觉得可以分桶，再merge，不过没时间写了，过一天就收到了拒绝信，真的很难。

13. 2024(4-6月) 码农类General 硕士 全职@airbnb - 网上海投 - 技术电面  | 😐 Neutral 😐 AverageFail | 在职跳槽

    > 我面试遇到的这题似乎不再题库里面(应该不在吧.. 完全没看过啊), 语法是c/c++
    > 要求实现 function retrier, 意思就是要try/catch error 然后重新执行function
    > 要求实现三种
    > 1. 基本要求, retry 一次
    > 2. 每隔固定interval, retry 一次
    > 3. exponential backoff
    >
    > 完全不懂, 写不出来, 所以程式码我也记不住...
    > 惨烈的fail
    >
    > 我觉得这种要实现一种feature的问题根本没意义啊, 又没有算法层面的东西, 一翻两瞪眼, 平常工作没用到不会就是不会

14. 2024(4-6月) 码农类General 本科 全职@airbnb - 内推 - 视频面试  | 😃 Positive 😐 AverageFail | 在职跳槽

    > 输入2D nested list，然后写iterator。
    > next, hasNext
    > delete()方法，要求delete“上一次”调用next()时访问的element. has to be in-place

15. 2024(4-6月) 码农类General 硕士 全职@airbnb - 内推 - Onsite  | 😃 Positive 😣 HardOther | 在职跳槽

    > 巴流丝的变形题，解题思路差不多，M*N到地图上有房子，钥匙和障碍。老铁们求加米。

16. 2023(4-6月) 码农类General 本科 全职@airbnb - 内推 - Onsite  | 😃 Positive 😐 AverageWaitList | 在职跳槽

    > Round 1: Nondeterministic Trellis
    >
    >  AutomataRound 2: lc 68
    > Round 3: RSS Feed
    > Round 4: Cross Functional Interview with someone in Customer Experience team
    > Round 5: Cross Functional Interview with Engineering manager of payments team
    > Round 6: Resume deep dive

17. 2024(4-6月) 码农类General 硕士 全职@airbnb - 内推 - 技术电面  | 🙁 Negative 😫 HardestOther | 在职跳槽

    > 刚面完的电面，准备了一大堆的面经都没能够用上，和菜单题相似但是改了一下，应该还是DFS，但是硬写DFS还是生疏了一些
    >
    > 给你一堆菜单有一系列套餐，比如
    > [
    >     [5.00, "pizza"],
    >
    > [8.00, "sandwich,coke"],
    >     [4.00, "pasta"],
    >     [2.00, "coke"],
    >     [6.00, "pasta,coke,pizza"],
    >     [8.00, "burger,coke,pizza"],
    >     [5.00, "sandwich"]
    > ]
    >
    > 然后有一个wishlist  ["burger", "pasta"]，买各种套餐的组合来cover所有想要的需求，求最小开销
    >
    > 应该是稍微简化一些的利口 衣衣而舞，DP + bitmask（如果菜品数不超过64）
    > 用bit mask表示wishilist和每一个套餐的菜品。dp数组dp[i]表示bit mask为i时候的最小花销，从i=0开始推导每次在所有套餐中选择和wishlist重叠并且总花销最小的套餐，然后更新dp[i]。状态转换方程和换零钱问题相似
    >
    > 衣衣而舞 难度还要高一些，因为最后要得出具体的最优组合

18. 2024(1-3月) 码农类General 硕士 全职@airbnb - 猎头 - Onsite  | 😐 Neutral 😐 AverageOther | 在职跳槽

    > 楼主最近几个月面了气床，心情从最开始的激动到中间的失望再到最后的躺平。。。
    > 店面过后，Recruiter说约Onsite，中间一度失联几周，发邮件也不回，就在我坚定的觉得被Ghost了后，他又莫名其妙出现了，回了句我收到你的Availability了。。然后又消失了。楼主也没再找他们，因为也知道找不到，就只能等着他们联系我。又过了两周终于发来了说安排好了。几周后好不容易面了，最后一轮又不断的被Reschedule，又拖了几周。。（还有他家安排时间，选的永远都是你给的最后一个时间）今天终于面完了，一个面试拖拖拉拉几个月，楼主后来一直很佛，找我就接着面，不找就拉到。。面这家就不能急，不然能被气死 orz ...
    >
    > onsite 面试四轮，也都是面筋上有的
    >
    > 1. Code Review 就是给三个PR，让你去看，想写啥写啥。  哎我被拖的都佛了 他们最好直接告诉我挂了 不然我都不会去问
    > 2. SD: group chat 。 没什么要求，自由发挥。。奇怪的是一直是我在说，对方就一直听着。。
    > 3. coding： 那个倒水。 40分钟时间真的紧张，尤其是还要写Print。当时楼主脑子抽了，Code 全写完了，但是Parameter传错了。。导致没Test完。。你去搜 深秋面经 倒水 像刷题网期捂捂 不过还要把图全都打印出来
    > 4. tech experience: 这轮面筋里写的都是各种PUA，但是楼主经历的也基本全是我在说。。。因为不知道这轮是需要我自己Driver，所以时间没把握好，导致没讲完。。

19. 2024(1-3月) 码农类General 硕士 全职@airbnb - Other - 在线笔试  | 😃 Positive 🙂 EasyFail | 在职跳槽

    > 和这个差不多
    >
    > -----------------
    >
    > HM: 聊了一下项目，问了几个BQ
    > VO:
    >
    > 1. Experience: 过去项目的dive deep
    > 2. SD：一个给host看的平均价格的chart，LZ聊了一下online data processing和offline data processing
    > 3. codig： 面试官自己出的题，一个retryer，要求是client可以自由选择retry 策略。这轮答得不好。
    > 4. SD：一个加了马甲的job schduler
    >
    > 隔周HR安排了cross function 和 coding加面。
    > 加面是boggle game，算是面经题。
    >
    > 在隔周，HR call说是面试通过了，没有red flag。
    > 但是HC觉着缺少staff level的signal。问要不要考虑senior level。
    > LZ有了别家的staff offer了，就move on了。
    >
    > ----------------
    >
    > 1. Experience: 聊了一下过去讲的项目
    > 2. SD：一个给host看的平均价格的chart，online data processing, offline data processing
    > 3. coding： 一个retryer，要求是client可以自由选择retry 策略。
    > 4. Code review

20. 2023(1-3月) 码农类General 硕士 全职@airbnb - 网上海投 - 技术电面  | 😐 Neutral 😣 HardFail | 在职跳槽

    >  为什么大家都是考老题，就我来了个新题……
    >
    > part 1
    > 给一串0到9的数字，返回最小可以组成的整数，以string返回
    > 比如 [1, 3, 3, 4, 2] -> "12334"
    > 所有数字用一遍，0除外，比如[0, 1, 2]就返回12
    >
    > part 2
    > part1的基础上返回值要大于或等于一个lower bound
    > 比如 [7, 1, 8], lower bound = 719，返回781
    >
    > 0在part 1没用，但在part 2有用
    >
    > part 1简单，part 2有思路但只写到一半，面到45分钟准时叫断，肯定挂了
    >
    > ````java
    > public class GetMinNum {     public static String getMinNum(int[] arr) {         int[] bucket = new int[10];         for (int digit : arr) {             bucket[digit]++;         }          StringBuilder sb = new StringBuilder();         boolean addedZero = false;         for (int i = 1; i < bucket.length; i++) {             int count = bucket[i];             for (int j = 0; j < count; j++) {                 sb.append(i);             }              if (sb.length() > 0 && !addedZero && bucket[0] > 0) {                 for (int k = 0; k < bucket[0]; k++) {                     sb.append(0);                 }                 addedZero = true;             }         }          return sb.toString();     }      public static String nextPermutationWithLowerBound(int[] arr, int boundNum) {         int[] freqArr = new int[10];         for (int digit : arr) {             freqArr[digit]++;         }          String boundNumStr = String.valueOf(boundNum);         if (boundNumStr.length() > arr.length) {             return "-1";         }          if (boundNumStr.length() < arr.length) {             return getMinNum(arr);         }          String res = nextPermutationWithLowerBound(freqArr, boundNumStr, 0);         return res == null ? "-1" : res;     }      private static String getMinWithOutZero(int[] freqArr) {         StringBuilder sb = new StringBuilder();         for (int i = 0; i < freqArr.length; i++) {             for (int j = 0; j < freqArr[i]; j++) {                 sb.append(i);             }         }          return sb.toString();     }      private static String nextPermutationWithLowerBound(int[] freqArr, String boundNumStr, int idx) {         if (idx == boundNumStr.length()) {             return "";         }          int boundDigit = boundNumStr.charAt(idx) - '0';         if (freqArr[boundDigit] > 0) {             freqArr[boundDigit]--;             String subRes = nextPermutationWithLowerBound(freqArr, boundNumStr, idx + 1);             if (subRes != null) {                 return boundDigit + subRes;             }             freqArr[boundDigit]++;         }          for (int i = boundDigit + 1; i < freqArr.length; i++) {             if (freqArr[i] > 0) {                 freqArr[i]--;                 String subRes = getMinWithOutZero(freqArr);                 return i + subRes;             }         }          return null;     }      public static void main(String[] args) {         System.out.println(getMinNum(new int[]{1,3,3,4,2}));         System.out.println(getMinNum(new int[]{7,3,0,1}));         System.out.println(nextPermutationWithLowerBound(new int[]{1,7,8}, 719));     } }
    > ````
    >
    > 
    >
    > 类似于这个题
    >
    > 2020(1-3月) 码农类General 硕士 全职@airbnb - 网上海投 - 技术电面  | Other | 在职跳槽
    >
    > 碰到很nice国人大哥，题目不难，但不是面经的题。
    > warm up:
    > Given input of digit array, e.g. [4,1,8] return the smallest number can be created using all digits。 For example [7,3,0,1] --> "1037"
    > 限定条件：输入digit可以有0， 结果可能overflow。输出结果可以是string
    > 解法把input digits sort in ascend order, concanate 成number。如果有0，把所有0放在第一个digit后面就行。
    >
    > follow up 1:
    > what's the time complexity, can you do in O(N) time.  --> bucket sort input digitis.
    >
    > follow up 2:
    > Similar to warmup question, input 多加一个lower bound. 要求输出最小的number, 但是要大于或等于lower bound.
    > 解法其实就是find all permutations, 找出大于或等于lower bound的最小值。
    >
    > 题目不难，就是hackerrank没有打开auto complete和auto import。所以debug 也要用一点时间。

21. 2024(1-3月) 码农类General 硕士 全职@airbnb - 网上海投 - 技术电面  | 😃 Positive 😐 AverageOther | 在职跳槽

    > 题目是ArrayList Iterator, 自己实现 next(), hasNext(), remove() 跑case
    > 这个题目在深秋版的面经里出现过，point就是airbnb考得还是那些题目。

22. 2023(10-12月) 码农类General 硕士 全职@airbnb - 内推 - 技术电面 Onsite 视频面试  | 🙁 Negative 😐 AverageFail | 在职跳槽

    > 电面：类似 刷题网 散思忆，区别在于
    > 1. 输入是raw 2D (nested) list/array
    > 2. 添加了delete()方法，要求delete“上一次”调用next()时访问的element，并且in-place修改传入的2D list，所以直接flatten成1d list的解法是不可行的。
    >
    > VO 1: Run Length Encoding (RLE)
    > Base：输入array，比如(1,1,1,3,6,6,1,1)，输出RLE的结果((3,1), (1,3), (2,6), (2,1))，即3个1，1个3，2个6，再2个1。
    > Follow up A: 等差数列RLE，比如(3,4,5,10,15,20,25)，输出RLE的结果((3,1,3), (10,5,4))，即【从3开始，差为1，数3个数】，再【从10开始，差为5，数4个数】。
    > Follow up B: 给Follow up A的encode输出写对应的decoder/iterator，通过 while ( hasNext() ) { yield next(); } 能还原输入的array。
    >
    > VO 2: Keyword Highlighting
    >
    > 给一段text以及一个关键词map，标注出关键词，e.g.
    >
    > Input text:     We visited google office in Mountain View on Feb 30th.
    > Input dict:     "Mountain View": "city", "Google": "company", "Mountain": "nature"
    >
    > Output:         We visited [company]{google} office in [city]{Mountain View} on Feb 30th.
    >
    > 解释1：关键词里的"Google"可以match text里的"google"，不区分大小写
    > 解释2：关键词优先match最长的，同时存在Mountain View和Mountain的情况下，只有前者被match
    > 解释3：输出不能改变原文的大小写，比如输入是小写google，尽管关键词是Google，输出也要保持小写g
    >
    > 以上所有coding都需要能run，自己写test case，并且bug free。
    >
    > SD：在线群聊系统，不需要太考虑scalability，着重数据库table设计以及message deliver的方式。
    >
    > 聊experience/project（这一轮还不算behavior）：地里其它帖子提到过，这是最恶心的一轮，1小时高强度挑刺pua。
    >
    > 总结：VO似乎都是新题，好在不算难。SD也是常规面经题。面完一周通知挂了，个人觉得是被experience轮整了。
    >
    > 除了电面是老题

23. 2023(10-12月) 码农类General 硕士 全职@airbnb - 网上海投 - Onsite  | 😐 Neutral 😐 AverageOther | 在职跳槽

    > HM: 聊了一下项目，问了几个BQ
    > VO:
    > 1. Experience: 过去项目的dive deep
    > 2. SD：一个给host看的平均价格的chart，LZ聊了一下online data processing和offline data processing
    > 3. codig： 面试官自己出的题，一个retryer，要求是client可以自由选择retry 策略。这轮答得不好。
    > 4. SD：一个加了马甲的job schduler
    >
    > 隔周HR安排了cross function 和 coding加面。
    > 加面是boggle game，算是面经题。
    >
    > 在隔周，HR call说是面试通过了，没有red flag。
    > 但是HC觉着缺少staff level的signal。问要不要考虑senior level。
    > LZ有了别家的staff offer了，就move on了。

24. 2023(10-12月) 码农类General 硕士 全职@airbnb - 猎头 - Onsite  | 😃 Positive 😐 AveragePass | 在职跳槽

    > 写一个过经回馈地里，签了NDA，说的模糊一点
    >
    > 这个公司最近在地里的面经风评不好，说是面试官都比较傲慢，但是我自己的体验还挺好的
    >
    > Coding：深秋travel buddy
    > SD：典型的user event aggregation system，什么方面都问到了，database API啥的
    > Code Review：给几个PR写code review，没啥难度，就跟在自己公司里review junior eng的PR一样，需要注意的是时间蛮紧张的，拿到就赶紧review
    > Experience：聊简历 聊过去的项目
    >
    > 求大米！！！！！

25. 2023(7-9月) 码农类General 本科 全职@airbnb - 网上海投 - Onsite  | 🙁 Negative 😐 AveragePass | 在职跳槽

    > VO一共4轮
    > 第一轮：Coding，题目是reshuffle array
    > 第二轮：系统设计，设计一个空气床用户的action collection 系统
    > 第三轮：Project Deep Dive
    > 第四轮：系统设计，设计群聊系统
    >
    > LZ接近10年经验，大大小小面试次数也有300+次，自己也是当前公司的面试官+面试官培训师。
    > 可以说爱逼逼的面试体验，是我整个career里面试体验最糟糕的

26. 2023(1-3月) 码农类General 硕士 全职@airbnb - 猎头 - Onsite  | 😃 Positive 🙂 EasyWaitList | 在职跳槽

    > 新鲜面筋，趁着还记得
    >
    > 4轮：
    > 第一轮，国人大哥，project deep dive，主要问你的project是干嘛的，有啥mile stone，你在project里扮演什么角色，每个milestone里有没有遇到什么困难，怎么解决。跟其他组的小伙伴们合作交流时有没有conflict，怎么解决。最后，做完这个project后有啥人生感悟
    > 第二轮，brown的小哥，system design，刚好问到我做过的东西。设计一个收集telemetry的系统。标准操作，上来clear需求，估存储需要，qps之类的。然后设计，模块之间的api和payload。设计数据库表单怎么存（sharding strategy），怎么query。最后，处理failure case，譬如，down了怎么办，慢了怎么办。最后问了，还能怎么优化，答曰：通过用户地域优化来preload cache。
    >
    >
    > 第三轮，brown的小哥2号，code review。这个很难描述，读题花的时间比comment长，3个pr，最后一个没有全做完。但不难，一眼就能看出test case覆盖面不够，有duplicate code。不过考了多线程（第三个pr），时间紧迫，就质疑了一下race condition有没有处理好。
    >
    > 第四轮，manager小姐姐，说是算法轮，但感觉被考了OOD。设计一个simulation游戏，具体规则不难，就是有一个游戏，有一堆牌，每个都有一个数字，能站起来or倒下。有骰子，2颗，player可以throw。提供一个函数，判断game over了没有。over的条件是如果骰子择出来的数字，牌里没有了，就可以over了。牌里有没有的规则是，有相应的牌是那个数字，或者有两个没倒下的牌加起来是那个数字。（two sum你懂的）。最后高速run了一个test case证明board类work就没时间了，player类和simulation类，只口述了思路
    >
    > 总体感觉面试官们都挺不错的，总体很nice，聊的挺愉快。除了第三轮真的太累以外，其他每轮我都反向bq了一波（假装有备而来）。
    >
    > 第二天给拒信，说系统设计和coding不行。说实在，整体不难，coding也就理解题目用得久一点，但系统设计真的no clue哪里挂了，嘛，move on

27. 2022(1-3月) 码农类General 硕士 全职@airbnb - 猎头 - 技术电面  | 😃 Positive 😐 AverageWaitList | 在职跳槽

    > 可能是abc的小姐姐，人挺好
    > 不知道面经理有没有，本人裸考的。一条数岛题的变形，给一个2d array，岛有类型和分数，你要算整个矩阵的总分数。
    >
    > 然而还是不能bug free，不知道小姐姐会不会放我过
    >
    > 补充内容 (2023-02-24 23:23 +8:00):
    > 昨天收到通知过了，估计考虑了很久。最近怎么都没什么人发airbnb的面经的，是大家都不爱写面经还是最近他们家没人面试。但hr跟我说，volume很多所以这么晚才回我...看来是去陪跑的...
    >
    > 一看就知道是数岛的马甲，个人觉得你准备的重点放在那题上比较好。我尽力再说具体点。
    > 就是，数岛题那样，给2为数组，但每个格子不是单纯的0，1，是类型+分数。然后算分数的方法是每个岛的size*分数总和。然后再把整个grid里的所有岛的分数加起来。
    > 我觉得这个可以各种变化着考，太具体地准备了我这个也不一定有用。这题反手就能来十个八个变体和follow up。譬如，改变算总分的规则，改变算岛数目的规则，给的grid要时常改变啥的。

28. 2022(10-12月) 码农类General 硕士 全职@airbnb - 内推 - 技术电面  | 😃 Positive 😐 AveragePass | 在职跳槽

    > 12月初面的，高频题 341
    > List of List iterator
    > 比如说, Given 2d vector = [ [1,2], [3], [4,5,6] ]
    >
    > 要求实现
    > boolean hasNext()
    > int next()
    > void remove()
    > 面试官说不一定所有call next()之前都会call hasNext().



