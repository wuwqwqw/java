package Utils;

public class WordsFrequency {


//    设计一个方法，找出任意指定单词在一本书中的出现频率。
//    你的实现应该支持如下操作：
//    WordsFrequency(book)构造函数，参数为字符串数组构成的一本书
//    get(word)查询指定单词在书中出现的频率
//    示例：
//    WordsFrequency wordsFrequency = new WordsFrequency({"i", "have", "an", "apple", "he", "have", "a", "pen"});
//    wordsFrequency.get("you"); //返回0，"you"没有出现过
//    wordsFrequency.get("have"); //返回2，"have"出现2次
//    wordsFrequency.get("an"); //返回1
//    wordsFrequency.get("apple"); //返回1
//    wordsFrequency.get("pen"); //返回1
//    链接：https://leetcode-cn.com/problems/words-frequency-lcci

    private Trie trie;

    public WordsFrequency(String[] book) {
        trie = new Trie();
        for (String s:book){
            trie.insert(s);
        }
    }

    public int get(String word) {
        Trie tem = trie;
        for (int i = 0;i<word.length();++i){
            if (null!= tem.next[word.charAt(i)-'a']){
                tem = tem.next[word.charAt(i)-'a'];
                if (word.length()-1==i){
                    return tem.count;
                }
            }else {
                return 0;
            }
        }
        return 0;
    }

    static class Trie{

        public Integer count;
        public Trie[] next;

        public Trie(){
            count = 0;
            next = new Trie[26];
        }

        public void insert(String s){
            Trie trie = this;
            for (int i = 0;i<s.length();++i){
                if (null==trie.next[s.charAt(i)-'a']){
                    trie.next[s.charAt(i)-'a'] = new Trie();
                }
                trie = trie.next[s.charAt(i)-'a'];
            }
            trie.count++;
        }
    }
}
