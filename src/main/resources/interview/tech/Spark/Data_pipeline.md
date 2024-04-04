# 1
>
    （1）基本语法
    bin/spark-submit \
    --class <main-class>
    --master <master-url> \
    --deploy-mode <deploy-mode> \
    --conf <key>=<value> \
    ... # other options
    <application-jar> \
    [application-arguments]
    （2）参数说明：
    --master 指定Master的地址，默认为Local
    --class: 你的应用的启动类 (如 org.apache.spark.examples.SparkPi)
    --deploy-mode: 是否发布你的驱动到worker节点(cluster) 或者作为一个本地客户端 (client) (default: client)*
    --conf: 任意的Spark配置属性， 格式key=value. 如果值包含空格，可以加引号“key=value” 
    application-jar: 打包好的应用jar,包含依赖. 这个URL在集群中全局可见。 比如hdfs:// 共享存储系统， 如果是 file:// path， 那么所有的节点的path都包含同样的jar
    application-arguments: 传给main()方法的参数
    --executor-memory 1G 指定每个executor可用内存为1G
    --total-executor-cores 2 指定每个executor使用的cup核数为2个

    Ss:Code
    //import org.apache.spark.sql.{SparkSession, SQLImplicits, Encoder, Encoders}
    from pyspark.sql import SparkSession
    import pyspark.sql.functions as F
    from pyspark.sql.types import *
    from pyspark import SparkContext
    sc = SparkContext("local", "First App")
    st = StructType([StructField("col1", StringType(), True),
        StructField("month", StringType(), True),
        StructField("col2", DoubleType(), True)
    ])
    spark = SparkSession.builder.appName("Interview").config("spark.sql.warehouse.dir", warehouse_locations).enableHiveSupport().getOrCreate()
    df = spark.read.parquet(path)
    df = df.select("col_name").filter(df.col_name != 0 | df.col_name > 0).withColumn("col_name", F.when(F.col("col_name") != 0 , "option1")).otherwise(F.col("another_col_name")).withColumnRenamed("old_name", "newName").dropDuplicates()
    df = df.groupBy(["col1", "col2"]).agg({"col1": "sum". "col2": "sum"}).withColumnRenamed("sum(cols1)", "col1")
    df.write.option("path", path).option("format", "parquet").("mode", "overwrite")

    SC:code
    //1.创建SparkConf并设置App名称
        val conf = new SparkConf().setAppName("WC")

    //2.创建SparkContext，该对象是提交Spark App的入口
        val sc = new SparkContext(conf)

        //3.使用sc创建RDD并执行相应的transformation和action
        sc.textFile(args(0)).flatMap(_.split(" ")).map((_, 1)).reduceByKey(_+_, 1).sortBy(_._2, false).saveAsTextFile(args(1))

    //4.关闭连接
        sc.stop()



# 2
    <1> 
    SELECT OrderID, Quantity,
    CASE
        WHEN Quantity > 30 THEN 'The quantity is greater than 30'
        WHEN Quantity = 30 THEN 'The quantity is 30'
        ELSE 'The quantity is under 30'
    END AS QuantityText
    FROM OrderDetails;

    <2>
    select
    max(case when columnname = 'FirstName' then value end) Firstname,
    max(case when columnname = 'Amount' then value end) Amount,
    max(case when columnname = 'PostalCode' then value end) PostalCode,
    max(case when columnname = 'LastName' then value end) LastName,
    max(case when columnname = 'AccountNumber' then value end) AccountNumber
    from yourtable

    <3>
    select coalesce(fieldname, '123') as fieldname 
    from tablename 

# 3
    >
    Set Up Spark
    1.	当开启了动态资源分配（spark.dynamicAllocation.enabled），num-executor选项将不再兼容，如果设置了num-executor，那么动态资源分配将被关闭。在Spark独立部署的集群中，你只需要在worker启动前设置 spark.shuffle.server.enabled 为true即可。
    spark.shuffle.service.enabled true   # 开启外部shuffle服务，开启这个服务可以保护executor的shuffle文件，安全移除executor，在Yarn模式下这个shuffle服务以org.apache.spark.yarn.network.YarnShuffleService实现
    spark.shuffle.service.port 7337 # Shuffle Service服务端口，必须和yarn-site中的一致
    spark.dynamicAllocation.enabled true  # 开启动态资源分配
    spark.dynamicAllocation.minExecutors 1  # 每个Application最小分配的executor数
    spark.dynamicAllocation.maxExecutors 30  # 每个Application最大并发分配的executor数
    spark.dynamicAllocation.schedulerBacklogTimeout 1s # 任务待时间（超时便申请新资源)默认60秒
    spark.dynamicAllocation.sustainedSchedulerBacklogTimeout 5s #  再次请求等待时间，默认60秒
    spark.dynamicAllocation.executorIdleTimeout # executor闲置时间（超过释放资源）默认600秒

