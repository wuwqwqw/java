import org.junit.Test;

import java.util.List;

public class LianBiao {

    class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
            next = null;
        }
    }
    
//    给定两个用链表表示的整数，每个节点包含一个数位。
//    这些数位是反向存放的，也就是个位排在链表首部。
//    编写函数对这两个整数求和，并用链表形式返回结果。
//    示例：
//    输入：(7 -> 1 -> 6) + (5 -> 9 -> 2)，即617 + 295
//    输出：2 -> 1 -> 9，即912
//    链接：https://leetcode-cn.com/problems/sum-lists-lcci

    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode tem1 = l1;
        ListNode tem2 = l2;
        while (true) {
            if (tem2 == null) {
                break;
            }
            if (tem1 == null) {
                ListNode tem = l2;
                l2 = l1;
                l1 = tem;
                break;
            }
            tem1 = tem1.next;
            tem2 = tem2.next;
        }
        ListNode ans = l1;
        int lastValue = 0;
        while (l1 != null) {
            if (l2 != null) {
                int tem = (l1.val + l2.val + lastValue);
                l1.val = tem % 10;
                lastValue = tem / 10;
                l2 = l2.next;
            } else {
                int tem = (l1.val + lastValue);
                l1.val = tem % 10;
                lastValue = tem / 10;
            }
            if (l1.next == null && lastValue == 1) {
                l1.next = new ListNode(1);
                l1 = l1.next;
            }
            l1 = l1.next;
        }
        return ans;
    }

    @Test
    public void testAddTwoNumbers() {
        ListNode listNode = new ListNode(0);
        ListNode listNode1 = new ListNode(7);
        listNode1.next = new ListNode(3);
        ListNode listNode2 = addTwoNumbers(listNode, listNode1);
    }

//    给你一个链表的头节点 head 和一个特定值 x ，请你对链表进行分隔，使得所有 小于 x 的节点都出现在 大于或等于 x 的节点之前。
//    你不需要 保留 每个分区中各节点的初始相对位置。
//    示例 1：
//    输入：head = [1,4,3,2,5,2], x = 3
//    输出：[1,2,2,4,3,5]
//    链接：https://leetcode-cn.com/problems/partition-list-lcci

    public ListNode partition(ListNode head, int x) {
        ListNode small = new ListNode(0);
        ListNode smallHead = small;
        ListNode large = new ListNode(0);
        ListNode largeHead = large;
        while (head != null) {
            if (head.val < x) {
                small.next = head;
                small = small.next;
            } else {
                large.next = head;
                large = large.next;
            }
            head = head.next;
        }
        large.next = null;
        small.next = largeHead.next;
        return smallHead.next;
    }

//    编写代码，移除未排序链表中的重复节点。保留最开始出现的节点。
//    示例1:
//    输入：[1, 2, 3, 3, 2, 1]
//    输出：[1, 2, 3]
//    https://leetcode-cn.com/problems/remove-duplicate-node-lcci/

    // 时间换空间
    public ListNode removeDuplicateNodes(ListNode head) {
        ListNode ob = head;
        while (ob != null) {
            ListNode oc = ob;
            while (oc.next != null) {
                if (oc.next.val == ob.val) {
                    oc.next = oc.next.next;
                } else {
                    oc = oc.next;
                }
            }
            ob = ob.next;
        }
        return head;
    }

//    实现一种算法，找出单向链表中倒数第 k 个节点。返回该节点的值。
//    注意：本题相对原题稍作改动
//    示例：
//    输入： 1->2->3->4->5 和 k = 2
//    输出： 4
//    说明：
//    给定的 k保证是有效的。
//    链接：https://leetcode-cn.com/problems/kth-node-from-end-of-list-lcci

    public int kthToLast(ListNode head, int k) {
        ListNode right = head;
        for (;k>0;--k){
            right = right.next;
        }
        while (right!=null){
            right = right.next;
            head = head.next;
        }
        return head.val;
    }


}
