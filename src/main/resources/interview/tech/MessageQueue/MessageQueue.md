## 1. Kafka

### 1.  Kafka definition 

> Apache Kafka is an open-source distributed streaming system used for stream processing, real-time data pipelines, and data integration at scale.
>
> ![image-20240325152828970](MessageQueue.assets/kafka-structure.png)

### 2. Kafka producer-生产者

> 在消息发送的过程中，涉及到了两个线程——**main** 线程和 **Sender** 线程。在 main 线程中创建了一个双端队列 **RecordAccumulator**。main 线程将消息发送给 RecordAccumulator，Sender 线程不断从RecordAccumulator 中拉取消息发送到Kafka Broker。
>
> ![image-20240325153049215](MessageQueue.assets/producer.png)
>
> **异步发送,带回调函数（可不带）：**回调函数会在 producer 收到 ack 时调用，为异步调用，该方法有两个参数，分别是元数据信息（RecordMetadata）和异常信息（Exception） ，如果 Exception 为 null，说明消息发送成功，如果Exception 不为null，说明消息发送失败
>
> ```java
> for (int i = 0; i < 500; i++) {
>  kafkaProducer.send(new ProducerRecord<>("first", "atguigu" + i), new Callback() {
>      @Override
>      public void onCompletion(RecordMetadata metadata, Exception exception) {
>          if (exception == null){
>              System.out.println("主题： "+metadata.topic() + " 分区： "+ metadata.partition());
>          }
>      }
>  });
>  Thread.sleep(2);
> }
> ```
>
> **同步发送： **在异步发送的基础上，再调用一下 get()方法即可
>
> ```java
> // 2 发送数据
> for (int i = 0; i < 5; i++) {
>  kafkaProducer.send(new ProducerRecord<>("first","atguigu"+i)).get();
> }
> ```

### 3. Kafka Partition for producer-生产者分区

> （1）便于合理使用存储资源，每个Partition在一个Broker上存储，可以把海量的数据按照分区存储在多台Broker上。合理控制分区的任务，可以实现负载均衡的效果。
>
> （2）提高并行度，生产者可以以分区为单位发送数据；消费者可以以分区为单位进行消费数据。
>
> ![image-20240325155825023](MessageQueue.assets/partition_for_producer.png)
>
> ![image-20240325155938824](MessageQueue.assets/partition_stratety_for_producer.png)

### 4. Kafka 生产者提高吞吐量

> ![image-20240328223924455](MessageQueue.assets/image-20240328223924455.png)

### 5. Kafka 生产者提高数据可靠性

> 1. **ACK 应答级别**
>
>    在生产环境，**acks=0**很少使用；**acks=1**，一般用于传输普通日志，允许丢个别数据；**acks=-1**，一般用于传输和钱相关的数据
>
>    对可靠性要求比较高的场景。
>
> ![image-20240328224127090](MessageQueue.assets/image-20240328224127090.png)
>
> 2. **数据去重1： 幂等性**
>
>    幂等性就是指Producer不论向Broker发送多少次重复数据，Broker端都只会持久化一条，保证了不重复。
>
>    精确一次（**Exactly Once**）**=** 幂等性**+** 至少一次（**ack=-1 +** 分区副本数**>=2 + ISR**最小副本数量**>=2**）。
>
>    重复数据的判断标准：具有<PID, Partition, SeqNumber>相同主键的消息提交时，Broker只会持久化一条。其中PID是Kafka每次重启都会分配一个新的；Partition 表示分区号；Sequence Number是单调自增的。所以**幂等性只能保证的是在单分区单会话内不重复**。 开启参数**enable.idempotence** 默认为true，false 关闭。
>
>    ![image-20240328224633977](MessageQueue.assets/image-20240328224633977.png)
>
> 3. **数据去重2: 开启事物**
>
>    ![image-20240328224901328](MessageQueue.assets/image-20240328224901328.png)
>
> 4. **保证数据有序**
>
>    ![image-20240328225126156](MessageQueue.assets/image-20240328225126156.png)

### 6. Kafka Broker

> 1. 工作流程![image-20240328225236925](MessageQueue.assets/image-20240328225236925.png)
>
> 2. Follower故障处理细节
>    ![image-20240328225718795](MessageQueue.assets/image-20240328225718795.png)
>
> 3. Leader故障处理细节
>    ![image-20240328225733845](MessageQueue.assets/image-20240328225733845.png)
>
> 4. 文件存储机制
>
>    ![image-20240328230006845](MessageQueue.assets/image-20240328230006845.png)
>
> 5. 页缓存 + 零拷贝技术
>
>    **零拷贝**：Kafka的数据加工处理操作交由Kafka生产者和Kafka消费者处理。Kafka Broker应用层不关心存储的数据，所以就不用走应用层，传输效率高。
>
>    **PageCache**页缓存：Kafka重度依赖底层操作系统提供的PageCache功能。当上层有写操作时，操作系统只是将数据写入
>
>    PageCache。当读操作发生时，先从PageCache中查找，如果找不到，再去磁盘中读取。实际上PageCache是把尽可能多的空闲内存都当做了磁盘缓存来使用。

