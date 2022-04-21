import org.junit.Test;

import javax.security.auth.login.CredentialNotFoundException;
import java.util.*;

public class Sort {

//    给你一个包含 n 个整数的数组nums，判断nums中是否存在三个元素 a，b，c ，使得a + b + c = 0 ？请你找出所有和为 0 且不重复的三元组。
//    注意：答案中不可以包含重复的三元组。
//    示例 1：
//    输入：nums = [-1,0,1,2,-1,-4]
//    输出：[[-1,-1,2],[-1,0,1]]
//    链接：https://leetcode-cn.com/problems/3sum

    public List<List<Integer>> threeSum(int[] nums) {
        int n = nums.length;
        Arrays.sort(nums);
        List<List<Integer>> ans = new ArrayList<List<Integer>>();
        for (int first = 0; first < n; ++first) {
            if (first > 0 && nums[first] == nums[first - 1]) {
                continue;
            }
            int third = n - 1;////////////////////////////在第一个枚举之后就初始化第三个指针到最后面，每一次第二个枚举都不对第三个指针操作
            int target = -nums[first];
            for (int second = first + 1; second < n; ++second) {
                if (second > first + 1 && nums[second] == nums[second - 1]) {
                    continue;
                }
                while (second < third && nums[second] + nums[third] > target) {
                    --third;
                }
                if (second == third) {
                    break;
                }
                if (nums[second] + nums[third] == target) {
                    List<Integer> list = new ArrayList<Integer>();
                    list.add(nums[first]);
                    list.add(nums[second]);
                    list.add(nums[third]);
                    ans.add(list);
                }
            }
        }
        return ans;
    }

//    给你一个字符串数组，请你将 字母异位词 组合在一起。可以按任意顺序返回结果列表。
//    字母异位词 是由重新排列源单词的字母得到的一个新单词，所有源单词中的字母通常恰好只用一次。
//    输入: strs = ["eat", "tea", "tan", "ate", "nat", "bat"]
//    输出: [["bat"],["nat","tan"],["ate","eat","tea"]]
//    链接：https://leetcode-cn.com/problems/group-anagrams

    public List<List<String>> groupAnagrams(String[] strs) {
        HashMap<String, ArrayList<String>> map = new HashMap<>();
        for (String s : strs) {
            char[] chars = s.toCharArray();
            Arrays.sort(chars);
            ArrayList<String> strings;
            if (map.containsKey(String.valueOf(chars))) {
                strings = map.get(String.valueOf(chars));
            } else {
                strings = new ArrayList<>();
            }
            strings.add(s);
            map.put(String.valueOf(chars), strings);
        }
        List<List<String>> ans = new ArrayList<>();
        for (String key : map.keySet()) {
            ans.add(map.get(key));
        }
        return ans;
    }

//    给你一个整数数组 nums和一个整数k，请你返回其中出现频率前k高的元素。你可以按任意顺序返回答案。
//    示例 1:
//    输入: nums = [1,1,1,2,2,3], k = 2
//    输出: [1,2]
//    链接：https://leetcode-cn.com/problems/top-k-frequent-elements

    public int[] topKFrequent(int[] nums, int k) {//维护一个长度为k的小顶堆
        Map<Integer, Integer> occurrences = new HashMap<Integer, Integer>();
        for (int i : nums) {
            occurrences.put(i, occurrences.getOrDefault(i, 0) + 1);
        }
        PriorityQueue<int[]> queue = new PriorityQueue<int[]>((a, b) -> (a[1] - b[1]));
        for (Map.Entry<Integer, Integer> entry : occurrences.entrySet()) {
            int num = entry.getKey(), count = entry.getValue();
            if (queue.size() == k) {
                if (queue.peek()[1] < count) {
                    queue.poll();
                    queue.offer(new int[]{num, count});
                }
            } else {
                queue.offer(new int[]{num, count});
            }
        }
        int[] ret = new int[k];
        for (int i = 0; i < k; ++i) {
            ret[i] = queue.poll()[0];
        }
        return ret;
    }

//    给定整数数组 nums 和整数 k，请返回数组中第 k 个最大的元素。
//    请注意，你需要找的是数组排序后的第 k 个最大的元素，而不是第 k 个不同的元素。
//    示例 1:
//    输入: [3,2,1,5,6,4] 和 k = 2
//    输出: 5
//    链接：https://leetcode-cn.com/problems/kth-largest-element-in-an-array