# 3
>
    1. 避免创建重复的RDD，尽量复用同一份数据。

    2. 尽量避免使用shuffle类算子，因为shuffle操作是spark中最消耗性能的地方，reduceByKey、join、distinct、repartition等算子都会触发shuffle操作，尽量使用map类的非shuffle算子

    3. 用aggregateByKey和reduceByKey替代groupByKey,因为前两个是预聚合操作，会在每个节点本地对相同的key做聚合，等其他节点拉取所有节点上相同的key时，会大大减少磁盘IO以及网络开销。

    4. repartition适用于RDD[V], partitionBy适用于RDD[K, V]

    5. mapPartitions操作替代普通map，foreachPartitions替代foreach

    6. filter操作之后进行coalesce操作，可以减少RDD的partition数量

    7. 如果有RDD复用，尤其是该RDD需要花费比较长的时间，建议对该RDD做cache，若该RDD每个partition需要消耗很多内存，建议开启Kryo序列化机制(据说可节省2到5倍空间),若还是有比较大的内存开销，可将storage_level设置为MEMORY_AND_DISK_SER

    8. 尽量避免在一个Transformation中处理所有的逻辑，尽量分解成map、filter之类的操作

    9. 多个RDD进行union操作时，避免使用rdd.union(rdd).union(rdd).union(rdd)这种多重union，rdd.union只适合2个RDD合并，合并多个时采用SparkContext.union(Array(RDD))，避免union嵌套层数太多，导致的调用链路太长，耗时太久，且容易引发StackOverFlow

    10. spark中的Group/join/XXXByKey等操作，都可以指定partition的个数，不需要额外使用repartition和partitionBy函数

    11. 尽量保证每轮Stage里每个task处理的数据量>128M

    12. 如果2个RDD做join，其中一个数据量很小，可以采用Broadcast Join，将小的RDD数据collect到driver内存中，将其BroadCast到另外以RDD中，其他场景想优化后面会讲

    13. 2个RDD做笛卡尔积时，把小的RDD作为参数传入，如BigRDD.certesian(smallRDD)

    14. 若需要Broadcast一个大的对象到远端作为字典查询，可使用多executor-cores，大executor-memory。若将该占用内存较大的对象存储到外部系统，executor-cores=1， executor-memory=m(默认值2g),可以正常运行，那么当大字典占用空间为size(g)时，executor-memory为2*size，executor-cores=size/m(向上取整)

    15.如果对象太大无法BroadCast到远端，且需求是根据大的RDD中的key去索引小RDD中的key，可使用zipPartitions以hash join的方式实现，具体原理参考下一节的shuffle过程

    16. 如果需要在repartition重分区之后还要进行排序，可直接使用repartitionAndSortWithinPartitions，比分解操作效率高，因为它可以一边shuffle一边排序

# 4
shuffle性能优化
>
    3.1 什么是shuffle操作

    spark中的shuffle操作功能：将分布在集群中多个节点上的同一个key，拉取到同一个节点上，进行聚合或join操作，类似洗牌的操作。这些分布在各个存储节点上的数据重新打乱然后汇聚到不同节点的过程就是shuffle过程。 

    3.2 哪些操作中包含shuffle操作

    RDD的特性是不可变的带分区的记录集合，Spark提供了Transformation和Action两种操作RDD的方式。Transformation是生成新的RDD，包括map, flatMap, filter, union, sample, join, groupByKey, cogroup, ReduceByKey, cros, sortByKey, mapValues等；Action只是返回一个结果，包括collect，reduce，count，save，lookupKey等

    Spark所有的算子操作中是否使用shuffle过程要看计算后对应多少分区：

    若一个操作执行过程中，结果RDD的每个分区只依赖上一个RDD的同一个分区，即属于窄依赖，如map、filter、union等操作，这种情况是不需要进行shuffle的，同时还可以按照pipeline的方式，把一个分区上的多个操作放在同一个Task中进行
    若结果RDD的每个分区需要依赖上一个RDD的全部分区，即属于宽依赖，如repartition相关操作（repartition，coalesce）、*ByKey操作（groupByKey，ReduceByKey，combineByKey、aggregateByKey等）、join相关操作（cogroup，join）、distinct操作，这种依赖是需要进行shuffle操作的

    difference between map and mapPartitioin
    1. map()：每次处理一条数据。
    2. mapPartition()：每次处理一个分区的数据，这个分区的数据处理完后，原RDD中分区的数据才能释放，可能导致OOM。
    3. 开发指导：当内存空间较大的时候建议使用mapPartition()，以提高处理效率。
    
    difference between colapse and repartition
    1. colapse 作用：缩减分区数，用于大数据集过滤后，提高小数据集的执行效率。
    2. repartition 作用：根据分区数，重新通过网络随机洗牌所有数据。
    1. coalesce重新分区，可以选择是否进行shuffle过程。由参数shuffle: Boolean = false/true决定。
    2. repartition实际上是调用的coalesce，默认是进行shuffle的。源码如下：

    difference between ReduceByKey and groupByKey
    1. reduceByKey：按照key进行聚合，在shuffle之前有combine（预聚合）操作，返回结果是RDD[k,v].
    2. groupByKey：按照key进行分组，直接进行shuffle。
    用aggregateByKey和reduceByKey替代groupByKey,因为前两个是预聚合操作，会在每个节点本地对相同的key做聚合，等其他节点拉取所有节点上相同的key时，会大大减少磁盘IO以及网络开销。
