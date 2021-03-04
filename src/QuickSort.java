import java.util.*;

public class QuickSort {
    public static void main(String[] args) {
        int[] nums = new int[]{3,2,1,5,6,4};
        quicksort_fkl(nums, 0, nums.length-1);
    }

    //For QuickSort-Lomuto
    private static int[] key_arr;
    private static Map<Integer,Integer> nums_pair;

    //TIQ-347 QuickSort-Lomuto
    public static void swap_tp(int a, int b) {
        int tmp = key_arr[a];
        key_arr[a] = key_arr[b];
        key_arr[b] = tmp;
    }
    public static int partition_tp(int left_index, int right_index) {
        int pivot_freq = nums_pair.get(key_arr[right_index]);//设最右的数字为pivot
        int store_index = left_index;

        for (int i = left_index; i<=right_index; i++){
            if (nums_pair.get(key_arr[i]) < pivot_freq) {
                store_index++;
                swap_tp(i, store_index);
            }
        }
        //讲pivot放在store_index处
        //if (num_freq_pair.get(key_arr_inorder[right_index]) > num_freq_pair.get(key_arr_inorder[store_index]))
        swap_tp(store_index,right_index);
        return store_index;
    }
    public static void quick_select_tp(int left, int right) {
        //if one element array input
        if (left == right) return;
        int pivot_index = partition_tp(left, right);
        quick_select_tp(left, pivot_index);
        quick_select_tp(pivot_index + 1, right);
    }
    public static int[] topKFrequent_QuickSort(int[] nums, int k) {
        //build hash map: character and how often it appears
        nums_pair = new HashMap<>();
        for (int num:nums) {
            nums_pair.put(num, nums_pair.getOrDefault(num, 0)+1);
        }
        //array of num_freq_pair element
        int n = nums_pair.size();
        key_arr = new int[n];
        int i = 0;
        for (int num: nums_pair.keySet()) {
            key_arr[i] = num;
            i++;
        }
        quick_select_tp(0,n-1);
        System.out.println(key_arr);
        return Arrays.copyOfRange(key_arr,n-k,n);
    }

    //
    public static void swap_me(int index1, int index2) {
        int temp = key_arr[index1];
        key_arr[index1] = key_arr[index2];
        key_arr[index2] = temp;
    }
    public static int partition_me(int left_index, int right_index, int pivot_index) {
        int pivot = nums_pair.get(key_arr[pivot_index]);
        swap_me(right_index, pivot);
        int store_index = left_index;

         for (int i = left_index; i <= right_index; i++) {
             if (nums_pair.get(key_arr[i]) < pivot) {
                 swap_me(i,store_index);
                 store_index++;
             }
         }
         swap_me(store_index,right_index);
         return store_index;
    }
    public static void quick_select_me(int left_index, int right_index) {
        if (left_index == right_index) return;
        Random random_num = new Random();
        int pivot_index = left_index + random_num.nextInt(right_index - left_index);
        pivot_index = partition_me(left_index,right_index, pivot_index);
        quick_select_me(left_index, pivot_index);
        quick_select_me(pivot_index+1,right_index);
    }
    public static int majorityElement(int[] nums) {
        nums_pair = new HashMap<>();
        for (int num: nums) {
            nums_pair.put(num,nums_pair.getOrDefault(num,0)+1);
        }
        int n = nums_pair.size();
        key_arr = new int[n];
        int i = 0;
        for (int num : nums_pair.keySet()) {
            key_arr[i] = num;
            i++;
        }
        quick_select_me(0,n-1);
        System.out.println(key_arr);
        return key_arr[key_arr.length-1];
    }

    //215 QuickSort-Hoare
    public static int partition_fkl(int[] nums, int start_index, int end_index ) {
        int pivot = start_index+1, temp;
        while (start_index < end_index) {
            while (start_index <= end_index && nums[start_index] <= nums[pivot]) start_index++;//找到比pivot大的
            while (start_index <= end_index && nums[end_index] > nums[pivot]) end_index--;//找到比pivot小的
            if (start_index > end_index) break;
            temp = nums[start_index];//start和end互换
            nums[start_index] = nums[end_index];
            nums[end_index] = temp;
        }
        //end和pivot互换
        temp = nums[end_index];
        nums[end_index] = nums[pivot];
        nums[pivot] = temp;
        return end_index;
    }
    public static int[] quicksort_fkl (int[] nums, int start_index, int end_index) {
        if (start_index != end_index) {
            int pivot_index = partition_fkl(nums, start_index, end_index);
            quicksort_fkl(nums, start_index, pivot_index-1);
            quicksort_fkl(nums, pivot_index, end_index);
        }
        return nums;
    }
    public int findKthLargest(int[] nums, int k) {
        quicksort_fkl(nums,0,nums.length-1);
        return nums[nums.length-1-(k-1)];
    }
}