### 7. Kafka consumer

> 1. 工作流程
>
>    Consumer Group（CG）：消费者组，由多个consumer组成。形成一个消费者组的条件，是所有消费者的groupid相同。
>
>    • 消费者组内每个消费者负责消费不同分区的数据，一个分区只能由一个组内消费者消费。
>
>    • 消费者组之间互不影响。所有的消费者都属于某个消费者组，即消费者组是逻辑上的一个订阅者。
>
>    ![image-20240328231120828](MessageQueue.assets/image-20240328231120828.png)
>
> 2. 消费者组初始化流程
>
>    ![image-20240328231741901](MessageQueue.assets/image-20240328231741901.png)
>
>    ![image-20240328231803085](MessageQueue.assets/image-20240328231803085.png)

### 8. Kafka consumer分区策略以及再平衡

> 1. 一个consumer group中有多个consumer组成，一个 topic有多个partition组成，现在的问题是，到底由哪个consumer来消费哪个partition的数据。
>
> 2. Kafka有四种主流的分区分配策略： Range、RoundRobin、Sticky、CooperativeSticky。可以通过配置参数partition.assignment.strategy，修改分区的分配策略。**默认策略是Range + CooperativeSticky**。Kafka可以同时使用多个分区分配策略。
>
>    1. **Range** 分区策略
>
>       ![image-20240331164901221](MessageQueue.assets/image-20240331164901221.png)
>
>    2. **RoundRobin** 分区策略
>
>       ![image-20240331165337582](MessageQueue.assets/image-20240331165337582.png)
>
>    3. **Sticky**
>
>       粘性分区定义：可以理解为分配的结果带有“粘性的”。即在执行一次新的分配之前，考虑上一次分配的结果，尽量少的调整分配的变动，可以节省大量的开销。在出现同一消费者组内消费者出现问题的时候，会尽量保持原有分配的分区不变化。

### 9. Kafka Cousumer offset-偏移量

> 从0.9版本开始，consumer默认将offset保存在Kafka一个内置的topic中，该topic为__consumer_offsets. __consumer_offsets 主题里面采用 key 和 value 的方式存储数据。key 是 group.id+topic+分区号，value 就是当前 offset 的值。每隔一段时间，kafka 内部会对这个 topic 进行compact，也就是每个group.id+topic+分区号就保留最新数据。
>
> 1. 为了使我们能够专注于自己的业务逻辑，Kafka提供了自动提交offset的功能。自动提交offset的相关参数：
>
>    **enable.auto.commit**：是否开启自动提交offset功能，默认是true
>
>     **auto.commit.interval.ms**：自动提交offset的时间间隔，默认是5s
>
> 2. 手动提交offset
>
>    手动提交offset的方法有两种：分别是commitSync（同步提交）和commitAsync（异步提交）。两者的相同点是，都会将本次提交的一批数据最高的偏移量提交；不同点是，同步提交阻塞当前线程，一直到提交成功，并且会自动失败重试（由不可控因素导致，也会出现提交失败）；而异步提交则没有失败重试机制，故有可能提交失败。
>
>    1. 同步提交**offset**由于同步提交 offset 有失败重试机制，故更加可靠，但是由于一直等待提交结果，提交的效率比较低。
>    2. 异步提交**offset**虽然同步提交 offset 更可靠一些，但是由于其会阻塞当前线程，直到提交成功。因此吞吐量会受到很大的影响。因此更多的情况下，会选用异步提交 offset 的方式
>
> 指定 **Offset** 消费：
>
> 1. auto.offset.reset = earliest | latest | none 默认是latest。当 Kafka 中没有初始偏移量（消费者组第一次消费）或服务器上不再存在当前偏移量时（例如该数据已被删除）
>
>    （1）earliest：自动将偏移量重置为最早的偏移量，--from-beginning。
>
>    （2）latest（默认值） ：自动将偏移量重置为最新偏移量
>
>    （3）none：如果未找到消费者组的先前偏移量，则向消费者抛出异常。
>
>    （4）任意指定offset 位移开始消费
>
> 指定**时间**消费：
>
> ​	在生产环境中，会遇到最近消费的几个小时数据异常，想重新按照时间消费。
>
> 漏消费和重复消费（使用**消费者事务**解决）：
>
> ​	重复消费：已经消费了数据，但是 offset 没提交。
>
> ​	漏消费：先提交offset 后消费，有可能会造成数据的漏消费。
>
> ​	![image-20240331170912260](MessageQueue.assets/image-20240331170912260.png)
>
> 

