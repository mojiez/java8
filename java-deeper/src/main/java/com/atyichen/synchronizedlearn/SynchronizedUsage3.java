package com.atyichen.synchronizedlearn;

/**
 * @author mojie
 * @date 2024/12/1 16:30
 * @description: 同步代码块（锁可以是任何对象）
 */
public class SynchronizedUsage3 {
    private final Object lockA = new Object();
    private final Object lockB = new Object();
    private int countA = 0;
    private int countB = 0;

    // 使用不同的锁对象实现细粒度锁
    public void incrementA() {
        synchronized (lockA) {
            countA++;
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            System.out.println("A干了");
        }
    }

    public void incrementB() {
        synchronized (lockB) {
            System.out.println("B干了");
            countB++;
        }
    }

    // 同时获取多个锁
    public void incrementBoth() {
        synchronized (lockA) {
            synchronized (lockB) {
                countB++;
                countA++;
            }
        }
    }

    // 使用示例
    public static void main(String[] args) {
        SynchronizedUsage3 example = new SynchronizedUsage3();

        // 不同的锁对象的操作可以并行执行
        for (int i=0;i<10;i++) {
            new Thread(() -> example.incrementA()).start();
            new Thread(() -> example.incrementB()).start();
        }

    }
}
