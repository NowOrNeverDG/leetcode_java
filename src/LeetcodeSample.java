import java.lang.reflect.Array;
import java.util.*;

import static java.lang.Math.min;

public class LeetcodeSample {

    public static void main(String[] args) {
        int[] nums = new int[]{3,5};
        //findDuplicate(nums);
        System.out.println(coinChange(nums,4));
    }
    public class ListNode {
        int val;
        ListNode next;

        ListNode() {
        }

        ListNode(int val) {
            this.val = val;
        }

        ListNode(int val, ListNode next) {
            this.val = val;
            this.next = next;
        }
    }
    public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode() {}
        TreeNode(int val) {
            this.val = val;
        }
        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }

    //TIQ-94 Morris Algorithm
    public List<Integer> inorderTraversal(TreeNode root) {
        List<Integer> nodes = new ArrayList<>();
        TreeNode cur = root;
        while (cur != null) {
            if (cur.left != null) {//cur有左子节点：
                TreeNode pre = cur.left;
                while ((pre.right != null) && (pre.right != cur)) {//先找到cur左子节点的最右节点,记为pre
                    pre = pre.right;
                }
                if (pre.right == null) {//pre的右子节点为空，让其指向cur，cur向左移动
                    pre.right = cur;
                    cur = cur.left;
                } else {//pre的左子节点为cur,让其指向空，cur向右移动
                    pre.right = null;
                    nodes.add(cur.val);
                    cur = cur.right;
                }
            } else {//cur无左节点：cur向右移动
                nodes.add(cur.val);
                cur = cur.right;
            }
        }
        return nodes;
    }

    //TIQ-22 Iterating
    public static List<String> generateParenthesis(int n) {
        List<String> ans = new ArrayList<>();
        generateParenthesisBacktrack(ans, "", 0, 0, n);
        return ans;
    }
    public static void generateParenthesisBacktrack(List<String> ans, String cur, int open, int close, int max) {
        if (cur.length() == max * 2) {
            System.out.println("cur = " + cur + " ans = " + ans);
            ans.add(cur);
            return;
        }

        if (open < max) {
            System.out.println("open < max = " + cur + '(');
            generateParenthesisBacktrack(ans, cur + '(', open + 1, close, max);
        }
        if (close < open) {
            System.out.println("close < max = " + cur + ')');
            generateParenthesisBacktrack(ans, cur + ')', open, close + 1, max);
        }
    }

    //TIQ-206 Reverse
    public ListNode reverseList(ListNode head) {
        if (head == null || head.next == null) return head;
        ListNode p = reverseList(head.next);
        head.next.next = head;

        head.next = null;
        return p;
    }

    //TIq-412 Concentration
    public List<String> fizzBuzz(int n) {
        List<String> ans = new ArrayList<String>();

        for (int num = 1; num <= n; num++) {

            boolean dividedBy3 = (num % 3 == 0);
            boolean dividedBy5 = (num % 5 == 0);

            String conStr = "";
            if (dividedBy3) {
                conStr += "Fizz";
            }

            if (dividedBy5) {
                conStr += "Buzz";
            }

            if (!dividedBy3 && !dividedBy5) {
                conStr += Integer.toString(num);
            }
            ans.add(conStr);
        }
        return ans;

    }

    //TIQ-347 Heap
    public static int[] topKFrequent_Heap(int[] nums, int k) {
        if (k == nums.length) {
            return nums;
        }

        Map<Integer, Integer> count = new HashMap();
        for (int n: nums) {
            count.put(n, count.getOrDefault(n,0)+1);
            //getOrderDefault: 如果有key=n，则用keyd的value,如果没有key的value用defaltvalue
        }
        System.out.println("count = " + count);//count = {2=2, 3=4, 6=3, 7=3, 9=1}

        //用heap的先入后出原则，筛选K most
        //(n1, n2) -> count.get(n1) - count.get(n2):
        //比较容易判断的方法就是通过观察Comparator 比较器来判断，如果比较器定义的是 return o1 - o2 则是最小堆(升序排列)）；如果比较器定义的是 return o2 - o1 则是最大堆(降序排列)；
        Queue<Integer>heap = new PriorityQueue<>( (n1, n2) -> count.get(n1) - count.get(n2));
        System.out.println(count.keySet());
        for (int n: count.keySet()) {//keySet
            heap.add(n);
            System.out.println(n);
            System.out.println("heap.element() = " + heap.peek());
            System.out.println("heap.size() = " + heap.size());
            if (heap.size() > k) System.out.println("heap.poll = " + heap.poll());
            System.out.println("------");
        }

        //build output array
        int[] top = new int[k];
        for (int i = k - 1; i >= 0; --i) {
            top[i] = heap.poll();
        }
        return top;
    }

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

    //TIQ-238
    public int[] productExceptSelf(int[] nums) {
        int first = nums[0];
        int last = nums[nums.length-1];
        int length = nums.length;
        int[] arrL = new int[length];
        arrL[0] = 1;
        int[] arrR = new int[length];
        arrR[length-1] = 1;

        for (int i = 1; i < nums.length; i++) {
            arrL[i] = nums[i-1]*arrL[i-1];
            arrR[length-1-i] = nums[length-i] * arrR[length-i];
        }
        for (int i = 0; i < nums.length; i++) {
            arrR[i] = arrL[i] * arrR[i];
        }
        return arrR;
    }

    //TIQ-230 BST Recursive
    public static ArrayList<Integer> inorder (TreeNode root, ArrayList<Integer> arr) {//对bst按顺序排序
        if (root == null) return arr;
        inorder(root.left,arr);
        arr.add(root.val);
        inorder(root.right,arr);
        return arr;
    }
    public int kthSmallest(TreeNode root, int t) {
        ArrayList<Integer> arr = inorder(root, new ArrayList<Integer>());
        return arr.get(t-1);
    }
    //BST Iterative
    public int kthSmallest_Iterative(TreeNode root, int k) {
        LinkedList<TreeNode> stack = new LinkedList<TreeNode>();

        while (true) {
            while (root != null) {
                stack.add(root);
                root = root.left;
            }
            if (--k == 0) return root.val;
            root = root.right;
        }
    }

    //TIQ-48 Transpose + Reverse = 90% rotation
    // Reverse + Reverse = 180% rotation
    // Reverse + Transepose = 270% rotation
    public void rotate(int[][] matrix) {
        int n = matrix.length;
        for (int i = 0; i < n; i++) {
            for (int j = i; j < n; j++) {
                int temp = matrix[j][i];
                matrix[j][i] = matrix[i][j];
                matrix[i][j] = temp;
            }
        }
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                int temp = matrix[i][j];
                matrix[i][j] = matrix[i][n-j-1];
                matrix[i][n-j-1] = temp;
            }
        }
    }

    //TIQ-49
    public List<List<String>> groupAnagrams(String[] strs) {
        if (strs.length == 0) return new ArrayList();
        Map<String,List> ans = new HashMap<String, List>();
        int[] count = new int[26];
        for (String s : strs) {
            Arrays.fill(count,0);//给count所有的值赋值0
            for (char c : s.toCharArray()) count[c - 'a']++;//计数字母
            StringBuilder sb = new StringBuilder("");//给str加#
            for (int i = 0; i < 26; i++) {
                sb.append('#');
                sb.append(count[i]);
            }
            String key = sb.toString();
            if (!ans.containsKey(key)) ans.put(key,new ArrayList());
            ans.get(key).add(s);
        }
        return new ArrayList(ans.values());
    }
    public void moveZeroes(int[] nums) {
        if (nums == null || nums.length == 0 ) return;
        int j = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] != 0) {
                int temp = nums[j];
                nums[j] = nums[i];
                nums[i] = temp;
                j++;
            }
        }
    }

    //TIQ-122
    public int maxProfit (int[] prices) {
        int maxprofit = 0;
        for (int i = 1; i < prices.length; i++) {
            if (prices[i] > prices[i-1]) maxprofit = maxprofit + (prices[i] - prices[i-1]);
        }
        return maxprofit;
    }

    //TIQ-242
    public boolean isAnagram(String s, String t) {
        if (s.length() != t.length()) return false;
        int[] table = new int[26];

        for (int i = 0; i < s.length(); i++) {
            table[s.charAt(i) - 'a']++;
        }
        for (int i = 0; i < t.length(); i++) {
            table[t.charAt(i) - 'a']--;
            if (table[t.charAt(i) - 'a'] < 0) return false;
        }
        return true;
    }

    //
    public static int findDuplicate(int[] nums) {
        //find the intersection point of two runners.
        int tortoise = nums[0];
        int hare = nums[0];

        do {
            tortoise = nums[tortoise];
            hare = nums[nums[hare]];
        }while (tortoise != hare);

        //find the entrance of the cycle
        tortoise = nums[0];
        while (tortoise != hare) {
            tortoise = nums[tortoise];
            hare = nums[hare];
        }
        return hare;
    }

    //322-coin Changed Dynamic Programming
    public static int coinChange(int[] coins, int amount) {
        int max = amount + 1;
        int[] dp = new int[amount + 1];
        Arrays.fill(dp, max);
        dp[0] = 0;

        for (int i = 1; i <= amount; i++) {
            for (int coin = 0; coin < coins.length; coin++) {
                if (coins[coin] <= i) {
                    dp[i] = Math.min(dp[i], dp[i - coins[coin]] + 1);
                    System.out.println("dp[" + i + "] = " + dp[i]);
                }
            }
        }
        System.out.println(dp);
        return dp[amount] > amount ? -1 : dp[amount];
    }


}