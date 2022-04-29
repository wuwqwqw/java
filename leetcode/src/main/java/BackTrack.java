import org.junit.Test;
import org.springframework.util.StringUtils;

import java.nio.charset.StandardCharsets;
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

//    无重复字符串的排列组合。编写一种方法，计算某字符串的所有排列组合，字符串每个字符均不相同。
//    示例1:
//    输入：S = "qwe"
//    输出：["qwe", "qew", "wqe", "weq", "ewq", "eqw"]
//    链接：https://leetcode-cn.com/problems/permutation-i-lcci

    List<String> lists = new ArrayList<>();
    boolean []vis;

    public String[] permutation2(String S) {
        vis = new boolean[S.length()];
        backTrack(S,vis,new StringBuilder());
        String[] ans = new String[lists.size()];
        int i = 0;
        for (String s : lists) {
            ans[i] = s;
            ++i;
        }
        return ans;
    }

    public void backTrack(String S,boolean[]vis,StringBuilder builder){
        if (builder.length()==S.length()){
            lists.add(new String(builder));
        }else {
            for (int i = 0;i<S.length();++i){
                if (!vis[i]){
                    vis[i] = true;
                    builder.append(S.charAt(i));
                    backTrack(S,vis,builder);
                    builder.deleteCharAt(builder.length()-1);
                    vis[i] = false;
                }
            }
        }
    }

//    给定字典中的两个词，长度相等。写一个方法，把一个词转换成另一个词， 但是一次只能改变一个字符。每一步得到的新词都必须能在字典中找到。
//    编写一个程序，返回一个可能的转换序列。如有多个可能的转换序列，你可以返回任何一个。
//    示例 1:
//    输入:
//    beginWord = "hit",
//    endWord = "cog",
//    wordList = ["hot","dot","dog","lot","log","cog"]
//    输出:["hit","hot","dot","lot","log","cog"]
//    链接：https://leetcode-cn.com/problems/word-transformer-lcci
//    fixme 走过的路不能再走

    List<String> res = new ArrayList<>();
    List<String> ans=new ArrayList<>();
    boolean returnFlag = false;
    boolean[] visit;
    public List<String> findLadders(String beginWord, String endWord, List<String> wordList) {
        visit = new boolean[wordList.size()];
        ans.add(beginWord);
        if (!wordList.contains(endWord)){
            return res;
        }
        backTrack(beginWord,endWord,wordList);
        return res;
    }

    public void backTrack(String beginWord, String endWord, List<String> wordList){
        if (returnFlag||ans.get(ans.size()-1).equals(endWord)){
            returnFlag = true;
            if (res.isEmpty()){
                res.addAll(ans);
            }
        }else {
            for (int i = 0;i<wordList.size();++i){
                if (!visit[i]&&isNearWord(beginWord,wordList.get(i))){
                    visit[i] = true;
                    ans.add(wordList.get(i));
                    backTrack(wordList.get(i),endWord,wordList);
                    ans.remove(ans.size()-1);
                }
            }
        }
    }

    public boolean isNearWord(String a,String b){
        int count = 0;
        if (a.length()!=b.length()){
            return false;
        }
        for (int i =0;i<a.length();++i){
            if (a.charAt(i)!=b.charAt(i)){
                count++;
            }
        }
        return count == 1;
    }

//    给定一个仅包含数字2-9的字符串，返回所有它能表示的字母组合。答案可以按 任意顺序 返回。
//    给出数字到字母的映射如下（与电话按键相同）。注意 1 不对应任何字母。
//    示例 1：
//    输入：digits = "23"
//    输出：["ad","ae","af","bd","be","bf","cd","ce","cf"]
//    链接：https://leetcode-cn.com/problems/letter-combinations-of-a-phone-number

    Map<Character, String> phoneMap = new HashMap<Character, String>() {{
        put('2', "abc");
        put('3', "def");
        put('4', "ghi");
        put('5', "jkl");
        put('6', "mno");
        put('7', "pqrs");
        put('8', "tuv");
        put('9', "wxyz");
    }};
    List<String> phoneLists = new ArrayList<>();

    public List<String> letterCombinations(String digits) {
        if (digits.length()==0){
            return phoneLists;
        }
        backTrack(digits,0,new StringBuilder());
        return phoneLists;
    }

    public void backTrack(String digits,int index,StringBuilder builder){
        if (builder.length() == digits.length()){
            phoneLists.add(String.valueOf(builder));
        }else {
            String s = phoneMap.get(digits.charAt(index));
            for (int i = 0;i<s.length();++i){
                builder.append(s.charAt(i));
                backTrack(digits,index+1,builder);
                builder.deleteCharAt(builder.length()-1);
            }
        }
    }

