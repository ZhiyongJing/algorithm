1. 2022(7-9月) 码农类General 硕士 全职@walmartlabs - 网上海投 - 技术电面 Onsite  | 😃 Positive 🙂 Easy | Pass | 在职跳槽

   > 1. 电面，通过karat来面试。
   >
   > 题目是5选2基础知识，我选了OOD和test，大概的几个问题是override vs overload， polymorphism概念，给看一段代码，告诉他在deploy之前需要做些什么，答写unit test cover 所有条件。
   > 然后是做题，题目1 是karat面经中的string pattern, Find the first word in an array of words that matches a string pattern.
   > 题目2 是word search。 都不难。
   >
   > 2 VO
   > 他家VO就只有两轮，
   > 第一轮是1.5个小时，主要对你的简历考查tech dive deep，然后做一个system design， 题目是设计一个netflix。
   > 第二轮是1小时，HM考察behavior question。

2. 2024(7-9月) 码农类General 硕士 全职@walmartlabs - 网上海投 - 技术电面 视频面试  | 😃 Positive 😐 Average | Pass | 在职跳槽

   > 上周做了walmart的karat面试，形式是固定的：
   > 前5分钟自我介绍
   > 10分钟五类题目选两个快问快答，每一类各3道题
   > 最后45分钟coding，基本都是简书里原题，一共三道题，三道题是followup的形式，题干类似，做出一道才会给下一题，45分钟一般能把前两题做完，第三题写一点思路。面试官会在main函数里贴出所有测试数据，需要自己打印出运行结果，所有结果都正确就是通过。
   >
   > 楼主做了两次，由于第一次没好好看题库，第二道coding没跑成功。于是又redo了一次，顺利走到了第三道题。
   >
   > 两次的快问快答依稀记得的题目有：
   > Production issue类：
   > 1. 给一个图，图里cpu rate保持不变，ram rate随时间逐渐上升，解释这种现象
   > 2. 有一个服务，怎么测试它是否可以支持1000 request per second
   >
   > OOP类：
   > 1. Composition vs inheritance
   > 2. 选择上面任意一种的tradeoff是什么
   >
   > coding题目，第一次是简书里的nonogram那组题目，当时没做成功，事后回想，找出每一行/列的模式后，和给定的模式比较这样做会比较简单一些。
   > redo这次的题目是badge这道题，前两道题目简书里有，下面是第三道题的题干。这道题没想到什么好思路，当时时间也不多了，随便说了几句。

3. 2025(1-3月) 码农类General 本科 全职@walmartlabs - 猎头 - 技术电面  | 😃 Positive 🙂 Easy | Other | 在职跳槽

   > 贡献一个walmart karat的面经。
   > 二月底第一次参加karat 面试，上来1min自我介绍，然后5个问答题选2，我选了ood和另一个忘了。
   > odd有问inheritance和composition有什么区别什么的。
   > 然后做题，上来简单题，给一个0和1组成的矩阵，0表示可以走，1表示障碍物。第一问是找出走直线的情况下，所有全 0 的row和col id。
   > 当时许久不刷题，直接白给，于是马上约redo。
   > 3月底刚刚karat redo。
   > 套路一样，这次5个问答，我选了network and security 和场景题。有问怎么区分ddos和real website popularity increase，有问ddos 用proxy service防御缺点是什么之类。其他忘了。。
   > 还有frontend和test的选项（楼主后端直接震惊有frontend问答题可以选）。
   >
   > 然后算法两道题，第一道是给10个密码，5条规则，输出每条密码不符合的规则id。
   > 长度超过16
   > 不包含password，case insensitive
   > 包含大写和小写字母
   > 相同字母不超过4次，a 和A算不同的字母
   > 哦哦要包含特殊字符比如@#*
   >
   > hashmap存一下每个字符搞定，on 循环里判断每个character
   > 第二问是 数据处理感觉？给了user payment 和ticket price矩阵
   > user有 乘客名字，邮箱，买的什么票，买了几张的数据
   > payment 里有payment id，邮箱，总钱树
   > ticket 矩阵是ticket type 和单价数据
   > 输出payment id -> user name
   > payment 数据里，有可能没有邮箱。所以要用钱来match
   > 时间不够没做完，应该是提前用map 处理一下 name -> 邮箱和total amount的mapping就行。

