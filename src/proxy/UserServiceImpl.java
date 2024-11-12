package proxy;

/**
 * @author mojie
 * @date 2024/11/12 22:42
 * @description:
 */
public class UserServiceImpl implements UserService{
    @Override
    public void save(String data) {
        System.out.println("保存数据 " + data);
    }

    @Override
    public void find(String cao) {
        System.out.println("寻找数据 " + cao);
    }
}
