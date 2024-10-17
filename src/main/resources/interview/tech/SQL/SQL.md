## 1. SQL 

### 1.1 SQL 种类

> - DDL**(**Data Definition Languages**、数据定义语言)**，这些语句定义了不同的数据库、表、视图、索 引等数据库对象，还可以用来创建、删除、修改数据库和数据表的结构。主要的语句关键字包括 CREATE 、 DROP 、 ALTER 等。
> - DML**(**Data Manipulation Language**、数据操作语言)**，用于添加、删除、更新和查询数据库记 录，并检查数据完整性。主要的语句关键字包括 INSERT 、 DELETE 、 UPDATE 、 SELECT 等。SELECT**是**SQL**语言的基础，最为重要。**
> - DCL**(**Data Control Language**、数据控制语言)**，用于定义数据库、表、字段、用户的访问权限和 安全级别。主要的语句关键字包括 GRANT 、 REVOKE 、 COMMIT 、 ROLLBACK 、 SAVEPOINT 等
> - DQL(数据查询语言)
> - TCL (Transaction Control Language，事务控制语 言)  COMMIT 、 ROLLBACK

### 1.2  **常见的数据库对象**

> - **表(TABLE) ：**是存储数据的逻辑单元，以行和列的形式存在，列就是字段，行就是记录 
> - **数据字典：**  就是系统表，存放数据库相关信息的表。系统表的数据通常由数据库系统维护，程序员通常不修改，只可查看。
> - **约束CONSTRAINT)：**  执行数据校验的规则，用于保证数据完整性的规则
> - **视图(VIEW)：**  一个或者多个数据表里的数据的逻辑显示，视图并不存储数据 
> - **索引(INDEX) ：** 用于提高查询性能，相当于书的目录 
> - **存储过程 (PROCEDURE)： **用于完成一次完整的业务处理，没有返回值，但可通过传出参数将多个值传给调用环境 
> - **存储函数(FUNCTION) ：**用于完成一次特定的计算，具有一个返回值 
> - **触发器(TRIGGER)：** 相当于一个事件监听器，当数据库发生特定事件后，触发器被触发，完成相应的处理

## 2. SQL 运算符