//    给你一个 无重复元素 的整数数组candidates 和一个目标整数target，找出candidates中可以使数字和为目标数target的所有不同组合,并以列表形式返回。
//    你可以按 任意顺序 返回这些组合。
//    candidates 中的 同一个 数字可以 无限制重复被选取 。如果至少一个数字的被选数量不同，则两种组合是不同的。
//    对于给定的输入，保证和为target 的不同组合数少于 150 个。
//    示例1：
//    输入：candidates = [2,3,6,7], target = 7
//    输出：[[2,2,3],[7]]
//    解释：
//            2 和 3 可以形成一组候选，2 + 2 + 3 = 7 。注意 2 可以使用多次。
//            7 也是一个候选， 7 = 7 。
//    仅有这两种组合。
//    链接：https://leetcode-cn.com/problems/combination-sum

    List<List<Integer>> combinationLists = new ArrayList<>();

    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        backTrack(candidates,target,0,new ArrayList<>(),0);
        return combinationLists;
    }

    public void backTrack(int []candidates,int target,int sum,List<Integer> list,int index){
        if (sum>target){
            return;
        }else if (sum == target){
            combinationLists.add(new ArrayList<>(list));
        }else {
            for (int i = index;i<candidates.length;++i){
                sum+=candidates[i];
                list.add(candidates[i]);
                backTrack(candidates,target,sum,list,i);
                sum-=candidates[i];
                list.remove(list.size()-1);
            }
        }
    }

//    给定一个不含重复数字的数组 nums ，返回其 所有可能的全排列 。你可以 按任意顺序 返回答案。
//    示例 1：
//    输入：nums = [1,2,3]
//    输出：[[1,2,3],[1,3,2],[2,1,3],[2,3,1],[3,1,2],[3,2,1]]
//    链接：https://leetcode-cn.com/problems/permutations


    boolean []permuteVis;
    List<List<Integer>> permuteLists = new ArrayList<>();

    public List<List<Integer>> permute(int[] nums) {
        permuteVis = new boolean[nums.length];
        backTrack(nums,new ArrayList<>());
        return permuteLists;
    }

    public void backTrack(int []nums,List<Integer> list){
        if (list.size() == nums.length){
            permuteLists.add(new ArrayList<>(list));
        }else {
            for (int i = 0;i<nums.length;++i){
                if (!permuteVis[i]){
                    permuteVis[i] = true;
                    list.add(nums[i]);
                    backTrack(nums,list);
                    permuteVis[i] = false;
                    list.remove(list.size()-1);
                }
            }
        }
    }

//    给定一个数字，我们按照如下规则把它翻译为字符串：0 翻译成 “a” ，1 翻译成 “b”，……，11 翻译成 “l”，……，25 翻译成 “z”。一个数字可能有多个翻译。请编程实现一个函数，用来计算一个数字有多少种不同的翻译方法。
//    示例 1:
//    输入: 12258
//    输出: 5
//    解释: 12258有5种不同的翻译，分别是"bccfi", "bwfi", "bczi", "mcfi"和"mzi"
//    链接：https://leetcode-cn.com/problems/ba-shu-zi-fan-yi-cheng-zi-fu-chuan-lcof

    int ansTranslateNum=0;
    public int translateNum(int num) {
        bfs(String.valueOf(num),0);
        return ansTranslateNum;
    }
    public void bfs(String num,int index){
        if(index==num.length()){
            ansTranslateNum++;
        }else if(index<num.length()){
            if(num.charAt(index)-'0'<=0||num.charAt(index)-'0'>2){
                bfs(num,index+1);
            }else if(num.charAt(index)-'0'==1){
                bfs(num,index+1);
                bfs(num,index+2);
            }else{
                if(index+1<num.length()&&num.charAt(index+1)-'0'>5){
                    bfs(num,index+1);
                }else{
                    bfs(num,index+1);
                    bfs(num,index+2);
                }
            }
        }
    }
}
