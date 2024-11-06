package generics;

/**
 * 为什么需要Class对象
 * 泛型编程中
 */

import java.util.ArrayList;
import java.util.List;

/**
 * 不使用Class对象的工厂方法
 */
class ObjectFactory {
    // 这种方式无法保证类型安全
    public Object create() {
        return new Object();
    }
}

class Cat {
    private String name;
}
class Dog {
    private String name;
}
//    // 使用时需要强制类型转换
//    User user = (User) factory.create();  // 需要强制转换，可能出现ClassCastException

/**
 * 使用了Class对象的工厂方法
 */
class ObjectFactory1 {
    public <T> T create(Class<T> clazz) throws InstantiationException, IllegalAccessException {
        return clazz.newInstance();
    }
}
//    // 使用
//    User user = factory.create(User.class);  // 编译器知道返回类型是User
//    String str = factory.create(String.class);  // 编译器知道返回类型是String

/*
泛型擦除后的实际代码
// 编译前
public <T> T create(Class<T> clazz) {
    return clazz.newInstance();
}

// 泛型擦除后
public Object create(Class clazz) {
    return clazz.newInstance();
}

那为什么还能保证类型安全呢？ 被擦除得这么干净？
// 当你调用
User user = factory.create(User.class);

// 编译器会：
// 1. 知道 User.class 的类型是 Class<User>
// 2. 推断出 create 方法的泛型 T 是 User
// 3. 因此知道返回值类型是 User
// 4. 不需要强制类型转换

 */

/**
 * 一个更复杂的例子
 */
class Container<T> {
    private Class<T> type;

    // 构造函数需要Class对象来确定具体类型
    public Container(Class<T> type) {
        this.type = type;
    }

    public T createInstance() throws InstantiationException, IllegalAccessException {
        return type.newInstance();
    }
}

/*
泛型的上下限
 */
class A{}
class B extends A{}

public class GenericsMethodsDemo1 {
//    /**
//     * 泛型的上下限
//     */
//    public static void funA(A a) {
//
//    }
//
//    /**
//     * 这样调用没问题， 本质上就是子类可以自动向上转化为父类
//     * @param b
//     */
//    public static void funB(B b) {
//        funA(b);
//    }
//
//    public static void funC(List<A> listA) {
//
//    }
//
//    /**
//     * 这样调用是有问题的
//     * // 编译器检查 funC(listB) 调用时：
//     * // funC 期望参数类型：List<A>
//     * // 实际传入参数类型：List<B>
//     *
//     * // 虽然 B extends A
//     * // 但是 List<B> 不是 List<A> 的子类型！
//     * @param listB
//     */
//    public static void funD(List<B>  listB) {
//        funC(listB);
//    }

    /*
    解决方法
    <? extends A> 表示该类型参数可以是A或者A的子类类型 这是泛型的上限
    编译时擦除到A
    类型检查阶段
    // funE 的参数声明：List<? extends A>
    // 这表示：接受任何 List，其元素类型是 A 或 A 的子类

    // 检查 funE(listB) 调用：
    // listB 的类型是 List<B>
    // B 是 A 的子类
    // 所以 List<B> 符合 List<? extends A> 的要求

    编译阶段
    // 编译前（带有泛型信息）
    List<? extends A> listA

    // 擦除后（保留边界类型A）
    List listA  // 内部实际引用的类型是 A，而不是 Object

    // 编译器生成等价字节码代码
    // 源代码
    public static void funE(List<? extends A> listA) {
        A a = listA.get(0);
    }

    // 擦除后的代码
    public static void funE(List listA) {
        A a = (A) listA.get(0);  // 编译器插入从Object到A的转换
    }
     */
    public static void funE(List<? extends A> listA) {

    }

    public static void funF(List<B> listB) {
        funE(listB);
    }


    public static void main(String[] args) {
//        List<Object> list = new ArrayList<>();
//        list.add(new Cat());
//        list.add(new Dog());// 这样的操作是可以的， 因为List里面存放的是Object类型， Dog 和 Cat实际上进行了类型转化（自动向上类型转化）


    }
}
