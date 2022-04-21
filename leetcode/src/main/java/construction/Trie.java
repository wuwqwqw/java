package construction;

public class Trie {

//    Trie（发音类似 "try"）或者说 前缀树 是一种树形数据结构，用于高效地存储和检索字符串数据集中的键。这一数据结构有相当多的应用情景，例如自动补完和拼写检查。
//    请你实现 Trie 类：
//    Trie() 初始化前缀树对象。
//    void insert(String word) 向前缀树中插入字符串 word 。
//    boolean search(String word) 如果字符串 word 在前缀树中，返回 true（即，在检索之前已经插入）；否则，返回 false 。
//    boolean startsWith(String prefix) 如果之前已经插入的字符串 word 的前缀之一为 prefix ，返回 true ；否则，返回 false 。
//    链接：https://leetcode-cn.com/problems/implement-trie-prefix-tree

    Trie next[];
    boolean isEnd;

    public Trie() {
        next = new Trie[26];
        isEnd = false;
    }

    public void insert(String word) {
        Trie trie = this;
        for (int i = 0;i<word.length();++i){
            if (null==trie.next[word.charAt(i)-'a']){
                trie.next[word.charAt(i)-'a'] = new Trie();
            }
            trie = trie.next[word.charAt(i) - 'a'];
        }
        trie.isEnd = true;
    }

    public boolean search(String word) {
        Trie trie = this;
        for (int i = 0;i<word.length();++i){
            if (trie.next[word.charAt(i)-'a']!=null){
                trie = trie.next[word.charAt(i)-'a'];
            }else {
                return false;
            }
        }
        if (trie.isEnd){
            return true;
        }else {
            return false;
        }
    }

    public boolean startsWith(String prefix) {
        Trie trie = this;
        for (int i = 0;i<prefix.length();++i){
            if (trie.next[prefix.charAt(i)-'a']!=null){
                trie = trie.next[prefix.charAt(i)-'a'];
            }else {
                return false;
            }
        }
        return true;
    }
}
