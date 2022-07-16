import org.junit.Test;

import java.util.*;

public class ShuZu {

//    给你一幅由 N × N 矩阵表示的图像，其中每个像素的大小为 4 字节。请你设计一种算法，将图像旋转 90 度。
//    不占用额外内存空间能否做到？
//    示例 1:
//    给定 matrix =
//[
//        [1,2,3],
//        [4,5,6],
//        [7,8,9]
//        ],
//    原地旋转输入矩阵，使其变为:
//            [
//            [7,4,1],
//            [8,5,2],
//            [9,6,3]
//            ]
//    链接：https://leetcode-cn.com/problems/rotate-matrix-lcci

    public void rotate(int[][] matrix) {
        int length = matrix.length;
        int tem = 0;
        if (length % 2 == 0) {
            for (int i = 0; i < length / 2; ++i) {
                for (int j = 0; j < length / 2; ++j) {
                    extracted(matrix, length, i, j);
                }
            }
        } else {
            for (int i = 0; i < length / 2; ++i) {
                for (int j = 0; j <= length / 2; ++j) {
                    extracted(matrix, length, i, j);
                }
            }
        }
    }

    private void extracted(int[][] matrix, int length, int i, int j) {
        int tem1 = matrix[i][j];
        int tem2 = matrix[length - 1 - j][i];
        int tem3 = matrix[length - 1 - i][length - j - 1];
        int tem4 = matrix[j][length - 1 - i];
        matrix[i][j] = tem2;
        matrix[length - 1 - j][i] = tem3;
        matrix[length - 1 - i][length - j - 1] = tem4;
        matrix[j][length - 1 - i] = tem1;
    }

//    编写一种算法，若M × N矩阵中某个元素为0，则将其所在的行与列清零。
//    示例 1：
//    输入：
//            [
//            [1,1,1],
//            [1,0,1],
//            [1,1,1]
//            ]
//    输出：
//            [
//            [1,0,1],
//            [0,0,0],
//            [1,0,1]
//            ]

    public void setZeroes(int[][] matrix) {
        int m = matrix.length, n = matrix[0].length;
        boolean[] row = new boolean[m];
        boolean[] col = new boolean[n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (matrix[i][j] == 0) {
                    row[i] = col[j] = true;
                }
            }
        }
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (row[i] || col[j]) {
                    matrix[i][j] = 0;
                }
            }
        }
    }

//    设计一个算法，判断玩家是否赢了井字游戏。输入是一个 N x N 的数组棋盘，由字符" "，"X"和"O"组成，其中字符" "代表一个空位。
//    以下是井字游戏的规则：
//    玩家轮流将字符放入空位（" "）中。
//    第一个玩家总是放字符"O"，且第二个玩家总是放字符"X"。
//    "X"和"O"只允许放置在空位中，不允许对已放有字符的位置进行填充。
//    当有N个相同（且非空）的字符填充任何行、列或对角线时，游戏结束，对应该字符的玩家获胜。
//    当所有位置非空时，也算为游戏结束。
//    如果游戏结束，玩家不允许再放置字符。
//    如果游戏存在获胜者，就返回该游戏的获胜者使用的字符（"X"或"O"）；如果游戏以平局结束，则返回 "Draw"；如果仍会有行动（游戏未结束），则返回 "Pending"。
//    示例 1：
//    输入： board = ["O X"," XO","X O"]
//    输出： "X"
//    链接：https://leetcode-cn.com/problems/tic-tac-toe-lcci

    boolean isPending = false;

    public String tictactoe(String[] board) {
        for (int i = 0; i < board.length; ++i) {
            int oNum = 0, xNum = 0;
            for (int j = 0; j < board[i].length(); j++) {
                if (board[i].charAt(j) == 'X') {
                    xNum++;
                } else if (board[i].charAt(j) == 'O') {
                    oNum++;
                } else {
                    isPending = true;
                }
            }
            if (xNum == board.length || oNum == board.length) {
                return xNum == board.length ? "X" : "O";
            }
        }
        int oNum = 0, xNum = 0;
        for (int i = 0; i < board.length; ++i) {

            if (board[i].charAt(i) == 'X') {
                xNum++;
            } else if (board[i].charAt(i) == 'O') {
                oNum++;
            } else {
                isPending = true;
            }
            if (xNum == board.length || oNum == board.length) {
                return xNum == board.length ? "X" : "O";
            }
        }
        for (int i = 0; i < board.length; ++i) {
            oNum = 0;
            xNum = 0;
            for (int j = 0; j < board[i].length(); j++) {
                if (board[j].charAt(i) == 'X') {
                    xNum++;
                } else if (board[j].charAt(i) == 'O') {
                    oNum++;
                } else {
                    isPending = true;
                }
            }
            if (xNum == board.length || oNum == board.length) {
                return xNum == board.length ? "X" : "O";
            }
        }
        oNum = 0;
        xNum = 0;
        for (int i = 0; i < board.length; ++i) {
            if (board[i].charAt(board.length - 1 - i) == 'X') {
                xNum++;
            } else if (board[i].charAt(board.length - 1 - i) == 'O') {
                oNum++;
            } else {
                isPending = true;
            }
            if (xNum == board.length || oNum == board.length) {
                return xNum == board.length ? "X" : "O";
            }
        }
        return isPending ? "Pending" : "Draw";
    }

    @Test
    public void test() {
        tictactoe(new String[]{"O X", " XO", "X O"});
    }

