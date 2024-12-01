package com.atyichen.synchronizedlearn;

/**
 * @author mojie
 * @date 2024/11/30 16:07
 * @description: 修饰实例对象
 */
public class SynchronizedUsage1 {
    private int count = 0;
    // 实例方法同步，锁住的是this对象
    public synchronized void increment() {
        count++;
    }

    // 等价的显式写法
    public void incrementExplicit() {
        synchronized(this) {
            count++;
        }
    }

    // 多个同步实例方法共享同一个锁
    public synchronized void decrement() {
        count--;
    }

    // 使用示例
    public static void main(String[] args) {
        SynchronizedUsage1 example = new SynchronizedUsage1();
        int flag = 0;
        // 两个线程操作同一个实例
        for (int i=0;i<1000;i++) {
            // 创建两个线程
            Thread t1 = new Thread(() -> example.increment());
            Thread t2 = new Thread(() -> example.decrement());

            // 启动线程
            t1.start();
            t2.start();

            try {
                // 等待两个线程完成
                t1.join();
                t2.join();

                // 现在可以安全地检查 count
                if (example.count != 0) {
                    System.out.println("no!!");
                    flag = 1;
                    break;
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        if (flag == 0)
            System.out.println("good");
    }
}
