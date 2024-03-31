import jdk.nashorn.internal.objects.Global.println

import java.lang.reflect.Array

object ImplictConversion {
  def main(args: Array[String]): Unit = {
    //<1>
    //(1)声明变量时，类型可以省略，编译器自动推导，即类型推导
    //(2)类型确定后，就不能修改，说明 Scala 是强数据类型语言
    //(3)变量声明时，必须要有初始值
    var age = 18
    age = 30

    //<2>
    //(1)自动提升原则:有多种类型的数据混合运算时，系统首先自动将所有 数据转换成精度大的那种数值类型，然后再进行计算。
    var n = 1 + 2.0
    println(n) // n 就是 Double
    //(1)将数据由高精度转换为低精度，就需要使用到强制转换x
    //var num : Int = 2.7.toInt

    //<3>
    //Java:  ==比较两个变量本身的值，即两个对象在内存中的首地址; equals 比较字符串中所包含的内容是否相同。
    //Scala: ==更加类似于 Java 中的 equals，参照 jd 工具
    val s1 = "abc"
    val s2 = new String("abc")
    println(s1 == s2) //true
    println(s1.eq(s2)) //false

    //<4>
    //在 Scala 中其实是没有运算符的，所有运算符都是方法。
    // 标准的加法运算
    val i: Int = 1.+(1)
    // (1)当调用对象的方法时，.可以省略
    val j: Int = 1 + (1)
    // (2)如果函数参数只有一个，或者没有参数，()可以省略
    val k: Int = 1 + 1
    println(1.toString())
    println(1 toString())
    println(1 toString)

    //<5>
    //Scala 中 if else 表达式其实是有返回值的，具体返回值取决于满足条件的 代码体的最后一行内容。
    val res: String = if (age < 18) {
      "童年"
    }
    else if (age >= 18 && age < 30) {
      "中年"
    }
    else {
      "老年"
    }
    //需求 3:Scala 中返回值类型不一致，取它们共同的祖先类型。
    val res1: Any = if (age < 18) {
      "童年"
    } else if (age >= 18 && age < 30) {
      "中年"
    } else {
      100
    }

    //<6>
    //for loop with to, i 将会从 1-3 循环，前后闭合
    for (i <- 1 to 3) {
      println(i + " ")
    }
    //for loop with until, i 将会从 1 到 3-1 循环，前闭合后开
    for (i <- 1 until 3) {
      print(i + " ")
    }
    //(1)循环守卫，即循环保护式(也称条件判断式，守卫)。保护式为 true 则进入循环 体内部，为 false 则跳过，类似于 continue。
    for (i <- 1 to 3 if i != 2) {
      print(i + " ")
    }
    //(2)循环步长, by 表示步长. 需求:输出 1 到 10 以内的所有奇数
    for (i <- 1 to 10 by 2) {
      println("i=" + i)
    }
    //(3)嵌套循环. 没有关键字，所以范围后一定要加;来隔断逻辑
    for (i <- 1 to 3; j <- 1 to 3) {
      println(" i =" + i + " j = " + j)
    } // same to
    for (i <- 1 to 3) {
      for (j <- 1 to 3) {
        println("i =" + i + " j=" + j)
      }
    }
    //(4)引入变量.
    for (i <- 1 to 3; j = 4 - i) {
      println("i=" + i + " j=" + j)
    } // same to
    for (i <- 1 to 3) {
      var j = 4 - i
      println("i=" + i + " j=" + j)
    }
    //(5)循环返回值. 将遍历过程中处理的结果返回到一个新 Vector 集合中，使用 yield 关键字。
    var res = for (i <- 1 to 10) yield {
      i * 2
    }
    println(res) //Vector(2, 4, 6, 8, 10, 12, 14, 16, 18, 20)
    //(6) 倒序打印 10 到 1
    for (i <- 1 to 10 reverse) {
      println(i)
    }

    //<7>
    //与 for 语句不同，while 语句没有返回值，即整个 while 语句的结果是 Unit 类型()
    var i = 0
    while (i < 10) {
      println("宋宋，喜欢海狗人参丸" + i)
      i += 1
    }

    //<8>
    //循环中断. Scala 内置控制结构特地去掉了 break 和 continue，是为了更好的适应函数式编程，
    // 推 荐使用函数式的风格解决 break 和 continue 的功能，而不是一个关键字。
    // Scala 中使用 breakable 控制结构来实现 break 和 continue 功能。
    //(1) 采用异常的方式退出循环
    try {
      for (elem <- 1 to 10) {
        println(elem)
        if (elem == 5) throw new RuntimeException
      }
    } catch {
      case e =>
    }
    println("正常结束循环")


    //(2) import scala.util.control.Breaks. 采用 Scala 自带的函数，退出循环
    Breaks.breakable(
      for (elem <- 1 to 10) {
        println(elem)
        if (elem == 5) Breaks.break()
      }
    ) // or import scala.util.control.Breaks._
    breakable {
      for (elem <- 1 to 10) {
        println(elem)
        if (elem == 5) break
      }
    }

    //<9>
    //面向对象编程. 解决问题，分解对象，行为，属性，然后通过对象的关系以及行为的调用来解决问题。对象的本质:对数据和行为的一个封装
    //    对象:用户
    //    行为:登录、连接 JDBC、读取数据库
    //    属性:用户名、密码
    //函数式编程.解决问题时，将问题分解成一个一个的步骤，将每个步骤进行封装(函数)，通过调用这些封装好的步骤，解决问题。
    //    例如:请求->用户名、密码->连接 JDBC->读取数据库
    //函数和方法的区别
    //    1)核心概念
    //    (1)为完成某一功能的程序语句的集合，称为函数。
    //    (2)类中的函数称之方法。
    //    2)案例实操
    //    (1)Scala 语言可以在任何的语法结构中声明任何的语法
    //    (2)函数没有重载和重写的概念;方法可以进行重载和重写
    //    (3)Scala 中函数可以嵌套定义
    //(1)函数参数
    def test(s: String*): Unit = {
      println(s)
    }

    test("Hello", "Scala") // 有输入参数:输出 Array
    test() // 无输入参数:输出 List()
    //(2)如果参数列表中存在多个参数，那么可变参数一般放置在最后
    def test2(name: String, s: String*): Unit = {
      println(name + "," + s)
    }

    test2("jinlian", "dalang")

    // (3)参数默认值
    def test3(name: String, age: Int = 30): Unit = {
      println(s"$name, $age")
    }

    test3("jinlian", 20) // 如果参数传递了值，那么会覆盖默认值
    test3("dalang") // 如果参数有默认值，在调用的时候，可以省略这个参数

    //(4)至简原则细节
    //(1)return 可以省略，Scala 会使用函数体的最后一行代码作为返回值
    // (2)如果函数体只有一行代码，可以省略花括号
    // (3)返回值类型如果能够推断出来，那么可以省略(:和返回值类型一起省略)
    // (4)如果有 return，则不能省略返回值类型，必须指定
    // (5)如果函数明确声明 unit，那么即使函数体中使用 return 关键字也不起作用
    // (6)Scala 如果期望是无返回值类型，可以省略等号
    // (7)如果函数无参，但是声明了参数列表，那么调用时，小括号，可加可不加
    // (8)如果函数没有参数列表，那么小括号可以省略，调用时小括号必须省略
    // (9)如果不关心名称，只关心逻辑处理，那么函数名(def)可以省略
    // 函数标准写法
    def f(s: String): String = {
      return s + " jinlian"
    }

    println(f("Hello"))

    // 至简原则:能省则省
    //(1) return 可以省略,Scala 会使用函数体的最后一行代码作为返回值
    def f1(s: String): String = {
      s + " jinlian"
    }

    println(f1("Hello"))

    //(2)如果函数体只有一行代码，可以省略花括号
    def f2(s: String): String = s + " jinlian"

    //(3)返回值类型如果能够推断出来，那么可以省略(:和返回值类型一起 省略)
    def f3(s: String) = s + " jinlian" println (f3("Hello3"))

    //(4)如果有 return，则不能省略返回值类型，必须指定。
    def f4(): String = {
      return "ximenqing4"
    }

    println(f4())

    //(5)如果函数明确声明 unit，那么即使函数体中使用 return 关键字也不起作用
    def f5(): Unit = {
      return "dalang5"
    }

    println(f5())

    //(6)Scala 如果期望是无返回值类型,可以省略等号
    // 将无返回值的函数称之为过程
    def f6() {
      "dalang6"
    }

    println(f6())

    //(7)如果函数无参，但是声明了参数列表，那么调用时，小括号，可加可不加
    def f7() = "dalang7"

    println(f7())
    println(f7)

    //(8)如果函数没有参数列表，那么小括号可以省略,调用时小括号必须省略
    def f8 = "dalang"
    //println(f8())
    println(f8)

    //(9)如果不关心名称，只关心逻辑处理，那么函数名(def)可以省略
    def f9 = (x: String) => {
      println("wusong")
    }

    def f10(f: String => Unit) = {
      f("")
    }

    f10(f9)
    println(f10((x: String) => {
      println("wusong")
    }))

    //<10>
    //函数可以作为值进行传递
    def foo(): Unit = {
      println("foo...")
    }

    //(1)调用 foo 函数，把返回值给变量 f
    // val f = foo()
    val f = foo
    println(f)
    //(2)在被调用函数 foo 后面加上 _，相当于把函数 foo 当成一个整体， 传递给变量 f1
    val f1 = foo _
    foo()
    f1()

    //(3)如果明确变量类型，那么不使用下划线也可以将函数作为整体传递给 变量
    //       var f2:()=>Int = foo
    def foo(): Int = {
      println("foo...")
      1
    }

    //<11>
    //函数可以作为参数进行传递
    //(1)定义一个函数，函数参数还是一个函数签名;f 表示函数名称;(Int,Int)表示输入两个 Int 参数;Int 表示函数返回值
    def f1(f: (Int, Int) => Int): Int = {
      f(2, 4)
    }

    // (2)定义一个函数，参数和返回值类型和 f1 的输入参数一致
    def add(a: Int, b: Int): Int = a + b
    // (3)将 add 函数作为参数传递给 f1 函数，如果能够推断出来不是调用，_可以省略
    println(f1(add))
    println(f1(add _))

    //可以传递匿名函数
    //(x:Int)=>{函数体}。x:表示输入参数类型;Int:表示输入参数类型;函数体:表示具体代码逻辑
    //     传递匿名函数至简原则:
    // (1)参数的类型可以省略，会根据形参进行自动的推导
    // (2)类型省略之后，发现只有一个参数，则圆括号可以省略;其他情况:没有参数和参 数超过 1 的永远不能省略圆括号。
    // (3)匿名函数如果只有一行，则大括号也可以省略 
    // (4)如果参数只出现一次，则参数省略且后面参数可以用_代替
    // (1)定义一个函数:参数包含数据和逻辑函数
    def operation(arr: Array[Int], op: Int => Int) = {
      for (elem <- arr) yield op(elem)
    }

    // (2)定义逻辑函数
    def op(ele: Int): Int = {
      ele + 1
    }

    // (3)标准函数调用
    val arr = operation(Array(1, 2, 3, 4), op) println (arr.mkString(","))
    // (4)采用匿名函数
    val arr1 = operation(Array(1, 2, 3, 4), (ele: Int) => {
      ele + 1
    })
    // (4.1)参数的类型可以省略，会根据形参进行自动的推导; 
    val arr2 = operation(Array(1, 2, 3, 4), (ele) => {
      ele + 1
    })
    println(arr2.mkString(","))
    // (4.2)类型省略之后，发现只有一个参数，则圆括号可以省略;其他情况: 没有参数和参数超过 1 的永远不能省略圆括号。
    val arr3 = operation(Array(1, 2, 3, 4), ele => {
      ele + 1
    })
    println(arr3.mkString(","))
    // (4.3) 匿名函数如果只有一行，则大括号也可以省略
    val arr4 = operation(Array(1, 2, 3, 4), ele => ele + 1) println (arr4.mkString(","))
    //(4.4)如果参数只出现一次，则参数省略且后面参数可以用_代替
    val arr5 = operation(Array(1, 2, 3, 4), _ + 1)

    //传递的函数有两个参数
    def calculator(a: Int, b: Int, op: (Int, Int) => Int): Int
    = {
      op(a, b)
    }
    // (1)标准版
    println(calculator(2, 3, (x: Int, y: Int) => {
      x + y
    }))
    // (2)如果只有一行，则大括号也可以省略
    println(calculator(2, 3, (x: Int, y: Int) => x + y))
    // (3)参数的类型可以省略，会根据形参进行自动的推导;
     println(calculator(2, 3, (x , y) => x + y))
    // (4)如果参数只出现一次，则参数省略且后面参数可以用_代替
    println(calculator(2, 3, _ + _))

    //<12>



  }


}