### 10. Kafka 消费端提高消费数据能力

> 1）如果是Kafka消费能力不足，可以考虑增加Topic的分区数，并且同时提升消费组的消费者数量，消费者数= 分区数（缺一不可）
>
> 2）如果是下游的数据处理不及时：提高每批次拉取的数量。批次拉取数据过少（拉取数据/处理时间 < 生产速度），使处理的数据小于生产的数据，也会造成数据积压。
>
> `fetch.max.bytes`
>
> `max.poll.records`

### 11. Kafka 总体调优

> 1. 如何提升吞吐量
>
>    > 1. 提升生产吞吐量
>    >
>    >    > （1）buffer.memory：发送消息的缓冲区大小，默认值是32m，可以增加到64m。
>    >    >
>    >    > （2） batch.size：默认是 16k。如果batch 设置太小，会导致频繁网络请求，吞吐量下降；如果batch 太大，会导致一条消息需要等待很久才能被发送出去，增加网络延时。
>    >    >
>    >    > （3）linger.ms，这个值默认是 0，意思就是消息必须立即被发送。一般设置一个 5-100毫秒。如果linger.ms 设置的太小，会导致频繁网络请求，吞吐量下降；如果 linger.ms 太长，会导致一条消息需要等待很久才能被发送出去，增加网络延时。
>    >    >
>    >    > （4）compression.type：默认是 none，不压缩，但是也可以使用 lz4 压缩，效率还是不错的，压缩之后可以减小数据量，提升吞吐量，但是会加大 producer 端的CPU 开销。
>    >
>    > 2. 增加分区
>    >
>    > 3. 消费者提高吞吐量
>    >
>    >    > （1）调整fetch.max.bytes 大小，默认是50m。
>    >    >
>    >    > （2）调整max.poll.records 大小，默认是500 条。
>    >
>    > 4. 增加下游消费者处理能力
>
> 2. 数据精准一次
>
>    > 1）生产者角度
>    >
>    > acks 设置为-1 （acks=-1）。
>    >
>    > 幂等性（enable.idempotence = true） + 事务 。
>    >
>    > 2）broker 服务端角度
>    >
>    > 分区副本大于等于 2 （--replication-factor 2）。
>    >
>    > ISR 里应答的最小副本数量大于等于 2 （min.insync.replicas = 2）。
>    >
>    > **3**）消费者
>    >
>    > 事务 + 手动提交 offset （enable.auto.commit = false）。
>    >
>    > 消费者输出的目的地必须支持事务（MySQL、Kafka）。
>
> 3.  合理设置分区数
>
>     > （1）创建一个只有1 个分区的topic。
>     >
>     > （2）测试这个topic 的producer 吞吐量和consumer 吞吐量。
>     >
>     > （3）假设他们的值分别是 Tp 和Tc，单位可以是MB/s。
>     >
>     > （4）然后假设总的目标吞吐量是 Tt，那么分区数 = Tt / min（Tp，Tc）。
>     >
>     > 例如：producer 吞吐量 = 20m/s；consumer 吞吐量 = 50m/s，期望吞吐量100m/s；
>     >
>     > 分区数 = 100 / 20 = 5 分区
>     >
>     > 分区数一般设置为：3-10 个
>     >
>     > 分区数不是越多越好，也不是越少越好，需要搭建完集群，进行压测，再灵活调整分区
>     >
>     > 个数

### 12. Kafka 消息不丢失

