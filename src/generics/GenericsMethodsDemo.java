package generics;

import com.sun.tools.javah.Gen;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

public class GenericsMethodsDemo {
    /**
     *
     * @param c
     * @return
     * @param
     * @throws InstantiationException
     * @throws IllegalAccessException
     */
    // <T> 声明一个方法是泛型方法 T表示该方法返回的是泛型类型 Class<T>参数，用于创建泛型T代表的类的具体对象
    // 这个方法就是传入一个class类， 然后返回一个这个类的对象
    public <T> T getObject(Class<T> c) throws InstantiationException, IllegalAccessException {
        // 创建泛型对象
        T t = c.newInstance();
        return t;
    }

    public <T> T getObject(Class<T> c, Object var) throws InstantiationException, IllegalAccessException, NoSuchMethodException, InvocationTargetException {
        // 获取所有构造函数
        Constructor<?>[] constructors = c.getDeclaredConstructors();
        Constructor<T> constructor = null;

        // 查找参数个数为1的构造函数
        for (Constructor<?> ctor : constructors) {
            if (ctor.getParameterCount() == 1) {
                @SuppressWarnings("unchecked")
                Constructor<T> temp = (Constructor<T>) ctor;
                constructor = temp;
                break;
            }
        }

        if (constructor == null) {
            throw new NoSuchMethodException("No suitable constructor found");
        }

        return constructor.newInstance(var);
    }
    /*
    泛型擦除
       // 源代码
   class Generics<T> {
       private T var;
       public Generics(T var) { this.var = var; }
   }

   // 编译后实际的类（泛型擦除后）
   class Generics {
       private Object var;
       public Generics(Object var) { this.var = var; }
   }
   所以 Generics.class 实际上指向的是擦除后的类，它的构造函数签名是 Generics(Object)

     */
    public static void main(String[] args) {
        GenericsMethodsDemo genericsMethodsDemo = new GenericsMethodsDemo();
        try {
//            Generics object = (Generics)genericsMethodsDemo.getObject(Class.forName("generics.Generics"));
            Integer a = 11;
            /*
            执行getObject时, 参数 c 是 Generics.class 参数var是Integer.class
            当尝试获取构造函数时
            // 实际在寻找这个构造函数
            Constructor constructor = Generics.class.getConstructor(Integer.class);
            但是，我只有 public Generics(Object var) { ... }   // 这个才是真实存在的
            因此，不能用
            public <K, T> T getObject(Class<T> c, K var) throws InstantiationException, IllegalAccessException,
                NoSuchMethodException, InvocationTargetException {
                // 获取参数类型的构造函数
                Constructor<T> constructor = c.getDeclaredConstructor(Object.class);
                return constructor.newInstance(var);
           }
            不能用这种方法来获取构造函数
             */
            Generics<Integer> object = (Generics<Integer>) genericsMethodsDemo.getObject(Class.forName("generics.Generics"), a);
            System.out.println(object.getVar());
        } catch (InstantiationException e) {
            throw new RuntimeException(e);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } catch (InvocationTargetException e) {
            throw new RuntimeException(e);
        } catch (NoSuchMethodException e) {
            throw new RuntimeException(e);
        }
    }
}
