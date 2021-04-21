import java.util.*;

public class Greedy {

    //455-Assign Cookies
    //Assume you are an awesome parent and want to give your children some cookies. But, you should give each child at most one cookie.
    //Each child i has a greed factor g[i], which is the minimum size of a cookie that the child will be content with; and each cookie j has a size s[j]. If s[j] >= g[i], we can assign the cookie j to the child i, and the child i will be content. Your goal is to maximize the number of your content children and output the maximum number.
    //Input: g = [1,2,3], s = [1,1]
    //Output: 1
    public int findContentChildren(int[] g, int[] s) {
        Arrays.sort(g);//需要饼干数
        Arrays.sort(s);//饼干个数
        int gp = 0, sp = 0, count = 0;
        while (gp<g.length&&sp<s.length) {
            if (g[gp]<=s[sp]) {
                count++;
                gp++;
            }
            sp++;
        }
        return count;
    }

    //435 Non-overlapping Intervals
    //Given an array of intervals intervals where intervals[i] = [starti, endi], return the minimum number of intervals you need to remove to make the rest of the intervals non-overlapping.
    //Input: intervals = [[1,2],[2,3],[3,4],[1,3]]
    //Output: 1
    public int eraseOverlapIntervals1(int[][] intervals) {//对首部排序
        if (intervals.length == 0) return 0;
        Arrays.sort(intervals, Comparator.comparingInt(p -> p[0]));
        int count = 0;
        int start = intervals[0][1];
        int left = 0, right = 1;
        while(right < intervals.length) {
            if (intervals[left][1] > intervals[right][0]) {
                if (intervals[left][1] > intervals[right][1]) {
                    left = right;
                }
                count++;
            } else left = right;
            right++;
        }
        return count;
    }
    public int eraseOverlapIntervals(int[][] intervals) {//对尾部排序Greedy Approach based on end points
        if (intervals.length == 0) return 0;
        Arrays.sort(intervals,Comparator.comparingInt(p -> p[1]));
        int count = 0;
        int end = intervals[0][1];
        for (int i = 1; i < intervals.length; i++) {
            if (intervals[i][0] < end) {
                count++;
            }else {
                end = intervals[i][1];
            }
        }
        return count;
    }

    //452-Minimum Number of Arrows to Burst Balloons
    //There are some spherical balloons spread in two-dimensional space. For each balloon, provided input is the start and end coordinates of the horizontal diameter. Since it's horizontal, y-coordinates don't matter, and hence the x-coordinates of start and end of the diameter suffice. The start is always smaller than the end.
    //Input: points = [[10,16],[2,8],[1,6],[7,12]]
    //Output: 2
    public int findMinArrowShots(int[][] points) {
        if (points.length == 0) return 0;
        Arrays.sort(points, Comparator.comparingInt(o -> o[1]));
        int count = 1;
        int end = points[0][1];
        for (int i = 1; i < points.length; i++) {
            if (points[i][0] > end) {
                end = points[i][1];
                count++;
            }
        }
        return count;
    }

    //406-Queue Reconstruction by Height
    //You are given an array of people, people, which are the attributes of some people in a queue (not necessarily in order). Each people[i] = [hi, ki] represents the ith person of height hi with exactly ki other people in front who have a height greater than or equal to hi.
    //Input: people = [[7,0],[4,4],[7,1],[5,0],[6,1],[5,2]]
    //Output: [[5,0],[7,0],[5,2],[6,1],[4,4],[7,1]]
    public int[][] reconstructQueue(int[][] people) {
        Arrays.sort(people, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return o1[0] == o2[0] ? o1[0]-o2[0] : o2[1]-o1[1];
            }
        });
        List<int[]> output = new LinkedList<int[]>();
        for (int[] p : people) {
            output.add(p[1],p);
        }
        int n = people.length;
        return output.toArray(new int[n][2]);
    }

    //121-Best Time to Buy and Sell Stock
    //You are given an array prices where prices[i] is the price of a given stock on the ith day.
    //You want to maximize your profit by choosing a single day to buy one stock and choosing a different day in the future to sell that stock.
    //Input: prices = [7,1,5,3,6,4]
    //Output: 5
    public int maxProfit(int[] prices) {
        int base = Integer.MAX_VALUE;
        int profit = Integer.MIN_VALUE;
        for (int i = 0; i < prices.length;i++) {
            base = Math.min(base, prices[i]);
            profit = Math.max(profit, prices[i]-base);
        }
        return profit;
    }

    //605-Can Place Flowers
    //You have a long flowerbed in which some of the plots are planted, and some are not. However, flowers cannot be planted in adjacent plots.
    //Input: flowerbed = [1,0,0,0,1], n = 1
    //Output: true
    public boolean canPlaceFlowers(int[] flowerbed, int n) {
        int count = 0;
        for (int i = 0; i < flowerbed.length;i++) {
            if (flowerbed[i] == 1) continue;
            int pre = i==0 ? 0:flowerbed[i-1];
            int next = i==flowerbed.length-1 ? 0:flowerbed[i+1];

            if (pre==0&&next==0) {
                count++;
                flowerbed[i] = 1;
            }
        }
        return count>=n;
    }

    //392-Is Subsequence
    //A subsequence of a string is a new string that is formed from the original string by deleting some (can be none) of the characters without disturbing the relative positions of the remaining characters. (i.e., "ace" is a subsequence of "abcde" while "aec" is not).
    //Input: s = "abc", t = "ahbgdc"
    //Output: true
    public boolean isSubsequence(String s, String t) {
        if (s == null || t == null || s.length() > t.length()) return false;
        if (s.length() == 0) return true;
        int index = 0;
        for (int i = 0; i < t.length(); i++) {
            if (s.charAt(index) == t.charAt(i)) index++;
            if (index == s.length()) return true;
        }
        return index == s.length();
    }

    //665-Non-decreasing Array
    //Given an array nums with n integers, your task is to check if it could become non-decreasing by modifying at most one element.
    //Input: nums = [4,2,3]
    //Output: true
    public boolean checkPossibility(int[] nums) {
        int count = 0;
        for (int i = 1; i < nums.length-1; i++) {
            int pre = i == 1 ? 0:nums[i-2];
            System.out.println(pre + " / " + nums[i-1] + " / " + nums[i]);
            if (nums[i] >= nums[i-1]) continue;
            count++;
            if (pre < nums[i]) nums[i-1] = pre;
            else nums[i] = nums[i-1];
        }
        return count<=1;
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


}
