import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

public class ZiFuChuan {

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
}
