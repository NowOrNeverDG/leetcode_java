import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

public class DoublePoint {
    public static void main(String[] args) {
        System.out.println(minWindow("ADOBECODEBANC", "ABC"));
    }

    //567
    //Input: s1 = "ab" s2 = "eidbaooo"
    //Output: True
    public static boolean checkInclusion1(String s1, String s2) {
        if (s1.length() > s2.length()) return false;
        int[] pattern = new int[26];
        int[] text = new int[26];
        int pcount = 0;
        for (int i = 0; i < s1.length(); i++) {
            pattern[s1.charAt(i) - 'a']++;
        }

        for (int i = 0; i < 26; i++) {
            if (pattern[i] > 0) pcount++;
        }
        int left = 0, right = 0;
        int winCount = 0;
        while (right < s2.length()) {
            if (pattern[s2.charAt(right) - 'a'] > 0) {
                text[s2.charAt(right) - 'a']++;
                if (pattern[s2.charAt(right) - 'a'] == text[s2.charAt(right) - 'a']) winCount++;
            }
            right++;

            while (pcount == winCount) {
                if (right - left == s1.length()) return true;
                if (pattern[s2.charAt(left) - 'a'] > 0) {
                    text[s2.charAt(left) - 'a']--;
                    if (text[s2.charAt(left) - 'a'] < pattern[s2.charAt(left) - 'a']) winCount--;
                }
                left++;
            }
        }
        return false;
    }

    //76
    //Input: s = "ADOBECODEBANC", t = "ABC"
    //Output: "BANC"
    //public String minWindow(String s, String t)
    public static String minWindow(String s, String t) {
        int tLen = s.length(), pLen = t.length();
        char[] text = s.toCharArray(), pattern = t.toCharArray();

        int[] textFreq = new int[123];
        int[] patternFreq = new int[123];
        for (int i = 0; i < pLen; i++) {
            patternFreq[pattern[i]]++;
        }
        int pCount = 0;
        for (int i = 0; i < 123; i++) {
            if (patternFreq[i] > 0) pCount++;
        }
        int left = 0, right = 0, winCount = 0, MinAns = Integer.MAX_VALUE;
        StringBuilder Str = new StringBuilder();
        for (int i = 0; i < 123; i++) {
            Str.append("B");
        }
        String ansStr = Str.toString();

        while (right < tLen) {
            if (patternFreq[text[right]] > 0) {
                textFreq[text[right]]++;
                if (patternFreq[text[right]] == textFreq[text[right]]) winCount++;
            }
            right++;

            while(winCount == pCount) {
                String tempStr = s.substring(left,right);
                if (tempStr.length() < ansStr.length()) ansStr = tempStr;

                if (patternFreq[text[left]] > 0) {
                    textFreq[text[right]]--;
                    if (patternFreq[text[right]] > textFreq[text[right]]) winCount--;
                }
                left++;
            }
        }
        return ansStr;
    }
}