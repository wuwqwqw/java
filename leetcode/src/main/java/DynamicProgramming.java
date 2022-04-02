import org.junit.Test;

import java.util.Stack;

public class DynamicProgramming {

//    给定一个整数数组，找出总和最大的连续数列，并返回总和。
//    示例：
//    输入： [-2,1,-3,4,-1,2,1,-5,4]
//    输出： 6
//    解释： 连续子数组 [4,-1,2,1] 的和最大，为 6。
//    进阶：
//    如果你已经实现复杂度为 O(n) 的解法，尝试使用更为精妙的分治法求解。
//    链接：https://leetcode-cn.com/problems/contiguous-sequence-lcci


    public int maxSubArray(int[] nums) {
        int dp[] = new int[nums.length];
        dp[0] = nums[0];
        for (int i = 1; i < nums.length; ++i) {
            dp[i] = Math.max(nums[i], dp[i - 1] + nums[i]);
        }
        int max = Integer.MIN_VALUE;
        for (int tem : dp) {
            if (tem > max) {
                max = tem;
            }
        }
        return max;
    }

//    三步问题。有个小孩正在上楼梯，楼梯有n阶台阶，
//    小孩一次可以上1阶、2阶或3阶。实现一种方法，
//    计算小孩有多少种上楼梯的方式。结果可能很大，你需要对结果模1000000007。
//    示例1:
//    输入：n = 3
//    输出：4
//    说明: 有四种走法
//    链接：https://leetcode-cn.com/problems/three-steps-problem-lcci

    public int waysToStep(int n) {
        long[] dp = new long[n+4];
        dp[0] = 1;
        dp[1] = 2;
        dp[2] = 4;
        if(n<=3){
            return (int) dp[n - 1];
        }
        for (int i = 3; i < n; ++i){
            dp[i] = (dp[i-1] + dp[i-2] + dp[i-3]);
        }
        return (int)(dp[n-1]%1000000007);
    }

//    一个有名的按摩师会收到源源不断的预约请求，每个预约都可以选择接或不接。
//    在每次预约服务之间要有休息时间，因此她不能接受相邻的预约。
//    给定一个预约请求序列，替按摩师找到最优的预约集合（总预约时间最长），返回总的分钟数。
//    注意：本题相对原题稍作改动
//    示例 1：
//    输入： [1,2,3,1]
//    输出： 4
//    解释： 选择 1 号预约和 3 号预约，总时长 = 1 + 3 = 4。
//    链接：https://leetcode-cn.com/problems/the-masseuse-lcci

    public int massage(int[] nums) {
        int[] dp = new int[nums.length];
        switch (nums.length){
            case 0 : return 0;
            case 1 : return nums[0];
            case 2 : return Math.max(nums[0],nums[1]);
            case 3 : return Math.max(nums[1],nums[0]+nums[2]);
        }
        dp[0] = nums[0];
        dp[1] = Math.max(nums[0],nums[1]);
        dp[2] = Math.max(nums[1],nums[0]+nums[2]);
        int max = Math.max(Math.max(dp[1],dp[2]),dp[0]);
        for (int i = 3;i < nums.length;++i){
            dp[i] = Math.max(dp[i-2],dp[i-3])+nums[i];
            if (dp[i]>max){
                max = dp[i];
            }
        }
        return max;
    }
    //[2,7,9,3,1]

    @Test
    public void testMassage(){
        int ans = massage(new int[]{2,7,9,3,1});
    }


//    给定一个32位整数 num，你可以将一个数位从0变为1。请编写一个程序，找出你能够获得的最长的一串1的长度。
//    示例 1：
//    输入: num = 1775(11011101111)
//    输出: 8
//    链接：https://leetcode-cn.com/problems/reverse-bits-lcci

    public int reverseBits(int num) {
        int dp0[] = new int[32];
        int dp1[] = new int[32];
        int max = -1;
        for (int i = 0; i < 32; ++i) {
            if ((num & 1) == 1) {
                dp0[i] = i > 0 ? dp0[i - 1] + 1 : 1;
                dp1[i] = i > 0 ? dp1[i - 1] + 1 : 1;
            } else {
                dp0[i] = 0;
                dp1[i] = i > 0 ? dp0[i - 1] + 1 : 1;
            }
            num >>= 1;
            if (max < dp1[i]) {
                max = dp1[i];
            }
        }
        return max;
    }

//    给定一个布尔表达式和一个期望的布尔结果 result，
//    布尔表达式由 0 (false)、1 (true)、& (AND)、 | (OR) 和 ^ (XOR) 符号组成。
//    实现一个函数，算出有几种可使该表达式得出 result 值的括号方法。
//    示例 1:
//    输入: s = "1^0|0|1", result = 0
//    输出: 2
//    解释:两种可能的括号方法是
//    1^(0|(0|1))    1^((0|0)|1)
//    链接：https://leetcode-cn.com/problems/boolean-evaluation-lcci

    public int countEval(String s, int result) {
        String script = "";
        for (int i = 0;i<s.length();++i){

        }
        return Integer.parseInt(process(script));
    }

    public String process(String s){
        if (s.length()<=1){
            return s;
        }
        switch (s.charAt(1)){
            case '&':return (s.charAt(0) == s.charAt(2) ? s.charAt(0) : '0') +s.substring(3);
            case '|':return s.contains("1") ? "1" : "0";
            case '^':return s.charAt(0) == s.charAt(2) ? "1" : "0";
        }
        return "";
    }
}
