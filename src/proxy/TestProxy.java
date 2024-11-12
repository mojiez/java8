package proxy;

import serializable.User;

import java.lang.reflect.Proxy;

/**
 * @author mojie
 * @date 2024/11/12 22:51
 * @description:
 */
public class TestProxy {
    public static void main(String[] args) {
        // 创建目标对象（需要被代理的对象）
        UserService userService = new UserServiceImpl();

        // 创建代理对象
        UserService userServiceProxy = (UserService)Proxy.newProxyInstance(
                UserService.class.getClassLoader(), // 类加载器
                new Class<?>[]{UserService.class},  // 需要代理的接口
                new DoSomethingInvocationHandler(userService) // 代理处理器 指定需要被代理的对象
        );

        userServiceProxy.save("test");
        userServiceProxy.find("cao");
    }
}
