import org.junit.Test;

import java.util.*;

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
        long[] dp = new long[n + 4];
        dp[0] = 1;
        dp[1] = 2;
        dp[2] = 4;
        if (n <= 3) {
            return (int) dp[n - 1];
        }
        for (int i = 3; i < n; ++i) {
            dp[i] = (dp[i - 1] + dp[i - 2] + dp[i - 3]);
        }
        return (int) (dp[n - 1] % 1000000007);
    }

//    给定一个数组 prices ，它的第i 个元素prices[i] 表示一支给定股票第 i 天的价格。
//    你只能选择 某一天 买入这只股票，并选择在 未来的某一个不同的日子 卖出该股票。设计一个算法来计算你所能获取的最大利润。
//    返回你可以从这笔交易中获取的最大利润。如果你不能获取任何利润，返回 0 。
//    示例 1：
//    输入：[7,1,5,3,6,4]
//    输出：5
//    解释：在第 2 天（股票价格 = 1）的时候买入，在第 5 天（股票价格 = 6）的时候卖出，最大利润 = 6-1 = 5 。
//    注意利润不能是 7-1 = 6, 因为卖出价格需要大于买入价格；同时，你不能在买入前卖出股票。
//    链接：https://leetcode-cn.com/problems/best-time-to-buy-and-sell-stock

    public int maxProfit(int[] prices) {
        int[] dp = new int[prices.length];
        dp[0] = Integer.MIN_VALUE + 10000;
        int max = Integer.MIN_VALUE;
        for (int i = 1; i < prices.length; ++i) {
            dp[i] = Math.max(dp[i - 1] + prices[i] - prices[i - 1], prices[i] - prices[i - 1]);
            if (dp[i] > max) {
                max = dp[i];
            }
        }
        return Math.max(max, 0);
    }

//    给你一个整数数组 prices ，其中prices[i] 表示某支股票第 i 天的价格。
//    在每一天，你可以决定是否购买和/或出售股票。你在任何时候最多只能持有 一股 股票。你也可以先购买，然后在 同一天 出售。
//    返回 你能获得的 最大 利润。
//    示例 1：
//    输入：prices = [7,1,5,3,6,4]
//    输出：7
//    解释：在第 2 天（股票价格 = 1）的时候买入，在第 3 天（股票价格 = 5）的时候卖出, 这笔交易所能获得利润 = 5 - 1 = 4 。
//    随后，在第 4 天（股票价格 = 3）的时候买入，在第 5 天（股票价格 = 6）的时候卖出, 这笔交易所能获得利润 = 6 - 3 = 3 。
//    总利润为 4 + 3 = 7 。
//    链接：https://leetcode-cn.com/problems/best-time-to-buy-and-sell-stock-ii

    public int maxProfit2(int[] prices) {
        int[] dp = new int[prices.length];
        dp[0] = 0;
        int max = 0;
        int maxBeforeTwoDays = 0;
        for (int i = 1;i<prices.length;++i){
            dp[i] = Math.max(Math.max(dp[i-1]+prices[i]-prices[i-1],prices[i]-prices[i-1]+maxBeforeTwoDays),0);
            max = Math.max(max,dp[i]);
            if (i>=1){
                maxBeforeTwoDays = Math.max(maxBeforeTwoDays,dp[i-1]);
            }
        }
        return max;
    }

