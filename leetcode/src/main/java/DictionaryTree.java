import org.junit.Test;

import java.util.*;
import java.util.stream.Collectors;

public class DictionaryTree {

//    哦，不！你不小心把一个长篇文章中的空格、
//    标点都删掉了，并且大写也弄成了小写。
//    像句子"I reset the computer. It still didn’t boot!"
//    已经变成了"iresetthecomputeritstilldidntboot"。
//    在处理标点符号和大小写之前，你得先把它断成词语。
//    当然了，你有一本厚厚的词典dictionary，不过，有些词没在词典里。
//    假设文章用sentence表示，设计一个算法，把文章断开，要求未识别的字符最少，返回未识别的字符数。
//    注意：本题相对原题稍作改动，只需返回未识别的字符数
//    示例：
//    输入：
//    dictionary = ["looked","just","like","her","brother"]
//    sentence = "jesslookedjustliketimherbrother"
//    输出： 7
//    解释： 断句后为"jess looked just like tim her brother"，共7个未识别字符。
//    链接：https://leetcode-cn.com/problems/re-space-lcci

    public int respace(String[] dictionary, String sentence) {
        int n = sentence.length();
        Trie root = new Trie();
        for (String word : dictionary) {
            root.insert(word);
        }

        int[] dp = new int[n + 1];
        Arrays.fill(dp, Integer.MAX_VALUE);
        dp[0] = 0;
        for (int i = 1; i <= n; ++i) {
            dp[i] = dp[i - 1] + 1;

            Trie curPos = root;
            for (int j = i; j >= 1; --j) {
                int t = sentence.charAt(j - 1) - 'a';
                if (curPos.next[t] == null) {
                    break;
                } else if (curPos.next[t].isEnd) {
                    dp[i] = Math.min(dp[i], dp[j - 1]);
                }
                if (dp[i] == 0) {
                    break;
                }
                curPos = curPos.next[t];
            }
        }
        return dp[n];
    }


//    给定一组单词words，编写一个程序，找出其中的最长单词，
//    且该单词由这组单词中的其他单词组合而成。若有多个长度相同的结果，
//    返回其中字典序最小的一项，若没有符合要求的单词则返回空字符串。
//    示例：
//    输入： ["cat","banana","dog","nana","walk","walker","dogwalker"]
//    输出： "dogwalker"
//    解释： "dogwalker"可由"dog"和"walker"组成。
//    链接：https://leetcode-cn.com/problems/longest-word-lcci

    public String longestWord(String[] words) {
        Trie trie = new Trie();
        for (String s : words) {
            trie.insert(s);
        }
        List<String> collect = Arrays.stream(words).sorted((o1, o2) -> o1.length() - o2.length() == 0 ? o2.compareTo(o1) : o1.length() - o2.length()).collect(Collectors.toList());
        for (int i = collect.size() - 1; i >= 0; --i) {
            String s = collect.get(i);
            backTrack(s, trie, s.length());
            if (inTrieFlag) {
                return s;
            }
        }
        return "";
    }

    boolean inTrieFlag = false;

    public void backTrack(String s, Trie trie, int length) {
        if (s == null || s.equals("")) {
            inTrieFlag = true;
        }
        Trie tem = trie;
        if (!inTrieFlag) {
            for (int i = 0; i < s.length() && !inTrieFlag; ++i) {
                if (null != tem.next[s.charAt(i) - 'a']) {
                    tem = tem.next[s.charAt(i) - 'a'];
                } else {
                    return;
                }
                if (tem.isEnd && i != length) {
                    backTrack(s.substring(i + 1), trie, length);
                }
            }
        }
    }

    class Trie {

        public boolean isEnd;
        public Trie[] next;

        public Trie() {
            isEnd = false;
            next = new Trie[26];
        }

        public void insert(String s) {
            Trie trie = this;
            for (int i = 0; i < s.length(); ++i) {
                int index = s.charAt(i) - 'a';
                if (null == trie.next[index]) {
                    trie.next[index] = new Trie();
                }
                trie = trie.next[index];
            }
            trie.isEnd = true;
        }
    }

