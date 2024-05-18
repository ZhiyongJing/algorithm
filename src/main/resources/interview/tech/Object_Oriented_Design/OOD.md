### 1. OOD Principle

> OOD should follow SOLID principle.
>
> - S – Single responsibility principle(单一责任原则)
>
>   > 一个类应该有且只有一个去改变他的理由，这意味着一个类应该只有一项工作。
>   >
>   > **对类来说的，即一个类应该只负责一项职责**，比如UserDao类，只负责user表的CRUD。
>   >
>   > 如类A负责两个不同职责：职责 1，职责 2。当职责 1 需求变更而改变A时，可能造成职责 2 执行错误，所以需要将类A的粒度分解为A1，A2，也就是将A类分解为A1，A2两个类。 比如让一个CommonDao同时负责user表和order表的CRUD，这样就不符合单一职责原则，应该吧CommonDao分为UserDao和OrderDao这两个类。
>   >
>   > ```java
>   > public class Invoice {//创建一个 invoice class，包含创建发票和计算总额的业务逻辑。
>   > 	private Book book;
>   > 	private int quantity;
>   > 	private double discountRate;
>   > 	private double taxRate;
>   > 	private double total;
>   > 
>   > 	public Invoice(Book book, int quantity, double discountRate, double taxRate) {
>   > 		this.book = book;
>   > 		this.quantity = quantity;
>   > 		this.discountRate = discountRate;
>   > 		this.taxRate = taxRate;
>   > 		this.total = this.calculateTotal();
>   > 	}
>   > 
>   > 	public double calculateTotal() {
>   >  double price = ((book.price - book.price * discountRate) * this.quantity);
>   >  double priceWithTaxes = price * (1 + taxRate);
>   > 		return priceWithTaxes;
>   > 	}
>   > }
>   > 
>   > public class InvoicePrinter {//只负责打印
>   >  public void print(Invoice invoice) {
>   >      System.out.println(
>   >        invoice.quantity + "x " + invoice.book.name + " " +invoice.book.price + " $");
>   >      System.out.println("Discount Rate: " + invoice.discountRate);
>   >      System.out.println("Tax Rate: " + invoice.taxRate);
>   >      System.out.println("Total: " + invoice.total + " $");
>   >  }
>   > }
>   > 
>   > public class InvoicePersistence {//只负责存储
>   >  public void saveToFile(String filename， Invoic invoice) {
>   >      // Creates a file with given name and writes the invoice
>   >  }
>   > }
>   > ```
>
> - O – Open close principle(开放封闭原则)
>
>   > 对象或实体应该对扩展开放，对修改封闭 (Open to extension, close to modification)。
>   >
>   > 我们应该能在不动 class 已经存在代码的前提下添加新的功能。这是因为当我们修改存在的代码时，我们就面临着创建潜在 bug 的风险。因此，应该避免碰通过测试的可靠的生产环境的代码。**接口和抽象类**可以做到不动 class 还能添加新功能
>   >
>   > ```java
>   > interface InvoicePersistence {// 适用interface 保证不用更改原class代码去添加新功能
>   >     public void save(Invoice invoice);
>   > }
>   > public class DatabasePersistence implements InvoicePersistence {
>   >     @Override
>   >     public void save(Invoice invoice) {
>   >         // Save to DB
>   >     }
>   > }
>   > public class FilePersistence implements InvoicePersistence {
>   >     @Override
>   >     public void save(Invoice invoice) {
>   >         // Save to file
>   >     }
>   > }
>   > ```
>
> - L – Liskov substitution principle(里氏替换原则)
>
>   > 里氏替换原则帮我们规定了**如何正确的使用继承关系**
>   >
>   > 1. 如果对每个类型为 T1 的对象 o1，都有类型为 T2 的对象 o2，使得以 T1 定义的所有程序 P 在所有的对象 o1 都 代换成 o2 时，程序 P 的行为没有发生变化，那么类型T2 是类型 T1 的子类型。换句话说，**所有引用基类的地方必须能透明地使用其子类的对象**。
>   > 2. 在使用继承时，遵循里氏替换原则，在**子类中尽量不要重写父类的方法**
>   > 3. 里氏替换原则告诉我们，继承实际上让两个类耦合性增强了，在**适当的情况下，可以通过聚合，组合，依赖 来 解决问题**。
>   >
>   > 子类应该能替换为它的父类。意思是，给定 class B 是 class A 的子类，在预期传入 class A 的对象的任何方法传入 class B 的对象，方法都不应该有异常.
>   >
>   > ```java
>   > package com.atguigu.principle.liskov.improve;
>   > 
>   > public class Liskov {
>   > 
>   > 	public static void main(String[] args) {
>   > 		// TODO Auto-generated method stub
>   > 		A a = new A();
>   > 		System.out.println("11-3=" + a.func1(11, 3));
>   > 		System.out.println("1-8=" + a.func1(1, 8));
>   >         
>   > 		System.out.println("-----------------------");
>   > 		B b = new B();
>   > 		//因为B类不再继承A类，因此调用者，不会再func1是求减法
>   > 		//调用完成的功能就会很明确
>   > 		System.out.println("11+3=" + b.func1(11, 3));//这里本意是求出11+3
>   > 		System.out.println("1+8=" + b.func1(1, 8));// 1+8
>   > 		System.out.println("11+3+9=" + b.func2(11, 3));
>   >         
>   > 		//使用组合仍然可以使用到A类相关方法
>   > 		System.out.println("11-3=" + b.func3(11, 3));// 这里本意是求出11-3
>   > 	}
>   > }
>   > 
>   > //创建一个更加基础的基类
>   > class Base {
>   > 	//把更加基础的方法和成员写到Base类
>   > }
>   > 
>   > // A类
>   > class A extends Base {
>   > 	// 返回两个数的差
>   > 	public int func1(int num1, int num2) {
>   > 		return num1 - num2;
>   > 	}
>   > }
>   > 
>   > // B类继承了A
>   > // 增加了一个新功能：完成两个数相加,然后和9求和
>   > class B extends Base {
>   > 	//如果B需要使用A类的方法,使用组合关系
>   > 	private A a = new A();
>   > 	
>   > 	//这里，重写了A类的方法, 可能是无意识
>   > 	public int func1(int a, int b) {
>   > 		return a + b;
>   > 	}
>   > 
>   > 	public int func2(int a, int b) {
>   > 		return func1(a, b) + 9;
>   > 	}
>   > 	
>   > 	//我们仍然想使用A的方法
>   > 	public int func3(int a, int b) {
>   > 		return this.a.func1(a, b);
>   > 	}
>   > }
>   > ```
>   >
>   > 
>
> - I – Interface segregation principle(接口分离原则)
>
>   > 不应该强迫一个类实现它用不上的接口。该原则描述了很多客户端特定的接口优于一个多用途接口。客户端不应该强制实现他们不需要的函数。客户端不应该依赖它不需要的接口，即一个类对另一个类的依赖应该建立在最小的接口上。
>   > ```java
>   > public interface ParkingLot {
>   >   //停车场接口组合了两个事情：停车相关逻辑（停车、取车、获取车位信息）以及支付相关逻辑. 但是太具体了。
>   >   //即使是 FreeParking class 也要必须实现不相关的支付相关的方法。让我们隔离接口。
>   > 	void parkCar();	// Decrease empty spot count by 1
>   > 	void unparkCar(); // Increase empty spots by 1
>   > 	void getCapacity();	// Returns car capacity
>   > 	double calculateFee(Car car); // Returns the price based on number of hours
>   > 	void doPayment(Car car);
>   > }
>   > ```
>   >
>   > 
>
> - D – Dependency inversion principle(依赖反转原则)
>
>   >  抽象不应该依赖于具体实现，具体实现应该依赖于抽象， High-level的实体不应该依赖于low-level的实体。我们想要我们的类开放扩展，**因此我们需要明确我们的依赖的是接口而不是具体的类**。
>   >
>   >  - 高层模块不应该依赖低层模块，二者都应该依赖其抽象
>   >  - **抽象不应该依赖细节，细节应该依赖抽象**
>   >  - 依赖倒转(倒置)的中心思想是**面向接口编程**
>   >  - 依赖倒转原则是基于这样的设计理念：相对于细节的多变性，抽象的东西要稳定的多。以抽象为基础搭建的架 构比以细节为基础的架构要稳定的多。在 java 中，抽象指的是接口或抽象类，细节就是具体的实现类
>   >  - 使用接口或抽象类的目的是**制定好规范**，而不涉及任何具体的操作，把展现细节的任务交给他们的实现类去完 成
>   >
>   >  ```java
>   >  public class DependecyInversion {
>   >  	public static void main(String[] args) {
>   >  		//客户端无需改变
>   >  		Person person = new Person();
>   >  		person.receive(new Email());
>   >  		person.receive(new WeiXin());
>   >  	}
>   >  
>   >  }
>   >  
>   >  //定义接口
>   >  interface IReceiver {
>   >  	public String getInfo();
>   >  }
>   >  
>   >  class Email implements IReceiver {
>   >  	public String getInfo() {
>   >  		return "电子邮件信息: hello,world";
>   >  	}
>   >  }
>   >  
>   >  //增加微信
>   >  class WeiXin implements IReceiver {
>   >  	public String getInfo() {
>   >  		return "微信信息: hello,ok";
>   >  	}
>   >  }
>   >  
>   >  //方式2
>   >  class Person {
>   >  	//这里我们是对接口的依赖
>   >  	public void receive(IReceiver receiver ) {
>   >  		System.out.println(receiver.getInfo());
>   >  	}
>   >  }
>   >  作者：JonyJava
>   >  链接：https://juejin.cn/post/6847902224765419534
>   >  
>   >  ```
>
> - 迪米特法则
>
>   > 1. 一个对象应该对其他对象保持最少的了解
>   > 2. 类与类关系越密切，耦合度越大
>   > 3. 迪米特法则(Demeter Principle)又叫**最少知道原则**，即一个类对自己依赖的类知道的越少越好。也就是说，对于被依赖的类不管多么复杂，都尽量将逻辑封装在类的内部。对外除了提供的 public 方法，不对外泄露任何信息
>   >
>   > 迪米特法则还有个更简单的定义：只与直接的朋友通信
>   >
>   > 直接的朋友：每个对象都会与其他对象有耦合关系，只要两个对象之间有耦合关系，我们就说这两个对象之间 是朋友关系。耦合的方式很多，依赖，关联，组合，聚合等。其中，我们称出现在**成员变量**，**方法参数**，**方法返回值**中的类为直接的朋友，而出现在局部变量中的类不是直接的朋友。也就是说，陌生的类最好不要以局部变量的形式出现在类的内部。
>   >
>   > 作者：JonyJava
>   > 链接：https://juejin.cn/post/6847902224765419534
>   > 来源：稀土掘金
>   > 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。

