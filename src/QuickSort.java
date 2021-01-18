import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class QuickSort {
    //TIQ-347 QuickSort-Lomuto
    private static int[] key_arr_inorder;
    private static Map<Integer,Integer> num_freq_pair;
    public static void swap(int a, int b) {
        System.out.println(a + " , " + b);
        int tmp = key_arr_inorder[a];
        key_arr_inorder[a] = key_arr_inorder[b];
        key_arr_inorder[b] = tmp;
    }
    public static int partition(int left_index, int right_index) {
        int pivot_freq = num_freq_pair.get(key_arr_inorder[right_index]);//设最右的数字为pivot
        int store_index = left_index;

        for (int i = left_index; i<=right_index; i++){
            if (num_freq_pair.get(key_arr_inorder[i]) < pivot_freq) {
                store_index++;
                swap(i, store_index);
            }
        }
        //讲pivot放在store_index处
        //if (num_freq_pair.get(key_arr_inorder[right_index]) > num_freq_pair.get(key_arr_inorder[store_index]))
        swap(store_index,right_index);
        return store_index;
    }
    public static void quick_select(int left, int right) {
        //if one element array input
        if (left == right) return;

        int pivot_index = partition(left, right);
        quick_select(left, pivot_index);
        quick_select(pivot_index + 1, right);
    }
    public static int[] topKFrequent_QuickSort(int[] nums, int k) {
        //build hash map: character and how often it appears
        num_freq_pair = new HashMap<>();
        for (int num:nums) {
            num_freq_pair.put(num, num_freq_pair.getOrDefault(num, 0)+1);
        }
        //array of num_freq_pair element
        int n = num_freq_pair.size();
        key_arr_inorder = new int[n];
        int i = 0;
        for (int num: num_freq_pair.keySet()) {
            key_arr_inorder[i] = num;
            i++;
        }
        quick_select(0,n-1);
        System.out.println(key_arr_inorder);
        return Arrays.copyOfRange(key_arr_inorder,n-k,n);
    }
}