> 1. **列的别名alias**
>
>    > - 紧跟列名，可以 **在列名和别名之间加入关键字**AS**，别名使用双引号** ，以便在别名中包含空格或特殊字符并区分大小写。
>    >   `SELECT last_name AS name, commission_pct comm,, salary*12 "Annual Salary" from employees;`
>
> 2. **空值参与运算**
>
>    > - 所有运算符或列值遇到null值，运算的结果都为null
>
> 3. **着重号emphasis**
>
>    > ```sql
>    > SELECT * FROM `ORDER`;
>    > ```
>
> 4. **显示表结构**
>
>    > - 使用DESCRIBE 或 DESC 命令，表示表结构。
>    >   `DESCRIBE employees;` 或者 `DESC employees;`
>
> 5. **运算符**
>
>    > 1. **算数运算符**
>    >
>    >    > - 一个整数类型的值对整数进行加法和减法操作，结果还是一个整数; 
>    >    > - 一个整数类型的值对浮点数进行加法和减法操作，结果是一个浮点数; 
>    >    > - 加法和减法的优先级相同，进行先加后减操作与进行先减后加操作的结果是一样的;
>    >    > - 一个数乘以整数1和除以整数1后仍得原数; 
>    >    > - 一个数乘以浮点数1和除以浮点数1后变成浮点数，数值与原数相等; 
>    >    > - 一个数除以整数后，不管是否能除尽，结果都为一个浮点数; 
>    >    > - 一个数除以另一个数，除不尽时，结果为一个浮点数，并保留到小数点后4位;
>    >
>    > 2. **比较运算符**
>    >
>    >    > 1. **等号运算符**。
>    >    >
>    >    >    > - 如果等号两边的值、字符串或表达式都为字符串，则MySQL会按照字符串进行比较，其比较的 是每个字符串中字符的ANSI编码是否相等。 
>    >    >    > - 如果等号两边的值都是整数，则MySQL会按照整数来比较两个值的大小。
>    >    >    > -  如果等号两边的值一个是整数，另一个是字符串，则MySQL会将字符串转化为数字进行比较。
>    >    >    > -  如果等号两边的值、字符串或表达式中有一个为NULL，则比较结果为NULL
>    >    >    >
>    >    >    > **SQL中赋值符号使用 :=**
>    >    >
>    >    > 2. **空运算符和非空运算符，**非空运算符** 空运算符(IS NULL或者ISNULL)判断一个值是否为NULL，如果为NULL则返回1，否则返回 0
>    >    >
>    >    > 3. **最小值运算符** 语法格式为:LEAST(值1，值2，...，值n)。其中，“值n”表示参数列表中有n个值。在有 两个或多个参数的情况下，返回最小值。当比较值列表中有NULL时，不能判断大小，返回值为NULL。
>    >    >
>    >    > 4. **最大值运算符** 语法格式为:GREATEST(值1，值2，...，值n)。其中，n表示参数列表中有n个值。当有 两个或多个参数时，返回值为最大值。假如任意一个自变量为NULL，则GREATEST()的返回值为NULL
>    >    >
>    >    > 5. BETWEEN AND**运算符** BETWEEN运算符使用的格式通常为SELECT D FROM TABLE WHERE C BETWEEN A AND B，此时，当C大于或等于A，并且C小于或等于B时，结果为1，否则结果为0。
>    >    >
>    >    > 6. IN**运算符** IN运算符用于判断给定的值是否是IN列表中的一个值，如果是则返回1，否则返回0。如果给 定的值为NULL，或者IN列表中存在NULL，则结果为NULL。
>    >    >
>    >    > 7. NOT IN**运算符** NOT IN运算符用于判断给定的值是否不是IN列表中的一个值，如果不是IN列表中的一 个值，则返回1，否则返回0。
>    >    >
>    >    > 8. LIKE**运算符** LIKE运算符主要用来匹配字符串，通常用于模糊匹配，如果满足条件则返回1，否则返回 0。如果给定的值或者匹配条件为NULL，则返回结果为NULL。**“%”:匹配0个或多个字符。 “_”:只能匹配一个字符** 
>    >    >
>    >    > 9. ESCAPE。 如果使用\表示转义，要省略ESCAPE。如果不是\，则要加上ESCAPE。
>    >    >
>    >    > 10. **REGEXP运算符**。REGEXP运算符用来匹配字符串，语法格式为: expr REGEXP 匹配条件 。如果expr满足匹配条件，返回0，否则1
>    >    >
>    >    >     ```sql
>    >    >     SELECT 'atguigu' REGEXP 'gu.gu', 'atguigu' REGEXP '[ab]';
>    >    >     ```
>    >    >
>    >    > 11. **逻辑运算符**. AND OR XOR. 由于AND的优先级高于OR，因此先 对AND两边的操作数进行操作，再与OR中的操作数结合。逻辑异或(XOR)运算符是当给定的值中任意一个值为NULL时，则返回NULL;如果 两个非NULL的值都是0或者都不等于0时，则返回0;如果一个值为0，另一个值不为0时，则返回1。
>    >    >
>    >    > 12. ***位运算符**. 位运算符是在二进制数上进行计算的运算符。位运算符会先将操作数变成二进制数，然后进行位运算，
>    >    >
>    >    >     最后将计算结果从二进制变回十进制数
>
> 6. **排序与分页**
>
>    > - 使用 ORDER BY 子句排序. ORDER BY **子句在**SELECT**语句的结尾。**
>    >
>    >   > `SELECT last_name,department_id, salary FROM   employees ORDER BY department_id, salary DESC;`
>    >
>    > - LIMIT
>    >
>    >   > - MySQL 8.0中可以使用“LIMIT 3 OFFSET 4”，意思是获取从第5条记录开始后面的3条记录，和“LIMIT 4,3;”返回的结果相同。
>    >   > - 分页显式公式 **:(当前页数**-1**)*****每页条数，每页条数**

## 3. 函数

### 3.1 多表查询

> 1. 多表查询，也称为关联查询，指两个或更多个表一起完成查询操作。
>    前提条件:这些一起查询的表之间是有关系的(一对一、一对多)，它们之间一定是有关联字段，这个 关联字段可能建立了外键，也可能没有建立外键。
>
> 2. **笛卡尔积的错误会在下面条件下产生** :
>
>    > - 省略多个表的连接条件(或关联条件)
>    > - 连接条件(或关联条件)无效
>    > -  所有表中的所有行互相连接
>    > - 为了避免笛卡尔积， 可以**在** WHERE **加入有效的连接条件。**
>    > - 在表中有相同列时，在列名之前加上表名前缀。
>
> 3. 使用JOIN...ON子句创建连接的语法结构:
>
>    > ```sql
>    > SELECT table1.column, table2.column,table3.column
>    > FROM table1
>    > JOIN table2 ON table1 和 table2 的连接条件 
>    > JOIN table3 ON table2 和 table3 的连接条件
>    > -- 它的嵌套逻辑类似我们使用的 FOR 循环:
>    > for t1 in table1:
>    >     for t2 in table2:
>    >        if condition1:
>    >            for t3 in table3:
>    >               if condition2:
>    >                   output t1 + t2 + t3
>    > ```

### 3.2 单行函数

