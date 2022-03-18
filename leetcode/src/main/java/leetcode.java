import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import sun.reflect.generics.tree.Tree;

import java.util.*;

@Slf4j
public class leetcode {
    public static void main(String[] args) {

    }

    public boolean isUnique(String astr) {
        HashSet<Character> characters = new HashSet<>();
        for (int i = 0; i < astr.length(); ++i) {
            if (characters.contains(astr.charAt(i))) {
                return false;
            } else {
                characters.add(astr.charAt(i));
            }
        }
        return true;
    }

    public boolean CheckPermutation(String s1, String s2) {
        HashMap<Character, Integer> map1 = new HashMap<>();
        HashMap<Character, Integer> map2 = new HashMap<>();
        for (int i = 0; i < s1.length(); i++) {
            map1.put(s1.charAt(i), map1.getOrDefault(s1.charAt(i), 0) + 1);
        }
        for (int i = 0; i < s2.length(); i++) {
            map2.put(s2.charAt(i), map2.getOrDefault(s2.charAt(i), 0) + 1);
        }
        return map1.equals(map2);
    }

    public String compressString(String S) {
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < S.length(); i++) {
            int j = i + 1;
            while (j < S.length() && S.charAt(j) == S.charAt(i)) {
                j++;
            }
            stringBuilder.append(S.charAt(i));
            stringBuilder.append(j - i);
            i = j - 1;
        }
        if (stringBuilder.length() < S.length()) {
            return stringBuilder.toString();
        } else {
            return S;
        }
    }

    public boolean isFlipedString(String s1, String s2) {
        for (int i = 0; i < s2.length(); i++) {
            if (s2.charAt(i) == s1.charAt(0)) {
                StringBuilder stringBuilder = new StringBuilder();
                stringBuilder.append(s2, i, s2.length());
                stringBuilder.append(s2, 0, i);
                if (stringBuilder.toString().equals(s1)) {
                    return true;
                }
            }
        }
        return false;
    }

    class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
            next = null;
        }
    }

    public class Solution {
        public ListNode detectCycle(ListNode head) {
            ListNode fast = head;
            ListNode slow = head;
            while (fast.next != null && fast.next.next != null) {
                fast = fast.next.next;
                slow = slow.next;
                if (slow == fast) {
                    fast = head;
                    while (true) {
                        if (fast == slow) {
                            return slow;
                        }
                        fast = fast.next;
                        slow = slow.next;
                    }
                }
            }
            return null;
        }
    }

//    输入：n = 3, graph = [[0, 1], [0, 2], [1, 2], [1, 2]], start = 0, target = 2
//    输出：true

    public boolean findWhetherExistsPath(int n, int[][] graph, int start, int target) {
        HashMap<Integer, List<Integer>> map = new HashMap<>();
        for (int i = 0; i < graph.length; i++) {
            if (!map.containsKey(graph[i][0])) {
                map.put(graph[i][0], new ArrayList<>());
            }
            map.get(graph[i][0]).add(graph[i][1]);
        }
        boolean vis[] = new boolean[n];
        Queue<Integer> queue = new LinkedList<>();
        queue.offer(start);
        while (!queue.isEmpty()) {
            Integer poll = queue.poll();
            List<Integer> integers = map.get(poll);
            if (integers.isEmpty()) {
                continue;
            }
            for (Integer i : integers) {
                if (!vis[i]) {
                    if (target == i) {
                        return true;
                    } else {
                        vis[i] = true;
                        queue.offer(i);
                    }
                }
            }
        }
        return false;
    }

//    URL化。编写一种方法，将字符串中的空格全部替换为%20。
//    假定该字符串尾部有足够的空间存放新增字符，并且知道字符串的“真实”长度。
//    （注：用Java实现的话，请使用字符数组实现，以便直接在数组上操作。）
//    输入："Mr John Smith    ", 13
//    输出："Mr%20John%20Smith"
//    https://leetcode-cn.com/problems/string-to-url-lcci/

    public String replaceSpaces(String S, int length) {
        //先把字符串转化为字符数组
        char[] chars = S.toCharArray();
        int index = chars.length - 1;
        for (int i = length - 1; i >= 0; i--) {
            //如果遇到空格就把他转化为"%20"
            if (chars[i] == ' ') {
                chars[index--] = '0';
                chars[index--] = '2';
                chars[index--] = '%';
            } else {
                chars[index--] = chars[i];
            }
        }
        return new String(chars, index + 1, chars.length - index - 1);
    }

//    给定一个字符串，编写一个函数判定其是否为某个回文串的排列之一。
//    回文串是指正反两个方向都一样的单词或短语。排列是指字母的重新排列。
//    回文串不一定是字典当中的单词。
//    输入："tactcoa"
//    输出：true（排列有"tacocat"、"atcocta"，等等）
//    链接：https://leetcode-cn.com/problems/palindrome-permutation-lcci


    public boolean canPermutePalindrome(String s) {
        Set<Character> set = new HashSet<>();
        for (int i = 0; i < s.length(); ++i) {
            if (set.contains(s.charAt(i))) {
                set.remove(s.charAt(i));
            } else {
                set.add(s.charAt(i));
            }
        }
        return set.size() <= 1;
    }


