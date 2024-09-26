package async;

import java.util.Random;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

public class CompletableFutureExample {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        CompletableFuture<String> future = CompletableFuture.supplyAsync(() -> {
            // 模拟耗时操作
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return "Task completed!";
        });

        future.thenAccept(result -> System.out.println("Result: " + result));
        System.out.println("Main thread continues...");

//        /**
//         * runAsync + get
//         */
//        CompletableFuture<Void> future = CompletableFuture.runAsync(() -> System.out.println("hello!"));
//        try {
//            // 用于等待异步任务的完成，并返回任务的结果
//            // 由于runAsync方法创建的任务不返回任何结果， 所以这里的get单纯就是阻塞主线程，等待异步任务完成
//            future.get();// 输出 "hello!"
//        } catch (InterruptedException e) {
//            throw new RuntimeException(e);
//        } catch (ExecutionException e) {
//            throw new RuntimeException(e);
//        }

//        /**
//         * supplyAsync + get 需要异步操作并关心返回结果，可以使用
//         */
//        CompletableFuture<String> future1 = CompletableFuture.supplyAsync(() -> {
//            System.out.println("wocao");
//            return "nima";
//        });
//
//        System.out.println("主线程去做其他事情");
//        try {
//            String result = future1.get();
//            System.out.println(result);
//        } catch (InterruptedException e) {
//            throw new RuntimeException(e);
//        } catch (ExecutionException e) {
//            throw new RuntimeException(e);
//        }

        // 处理异步结算的结果
        // 获取到异步计算的结果之后，还可以对其进行进一步的处理

        /**
         * thenApply
         */
        // completedFuture创造了一个立即完成的CompletableFuture对象，并且返回值就是 hello
//        CompletableFuture<String> future = CompletableFuture.completedFuture("hello!")
//                .thenApply(s -> s + "world!");
//        try {
//            //        assertEquals("hello!world!", future.get());
//            System.out.println(future.get());
//
//            // 这次调用将被忽略。
//            // CompletableFuture 是不可变的，一旦它的结果被计算出来并且任务完成了，所有基于这个 CompletableFuture 的后续操作都无法改变它的结果。因此，后续的 thenApply 或 thenApplyAsync 调用会创建一个新的 CompletableFuture，但不会影响已经完成的 CompletableFuture。
//            //        assertEquals("hello!world!", future.get());
//            future.thenApply(s -> s + "nice!");
//            System.out.println(future.get());
//        } catch (InterruptedException e) {
//            throw new RuntimeException(e);
//        } catch (ExecutionException e) {
//            throw new RuntimeException(e);
//        }

//        // 所以要使用链式调用
//        CompletableFuture<String> future = CompletableFuture.completedFuture("hello!")
//                .thenApply(s -> s + "world!").thenApply(s -> s + "nice!");
//        System.out.println(future.get());
//
//        // 回调函数 ： 作为参数传递给另一个函数的函数，当某个操作完成后，原函数会调用这个回调函数 .thenApply后面定义的就是回调函数
//        // 如果不需要从回调函数中获取返回结果，可以使用 thenAccept or thenRun
//
//        // thenAccept可以接收 上一个异步计算的结果
//        CompletableFuture.completedFuture("hello!")
//                .thenApply(s -> s + "world!").thenApply(s -> s + "nice!").thenAccept(System.out::println);//hello!world!nice!
//
//        CompletableFuture.completedFuture("hello!")
//                .thenApply(s -> s + "world!").thenApply(s -> s + "nice!").thenRun(() -> System.out.println("hello!"));//hello!

//        CompletableFuture<String> future1 = CompletableFuture.supplyAsync(() -> {
//            System.out.println("Task 1 thread: " + Thread.currentThread().getName());
//            return "Hello";
//        }).thenApply(result -> {
//            System.out.println("Task 2 thread: " + Thread.currentThread().getName());
//            return result + " World";
//        });

        // 为什么要提出来 ,thenApply 而不是直接在一个异步任务里面写完所有逻辑？？
        // 主要是函数式编程的思想 ， 将逻辑分割成多个步骤 模块化设计 使代码代码更好维护

        /**
         * whenComplete()
         * whenComplete 是 CompletableFuture 提供的方法之一，用来在任务完成后执行指定的逻辑。
         * 	•	该方法接收一个 BiConsumer 类型的回调函数，它有两个参数：
         * 	•	res：表示任务执行成功后返回的结果（这里是 "hello!"）。
         * 	•	ex：表示任务执行中抛出的异常，如果任务正常完成，ex 为 null。
         */

////        whenComple 是非阻塞的
//        CompletableFuture<String> future2 = CompletableFuture.supplyAsync(() -> {
//                    try {
//                        Thread.sleep(1000); // 模拟耗时操作
//                    } catch (InterruptedException e) {
//                        e.printStackTrace();
//                    }
//                    return "hello!";
//                })
//                .whenComplete((res, ex) -> {
//                    // res 代表返回的结果
//                    // ex 的类型为 Throwable ，代表抛出的异常
//                    System.out.println(res);
//                    // 这里没有抛出异常所有为 null
////                    assertNull(ex);
//                });
//        future2.get();
////        assertEquals("hello!", future.get());