    int num;

    public int findKthLargest(int[] nums, int k) {
        findKthLargestQuickSort(nums, 0, nums.length - 1, k);
        return num;
    }

    public void findKthLargestQuickSort(int[] nums, int left, int right, int k) {
        if (left > right) {
            return;
        }
        int base = nums[left];
        int leftIndex = left;
        int rightIndex = right;
        while (leftIndex < rightIndex) {
            while (rightIndex > leftIndex && nums[rightIndex] >= base) {
                rightIndex--;
            }
            if (rightIndex > leftIndex) {
                nums[leftIndex] = nums[rightIndex];
            }
            while (rightIndex > leftIndex && nums[leftIndex] < base) {
                leftIndex++;
            }
            if (rightIndex > leftIndex) {
                nums[rightIndex] = nums[leftIndex];
            }
        }
        nums[leftIndex] = base;//指针最后必然在左，可以手写一个例子测试一下
        if (leftIndex == nums.length - k) {
            num = nums[leftIndex];
        } else if (leftIndex > nums.length - k) {
            findKthLargestQuickSort(nums, left, leftIndex - 1, k);
        } else {
            findKthLargestQuickSort(nums, leftIndex + 1, right, k);
        }
    }

//    以数组 intervals 表示若干个区间的集合，其中单个区间为 intervals[i] = [starti, endi] 。
//    请你合并所有重叠的区间，并返回一个不重叠的区间数组，该数组需恰好覆盖输入中的所有区间。
//    示例 1：
//    输入：intervals = [[1,3],[2,6],[8,10],[15,18]]
//    输出：[[1,6],[8,10],[15,18]]
//    解释：区间 [1,3] 和 [2,6] 重叠, 将它们合并为 [1,6].
//    链接：https://leetcode-cn.com/problems/merge-intervals

    public int[][] merge(int[][] intervals) {
        Arrays.sort(intervals, (o1, o2) -> o1[0] == o2[0] ? o1[1] - o2[1] : o1[0] - o2[0]);
        int[] tem = {intervals[0][0], intervals[0][1]};
        ArrayList<int[]> lists = new ArrayList<>();
        for (int i = 1; i < intervals.length; ++i) {
            if (intervals[i][0] > tem[1]) {
                lists.add(new int[]{tem[0], tem[1]});
                tem[0] = intervals[i][0];
                tem[1] = intervals[i][1];
            } else {
                int min = Math.min(intervals[i][0], tem[0]);
                int max = Math.max(intervals[i][1], tem[1]);
                tem[0] = min;
                tem[1] = max;
            }
        }
        lists.add(new int[]{tem[0], tem[1]});
        int[][] ans = lists.toArray(new int[lists.size()][2]);
        return ans;
    }

    @Test
    public void testMerge() {
        merge(new int[][]{{1, 3}, {2, 6}, {8, 10}, {15, 18}});
    }

//    给定一个包含红色、白色和蓝色、共n 个元素的数组nums，原地对它们进行排序，使得相同颜色的元素相邻，并按照红色、白色、蓝色顺序排列。
//    我们使用整数 0、1 和 2 分别表示红色、白色和蓝色。
//    必须在不使用库的sort函数的情况下解决这个问题。
//    示例 1：
//    输入：nums = [2,0,2,1,1,0]
//    输出：[0,0,1,1,2,2]
//    链接：https://leetcode-cn.com/problems/sort-colors

