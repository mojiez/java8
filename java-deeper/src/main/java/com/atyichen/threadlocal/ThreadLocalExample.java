package com.atyichen.threadlocal;

import java.text.SimpleDateFormat;
import java.util.Random;

/**
 * @author mojie
 * @date 2024/12/1 17:01
 * @description: 学习 ThreadLocal
 */
/*
如果类本身是线程安全的，而且只需要一个实例，不需要使用ThreadLocal。让我举例说明：

类不是线程安全的，但只需要一个实例
可以使用synchronized来同步访问
 */
public class ThreadLocalExample implements Runnable{

    // SimpleDateFormat 不是线程安全的，所以每个线程都要有自己独立的副本
    // 如果不使用ThreadLocal，所有线程会共享同一个SimpleDateFormat实例，这可能导致线程安全问题。
    private static final ThreadLocal<SimpleDateFormat> formatter = ThreadLocal.withInitial(() -> new SimpleDateFormat("yyyyMMdd HHmm"));

    public static void main(String[] args) {
        ThreadLocalExample obj = new ThreadLocalExample();
        for (int i=0;i<10;i++){
            Thread t1 = new Thread(obj, String.valueOf(i));
            try {
                Thread.sleep(new Random().nextInt(1000));
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            t1.start();
        }
    }

    @Override
    public void run() {
        System.out.println("Thread Name= "+Thread.currentThread().getName()+" default Formatter = "+formatter.get().toPattern());
        try {
            Thread.sleep(new Random().nextInt(1000));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        //formatter pattern is changed here by thread, but it won't reflect to other threads
        formatter.set(new SimpleDateFormat());

        System.out.println("Thread Name= "+Thread.currentThread().getName()+" formatter = "+formatter.get().toPattern());
    }
}
