### 1. Pillars of OOP

> - **Inheritance(继承)** 
>
>   > Inheritance is an important pillar of OOP (Object Oriented Programming). It is the mechanism in Java by which one class is allowed to inherit the features (fields and methods) of another class. We are achieving inheritance by using ***\*extends\**** keyword. Inheritance is also known as “***\*is-a\****” relationship.
>   >
>   > 继承是面向对象的三大特征之一，也是实现代码复用的重要手段。Java的继承具有单继承特点，每个子类只有一个直接父类。 
>
> - **Encapsulation(封装)** 
>
>   > It is defined as the wrapping up of data under a single unit. It is the mechanism that binds together the code and the data it manipulates. Another way to think about encapsulation is that it is a protective shield that prevents the data from being accessed by the code outside this shield. 
>   >
>   > 封装就是把属于同一类事物的共性（包括属性与方法）归到一个类中，以方便使用。
>   >
>   > - 封装也称为信息隐藏，是指利用抽象数据类型将数据和基于数据的操作封装在一起，使其构成一个不可分割的独立实体，数据被保护在抽象数据类型的内部，尽可能地隐藏内部的细节，只保留一些对外接口使之与外部发生联系。 
>   > - 系统的其他部分只有通过包裹在数据外面的被授权的操作来与这个抽象数据类型交流与交互。也就是说，用户无需知道对象内部方法的实现细节，但可以根据对象提供的外部接口(对象名和参数)访问该对象。
>
> - **Polymorphism(多态)** 
>
>   > It refers to the ability of object-oriented programming languages to differentiate between entities with the same name efficiently. This is done by Java with the help of the signature and declaration of these entities. 
>   >
>   > 多态的概念发展出来，是以封装和继承为基础的。 多态就是在抽象的层面上实施一个统一的行为，到个体（具体）的层面上时，这个统一的行为会因为个体（具体）的形态特征而实施自己的特征行为
>   >
>   > 实现多态的技术称为：   动态绑定（dynamic binding），是指在执行期间判断所引用对象的实际类型，根据其实际的类型调用其相应的方法。
>   >
>   > 多态存在的三个必要条件    一、要有继承；    二、要有重写；    三、父类引用指向子类对象。
>   >
>   > **多态的作用**
>   >
>   > - 消除类型之间的耦合关系。
>   > - 可替换性（substitutability)多态对已存在代码具有可替换性。例如，多态对圆Circle类工作，对其他任何圆形几何体，如圆环，也同样工作。
>   > - 可扩充性（extensibility）多态对代码具有可扩充性。增加新的子类不影响已存在类的多态性、继承性，以及其他特性的运行和操作。实际上新加子类更容易获得多态功能。 例如，在实现了圆锥、半圆锥以及半球体的多态基础上，很容易增添球体类的多态性。
>   > - 接口性（interface-ability）多态是超类通过方法签名，向子类提供了一个共同接口，由子类来完善或者覆盖它而实现的
>
> - **Abstraction(抽象)** 
>
>   > Data Abstraction is the property by virtue of which only the essential details are displayed to the user. The trivial or non-essential units are not displayed to the user. 
>   >
>   > In Java, abstraction is achieved by [interfaces](https://www.geeksforgeeks.org/interfaces-in-java/) and [abstract classes](https://www.geeksforgeeks.org/abstract-classes-in-java/). We can achieve 100% abstraction using interfaces.
>   >
>   > - 要定义一个方法和类。但还没确定怎么去实现它的具体一点的子方法，那我就可以用抽象类或接口。 具体怎么用，要做什么，我不用关心，由使用的人自己去定义去实现。 
>   > - 提高程序的复用率，增加程序 的可维护性，可扩展性，就必须是面向接口的编程，面向抽象的编程， 正确地使用接口、抽象类这些太有用的抽象类型做为你结构层次上的顶层。

### 2. OOD 流程

> - **Clarify**
>
>   > 通过和面试官交流，去除题目中歧义，确定答题范围，使用What， How 语句去明确需求。
>
> - **Core objects**
>
>   > 确定题目所涉及的类(**题目中的关键名词即为一个类，考虑类的属性**)，以及类之间的映射关系
>   >
>   > 以一个Object作为基础，根据场景线性思考，找出所有的Object，以及彼此之间映射关系
>   >
>   > **Core Objects 这步的作用是：**
>   >
>   > - 帮助后续的设计明确你需要哪些类；
>   > - 它能承上启下，来自于clarify的结果，又成为use case的依据；
>   > - 同时还为画类图打下基础
>
> - **Cases**
>
>   > 确定题目中所需要实现的场景和功能，把你**要实现的功能列出来**；同时也能帮你**理清思路**，实现一个一个Case；最后再**作为检查的标准**。每个use case只需用一句话描述就行了。
>   >
>   > - 利用定义的Core Object, 列举出每个Object对应产生的use case.
>   > - 每个use case只需要先用一句简单的话来描述即可
>
> - **Classes**
>
>   > OOD面试很重要的部分就是画类图，通过类图的方式，具体填充题目中设 计的类。 它建立在use case基础上，条理清晰，便于交流和修改， 节约面试时间，不容易在coding上挣扎。怎么画类图
>   >
>   > - 遍历你所列出的use cases
>   >
>   > - 对于每一个use case，更加详细的描述这个use case在做什么事情 (例如:take external request -> ElevatorSystem takes an external request, and decide to push this request to an appropriate elevator)
>   > - 针对这个描述，在已有的Core objects里填充进所需要的信息
>   >
>   > | **Class Name** |
>   > | :------------- |
>   > | Attributes     |
>   > | Functions      |
>
>
> - **Correctness**
>
> > 检查自己的设计，是否满足关键点

### 3. OOD 分类

> - **管理类**
>
>   > 特征：
>   >
>   > > - Gym 体育馆 管理员
>   > > - Parking lot 停车场 管理员
>   > > - Restaurant 餐厅 管理员
>   > > - Library 图书馆 管理员
>   > > - Super market 超市 管理员
>   > > - Hotel •... 宾馆 管理员
>   > >
>   > > 题目后面都可以接上三个字： 管理员
>   >
>   > 题目后面都可以接上三个字： 管理员（management）， 设计一个模拟/代替管理员日常工作的系统
>   >
>   > - **Clarify**
>   >
>   >   > 通过和面试官交流，去除题目中歧义，确定答题范围， 使用What， How 语句去明确需求。
>   >   > 除了题目中问的名词外，还需要从管理的名词来考虑
>   >
>   > - **Core Object**
>   >
>   >   > 确定题目所涉及的类(**题目中的关键名词即为一个类，考虑类的属性 **)，以及类之间的映射关系
>   >   >
>   >   > 以一个Object作为基础，考虑这个管理系统中，Input和Output是什么, 根据场景线性思考，找出所有的Object，以及彼此之间映射关系
>   >   >
>   >   > **Core Objects 这步的作用是：**
>   >   >
>   >   > - 帮助后续的设计明确你需要哪些类；
>   >   > - 它能承上启下，来自于clarify的结果，又成为use case的依据；
>   >   > - 同时还为画类图打下基础
>   >
>   > - **Use Case**
>   >
>   >   > 确定题目中所需要实现的场景和功能，把你**要实现的功能列出来**；同时也能帮你**理清思路**，实现一个一个Case；最后再**作为检查的标准**。每个use case只需用一句话描述就行了。
>   >   >
>   >   > - 利用定义的Core Object, 列举出每个Object对应产生的use case.
>   >   > - 每个use case只需要先用一句简单的话来描述即可
>   >   >
>   >   > - 从管理员角度考虑
>   >   >
>   >   > Management类常见Use case:
>   >   >
>   >   > - Reservation : X
>   >   > - Serve: Park vehicle
>   >   > - Check out: Clear spot + Calculate price
>   >
>   > - **Class**
>   >
>   >   > OOD面试很重要的部分就是画类图，通过类图的方式，具体填充题目中设 计的类。 它建立在use case基础上，条理清晰，便于交流和修改， 节约面试时间，不容易在coding上挣扎。怎么画类图
>   >   >
>   >   > - 遍历你所列出的use cases
>   >   >
>   >   > - 对于每一个use case，更加详细的描述这个use case在做什么事情 (例如:take external request -> ElevatorSystem takes an external request, and decide to push this request to an appropriate elevator)
>   >   > - 针对这个描述，在已有的Core objects里填充进所需要的信息
>
> - **预定类**
>
>   > 特征：
>   >
>   > > - Vending machine
>   > > - Jukebox
>   > > - CD Player
>   > > - Coffee maker
>   > > - Kindle
>   >
>   > - **Clarify**
>   >
>   >   > 通过和面试官交流，去除题目中歧义，确定答题范围， 使用What， How 语句去明确需求。
>   >   > 除了题目中问的名词外，还需要从管理的名词来考虑预定的东西
>   >
>   > - **Core Object**
>   >
>   >   > 确定题目所涉及的类(**题目中的关键名词即为一个类，考虑类的属性 **)，以及类之间的映射关系
>   >   >
>   >   > 以一个Object作为基础，考虑这个管理系统中，Input和Output是什么, 根据场景线性思考，找出所有的Object，以及彼此之间映射关系
>   >   >
>   >   > **Core Objects 这步的作用是：**
>   >   >
>   >   > - 帮助后续的设计明确你需要哪些类；
>   >   > - 它能承上启下，来自于clarify的结果，又成为use case的依据；
>   >   > - 同时还为画类图打下基础
>   >
>   > - **Use Case**
>   >
>   >   > 确定题目中所需要实现的场景和功能，把你**要实现的功能列出来**；同时也能帮你**理清思路**，实现一个一个Case；最后再**作为检查的标准**。每个use case只需用一句话描述就行了。
>   >   >
>   >   > - 利用定义的Core Object, 列举出每个Object对应产生的use case.
>   >   > - 每个use case只需要先用一句简单的话来描述即可
>   >   >
>   >   > - 从管理员角度考虑
>   >   >
>   >   > 预约类常见Use case:
>   >   >
>   >   > - Search criteria -> Search() -> List<Result> -> Select() -> Receipt
>   >   > - Result == Timeslot/Room 。。。
>   >
>   > - **Class**
>   >
>   >   > OOD面试很重要的部分就是画类图，通过类图的方式，具体填充题目中设 计的类。 它建立在use case基础上，条理清晰，便于交流和修改， 节约面试时间，不容易在coding上挣扎。怎么画类图
>   >   >
>   >   > - 遍历你所列出的use cases
>   >   >
>   >   > - 对于每一个use case，更加详细的描述这个use case在做什么事情 (例如:take external request -> ElevatorSystem takes an external request, and decide to push this request to an appropriate elevator)
>   >   > - 针对这个描述，在已有的Core objects里填充进所需要的信息
>
> - 实物类
>
>   > 特征：
>   >
>   > > 需要预定的东西 Search criteria -> Search() -> List<Result> -> Select() -> Receipt
>   >
>   > - **Clarify**
>   >
>   >   > 通过和面试官交流，去除题目中歧义，确定答题范围， 使用What， How 语句去明确需求。
>   >   > 除了题目中问的名词外，还需要从管理的名词来考虑预定的东西
>   >
>   > - **Core Object**
>   >
>   >   > 确定题目所涉及的类(**题目中的关键名词即为一个类，考虑类的属性 **)，以及类之间的映射关系
>   >   >
>   >   > 以一个Object作为基础，考虑这个管理系统中，Input和Output是什么, 根据场景线性思考，找出所有的Object，以及彼此之间映射关系
>   >   >
>   >   > **Core Objects 这步的作用是：**
>   >   >
>   >   > - 帮助后续的设计明确你需要哪些类；
>   >   > - 它能承上启下，来自于clarify的结果，又成为use case的依据；
>   >   > - 同时还为画类图打下基础
>   >
>   > - **Use Case**
>   >
>   >   > 确定题目中所需要实现的场景和功能，把你**要实现的功能列出来**；同时也能帮你**理清思路**，实现一个一个Case；最后再**作为检查的标准**。每个use case只需用一句话描述就行了。
>   >   >
>   >   > - 利用定义的Core Object, 列举出每个Object对应产生的use case.
>   >   > - 每个use case只需要先用一句简单的话来描述即可
>   >   >
>   >   > - 从管理员角度考虑
>   >   >
>   >   > 预约类常见Use case:
>   >   >
>   >   > - Search criteria -> Search() -> List<Result> -> Select() -> Receipt
>   >   > - Result == Timeslot/Room 。。。
>   >
>   > - **Class**
>   >
>   >   > OOD面试很重要的部分就是画类图，通过类图的方式，具体填充题目中设 计的类。 它建立在use case基础上，条理清晰，便于交流和修改， 节约面试时间，不容易在coding上挣扎。怎么画类图
>   >   >
>   >   > - 遍历你所列出的use cases
>   >   >
>   >   > - 对于每一个use case，更加详细的描述这个use case在做什么事情 (例如:take external request -> ElevatorSystem takes an external request, and decide to push this request to an appropriate elevator)
>   >   > - 针对这个描述，在已有的Core objects里填充进所需要的信息
>
> - **游戏类**
>
>   > 特征：
>   >
>   > > - 棋类
>   > >   - 象棋，国际象棋，围棋，军旗，跳棋，五子棋 ... 
>   > > - 类棋类
>   > >   - Tic Tac Toe, 扫雷
>   > > - 牌类
>   > >   - Black jack, 德州扑克, 斗地主, 狼人杀
>   > >
>   > > 棋牌类的特点:
>   > >
>   > > \- 玩家 - 规则 - 胜负 - 积分
>   > >
>   > > 针对棋牌类的状态，来做Use cases
>   >
>   > - **Clarify**
>   >
>   >   > 通过和面试官交流，去除题目中歧义，确定答题范围， 使用What， How 语句去明确需求。
>   >   > 除了题目中问的名词外，还需要从管理的名词来考虑预定的东西
>   >   >
>   >   > - 玩家
>   >   >   - 是否需要专门的Player类（有积分）
>   >   >   - Player之间有什么区别
>   >   > - 规则
>   >   >   - 怎么玩
>   >   > - 胜负
>   >   >   - 确认胜负规则
>   >   > - 积分
>   >
>   > - **Core Object**
>   >
>   >   > 确定题目所涉及的类(**题目中的关键名词即为一个类，考虑类的属性 **)，以及类之间的映射关系
>   >   >
>   >   > 以一个Object作为基础，考虑这个管理系统中，Input和Output是什么, 根据场景线性思考，找出所有的Object，以及彼此之间映射关系
>   >   >
>   >   >  参考棋牌类的专业名词来考虑 获取全新2022版
>   >   >
>   >   > - Board
>   >   >
>   >   > - Suit
>   >   > - Hand 
>   >   > - Move -...
>   >
>   > - **Use Case**
>   >
>   >   > 确定题目中所需要实现的场景和功能，把你**要实现的功能列出来**；同时也能帮你**理清思路**，实现一个一个Case；最后再**作为检查的标准**。每个use case只需用一句话描述就行了。
>   >   >
>   >   > 棋牌类的状态:一局棋牌，分为哪些状态(State)
>   >   >
>   >   > - Initialization (摆盘，洗牌...)
>   >   > - Play (下棋，出牌...)
>   >   > - Win/Lose check (胜负结算) + Tie (流局)
>   >
>   > - **Class**
>   >
>   >   > OOD面试很重要的部分就是画类图，通过类图的方式，具体填充题目中设 计的类。 它建立在use case基础上，条理清晰，便于交流和修改， 节约面试时间，不容易在coding上挣扎。怎么画类图
>   >   >
>   >   > - 遍历你所列出的use cases
>   >   >
>   >   > - 对于每一个use case，更加详细的描述这个use case在做什么事情 (例如:take external request -> ElevatorSystem takes an external request, and decide to push this request to an appropriate elevator)
>   >   > - 针对这个描述，在已有的Core objects里填充进所需要的信息



