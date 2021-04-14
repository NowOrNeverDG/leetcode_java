import java.util.ArrayDeque;
import java.util.Deque;

public class ArrayNMatrix {

    //240-Search a 2D Matrix II
    //Write an efficient algorithm that searches for a target value in an m x n integer matrix. The matrix has the following properties:
    //Input: matrix = [[1,4,7,11,15],[2,5,8,12,19],[3,6,9,16,22],[10,13,14,17,24],[18,21,23,26,30]], target = 5
    //Output: true
    public boolean searchMatrix(int[][] matrix, int target) {
        int r = matrix.length;
        int c = matrix[0].length;
        int i = r-1;
        int j = 0;
        while (0<=i&&i<r&&0<=j&&j<c) {
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
        for (int i = 0; i < nums.length;i++) {
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
        int[] temp = new int[r*c];

        for (int i = 0; i < nums.length; i++) {
            for (int j = 0; j < nums[i].length; j++) {
                temp[i*nums[i].length+j] = nums[i][j];
            }
        }
        for (int i = 0; i < r; i++) {
            for (int j = 0; j < c; j++) {
                ans[i][j] = temp[i*c+j];
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
                maxSize = Math.max(stack.size(),maxSize);
            }
        }
        return maxSize;
    }
}