//    给定一个整数数组prices，其中第prices[i]表示第i天的股票价格。
//    设计一个算法计算出最大利润。在满足以下约束条件下，你可以尽可能地完成更多的交易（多次买卖一支股票）:
//    卖出股票后，你无法在第二天买入股票 (即冷冻期为 1 天)。
//    注意：你不能同时参与多笔交易（你必须在再次购买前出售掉之前的股票）。
//    示例 1:
//    输入: prices = [1,2,3,0,2]
//    输出: 3
//    解释: 对应的交易状态为: [买入, 卖出, 冷冻期, 买入, 卖出]
//    链接：https://leetcode-cn.com/problems/best-time-to-buy-and-sell-stock-with-cooldown

    public int maxProfit3(int[] prices) {
        int[] dp = new int[prices.length];
        dp[0] = 0;
        int max = 0;
        for (int i = 1;i<prices.length;++i){
            int maxTem = 0;
            for (int j = i-3;j>0;--j){
                maxTem = Math.max(maxTem,dp[j]);
            }
            dp[i] = Math.max(Math.max(dp[i-1]+prices[i]-prices[i-1],prices[i]-prices[i-1]+maxTem),0);
            max = Math.max(max,dp[i]);
        }
        return max;
    }

    @Test
    public void testMaxProfit(){
        maxProfit2(new int[]{6,1,6,4,3,0,2});
    }

//    给定一个数组，它的第 i 个元素是一支给定的股票在第 i 天的价格。
//    设计一个算法来计算你所能获取的最大利润。你最多可以完成两笔交易。
//    注意：你不能同时参与多笔交易（你必须在再次购买前出售掉之前的股票）。
//    示例1:
//    输入：prices = [3,3,5,0,0,3,1,4]
//    输出：6
//    解释：在第 4 天（股票价格 = 0）的时候买入，在第 6 天（股票价格 = 3）的时候卖出，这笔交易所能获得利润 = 3-0 = 3 。
//    随后，在第 7 天（股票价格 = 1）的时候买入，在第 8 天 （股票价格 = 4）的时候卖出，这笔交易所能获得利润 = 4-1 = 3 。
//    链接：https://leetcode-cn.com/problems/best-time-to-buy-and-sell-stock-iii

    public int maxProfit4(int[] prices) {
        int[] dp = new int[prices.length];
        dp[0] = 0;
        int max = 0;
        int maxBeforeTwoDays = 0;
        for (int i = 1;i<prices.length;++i){
            dp[i] = Math.max(Math.max(dp[i-1]+prices[i]-prices[i-1],prices[i]-prices[i-1]+maxBeforeTwoDays),0);
            max = Math.max(max,dp[i]);
            if (i>=1){
                maxBeforeTwoDays = Math.max(maxBeforeTwoDays,dp[i-1]);
            }
        }
        return max;
    }

//    给定一个整数数组prices ，它的第 i 个元素prices[i] 是一支给定的股票在第 i 天的价格。
//    设计一个算法来计算你所能获取的最大利润。你最多可以完成 k 笔交易。
//    注意：你不能同时参与多笔交易（你必须在再次购买前出售掉之前的股票）。
//    示例 1：
//    输入：k = 2, prices = [2,4,1]
//    输出：2
//    解释：在第 1 天 (股票价格 = 2) 的时候买入，在第 2 天 (股票价格 = 4) 的时候卖出，这笔交易所能获得利润 = 4-2 = 2 。
//    链接：https://leetcode-cn.com/problems/best-time-to-buy-and-sell-stock-iv

//    public int maxProfit5(int k, int[] prices) {
//
//    }

//    给你一个整数 n ，对于0 <= i <= n 中的每个 i ，计算其二进制表示中 1 的个数 ，返回一个长度为 n + 1 的数组 ans 作为答案。
//    示例 1：
//    输入：n = 2
//    输出：[0,1,1]
//    解释：
//            0 --> 0
//            1 --> 1
//            2 --> 10
//    链接：https://leetcode-cn.com/problems/counting-bits
//    先从0到10举个例子就明白了 0000 0001 0010 0011 0100 0101 0110 0111

    public int[] countBits(int n) {
        int[] bits = new int[n + 1];
        bits[0] = 0;
        int highBit = 1;
        for (int i = 1; i <= n; i++) {
            // 如果(i&(i-1))==0,说明i为2的整数次幂
            if ((i & (i - 1)) == 0) {
                highBit = i;
            }
            // 每个数的比特数都是去掉最高位的1后的数的比特数+1 例如：0011和0111 dp[11] = dp[11-8] + 1
            bits[i] = bits[i - highBit] + 1;
        }
        return bits;
    }

