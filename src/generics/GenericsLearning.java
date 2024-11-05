package generics;

/**
 * 最简单的泛型类
 * @param <T>
 */
class Generics<T> {
    private T var; // var的类型由外部指定

    public Generics(T var) {
        this.var = var;
    }

    public Generics() {
    }

    public T getVar(){ // 返回值类型由外部指定
        return var;
    }
    public void setVar(T var) { //函数参数的类型由外部指定
        this.var = var;
    }
}

/**
 * 指定两个泛型类型
 * 泛型类
 * @param <K>
 * @param <V>
 */
class Point<K,V> {
    private K key;
    private V value;

    public K getKey() {
        return key;
    }

    public void setKey(K key) {
        this.key = key;
    }

    public V getValue() {
        return value;
    }

    public void setValue(V value) {
        this.value = value;
    }
}

/*
泛型接口
在接口上定义泛型
 */
interface Info<T> {
    public T getVar(); // 定义抽象方法， 抽象方法的返回值是泛型类型
}

class InfoImpl<T> implements Info<T> {
    private T var;

    public InfoImpl() {
    }

    public InfoImpl(T var) {
        this.var = var;
    }

    @Override
    public T getVar() {
        return this.var;
    }

    public void setVar(T var) {
        this.var = var;
    }
}
public class GenericsLearning {
    public static void main(String[] args) {
//        /*
//        最简单的泛型类
//         */
//        Generics<String> generics = new Generics<>();
////        generics.setVar(123);
//        generics.setVar("nihao");
//        System.out.println(generics.getVar());

//        /*
//        多泛型的 泛型对象
//         */
//        Point<String, Integer> point = new Point<>();
//        point.setKey("nihao");
//        point.setValue(123);
//        System.out.println(point.getKey() + point.getValue());
    }
}
