package async;

import java.util.concurrent.CompletableFuture;
public class CompletableFutureMeituan {
    public CompletableFuture<Void> task1() {
        return CompletableFuture.runAsync(() -> {
            System.out.println("task1 running");
        });
    }
    public CompletableFuture<Void> task2() {
        return CompletableFuture.runAsync(() -> {
            System.out.println("task2 running");
        });
    }
    public CompletableFuture<Void> task3() {
        return CompletableFuture.runAsync(() -> {
            System.out.println("task3 running");
        });
    }
    public CompletableFuture<Void> task4() {
        return CompletableFuture.runAsync(() -> {
            System.out.println("task4 running");
        });
    }
    public CompletableFuture<Void> task5() {
        return CompletableFuture.runAsync(() -> {
            System.out.println("task5 running");
        });
    }
    public CompletableFuture<Void> task6() {
        return CompletableFuture.runAsync(() -> {
            System.out.println("task6 running");
        });
    }

    /**
     * 一个传统的异步API 使用回调来处理结果
     */
    public void asyncOperationWithCallback(Callback callback) {
        // 模拟异步操作

        /**
         * 错误写法
         * 一个catch亦能捕获一个异常
         */
//        new Thread(() -> {
//            try {
//                Thread.sleep(2000); // 模拟耗时操作
//                throw new RuntimeException("woxiang 犯罪");
////                callback.onSuccess("Operation successful!");
//            } catch (InterruptedException e, RuntimeException e1) {
//                callback.onFailure(e1);
//                callback.onFailure(e);
//            }
//        }).start();

        // 模拟异步操作
        Thread thread = new Thread(() -> {
            try {
                // 模拟耗时操作
                Thread.sleep(2000);
                // 模拟成功操作
                callback.onSuccess("Operation successful!");
            } catch (InterruptedException e) {
                // 模拟失败操作
                callback.onFailure(e);
            }
        });
        /**
         * 设置为守护线程
         * 如果你希望主线程在启动异步任务后立即结束，不等待异步任务完成，
         * 通常的做法是将异步线程设置为守护线程（Daemon Thread）。
         * 守护线程不会阻止 JVM 退出。如果所有线程都是守护线程，JVM 会在主线程退出时直接终止程序，不会等待守护线程完成。
         */
        thread.setDaemon(false);
        thread.start();
    }

    /**
     * 将传统异步API转化为 CompletableFuture：
     * 使用匿名内部类 实现callback接口
     */
    // 转换为 CompletableFuture 的方法
    public CompletableFuture<String> asyncOperation() {
        CompletableFuture<String> cf = new CompletableFuture<>();
        /**
         * 这行代码启动了一个异步操作（假设 asyncOperationWithCallback 是异步的），并且传递了一个 Callback 对象用于在异步操作完成时执行回调。
         * 	•	如果异步操作成功完成，onSuccess 方法会被调用，cf.complete(result) 会被执行，CompletableFuture 将完成，并返回结果。
         * 	•	如果异步操作失败，onFailure 方法会被调用，cf.completeExceptionally(t) 会被执行，CompletableFuture 将以异常的方式完成。
         */
        asyncOperationWithCallback(new Callback() {
            @Override
            public void onSuccess(String result) {
                System.out.println(result);
                cf.complete(result); // 手动完成 CompletableFuture
            }

            @Override
            public void onFailure(Throwable t) {
                cf.completeExceptionally(t); // 手动抛出异常
            }
        });

        /**
         * 在 asyncOperation 方法中，return cf; 会在 asyncOperationWithCallback 方法调用完成后立即执行，
         * 并返回一个尚未完成的 CompletableFuture 对象。这个返回是非阻塞的，意味着 asyncOperation 方法不会等待 cf 完成就直接返回。
         */
        return cf; // 返回未完成的 CompletableFuture
    }

    public static void main(String[] args) {
        CompletableFutureMeituan completableFutureMeituan = new CompletableFutureMeituan();

        /**
         * exam1
         */

        /**
         * 零依赖 CompletableFuture的创建
         * 1. 先初始化一个未完成的CompletableFuture，然后通过complete()、completeExceptionally()，完成该CompletableFuture
         * 一个典型使用场景，就是将回调方法转为CompletableFuture，然后再依赖CompletableFuture的能力进行调用编排
         *
         * 	•	在传统的异步编程中，通常会使用回调函数来处理异步操作的结果。这种方式的问题是代码容易变得复杂和难以维护，尤其是在多个异步操作需要依赖和组合的时候。
         * 	•	使用 CompletableFuture，你可以将这些回调函数转化为 CompletableFuture，使得异步操作更加易于管理和组合。
         */

        /**
         * 传统的异步编程
         */
//        completableFutureMeituan.asyncOperationWithCallback(new CallbackA());

        /**
         * 使用 CompletableFuture，你可以将这些回调函数转化为 CompletableFuture
         */
//        CompletableFuture<String> future = completableFutureMeituan.asyncOperation();

        /**
         * 问题：为什么我运行的时候，主线程一直不结束，我明明没有使用 get join
         *虽然主线程的代码看似没有显式地阻塞（例如没有调用 get 或 join），但如果主线程退出时有其他线程（如你启动的异步线程）仍在运行，
         * Java 虚拟机（JVM）会等待这些线程结束后才完全退出。因此，如果异步线程仍在运行，主线程将不会结束，直到这些线程完成或主程序结束。
         * 如果不想等，可以使用守护线程
         */
//        System.out.println("main doing something");

        //////////////////////////////////////////////////////
        /**
         * 直接使用 supplyAsync发起异步调用
         */
        CompletableFuture<String> cf1 = CompletableFuture.supplyAsync(() -> {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            return "result1";
        });
        System.out.println("main doing something");
        cf1.join();

        /**
         * runAsync或supplyAsync的区别？？
         * runAsync() 方法接受的参数是 Runnable ，这是一个函数式接口，不允许返回值。当你需要异步操作且不关心返回结果的时候可以使用 runAsync() 方法。
         * 当你需要异步操作且关心返回结果的时候,可以使用 supplyAsync() 方法。
         */

//        String join = future.join();
//        System.out.println(join);
    }


}
/**
 * 定义回调函数接口
 */
interface Callback {
    void onSuccess(String result);
    void onFailure(Throwable t);
}

/**
 * 回调函数接口实现类
 */
class CallbackA implements Callback {

    @Override
    public void onSuccess(String result) {
        System.out.println(result);
    }

    @Override
    public void onFailure(Throwable t) {
        System.out.println(t);
    }
}