//    写一个函数，输入 n ，求斐波那契（Fibonacci）数列的第 n 项（即 F(N)）。斐波那契数列的定义如下：
//    F(0) = 0, F(1)= 1
//    F(N) = F(N - 1) + F(N - 2), 其中 N > 1.
//    斐波那契数列由 0 和 1 开始，之后的斐波那契数就是由之前的两数相加而得出。
//    答案需要取模 1e9+7（1000000007），如计算初始结果为：1000000008，请返回 1。
//    链接：https://leetcode-cn.com/problems/fei-bo-na-qi-shu-lie-lcof
//    很奇特的题目，直接给出了状态转移方程

    public int fib(int n) {
        if (n == 0) return 0;
        if (n == 1) return 1;
        int tem1 = 0, tem2 = 1;
        int fn = 0;
        for (int i = 2; i <= n; i++) {
            fn = (tem1 + tem2) % 1000000007;
            tem1 = tem2;
            tem2 = fn;

        }
        return fn % 1000000007;
    }

//    一只青蛙一次可以跳上1级台阶，也可以跳上2级台阶。求该青蛙跳上一个 n级的台阶总共有多少种跳法。
//    答案需要取模 1e9+7（1000000007），如计算初始结果为：1000000008，请返回 1。
//    示例 1：
//    输入：n = 2
//    输出：2
//    链接：https://leetcode-cn.com/problems/qing-wa-tiao-tai-jie-wen-ti-lcof

    public int numWays(int n) {
        long[] dp = new long[n + 1];
        if (n == 0) return 1;
        if (n == 1) return 1;
        if (n == 2) return 2;
        dp[1] = 1;
        dp[2] = 2;
        for (int i = 3; i <= n; ++i) {
            dp[i] = (dp[i - 1] + dp[i - 2]) % 1000000007;
        }
        return (int) dp[n];
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
        switch (nums.length) {
            case 0:
                return 0;
            case 1:
                return nums[0];
            case 2:
                return Math.max(nums[0], nums[1]);
            case 3:
                return Math.max(nums[1], nums[0] + nums[2]);
        }
        dp[0] = nums[0];
        dp[1] = Math.max(nums[0], nums[1]);
        dp[2] = Math.max(nums[1], nums[0] + nums[2]);
        int max = Math.max(Math.max(dp[1], dp[2]), dp[0]);
        for (int i = 3; i < nums.length; ++i) {
            dp[i] = Math.max(dp[i - 2], dp[i - 3]) + nums[i];
            if (dp[i] > max) {
                max = dp[i];
            }
        }
        return max;
    }
    //[2,7,9,3,1]

    @Test
    public void testMassage() {
        System.out.println(Integer.MAX_VALUE);
        int ans = massage(new int[]{2, 7, 9, 3, 1});
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
        for (int i = 0; i < s.length(); ++i) {

        }
        return Integer.parseInt(process(script));
    }

    public String process(String s) {
        if (s.length() <= 1) {
            return s;
        }
        switch (s.charAt(1)) {
            case '&':
                return (s.charAt(0) == s.charAt(2) ? s.charAt(0) : '0') + s.substring(3);
            case '|':
                return s.contains("1") ? "1" : "0";
            case '^':
                return s.charAt(0) == s.charAt(2) ? "1" : "0";
        }
        return "";
    }

//    有些数的素因子只有 3，5，7，请设计一个算法找出第 k 个数。注意，不是必须有这些素因子，
//    而是必须不包含其他的素因子。例如，前几个数按顺序应该是 1，3，5，7，9，15，21。
//    示例 1:
//    输入: k = 5
//    输出: 9
//    链接：https://leetcode-cn.com/problems/get-kth-magic-number-lcci

    public int getKthMagicNumber(int k) {
        int[] dp = new int[k];
        int p1 = 0, p2 = 0, p3 = 0;
        dp[0] = 1;
        for (int i = 1; i < k; i++) {
            int v1 = dp[p1] * 3, v2 = dp[p2] * 5, v3 = dp[p3] * 7;
            dp[i] = Math.min(v1, Math.min(v2, v3));
            if (dp[i] == v1) {
                p1++;
            }
            if (dp[i] == v2) {
                p2++;
            }
            if (dp[i] == v3) {
                p3++;
            }
            //步进+去重
        }
        return dp[k - 1];
    }