//    在一个 n * m 的二维数组中，每一行都按照从左到右递增的顺序排序，每一列都按照从上到下递增的顺序排序。
//    请完成一个高效的函数，输入这样的一个二维数组和一个整数，判断数组中是否含有该整数。
//    示例:
//    现有矩阵 matrix 如下：
//            [
//            [1,   4,  7, 11, 15],
//            [2,   5,  8, 12, 19],
//            [3,   6,  9, 16, 22],
//            [10, 13, 14, 17, 24],
//            [18, 21, 23, 26, 30]
//            ]
//    链接：https://leetcode-cn.com/problems/er-wei-shu-zu-zhong-de-cha-zhao-lcof

    public boolean findNumberIn2DArray(int[][] matrix, int target) {
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
            return false;
        }
        int rows = matrix.length, columns = matrix[0].length;
        int row = 0, column = columns - 1;
        while (row < rows && column >= 0) {
            int num = matrix[row][column];
            if (num == target) {
                return true;
            } else if (num > target) {
                column--;
            } else {
                row++;
            }
        }
        return false;
    }

//    给定 N 个人的出生年份和死亡年份，第 i 个人的出生年份为 birth[i]，死亡年份为 death[i]，实现一个方法以计算生存人数最多的年份。
//    你可以假设所有人都出生于 1900 年至 2000 年（含 1900 和 2000 ）之间。如果一个人在某一年的任意时期处于生存状态，
//    那么他应该被纳入那一年的统计中。例如，生于 1908 年、死于 1909 年的人应当被列入 1908 年和 1909 年的计数。
//    如果有多个年份生存人数相同且均为最大值，输出其中最小的年份。
//    示例：
//    输入：
//    birth = {1900, 1901, 1950}
//    death = {1948, 1951, 2000}
//    输出： 1901
//    链接：https://leetcode-cn.com/problems/living-people-lcci

    public int maxAliveYear(int[] birth, int[] death) {
        int[] count = new int[102];
        for (int i = 0; i < birth.length; ++i) {
            count[birth[i] - 1900]++;
            count[death[i] - 1900 + 1]--;
        }
        int max = -1, maxYear = 1900, tem = 0;
        for (int i = 0; i < count.length; ++i) {
            tem += count[i];
            if (max < tem) {
                max = tem;
                maxYear = i + 1900;
            }
        }
        return maxYear;
    }

    @Test
    public void testMaxAliveYear() {
        int[] ints = {1972, 1908, 1915, 1957, 1960, 1948, 1912, 1903, 1949, 1977, 1900, 1957, 1934, 1929, 1913, 1902, 1903, 1901};
        int[] ints2 = {1997, 1932, 1963, 1997, 1983, 2000, 1926, 1962, 1955, 1997, 1998, 1989, 1992, 1975, 1940, 1903, 1983, 1969};

        maxAliveYear(ints, ints2);
    }

