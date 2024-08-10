package annotation;

public class AnnotationTest {
    /*
    如何定义一个注解？？

    1. 用@interface定义注解
    public @interface Report {}

    2. 添加参数，添加默认值
    public @interface Report {
        int type() default 0;
        String value() default "";
        String info() default "info";
    }

    3. 使用元注解配置注解
    @Target(ElementType.TYPE)
    @Retention(RetentionPolicy.RUNTIME)
    public @interface Report {
        int type() default 0;
        String level() default "info";
        String value() default "";
    }
     */
}
