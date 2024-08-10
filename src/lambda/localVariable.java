package lambda;

/**
 * lambda表达式调用外部变量
 */

interface VariableInterface1{
    void method();
}
public class localVariable {
    // 局部变量在lambda表达式中默认被定义为final的，也就是说lambda表达式智能调用局部变量，不能改变其值
    // 局部变量 对应 成员变量（实例变量） 和 类变量（静态变量） 后两者在lambda中不被视为final

//    /**
//     * 错误写法
//     * @param args
//     */
//    public static void main(String[] args){
//        int value = 100;
//        VariableInterface1 v = ()->{
//            int num = value - 90;
//            value = 12;
//        };
//    }

    // 可以将外部变量声明为一个数据或者一个类（包括容器类） 就可以修改其中的值

//        /**
//     * 正确写法1
//     * @param args
//     */
//    public static void main(String[] args){
////        int value = 100;
//        int[] value = new int[100];
//        value[0] = 100;
//        VariableInterface1 v = ()->{
//            int num = value[0] - 90;
//            System.out.println("num: " + num);
//            value[0] = 12;
//        };
//        v.method();
//        System.out.println("value[0]: " + value[0]);
//    }

            /**
     * 正确写法2
     * @param args
     */
    public static void main(String[] args){
//        int value = 100;
//        int[] value = new int[100];
        class Value {
            int num = 100;
        }

        Value value = new Value();
        VariableInterface1 v = ()->{
//            int num = value[0] - 90;
            int num = value.num - 90;
            System.out.println("num: " + num);
//            value[0] = 12;
            value.num = 12;
        };
        v.method();
        System.out.println("value[0]: " + value.num);
    }

    /**
     * 写成容器类
     * 容器类就是List Set Map这些
     * 在 Lambda 表达式中，由于局部变量必须是隐式 final 的，因此不能直接修改一个局部变量的值。但是，如果你使用一个容器类（如 List 或 Map），你可以在 Lambda 表达式中修改容器类中的元素值，而不会违反 final 的限制。
     * 注意事项
     *
     * 虽然使用容器类可以绕过 Lambda 表达式的 final 限制，但在多线程环境下仍需谨慎，确保线程安全。对于多个线程可能同时访问和修改容器类中的数据的情况，使用同步机制或线程安全的集合类（如 ConcurrentHashMap、CopyOnWriteArrayList）是非常重要的。
     */
}
