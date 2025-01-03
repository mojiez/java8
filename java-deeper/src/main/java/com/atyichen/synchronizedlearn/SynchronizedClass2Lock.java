package com.atyichen.synchronizedlearn;

/**
 * @author mojie
 * @date 2025/1/3 20:19
 * @description: 修饰静态方法 类锁
 * 实现Runnable接口是定义线程任务的一种方式
 * 实现Runnable接口后， 可以将该类的实例传递给Thread对象， 线程会调用run()方法执行任务
 */
public class SynchronizedClass2Lock implements Runnable{
    // 维护一个静态的实例
    static SynchronizedClass2Lock instance1 = new SynchronizedClass2Lock();
    static SynchronizedClass2Lock instance2 = new SynchronizedClass2Lock();
    @Override
    public void run() {
        method();
    }

    // synchronized用在静态方法上， 默认的锁就是方法所在的Class类， 所以无论是哪个线程访问， 即使用不同的实例， 也不能异步执行
    public void method() {
        synchronized (SynchronizedClass2Lock.class) {
            System.out.println("我是线程" + Thread.currentThread().getName());
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName() + "结束");
        }
    }

    public static void main(String[] args) {
        // 对应的this是两个不同的实例， 但是锁的是类， 所以还是要抢锁
        Thread t1 = new Thread(instance1, "thread1");
        Thread t2 = new Thread(instance2, "thread2");

        t1.start();
        t2.start();
    }
}