//    字符串有三种编辑操作:插入一个字符、删除一个字符或者替换一个字符。 给定两个字符串，编写一个函数判定它们是否只需要一次(或者零次)编辑。
//    输入:
//    first = "pale"
//    second = "ple"
//    输出: True
//    链接：https://leetcode-cn.com/problems/one-away-lcci

    public boolean oneEditAway(String first, String second) {
        if (Math.abs(first.length() - second.length()) > 1) {
            return false;
        }
        int min = Math.min(first.length(), second.length());
        int i = 0;
        for (; i < min; ++i) {
            if (first.charAt(i) != second.charAt(i)) {
                break;
            }
        }
        int firstIndex = i + 1;
        int secondIndex = i + 1;
        if (first.length() > second.length()) {
            secondIndex--;
        } else if (first.length() < second.length()) {
            firstIndex--;
        }
        for (; firstIndex < first.length(); ++firstIndex, ++secondIndex) {
            if (first.charAt(firstIndex) != second.charAt(secondIndex)) {
                return false;
            }
        }
        return true;
    }

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

//    实现一个函数，检查一棵二叉树是否为二叉搜索树。
//    示例1:
//    输入:
//            2
//            / \
//            1   3
//    输出: true
//    示例2:
//    输入:
//            5
//            / \
//            1   4
//                / \
//               3   6
//    输出: false
//    解释: 输入为: [5,1,4,null,null,3,6]。
//                根节点的值为 5 ，但是其右子节点值为 4 。
//    链接：https://leetcode-cn.com/problems/legal-binary-search-tree-lcci


    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

    public Long lastValue = Long.MIN_VALUE;
    Boolean ans = Boolean.TRUE;

    public boolean isValidBST(TreeNode root) {
        if(root==null){
            return true;
        }
        mid(root);
        return ans;
    }

    public void mid(TreeNode root){
        if (null!=root.left){
            mid(root.left);
        }
        if (!ans|| (long) root.val <=lastValue){
            ans = false;
            return;
        }else{
            lastValue = (long) root.val;
        }
        if (root.right!=null){
            mid(root.right);
        }
    }

//    设计并实现一个算法，找出二叉树中某两个节点的第一个共同祖先。不得将其他的节点存储在另外的数据结构中。注意：这不一定是二叉搜索树。
//    例如，给定如下二叉树: root = [3,5,1,6,2,0,8,null,null,7,4]
//
//            3
//            / \
//            5   1
//            / \ / \
//            6  2 0  8
//            / \
//            7   4
//    示例 1:
//    输入: root = [3,5,1,6,2,0,8,null,null,7,4], p = 5, q = 1
//    输出: 3
//    解释: 节点 5 和节点 1 的最近公共祖先是节点 3。
//    链接：https://leetcode-cn.com/problems/first-common-ancestor-lcci


    List<TreeNode> listP = new ArrayList<>();
    List<TreeNode> listQ = new ArrayList<>();

    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        front(root,p,listP);
        front(root,q,listQ);
        int min = Math.min(listP.size(), listQ.size());
        for (int index = 0;index<min;++index){
            if (listP.get(index)==listQ.get(index)&&(index+1>=min||listP.get(index+1)!=listQ.get(index+1))){
                return listP.get(index);
            }
        }
        return null;
    }

    private void front(TreeNode root,TreeNode target,List<TreeNode> setP){
        if (root==null||(setP.size()>0&&setP.get(setP.size()-1)==target)){
            return;
        }else {
            setP.add(root);
        }
        front(root.left,target,setP);
        front(root.right,target,setP);
        if (setP.size()>0&&setP.get(setP.size()-1)==target){
            return;
        }
        setP.remove(setP.size()-1);
    }

    @Test
    public void test(){
        TreeNode treeNode = new TreeNode(3);
        treeNode.left=new TreeNode(5);
        treeNode.right=new TreeNode(1);
        treeNode.left.left=new TreeNode(6);
        TreeNode ans = lowestCommonAncestor(treeNode, treeNode.left, treeNode.left.left);
    }

//    检查子树。你有两棵非常大的二叉树：T1，有几万个节点；T2，有几万个节点。设计一个算法，判断 T2 是否为 T1 的子树。
//    如果 T1 有这么一个节点 n，其子树与 T2 一模一样，则 T2 为 T1 的子树，也就是说，从节点 n 处把树砍断，得到的树与 T2 完全相同。
//    示例1:
//    输入：t1 = [1, 2, 3], t2 = [2]
//    输出：true
//    链接：https://leetcode-cn.com/problems/check-subtree-lcci

    public boolean checkSubTree(TreeNode t1, TreeNode t2) {


        return true;
    }

//    给定一棵二叉树，其中每个节点都含有一个整数数值(该值或正或负)。
//    设计一个算法，打印节点数值总和等于某个给定值的所有路径的数量。
//    注意，路径不一定非得从二叉树的根节点或叶节点开始或结束，但是其方向必须向下(只能从父节点指向子节点方向)。
//    示例:
//    给定如下二叉树，以及目标和 sum = 22，
//            5
//            / \
//            4   8
//            /   / \
//            11  13  4
//            /  \    / \
//            7    2  5   1
//    返回:3
//    解释：和为 22的路径有：[5,4,11,2], [5,8,4,5], [4,11,7]
//    链接：https://leetcode-cn.com/problems/paths-with-sum-lcci

    public int pathSum(TreeNode root, int sum) {

        return 1;
    }
}
