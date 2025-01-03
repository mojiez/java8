package com.atyichen.synchronizedlearn;

/**
 * @author mojie
 * @date 2025/1/3 20:19
 * @description:
 * 实现Runnable接口是定义线程任务的一种方式
 * 实现Runnable接口后， 可以将该类的实例传递给Thread对象， 线程会调用run()方法执行任务
 */
public class SynchronizedObjectLock implements Runnable{
    // 维护一个静态的实例
    static SynchronizedObjectLock instance = new SynchronizedObjectLock();
    private int count = 0;
    @Override
    public void run() {

        for (int i=0;i<1000000;i++) {
            increamentCount();
        }
    }

    // 锁为this 即锁的是当前实例对象，线程a必须要等到线程b释放了锁后，才能执行
//    private synchronized void increamentCount() {
//        count++;
//        System.out.println(Thread.currentThread().getName() + " count: " + count);
//    }

    // 如果不加锁， 可能导致脏数据 count不一定是200
    private void increamentCount() {
        count++;
        System.out.println(Thread.currentThread().getName() + " count: " + count);
    }
    public static void main(String[] args) {
        Thread t1 = new Thread(instance, "thread1");
        Thread t2 = new Thread(instance, "thread2");

        t1.start();
        t2.start();
        // 两个线程并发执行

        // 分别进行循环i=0...10
        // count最后应该是200 加锁的话
        // 这就是多个线程控制一个对象实例的例子
    }
}
