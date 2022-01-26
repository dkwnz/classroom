package work.week04;

import java.util.concurrent.*;
import java.util.concurrent.locks.ReentrantLock;

public class concurrency {

    private static Object message = new Object();

    private static final ReentrantLock lock = new ReentrantLock(true);

    private static final CountDownLatch countDownLatch = new CountDownLatch(1);

    private static final CyclicBarrier cyclicBarrier = new CyclicBarrier(2);

    private static final Semaphore semaphore = new Semaphore(0);

    public static void main(String[] args) throws InterruptedException, ExecutionException, BrokenBarrierException {

        long start=System.currentTimeMillis();

        // 在这里创建一个线程或线程池，
        // 异步执行 下面方法
        //方法一
        Method1 method1 = new Method1();
        method1.start();
        method1.join();
        int result = method1.threadResult; //这是得到的返回值
        // 确保  拿到result 并输出
        System.out.println("方法一异步计算结果为："+result);
        System.out.println("方法一使用时间："+ (System.currentTimeMillis()-start) + " ms");

        //方法二
        Method2 method2 = new Method2();
        method2.start();
        synchronized (message) {
            message.wait();
        }
        result = method2.threadResult; //这是得到的返回值
        // 确保  拿到result 并输出
        System.out.println("方法二异步计算结果为："+result);
        System.out.println("方法二使用时间："+ (System.currentTimeMillis()-start) + " ms");

        //方法三
        Method3 method3 = new Method3();
        method3.start();
        while (method3.threadResult == 0) {
            Thread.sleep(1000);
        }
        result = method3.threadResult; //这是得到的返回值
        // 确保  拿到result 并输出
        System.out.println("方法三异步计算结果为："+result);
        System.out.println("方法三使用时间："+ (System.currentTimeMillis()-start) + " ms");

        //方法四
        ExecutorService service = Executors.newCachedThreadPool();
        CompletableFuture<Integer> future = new CompletableFuture<>();
        Method4 method4 = new Method4(future);
        service.submit(method4);
        result = future.get(); //这是得到的返回值
        // 确保  拿到result 并输出
        System.out.println("方法四异步计算结果为："+result);
        System.out.println("方法四使用时间："+ (System.currentTimeMillis()-start) + " ms");

        //方法五
        Method5 method5 = new Method5();
        method5.start();
        Thread.sleep(1000);
        lock.lock();
        try {
            Thread.sleep(10);
        } finally {
            lock.unlock();
        }
        result = method5.threadResult; //这是得到的返回值
        // 确保  拿到result 并输出
        System.out.println("方法五异步计算结果为："+result);
        System.out.println("方法五使用时间："+ (System.currentTimeMillis()-start) + " ms");

        //方法六
        Method6 method6 = new Method6();
        method6.start();
        countDownLatch.await();
        result = method6.threadResult; //这是得到的返回值
        // 确保  拿到result 并输出
        System.out.println("方法六异步计算结果为："+result);
        System.out.println("方法六使用时间："+ (System.currentTimeMillis()-start) + " ms");

        //方法七
        Method7 method7 = new Method7();
        method7.start();
        cyclicBarrier.await();
        result = method7.threadResult; //这是得到的返回值
        // 确保  拿到result 并输出
        System.out.println("方法七异步计算结果为："+result);
        System.out.println("方法七使用时间："+ (System.currentTimeMillis()-start) + " ms");

        //方法八
        Method8 method8 = new Method8();
        method8.start();
        semaphore.acquire();
        result = method8.threadResult; //这是得到的返回值
        // 确保  拿到result 并输出
        System.out.println("方法八异步计算结果为："+result);
        System.out.println("方法八使用时间："+ (System.currentTimeMillis()-start) + " ms");


        // 然后退出main线程
    }

    private static int sum() {
        return fibo(36);
    }

    private static int fibo(int a) {
        if ( a < 2)
            return 1;
        return fibo(a-1) + fibo(a-2);
    }

    public static class Method1 extends Thread {
        public int threadResult;
        @Override
        public void run() {
            threadResult = sum();
        }
    }

    public static class Method2 extends Thread {
        public int threadResult;
        @Override
        public void run() {
            threadResult = sum();
            synchronized (message) {
                message.notifyAll();
            }
        }
    }

    public static class Method3 extends Thread {
        public volatile int threadResult = 0;
        @Override
        public void run() {
            threadResult = sum();
            synchronized (message) {
                message.notifyAll();
            }
        }
    }

    public static class Method4 implements Runnable {
        private CompletableFuture<Integer> future;

        public Method4(CompletableFuture<Integer> future) {
            this.future = future;
        }
        @Override
        public void run() {
            future.complete(sum());
        }
    }

    public static class Method5 extends Thread {
        public int threadResult;
        @Override
        public void run() {
            lock.lock();
            try {
                threadResult = sum();
            } finally {
                lock.unlock();
            }
        }
    }

    public static class Method6 extends Thread {
        public int threadResult;
        @Override
        public void run() {
            threadResult = sum();
            countDownLatch.countDown();
        }
    }

    public static class Method7 extends Thread {
        public int threadResult;
        @Override
        public void run() {
            threadResult = sum();
            try {
                cyclicBarrier.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (BrokenBarrierException e) {
                e.printStackTrace();
            }
        }
    }

    public static class Method8 extends Thread {
        public int threadResult;
        @Override
        public void run() {
            threadResult = sum();
            semaphore.release();
        }
    }
}
