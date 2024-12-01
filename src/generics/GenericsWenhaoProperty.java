package generics;

import java.util.ArrayList;
import java.util.List;

class RpcRequest<T> {
    public Class<?>[] paramsType;
    public Class<T>[] paramsType2;
    public RpcRequest(Class<?>[] paramsType) {
        this.paramsType = paramsType;
    }
}

class ServiceRegisterInfo<T> {
    public Class<? extends T> aClass;
}
public class GenericsWenhaoProperty {
//    /**
//     * 这样写就是错误的
//     * @param args
//     */
//    public static void main(String[] args) {
//        RpcRequest rpcRequest = new RpcRequest(new Class<String>[]{Integer.class, String.class, Double.class});
////        for(Class<?> clazz : rpcRequest.paramsType) {
////            System.out.println(clazz);
////        }
//        rpcRequest.paramsType2 = new Class[]{Integer.class, String.class};
//        for (Class clazz : rpcRequest.paramsType2) {
//            System.out.println(clazz);
//        }
//    }

    /**
     * 这样写不会报错， 因为类型检查阶段可以混过去
     * @param args
     */
    public static void main(String[] args) {
        RpcRequest rpcRequest = new RpcRequest(new Class[]{Integer.class, String.class, Double.class});
//        for(Class<?> clazz : rpcRequest.paramsType) {
//            System.out.println(clazz);
//        }
        rpcRequest.paramsType2 = new Class[]{Integer.class, String.class};
        for (Class clazz : rpcRequest.paramsType2) {
            System.out.println(clazz);
        }
    }

    public void testServiceRegisterInfo() {
        // 这样写就说明serviceRegisterInfo中的aClass应该是Number或者Number的子类的 Class 对象
        ServiceRegisterInfo<Number> serviceRegisterInfo = new ServiceRegisterInfo<>();

        // 这样写 就说明 serviceRegisterInfoList 中的每个元素 serviceRegisterInfo 中的aClass 都是Number或者Number的子类的 Class 对象
        List<ServiceRegisterInfo<Number>> serviceRegisterInfoList = new ArrayList<>();

        // 这样写 serviceRegisterInfoList1 中的每个元素 serviceRegisterInfo 中的aClass 都可以不同
        List<ServiceRegisterInfo<?>> serviceRegisterInfoList1 = new ArrayList<>();
        ServiceRegisterInfo<Number> serviceRegisterInfo1 = new ServiceRegisterInfo<>();
        ServiceRegisterInfo<String> serviceRegisterInfo2 = new ServiceRegisterInfo<>();
        serviceRegisterInfoList1.add(serviceRegisterInfo1);
        serviceRegisterInfoList1.add(serviceRegisterInfo2);
        // 这种情况下第一个元素中的aClass是 Number或者Number的子类的Class对象
        // 第二个元素中的aClass是 String或者String的子类的Class对象
    }

}