> 1. **数值函数**
>
>    > Abs(x). 
>    >
>    > Sign(x) 返回X的符号。正数返回1，负数返回-1，0返回0
>    >
>    > Ceil(x), Floor(x)
>    >
>    > Least(x1, x2, x3), greatest(x1, x2, x3) 返回列表中的最小值
>    >
>    > BIN(x) 返回x的二进制编码
>
> 2. **字符串函数**
>
>    > ASCII(S) 返回字符串S中的第一个字符的ASCII码值
>    >
>    > CONCAT(s1,s2,......,sn) 连接s1,s2,......,sn为一个字符串
>    >
>    > CONCAT_WS(x, s1,s2,......,sn) 同CONCAT(s1,s2,...)函数，但是每个字符串之间要加上x
>    >
>    > UPPER(s) 将字符串s的所有字母转成大写字母
>
> 3. **日期和时间函数**
>
>    > CURRENT_DATE()  返回当前日期，只包含年、月、日
>    >
>    > CURRENT_TIME() 返回当前时间，只包含时、分、秒 
>    >
>    > UNIX_TIMESTAMP() 以UNIX时间戳的形式返回当前时间。SELECT UNIX_TIMESTAMP() - >1634348884
>    >
>    > QUARTER(date) 返回日期对应的季度，范围为1~4
>    >
>    > DAYNAME(date) 返回星期几:MONDAY，TUESDAY.....SUNDAY
>    >
>    > DATE_FORMAT(date,fmt) 按照字符串fmt格式化日期date值
>
> 4. **加密与解密函数**
>
>    > MD5(str) 返回字符串str的md5加密后的值，也是一种加密方式。若参数为 NULL，则会返回NULL
>
> 5. **信息函数**
>
>    > CURRENT_USER() 返回当前连接MySQL的用户名，返回结果格式为 “主机名@用户名”

### 3.3 **聚合函数**

> 聚合函数作用于一组数据，并对一组数据返回一个值。
>
> 1. **聚合函数类型**
>
>    > - AVG() 
>    > - SUM() 
>    > - MAX() 
>    > - MIN() 
>    > - COUNT()
>    >
>    > `SELECT AVG(salary), MAX(salary),MIN(salary), SUM(salary) FROM   employees`
>
> 2. **可以使用GROUP BY子句将表中的数据分成若干组**
>
>    > - 包含在 GROUP BY 子句中的列不必包含在SELECT 列表中
>    > - **在**SELECT**列表中所有未包含在聚合函数中的列都应该包含在** GROUP BY**子句中**
>
> 3. **过滤分组:HAVING**
>
>    > - 使用条件
>    >
>    >   - 行已经被分组。
>    >
>    >   - 使用了聚合函数。
>    >
>    >   - 满足HAVING 子句中条件的分组将被显示。
>    >   - HAVING 不能单独使用，必须要跟 GROUP BY 一起使用。
>    >   - **非法使用聚合函数 : 不能在** WHERE **子句中使用聚合函数**
>    >
>    > ```sql
>    > SELECT   department_id, MAX(salary)
>    > FROM     employees
>    > GROUP BY department_id
>    > HAVING   MAX(salary)>10000 ;
>    > ```
>    >
>    > **WHERE 先筛选数据再关联，执行效率高 不能使用分组中的计算函数进行筛选** 
>    >
>    > **HAVING 可以使用分组中的计算函数 在最后的结果集中进行筛选，执行效率较低** 
>    >
>    > **包含分组 统计函数的条件用 HAVING，普通条件用 WHERE。**
>
> 4. SELECT **语句的执行顺序**
>
>    >  FROM -> WHERE -> GROUP BY -> HAVING -> SELECT 的字段 -> DISTINCT -> ORDER BY -> LIMIT
>    > ```sql
>    > SELECT DISTINCT player_id, player_name, count(*) as num # 顺序 5 
>    > FROM player JOIN team ON player.team_id = team.team_id # 顺序 1 
>    > WHERE height > 1.80 # 顺序 2
>    > GROUP BY player.team_id # 顺序 3
>    > HAVING num > 2 # 顺序 4 
>    > ORDER BY num DESC # 顺序 6 
>    > LIMIT 2 # 顺序 7
>    > 
>    > --在 SELECT 语句执行这些步骤的时候，每个步骤都会产生一个 虚拟表 ，然后将这个虚拟表传入下一个步 骤中作为输入。需要注意的是，这些步骤隐含在 SQL 的执行过程中，对于我们来说是不可见的。
>    > 
>    > SELECT 是先执行 FROM 这一步的。在这个阶段，如果是多张表联查，还会经历下面的几个步骤:
>    > 1. 首先先通过 CROSS JOIN 求笛卡尔积，相当于得到虚拟表 vt(virtual table)1-1;
>    > 2. 通过 ON 进行筛选，在虚拟表 vt1-1 的基础上进行筛选，得到虚拟表 vt1-2;
>    > 3. 添加外部行。如果我们使用的是左连接、右链接或者全连接，就会涉及到外部行，也就是在虚拟
>    > 表 vt1-2 的基础上增加外部行，得到虚拟表 vt1-3。 当然如果我们操作的是两张以上的表，还会重复上面的步骤，直到所有表都被处理完为止。这个过程得
>    > 到是我们的原始数据。
>    > ```

