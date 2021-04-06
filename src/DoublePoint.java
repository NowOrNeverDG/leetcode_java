import java.util.*;

public class DoublePoint {
    public static void main(String[] args) {
        System.out.println("Answer is : " + lengthOfLongestSubstring("abcabcbb"));

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
        int left = 0, right = 0, winCount = 0, minLen = Integer.MAX_VALUE;
        StringBuilder str = new StringBuilder();
        for (int i = 0; i < tLen+1; i++) {
            str.append('+');
        }

        String ansStr = "";
        while (right < tLen) {
            if (patternFreq[text[right]] > 0) {
                textFreq[text[right]]++;
                if (patternFreq[text[right]] == textFreq[text[right]]) winCount++;
            }
            right++;

            while(winCount == pCount) {
                if ((right - left) < minLen) {
                    minLen = right - left;
                    ansStr = s.substring(left,right);
                }

                if (patternFreq[text[left]] > 0) {
                    textFreq[text[left]]--;
                    if (patternFreq[text[left]] > textFreq[text[left]]) winCount--;
                }
                left++;
            }
        }
        return ansStr;
    }

    //438
    //Input: s: "cbaebabacd" p: "abc"
    //Output: [0, 6]
    public static List<Integer> findAnagrams(String s, String p) {
        char[] text = s.toCharArray();
        char[] pattern = p.toCharArray();

        int tLen = text.length, pLen = pattern.length;

        int[] textFreq = new int[26];
        int[] patternFreq = new int[26];
        for (char i:pattern) {
            patternFreq[i - 'a']++;
        }
        int pCount = 0;
        for (int i = 0; i < 26; i++) {
            if (patternFreq[i] > 0) pCount++;
        }
        int left = 0, right = 0, winCount = 0;
        List<Integer> ansList= new ArrayList<>();
        while (right < tLen) {
            if (patternFreq[text[right] - 'a'] > 0) {
                textFreq[text[right] - 'a']++;
                if (patternFreq[text[right] - 'a'] == textFreq[text[right] - 'a']) winCount++;
            }
            right++;
            while (winCount == pCount ) {
                if (right - left == pLen) ansList.add(right-pLen);
                if (patternFreq[text[left] - 'a'] > 0) {
                    textFreq[text[left] - 'a']--;
                    if (patternFreq[text[left] - 'a'] > textFreq[text[left] - 'a']) winCount--;
                }
                left++;
            }
        }
        return ansList;
    }

    //3
    //Input: s = "abcabcbb"
    //Output: 3
    public static int lengthOfLongestSubstring(String s) {
        char[] text = s.toCharArray();
        int tLen = text.length;
        int[] textFrq = new int[128];

        int left = 0, right = 0, maxLong = 0;
        while (right < tLen) {
            if (textFrq[text[right]] == 0) {
                textFrq[text[right]]++;
                right++;
                maxLong = Math.max(maxLong, right - left);
            } else {
                textFrq[text[left]]--;
                left++;
            }
        }
        return maxLong;
    }

}