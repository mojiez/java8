package com.atyichen.synchronizedlearn;

/**
 * @author mojie
 * @date 2025/1/3 20:19
 * @description:
 * 实现Runnable接口是定义线程任务的一种方式
 * 实现Runnable接口后， 可以将该类的实例传递给Thread对象， 线程会调用run()方法执行任务
 */
public class SynchronizedObjectLock1 implements Runnable{
    // 维护一个静态的实例
    static SynchronizedObjectLock1 instance = new SynchronizedObjectLock1();
    @Override
    public void run() {
        // 同步代码块形式——锁为this
        synchronized (this) {
            System.out.println("我是线程:" + Thread.currentThread().getName());
            try {
                Thread.sleep(3000);
            }catch (Exception e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName()+" 结束");
        }
    }

    public static void main(String[] args) {
        Thread t1 = new Thread(instance, "thread1");
        Thread t2 = new Thread(instance, "thread2");

        t1.start();
        t2.start();
    }
}
