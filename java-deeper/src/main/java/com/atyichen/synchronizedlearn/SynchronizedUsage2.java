package com.atyichen.synchronizedlearn;

/**
 * @author mojie
 * @date 2024/12/1 15:58
 * @description: 同步静态方法 锁当前类的Class对象
 */
public class SynchronizedUsage2 {
    private static int staticCount = 0;

    // 静态方法同步， 锁住的是 SynchronizedUsage2.class 类对象
    public static synchronized void incrementStatic() {
        staticCount++;
    }
    // 等价的显式写法
    public static void incrementStaticExplicit() {
        synchronized (SynchronizedUsage2.class) {
            staticCount++;
        }
    }

    // 多个静态同步方法共享同一个类锁
    public static synchronized void decrementStatic() {
        staticCount--;
    }

    public static void main(String[] args) {

        int flag = 0;
        try {
            for (int i=0;i<1000;i++) {
                // 同一个线程对象不能多次 start
                Thread t1 = new Thread(() -> SynchronizedUsage2.incrementStatic());
                Thread t2 = new Thread(() -> SynchronizedUsage2.decrementStatic());
                t1.start();
                t2.start();
                t1.join();
                t2.join();

                if (SynchronizedUsage2.staticCount != 0) {
                    flag = 1;
                    System.out.println("no!");
                    break;
                }
            }
            if (flag == 0) System.out.println("good");
        }catch (Exception e) {
            e.printStackTrace();
        }

    }
}
