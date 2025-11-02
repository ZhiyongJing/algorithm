package interview.tech.thread;

/**
 * **综合推荐**
 *
 * 在现代Java开发中，**推荐使用`ExecutorService`和线程池**来管理线程。这种方式提供了高效、灵活的线程管理能力，适用于大多数并发任务场景。
 * 结合`Callable`和`Future`，可以方便地获取任务结果和处理异常。即方法5。
 *
 * 对于更复杂的异步任务流程，**推荐使用`CompletableFuture`**。它提供了强大的任务组合和回调机制，适合构建复杂的异步和并行处理逻辑。
 *
 * 其他方法如继承`Thread`类或实现`Runnable`接口，适用于简单或特定的场景，但在大规模和复杂应用中不如`ExecutorService`和`CompletableFuture`高效和灵活。
 *
 * **总结**：
 * - **优先选择**：`ExecutorService`和线程池、`CompletableFuture`。
 * - **适用选择**：实现`Runnable`或`Callable`接口、使用匿名内部类或Lambda表达式。
 * - **慎用选择**：继承`Thread`类、使用`FutureTask`，除非在特定场景下确有需要。
 */

import java.util.concurrent.*;

public class ThreadImpl {
    /**
     * 方法1：继承Thread类, 并重写了 run() 方法。
     * 优点：
     * - 简单直观，易于理解和使用。
     * - 可以直接调用Thread类的方法，如start()、sleep()等。
     * 缺点：
     * - Java是单继承机制，继承Thread类后，无法再继承其他类，限制了类的扩展性。
     * - 不利于资源共享，多个线程无法共享同一个对象的资源。
     */
    class ExtendThread extends Thread{
        @Override
        public void run(){
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            // 线程执行的任务
            System.out.println("继承Thread类的线程" + Thread.currentThread().getId() + "正在运行");
        }
    }

