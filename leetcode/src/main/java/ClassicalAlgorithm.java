import org.junit.Test;

// 需要记住的算法
public class ClassicalAlgorithm {

    class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
            next = null;
        }
    }
    
//    给定一个链表，如果它是有环链表，实现一个算法返回环路的开头节点。若环不存在，请返回 null。
//    如果链表中有某个节点，可以通过连续跟踪 next 指针再次到达，则链表中存在环。 为了表示给定链表中的环，
//    我们使用整数 pos 来表示链表尾连接到链表中的位置（索引从 0 开始）。 如果 pos 是 -1，
//    则在该链表中没有环。注意：pos 不作为参数进行传递，仅仅是为了标识链表的实际情况。
//    示例 1：
//    输入：head = [3,2,0,-4], pos = 1
//    输出：tail connects to node index 1
//    解释：链表中有一个环，其尾部连接到第二个节点。
//    链接：https://leetcode-cn.com/problems/linked-list-cycle-lcci

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


//    给定一个整数数组，编写一个函数，找出索引m和n，只要将索引区间[m,n]的元素排好序，整个数组就是有序的。
//    注意：n-m尽量最小，也就是说，找出符合条件的最短序列。
//    函数返回值为[m,n]，若不存在这样的m和n（例如整个数组是有序的），请返回[-1,-1]。
//    输入： [1,2,4,7,10,11,7,12,6,7,16,18,19]
//    输出： [3,9]
//    链接：https://leetcode-cn.com/problems/sub-sort-lcci

    public int[] subSort(int[] array) {
        if(array == null || array.length == 0) return new int[]{-1, -1};
        int last = -1, first = -1;
        int max = Integer.MIN_VALUE;
        int min = Integer.MAX_VALUE;
        int len = array.length;
        for(int i = 0; i < len; i++){
            if(array[i] < max){
                last = i;
            }else{
                max = Math.max(max, array[i]);
            }
            if(array[len - 1 - i] > min){
                first = len - 1 - i;
            }else{
                min = Math.min(min, array[len - 1 - i]);
            }
        }
        return new int[] {first, last};
    }

    @Test
    public void testSubSort(){
        int[] ints = {1, 2, 4, 7, 10, 11, 7, 12, 6, 7, 16, 18, 19};
        subSort(ints);
    }

//    给你一个字符串 s，找到 s 中最长的回文子串。
//    示例 1：
//    输入：s = "babad"
//    输出："bab"
//    解释："aba" 同样是符合题意的答案。
//    链接：https://leetcode-cn.com/problems/longest-palindromic-substring

    public String longestPalindrome(String s) {
        int len = s.length();
        if (len < 2) {
            return s;
        }
        int maxLen = 1;
        int tem=1;
        int mid=0;
        for(int i=1;i<len-1;i++){
            tem=1;
            for(int j=1;j+i<len&&i-j>=0;j++){
                if(s.charAt(i-j)!=s.charAt(i+j)){
                    break;
                }
                tem+=2;
                if(maxLen<tem){
                    maxLen=tem;
                    mid=i;
                }
            }
        }
        for(int i=0;i<len-1;i++){
            if(s.charAt(i)==s.charAt(i+1)){
                tem=2;
                if(maxLen<tem){
                    maxLen=tem;
                    mid=i;
                }
                for(int j=1;j+i+1<len&&i-j>=0;j++){
                    if(s.charAt(i-j)!=s.charAt(i+j+1)){
                        break;
                    }
                    tem+=2;
                    if(maxLen<tem){
                        maxLen=tem;
                        mid=i;
                    }
                }
            }
        }
        if(maxLen%2==1){
            return s.substring(mid-maxLen/2, mid+maxLen/2+1);
        }else{
            return s.substring(mid-maxLen/2+1, mid+maxLen/2+1);
        }
    }

//    给你一个字符串 s ，请你统计并返回这个字符串中 回文子串 的数目。
//    回文字符串 是正着读和倒过来读一样的字符串。
//    子字符串 是字符串中的由连续字符组成的一个序列。
//    具有不同开始位置或结束位置的子串，即使是由相同的字符组成，也会被视作不同的子串。
//    示例 1：
//    输入：s = "abc"
//    输出：3
//    解释：三个回文子串: "a", "b", "c"
//    示例 2：
//    输入：s = "aaa"
//    输出：6
//    解释：6个回文子串: "a", "a", "a", "aa", "aa", "aaa"
//    链接：https://leetcode-cn.com/problems/palindromic-substrings

    public int countSubstrings(String s) {
        int ans = 0;
        for (int i = 0;i<s.length();++i){
            ans++;
            for (int j = 1;i-j>=0&&i+j<s.length();++j){
                if (s.charAt(i-j)==s.charAt(i+j)){
                    ans++;
                }else {
                    break;
                }
            }
            if (i+1<s.length()&&s.charAt(i)==s.charAt(i+1)){
                ans++;
                for (int j = 1;i-j>=0&&i+1+j<s.length();++j){
                    if (s.charAt(i-j)==s.charAt(i+j+1)){
                        ans++;
                    }else {
                        break;
                    }
                }
            }
        }
        return ans;
    }

    @Test
    public void testCountSubstrings(){
        countSubstrings("abc");
    }

//    给你一个整数 n ，求恰由 n 个节点组成且节点值从 1 到 n 互不相同的 二叉搜索树 有多少种？返回满足题意的二叉搜索树的种数。
//    示例 1：
//    输入：n = 3
//    输出：5
//    链接：https://leetcode-cn.com/problems/unique-binary-search-trees

    public int numTrees(int n) {
        int[] G = new int[n + 1];
        G[0] = 1;
        G[1] = 1;

        for (int i = 2; i <= n; ++i) {
            for (int j = 1; j <= i; ++j) {
                G[i] += G[j - 1] * G[i - j];
            }
        }
        return G[n];
    }

}