### 2. Pillars of OOP

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

### 3. OOD 流程

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

### 4. OOD 分类

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



### 5. Design Pattern

> 1. **Simple Factory**
>
>    > 简单工厂模式（Simple Factory Pattern）又叫静态工厂方法模式（Static FactoryMethod Pattern）。专门定义一个类（如上文中的CarFactory1、CarFactory2、CarFactory3）来负责创建其它类的实例，由它来决定实例化哪个具体类，从而避免了在客户端代码中显式指定，实现了解耦。该类由于可以创建同一抽象类（或接口）下的不同子类对象，就像一个工厂一样，因此被称为工厂类。
>    >
>    > 1. 有一种抽象产品——汽车（Car），同时有多种具体的子类产品，如BenzCar，BMWCar，LandRoverCar。作为司机，如果要开其中一种车，比如BenzCar，最直接的做法是直接创建BenzCar的实例，并执行其drive方法，如下
>    >
>    >    ```java
>    >    //interface Car{...}
>    >    //class BenzCar implement Car{...}
>    >    //class BMWCara implement Car{...}
>    >    //class LandRoverCar{...}
>    >    public class Driver1 {
>    >      public static void main(String[] args) {
>    >        BenzCar car = new BenzCar();
>    >        car.drive();
>    >      }
>    >    }
>    >    ```
>    >
>    > 2. 此时如果要改为开Land Rover，则需要修改代码，创建Land Rover的实例并执行其drive方法。这也就意味着任何时候需要换一辆车开的时候，都必须修改客户端代码。一种稍微好点的方法是，通过读取配置文件，获取需要开的车，然后创建相应的实例并由父类Car的引用指向它，利用多态执行不同车的drive方法。如下
>    >
>    >    ```java
>    >    public class Driver2 {
>    >      private static final Logger LOG = LoggerFactory.getLogger(Driver2.class);
>    >      public static void main(String[] args) throws ConfigurationException {
>    >        XMLConfiguration config = new XMLConfiguration("car.xml");
>    >        String name = config.getString("driver2.name");
>    >        Car car;
>    >        switch (name) {
>    >        case "Land Rover":
>    >          car = new LandRoverCar();
>    >          break;
>    >        case "BMW":
>    >          car = new BMWCar();
>    >          break;
>    >        case "Benz":
>    >          car = new BenzCar();
>    >          break;
>    >        default:
>    >          car = null;
>    >          break;
>    >        }
>    >        LOG.info("Created car name is {}", name);
>    >        car.drive();
>    >      }
>    >    }
>    >    ```
>    >
>    > 3. 对于Car的使用方而言，只需要通过参数即可指定所需要Car的各类并得到其实例，同时无论使用哪种Car，都不需要修改后续对Car的操作。至此，简单工厂模式的原型已经形成。如果把上述的逻辑判断封装到一个专门的类的静态方法中，则实现了简单工厂模式。工厂代码如下
>    >
>    >    ```java
>    >    public class CarFactory1 {
>    >         
>    >      private static final Logger LOG = LoggerFactory.getLogger(CarFactory1.class);
>    >       
>    >      public static Car newCar() {
>    >        Car car = null;
>    >        String name = null;
>    >        try {
>    >          XMLConfiguration config = new XMLConfiguration("car.xml");
>    >          name = config.getString("factory1.name");
>    >        } catch (ConfigurationException ex) {
>    >          LOG.error("parse xml configuration file failed", ex);
>    >        }
>    >       
>    >        switch (name) {
>    >        case "Land Rover":
>    >          car = new LandRoverCar();
>    >          break;
>    >        case "BMW":
>    >          car = new BMWCar();
>    >          break;
>    >        case "Benz":
>    >          car = new BenzCar();
>    >          break;
>    >        default:
>    >          car = null;
>    >          break;
>    >        }
>    >        LOG.info("Created car name is {}", name);
>    >        return car;
>    >      }
>    >       
>    >    }
>    >    ```
>    >
>    >    
>    >
>    > 
>
> 2. 