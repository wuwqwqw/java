import java.util.*;

public class leetcode {
    public static void main(String[] args) {
        boolean flipedString = isFlipedString("waterbottle"
                , "erbottlewat");

    }

    public boolean isUnique(String astr) {
        HashSet<Character> characters = new HashSet<>();
        for (int i=0;i<astr.length();++i){
            if (characters.contains(astr.charAt(i))){
                return false;
            }else{
                characters.add(astr.charAt(i));
            }
        }
        return true;
    }

    public boolean CheckPermutation(String s1, String s2) {
        HashMap<Character, Integer> map1 = new HashMap<>();
        HashMap<Character, Integer> map2 = new HashMap<>();
        for(int i=0;i<s1.length();i++){
            map1.put(s1.charAt(i),map1.getOrDefault(s1.charAt(i),0)+1);
        }
        for(int i=0;i<s2.length();i++){
            map2.put(s2.charAt(i),map2.getOrDefault(s2.charAt(i),0)+1);
        }
        return map1.equals(map2);
    }

    public String compressString(String S) {
        StringBuilder stringBuilder = new StringBuilder();
        for (int i=0;i<S.length();i++){
            int j=i+1;
            while(j<S.length()&&S.charAt(j)==S.charAt(i)){
                j++;
            }
            stringBuilder.append(S.charAt(i));
            stringBuilder.append(j-i);
            i=j-1;
        }
        if(stringBuilder.length()<S.length()){
            return stringBuilder.toString();
        }else{
            return S;
        }
    }

    public static boolean isFlipedString(String s1, String s2) {
        for (int i = 0;i<s2.length();i++){
            if(s2.charAt(i)==s1.charAt(0)){
                StringBuilder stringBuilder = new StringBuilder();
                stringBuilder.append(s2, i, s2.length());
                stringBuilder.append(s2, 0, i);
                if (stringBuilder.toString().equals(s1)){
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
            while(fast.next!=null&&fast.next.next!=null){
                fast=fast.next.next;
                slow=slow.next;
                if (slow==fast){
                    fast=head;
                    while(true){
                        if(fast==slow){
                            return slow;
                        }
                        fast=fast.next;
                        slow=slow.next;
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
        for (int i = 0;i<graph.length;i++){
            if (!map.containsKey(graph[i][0])){
                map.put(graph[i][0],new ArrayList<>());
            }
            map.get(graph[i][0]).add(graph[i][1]);
        }
        boolean vis[]=new boolean[n];
        Queue<Integer> queue = new LinkedList<>();
        queue.offer(start);
        while (!queue.isEmpty()){
            Integer poll = queue.poll();
            List<Integer> integers = map.get(poll);
            if (integers.isEmpty()){
                continue;
            }
            for (Integer i:integers){
                if (!vis[i]){
                    if (target==i){
                        return true;
                    }else{
                        vis[i]=true;
                        queue.offer(i);
                    }
                }
            }
        }
        return false;
    }


}
