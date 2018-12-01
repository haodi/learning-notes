package learning.leetcode;

import java.util.TreeSet;

/**
 * @author lihaodi
 */
public class LongestSubString {
    public static void main(String[] args) {
        System.out.println(lengthOfLongestSubstring("abcabcbb"));
        System.out.println(lengthOfLongestSubstring(" "));
    }

    public static int lengthOfLongestSubstring(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }

        String[] characters = s.split("");
        String subString;
        TreeSet<Integer> subStringLengths = new TreeSet<>();

        for (int i = 0; i < characters.length; i++) {
            subString = characters[i];

            for (int j = i + 1; j < characters.length; j++) {
                if (subString.contains(characters[j])) {
                    break;
                } else {
                    subString += characters[j];
                }
            }
            subStringLengths.add(subString.length());
        }

        return subStringLengths.size() == 0 ? 0 : subStringLengths.last();
    }
}
