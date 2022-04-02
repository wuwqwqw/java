import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class BackTrack {

//    括号。设计一种算法，打印n对括号的所有合法的（例如，开闭一一对应）组合。
//    说明：解集不能包含重复的子集。
//    例如，给出n = 3，生成结果为：
//            [
//            "((()))",
//            "(()())",
//            "(())()",
//            "()(())",
//            "()()()"
//            ]
//    链接：https://leetcode-cn.com/problems/bracket-lcci

    public List<String> generateParenthesis(int n) {
        // 设置一个左括号数量，和右括号数量跟着回溯，来剪枝
        char[] brackets = new char[]{'(', ')'};
        StringBuilder combo = new StringBuilder();
        List<String> combos = new ArrayList<>();
        backTrack(combos, combo, brackets, 0, 0, n);
        return combos;
    }

    public void backTrack(List<String> combos, StringBuilder combo, char[] brackets, int leftNum, int rightNum, int n) {
        if (rightNum <= leftNum && leftNum <= n) {
            if (combo.length() == n * 2) {
                combos.add(String.valueOf(combo));
                return;
            }
            for (int i = 0; i < 2; ++i) {
                combo.append(brackets[i]);
                int i1 = brackets[i] == '(' ? ++leftNum : ++rightNum;
                backTrack(combos, combo, brackets, leftNum, rightNum, n);
                int i2 = brackets[i] == '(' ? --leftNum : --rightNum;
                combo.deleteCharAt(combo.length() - 1);
            }
        }
    }

    @Test
    public void testGene() {
        List<String> strings = generateParenthesis(3);
    }

    //    幂集。编写一种方法，返回某集合的所有子集。集合中不包含重复的元素。
//    说明：解集不能包含重复的子集。
//    示例:
//    输入： nums = [1,2,3]
//    输出：
//            [
//            [3],
//            [1],
//            [2],
//            [1,2,3],
//            [1,3],
//            [2,3],
//            [1,2],
//            []
//            ]
//    链接：https://leetcode-cn.com/problems/power-set-lcci

    public List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> combos = new ArrayList<List<Integer>>();
        ArrayList<Integer> combo = new ArrayList<>();
        backTrack(combos, combo, nums, 0);
        return combos;
    }

    public void backTrack(List<List<Integer>> combos, List<Integer> combo, int[] nums, int index) {
        combos.add(new ArrayList<>(combo));
        if (index > nums.length - 1) {
            return;
        } else {
            for (int i = index; i < nums.length; ++i) {
                combo.add(nums[i]);
                backTrack(combos, combo, nums, i + 1);
                combo.remove(combo.size() - 1);
            }
        }
    }
}
