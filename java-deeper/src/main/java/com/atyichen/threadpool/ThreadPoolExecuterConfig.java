package com.atyichen.threadpool;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.*;

/**
 * @author mojie
 * @date 2024/12/2 15:11
 * @description: 线程池配置类
 */
/*
@Configuration用于定义配置类，可替换xml配置文件，被注解的类内部包含有一个或多个被@Bean注解的方法，
这些方法将会被AnnotationConfigApplicationContext或AnnotationConfigWebApplicationContext类进行扫描，
并用于构建bean定义，初始化Spring容器。

Configuration注解中如何读取配置文件yml的参数来初始化Bean
 */
@Configuration
public class ThreadPoolExecuterConfig {
//    @Bean
//    public ThreadPoolExecutor threadPoolExecutor() {
//        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor();
//        return threadPoolExecutor;
//    }

//    @Bean
//    public ThreadPoolExecutor threadPoolExecutor(int corePoolSize,
//                                                 int maximumPoolSize,
//                                                 long keepAliveTime,
//                                                 TimeUnit unit,
//                                                 BlockingQueue<Runnable> workQueue,
//                                                 ThreadFactory threadFactory,
//                                                 RejectedExecutionHandler handler) {
//        /*
//        ThreadPoolExecutor参数分析
//        corePoolSize 核心线程数， 公司的正式员工， 正常情况下都是随时待命处理任务的。 如果说我们的AI服务（需要多线程处理的任务）只允许四个任务同时进行， 那么核心线程出就应该被设置为四
//        maximumPoolSize 最大线程数， 在极限情况下我们的线程池能有多少个线程在工作。 就算任务再多， 最多也只能雇佣这么多人， 假设AI服务最多只允许四个服务同时进行， 最大线程数应该设置为四
//        keepAliveTime 空闲线程存活时间， 决定当任务少的时候， 临时雇佣的线程会等待多久才会被剔除
//
//        什么时候会雇佣临时线程？
//        当任务数量超过 corePoolSize时， 线程池会将任务放入队列中。
//        如果队列已满， 且任务数量超过corePoolSize， 线程池会创建临时线程来处理任务， 直到达到maximumPoolSize
//
//        TimeUnit: 空闲线程存活时间的单位 将keepAliveTime和TimeUnit组合在一起， 就能指定一个具体的时间， 比如分钟 秒
//        workQueue: 工作队列/任务队列 存储所有等待执行的任务。也可以叫它阻塞队列，一定要设置这个队列的长度，因为无限长度的队列会消耗大量的系统资源
//        threadFactory: 线程工厂 负责控制每个线程的生成（负责招聘员工）
//        RejectedExecutionHandler: 拒绝策略，当任务队列已满的时候，我们应该怎么处理新来的任务？
//
//        注意，线程池的核心线程默认不会被回收，即使它们已经空闲了
//         */
//        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(corePoolSize, maximumPoolSize, keepAliveTime, unit, workQueue, threadFactory, handler);
//        return threadPoolExecutor;
//
////        /*
////        线程池的拒绝策略:
////         */
////        ThreadPoolExecutor.AbortPolicy: 抛出异常来拒绝新任务的处理;
////        ThreadPoolExecutor.CallerRunsPolicy: 将任务回退给任务的提交者线程（即调用execute或submit方法的线程），并使用该线程来执行任务， 调用者会同步执行任务， 而不是异步地由线程池中的线程执行。
////        ThreadPoolExecutor.DiscardPolicy: 不处理新任务， 直接丢弃掉;
////        ThreadPoolExecutor.DiscardOldestPolicy: 此策略将丢弃最早的未处理的任务请求
//
//    }

}