4. 2023(1-3月) 码农类General 本科 全职@Karat - 网上海投 - 技术电面  | 😐 Neutral 😐 Average | Pass | 应届毕业生

   > 最近面了几家公司都用Karat提供OA， 面试形式都是一样的， 5 分钟自我介绍， 10分钟回答三道基础知识问题， 45分钟coding 有三轮，大部分公司打出两道就给过。给大家分享一下见过的题
   >
   > 
   >
   > 道基础知识题：
   > 1. A song service uses consistent hashing to distribute songs. Is this okay to use?
   > 2. A service has 1000 documents and gets 1000 requests, how many servers and db do you need to support this service
   > 3. Given 3 examples, select which type of consistency
   > 4. For an app what are pros/cons of storing hints on device vs on server
   > 5. If you want to launch an app globally, how do you scale the backend
   > 6. If you build a log analysis system, what considerations are needed to estimate the number of servers needed for next year
   >
   > Coding Round 见过的题
   >
   > 1. 写 TicTacToe
   > 2. 写AI来玩，AI可以放任何空地方
   >
   >
   > 写snake游戏
   > 1. 给一个matrix，0 是passable，+是impassible。 找出row和column都是0.
   >    board1 = [['+', '+', '+', '+', '+', '+', '+', '0', '0'],
   >           ['+', '+', '0', '0', '0', '0', '0', '+', '+'],
   >           ['0', '0', '0', '0', '0', '+', '+', '0', '+'],
   >           ['+', '+', '0', '+', '+', '+', '+', '0', '0'],
   >           ['+', '+', '0', '0', '0', '0', '0', '0', '+'],
   >           ['+', '+', '0', '+', '+', '0', '+', '0', '+']]
   >
   > def findPassableLanes(board)
   >
   >
   > 2. 找出所有的exit。exit 是边上的0。有些exit是unreachable
   >   getNestEntranceCount(board1)
   >
   > 3. 给一个starting point， 找出最近的exit。就是简单的bfs

5. 2022(4-6月) 码农类General 硕士 全职@walmartlabs - Other - 技术电面 视频面试  | 😐 Neutral 😐 Average | Pass | 在职跳槽

   > 求大米求大米～～ 感谢大家求大米！！
   > Karat 总共60分钟，全程开摄像头。
   > 1.自我介绍
   >
   > 2. 10分钟cs知识快问快答，5个领域选2个回答，
   >
   > 我选了 production issue和test，还有front end，以及另外两个不记得了。。
   >
   >
   > 3. 45分钟做题，据说是做得越多多越好，我做了1道半，以为凉凉了，结果最后结果说通过了。
   >
   > 以下是其中一道题的题目，另一道题类似，都是比较基础的题目，需要把给的3个例子用main function跑过。然后再回答time， space复杂度。

6. 2025(10-12月) 码农类General 硕士 全职@walmartlabs - 猎头 - 技术电面  | 😐 Neutral 😐 Average | Pass | 其他

   > Karat能约的时间真的很多 大概是2周内每一天的都有slot
   > 上来是自我介绍大概一分钟 然后直接就开始问SpringBoot问题*6 并没有让我5选2
   >
   > 后续的coding是validate sudoku和validate nonogram 网上有的
   > 只是nonogram内部变成了char[][]

7. 2025(7-9月) 码农类General 本科 全职@walmartlabs - 网上海投 - 技术电面  | 😃 Positive 🙂 Easy | Pass | 其他

   > 1. Conceptual Question + Fundamentals Question like What is the purpose of the package.json file in a Node.js project? explain the prototype chain.
   > 2. 给一些JS代码然后说出output 顺序 + 修改和建议他给的代码
   > 3. 最后40分钟的LC style 题，他没有要求要解多少道题，楼主只做了一道类似password validation 的题目。

8. 2025(4-6月) 码农类General 硕士 全职@walmartlabs - 网上海投 - 在线笔试  | 😐 Neutral 😐 Average | Pass | 在职跳槽

   > 第一轮约的karat
   >
   > 上来先5选2的快问快答，有一个system的题直接说不会（composition vs inheritance），剩下五道题说出来了。
   > 然后是coding，是找最长play list的题，第二个follow up 没写完。
   > 以为挂了结果今天recuiter说过了，好神奇