    public void sortColors(int[] nums) {
        int red = 0, white = 0;
        for (int num : nums) {
            if (num == 1) {
                white++;
            } else if (num == 0) {
                red++;
            }
        }
        for (int i = 0; i < nums.length; ++i) {
            if (i < red) {
                nums[i] = 0;
            } else if (i >= red + white) {
                nums[i] = 2;
            } else {
                nums[i] = 1;
            }
        }
    }

    @Test
    public void testSortColors() {
        sortColors(new int[]{2, 0, 2, 1, 1, 0});
    }

//    给你链表的头结点head，请将其按升序排列并返回排序后的链表。
//    示例 1：
//    输入：head = [4,2,1,3]
//    输出：[1,2,3,4]
//    来源：力扣（LeetCode）
//    链接：https://leetcode-cn.com/problems/sort-list

    public static class ListNode {
        int val;
        ListNode next;

        ListNode() {
        }

        ListNode(int val) {
            this.val = val;
        }

        ListNode(int val, ListNode next) {
            this.val = val;
            this.next = next;
        }
    }

    public ListNode sortList(ListNode head) {
        return sortList(head, null);
    }

    public ListNode sortList(ListNode head, ListNode tail) {
        if (head == null) {
            return null;
        }
        if (head.next == tail) {
            head.next = null;
            return head;
        }
        ListNode slow = head, fast = head;
        while (fast != tail) {
            slow = slow.next;
            fast = fast.next;
            if (fast != tail) {
                fast = fast.next;
            }
        }
        ListNode mid = slow;
        ListNode list1 = sortList(head, mid);
        ListNode list2 = sortList(mid, tail);
        return merge(list1, list2);
    }

    public ListNode merge(ListNode head1, ListNode head2) {
        ListNode dummyHead = new ListNode(0);
        ListNode temp = dummyHead, temp1 = head1, temp2 = head2;
        while (temp1 != null && temp2 != null) {
            if (temp1.val <= temp2.val) {
                temp.next = temp1;
                temp1 = temp1.next;
            } else {
                temp.next = temp2;
                temp2 = temp2.next;
            }
            temp = temp.next;
        }
        if (temp1 != null) {
            temp.next = temp1;
        } else if (temp2 != null) {
            temp.next = temp2;
        }
        return dummyHead.next;
    }

//    给你一个整数数组 nums ，你需要找出一个 连续子数组 ，如果对这个子数组进行升序排序，那么整个数组都会变为升序排序。
//    请你找出符合题意的 最短 子数组，并输出它的长度。
//    示例 1：
//    输入：nums = [2,6,4,8,10,9,15]
//    输出：5
//    解释：你只需要对 [6, 4, 8, 10, 9] 进行升序排序，那么整个表都会变为升序排序。
//    链接：https://leetcode-cn.com/problems/shortest-unsorted-continuous-subarray
//    一趟扫描
//    public int findUnsortedSubarray(int[] nums) {
//        int n = nums.length;
//        int maxn = Integer.MIN_VALUE, right = -1;
//        int minn = Integer.MAX_VALUE, left = -1;
//        for (int i = 0; i < n; i++) {
//            if (maxn > nums[i]) {
//                right = i;
//            } else {
//                maxn = nums[i];
//            }
//            if (minn < nums[n - i - 1]) {
//                left = n - i - 1;
//            } else {
//                minn = nums[n - i - 1];
//            }
//        }
//        return right == -1 ? 0 : right - left + 1;
//    }


    public int findUnsortedSubarray(int[] nums) {
        int[] ints = new int[nums.length];
        for (int i = 0; i < nums.length; ++i) {
            ints[i] = nums[i];
        }
        quickSort(nums, 0, nums.length - 1);
        int left = 0, right = 0;
        for (int i = 0; i < nums.length; ++i) {
            if (nums[i] != ints[i]) {
                left = i;
                break;
            }
        }
        for (int i = nums.length - 1; i >= 0; --i) {
            if (nums[i] != ints[i]) {
                right = i;
                break;
            }
        }
        return left == right ? 0 : right - left + 1;
    }