### 3.4 **子查询**

> 1. **子查询的基本使用**
>
>    > - 子查询(内查询)在主查询之前一次执行完成。
>    >
>    > - 子查询的结果被主查询(外查询)使用 。
>    >
>    > - 子查询要包含在括号内
>    > - 将子查询放在比较条件的右侧
>    > - **单行操作符对应单行子查询，多行操作符对应多行子查询**
>
> 2. **子查询的分类: **我们按内查询的结果返回一条还是多条记录，将子查询分为 单行子查询 、 多行子查询 。
>
>    > 1. **单行子查询**
>    >
>    >    > 可以使用比较运算符 =, >, <, >=, <=, !=
>    >    >
>    >    > 1. 子查询放入where
>    >    >
>    >    >    >```sql
>    >    >    >-- **返回公司工资最少的员工的**last_name,job_id**和**salary
>    >    >    >SELECT last_name, job_id, salary
>    >    >    >FROM   employees
>    >    >    >WHERE  salary =
>    >    >    >                (SELECT MIN(salary)
>    >    >    >                 FROM   employees);
>    >    >    >```
>    >    >
>    >    > 2. Case 中子查询
>    >    >
>    >    >    > ```sql
>    >    >    > -- 显式员工的employee_id,last_name和location。其中，若员工department_id与location_id为1800 的department_id相同，则location为’Canada’，其余则为’USA’。
>    >    >    > SELECT employee_id, last_name,
>    >    >    >        (CASE department_id
>    >    >    >         WHEN
>    >    >    >              (SELECT department_id FROM departments
>    >    >    >           WHERE location_id = 1800)
>    >    >    >         THEN 'Canada' ELSE 'USA' END) location
>    >    >    > FROM   employees;
>    >    >    > ```
>    >
>    > 2. **多行子查询**
>    >
>    >    > 1. **多行比较操作符**
>    >    >
>    >    >    > IN 等于列表中的 **任意一个**
>    >    >    >
>    >    >    > ANY 需要和单行比较操作符一起使用，和子查询返回的 **某一个** 值比较
>    >    >    >
>    >    >    > ALL 需要和单行比较操作符一起使用，和子查询返回的 **所有** 值比较
>    >    >    >
>    >    >    > ```sql
>    >    >    > -- 查询平均工资最低的部门id
>    >    >    > SELECT department_id
>    >    >    > FROM employees
>    >    >    > GROUP BY department_id 
>    >    >    > HAVING AVG(salary) <= ALL (
>    >    >    >                 SELECT AVG(salary) avg_sal
>    >    >    >                 FROM employees
>    >    >    >                 GROUP BY department_id
>    >    >    > )
>    >    >    > ```
>
> 3. **子查询的分类: **我们按内查询是否被执行多次，将子查询划分为 相关(或关联)子查询 和 不相关(或非关联)子查询 。
>
>    > 1. **相关子查询**
>    >
>    >    > 如果子查询的执行依赖于外部查询，通常情况下都是因为子查询中的表用到了外部的表，并进行了条件关联，**因此每执行一次外部查询，子查询都要重新计算一次，这样的子查询就称之为 关联子查询** 。
>    >    >
>    >    > ```sql
>    >    > -- 若employees表中employee_id与job_history表中employee_id相同的数目不小于2，输出这些相同 id的员工的employee_id,last_name和其job_id
>    >    > SELECT e.employee_id, last_name,e.job_id
>    >    > FROM   employees e
>    >    > WHERE  2 <= (SELECT COUNT(*)
>    >    >              FROM   job_history
>    >    >              WHERE  employee_id = e.employee_id);
>    >    > ```
>    >    >
>    >    > 关联子查询通常也会和 EXISTS操作符一起来使用，用来检查在子查询中是否存在满足条件的行。
>    >    >
>    >    > ```sql
>    >    > -- 查询公司管理者的employee_id，last_name，job_id，department_id信息
>    >    > SELECT employee_id, last_name, job_id, department_id
>    >    > FROM   employees e1
>    >    > WHERE  EXISTS ( SELECT *
>    >    >                  FROM   employees e2
>    >    >                  WHERE  e2.manager_id =
>    >    >                         e1.employee_id);
>    >    > ```
>    >    >
>    >    > 题目中可以使用子查询，也可以使用自连接。一般情况建议你使用自连接，因为在许多 DBMS 的处理过 程中，对于自连接的处理速度要比子查询快得多。











