package order;

import java.util.Arrays;
import java.util.Comparator;

/**
 * @author mojie
 * @date 2024/11/27 10:07
 * @description: 对int数组进行排序的写法
 */
public class IntArrayOrder {
    /**
     * 对 int[] 进行排序
     */
    public void orderArray1() {
//        // int[] 升序排序 直接用 Arrays.sort() 就行
//        int[] arr = new int[]{5,4,3,2,1,3,4,2};
//        Arrays.sort(arr);
//        Arrays.stream(arr).forEach(System.out::println);

        // int[] 降序排序 需要先将int 转为 Integer， 然后用Arrays.sort + 自定义Comparator
        int[] arr = new int[]{2,1,3,5,4,3};
        Integer[] array = Arrays.stream(arr).boxed().toArray(size -> new Integer[size]);
        Arrays.sort(array, new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                //compare 返回负数表示 o1 在 o2 前面 o1-o2

                // 假设 o1 数值大， 现在想要降序
                return o2 - o1;
            }
        });

        // 转回 int[]
        arr = Arrays.stream(array).mapToInt(Integer::intValue).toArray();
        Arrays.stream(arr).forEach(System.out::println);

        // 或者降序排序， 就倒着取呗

        // 或者手动实现排序算法
    }

    /**
     * 对int[][] 进行排序
     */
    public void orderArray2() {
        int[][] arr = new int[][]{{7,0},{4,4},{7,1},{5,0},{6,1},{5,2}};
        Arrays.sort(arr, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                if (o1[1] != o2[1]) return o2[1]-o1[1];
                return o1[0]-o2[0];
            }
        });
    }
    public static void main(String[] args) {
        IntArrayOrder intArrayOrder = new IntArrayOrder();
        intArrayOrder.orderArray1();
    }
}
