import org.junit.Test;

import java.util.Arrays;

public class BeiBao {

//    给你一个整数 n ，返回 和为 n 的完全平方数的最少数量 。
//    完全平方数 是一个整数，其值等于另一个整数的平方；换句话说，其值等于一个整数自乘的积。例如，1、4、9 和 16 都是完全平方数，而 3 和 11 不是。
//    示例1：
//    输入：n = 12
//    输出：3
//    解释：12 = 4 + 4 + 4
//    链接：https://leetcode-cn.com/problems/perfect-squares

    public int numSquares(int n) {
        int[] coins = new int[(int) Math.sqrt(n)];
        for (int i =0 ;i<(int) Math.sqrt(n);i++){
            coins[i] = (i+1)*(i+1);
        }
        int[] dp = new int[n + 1];
        for (int i = 0;i<=n;++i){
            dp[i] = i;
        }
        for (int coin : coins){
            for (int i = coin;i<=n;++i){
                dp[i] = Math.min(dp[i],dp[i-coin]+1);
            }
        }
        return dp[n];
    }

    @Test
    public void testNumSquares(){
        numSquares(12);
    }

//    硬币。给定数量不限的硬币，币值为25分、10分、5分和1分，编写代码计算n分有几种表示法。(结果可能会很大，你需要将结果模上1000000007)
//    示例1:
//    输入: n = 5
//    输出：2
//    解释: 有两种方式可以凑成总金额:
//            5=5
//            5=1+1+1+1+1
//    链接：https://leetcode-cn.com/problems/coin-lcci

    static final int MOD = 1000000007;
    int[] coins = {1, 5, 10, 25};

    public int waysToChange(int n) {
        int[] f = new int[n + 1];
        f[0] = 1;
        for (int c = 0; c < 4; ++c) {
            int coin = coins[c];
            for (int i = coin; i <= n; ++i) {
                //从前往后遍历，保证前面的状态可能已经选了这一件
                f[i] = (f[i] + f[i - coin]) % MOD;
            }
        }
        return f[n];
    }

    @Test
    public void testWaysToChange(){
        waysToChange(30);
    }

//    给你一个整数数组 coins ，表示不同面额的硬币；以及一个整数 amount ，表示总金额。
//    计算并返回可以凑成总金额所需的 最少的硬币个数 。如果没有任何一种硬币组合能组成总金额，返回-1 。
//    你可以认为每种硬币的数量是无限的。
//    示例1：
//    输入：coins = [1, 2, 5], amount = 11
//    输出：3
//    解释：11 = 5 + 5 + 1
//    示例 2：
//    链接：https://leetcode-cn.com/problems/coin-change

    public int coinChange(int[] coins, int amount) {
        int max = amount + 1;
        int[] dp = new int[amount + 1];
        Arrays.fill(dp, 9999999);
        dp[0] = 0;
        for (int coin:coins){
            for (int j = 1; j <= amount; j++) {
                if (coin <= j) {
                    dp[j] = Math.min(dp[j], dp[j - coin] + 1);
                }
            }
        }
        return dp[amount]>amount?-1:dp[amount];
    }

//    给你一个 只包含正整数 的 非空 数组nums 。
//    请你判断是否可以将这个数组分割成两个子集，使得两个子集的元素和相等。
//    示例 1：
//    输入：nums = [1,5,11,5]
//    输出：true
//    解释：数组可以分割成 [1, 5, 5] 和 [11] 。
//    链接：https://leetcode-cn.com/problems/partition-equal-subset-sum

    public boolean canPartition(int[] nums) {
        int target=0;
        for(int num:nums){
            target+=num;
        }
        if(target%2!=0)return false;
        target/=2;
        int dp[]=new int[target+1];
        dp[0]=1;
        for(int num:nums){
            for(int i=target;i>=num;i--){
                if(i-num>=0&&dp[i-num]==1)
                    dp[i]=1;
            }
        }
        if(dp[target]>0)return true;
        else return false;
    }

//    给你一个整数数组 nums 和一个整数 target 。
//    向数组中的每个整数前添加'+' 或 '-' ，然后串联起所有整数，可以构造一个 表达式 ：
//    例如，nums = [2, 1] ，可以在 2 之前添加 '+' ，在 1 之前添加 '-' ，然后串联起来得到表达式 "+2-1" 。
//    返回可以通过上述方法构造的、运算结果等于 target 的不同 表达式 的数目。
//    示例 1：
//    输入：nums = [1,1,1,1,1], target = 3
//    输出：5
//    解释：一共有 5 种方法让最终目标和为 3 。
//            -1 + 1 + 1 + 1 + 1 = 3
//            +1 - 1 + 1 + 1 + 1 = 3
//            +1 + 1 - 1 + 1 + 1 = 3
//            +1 + 1 + 1 - 1 + 1 = 3
//            +1 + 1 + 1 + 1 - 1 = 3
//    链接：https://leetcode-cn.com/problems/target-sum
//    1、转化为01背包问题
//    数组里都是非负整数，要想和为target，需要的负数和为-(sum-target)/2
//    2、解决01背包问题

    public int findTargetSumWays(int[] nums, int target) {
        int sum = 0;
        for (int num : nums) {
            sum += num;
        }
        int diff = sum - target;
        if (diff < 0 || diff % 2 != 0) {
            return 0;
        }
        int neg = diff / 2;
        int dp[]=new int[neg+1];
        dp[0]=1;
        for(int num:nums){
            for(int j=neg;j>=num;j--){
                //从后往前遍历，保证前面没选过这件（因为前面是上一轮商品的遍历，没有这件商品）
                dp[j]+=dp[j-num];
            }
        }
        return dp[neg];
    }


}