//    给你一根长度为 n 的绳子，请把绳子剪成整数长度的 m 段（m、n都是整数，n>1并且m>1），
//    每段绳子的长度记为 k[0],k[1]...k[m-1] 。请问 k[0]*k[1]*...*k[m-1] 可能的最大乘积是多少？
//    例如，当绳子的长度是8时，我们把它剪成长度分别为2、3、3的三段，此时得到的最大乘积是18。
//    示例 1：
//    输入: 2
//    输出: 1
//    解释: 2 = 1 + 1, 1 × 1 = 1
//    示例2:
//    输入: 10
//    输出: 36
//    解释: 10 = 3 + 3 + 4, 3 ×3 ×4 = 36
//    链接：https://leetcode-cn.com/problems/jian-sheng-zi-lcof

    public int cuttingRope(int n) {
        int[] dp = new int[n + 1];
        dp[2] = 1;
        for (int i = 3; i < n + 1; i++) {
            for (int j = 2; j < i; j++) {
                dp[i] = Math.max(dp[i], Math.max(j * (i - j), j * dp[i - j]));
            }
        }
        return dp[n];
    }

//    给你一根长度为 n 的绳子，请把绳子剪成整数长度的 m段（m、n都是整数，n>1并且m>1），
//    每段绳子的长度记为 k[0],k[1]...k[m - 1] 。请问 k[0]*k[1]*...*k[m - 1]
//    可能的最大乘积是多少？例如，当绳子的长度是8时，我们把它剪成长度分别为2、3、3的三段，此时得到的最大乘积是18。
//    答案需要取模 1e9+7（1000000007），如计算初始结果为：1000000008，请返回 1。
//    示例 1：
//    输入: 2
//    输出: 1
//    解释: 2 = 1 + 1, 1 × 1 = 1
//    链接：https://leetcode-cn.com/problems/jian-sheng-zi-ii-lcof

    // 贪心算法 要取模不能dp
    public int cuttingRope2(int n) {
        // 贪心算法  可以通过数学证明得知当等于3的绳子段数越多，乘积越大
        if (n < 4) {
            return n - 1;
        }
        long res = 1;
        while (n > 4) {
            res = (res * 3) % 1000000007;
            n -= 3;
        }
        return (int) (res * n % 1000000007);
    }

//    一个机器人位于一个 m x n网格的左上角 （起始点在下图中标记为 “Start” ）。
//    机器人每次只能向下或者向右移动一步。机器人试图达到网格的右下角（在下图中标记为 “Finish” ）。
//    问总共有多少条不同的路径？
//    示例 1：
//    输入：m = 3, n = 7
//    输出：28
//    链接：https://leetcode-cn.com/problems/unique-paths

    public int uniquePaths(int m, int n) {
        int[][] dp = new int[m][n];
        for (int i = 0;i<m;++i){
            Arrays.fill(dp[i],1);
        }
        for (int i = 1; i < m; ++i) {
            for (int j = 1; j < n; ++j) {
                dp[i][j] = dp[i][j - 1] + dp[i - 1][j];
            }
        }
        return dp[m - 1][n - 1];
    }

//    给定一个包含非负整数的 mxn网格grid ，请找出一条从左上角到右下角的路径，使得路径上的数字总和为最小。
//    说明：每次只能向下或者向右移动一步。
//    示例 1：
//    输入：grid = [[1,3,1],[1,5,1],[4,2,1]]
//    输出：7
//    解释：因为路径 1→3→1→1→1 的总和最小。
//    链接：https://leetcode-cn.com/problems/minimum-path-sum

    public int minPathSum(int[][] grid) {
        for (int i = 1;i<grid.length;++i){
            grid[i][0] += grid[i-1][0];
        }
        for (int i = 1;i<grid[0].length;++i){
            grid[0][i] += grid[0][i-1];
        }
        for (int i = 1;i<grid.length;++i){
            for (int j = 1;j<grid[0].length;++j){
                grid[i][j] +=Math.min(grid[i-1][j],grid[i][j-1]);
            }
        }
        return grid[grid.length-1][grid[0].length-1];
    }

