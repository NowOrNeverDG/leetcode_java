import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class HouseRobber {

    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode() {}
        TreeNode(int val) { this.val = val; }
        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }
    public static void main(String[] args) {
        HouseRobber hr = new HouseRobber();
        System.out.println(hr.robII(new int[]{1,2,3,1}));
    }

    //198. House Robber
    public int rob(int[] nums) {
        int n = nums.length;
        int[] dp = new int[n];
        for (int i = 0; i < n; i++) {
            if (i==0) {
                dp[i] = nums[i];
                continue;
            } else if (i==1) {
                dp[i] = Math.max(dp[i-1], nums[i]); continue;
            }
            dp[i] = Math.max(nums[i] + dp[i-2], dp[i-1]);
        }
        return dp[n-1];
    }

    //213. House Robber II
    //Input: nums = [2,3,2]
    //Output: 3
    public int robII(int[] nums) {
        int n = nums.length;
        if (n == 1) return nums[0];
        int[] rob1 = Arrays.copyOfRange(nums,0,n-1);
        int[] rob2 = Arrays.copyOfRange(nums,1,n);
        return Math.max(robRange(rob1), robRange(rob2));
    }
    private int robRange(int[] nums) {
        int n = nums.length;
        int[] dp = new int[n];
        for (int i = 0; i < n; i++) {
            if (i == 0) { dp[i] = nums[i]; continue; }
            else if (i == 1) { dp[i] = Math.max(dp[i - 1], nums[i]); continue;}
            dp[i] = Math.max(nums[i] + dp[i - 2], dp[i - 1]);
        }
        return dp[n - 1];
    }

    //337. House Robber III
    //Input: root = [3,2,3,null,3,null,1]
    //Output: 7
    Map<TreeNode, Integer> memo337 = new HashMap<>();
    public int robIII(TreeNode root) {
        if (root == null) return 0;
        if (memo337.containsKey(root)) return memo337.get(root);
        int left_val = root.left == null ? 0 : robIII(root.left.left) + robIII(root.left.right);
        int right_val = root.right == null ? 0 : robIII(root.right.left) + robIII(root.right.right);

        int rob_it = root.val + left_val + right_val;
        int not_rob = robIII(root.left) + robIII(root.right);

        int res = Math.max(not_rob, rob_it);
        memo337.put(root, res);
        return res;
    }
}
