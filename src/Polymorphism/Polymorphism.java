package Polymorphism;

public class Polymorphism {
    // 多态是指在运行时通过父类引用指向子类对象，调用子类的重写方法。
    public static void main(String[] args) {
        /**
         * 装箱 java自动将基本类型int 10 装箱为Integer对象（包装类）
         * Integer类维护了一个常量池
         * 当装箱的 int 值在 -128 到 127 之间时，Java 会从缓存池中返回相同的 Integer 对象，而不是创建新的 Integer 对象。
         */
        Integer i1 = 10;
        Integer i2 = 10;
        System.out.println(i1 == i2); // 返回 true

        // i3是装箱得到的缓存池中的对象 i4是new的 地址不一样
        Integer i3 = 40;
        Integer i4 = new Integer(40);
        System.out.println(i3 == i4); // 返回 false
    }
}
