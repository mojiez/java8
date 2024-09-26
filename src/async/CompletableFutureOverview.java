package async;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

public class CompletableFutureOverview {
    // 假设有三个操作step1、step2、step3存在依赖关系，其中step3的执行依赖step1和step2的结果。
    public void op1() throws ExecutionException, InterruptedException {
        // supplyAsync 是一个工厂方法， 用于创建异步任务
        CompletableFuture<String> cf1 = CompletableFuture.supplyAsync(()->{
            System.out.println("执行step1");
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            return "step1 result!";
        });

        CompletableFuture<String> cf2 = CompletableFuture.supplyAsync(()->{
            System.out.println("执行step2");
            try {
                Thread.sleep(2000);
            }catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            return "step2 result!";
        });

//        cf1.thenCombine(cf2, (result1, result2)->{
//            System.out.println(result1+result2);
//            System.out.println("执行step3");
//            return "step3 result!";
//            // Accept 不需要返回值
//        }).thenAccept((result3)->{
//            System.out.println(result3);
//        }).get();

        cf1.thenCombine(cf2, (result1, result2) -> {
            System.out.println(result1+result2);
            System.out.println("执行step3");
            return null;
        }).thenAccept(result3->{
            System.out.println(result3);
        }).get();
    }


    public void op2() {
        /**
         * 零依赖
         * 不依赖其他CompletableFuture来创建新的CompletableFuture
         */
        CompletableFuture<String> cf1 = CompletableFuture.supplyAsync(()->{
            System.out.println("执行cf1");
            try {
                Thread.sleep(3000);
            }catch (Exception e) {
                System.out.println(e.getMessage());
            }
            System.out.println("cf1 done");
            return "cf1_result";
        });

        CompletableFuture<String> cf2 = CompletableFuture.supplyAsync(()->{
            System.out.println("执行cf2");
            try {
                Thread.sleep(2000);
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
            System.out.println("cf2 done");
            return "sf2_result";
        });

        /**
         * 一元依赖：依赖一个CF
         * 使用 thenAccept thenApply
         */
        CompletableFuture<String> cf3 = cf1.thenApply((result1) -> {
            System.out.println("得到cf1返回结果: " + result1 + ", 执行cf3..");
            try {
                Thread.sleep(5000);
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
            System.out.println("cf3执行完毕");
            return "cf3_result";
        });

        CompletableFuture<Void> cf5 = cf2.thenAccept((result2) -> {
            System.out.println("得到cf2返回结果: " + result2 + ", 执行cf5..");
            try {
                Thread.sleep(2000);
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
            System.out.println("cf5执行完毕");
        });

        /**
         * 二元依赖：依赖两个CF
         * .thenCombine()
         */

        CompletableFuture<String> cf4 = cf1.thenCombine(cf2, (result1, result2) -> {
            System.out.println("得到cf1 cf2的返回结果， 执行cf4");
            try {
                Thread.sleep(8000);
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
            System.out.println("cf4执行完毕");
            return "cf4_result";
        });

        /**
         * 多元依赖：依赖多个CF
         * 可以通过allOf或anyOf方法来实现，
         * 区别是当需要多个依赖全部完成时使用allOf，
         * 当多个依赖中的任意一个完成即可时使用anyOf，
         * 如下代码所示：
         */
        CompletableFuture<Void> cf5_5 = CompletableFuture.allOf(cf3, cf4, cf5);
        CompletableFuture<Void> cf6 = cf5_5.thenAccept(v->{
            // 得到cf3 cf4 cf5的返回值
            String result3 = "";
            String result4 = "";
            Void result5;
            try {
                result3 = cf3.get();
                result4 = cf4.get();
                result5 = cf5.get();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            } catch (ExecutionException e) {
                throw new RuntimeException(e);
            }
            System.out.println("得到result3 4 5:" +result3+ result4 +result5);
            System.out.println("执行cf6");
            try {
                Thread.sleep(1000);
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
            System.out.println("cf6 done");
        });
        cf6.join();
    }
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        CompletableFutureOverview completableFutureOverview = new CompletableFutureOverview();
        completableFutureOverview.op2();
    }
}
