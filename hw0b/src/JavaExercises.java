import java.util.ArrayList;
import java.util.List;

public class JavaExercises {

    /** 返回一个数组 [1, 2, 3, 4, 5, 6] */
    public static int[] makeDice() {
        // TODO: 填充此函数。
        int[] arr1 = {1, 2, 3, 4, 5, 6};
        return arr1;
    }

    /** 根据顾客返回相应的订单。
     *  如果顾客是 Ergun，返回 ["beyti", "pizza", "hamburger", "tea"]。
     *  如果顾客是 Erik，返回 ["sushi", "pasta", "avocado", "coffee"]。
     *  在其他任何情况下，返回一个大小为 3 的空 String 数组。 */
    public static String[] takeOrder(String customer) {
        // TODO: 填充此函数。
        String[] s1 = {"beyti", "pizza", "hamburger", "tea"};
        String[] s2 = {"sushi", "pasta", "avocado", "coffee"};
        String[] s3 = new String[3];

        if(customer.equals("Ergun")){
            return s1;
        } else if (customer.equals("Erik")) {
            return s2;
        }else {
            return s3;
        }
    }

    /** 返回给定数组中最大元素和最小元素之间的正差值。
     *  假设数组非空。 */
    public static int findMinMax(int[] array) {
        // TODO: 填充此函数。
        int max_num = array[0];
        int min_num = array[0];
        for (int i = 1; i < array.length; i++) {
            if (array[i] > max_num){
                max_num = array[i];
            }
            if (array[i] < min_num){
                min_num = array[i];
            }
        }

        return max_num - min_num;
    }

    /**
     * 使用递归计算从输入数字 n 开始的冰雹序列（Hailstone sequence），并将其作为整数列表返回。
     * 冰雹序列描述如下：
     *    - 选择一个正整数 n 作为开始
     *        - 如果 n 是偶数，将 n 除以 2
     *        - 如果 n 是奇数，将 n 乘以 3 并加 1
     *    - 继续此过程，直到 n 变为 1
     */
    public static List<Integer> hailstone(int n) {
        return hailstoneHelper(n, new ArrayList<>());
    }

    private static List<Integer> hailstoneHelper(int x, List<Integer> list) {
        // TODO: 填充此函数。
        list.add(x);
        if (x == 1){
            return list;
        }
        if(x % 2 == 0){
            return hailstoneHelper(x/2, list);
        }else{
            return hailstoneHelper(x * 3 + 1, list);
        }
    }

}