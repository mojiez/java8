package com.atyichen.synchronizedlearn;

/**
 * @author mojie
 * @date 2025/1/3 20:19
 * @description:
 * 实现Runnable接口是定义线程任务的一种方式
 * 实现Runnable接口后， 可以将该类的实例传递给Thread对象， 线程会调用run()方法执行任务
 */
public class SynchronizedObjectLock2 implements Runnable{
    // 维护一个静态的实例
    static SynchronizedObjectLock2 instance = new SynchronizedObjectLock2();

    // 创建两把锁
    Object block1 = new Object();
    Object block2 = new Object();

    @Override
    public void run() {
        // 同步代码块形式
        // •	锁的是对象 block1。
        // •	不同的线程可以同时获取其他锁（如 block2），从而并行执行不同的同步方法/代码块。

        // 第一个同步代码块使用第一把锁， 当他释放后， 后面的代码块使用的是第二把锁， 可以马上执行
        synchronized (block1) {
            System.out.println("block1锁， 我是线程" + Thread.currentThread().getName());
            try{
                Thread.sleep(3000);
            }catch (Exception e) {
                e.printStackTrace();
            }
            System.out.println("block1锁" + Thread.currentThread().getName() + " 结束");
        }

        synchronized (block2) {
            System.out.println("block2, 我是线程" + Thread.currentThread().getName());
            try{
                Thread.sleep(3000);
            }catch (Exception e) {
                e.printStackTrace();
            }
            System.out.println("block2锁" + Thread.currentThread().getName() + " 结束");
        }
    }

    public static void main(String[] args) {
        Thread t1 = new Thread(instance, "thread1");
        Thread t2 = new Thread(instance, "thread2");

        t1.start();
        t2.start();
    }
}
