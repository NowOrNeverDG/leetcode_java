public class DynamicProgramming {
    //121-Best Time to Buy and Sell Stock
    //You are given an array prices where prices[i] is the price of a given stock on the ith day.
    //You want to maximize your profit by choosing a single day to buy one stock and choosing a different day in the future to sell that stock.
    //Input: prices = [7,1,5,3,6,4]
    //Output: 5
    public int maxProfit(int[] prices) {
        int n = prices.length;
        int dp_i_0 = 0;
        int dp_i_1 = Integer.MIN_VALUE;
        for (int i = 0; i < prices.length; i++) {
            dp_i_0 = Math.max(dp_i_0,dp_i_1+prices[i]);
            dp_i_1 = Math.max(dp_i_1,-prices[i]);
        }
        return dp_i_0;
    }

    //53-Maximum Subarray
    //Given an integer array nums, find the contiguous subarray (containing at least one number) which has the largest sum and return its sum.
    //Input: nums = [-2,1,-3,4,-1,2,1,-5,4]
    //Output: 6
    public int maxSubArray(int[] nums) {
        int[] dp = new int[nums.length];
        int max = nums[0];
        dp[0] = nums[0];
        for (int i = 1; i < nums.length; i++) {
            dp[i] = Math.max(dp[i-1]+nums[i], nums[i]);
            max = Math.max(dp[i],max);

        }
        return max;
    }

    //70-Climbing Stairs
    //You are climbing a staircase. It takes n steps to reach the top.Each time you can either climb 1 or 2 steps. In how many distinct ways can you climb to the top?
    //Input: n = 2
    //Output: 2
    public int climbStairs1(int n) {
        int[] dp = new int[n];
        dp[0] = 1;
        dp[1] = 1;
        if (n == 0) return 0;
        if (n == 1) return dp[n-1];

        for (int i = 2; i <= n; i++) {
            dp[i] = dp[i-1] + dp[i-2];
        }
        return dp[n];
    }
    public int climbStairs(int n) {
        int dp_0 = 1;
        int dp_1 = 1;
        if (n == 1) return dp_1;
        for (int i = 2; i <= n; i++) {
            int dp_temp = dp_1;
            dp_1 = dp_0+dp_1;
            dp_0 = dp_temp;
        }
        return dp_1;
    }

    //64-Minimum Path Sum
    //Given a m x n grid filled with non-negative numbers, find a path from top left to bottom right, which minimizes the sum of all numbers along its path.
    //Input: grid = [[1,3,1],[1,5,1],[4,2,1]]
    //Output: 7
    public int minPathSum(int[][] grid) {
        int r = grid.length;
        int c = grid[0].length;
        if (r == 0 || c == 0) return 0;
        for (int i = r-1; i >= 0; i--) {
            for (int j = c-1; j >= 0; j--) {
                if (i == r-1&&j == c-1) grid[i][j] = grid[i][j];
                else if (i == r-1&&j != c-1) grid[i][j] += grid[i][j+1];
                else if (j == c-1&&i != r-1) grid[i][j] += grid[i+1][j];
                else grid[i][j] += Math.min(grid[i+1][j],grid[i][j+1]);
            }
        }
        return grid[0][0];
    }

}
