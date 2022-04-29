import org.junit.Test;

import javax.sound.midi.Soundbank;
import java.net.Socket;
import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.List;

public class BitOperation {

    //    输出int的二进制数

    public StringBuilder get0B(int test) {
        int tem = 1;
        StringBuilder builder = new StringBuilder("");
        for (int i = 0; i < 32; ++i) {
            if ((test & tem) != 0) {
                builder.append(1);
            } else {
                builder.append(0);
            }
            tem <<= 1;
        }
        return builder.reverse();
    }

    @Test
    public void testInt() {
        System.out.println(get0B(0x00000000) + "->" + 0x00000000);
        System.out.println(get0B(0x7FFFFFFF) + "->" + 0x7FFFFFFF);
        System.out.println(get0B(0x80000000) + "->" + 0x80000000);
        System.out.println(get0B(0xFFFFFFFF) + "->" + 0xFFFFFFFF);
    }

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

//    编写一个函数，不用临时变量，直接交换numbers = [a, b]中a与b的值。
//    示例：
//    输入: numbers = [1,2]
//    输出: [2,1]
//    https://leetcode-cn.com/problems/swap-numbers-lcci/

    public int[] swapNumbers(int[] numbers) {
        numbers[0] = numbers[0] ^ numbers[1];
        numbers[1] = numbers[0] ^ numbers[1];
        numbers[0] = numbers[0] ^ numbers[1];
        return numbers;
    }

//    给你一个整数 n ，对于0 <= i <= n 中的每个 i ，计算其二进制表示中 1 的个数 ，返回一个长度为 n + 1 的数组 ans 作为答案。
//    示例 1：
//    输入：n = 2
//    输出：[0,1,1]
//    解释：
//            0 --> 0
//            1 --> 1
//            2 --> 10
//    链接：https://leetcode-cn.com/problems/counting-bits

    public int[] countBits(int n) {
        int[] bits = new int[n + 1];
        bits[0] = 0;
        int highBit = 1;
        for (int i = 1; i <= n; i++) {
            if ((i & (i - 1)) == 0) highBit = i;
            bits[i] = bits[i - highBit] + 1;
        }
        return bits;
    }

//    给定一个非空整数数组，除了某个元素只出现一次以外，其余每个元素均出现两次。找出那个只出现了一次的元素。
//    说明：
//    你的算法应该具有线性时间复杂度。 你可以不使用额外空间来实现吗？
//    示例 1:
//    输入: [2,2,1]
//    输出: 1
//    链接：https://leetcode-cn.com/problems/single-number

    public int singleNumber(int[] nums) {
        int ans = 0;
        for (int i : nums) {
            ans ^= i;
        }
        return ans;
    }

//    给定一个包含n + 1 个整数的数组nums ，其数字都在[1, n]范围内（包括 1 和 n），可知至少存在一个重复的整数。
//    假设 nums 只有 一个重复的整数 ，返回这个重复的数 。
//    你设计的解决方案必须 不修改 数组 nums 且只用常量级 O(1) 的额外空间。
//    示例 1：
//    输入：nums = [1,3,4,2,2]
//    输出：2
//    链接：https://leetcode-cn.com/problems/find-the-duplicate-number

    public int findDuplicate(int[] nums) {
        int slow = 0, fast = 0;
        do {
            slow = nums[slow];
            fast = nums[nums[fast]];
        } while (slow != fast);
        slow = 0;
        while (slow != fast) {
            slow = nums[slow];
            fast = nums[fast];
        }
        return slow;
    }

//    两个整数之间的 汉明距离 指的是这两个数字对应二进制位不同的位置的数目。
//    给你两个整数 x 和 y，计算并返回它们之间的汉明距离。
//    示例 1：
//    输入：x = 1, y = 4
//    输出：2
//    解释：
//            1   (0 0 0 1)
//            4   (0 1 0 0)
//            ↑   ↑
//    上面的箭头指出了对应二进制位不同的位置。
//    链接：https://leetcode-cn.com/problems/hamming-distance

    public int hammingDistance(int x, int y) {
        int ans = 0;
        while (x != 0 || y != 0) {
            if ((x & 1) != (y & 1)) {
                ans++;
            }
            x = x >> 1;
            y = y >> 1;
        }
        return ans;
    }

//    编写一个函数，输入是一个无符号整数（以二进制串的形式），返回其二进制表达式中数字位数为 '1' 的个数（也被称为汉明重量).）。
//    提示：
//    请注意，在某些语言（如 Java）中，没有无符号整数类型。在这种情况下，输入和输出都将被指定为有符号整数类型，并且不应影响您的实现，因为无论整数是有符号的还是无符号的，其内部的二进制表示形式都是相同的。
//    在 Java 中，编译器使用 二进制补码 记法来表示有符号整数。因此，在上面的示例 3中，输入表示有符号整数 -3。
//    链接：https://leetcode-cn.com/problems/er-jin-zhi-zhong-1de-ge-shu-lcof

