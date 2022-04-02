public class BitOperation {

//    递归乘法。 写一个递归函数，不使用 * 运算符， 实现两个正整数的相乘。可以使用加号、减号、位移，但要吝啬一些。
//    示例1:
//    输入：A = 1, B = 10
//    输出：10
//    链接：https://leetcode-cn.com/problems/recursive-mulitply-lcci

    public int multiply(int A, int B) {
        if (A > B)
            return multiply(B, A);
        if (A == 1)
            return B;
        if (A % 2 == 0)
            return multiply(A >> 1, B) << 1;
        else
            return (multiply(A >> 1, B) << 1) + B;
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


}