//    给你一个字符串 s 和一个字符串列表 wordDict 作为字典。请你判断是否可以利用字典中出现的单词拼接出 s 。
//    注意：不要求字典中出现的单词全部都使用，并且字典中的单词可以重复使用。
//    示例 1：
//    输入: s = "leetcode", wordDict = ["leet", "code"]
//    输出: true
//    解释: 返回 true 因为 "leetcode" 可以由 "leet" 和 "code" 拼接成。
//    链接：https://leetcode-cn.com/problems/word-break

    public boolean wordBreak(String s, List<String> wordDict) {
        Set<String> wordDictSet = new HashSet(wordDict);
        boolean[] dp = new boolean[s.length() + 1];
        dp[0] = true;
        for (int i = 1; i <= s.length(); i++) {
            for (int j = 0; j < i; j++) {
                if (dp[j] && wordDictSet.contains(s.substring(j, i))) {
                    dp[i] = true;
                    break;
                }
            }
        }
        return dp[s.length()];
    }

//    给你一个整数数组 nums，请你找出数组中乘积最大的非空连续子数组（该子数组中至少包含一个数字），并返回该子数组所对应的乘积。
//    测试用例的答案是一个32-位 整数。
//    子数组 是数组的连续子序列。
//    示例 1:
//    输入: nums = [2,3,-2,4]
//    输出: 6
//    解释:子数组 [2,3] 有最大乘积 6。
//    链接：https://leetcode-cn.com/problems/maximum-product-subarray

    public int maxProduct(int[] nums) {
        int length=nums.length;
        int max[]=new int[length];
        int min[]=new int[length];
        max[0]=nums[0];
        min[0]=nums[0];
        for(int i=1;i<length;i++){
            max[i]=Math.max(Math.max(min[i-1]*nums[i],max[i-1]*nums[i]),nums[i]);
            min[i]=Math.min(Math.min(min[i-1]*nums[i],max[i-1]*nums[i]),nums[i]);
        }
        int maxans=-1000;
        for(int i=0;i<length;i++){
            if(max[i]>maxans)maxans=max[i];
        }
        return maxans;
    }

    @Test
    public void testMaxProduct(){
        maxProduct(new int[]{2,-5,-2,-4,3});
    }

//    你是一个专业的小偷，计划偷窃沿街的房屋。每间房内都藏有一定的现金，影响你偷窃的唯一制约因素就是相邻的房屋装有相互连通的防盗系统，
//    如果两间相邻的房屋在同一晚上被小偷闯入，系统会自动报警。
//    给定一个代表每个房屋存放金额的非负整数数组，计算你 不触动警报装置的情况下 ，一夜之内能够偷窃到的最高金额。
//    示例 1：
//    输入：[1,2,3,1]
//    输出：4
//    解释：偷窃 1 号房屋 (金额 = 1) ，然后偷窃 3 号房屋 (金额 = 3)。偷窃到的最高金额 = 1 + 3 = 4 。
//    链接：https://leetcode-cn.com/problems/house-robber

    public int rob(int[] nums) {
        int dp[]=new int[nums.length];
        if(nums.length==1){
            return nums[0];
        }
        if(nums.length==2){
            return Math.max(nums[0],nums[1]);
        }
        if(nums.length==3){
            return Math.max(nums[0]+nums[2],nums[1]);
        }
        dp[0]=nums[0];
        dp[1]=nums[1];
        dp[2]=nums[0]+nums[2];
        for(int i=3;i<nums.length;i++){
            dp[i]=Math.max(dp[i-3],dp[i-2])+nums[i];
        }
        return Math.max(dp[nums.length-1],dp[nums.length-2]);
    }

