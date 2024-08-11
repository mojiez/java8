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

    public static void main(String[] args) {
        CompletableFutureMeituan completableFutureMeituan = new CompletableFutureMeituan();

        /**
         * exam1
         */

        /**
         * 零依赖 CompletableFuture的创建
         */

    }
}
