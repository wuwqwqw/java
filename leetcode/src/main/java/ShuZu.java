import org.junit.Test;

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
        for (int i = 0;i<board.length;++i){
            int oNum = 0,xNum = 0;
            for (int j = 0;j<board[i].length();j++){
                if (board[i].charAt(j)=='X'){
                    xNum++;
                }else if (board[i].charAt(j)=='O'){
                    oNum++;
                }else {
                    isPending = true;
                }
            }
            if (xNum==board.length||oNum==board.length){
                return xNum==board.length?"X":"O";
            }
        }
        int oNum = 0,xNum = 0;
        for (int i = 0;i<board.length;++i){

            if (board[i].charAt(i)=='X'){
                xNum++;
            }else if (board[i].charAt(i)=='O'){
                oNum++;
            }else {
                isPending = true;
            }
            if (xNum==board.length||oNum==board.length){
                return xNum==board.length?"X":"O";
            }
        }
        for (int i = 0;i<board.length;++i){
            oNum = 0;
            xNum = 0;
            for (int j = 0;j<board[i].length();j++){
                if (board[j].charAt(i)=='X'){
                    xNum++;
                }else if (board[j].charAt(i)=='O'){
                    oNum++;
                }else {
                    isPending = true;
                }
            }
            if (xNum==board.length||oNum==board.length){
                return xNum==board.length?"X":"O";
            }
        }
        oNum = 0;
        xNum = 0;
        for (int i = 0;i<board.length;++i){
            if (board[i].charAt(board.length-1-i)=='X'){
                xNum++;
            }else if (board[i].charAt(board.length-1-i)=='O'){
                oNum++;
            }else {
                isPending = true;
            }
            if (xNum==board.length||oNum==board.length){
                return xNum==board.length?"X":"O";
            }
        }
        return isPending?"Pending":"Draw";
    }

    @Test
    public void test(){
        tictactoe(new String[]{"O X", " XO", "X O"});
    }


}
