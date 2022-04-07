import org.junit.Test;

import java.util.*;

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

//    有重复字符串的排列组合。编写一种方法，计算某字符串的所有排列组合。
//    示例1:
//    输入：S = "qqe"
//    输出：["eqq","qeq","qqe"]
//    示例2:
//    输入：S = "ab"
//    输出：["ab", "ba"]
//    链接：https://leetcode-cn.com/problems/permutation-ii-lcci

    public String[] permutation(String S) {
        char[] chars = new char[S.length()];
        for (int i = 0; i < S.length(); ++i) {
            chars[i] = S.charAt(i);
        }
        Arrays.sort(chars);
        S = String.valueOf(chars);
        List<String> strings = new ArrayList<>();
        boolean vis[] = new boolean[S.length()];
        StringBuilder builder = new StringBuilder("");
        backTrack(builder, strings, S, vis);
        String[] ans = new String[strings.size()];
        int i = 0;
        for (String s : strings) {
            ans[i] = s;
            ++i;
        }
        return ans;
    }

    public void backTrack(StringBuilder builder, List<String> strings, String S, boolean[] vis) {
        if (builder.length() == S.length()) {
            strings.add(String.valueOf(builder));
        } else {
            for (int i = 0; i < S.length(); ++i) {
                if (!vis[i]) {
                    vis[i] = true;
                    builder.append(S.charAt(i));
                    backTrack(builder, strings, S, vis);
                    vis[i] = false;
                    builder.deleteCharAt(builder.length() - 1);
                    while (i + 1 < S.length() && S.charAt(i) == S.charAt(i + 1)) {
                        ++i;
                    }
                }
            }
        }
    }

    @Test
    public void testPermutation() {
        permutation("qqe");
    }
}
