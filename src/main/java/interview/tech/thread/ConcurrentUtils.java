package interview.tech.thread;

import java.util.Random;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

public class ConcurrentUtils {
    public static void main(String[] args) throws InterruptedException {
        /**
         * `CountDownLatch` 是一个同步辅助工具，它允许一个或多个线程等待，直到在其他线程中执行的一组操作完成。它使用一个计数器，该计数器在初始化时被设置为一个指定的数值，
         * 每当调用 `countDown()` 方法时，计数器会递减。线程可以通过调用 `await()` 方法等待计数器归零。
         *
         * ### **关键特性**
         *
         * - **一次性使用**：`CountDownLatch` 在计数器达到零后，无法重置或重新使用。
         * - **线程协调**：适用于需要一个线程等待多个其他线程完成任务的场景。
         * - **简单易用**：API 简单，适合单一的等待事件。
         *
         * ### **适用场景**
         *
         * 1. **等待多个线程完成任务**：
         *    - **示例**：主线程需要等待多个子线程完成初始化任务后再继续执行。
         *    - **场景**：应用启动时，需要多个服务组件初始化完成。
         * 2. **启动多个线程同时执行**：
         *    - **示例**：确保多个线程在某个条件满足后同时开始执行任务。
         *    - **场景**：性能测试中，多个线程需要在同一时间点开始进行压力测试。
         * 3. **服务器启动**：
         *    - **示例**：确保服务器在所有资源准备就绪后开始接受请求。
         *    - **场景**：Web 服务器启动时，需要等待数据库连接池、缓存等组件初始化完成。
         */
        //演示 CountDownLatch，6个同学陆续离开教室之后，班长锁门
        CountDownLatch countDownLatch = new CountDownLatch(6);
        for (int i = 0; i <= 6; i++) {
            new Thread(
                    () -> {
                        System.out.println(Thread.currentThread().getName() + " 号同学离开了教室");
                        countDownLatch.countDown();
                    }, String.valueOf(i)
            ).start();
        }
        countDownLatch.await();
        System.out.println(Thread.currentThread().getName()+" 班长锁门走人了");

        Thread.sleep(5000);
        /**
         * ### **定义**
         *
         * `CyclicBarrier` 是一个同步辅助工具，它允许一组线程互相等待，直到所有线程都达到某个共同的屏障点。
         * 与 `CountDownLatch` 不同，`CyclicBarrier` 可以被重用，适用于循环使用的同步场景。
         *
         * ### **关键特性**
         *
         * - **可重用**：在所有线程到达屏障点后，`CyclicBarrier` 会自动重置，允许下一轮使用。
         * - **屏障动作**：可以在所有线程到达屏障点时执行一个特定的任务。
         * - **适用于多次同步**：适用于需要多次同步的场景，如多阶段计算。
         *
         * ### **适用场景**
         *
         * 1. **分阶段计算**：
         *    - **示例**：在并行计算中，每个阶段需要所有线程完成后才能进入下一阶段。
         *    - **场景**：图像处理中的多阶段滤镜应用，每个滤镜完成后需要所有线程同步。
         * 2. **并发测试与模拟**：
         *    - **示例**：确保多个线程在同一时间点开始执行特定任务。
         *    - **场景**：压力测试中，确保所有测试线程同时开始发送请求。
         * 3. **迭代算法**：
         *    - **示例**：算法的每一轮计算需要所有线程完成当前轮次后才能进入下一轮。
         *    - **场景**：分布式求解数值优化问题，每一轮迭代需要所有节点同步结果。
         */
        //集齐7颗龙珠就可以召唤神龙
        final int NUMBER = 7;
        CyclicBarrier cyclicBarrier =
                new CyclicBarrier(NUMBER,()->{
                    System.out.println("*****集齐7颗龙珠就可以召唤神龙");
                });

        //集齐七颗龙珠过程
        for (int i = 1; i <=7; i++) {
            new Thread(()->{
                try {
                    System.out.println(Thread.currentThread().getName()+" 星龙被收集到了");
                    //等待
                    cyclicBarrier.await();
                    System.out.println(Thread.currentThread().getName() + " done");
                } catch (Exception e) {
                    e.printStackTrace();
                }
            },String.valueOf(i)).start();
        }

        Thread.sleep(5000);

        /**
         * ### **定义**
         *
         * `Semaphore` 是一个控制对共享资源访问的计数信号量。它维护一组许可证，线程在访问共享资源前需要获取许可证，使用完毕后释放许可证。`Semaphore` 可以用来限制同时访问某个资源的线程数量。
         *
         * ### **关键特性**
         *
         * - **计数信号量**：允许多个线程同时访问有限的资源。
         * - **公平性**：可以配置为公平模式，按照线程请求许可证的顺序授予许可证，避免饥饿。
         * - **灵活性**：既可以作为互斥锁（binary semaphore），也可以允许多个并发访问（counting semaphore）。
         *
         * ### **适用场景**
         *
         * 1. **资源池管理**：
         *    - **示例**：限制同时访问数据库连接池的线程数量。
         *    - **场景**：数据库连接池中，最多允许一定数量的连接同时使用，避免资源耗尽。
         * 2. **流量控制与限流**：
         *    - **示例**：限制同时处理的请求数，防止系统过载。
         *    - **场景**：Web 服务器中，限制同时处理的 HTTP 请求数量。
         * 3. **实现互斥**：
         *    - **示例**：确保同一时间只有一个线程访问某个资源。
         *    - **场景**：文件写操作，确保同一时间只有一个线程可以写入文件。
         * 4. **信号同步**：
         *    - **示例**：协调多个线程的执行顺序。
         *    - **场景**：生产者-消费者模型中，控制生产者和消费者的同步。
         */
        //6辆汽车，停3个车位
        Semaphore semaphore = new Semaphore(3); //创建Semaphore，设置许可数量

        //模拟6辆汽车
        for (int i = 1; i <=6; i++) {
            new Thread(()->{
                try {
                    //抢占
                    semaphore.acquire();
                    System.out.println(Thread.currentThread().getName()+" 抢到了车位");
                    //设置随机停车时间
                    TimeUnit.SECONDS.sleep(new Random().nextInt(5));
                    System.out.println(Thread.currentThread().getName()+" ------离开了车位");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    //释放
                    semaphore.release();
                }
            },String.valueOf(i)).start();
        }

    }
}
