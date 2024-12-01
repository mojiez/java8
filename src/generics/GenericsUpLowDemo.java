package generics;

import java.util.List;

/**
 * 泛型上下限的引入
 * 注意
 * 类泛型 只能定义T 和 T extends Number
 * 不能定义 T super
 * 即 类泛型不能定义 下限
 * // 如果允许 T super String
 * class Info2<T super String> {
 *     private T var;  // 擦除后应该变成什么类型？
 *     // Object？那么就失去了下界的意义
 *     // String？那么可能不兼容T的实际类型
 * }
 *
 * // 对于 T extends Number来说
 * class Info2<T extends Number> {
 *     private T var; // 擦除后T被替换为边界类型 Number
 * }
 *
 * 对于 T 来说
 * class Info2<T> {
 *     private T var; // 擦除后T被替换为 Object
 * }
 *
 *
 */

/*
class Info2<T extends Number> {
      private T var;
  }

Info2<String> info = new Info2();
这里会发生什么?

类型检查阶段：
// 编译器检查所有泛型类型使用是否合法
Info2<Integer> info1 = new Info2<>();    // 通过：Integer extends Number
Info2<String> info2 = new Info2<>();     // 错误：String 不是 Number 的子类

对于：
class Info2<T extends Number> {
      private T var;
  }

Info2<Double> info = new Info2();
类型检查阶段：
通过 因为Double extends Number

泛型擦除阶段：
// 擦除后的代码
class Info2 {
    private Number var;  // T 被替换为上界 Number
}

Info2 info = new Info2();
 */
class Info1 <T extends Number> { //此泛型的上限是Number
    private T var;

    @Override
    public String toString() {
        return "Info1{" +
                "var=" + var +
                '}';
    }

    public T getVar() {
        return var;
    }

    public void setVar(T var) {
        this.var = var;
    }
}

class Info2<T> {
    private T var;

    @Override
    public String toString() {
        return "Info2{" +
                "var=" + var +
                '}';
    }

    public T getVar() {
        return var;
    }

    public void setVar(T var) {
        this.var = var;
    }
}
public class GenericsUpLowDemo {
    public static void main(String[] args) {
        // 可以接收Number 和 Number以下的
        Info1<Integer> info = new Info1<>();
        // Info1<String> info2 = new Info1<String>(); 这样写就是错的

    }
    /**
     * 下限
     * super的正确用法用在泛型方法中
     */
    public static void fun(Info2<? super String> temp) { // 只接收String和String以上的（Object）

    }
    public static void funA(List<? super String> list) {
        list.add("nihao");
        Object s = list.get(0); // 这样写是可以的，因为 ? 不确定是什么
//        String s1 = list.get(0); // 这样写不可以， 因为类型检查阶段， 不确定给list传的是Object 还是 String， 所以不能这样取
    }

    /*
    为什么不能写 T super String
    下面这段代码报错
    原因是T应该是一个确定的类型（在整个类和方法中表示同一个具体的类型）
    如果写 T super String, T就不是一个确定的了， 它可以是String  可以是Object  定义冲突
    这违背了设计T的初衷
     */
//    public static <T> void funB(Info2<T super String>) {
//
//    }

    /**
     * 泛型方法中使用T的例子
     * 这两个方法中的
     */
    public static <T extends Number> double sum(List<T> numbers) {
        double sum = 0;
        for (T number : numbers) {
            sum += number.doubleValue();
        }
        return sum;
    }
//    // 这样写有点不优雅， 上面的写法比较好
//    public static <T> double sum1(List<T> numbers) {
//        double sum = 0;
//        for (T number : numbers) {
//            sum += (double) number;
//        }
//        return sum;
//    }
}
