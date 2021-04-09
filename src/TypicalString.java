import java.util.HashMap;
import java.util.HashSet;


public class TypicalString {

    //242-Valid Anagram
    //Given two strings s and t, return true if t is an anagram of s, and false otherwise.
    //Input: s = "anagram", t = "nagaram"
    //Output: true
    public boolean isAnagram(String s, String t) {
        HashMap<Character,Integer> s_map = new HashMap<Character, Integer>();
        for (char c: s.toCharArray()) s_map.put(c , s_map.getOrDefault(c,0)+1);
        for (char c: t.toCharArray()) s_map.put(c , s_map.getOrDefault(c,0)-1);
        for (char c : s_map.keySet()) {
            if (s_map.get(c) == 0) continue;
            return false;
        }
        return true;
    }

    //409-Longest Palindrome
    //Input: s = "abccccdd"
    //Output: 7
    public int longestPalindrome(String s) {
        HashSet<Character> set = new HashSet<>();
        char[] arr = s.toCharArray();
        int count = 0;
        for (int i = 0; i<s.length();i++) {
            if (set.contains(arr[i])) { set.remove(arr[i]); count += 2;}
            else set.add(arr[i]);
        }

        return set.isEmpty() ? count:count+1;
    }

}