//    小偷又发现了一个新的可行窃的地区。这个地区只有一个入口，我们称之为root。
//    除了root之外，每栋房子有且只有一个“父“房子与之相连。一番侦察之后，聪明的小偷意识到“这个地方的所有房屋的排列类似于一棵二叉树”。
//    如果 两个直接相连的房子在同一天晚上被打劫 ，房屋将自动报警。
//    给定二叉树的root。返回在不触动警报的情况下，小偷能够盗取的最高金额。
//    示例 1:
//    输入: root = [3,2,3,null,3,null,1]
//    输出: 7
//    解释:小偷一晚能够盗取的最高金额 3 + 3 + 1 = 7
//    链接：https://leetcode-cn.com/problems/house-robber-iii

    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

    Map<TreeNode, Integer> f = new HashMap<TreeNode, Integer>();
    Map<TreeNode, Integer> g = new HashMap<TreeNode, Integer>();

    public int rob(TreeNode root) {
        dfs(root);
        return Math.max(f.getOrDefault(root, 0), g.getOrDefault(root, 0));
    }

    public void dfs(TreeNode node) {
        if (node == null) {
            return;
        }
        dfs(node.left);
        dfs(node.right);
        f.put(node, node.val + g.getOrDefault(node.left, 0) + g.getOrDefault(node.right, 0));
        g.put(node, Math.max(f.getOrDefault(node.left, 0), g.getOrDefault(node.left, 0)) +
                Math.max(f.getOrDefault(node.right, 0), g.getOrDefault(node.right, 0)));
    }


//    在一个由 '0' 和 '1' 组成的二维矩阵内，找到只包含 '1' 的最大正方形，并返回其面积。
//    示例 1：
//    输入：matrix = [["1","0","1","0","0"],["1","0","1","1","1"],["1","1","1","1","1"],["1","0","0","1","0"]]
//    输出：4
//    链接：https://leetcode-cn.com/problems/maximal-square

    public int maximalSquare(char[][] matrix) {
        int max = 0;
        for (int i = 0;i<matrix.length;++i){
            for (int j =0;j<matrix[0].length;++j){
                if (i>0&&j>0&&matrix[i][j] == '1'){
                    matrix[i][j]+=Math.min(Math.min(matrix[i-1][j]-'0',matrix[i][j-1]-'0'),matrix[i-1][j-1]-'0');
                }
                max = Math.max(max,matrix[i][j]-'0');
            }
        }
        return max*max;
    }

    @Test
    public void testMaximalSquare(){
        char[][] chars = {{'1', '0', '1', '0', '0'}, {'1', '0', '1', '1', '1'}, {'1', '1', '1', '1', '1'}, {'1', '0', '0', '1', '0'}};
        maximalSquare(chars);
    }

//    给你一个整数数组 nums ，找到其中最长严格递增子序列的长度。
//    子序列是由数组派生而来的序列，删除（或不删除）数组中的元素而不改变其余元素的顺序。例如，[3,6,2,7] 是数组 [0,3,1,6,2,2,7] 的子序列。
//    示例 1：
//    输入：nums = [10,9,2,5,3,7,101,18]
//    输出：4
//    解释：最长递增子序列是 [2,3,7,101]，因此长度为 4 。
//    链接：https://leetcode-cn.com/problems/longest-increasing-subsequence

    public int lengthOfLIS(int[] nums) {
        int []dp =new int[nums.length];
        int max = 1;
        Arrays.fill(dp,1);
        for (int i = 1;i<nums.length;++i){
            int index = i;
            for (int j = 0;j<i;j++){
                if (nums[j]<nums[i]&&dp[j]+1>=dp[index]+1){
                    index = j;
                    dp[i] = dp[index]+1;
                }
            }
            max = Math.max(max,dp[i]);
        }
        return max;
    }

    @Test
    public void testLengthOfLIS(){
        lengthOfLIS(new int[]{10,9,2,5,3,7,101,18});
    }
}