9. 2024(7-9月) 码农类General 硕士 全职@walmartlabs - 网上海投 - 技术电面 视频面试  | 😃 Positive 😐 Average | Pass | 在职跳槽

   > 上周做了walmart的karat面试，形式是固定的：
   > 前5分钟自我介绍
   > 10分钟五类题目选两个快问快答，每一类各3道题
   > 最后45分钟coding，基本都是简书里原题，一共三道题，三道题是followup的形式，题干类似，做出一道才会给下一题，45分钟一般能把前两题做完，第三题写一点思路。面试官会在main函数里贴出所有测试数据，需要自己打印出运行结果，所有结果都正确就是通过。
   >
   > 楼主做了两次，由于第一次没好好看题库，第二道coding没跑成功。于是又redo了一次，顺利走到了第三道题。
   >
   > 两次的快问快答依稀记得的题目有：
   > Production issue类：
   > 1. 给一个图，图里cpu rate保持不变，ram rate随时间逐渐上升，解释这种现象
   > 2. 有一个服务，怎么测试它是否可以支持1000 request per second
   >
   > OOP类：
   > 1. Composition vs inheritance
   > 2. 选择上面任意一种的tradeoff是什么
   >
   > coding题目，第一次是简书里的nonogram那组题目，当时没做成功，事后回想，找出每一行/列的模式后，和给定的模式比较这样做会比较简单一些。
   > redo这次的题目是badge这道题，前两道题目简书里有，下面是第三道题的题干。这道题没想到什么好思路，当时时间也不多了，随便说了几句。

10. 2024(4-6月) 码农类General 硕士 全职@walmartlabs - Other - 技术电面  | 😐 Neutral 😐 Average | Other | 在职跳槽

    > - brief introduction
    > - OOPS question - composition vs inheritance , dependency injections
    > -  then coding : Got a question from here https://www.jianshu.com/p/fdbcba5fe5bc ( isValidMatrix) 

11. 2023(10-12月) 码农类General 硕士 全职@walmartlabs - 猎头 - HR筛选  | 😐 Neutral 😐 Average | Pass | 在职跳槽

    > recruiter 在linkedin 上面reach out 然后给我发了一个karat 面试流程。
    > karat 面试：
    >
    > 选了system internal 和 OOD  主要问了
    > 1.  线程和processor 区别
    >
    > 2.  GC 和内存方面问题 比如内存溢出
    > 3.  线程之前怎么传递变量
    > 4.  head 和 stack 区别
    > 5.  OOD 问题 地里面镜很多 都是差不多的  继承 封装 聚合那些东西。除了这个之外还问一个 dependence injection 这个蒙了
    >     coding：
    >     这个不太难 主要考你会不会coding 用一些数据结构。 medium 题目
    > 6.  统计不同学生之前 共享课程。    比如
    >   [1: "a","b"], [2: "a","d"]  [3: "f"] ---- >   ["1,2": "a"]  ["1,3": ""], ["2,3":""]
    > 7.  一个topology sort 问题 不难。  先找到indgree 为0的 然后开始从上往便利 最后返回中间的课程。
    >   比如 [a,b]  [b,c] [c,d] [d,e] --->  a‍‍‌‌‌‍‌‌‍‌‍‌‍‍‍‍‌‍ -- b ---c --- d -- e   返回c
    >
    > 只记得这些事比较难的，其他简单的很容易答，还可以从几个种类中选自己擅长的
    > OOD 怎么简化写function
    > 各种不同的data structure特点，以及什么时候用什么data structure
    > race condition

12. 2022(7-9月) 码农类General 硕士 全职@walmartlabs - 网上海投 - 技术电面 Onsite  | 😃 Positive 🙂 Easy | Pass | 在职跳槽

    > 最近面了walmart，发个帖子供大家参考。
    >
    > 1. 电面，通过karat来面试。
    >
    > 题目是5选2基础知识，我选了OOD和test，大概的几个问题是override vs overload， polymorphism概念，给看一段代码，告诉他在deploy之前需要做些什么，答写unit test cover 所有条件。
    > 然后是做题，题目1 是karat面经中的string pattern, Find the first word in an array of words that matches a string pattern.
    > 题目2 是word search。 都不难。
    >
    > 2 VO
    > 他家VO就只有两轮，
    > 第一轮是1.5个小时，主要对你的简历考查tech dive deep，然后做一个system design， 题目是设计一个netflix。
    > 第二轮是1小时，HM考察behavior question。
    >
    > 如果有帮助请加米，谢谢！

