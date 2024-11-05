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
public class GenericsMethodsDemo1 {
    public static void main(String[] args) {
        List<Object> list = new ArrayList<>();
        list.add(new Cat());
        list.add(new Dog());// 这样的操作是可以的， 因为List里面存放的是Object类型， Dog 和 Cat实际上进行了类型转化（自动向上类型转化）
        //
    }
}
