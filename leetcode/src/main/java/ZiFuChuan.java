import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

public class ZiFuChuan {

//    给定一个字符串 s ，请你找出其中不含有重复字符的最长子串的长度。
//    示例1:
//    输入: s = "abcabcbb"
//    输出: 3
//    解释: 因为无重复字符的最长子串是 "abc"，所以其长度为 3。
//    链接：https://leetcode.cn/problems/longest-substring-without-repeating-characters

    public int lengthOfLongestSubstring(String s) {
        // 哈希集合，记录每个字符是否出现过
        Set<Character> occ = new HashSet<Character>();
        int n = s.length();
        // 右指针，初始值为 -1，相当于我们在字符串的左边界的左侧，还没有开始移动
        int rk = -1, ans = 0;
        for (int i = 0; i < n; ++i) {
            if (i != 0) {
                // 左指针向右移动一格，移除一个字符
                occ.remove(s.charAt(i - 1));
            }
            while (rk + 1 < n && !occ.contains(s.charAt(rk + 1))) {
                // 不断地移动右指针
                occ.add(s.charAt(rk + 1));
                ++rk;
            }
            // 第 i 到 rk 个字符是一个极长的无重复字符子串
            ans = Math.max(ans, rk - i + 1);
        }
        return ans;
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

//    实现一个算法，确定一个字符串 s 的所有字符是否全都不同。
//    示例 1：
//    输入: s = "leetcode"
//    输出: false
//    示例 2：
//    输入: s = "abc"
//    输出: true
//    链接：https://leetcode-cn.com/problems/is-unique-lcci

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

//    给定两个字符串 s1 和 s2，请编写一个程序，确定其中一个字符串的字符重新排列后，能否变成另一个字符串。
//    示例 1：
//    输入: s1 = "abc", s2 = "bca"
//    输出: true
//    示例 2：
//    输入: s1 = "abc", s2 = "bad"
//    输出: false
//    链接：https://leetcode-cn.com/problems/check-permutation-lcci

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

//    字符串压缩。利用字符重复出现的次数，编写一种方法，实现基本的字符串压缩功能。
//    比如，字符串aabcccccaaa会变为a2b1c5a3。
//    若“压缩”后的字符串没有变短，则返回原先的字符串。
//    你可以假设字符串中只包含大小写英文字母（a至z）。
//    示例1:
//    输入："aabcccccaaa"
//    输出："a2b1c5a3"
//    示例2:
//    输入："abbccd"
//    输出："abbccd"
//    解释："abbccd"压缩后为"a1b2c2d1"，比原字符串长度更长。
//    链接：https://leetcode-cn.com/problems/compress-string-lcci

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

//    字符串轮转。给定两个字符串s1和s2，请编写代码检查s2是否为s1旋转而成（比如，waterbottle是erbottlewat旋转后的字符串）。
//    示例1:
//    输入：s1 = "waterbottle", s2 = "erbottlewat"
//    输出：True
//    示例2:
//    输入：s1 = "aa", s2 = "aba"
//    输出：False
//    链接：https://leetcode-cn.com/problems/string-rotation-lcci

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

//    你有两个字符串，即pattern和value。 pattern字符串由字母"a"和"b"组成，用于描述字符串中的模式。
//    例如，字符串"catcatgocatgo"匹配模式"aabab"（其中"cat"是"a"，"go"是"b"），
//    该字符串也匹配像"a"、"ab"和"b"这样的模式。但需注意"a"和"b"不能同时表示相同的字符串。
//    编写一个方法判断value字符串是否匹配pattern字符串。
//    示例 1：
//    输入： pattern = "abba", value = "dogcatcatdog"
//    输出： true
//    链接：https://leetcode-cn.com/problems/pattern-matching-lcci

    public boolean patternMatching(String pattern, String value) {
        int count_a = 0, count_b = 0;
        for (char ch: pattern.toCharArray()) {
            if (ch == 'a') {
                ++count_a;
            } else {
                ++count_b;
            }
        }
        if (count_a < count_b) {
            int temp = count_a;
            count_a = count_b;
            count_b = temp;
            char[] array = pattern.toCharArray();
            for (int i = 0; i < array.length; i++) {
                array[i] = array[i] == 'a' ? 'b' : 'a';
            }
            pattern = new String(array);
        }
        if (value.length() == 0) {
            return count_b == 0;
        }
        if (pattern.length() == 0) {
            return false;
        }
        for (int len_a = 0; count_a * len_a <= value.length(); ++len_a) {
            int rest = value.length() - count_a * len_a;
            if ((count_b == 0 && rest == 0) || (count_b != 0 && rest % count_b == 0)) {
                int len_b = (count_b == 0 ? 0 : rest / count_b);
                int pos = 0;
                boolean correct = true;
                String value_a = "", value_b = "";
                for (char ch: pattern.toCharArray()) {
                    if (ch == 'a') {
                        String sub = value.substring(pos, pos + len_a);
                        if (value_a.length() == 0) {
                            value_a = sub;
                        } else if (!value_a.equals(sub)) {
                            correct = false;
                            break;
                        }
                        pos += len_a;
                    } else {
                        String sub = value.substring(pos, pos + len_b);
                        if (value_b.length() == 0) {
                            value_b = sub;
                        } else if (!value_b.equals(sub)) {
                            correct = false;
                            break;
                        }
                        pos += len_b;
                    }
                }
                if (correct && !value_a.equals(value_b)) {
                    return true;
                }
            }
        }
        return false;
    }

//    给你一个 32 位的有符号整数 x ，返回将 x 中的数字部分反转后的结果。
//    如果反转后整数超过 32 位的有符号整数的范围[−231, 231− 1] ，就返回 0。
//    假设环境不允许存储 64 位整数（有符号或无符号）。
//    示例 1：
//    输入：x = 123
//    输出：321
//    链接：https://leetcode.cn/problems/reverse-integer
    public int reverse(int x) {
        String s = String.valueOf(x);
        if (s.charAt(0)=='-'){
            s = s.substring(1);
        }
        s = new StringBuilder(s).reverse().toString();
        if (Long.parseLong(s)>Integer.MAX_VALUE){
            return 0;
        }else {
            return x>0?Integer.parseInt(s):-Integer.parseInt(s);
        }
    }

//    给你一个字符串 s，找到 s 中最长的回文子串。
//    示例 1：
//    输入：s = "babad"
//    输出："bab"
//    解释："aba" 同样是符合题意的答案。
//    链接：https://leetcode.cn/problems/longest-palindromic-substring

    // 本题有时间空间双O(n)的manacher算法，比较复杂，这里是中心扩展法，空间O(1)，时间O(N2)
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


}
