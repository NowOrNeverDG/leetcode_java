import java.util.*;

public class ArrayNMatrix {

    //240-Search a 2D Matrix II
    //Write an efficient algorithm that searches for a target value in an m x n integer matrix. The matrix has the following properties:
    //Input: matrix = [[1,4,7,11,15],[2,5,8,12,19],[3,6,9,16,22],[10,13,14,17,24],[18,21,23,26,30]], target = 5
    //Output: true
    public boolean searchMatrix(int[][] matrix, int target) {
        int r = matrix.length;
        int c = matrix[0].length;
        int i = r - 1;
        int j = 0;
        while (0 <= i && i < r && 0 <= j && j < c) {
            System.out.println("i = " + i + " / " + "j =" + j);
            if (matrix[i][j] < target) j++;
            else if (matrix[i][j] > target) i--;
            else if (matrix[i][j] == target) return true;
        }
        return false;
    }

    //283-Move Zeroes
    //Given an integer array nums, move all 0's to the end of it while maintaining the relative order of the non-zero elements.
    //Input: nums = [0,1,0,3,12]
    //Output: [1,3,12,0,0]
    public void moveZeroes(int[] nums) {
        int p = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] != 0) {
                int temp = nums[i];
                nums[i] = nums[p];
                nums[p] = nums[i];
                p++;
            }
        }
    }

    //566-Reshape the Matrix
    //You're given a matrix represented by a two-dimensional array, and two positive integers r and c representing the row number and column number of the wanted reshaped matrix, respectively.
    //Input:
    //nums =
    //[[1,2],
    // [3,4]]
    //r = 1, c = 4
    //Output:
    //[[1,2,3,4]]
    public int[][] matrixReshape(int[][] nums, int r, int c) {
        if (nums.length * nums[0].length != r * c) return nums;

        int[][] ans = new int[r][c];
        int[] temp = new int[r * c];

        for (int i = 0; i < nums.length; i++) {
            for (int j = 0; j < nums[i].length; j++) {
                temp[i * nums[i].length + j] = nums[i][j];
            }
        }
        for (int i = 0; i < r; i++) {
            for (int j = 0; j < c; j++) {
                ans[i][j] = temp[i * c + j];
            }
        }
        return ans;
    }

    //485-Max Consecutive Ones
    //Given a binary array nums, return the maximum number of consecutive 1's in the array.
    //Input: nums = [1,1,0,1,1,1]
    //Output: 3
    public int findMaxConsecutiveOnes(int[] nums) {
        Deque<Integer> stack = new ArrayDeque<>();
        int maxSize = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == 0) stack.clear();
            else {
                stack.push(i);
                maxSize = Math.max(stack.size(), maxSize);
            }
        }
        return maxSize;
    }

    //287-Find the Duplicate Number
    //Given an array of integers nums containing n + 1 integers where each integer is in the range [1, n] inclusive.
    //Input: nums = [1,3,4,2,2]
    //Output: 2
    public int findDuplicate(int[] nums) {
        HashSet set = new HashSet();
        int ans;
        for (int i = 0; i < nums.length; i++) {
            if (set.contains(nums[i])) return nums[i];
            set.add(nums[i]);
        }
        return -1;
    }
    public int findDuplicate1(int[] nums) {//快慢指针Floyd's Tortoise and Hare
        int slow = 0;
        int fast = 0;
        do {
            fast = nums[nums[fast]];
            slow = nums[slow];
        } while (slow != fast);
        fast = 0;
        while (fast != slow) {
            fast = nums[fast];
            slow = nums[slow];
        }
        return slow;
    }

    //667. Beautiful Arrangement II
    //Given two integers n and k, construct a list answer that contains n different positive integers ranging from 1 to n and obeys the following requirement:
    //Input: n = 3, k = 1
    //Output: [1,2,3]
    public int[] constructArray(int n, int k) {
        int[] ans = new int[n];
        int c = 0;
        for (int v = 1; v < n-k; v++) {
            ans[c++] = v;
        }
        for (int i = 0; i < ans.length; i++)         System.out.println(ans[i]);
        for (int i = 0; i <= k; i++) {
            ans[c++] = (i%2 == 0) ? (n-k + i/2) : (n - i/2);
        }
        return ans;
    }

    //697-Degree of an Array
    //Given a non-empty array of non-negative integers nums, the degree of this array is defined as the maximum frequency of any one of its elements.
    //Input: nums = [1,2,2,3,1]
    //Output: 2
    public int findShortestSubArray(int[] nums) {
        HashMap<Integer,Integer> left = new HashMap<Integer, Integer>();
        HashMap<Integer,Integer> right = new HashMap<Integer, Integer>();
        HashMap<Integer,Integer> count = new HashMap<Integer, Integer>();

        for (int i = 0; i < nums.length; i++) {
            //计数
            count.put(nums[i], count.getOrDefault(nums[i], 0) + 1);
            //
            if (left.containsKey(nums[i])) {
                right.put(nums[i], i);
            } else {
                left.put(nums[i], i);
                right.put(nums[i], i);
            }
        }

        int degree = Collections.max(count.values());
        int ans = nums.length;
        for (int key:count.keySet()) {
            if (count.get(key) == degree) {
                ans = right.get(key) - left.get(key)+1 > ans ? ans:right.get(key)-left.get(key)+1;
            }
        }
        return ans;
    }
}
