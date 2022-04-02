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
}
