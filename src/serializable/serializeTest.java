package serializable;

import java.io.*;

public class serializeTest {
    public static void main(String[] args) {
        User user = new User();
        user.setName("zzz");
        user.setAge("18");
        user.setTitle("我是你爹");

        try {
            serialize(user);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        System.out.println("序列化前的结果" + user);
        User deUser;
        try {
            deUser = deserialize();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        System.out.println("序列化后的结果" + deUser);
    }

    /**
     * 序列化
     * @param user
     * @throws Exception
     */
    private static void serialize(User user) throws Exception {
        ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(new File("./user1.txt")));
        oos.writeObject(user);
        oos.close();
    }

    private static User deserialize() throws Exception {
        ObjectInputStream ois = new ObjectInputStream(new FileInputStream(new File("./user1.txt")));
        return (User) ois.readObject();
    }

}
