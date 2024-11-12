package proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * @author mojie
 * @date 2024/11/12 22:45
 * @description: 定义代理行为
 *
 * 我给你提供了一个代理用法， 你需要告诉我我这个代理应该干什么
 * 我告诉你方法 你告诉我 你希望在原方法的基础上做什么
 */
public class DoSomethingInvocationHandler implements InvocationHandler {
    private final Object target;

    public DoSomethingInvocationHandler(Object target) {
        this.target = target;
    }

    /**
     * 所谓代理处理器， 就是每次调用某个实现类的方法， 就是调用invoke
     * 每次调用代理对象的任何方法，都会被转发到invoke方法
     *
     * @param proxy the proxy instance that the method was invoked on
     *
     *
     * @param method the {@code Method} instance corresponding to
     * the interface method invoked on the proxy instance.  The declaring
     * class of the {@code Method} object will be the interface that
     * the method was declared in, which may be a superinterface of the
     * proxy interface that the proxy class inherits the method through.
     *
     * @param args an array of objects containing the values of the
     * arguments passed in the method invocation on the proxy instance,
     * or {@code null} if interface method takes no arguments.
     * Arguments of primitive types are wrapped in instances of the
     * appropriate primitive wrapper class, such as
     * {@code java.lang.Integer} or {@code java.lang.Boolean}.
     *
     * @return
     * @throws Throwable
     */
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        // proxy就是对代理对象自身的引用 主要可以实现链式调用 等一些复杂操作

        System.out.println("开始代理");
        System.out.println("do something");
        System.out.println("开始调用方法 " + method.getName());
        // 第一个参数是具体调用这个方法的实例
        Object result = method.invoke(target, args);
        System.out.println("方法调用结束");
        return result;
    }
}
