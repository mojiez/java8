package lambda;
interface Strategy {
    String approach(String msg);
}
interface StrategyTwice {
    String approach1(String msg);
    String approach2(String msg);
}
class Soft implements Strategy {

    @Override
    public String approach(String msg) {
        return msg.toLowerCase() + "?";
    }
}

class Unrelated {
    static String twice(String msg) {
        return msg + " " + msg;
    }
}

public class Strategize {
    // 函数式编程
    // 使用代码以某种方式操纵其他代码
    Strategy strategy;
    String msg;
    Strategize(String msg) {
        // 最原始的方法
        strategy = new Soft(); // [1]
        this.msg = msg;
    }
    void communicate() {
        System.out.println(strategy.approach(msg));
    }
    void changeStrategy(Strategy strategy) {
        this.strategy = strategy;
    }

    public static void main(String[] args) {
        Strategy[] strategies = {
                new Strategy() { // [2]
                    // 使用了匿名内部类 来实现接口
                    public String approach(String msg) {
                        return msg.toUpperCase() + "!";
                    }
                },

                // 使用lambda表达式来实现接口
                // 要使用 lambda表达式来实现接口，接口必须满足：有且仅有一个抽象方法（它是一个函数式接口）
                // 函数式接口 还可以有若干个默认方法和静态方法 不受限制
                msg -> msg.substring(0, 5), // [3]

                // java8的方法引用  类或对象的名称::方法的名称（不需要参数列表）
                // 如果一个方法 跟接口定义的方法 接收参数和返回值 都相同的话 就可以使用方法引用的方法 来实现函数式接口
                Unrelated::twice // [4]
        };
        Strategize s = new Strategize("Hello there");
        s.communicate();
        for(Strategy newStrategy : strategies) {
            // 使用 给接口 定义不同的实现类的方法
            s.changeStrategy(newStrategy); // [5]
            s.communicate(); // [6]
        }
    }
}

/*
方法引用 lambda表达式的出现 能够让我们在 需要时传递功能
 */