    /**
     * 方法2：实现Runnable接口. 有时我们要同时融合实现Runnable接口和Thread子类两种方式。
     *       例如，实现了Thread子类的实例可以执行多个实现了Runnable接口的线程。一个典型的应用就是线程池.
     * 优点：
     * - 避免单继承限制，可以实现Runnable接口，同时继承其他类。
     * - 资源共享方便，多个线程可以共享同一个Runnable对象的资源。
     * - 符合面向接口编程，增强了代码的可维护性和可扩展性。
     * 缺点：
     * - 相对于继承Thread类，代码稍显复杂。
     * - 无法直接访问Thread类的方法，需要通过Thread对象来调用。
     */
    class MyRunnable implements Runnable{
        @Override
        public void run() {
            for(int i = 0; i < 5; i++) {
                try {
                    Thread.sleep(5000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                System.out.println("实现Runnable接口的线程" + Thread.currentThread().getId() + "正在运行");
            }
        }
    }

    /**
     * 方法3：使用匿名内部类。
     * 优点：
     * - 代码简洁，减少了冗余的类定义。适用于短小的线程任务，快速编写和测试。
     * 缺点：
     * - 对于复杂的任务，匿名内部类可能导致代码难以阅读和维护。由于是匿名的，无法在其他地方复用相同的逻辑。
     */
    Thread anonymousThread = new Thread(new Runnable() {
        @Override
        public void run() {
            System.out.println("匿名Runnable线程" + Thread.currentThread().getId() + "正在运行");
        }
    });

    /**
     * 方法4：（使用Lambda表达式）。
     * 优点：
     * - 代码简洁，减少了冗余的类定义。适用于短小的线程任务，快速编写和测试。
     * 缺点：
     * - 需要理解 Lambda 表达式的语法和概念。对于复杂的任务，可能导致“回调地狱”现象，使代码难以理解和维护。
     */
    Thread lambdaThread = new Thread(() -> {
        System.out.println("Lambda Runnable线程" + Thread.currentThread().getId() + "正在运行");
    });

    /**
     * 方法5：实现Callable接口与Future
     * 优点：
     * - 可以返回结果，适用于需要线程执行结果的场景。
     * - 支持抛出异常，增强了错误处理能力。
     * - 与线程池结合使用，进行更高效的线程管理。
     * 缺点：
     * - 使用较为复杂，需要配合ExecutorService和Future。
     * - 需要手动管理线程池的生命周期，防止资源泄漏。
     */
    class MyCallable implements Callable<Integer>{
        @Override
        public Integer call() throws Exception {
            for(int i = 0; i < 5; i++){
                Thread.sleep(2000);
                System.out.println("实现Callable接口的线程" + Thread.currentThread().getId() + "正在运行");

            }
            return 100;
        }
    }

    /**
     * 方法6：使用ExecutorService和线程池
     * 优点：
     * - 高效管理线程，复用线程，减少资源开销。
     * - 提供丰富的线程池配置选项，适应不同需求。
     * - 支持任务调度、未来结果获取等高级功能，便于管理复杂的并发任务。
     * 缺点：
     * - 需要理解和配置线程池的各项参数，增加了实现难度。
     * - 需要手动关闭线程池，防止资源泄漏和线程泄露。
     *
     * 示例已在main方法中展示
     */
    ExecutorService fixedThreadPool = Executors.newFixedThreadPool(3); // 创建固定大小的线程池


    /**
     * 方法7：使用FutureTask. Java 库具有具体的 FutureTask 类型，该类型实现 Runnable 和 Future，并方便地将两种功能组合在一起。
     *       可以通过为其构造函数提供 Callable 来创建FutureTask。然后，将 FutureTask 对象提供给 Thread 的构造函数以创建Thread 对象。因此，间接地使用 Callable 创建线程.
     *       一般 FutureTask 多用于耗时的计算，主线程可以在完成自己的任务后，再去获取结果。仅在计算完成时才能检索结果；如果计算尚未完成，则阻塞 get 方法.
     *       get 只计算一次,因此 get 方法放到最后.一旦计算完成，就不能再重新开始或取消计算
     * 优点：
     * - 可以封装Callable或Runnable任务，提供Future的功能，如获取结果和取消任务。
     * - 可以灵活地与Thread结合使用，增强线程的功能。
     * - 支持任务的启动和执行状态的监控。
     * 缺点：
     * - 使用较为复杂，需要手动管理Thread和FutureTask的生命周期。
     * - 不如ExecutorService和线程池高效，特别是在大量线程管理时。
     *
     * 示例已在main方法中展示
     */


    /**
     * 方法8：使用ForkJoinPool和RecursiveTask
     * 优点：
     * - 适合处理大规模的并行任务，充分利用多核处理器的性能。
     * - 自动处理任务的拆分和合并，简化并行编程。
     * - 适合分治算法，如归并排序、快速排序等。
     * 缺点：
     * - 适用场景有限，主要适用于分治算法。
     * - 学习曲线陡峭，需要理解ForkJoin框架的工作原理和任务拆分策略。
     */
    class SumTask extends RecursiveTask<Integer>{
        private  int[] numbers;
        private int start;
        private int end;

        public SumTask(int[] numbers, int start, int end){
            this.numbers = numbers;
            this.start = start;
            this.end = end;
        }
        @Override
        protected Integer compute() {
            if (end - start <= 2) { // 任务足够小，直接计算
                int sum = 0;
                for (int i = start; i < end; i++) {
                    sum += numbers[i];
                }
                return sum;
            } else { // 拆分任务
                int mid = (start + end) / 2;
                SumTask leftTask = new SumTask(numbers, start, mid);
                SumTask rightTask = new SumTask(numbers, mid, end);
                leftTask.fork(); // 异步执行左半部分
                int rightResult = rightTask.compute(); // 同步执行右半部分
                int leftResult = leftTask.join(); // 获取左半部分结果
                return leftResult + rightResult;
            }
        }
    }

    /**
     * 方法9：使用CompletableFuture
     * 优点：
     * - 支持链式调用，能够方便地组合多个异步任务。
     * - 提供丰富的回调方法，如thenApply、thenAccept、thenCompose等，增强了代码的可读性和维护性。
     * - 支持异常处理机制，能够优雅地处理异步任务中的错误。
     * 缺点：
     * - 对于复杂的异步逻辑，链式调用可能导致“回调地狱”现象。
     * - 异步执行和链式调用使得调试过程变得复杂，难以跟踪错误源头。
     */



    public static void main(String[] args) {
        System.out.println("Main Thread" +Thread.currentThread().getId()+ "正在运行");
        ThreadImpl testThread = new ThreadImpl();

//        // 示例1：继承Thread类
//        ExtendThread myThread1 = testThread.new ExtendThread();
//        ExtendThread myThread2 = testThread.new ExtendThread();
//        myThread1.start();
//        myThread2.start();

//        // 示例2：实现Runnable接口
//        Runnable myRunnable1 = testThread.new MyRunnable();
//        Runnable myRunnable2 = testThread.new MyRunnable();
//        Thread runnableThread1 = new Thread(myRunnable1);
//        Thread runnableThread2 = new Thread(myRunnable2);
//        runnableThread1.start();
//        runnableThread2.start();
//
//        // 示例3：使用匿名内部类
//        testThread.anonymousThread.start();
//
//        //示例4（使用Lambda表达式）
//        testThread.lambdaThread.start();

        // 示例5：实现Callable接口与Future
//        ExecutorService executorService = Executors.newSingleThreadExecutor();
        ExecutorService executorService = Executors.newFixedThreadPool(2);
        Callable<Integer> myCallable1 = testThread.new MyCallable();
        Callable<Integer> myCallable2 = testThread.new MyCallable();
        Future<Integer> future1 = executorService.submit(myCallable1);
        Future<Integer> future2 = executorService.submit(myCallable2);
        try {
            Integer i = future1.get();
            Integer j = future2.get();
            System.out.println("返回结果" + i);
            System.out.println("返回结果" + i);
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        } finally {
            executorService.shutdown();
        }
//
//        // 示例6：使用ExecutorService和线程池
//        for (int i = 0; i < 5; i++) {
//            int taskId = i;
//            testThread.fixedThreadPool.submit(() -> {
//                System.out.println("线程池任务 " + taskId + " 正在运行");
//            });
//        }
//        testThread.fixedThreadPool.shutdown(); // 关闭线程池
//
//        // 示例8：使用FutureTask
//        Runnable runnableTask = new Runnable() {
//            @Override
//            public void run() {
//                System.out.println("匿名Runnable线程" + Thread.currentThread().getId() + "正在运行");
//            }
//        };
//        // 使用futureTask 调用runnable
//        FutureTask<String> futureRunableTask = new FutureTask<>(runnableTask, "runnableTask任务完成");// 由于Runnable没有返回值，需要提供一个预定义的结果，例如"任务完成"
//        Thread futureRunnableTaskThread = new Thread(futureRunableTask);// 使用Thread启动FutureTask
//        futureRunnableTaskThread.start();
//        Callable<String> callableTask = () -> { // 创建一个Callable任务
//            Thread.sleep(500); // 模拟耗时操作
//            return "FutureTask执行结果";
//        };
//        // 使用futureTask 调用callable
//        FutureTask<String> futureCallableTask = new FutureTask<>(callableTask); // 创建FutureTask对象
//        Thread futureCallableTaskThread = new Thread(futureCallableTask);// 使用Thread启动FutureTask
//        futureCallableTaskThread.start(); // 启动线程
//        try {
//            // 获取FutureTask的执行结果
//            String futureTaskResult = futureCallableTask.get();
//            System.out.println("FutureTask返回结果: " + futureTaskResult); // 输出结果
//        } catch (InterruptedException | ExecutionException e) {
//            e.printStackTrace();
//        }
//
//
//        // 示例8：使用ForkJoinPool和RecursiveTask
//        ForkJoinPool forkJoinPool = new ForkJoinPool(); // 创建ForkJoinPool
//        int[] numbers = {1, 2, 3, 4, 5, 6, 7, 8};
//        SumTask sumTask = testThread.new SumTask(numbers, 0, numbers.length); // 创建任务
//        int sumResult = forkJoinPool.invoke(sumTask); // 执行任务
//        System.out.println("ForkJoinPool计算总和: " + sumResult); // 输出总和
//
//
//        // 示例9：使用CompletableFuture
//        CompletableFuture<String> completableFuture = CompletableFuture.supplyAsync(() -> {
//            try {
//                Thread.sleep(1000); // 模拟耗时操作
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//            return "Hello, CompletableFuture!";
//        });
//
//        completableFuture.thenAccept(result -> {
//            System.out.println(result); // 输出结果
//        });
//
//        try {
//            completableFuture.get(); // 等待任务完成
//        } catch (InterruptedException | ExecutionException e) {
//            e.printStackTrace();
//        }

        System.out.println("Main Thread" +Thread.currentThread().getId()+ "结束运行");

    }
}