//    给定两个整数数组，请交换一对数值（每个数组中取一个数值），使得两个数组所有元素的和相等。
//    返回一个数组，第一个元素是第一个数组中要交换的元素，第二个元素是第二个数组中要交换的元素。
//    若有多个答案，返回任意一个均可。若无满足条件的数值，返回空数组。
//    输入: array1 = [4, 1, 2, 1, 1, 2], array2 = [3, 6, 3, 3]
//    输出: [1, 3]
//    链接：https://leetcode-cn.com/problems/sum-swap-lcci

    public int[] findSwapValues(int[] array1, int[] array2) {
        int minus = getSum(array1) - getSum(array2);
        if (Math.abs(minus) % 2 == 1) {
            return new int[]{};
        }
        minus = minus / 2;
        Arrays.sort(array1);
        Arrays.sort(array2);
        for (int i = 0, j = 0; i < array1.length && j < array2.length; ) {
            if (array1[i] - array2[j] == minus) {
                return new int[]{array1[i], array2[j]};
            } else if (array1[i] - array2[j] < minus) {
                i++;
            } else {
                j++;
            }
        }
        return new int[]{};
    }

    public int getSum(int[] array) {
        int sum = 0;
        for (int i : array) {
            sum += i;
        }
        return sum;
    }

    @Test
    public void testFindSwapValues() {
        int[] ints = {519, 886, 282, 382, 662, 4718, 258, 719, 494, 795};
        int[] ints1 = {52, 20, 78, 50, 38, 96, 81, 20};
        findSwapValues(ints, ints1);
    }

//    设计一个算法，找出数组中两数之和为指定值的所有整数对。一个数只能属于一个数对。
//    示例 1:
//    输入: nums = [5,6,5], target = 11
//    输出: [[5,6]]
//    示例 2:
//    输入: nums = [5,6,5,6], target = 11
//    输出: [[5,6],[5,6]]
//    链接：https://leetcode-cn.com/problems/pairs-with-sum-lcci

    public List<List<Integer>> pairSums(int[] nums, int target) {
        List<List<Integer>> lists = new ArrayList<>();
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int num : nums) {
            map.put(num, map.getOrDefault(num, 0) + 1);
        }
        for (int key : nums) {
            int minus = target - key;
            if (minus == key && map.get(minus) < 2) {
                continue;
            }
            if (map.containsKey(minus) && map.get(minus) != 0 && map.get(key) != 0) {
                ArrayList<Integer> list = new ArrayList<>();
                list.add(minus);
                list.add(key);
                lists.add(list);
                map.put(key, map.get(key) - 1);
                map.put(minus, map.get(minus) - 1);
            }
        }
        return lists;
    }

    @Test
    public void testPairSums() {
        pairSums(new int[]{1, 2, 0, 0, 2, 1, 0, 2, 2, 3}, 2);
    }

//    给定两个整数数组a和b，计算具有最小差绝对值的一对数值（每个数组中取一个值），并返回该对数值的差
//    输入：{1, 3, 15, 11, 2}, {23, 127, 235, 19, 8}
//    输出：3，即数值对(11, 8)
//    链接：https://leetcode-cn.com/problems/smallest-difference-lcci

    public int smallestDifference(int[] a, int[] b) {
        int[][] ints = new int[a.length + b.length][2];
        for (int i = 0;i<a.length;++i){
            ints[i][0] = a[i];
            ints[i][1] = 0;
        }
        for (int j = 0;j<b.length;++j){
            ints[a.length+j][0] = b[j];
            ints[a.length+j][1] = 1;
        }
        Arrays.sort(ints,new Comparator<int[]>(){
            @Override
            public int compare(int[]a, int[]b){
                return a[0] - b[0];
            }
        });
        int min = Integer.MAX_VALUE;
        for (int i = 0;i<ints.length-1;++i){
            if (ints[i+1][0]-ints[i][0]<min&&ints[i+1][1]!=ints[i][1]){
                min = ints[i+1][0]-ints[i][0];
            }
        }
        return min;
    }

    public long maxWater (int[] arr) {
        long ans = 0;
        int left = 0;
        for (int i = 1;i<arr.length;i++){
            if (arr[i]>=arr[left]){
                if(left+1 == i){
                    left ++;
                    continue;
                }
                for (int j = left+1;j<i;++j){
                    ans+=arr[left]-arr[j];
                }
                left = i;
            }
        }
        if (left!=arr.length-1){
            int[] ints = new int[arr.length - left];
            for (int i = arr.length-1;i>=left;i--){
                ints[arr.length-1-i] = arr[i];
            }
            return ans+maxWater(ints);
        }else {
            return ans;
        }
    }

    @Test
    public void testMaxWater(){
        long l = maxWater(new int[]{5,5,1,7,1,1,5,2,7,6});
        System.out.printf(String.valueOf(l));
    }
}