        /**
         * handle 处理异常
         */
//        // 处理异常
//        CompletableFuture<String> future
//                = CompletableFuture.supplyAsync(() -> {
//                    Random random = new Random();
//                    int randomNum = random.nextInt(2);
//            System.out.println(randomNum);
//            if (randomNum == 1) {
//                throw new RuntimeException("Computation error!");
//            }
//            // 抛出异常之后的代码不会执行 直接跳到异常处理代码
//            return "hello!";
//        }).handle((res, ex) -> {
//            // res 代表返回的结果
//            // ex 的类型为 Throwable ，代表抛出的异常
//            System.out.println(ex);
//            return res != null ? res : "world!";
//        });
////        assertEquals("world!", future.get());
//        System.out.println(future.get());

        /**
         * 组合 CompletableFuture
         */

//        /**
//         * thenCompose
//         */
//        CompletableFuture<String> future
//                = CompletableFuture.supplyAsync(() -> "hello!")
//                .thenCompose(s -> CompletableFuture.supplyAsync(() -> s + "world!"));
//        System.out.println(future.get());
//        // 在实际开发中，这个方法还是非常有用的。比如说，task1 和 task2 都是异步执行的，但 task1 必须执行完成后才能开始执行 task2（task2 依赖 task1 的执行结果）。

//        /**
//         * thenCombine()
//         * thenCombine 方法会等待两个 CompletableFuture 都完成，然后将它们的结果 s1 和 s2 传递给一个 BiFunction（即 (s1, s2) -> s1 + s2），合并两个结果并返回一个新的 CompletableFuture。
//         * 	•	在这个例子中，s1 是 "hello!"，s2 是 "world!"，因此合并后的结果是 "hello!world!"。
//         */
//        CompletableFuture<String> completableFuture = CompletableFuture.supplyAsync(() -> "hello!")
//                .thenCombine(
//                        CompletableFuture.supplyAsync(() -> "world!"),
//                        (s1, s2) -> s1 + s2
//                );
//        String join = completableFuture.join();
//        System.out.println(join);

//        /**
//         * 除了 thenCompose() 和 thenCombine() 之外， 还有一些其他的组合 CompletableFuture 的方法用于实现不同的效果，满足不同的业务需求
//         * 例如，如果我们想要实现 task1 和 task2 中的任意一个任务执行完后就执行 task3 的话，可以使用 acceptEither()。
//         */
//
//        CompletableFuture<String> task = CompletableFuture.supplyAsync(() -> {
//            System.out.println("任务1开始执行，当前时间：" + System.currentTimeMillis());
//            try {
//                Thread.sleep(500);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//            System.out.println("任务1执行完毕，当前时间：" + System.currentTimeMillis());
//            return "task1";
//        });
//
//        CompletableFuture<String> task2 = CompletableFuture.supplyAsync(() -> {
//            System.out.println("任务2开始执行，当前时间：" + System.currentTimeMillis());
//            try {
//                Thread.sleep(1000);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//            System.out.println("任务2执行完毕，当前时间：" + System.currentTimeMillis());
//            return "task2";
//        });
//
//        task.acceptEitherAsync(task2, (res) -> {
//            System.out.println("任务3开始执行，当前时间：" + System.currentTimeMillis());
//            System.out.println("上一个任务的结果为：" + res);
//        });
//
//// 增加一些延迟时间，确保异步任务有足够的时间完成
//        try {
//            Thread.sleep(2000);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }

        /**
         * 并行运行多个 CompletableFuture
         * 你可以通过 CompletableFuture 的 allOf()这个静态方法来并行运行多个 CompletableFuture 。实际项目中，我们经常需要并行运行多个互不相关的任务，这些任务之间没有依赖关系，可以互相独立地运行。
         */

//        CompletableFuture<Void> task1 =
//                CompletableFuture.supplyAsync(()->{
//                    //自定义业务操作
//                });
//......
//        CompletableFuture<Void> task6 =
//                CompletableFuture.supplyAsync(()->{
//                    //自定义业务操作
//                });
//......
//        CompletableFuture<Void> headerFuture=CompletableFuture.allOf(task1,.....,task6);
//
//        try {
//            headerFuture.join();
//        } catch (Exception ex) {
//    ......
//        }
//        System.out.println("all done. ");

        /**
         * allOf() 方法会等到所有的 CompletableFuture 都运行完成之后再返回
         * anyOf() 方法不会等待所有的 CompletableFuture 都运行完成之后再返回，只要有一个执行完成即可！
         */


        /**
         * CompletableFuture的使用建议
         *
         * 1. 使用自定义线程池
         * CompletableFuture 默认使用ForkJoinPool.commonPool() 作为执行器，这个线程池是全局共享的，可能会被其他任务占用，导致性能下降或者饥饿。
         * 因此，建议使用自定义的线程池来执行 CompletableFuture 的异步任务，可以提高并发度和灵活性。
         *
         * 2. 避免使用get()
         * CompletableFuture的get()方法是阻塞的，尽量避免使用。如果必须要使用的话，需要添加超时时间，否则可能会导致主线程一直等待，无法执行其他任务。
         * join也是阻塞的
         */
    }
}