13. [面试经验] Indeed/确实面经--Karat面试    

    > 前两天刚参加完Indeed Karat的电面，确实问的大多数都是地里面经的题目，楼主也来分享一下面试准备的题，希望能够帮助到大家，以及楼主新人很多帖子看不了，请大家大米支持下哈！
    > 流程：
    > * Self-Introduction (~ 1 min)
    > * Q & A (~ 10 min) (choose 2 fields, about 3 problems in every field)
    > * Coding (2 ~ 3 problems) with time and space complexity analysis
    >
    > Q & A 环节: （楼主投的是后端工程师，给的这些领域，地里有小伙伴说可能还有其他的类型？）
    > 1. Web application
    >     Building user interfaces for web services and sites
    > 2. Production issues
    >     Diagnosing problems with production services
    > 3. System internals
    >     How computers manage and allocate resources like CPU and memory
    >     4.OOP
    >     Best practices for building classes and systems of classes
    >     5.Testing
    >     Validating code and related concerns
    >     五选二，楼主之前准备的是System internals和OOP，总体上就是根据自己的准备知识，尽量说就可以了。
    >     贴一下楼主尽可能整理的问题和答案（不一定很准确，大家参考着自己习惯的知识上靠吧），希望能够帮助大家：
    >     System Internals:
    >     1 Graph(CPU usage, RAM usage), 大概就是随着time，CPU资源消耗不变, increase; How to detect; What could cause that; How to resolve?
    >     maybe memory leak, try to find
    >     2 Memory Leak, how to solve?
    >
    > Memory Leak: some objects are referenced, but not used
    >     Symptoms of a Memory leak: Works fast at first, but slows over time; OutOfMemoryError after runing; There are occasionally crashes in the applications.
    >     Why
    >        1. Memory Leak Through static Fields
    >        2. Through Unclosed Resources
    >        3. Improper equals() and hashCode() Implementations
    >     Handle:
    >        1. Enable Profiling: Java profilers are tools that monitor and diagnose the memory leaks through the application.
    >        2. Verbose Garbage Collection: enable verbose garbage collection to track detailed trace of GC
    >        3. use tools like Eclipse to show warnings and errors whenever it encounters obvious cases of memory leaks
    >        4. use benchmarks to measure and analyze the Java code's performance after changed code.
    >        5. Code Reviews
    > 3 Connection Pool Timeout
    >
    > 以下内容需要积分高于 150 您已经可以浏览
    >
    > This value indicates the number of seconds that a connection request waits when there are no connections available in the free pool and no new connections can be created.
    >     Handle: Check connection pool configurations and logs about connection use/idle messages; also check code for correct usage and relasing.
    >
    >
    > 4 Thread Exhausion
    >
    > 以下内容需要积分高于 150 您已经可以浏览
    >
    > Thread exhaustion arise when you generate more tasks than your ThreadPool can handle.
    >     Handle: Use Asynchronous code (including async/await) with fewer threads to avoid blocking threads.
    >
    >
    > 5 How do you know if you can handle 1k requests per second (now it's 1 request per second)
    > 以下内容需要积分高于 150 您已经可以浏览
    >
    > load test with mocked production data
    >
    >
    > 6 操作系统中进程和线程的区别。
    > Both processes and threads are independent sequences of execution. The typical difference is that threads (of the same process) run in a shared memory space, while processes run in separate memory spaces.
    > Each process provides the resources needed to execute a program. Each process is started with a single thread, often called the primary thread, but can create additional threads from any of its threads.
    > A thread is an entity within a process that can be scheduled for execution.
    > 7 What is garbage collection
    > Once an object is no longer referenced and therefore is not reachable by the application code, the garbage collector removes it and reclaims the unused memory.
    > 8 stack vs heap
    > 以下内容需要积分高于 150 您已经可以浏览
    >
    > Heap memory is used by all the parts of the application whereas stack memory is used only by one thread of execution. When an object is created, it's mostly stored in the Heap space and stack memory contains the reference to it.
    >
    >
    > 9 How to communicate between threads? How to communicate between processes?
    >
    > 以下内容需要积分高于 150 您已经可以浏览
    >
    > Thread communicate via shared memory. The information is exchanged over objects by locking and notification.
    > The communication of the different processes with each other on the same machine use the IPC (inter process communication). IPC have different mechanism PIPE, FIFO, message queue, semaphore, shared memory etc.
    >
    >
    > 10 What is the difference between garbage collection and 传统的内存管理方法?
    >
    > 以下内容需要积分高于 150 您已经可以浏览
    >
    > Explain Garbage collection, then
    > Garbage collection can automatically handle the deletion of unused objects or some objects that are inaccessible to free up memory resources.
    > In traditional forms of memory management, programmers need to do this by themselves.
    >
    >
    > 11 给一个场景不太适合使用garbage collection模型去管理内存
    > 以下内容需要积分高于 150 您已经可以浏览
    >
    > Online services which need low latency and keep communication with users(no "stop the world" time interval)
    >
    > 
    >
    > Object-oriented design:
    > 1. 简单描述下composition 跟inheritance 以及他们的区别。
    > Inheritance is an "is-a" relationship. Composition is a "has-a" relationship.
    > Composition and Inheritance both provide code reusability by relating class.
    > Inheritance derives one class from another one class, composition can have an instance of another class as a field.
    > 2.What is dependency injection(DI)? 对管理软件有什么好处?
    >
    > 以下内容需要积分高于 150 您已经可以浏览
    >
    > Dependency Injection is a technique where an object receives other objects that it depends on.
    > DI is a design pattern used to implement IoC. By DI, the responsibility of creating objects is shifted from our application code to the Spring container; this phenomenon is called Inversion of Control (IoC). The injection can be constructor injection(recommended) or setter injection or field injection.
    > Advantages: Dependency injection makes testing easier and DI is an approach to implement loose coupling among the classes in an application.
    > Disadvantages: Code is difficult to trace and read in Dependency Injection, it increases complexity in the linkages between classes.
    >
    >
    > 3.Polymorphism
    > The word polymorphism means having many forms. It describes the concept that different classes can be used with the same interface.
    > Advantages: It helps the programmer to reuse the codes
    > Disadvantages: Polymorphism reduces the readability of the program and runtime polymorphism can lead to the performance issue as machine needs to decide which method or variable to invoke
    > 4.what is the most important object oriented programming principle for complex system
    > Four basic concept:
    > Abstraction: shows only the necessary details to the client of an object
    > Encapsulation: process of enclosing one or more items within a physical or logical package
    > Inheritance: allows us to define a class in terms of another class
    > Polymorphism: an instance of something in various forms
    > The five principles are as follows:
    > * S – Single Responsibility Principle (SRP): “A class should have one, and only one, reason to change.”
    > * O – Open Closed Principle (OCP): “You should be able to extend a classes behavior, without modifying.”
    > * L – Liskov Substitution Principle (LSP): “Derived classes must be substitutable for their base classes.”
    > * I – Interface Segregation Principle (ISP): “Clients should not be forced to depend upon interfaces that they don't use.”
    > * D – Dependency Inversion Principle (DIP): “Depend on abstraction, not on concretions.”
    > 5.如果向Collection framework里面加入一个新的class. 你打算加上哪些文档?
    > 以下内容需要积分高于 150 您已经可以浏览
    >
    > 跟一般API文档相比，看重的是细节，比如是否修改原数据，返回数据是否有序，接受input的range，是不是线程safe，时间空间效率等等
    >
    >
    > 6.公司实现了一个数据结构，你觉得documentation里头要写啥比较好，随便讲
    >
    > 以下内容需要积分高于 150 您已经可以浏览
    >
    > @author（只出现在类和接口的文档中）
    > @version（只出现在类和接口的文档中）
    > @param（只出现在方法或构造器的文档中）
    > @return（只出现在方法中）
    > @exception（从java1.2之后也可以使用@thrown替代）
    > @see
    > @since
    > @serial（也可以使用@serialField或@serialData替代）
    > @deprecated
    > more examples
    >
    >
    > 7.undo/redo , which data structure is good for this?
    > stack
    > 8.What is the main difference between overloading and overriding?
    > Overloading is static Binding, whereas Overriding is dynamic Binding. Overloading is nothing but the same method with different arguments, and it may or may not return the equal value in the same class itself.
    > Overriding is the same method names with the same arguments and return types associated with the class and its child class.
    > 9. What is Static Binding and Dynamic Binding?
    > Binding is nothing but the association of a name with the class. Static Binding is a binding in which name can be associated with the class during compilation time, and it is also called as early Binding.
    > Dynamic Binding is a binding in which name can be associated with the class during execution time, and it is also called as Late Binding.
    > 10.When would you use composition and inheritance over the other, what are some of the tradeoffs?
    >
    > 以下内容需要积分高于 150 您已经可以浏览
    >
    > Does TypeB want to expose the complete interface (all public methods no less) of TypeA such that TypeB can be used where TypeA is expected? Indicates Inheritance. "is-a" relationship
    > Does TypeB want only some/part of the behavior exposed by TypeA? Indicates need for Composition. "has-a" relationship
    >
    > 
    >
    > 编程题：
    > 楼主面的是Calculator，大家可以参考地里的面经资料寻找答案，或者自己练一下
    > 1) 给一个String只有数字，+和-，比如43+2-2，返回计算结果
    > 2) 第一题的follow up，这次加了括号，比如(33-3)+((8-3)-(3-88))，返回计算结果 (LT 224)
    > 3) 第二题的follow up，这次String里会有variable，比如（apple-4)+orange-(banana+3)，然后给一个map，{{banana: 4}, {apple : 9}}，不是所有的variable都有值，这个例子就会返回5+orange-7。
    >
    > 最后祝大家面试顺利哈！