    public int hammingWeight(int n) {
        int ans = 0;
        while (n != 0) {
            ans += (n & 1);
            n >>>= 1;//因为是无符号数
        }
        return ans;
    }

//    一个整型数组 nums 里除两个数字之外，其他数字都出现了两次。请写程序找出这两个只出现一次的数字。要求时间复杂度是O(n)，空间复杂度是O(1)。
//    示例 1：
//    输入：nums = [4,1,4,6]
//    输出：[1,6] 或 [6,1]
//    链接：https://leetcode-cn.com/problems/shu-zu-zhong-shu-zi-chu-xian-de-ci-shu-lcof

    public int[] singleNumbers(int[] nums) {
        int ret = 0;
        for (int n : nums) {
            ret ^= n;
        }
        int div = 1;
        while ((div & ret) == 0) {
            div <<= 1;
        }
        int a = 0, b = 0;
        for (int n : nums) {
            if ((div & n) != 0) {
                a ^= n;
            } else {
                b ^= n;
            }
        }
        return new int[]{a, b};
    }

//    在一个数组 nums 中除一个数字只出现一次之外，其他数字都出现了三次。请找出那个只出现一次的数字。
//    示例 1：
//    输入：nums = [3,4,3,3]
//    输出：4
//    链接：https://leetcode-cn.com/problems/shu-zu-zhong-shu-zi-chu-xian-de-ci-shu-ii-lcof

    public int singleNumber2(int[] nums) {
        int counts[] = new int[32];
        for (int num : nums) {
            for (int i = 0; num != 0; i++) {
                counts[i] += num & 1;
                num >>>= 1;
            }
        }
        int ans = 0;
        int tem = 1;
        for (int i = 0; i < 32; i++) {
            ans += counts[i] % 3 * tem;
            tem *= 2;
        }
        return ans;
    }

//    求 1+2+...+n ，要求不能使用乘除法、for、while、if、else、switch、case等关键字及条件判断语句（A?B:C）。
//    示例 1：
//    输入: n = 3
//    输出:6
//    链接：https://leetcode-cn.com/problems/qiu-12n-lcof

    public int sumNums(int n) {
        boolean flag = n > 0 && (n += sumNums(n - 1)) > 0;
        return n;
    }

//    写一个函数，求两个整数之和，要求在函数体内不得使用 “+”、“-”、“*”、“/” 四则运算符号。
//    示例:
//    输入: a = 1, b = 1
//    输出: 2
//    链接：https://leetcode-cn.com/problems/bu-yong-jia-jian-cheng-chu-zuo-jia-fa-lcof

    public int add(int a, int b) {
        while (true) {
            int c = (b & a) << 1;
            b = a ^ b;
            if ((c & b) == 0) return b | c;
            a = b;
            b = c;
        }
    }

//    给定两个整型数字 N 与 M，以及表示比特位置的 i 与 j（i <= j，且从 0 位开始计算）。
//    编写一种方法，使 M 对应的二进制数字插入 N 对应的二进制数字的第 i ~ j 位区域，不足之处用 0 补齐。具体插入过程如图所示。
//    题目保证从 i 位到 j 位足以容纳 M， 例如： M = 10011，则 i～j 区域至少可容纳 5 位。
//    示例1:
//    输入：N = 1024(10000000000), M = 19(10011), i = 2, j = 6
//    输出：N = 1100(10001001100)
//    链接：https://leetcode-cn.com/problems/insert-into-bits-lcci

    public int insertBits(int N, int M, int i, int j) {
        //left in (,j+1];middle in [i,j];right in [i-1,0];
        int left = N >> j >> 1; //把最左边的部分调整好了，即抛弃了替换部分和低位部分
        left = left << j << 1;  //因此最后要进行或运算，所以把他再移到原来的高位上。
        int middle = M << i;  //替换N的j<-----i位，那么只需要将M左移i位即可
        int right = N & ((1 << i) - 1);//只需要N的低位，将高位置零,(1<<2)-1 = (11)2
        return left | middle | right;
    }

    @Test
    public void testInsertBits() {
        System.out.println(insertBits(1024, 19, 2, 6));
        ;
    }

//    二进制数转字符串。给定一个介于0和1之间的实数（如0.72），类型为double，打印它的二进制表达式。如果该数字无法精确地用32位以内的二进制表示，则打印“ERROR”。
//    示例1:
//    输入：0.625
//    输出："0.101"
//    示例2:
//    输入：0.1
//    输出："ERROR"
//    提示：0.1无法被二进制准确表示
//    链接：https://leetcode-cn.com/problems/bianry-number-to-string-lcci