    public void quickSort(int[] nums, int left, int right) {
        int leftIndex = left;
        int rightIndex = right;
        int base = nums[left];
        while (leftIndex < rightIndex) {
            while (leftIndex < rightIndex && nums[rightIndex] >= base) {
                rightIndex--;
            }
            if (leftIndex < rightIndex) {
                nums[leftIndex] = nums[rightIndex];
            }
            while (leftIndex < rightIndex && nums[leftIndex] < base) {
                leftIndex++;
            }
            if (leftIndex < rightIndex) {
                nums[rightIndex] = nums[leftIndex];
            }
        }
        nums[leftIndex] = base;
        if (leftIndex - 1 > left)
            quickSort(nums, left, leftIndex - 1);
        if (right > leftIndex + 1)
            quickSort(nums, leftIndex + 1, right);
    }

//    给你一个用字符数组tasks表示的CPU需要执行的任务列表。其中每个字母表示一种不同种类的任务。任务可以以任意顺序执行，并且每个任务都可以在1个单位时间内执行完。
//    在任何一个单位时间，CPU可以完成一个任务，或者处于待命状态。
//    然而，两个相同种类 的任务之间必须有长度为整数 n 的冷却时间，因此至少有连续 n 个单位时间内 CPU 在执行不同的任务，或者在待命状态。
//    你需要计算完成所有任务所需要的 最短时间 。
//    示例 1：
//    输入：tasks = ["A","A","A","B","B","B"], n = 2
//    输出：8
//    解释：A -> B -> (待命) -> A -> B -> (待命) -> A -> B
//    在本示例中，两个相同类型任务之间必须间隔长度为 n = 2 的冷却时间，而执行一个任务只需要一个单位时间，所以中间出现了（待命）状态。
//    链接：https://leetcode-cn.com/problems/task-scheduler

    public int leastInterval(char[] tasks, int n) {
        if (n == 0) {
            return tasks.length;
        }
        HashMap<Integer, Integer> map = new HashMap<>();
        for (char c : tasks) {
            map.put(c - 'A', map.getOrDefault(c - 'A', 0) + 1);
        }
        ArrayList<Integer> list = new ArrayList<>();
        for (Integer i : map.values()) {
            list.add(i);
        }
        int ans = 0;
        while (list.size() > 0) {
            list.sort(Collections.reverseOrder());
            int tem = n;
            for (int i = 0; i < list.size(); ++i) {
                if (list.get(i) == 1) {
                    list.remove(i);
                    --i;
                } else {
                    list.set(i, list.get(i) - 1);
                }
                tem--;
                if (tem == -1) {
                    break;
                }
            }
            ans += n + 1;
            if (list.size() == 0){
                ans -=tem+1;
            }
        }
        return ans;
    }

    @Test
    public void testLeastInterval() {
        leastInterval(new char[]{'A', 'A', 'A', 'B', 'B', 'B'}, 2);
    }

//    输入一个非负整数数组，把数组里所有数字拼接起来排成一个数，打印能拼接出的所有数字中最小的一个。
//    示例 1:
//    输入: [10,2]
//    输出: "102"
//    https://leetcode-cn.com/problems/ba-shu-zu-pai-cheng-zui-xiao-de-shu-lcof/

    public String minNumber(int[] nums) {
        String[] strings = new String[nums.length];
        for (int i = 0;i<nums.length;++i){
            strings[i] = String.valueOf(nums[i]);
        }
        Arrays.sort(strings, (s1,s2)->((s1+s2).compareTo(s2+s1)));
        StringBuilder builder = new StringBuilder("");
        for (String s:strings){
            builder.append(s);
        }
        return String.valueOf(builder);
    }

    @Test
    public void testMinNumber(){
        minNumber(new int[]{824,938,1399,5607,6973,5703,9609,4398,8247});
    }

}