14. 123

    > ## 快问快答：
    >
    > OOP:
    >
    > 1. Composition vs Inheritance - difference, when would you use which? Advantage, disadvantage
    >
    > Inheritance and composition are two programming techniques developers use to establish relationships between claseritance and compositionses and objects. Whereas **inheritance derives one class from another, composition defines a class as the sum of its parts**.
    >
    > **Difference:**
    >
    > **Inheritance** offers a way to **reuse code by extending a class easily and with little effort.**
    >
    > In situations where you want to **use an object as a field within another class**, you should use **composition**.
    >
    > 1. what is dependency injection? how to implement it and what are advantages of it.
    >
    > A pattern we use to implement IoC to increase modularity. Means the Spring container injects objects into other objects or dependencies. It allows for loose coupling of components and moves the responsibility of managing components onto the container.
    >
    > 1. Polymorphism
    >
    > Polymorphism in Java is a concept by which we can perform a single action in different ways. There are two types of polymorphism in Java: compile-time polymorphism and runtime polymorphism. They are realized by method overloading and method overriding.
    >
    > 1. Documentation for company
    >
    > We use Swagger
    >
    > some annotations used for documentation:
    >
    > @Api: Marks a class as a Swagger resource.
    >
    > @ApiParam: Adds additional meta-data for operation parameters.
    >
    > @ApiResponse: Describes a possible response of an operation.
    >
    > @ApiOperation: Describes an operation or typically a HTTP method against a specific path.
    >
    > Test:h
    >
    > 1. When would you **mock**? Unit Test vs Integration Test
    >
    > **Mock** means creating a fake version of an external or internal service that can stand in for the real one. 
    >
    > **Unit testing** means testing individual modules of an application in isolation (without any interaction with dependencies) to confirm that the code is doing things right. 
    >
    > **Integration testing** means checking if different modules are working fine when combined together as a group.**Integration tests are a fundamental part of automated test suites.**
    >
    > 1. How to reduce system load and startup when doing integration tests
    >    1. Using profiles wisely – how profiles impact performance
    >    2. Reconsidering *@MockBean –* how mocking hits performance
    >    3. Refactoring *@MockBean* – alternatives to improve performance
    >    4. Thinking carefully about @*DirtiesContext –* a useful but dangerous annotation and how not to use it
    >    5. Using test slices – a cool tool that can help or get on our way
    >    6. Using class inheritance – a way to organize tests in a safe manner
    >    7. State management – good practices to avoid flakey tests
    >    8. Refactoring into unit tests – the best way to get a solid and snappy build
    > 2. How would you test for specific scenarios (Don't exactly remember specific scenario)
    >
    > -I said test for happy path, test for negatives, input errors etc.
    >
    > 1. what is mock, when to use and when not to use
    >
    > **Mocking** is a way to replace a dependency in a unit under test with a stand-in for that dependency.
    >
    > **When to use**: when testing things that cross the dependency inversion boundaries of the system
    >
    > 1. you have created a new data structure and you have documented all method signatures what other things will you include in documentation apart from method signature details.
    >
    > Documentation for Java class:
    >
    > 1. Method functionality
    > 2. Return type and what it is
    > 3. Parameters in the methods