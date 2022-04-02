import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.LinkedList;

public class Tree {

//    二叉树数据结构TreeNode可用来表示单向链表
//    （其中left置空，right为下一个链表节点）。实现一个方法，
//    把二叉搜索树转换为单向链表，
//    要求依然符合二叉搜索树的性质，转换操作应是原址的，
//    也就是在原始的二叉搜索树上直接修改。
//    返回转换后的单向链表的头节点。
//    注意：本题相对原题稍作改动
//    示例：
//    输入： [4,2,5,1,3,null,6,0]
//    输出： [0,null,1,null,2,null,3,null,4,null,5,null,6]
//    来源：力扣（LeetCode）
//    链接：https://leetcode-cn.com/problems/binode-lcci

    TreeNode lastNode = null;
    TreeNode ansConvertBiNode = null;

    public TreeNode convertBiNode(TreeNode root) {
        midConvertBiCode(root);
        for (TreeNode tem = ansConvertBiNode; tem != null; tem = tem.right) {
            tem.left = null;
        }
        return ansConvertBiNode;
    }

    public void midConvertBiCode(TreeNode root) {
        if (root == null) {
            return;
        }
        midConvertBiCode(root.left);
        if (lastNode != null) {
            lastNode.right = root;
            lastNode = root;
        }
        if (lastNode == null) {
            ansConvertBiNode = root;
            lastNode = root;
        }
        midConvertBiCode(root.right);
    }

    //    给定一个有序整数数组，元素各不相同且按升序排列，编写一个算法，创建一棵高度最小的二叉搜索树。
//    示例:
//    给定有序数组: [-10,-3,0,5,9],
//    一个可能的答案是：[0,-3,9,-10,null,5]，它可以表示下面这个高度平衡二叉搜索树：
//            0
//            / \
//            -3   9
//            /   /
//            -10  5
//    链接：https://leetcode-cn.com/problems/minimum-height-tree-lcci

    public TreeNode sortedArrayToBST(int[] nums) {
        return buildTree(nums, 0, nums.length - 1);
    }

    public TreeNode buildTree(int[] nums, int begin, int end) {
        if (begin > end) {
            return null;
        }
        int mid = (begin + end) / 2;
        TreeNode root = new TreeNode(nums[mid]);
        root.left = buildTree(nums, begin, mid - 1);
        root.right = buildTree(nums, mid + 1, end);
        return root;
    }

//    实现一个函数，检查二叉树是否平衡。在这个问题中，平衡树的定义如下：任意一个节点，其两棵子树的高度差不超过 1。
//    示例 1:
//    给定二叉树 [3,9,20,null,null,15,7]
//            3
//            / \
//            9  20
//            /  \
//            15   7
//    返回 true 。
//    链接：https://leetcode-cn.com/problems/check-balance-lcci

    boolean ansIsBalanced = true;

    public boolean isBalanced(TreeNode root) {
        depthIsBalanced(root);
        return ansIsBalanced;
    }

    public int depthIsBalanced(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int left = depthIsBalanced(root.left);
        int right = depthIsBalanced(root.right);
        if (Math.abs(left - right) > 1) {
            ansIsBalanced = false;
        }
        return Math.max(left, right) + 1;
    }

//    给定一棵二叉树，设计一个算法，
//    创建含有某一深度上所有节点的链表（比如，若一棵树的深度为 D，则会创建出 D 个链表）。
//    返回一个包含所有深度的链表的数组。
//    示例：
//    输入：[1,2,3,4,5,null,7,8]
//
//            1
//            /  \
//            2    3
//            / \    \
//            4   5    7
//            /
//            8
//    输出：[[1],[2,3],[4,5,7],[8]]
//    链接：https://leetcode-cn.com/problems/list-of-depth-lcci

    class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
            next = null;
        }
    }
    
    public ListNode[] listOfDepth(TreeNode tree) {
        ListNode[] nodes = new ListNode[depth(tree)];
        Queue<TreeNode> queue = new LinkedList<>();
        if (tree == null) {
            return nodes;
        } else {
            queue.offer(tree);
        }
        int depth = 0;
        while (!queue.isEmpty()) {
            TreeNode node = queue.poll();
            int k = queue.size();
            nodes[depth] = new ListNode(node.val);
            ListNode listNode = nodes[depth];
            if (node.left != null) {
                queue.offer(node.left);
            }
            if (node.right != null) {
                queue.offer(node.right);
            }
            for (; k > 0; --k) {
                TreeNode poll = queue.poll();
                if (poll.left != null) {
                    queue.offer(poll.left);
                }
                if (poll.right != null) {
                    queue.offer(poll.right);
                }
                listNode.next = new ListNode(poll.val);
                listNode = listNode.next;
            }
            depth++;
        }
        return nodes;
    }

    public int depth(TreeNode root) {
        if (root == null) {
            return 0;
        } else {
            return 1 + Math.max(depth(root.left), depth(root.right));
        }
    }

    @Test
    public void testListOfDepth() {
        TreeNode treeNode = new TreeNode(1);
        treeNode.left = new TreeNode(2);
        treeNode.right = new TreeNode(3);
        treeNode.left.left = new TreeNode(4);
        treeNode.left.right = new TreeNode(5);
        treeNode.right.right = new TreeNode(7);
        treeNode.left.left.left = new TreeNode(8);
        ListNode[] listNodes = listOfDepth(treeNode);
    }

