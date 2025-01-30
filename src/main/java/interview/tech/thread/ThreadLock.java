package interview.tech.thread;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.ReentrantLock;

public class ThreadLock {
    class Ticket{
        private int number = 30;

        public void sale() throws InterruptedException {
            if(number > 0){
                Thread.sleep(200);
                System.out.println("Thread" + Thread.currentThread().getId() + "卖出票：" + number-- + ", 剩下： " + number);
            }
        }

        //Pessimistic Lock: synchronized lock
        public synchronized void synchronizedSale() throws InterruptedException {
            if(number > 0){
                Thread.sleep(200);
                System.out.println("Thread" + Thread.currentThread().getId() + "卖出票：" + number-- + ", 剩下： " + number);
            }
        }

        //Pessimistic Lock: ReentrantLock
        private final ReentrantLock lock = new ReentrantLock(true);
        public void reentrantLockSale(){
            lock.lock();
            try{
                if(number > 0){
                    Thread.sleep(200);
                    System.out.println("Thread" + Thread.currentThread().getId() + "卖出票：" + number-- + ", 剩下： " + number);
                }

            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            } finally {
                lock.unlock();
            }
        }

        //OptimisticLock
        private AtomicInteger counter = new AtomicInteger(0);
        public void increment(){
            System.out.println("Thread" + Thread.currentThread().getId() + "try to increase");
            counter.incrementAndGet();
        }
        public int getCount(){
            return counter.get();
        }
    }

    public static void main(String[] args) {
        System.out.println("Main Thread" +Thread.currentThread().getId()+ "正在运行");

        ThreadLock threadLock = new ThreadLock();
        Ticket ticket = threadLock.new Ticket();
//        new Thread(new Runnable() {
//            @Override
//            public void run() {
//                //调用卖票方法
//                for (int i = 0; i < 40; i++) {
//                    try {
//                        ticket.synchronizedSale();
//                    } catch (InterruptedException e) {
//                        throw new RuntimeException(e);
//                    }
//                }
//            }
//        },"AA").start();
//
//        new Thread(new Runnable() {
//            @Override
//            public void run() {
//                //调用卖票方法
//                for (int i = 0; i < 40; i++) {
//                    try {
//                        ticket.synchronizedSale();
//                    } catch (InterruptedException e) {
//                        throw new RuntimeException(e);
//                    }
//                }
//            }
//        },"BB").start();


        ExecutorService executorService = Executors.newFixedThreadPool(5);

        Runnable myRunnable = new Runnable() {
            @Override
            public void run() {
                for(int i = 0; i < 20; i++){
//                      ticket.sale();

//                    ticket.reentrantLockSale();

//                    try {
//                        ticket.synchronizedSale();
//                    } catch (InterruptedException e) {
//                        throw new RuntimeException(e);
//                    }
                    ticket.increment();

                }
            }
        };
        Callable myCallable = new Callable() {
            @Override
            public String call() throws Exception {
                for(int i = 0; i < 20 ; i++){
                    ticket.increment();
                }
                return "job done";
            }
        };

        try{
//            //5 thread
//            for(int i = 0; i< 5; i++){
//                executorService.submit(myRunnable);
//            }
            for(int i = 0; i< 5; i++){
                executorService.submit(myCallable).get();
            }
        } catch (ExecutionException e) {
            throw new RuntimeException(e);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } finally {
//            executorService.shutdown();
        }
        System.out.println("current count is" + ticket.getCount());
        System.out.println("Main Thread" +Thread.currentThread().getId()+ "结束运行");

    }
}
