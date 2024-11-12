package serializable;

import java.io.Serializable;

/*
什么是java 序列化？
序列化： java中的序列化机制能够将一个实例对象写入到一个字节流中（只序列化对象的属性值， 而不会去序列化方法），
序列化后的对象可用于网络传输， 或者持久化到数据库、 磁盘中

反序列化： 需要对象的时候， 在通过字节流中的信息来重构一个相同的对象

Java中要使一个类可以序列化， 实现 Serializable 接口是最简单的
Serializable接口中没有方法和字段， 仅仅用于标识 具体的交给JVM

打开writeObject方法的源码看一下，发现方法中有这么一个逻辑，当要写入的对象是String、Array、Enum、Serializable类型的对象则可以正常序列化，否则会抛出NotSerializableException异常。


如果你的 User：
完全使用自定义序列化器
不会用到Java原生序列化
不需要向后兼容
那么 implements Serializable 是可以去掉的。

 */

/**
 * 测试序列化 写入磁盘
 * 如果去掉implements Serializable 会报 NotSerializableException
 */
public class User implements Serializable {
    private static final long serialVersionUID = 1L;
    private String name;
    private String age;
    private transient String title; // 序列化对象时如果希望哪个属性不被序列化，则用transient关键字修饰即可

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }


    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", age='" + age + '\'' +
                ", title='" + title + '\'' +
                '}';
    }
}