# 5
>
    what is rdd
    an RDD is an immutable distributed collection of elements of your data, partitioned across nodes in your cluster that can be operated in parallel with a low-level API that offers transformations and actions.

    What is Hadoop?
    Apache Hadoop is an open source framework that is used to efficiently store and process large datasets ranging in size from gigabytes to petabytes of data. Instead of using one large computer to store and process the data, Hadoop allows clustering multiple computers to analyze massive datasets in parallel more quickly.

# 6
>
    硬件资源： 6 节点，每个节点16 cores, 64 GB 内存
    
    core的个数，决定一个executor能够并发任务的个数。所以通常认为，一个executor越多的并发任务能够得到更好的性能，但有研究显示一个应用并发任务超过5，导致更差的性能。所以core的个数暂设置为5个。5个core是表明executor并发任务的能力，并不是说一个系统有多少个core，即使我们一个CPU有32个core，也设置5个core不变。

    executor个数，接下来，一个executor分配 5 core,一个node有15 core，从而我们计算一个node上会有3 executor（15 / 5），然后通过每个node的executor个数得到整个任务可以分配的executors个数。
    我们有6个节点，每个节点3个executor，6 × 3 = 18个executors，额外预留1个executor给AM，最终要配置17个executors。
    最后spark-submit启动脚本中配置 –num-executors = 17

    memory，配置每个executor的内存，一个node，3 executor， 63G内存可用，所以每个executor可配置内存为63 / 3 = 21G

# 7
>     
    import org.apache.spark.{SparkConf, SparkContext}
    //1.创建SparkConf并设置App名称
    scala> val conf = new SparkConf().setAppName("WC")

    //2.创建SparkContext，该对象是提交Spark App的入口
    scala> val sc = new SparkContext(conf)

    scala> val lines = sc.textFile("file:///export/data/test.txt")
    scala> val words=lines.flatMap(line=>line.split(" "))
    scala> val wordAndOne = words.map(word=>(word,1))
    scala> val wordCount = wordAndOne.reduceByKey((a,b)=>a+b)
    wordCount: org.apache.spark.rdd.RDD[(String, Int)] = ShuffledRDD[22] at reduceByKey at <console>:25
    scala> wordCount.foreach(println)
        (spark,3)
        (hadoop,2)
        (scala,1)
        (itcast,3)
        (heima,1)

# 8
>
    udf
    //需要注意的是受Scala limit 22限制，自定义UDF最多接受22个参数，不过正常情况下完全够用了
    val ds = Seq((1, "foo"), (2, "bar")).toDF("id", "text")
    val toUpperCase = functions.udf((s: String) => s.toUpperCase)
    ds.withColumn("text", toUpperCase('text)).show()

    // 注册可以在sql语句中使用的UDF
    spark.udf.register("to_uppercase", (s: String) => s.toUpperCase())
    // 创建一张表
    Seq((1, "foo"), (2, "bar")).toDF("id", "text").createOrReplaceTempView("t_foo")
    spark.sql("select id, to_uppercase(text) from t_foo").show()


# 9
>
    1)	每个节点可以起一个或多个Executor。
    2)	每个Executor由若干core组成，每个Executor的每个core一次只能执行一个Task。
    3)	每个Task执行的结果就是生成了目标RDD的一个partiton。
    注意: 这里的core是虚拟的core而不是机器的物理CPU核，可以理解为就是Executor的一个工作线程。而 Task被执行的并发度 = Executor数目 * 每个Executor核数。至于partition的数目：
    1)	对于数据读入阶段，例如sc.textFile，输入文件被划分为多少InputSplit就会需要多少初始Task。
    2)	在Map阶段partition数目保持不变。
    3)	在Reduce阶段，RDD的聚合会触发shuffle操作，聚合后的RDD的partition数目跟具体操作有关，例如repartition操作会聚合成指定分区数，还有一些算子是可配置的。
    RDD在计算的时候，每个分区都会起一个task，所以rdd的分区数目决定了总的的task数目。申请的计算节点（Executor）数目和每个计算节点核数，决定了你同一时刻可以并行执行的task。
    比如的RDD有100个分区，那么计算的时候就会生成100个task，你的资源配置为10个计算节点，每个两2个核，同一时刻可以并行的task数目为20，计算这个RDD就需要5个轮次。如果计算资源不变，你有101个task的话，就需要6个轮次，在最后一轮中，只有一个task在执行，其余核都在空转。如果资源不变，你的RDD只有2个分区，那么同一时刻只有2个task运行，其余18个核空转，造成资源浪费。这就是在spark调优中，增大RDD分区数目，增大任务并行度的做法。

        