    @Test
    public void testTrie() {
        longestWord(new String[]{"cat", "banana", "dog", "nana", "walk", "walker", "dogwalker"});
    }

//    给定一个较长字符串big和一个包含较短字符串的数组smalls，
//    设计一个方法，根据smalls中的每一个较短字符串，
//    对big进行搜索。输出smalls中的字符串在big里出现的所有位置positions，
//    其中positions[i]为smalls[i]出现的所有位置。
//    示例：
//    输入：
//    big = "mississippi"
//    smalls = ["is","ppi","hi","sis","i","ssippi"]
//    输出： [[1,4],[8],[],[3],[1,4,7,10],[5]]
//    链接：https://leetcode-cn.com/problems/multi-search-lcci


    public int[][] multiSearch(String big, String[] smalls) {
        int[][] ints = new int[smalls.length][];
        List<List<Integer>> lists = new ArrayList<>();
        for (int i = 0; i < smalls.length; i++) {
            lists.add(new ArrayList<>());
        }
        multiSearchTrie multiSearchTrie = new multiSearchTrie();
        for (int i = 0; i < smalls.length; ++i) {
            multiSearchTrie.insert(smalls[i], i);
        }
        for (int i = 0; i < big.length(); ++i) {
            multiSearchTrie tem = multiSearchTrie;
            for (int j = i; j < big.length(); ++j) {
                if (null == tem.next[big.charAt(j) - 'a']) {
                    break;
                }
                if (tem.next[big.charAt(j) - 'a'].index >= 0) {
                    lists.get(tem.next[big.charAt(j) - 'a'].index).add(i);
                }
                tem = tem.next[big.charAt(j) - 'a'];
            }
        }
        for (int i = 0; i < lists.size(); ++i) {
            List<Integer> integers = lists.get(i);
            int[] intsTem = new int[integers.size()];
            for (int j = 0; j < integers.size(); ++j) {
                intsTem[j] = integers.get(j);
            }
            ints[i] = intsTem;
        }
        return ints;
    }

    public static class multiSearchTrie {

        public multiSearchTrie[] next;
        public Integer index;

        public multiSearchTrie() {

            next = new multiSearchTrie[26];
            index = -1;
        }

        public void insert(String s, int index) {
            multiSearchTrie multiSearchTrie = this;
            for (int i = 0; i < s.length(); ++i) {
                if (null == multiSearchTrie.next[s.charAt(i) - 'a']) {
                    multiSearchTrie.next[s.charAt(i) - 'a'] = new multiSearchTrie();
                }
                multiSearchTrie = multiSearchTrie.next[s.charAt(i) - 'a'];
            }
            multiSearchTrie.index = index;
        }
    }

    @Test
    public void testMultiSearch() {
        multiSearch("mississippi", new String[]{"is", "hi"});
    }

//    给你一个字符串 s 和一个字符串列表 wordDict 作为字典。请你判断是否可以利用字典中出现的单词拼接出 s 。
//    注意：不要求字典中出现的单词全部都使用，并且字典中的单词可以重复使用。
//    示例 1：
//    输入: s = "leetcode", wordDict = ["leet", "code"]
//    输出: true
//    解释: 返回 true 因为 "leetcode" 可以由 "leet" 和 "code" 拼接成。
//    链接：https://leetcode-cn.com/problems/word-break

    public boolean wordBreak(String s, List<String> wordDict) {
        Set<String> wordDictSet = new HashSet(wordDict);
        boolean[] dp = new boolean[s.length() + 1];
        dp[0] = true;
        for (int i = 1; i <= s.length(); i++) {
            for (int j = 0; j < i; j++) {
                if (dp[j] && wordDictSet.contains(s.substring(j, i))) {
                    dp[i] = true;
                    break;
                }
            }
        }
        return dp[s.length()];
    }

}
