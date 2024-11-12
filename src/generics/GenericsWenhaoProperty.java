package generics;

class RpcRequest<T> {
    public Class<?>[] paramsType;
    public Class<T>[] paramsType2;
    public RpcRequest(Class<?>[] paramsType) {
        this.paramsType = paramsType;
    }
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
}
