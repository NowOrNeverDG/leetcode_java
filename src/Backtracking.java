import java.util.ArrayList;
import java.util.List;

public class Backtracking {
    public static void main(String[] args) {
        int[] nums = new int[]{1,2,3};
        subsets(nums);
    }

    //TIQ-22
    public static List<String> generateParenthesis(int n) {
        List<String> ans = new ArrayList<>();
        generateParenthesisBacktrack(ans, "", 0, 0, n);
        return ans;
    }
    public static void generateParenthesisBacktrack(List<String> ans, String cur, int open, int close, int max) {
        if (cur.length() == max * 2) {
            ans.add(cur);
            return;
        }

        if (open < max) {
            generateParenthesisBacktrack(ans, cur + '(', open + 1, close, max);
        }
        if (close < open) {
            generateParenthesisBacktrack(ans, cur + ')', open, close + 1, max);
        }
    }

    //TIQ-78
    private static List<List<Integer>> output = new ArrayList();
    private static int n, k;
    public static List<List<Integer>> subsets(int[] nums) {
        n = nums.length;
        for (k = 0; k < n+1; ++k) {
            backtrack(0, new ArrayList<Integer>(), nums);
        }
        return output;
    }
    public static void backtrack(int first, ArrayList<Integer> curr, int[] nums) {
        //if the combination is done
        if (curr.size() == k) {
            System.out.println("curr[]: " + curr);
            output.add(new ArrayList<>());
        }

        for (int i = first; i < n; ++i) {
            //add i into the current combination
            curr.add(nums[i]);
            //use next integers to complete the combination
            backtrack(i +1, curr, nums);
            //backtrack
            System.out.println(curr.remove(curr.size()-1));

        }
    }
}