    public String printBin(double num) {
        StringBuilder ans = new StringBuilder("0.");
        while (num != 0D && ans.length() <= 32) {
            num *= 2;
            if (num >= 1) {
                ans.append(1);
                num -= 1;
            } else ans.append(0);
        }
        return ans.length() > 32 ? "ERROR" : ans.toString();
    }

    @Test
    public void testPrintBin() {
        printBin(0.625d);
    }

//    下一个数。给定一个正整数，找出与其二进制表达式中1的个数相同且大小最接近的那两个数（一个略大，一个略小）。
//    示例1:
//    输入：num = 2（或者0b10）
//    输出：[4, 1] 或者（[0b100, 0b1]）
//    链接：https://leetcode-cn.com/problems/closed-number-lcci
//    比 num 大的数：从右往左，找到第一个 01 位置，然后把 01 转为 10，右侧剩下的 1 移到右侧的低位，右侧剩下的位清0。
//    比 num 小的数：从右往左，找到第一个 10 位置，然后把 10 转为 01，右侧剩下的 1 移到右侧的高位，右侧剩下的位置0。
//    常规上，低位在右侧，bitset 注意方向相反

    public int[] findClosedNumbers(int num) {
        int count = 0;// 记录1的数量
        int big = -1, small = -1;
        int numTmp = num;
        for (int i = 0; i < 30; i++) {
            // 遇到01，把他变为10，并且把右侧的1放到最右边
            if ((num & (1 << i)) != 0 && (num & (1 << i + 1)) == 0) {
                numTmp += (1 << i + 1);
                numTmp -= (1 << i);
                for (int j = 0; j < count; j++) {
                    numTmp += (1 << j);
                }
                big = numTmp;
                break;
            }
            if ((num & (1 << i)) != 0) count++;
            numTmp &= (~(1 << i));
        }
        numTmp = num;
        count = 0;
        for (int i = 0; i < 30; i++) {
            // 遇到10，把他变为01，并且把右侧的1放到最左边
            if ((num & (1 << i)) == 0 && (num & (1 << i + 1)) != 0) {
                numTmp -= (1 << i + 1);
                numTmp += (1 << i);
                for (int j = 0; j < count; j++) {
                    numTmp += (1 << i - j - 1);
                }
                small = numTmp;
                break;
            }
            if ((num & (1 << i)) != 0) count++;
            numTmp &= (~(1 << i));
        }
        return new int[]{big, small};
    }


    @Test
    public void testFindClosedNumbers() {
        int[] closedNumbers = findClosedNumbers(1);
        System.out.println(closedNumbers[0] + closedNumbers[1]);
    }

//    整数转换。编写一个函数，确定需要改变几个位才能将整数A转成整数B。
//    示例1:
//    输入：A = 29 （或者0b11101）, B = 15（或者0b01111）
//    输出：2
//    示例2:
//    输入：A = 1，B = 2
//    输出：2
//    链接：https://leetcode-cn.com/problems/convert-integer-lcci

    public int convertInteger(int A, int B) {
        int C = A ^ B, ans = 0;
        while (C != 0) {
            if ((C & 1) == 1) {
                ans++;
            }
            C >>>= 1;
        }
        return ans;
    }

    @Test
    public void testConvertInteger() {
        convertInteger(826966453, -729934991);
    }

//    配对交换。编写程序，交换某个整数的奇数位和偶数位，尽量使用较少的指令（也就是说，位0与位1交换，位2与位3交换，以此类推）。
//    示例1:
//    输入：num = 2（或者0b10）
//    输出 1 (或者 0b01)
//    示例2:
//    输入：num = 3
//    链接：https://leetcode-cn.com/problems/exchange-lcci

    public int exchangeBits(int num) {
        int odd = num & 0x55555555;
        int even = num & 0xaaaaaaaa;
        odd = odd << 1;
        even = even >>> 1;
        return odd | even;
    }

//    已知一个由像素点组成的单色屏幕，每行均有 w 个像素点，所有像素点初始为 0，左上角位置为 (0,0)。
//    现将每行的像素点按照「每 32 个像素点」为一组存放在一个 int 中，再依次存入长度为 length 的一维数组中。
//    我们将在屏幕上绘制一条从点 (x1,y) 到点 (x2,y) 的直线（即像素点修改为 1），请返回绘制过后的数组。
//    注意：
//    用例保证屏幕宽度 w 可被 32 整除（即一个 int 不会分布在两行上）
//    示例1:
//    输入：length = 1, w = 32, x1 = 30, x2 = 31, y = 0
//    输出：[3]
//    解释：在第 0 行的第 30 位到第 31 位画一条直线，屏幕二进制形式表示为 [00000000000000000000000000000011]，因此返回 [3]
//    链接：https://leetcode-cn.com/problems/draw-line-lcci

//    public int[] drawLine(int length, int w, int x1, int x2, int y) {
//
//    }
}
