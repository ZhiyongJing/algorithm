package interview.tech.thread.producer_consumer;

import java.util.concurrent.PriorityBlockingQueue;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;

/**
 * 一个线程安全的 Producer-Consumer 模型示例，其中使用 PriorityBlockingQueue 来处理具有不同优先级的任务。
 * 任务具有不同的优先级，较小的优先级值代表更高的优先级（即优先处理）。
 */
public class ProducerConsumerWithPriority {

    /**
     * Task 类实现了 Runnable 和 Comparable 接口，
     * 既可以作为一个待执行的任务，又可以按照优先级在 PriorityBlockingQueue 中排序。
     */
    static class Task implements Runnable, Comparable<Task> {
        private final int priority;     // 优先级：较小的值代表更高的优先级
        private final String taskName;  // 任务名称，用于标识任务
        private final long timestamp;   // 时间戳，用于在优先级相同时保持 FIFO 顺序

        public Task(String taskName, int priority) {
            this.taskName = taskName;
            this.priority = priority;
            this.timestamp = System.nanoTime();
        }

        /**
         * 任务的实际执行逻辑，这里仅仅打印输出任务信息并模拟一定的执行耗时。
         */
        @Override
        public void run() {
            System.out.println(Thread.currentThread().getName() +
                    " is executing " + taskName + " with priority " + priority);
            try {
                // 模拟任务执行需要一定时间
                TimeUnit.MILLISECONDS.sleep(100);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }

        /**
         * 定义任务的排序规则：
         * 1. 根据优先级（priority）排序，较小的优先级值排在前面；
         * 2. 如果优先级相同，则根据任务的时间戳保持先进先出顺序。
         */
        @Override
        public int compareTo(Task other) {
            if (this.priority != other.priority) {
                return Integer.compare(this.priority, other.priority);
            } else {
                return Long.compare(this.timestamp, other.timestamp);
            }
        }
    }

    /**
     * Producer 类实现 Runnable，用于模拟生产者，
     * 将任务按照随机优先级添加到共享的 PriorityBlockingQueue 中。
     */
    static class Producer implements Runnable {
        private final PriorityBlockingQueue<Task> queue;
        private final int producerId;

        public Producer(PriorityBlockingQueue<Task> queue, int producerId) {
            this.queue = queue;
            this.producerId = producerId;
        }

        @Override
        public void run() {
            // 模拟每个生产者生产 10 个任务
            for (int i = 0; i < 10; i++) {
                // 随机生成任务优先级（例如 1~10，1 为最高优先级）
                int priority = ThreadLocalRandom.current().nextInt(1, 11);
                String taskName = "Task-" + producerId + "-" + i;
                Task task = new Task(taskName, priority);
                // 将任务放入线程安全的队列中
                queue.put(task);
                System.out.println("Producer " + producerId + " produced " + taskName + " with priority " + priority);
                try {
                    // 模拟生产者在生产任务时的间隔
                    TimeUnit.MILLISECONDS.sleep(ThreadLocalRandom.current().nextInt(50, 150));
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }
        }
    }

    /**
     * Consumer 类实现 Runnable，用于模拟消费者，
     * 从共享的 PriorityBlockingQueue 中取出任务并执行。
     */
    static class Consumer implements Runnable {
        private final PriorityBlockingQueue<Task> queue;
        private final int consumerId;

        public Consumer(PriorityBlockingQueue<Task> queue, int consumerId) {
            this.queue = queue;
            this.consumerId = consumerId;
        }

        @Override
        public void run() {
            try {
                // 消费者不断从队列中取任务执行，直到线程被中断
                while (true) {
                    // 从队列中取出任务，如果队列为空则阻塞等待
                    Task task = queue.take();
                    System.out.println("Consumer " + consumerId + " took " + task.taskName +
                            " with priority " + task.priority);
                    // 执行任务
                    task.run();
                }
            } catch (InterruptedException e) {
                // 如果线程被中断，则退出循环
                Thread.currentThread().interrupt();
            }
        }
    }

    /**
     * main 方法中创建了共享的 PriorityBlockingQueue，
     * 启动多个生产者和消费者线程，模拟多线程环境下的任务生产与消费。
     */
    public static void main(String[] args) {
        // 创建一个线程安全的优先级阻塞队列
        PriorityBlockingQueue<Task> queue = new PriorityBlockingQueue<>();

        // 启动 2 个生产者线程
        for (int i = 0; i < 2; i++) {
            Thread producerThread = new Thread(new Producer(queue, i), "Producer-" + i);
            producerThread.start();
        }

        // 启动 3 个消费者线程
        for (int i = 0; i < 3; i++) {
            Thread consumerThread = new Thread(new Consumer(queue, i), "Consumer-" + i);
            consumerThread.start();
        }

        // 为了让示例可以结束，这里简单地设置主线程等待一段时间后退出
        // 实际生产环境中可能需要更复杂的关闭机制（例如 poison pill）
        try {
            TimeUnit.SECONDS.sleep(5);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        System.out.println("Main thread exiting, simulation complete.");
        // 注意：本示例中消费者线程并没有被显式关闭，如果需要优雅退出可使用额外机制通知消费者结束运行
    }
}