> - **生产者端的配置与优化**
>
> > 1. 保消息持久化（acks = all） 
> >
> >    > **`acks` 参数**控制生产者在消息被认为“已提交”之前，需要等待 Kafka 集群的响应。
> >    >
> >    > - **`acks=0`**：生产者不会等待任何确认，消息可能会丢失。
> >    > - **`acks=1`**：生产者会等待领导副本确认，但如果领导副本失败，消息可能会丢失。
> >    > - **`acks=all`** 或 **`acks=-1`**：生产者会等待所有同步副本确认，提供最高的可靠性，确保消息不会丢失（前提是 Kafka 集群的副本配置正确）。
> >
> > 2. 启用幂等性生产者（Idempotent Producer）
> >
> >    > **幂等性生产者**确保在重试发送的过程中，消息不会被重复写入，即使网络问题导致消息重传.
> >    >
> >    > enable.idempotence=true
> >
> > 3. 配置重试机制（Retries）
> >
> >    > **重试机制**允许生产者在发送失败时自动重试，增加消息成功发送的概率。
> >    >
> >    > **注意事项**：
> >    >
> >    > - 配置合适的重试次数和间隔，避免过多重试导致性能问题。
> >    > - 与幂等性生产者结合使用，确保重试过程中消息不会重复。
> >    >
> >    > retries=5
> >    >
> >    > retry.backoff.ms=100
> >
> > 4. 使用事务（Transactions）
> >
> >    > **事务**确保一系列消息的原子性提交，要么全部成功，要么全部失败。
> >    >
> >    > - 适用场景：
> >    >   - 需要在多个主题或分区中保证消息的一致性。
> >    >   - 实现精确一次（Exactly-Once）语义。
>
> - **集群(Broker)的配置与优化**
>
> > 1. 分区与副本（Partitions & Replicas）
> >
> >    > **分区数（Partitions）**：
> >    >
> >    > - **作用**：增加并行度，提升吞吐量。
> >    > - **建议**：根据预期的消费并发度和数据量，合理设置分区数。分区数应大于或等于消费者实例数，以充分利用消费者并行能力。
> >    >
> >    > **副本数（Replicas）**：
> >    >
> >    > - **作用**：提高数据的可用性和容错性。
> >    > - **建议**：通常设置副本数为3，确保在一个副本故障时，其他副本可以接管。
> >
> > 2. 副本因子（Replication Factor）
> >
> >    > **定义**：每个分区拥有的副本数量。
> >    >
> >    > **建议**：
> >    >
> >    > - **生产环境**：副本因子至少为3，以确保高可用性。
> >    > - **测试环境**：根据需要，可以设置为较低的值以节省资源。
> >
> > 3. 确保 ISR（In-Sync Replicas）数量
> >
> >    > **ISR 定义**：与领导副本保持同步的所有副本集合。
> >    >
> >    > **监控方法**：
> >    >
> >    > - 使用 Kafka 的监控工具（如 Prometheus、Grafana）监控 ISR 状态。
> >    > - 确保 ISR 数量始终满足预期，避免过多副本落后导致数据不可用。
> >
> > 4. 日志保留策略（Log Retention Policies）
> >
> >    > **日志保留时间**：
> >    >
> >    > - **配置参数**：`log.retention.hours`
> >    > - **建议**：根据业务需求设置合理的保留时间，确保消息在被消费前不会被删除。
> >    >
> >    > **日志保留大小**：
> >    >
> >    > - **配置参数**：`log.retention.bytes`
> >    > - **建议**：结合保留时间设置，确保磁盘空间充足。
>
> - **消费者端的配置与优化**
>
> > 1. 手动提交偏移量（Manual Offset Commit）
> >
> >    > **手动提交偏移量**允许消费者在成功处理消息后，才提交偏移量，确保消息不会在处理前被视为已消费。
> >    >
> >    > enable.auto.commit=false
> >    >
> >    > **实现步骤**：
> >    >
> >    > 1. **拉取消息并处理**。
> >    > 2. 手动提交偏移量：
> >    >    - **同步提交**：`consumer.commitSync();`
> >    >    - **异步提交**：`consumer.commitAsync();`
> >
> > 2. 幂等性与事务处理
> >
> >    > **幂等性**：确保消费者处理同一条消息多次不会导致数据不一致。
> >    >
> >    > - **实现方法**：在业务逻辑中加入去重机制，或在数据库操作中使用唯一约束。
> >    >
> >    > **事务处理**：在处理消息时，结合数据库事务，确保数据操作的原子性。
> >    >
> >    > > `````java
> >    > > try {
> >    > >     connection.setAutoCommit(false);
> >    > >     // 处理消息
> >    > >     processRecord(record);
> >    > >     // 提交数据库事务
> >    > >     connection.commit();
> >    > >     // 提交 Kafka 偏移量
> >    > >     consumer.commitSync();
> >    > > } catch (Exception e) {
> >    > >     connection.rollback();
> >    > >     // 处理异常
> >    > > };
> >    > > `````
> >
> > 3. 消费者故障恢复
> >
> >    > **确保消费者实例的高可用性**：
> >    >
> >    > - 部署多个消费者实例，确保其中一个故障时，其他实例能够接管其分区。
> >    > - 确保消费者组内的消费者数量不超过主题的分区数，以充分利用并行消费能力
> >    > - 使用自动重启机制（如 Kubernetes 的 liveness 和 readiness probes）确保消费者实例的持续运行。
> >    >
> >    > **处理重复消费**：
> >    >
> >    > - 即使使用手动提交偏移量，消费者故障后，未提交的偏移量会导致消息被重新消费。
> >    > - 通过幂等性处理确保消息处理的安全性。
> >    >
> >    > **分区再平衡**：
> >    >
> >    > - 在消费者组内添加或移除消费者时，Kafka 会自动进行分区再平衡，可能导致消费者短暂的消费中断。
> >    > - 设计消费者应用程序以处理分区再平衡的情况，避免数据丢失或重复处理。