//    设计一个算法，找出二叉搜索树中指定节点的“下一个”节点（也即中序后继）。
//    如果指定节点没有对应的“下一个”节点，则返回null。
//    示例 1:
//    输入: root = [2,1,3], p = 1
//
//            2
//            / \
//            1   3
//    输出: 2
//    链接：https://leetcode-cn.com/problems/successor-lcci

    TreeNode ansTreeNode = null;
    boolean flag = false;

    public TreeNode inorderSuccessor(TreeNode root, TreeNode p) {
        midInorderSuccessor(root, p);
        return ansTreeNode;
    }

    public void midInorderSuccessor(TreeNode root, TreeNode target) {
        if (root != null) {
            midInorderSuccessor(root.left, target);
            midInorderSuccessor(root.right, target);
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
        if (root == null) {
            return true;
        }
        mid(root);
        return ans;
    }

    public void mid(TreeNode root) {
        if (null != root.left) {
            mid(root.left);
        }
        if (!ans || (long) root.val <= lastValue) {
            ans = false;
            return;
        } else {
            lastValue = (long) root.val;
        }
        if (root.right != null) {
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
        front(root, p, listP);
        front(root, q, listQ);
        int min = Math.min(listP.size(), listQ.size());
        for (int index = 0; index < min; ++index) {
            if (listP.get(index) == listQ.get(index) && (index + 1 >= min || listP.get(index + 1) != listQ.get(index + 1))) {
                return listP.get(index);
            }
        }
        return null;
    }

    private void front(TreeNode root, TreeNode target, List<TreeNode> setP) {
        if (root == null || (setP.size() > 0 && setP.get(setP.size() - 1) == target)) {
            return;
        } else {
            setP.add(root);
        }
        front(root.left, target, setP);
        front(root.right, target, setP);
        if (setP.size() > 0 && setP.get(setP.size() - 1) == target) {
            return;
        }
        setP.remove(setP.size() - 1);
    }

    @Test
    public void test() {
        TreeNode treeNode = new TreeNode(3);
        treeNode.left = new TreeNode(5);
        treeNode.right = new TreeNode(1);
        treeNode.left.left = new TreeNode(6);
        TreeNode ans = lowestCommonAncestor(treeNode, treeNode.left, treeNode.left.left);
    }

//    检查子树。你有两棵非常大的二叉树：T1，有几万个节点；T2，有几万个节点。设计一个算法，判断 T2 是否为 T1 的子树。
//    如果 T1 有这么一个节点 n，其子树与 T2 一模一样，则 T2 为 T1 的子树，也就是说，从节点 n 处把树砍断，得到的树与 T2 完全相同。
//    示例1:
//    输入：t1 = [1, 2, 3], t2 = [2]
//    输出：true
//    链接：https://leetcode-cn.com/problems/check-subtree-lcci

    public boolean checkSubTree(TreeNode t1, TreeNode t2) {
        frontCheckSubTree(t1);
        String frontT1 = tem.toString();
        tem.delete(0, tem.length());
        frontCheckSubTree(t2);
        String frontT2 = tem.toString();
        tem.delete(0, tem.length());
        midCheckSubTree(t1);
        String midT1 = tem.toString();
        tem.delete(0, tem.length());
        midCheckSubTree(t2);
        String midT2 = tem.toString();
        tem.delete(0, tem.length());
        if (frontT1.contains(frontT2) && midT1.contains(midT2)) {
            return true;
        }
        return false;
    }

    StringBuilder tem = new StringBuilder("");

    public void frontCheckSubTree(TreeNode root) {
        if (root != null) {
            tem.append(root.val);
        } else {
            return;
        }
        frontCheckSubTree(root.left);
        frontCheckSubTree(root.right);
    }

    public void midCheckSubTree(TreeNode root) {
        if (root == null) {
            return;
        }
        midCheckSubTree(root.left);
        tem.append(root.val);
        midCheckSubTree(root.right);
    }

//    给定一棵二叉树，其中每个节点都含有一个整数数值(该值或正或负)。
//    设计一个算法，打印节点数值总和等于某个给定值的所有路径的数量。
//    注意，路径不一定非得从二叉树的根节点或叶节点开始或结束，但是其方向必须向下(只能从父节点指向子节点方向)。
//    示例:
//    给定如下二叉树，以及目标和sum = 22，
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
        dfsPathSum(root, sum);
        return answer;
    }

    int answer = 0;
    List<Integer> temList = new ArrayList<>();

    private void dfsPathSum(TreeNode root, int target) {
        if (root != null) {
            temList.add(root.val);
        } else {
            return;
        }
        int sum = 0;
        for (int i = temList.size() - 1; i >= 0; --i) {
            sum += temList.get(i);
            if (sum == target) {
                answer++;
            }
        }
        dfsPathSum(root.left, target);
        dfsPathSum(root.right, target);
        temList.remove(temList.size() - 1);
    }
}
