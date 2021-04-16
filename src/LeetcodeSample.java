import com.sun.source.tree.Tree;

import javax.print.DocFlavor;
import java.lang.reflect.Array;
import java.util.*;

import static java.lang.Math.max;
import static java.lang.Math.min;

public class LeetcodeSample {

    public static void main(String[] args) {
        int[] nums1 = new int[]{4,1,2};
        int[] nums2 = new int[]{1,3,4,2};
        Deque<Integer> deque = new ArrayDeque<>();
    }

    public class ListNode {
        int val;
        ListNode next;
        ListNode() {}
        ListNode(int val) {
            this.val = val;
        }
        ListNode(int val, ListNode next) {
            this.val = val;
            this.next = next;
        }
    }
    public class TreeNode {
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
    public class Node {
        public int val;
        public Node left;
        public Node right;
        public Node next;

        public Node() {}

        public Node(int _val) {
            val = _val;
        }

        public Node(int _val, Node _left, Node _right, Node _next) {
            val = _val;
            left = _left;
            right = _right;
            next = _next;
        }
    };


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

    //122
    public int maxProfit (int[] prices) {
        int maxprofit = 0;
        for (int i = 1; i < prices.length; i++) {
            if (prices[i] > prices[i-1]) maxprofit = maxprofit + (prices[i] - prices[i-1]);
        }
        return maxprofit;
    }

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

    //287-Floyd's algorithm
//    public static int findDuplicate(int[] nums) {
//        //find the intersection point of two runners.
//        int tortoise = nums[0];
//        int hare = nums[0];
//
//        do {
//            tortoise = nums[tortoise];
//            hare = nums[nums[hare]];
//        }while (tortoise != hare);
//
//        //find the entrance of the cycle
//        tortoise = nums[0];
//        while (tortoise != hare) {
//            tortoise = nums[tortoise];
//            hare = nums[hare];
//        }
//        return hare;
//    }

    //322-coin Changed Dynamic Programming
    //You are given an integer array coins representing coins of different denominations and an integer amount representing a total amount of money.
    //Return the fewest number of coins that you need to make up that amount. If that amount of money cannot be made up by any combination of the coins, return -1.
    //Input: coins = [1,2,5], amount = 11
    //Output: 3
    //Explanation: 11 = 5 + 5 + 1
    public static int coinChange(int[] coins, int amount) {
        int max = amount + 1;
        int[] dp = new int[amount + 1];
        Arrays.fill(dp, max);
        dp[0] = 0;

        for (int i = 1; i <= amount; i++) {
            for (int coin = 0; coin < coins.length; coin++) {
                if (coins[coin] <= i) {
                    dp[i] = Math.min(dp[i], dp[i - coins[coin]] + 1);
                }
            }
        }
        System.out.println(dp);
        return dp[amount] > amount ? -1 : dp[amount];
    }

    //1-two sum
    //Given an array of integers nums and an integer target, return indices of the two numbers such that they add up to target.
    //Input: nums = [2,7,11,15], target = 9 只有一解
    //Output: [0,1]
    public static int[] twoSum(int[] nums, int target) {
        Map<Integer,Integer> id_index = new HashMap<>();
        int[] res = new int[2];
        for (int i = 0; i < nums.length; i++) {
            if (!id_index.containsKey(target - nums[i])) id_index.put(nums[i],i);
            else {
                res[0] = id_index.get(target - nums[i]);
                res[1] = i;
                return res;
            }
        }
        return res;
    }
    //1-two sum
    //nums = [1,1,1,2,2,3], target = 4 可有多解
    //Output: [[1,3],[2,2]]
    public static List<List<Integer>> twoSumTarget(int[] nums, int target) {
        Arrays.sort(nums);
        int left = 0, right = nums.length-1;
        List<List<Integer>> res = new ArrayList<>();
        while (left < right) {
           int low = nums[left], high = nums[right];
           if (low + high > target) high--;
           else if (low + high < target) low++;
           else {
                List<Integer> ans = new ArrayList<>();
                ans.add(low);
                ans.add(high);
                res.add(ans);
                while (left < right && low == nums[left]) left++;
                while (left < right && high == nums[right]) right--;
            }
        }
        return res;
    }

    //15-3sum
    //Given an integer array nums, return all the triplets [nums[i], nums[j], nums[k]] such that i != j, i != k, and j != k, and nums[i] + nums[j] + nums[k] == 0.
    //Input: nums = [-1,0,1,2,-1,-4]
    //Output: [[-1,-1,2],[-1,0,1]]
    private List<List<Integer>> twoSumTargetFor3Sum(int[] nums, int target) {
        Arrays.sort(nums);
        int left = 0, right = nums.length-1;
        List<List<Integer>> res = new ArrayList<>();
        while (left < right) {
            int low = nums[left], high = nums[right];
            if (low + high > target) high--;
            else if (low + high < target) low++;
            else {
                List<Integer> ans = new ArrayList<>();
                ans.add(low);
                ans.add(high);
                res.add(ans);
                while (left < right && low == nums[left]) left++;
                while (left < right && high == nums[right]) right--;
            }
        }
        return res;
    }
    public List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        for (int i = 0; i < nums.length; i++) {
            int target = 0 - nums[i];
            List<List<Integer>> single_res = twoSumTarget(nums, target);//[-1,2][0,1]
            if (!single_res.isEmpty()) {
                List<Integer> temp = new ArrayList<>();
                for (int j = 0; j < single_res.size(); j++) {
                    temp = single_res.get(j);
                    temp.add(nums[i]);
                }
                res.add(temp);
            }
        }
        return res;
    }

    //116-Populating Next Right Pointers in Each Node
    //You are given a perfect binary tree where all leaves are on the same level, and every parent has two children. The binary tree has the following definition:
    //Populate each next pointer to point to its next right node. If there is no next right node, the next pointer should be set to NULL.
    public Node connect(Node root) {
        if (root == null) return  null;
        connectTwoNode(root.left,root.right);
        return root;
    }
    private void connectTwoNode(Node node1, Node node2) {
        if (node1 == null || node2 == null) { return; }
        node1.next = node2;
        connectTwoNode(node1.left,node1.right);
        connectTwoNode(node2.left,node2.right);
        connectTwoNode(node1.right,node2.left);
    }

    //54. Spiral Matrix
    //Given an m x n matrix, return all elements of the matrix in spiral order.
    //Input: matrix = [[1,2,3],[4,5,6],[7,8,9]]
    //Output: [1,2,3,6,9,8,7,4,5]
    public List<Integer> spiralOrder(int[][] matrix) {
        int row = matrix.length;
        int column = matrix[0].length;
        List<Integer> ans = new ArrayList<Integer>();
        int rowBegin = 0;
        int rowEnd = row -1;
        int columnBegin = 0;
        int columnEnd = column -1;
        while(rowBegin<=rowEnd&& columnBegin<=columnEnd) {
            for (int i = columnBegin; i<= columnEnd&&ans.size()<row*column; i++) {ans.add(matrix[rowBegin][i]);}
            rowBegin++;
            for (int i = rowBegin; i <= rowEnd&&ans.size()<row*column; i++) {ans.add(matrix[i][columnEnd]);}
            columnEnd--;
            for (int i = columnEnd; i >= columnBegin&&ans.size()<row*column; i--) {ans.add(matrix[rowEnd][i]);}
            rowEnd--;
            for (int i = rowEnd; i >= rowBegin&&ans.size()<row*column; i--) {ans.add(matrix[i][columnBegin]);}
            columnBegin++;
        }
        return ans;
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

    //496-Next Greater Element I
    //You are given two integer arrays nums1 and nums2 both of unique elements, where nums1 is a subset of nums2.
    //Find all the next greater numbers for nums1's elements in the corresponding places of nums2.
    //The Next Greater Number of a number x in nums1 is the first greater number to its right in nums2. If it does not exist, return -1 for this number.
    //Input: nums1 = [4,1,2], nums2 = [1,3,4,2]
    //Output: [-1,3,-1]
    public int[] nextGreaterElement(int[] nums1, int[] nums2) {
        int[] ans = new int[nums1.length];
        for (int i = 0; i<nums1.length;i++) {
            int key = -1;
            boolean swh = false;
            for (int j = 0; j< nums2.length;j++) {
                if (nums2[j] == nums1[i]) swh = true;
                if (swh == true) {
                    if (nums2[j] > nums1[i]) {key = nums2[j]; break;}
                }
            }
            ans[i] = key;
        }
        return ans;
    }
    public static int[] nextGreaterElementI(int[] nums1, int[] nums2) {
        int n = nums2.length;
        Deque<Integer> stack = new ArrayDeque<Integer>();
        Map<Integer,Integer> map = new HashMap<>();

        for (int i = n-1; i>=0;i--) {
            while (!stack.isEmpty()&&nums2[i]>stack.peek()){
                stack.pop();
            }
            map.put(nums2[i],stack.isEmpty()? -1:stack.peek());
            stack.push(nums2[i]);
        }
        int[] ans = new int[nums1.length];
        for (int i = 0; i < nums1.length; i++) {
            ans[i] = map.get(nums1[i]);
        }
        return ans;
    }

    //5-Longest Palindromic Substring
    //Given a string s, return the longest palindromic substring in s.
    //Input: s = "babad"
    //Output: "bab"
    private int lo5, maxLen5;
    public String longestPalindrome(String s) {
        int len = s.length();
        if (len < 2) return s;
        String res = "";
        for (int i = 0; i < len - 1; i++) {
            String s1 = palindrome5(s, i, i);
            String s2 = palindrome5(s, i, i + 1);

            res = res.length() > s1.length() ? res : s1;
            res = res.length() > s2.length() ? res : s2;
        }
        return res;
    }
    private String palindrome5(String s, int j,int k) {
        while (j>=0&&k<s.length()&&s.charAt(j)==s.charAt(k)){
            j--;
            k++;
        }
        return s.substring(j,k+1);
    }

    //409-Longest Palindrome
    //Given a string s which consists of lowercase or uppercase letters, return the length of the longest palindrome that can be built with those letters.
    //Input: s = "abccccdd"
    //Output: 7
    public int longestPalindrome1(String s) {
        HashSet<Character> set = new HashSet();
        int count = 0;
        for (int i=0; i<s.length();i++) {
            if (set.contains(s.charAt(i)) ) {
                set.remove(s.charAt(i));
                count++;
            }else {
                set.add(s.charAt(i));
            }
        }
        return set.isEmpty()? count*2:count*2+1;
    }

    //125-Valid Palindrome
    //Given a string s, determine if it is a palindrome, considering only alphanumeric characters and ignoring cases.
    //Input: s = "A man, a plan, a canal: Panama"
    //Output: true
    public boolean isPalindrome(String s) {
        int left = 0, right = s.length()-1;
        char[] c_str = s.toCharArray();

        while (left<right) {
            if (!Character.isLetterOrDigit(c_str[left])) left++;
            else if (!Character.isLetterOrDigit(c_str[right])) right--;
            else {
                if (Character.toLowerCase(c_str[left]) != Character.toLowerCase(c_str[right])) return false;
                left++;
                right--;
            }
        }
        return true;
    }

    //516-Longest Palindromic Subsequence
    //Given a string s, find the longest palindromic subsequence's length in s.
    //A subsequence is a sequence that can be derived from another sequence by deleting some or no elements without changing the order of the remaining elements.
    public int longestPalindromeSubseq(String s) {
        int[][] dp = new int[s.length()][s.length()];
        int left = 0, right = s.length()-1;
        dp[left][right] = 1;
        while (left<right) {
            if (s.charAt(left) == s.charAt(right)) {
                dp[left++][right--] = dp[left][right]+2;
            }
        }
        return 0;
    }

    //2-Add Two Numbers
    //You are given two non-empty linked lists representing two non-negative integers. The digits are stored in reverse order, and each of their nodes contains a single digit. Add the two numbers and return the sum as a linked list.
    //Input: l1 = [2,4,3], l2 = [5,6,4]
    //Output: [7,0,8]
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode listNode = new ListNode(0);
        int carry = 0;
        ListNode cur = listNode;
        while (l1!=null||l2!=null||carry!=0) {

            int v1 = l1 == null ? 0:l1.val;
            int v2 = l2 == null ? 0:l2.val;
            if (l1 != null) l1 = l1.next;
            if (l2 != null) l2 = l2.next;

            int value = carry + v1 + v2;
            carry = value/10;
            cur.next = new ListNode(value%10);
            cur = cur.next;
        }
        return listNode.next;
    }

    //24. Swap Nodes in Pairs
    //Given a linked list, swap every two adjacent nodes and return its head.
    //Input: head = [1,2,3,4]
    //Output: [2,1,4,3]
    public ListNode swapPairs(ListNode head) {
        if (head == null ||head.next == null) return head;
        ListNode next = head.next;
        head.next  = swapPairs(head.next.next);
        next.next = head;
        return next;
    }
    public ListNode swapPairs1(ListNode head) {
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        ListNode cur = dummy;

        while (cur.next != null&&cur.next.next != null) {
            ListNode pre = cur.next;
            ListNode nex = cur.next.next;
            cur.next = nex;
            pre.next = nex.next;
            nex.next = pre;
            cur = pre;
        }
        return dummy.next;
    }

    //206-Reverse Linked List
    //Given the head of a singly linked list, reverse the list, and return the reversed list.
    //Input: head = [1,2,3,4,5]
    //Output: [5,4,3,2,1]
    public ListNode reverseList(ListNode head) {
            if (head == null ||head.next == null) return head;

            ListNode lastNode = reverseList(head.next);
            lastNode.next = head;
            head.next = null;
            return lastNode;
    }
    public ListNode reverseList2(ListNode head) {
        ListNode pre = null;
        ListNode cur = head;
        while (cur != null) {
            ListNode temp = cur.next;
            cur.next = pre;
            pre = cur;
            cur = temp;
        }
        return pre;
    }

    //19. Remove Nth Node From End of List
    //Given the head of a linked list, remove the nth node from the end of the list and return its head.
    //Input: head = [1,2,3,4,5], n = 2
    //Output: [1,2,3,5]
    public ListNode removeNthFromEnd(ListNode head, int n) {
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        ListNode left = dummy;
        ListNode right = dummy;
        for (int i = 0; i< n+1; i++) {  right = right.next; }
        while (right.next != null) {
            right = right.next;
            left = left.next;
        }
        left.next = right;
        return dummy.next;
    }

    //160-Intersection of Two Linked Lists
    //Given the heads of two singly linked-lists headA and headB, return the node at which the two lists intersect. If the two linked lists have no intersection at all, return null.
    //Input: intersectVal = 8, listA = [4,1,8,4,5], listB = [5,6,1,8,4,5], skipA = 2, skipB = 3
    //Output: Intersected at '8'
    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        Deque<ListNode> stack = new ArrayDeque<>();
        while (headA != null) {
            stack.push(headA);
            headA = headA.next;
        }
        while (headB != null) {
            if (stack.contains(headB)) return headB;
            headB = headB.next;
        }
        return null;
    }
    public ListNode getIntersectionNode1(ListNode headA, ListNode headB) {
        ListNode p1 = headA;
        ListNode p2 = headB;
        while (p1 != p2) {
            p1 = p1 == null ? headB : p1.next;
            p2 = p2 == null ? headA : p2.next;
        }
        return p1;
    }

    //21-Merge Two Sorted Lists
    //Merge two sorted linked lists and return it as a sorted list. The list should be made by splicing together the nodes of the first two lists.
    //Input: l1 = [1,2,4], l2 = [1,3,4]
    //Output: [1,1,2,3,4,4]
    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        if (l1 == null) return l2;
        if (l2 == null) return l1;
        if (l1.val < l2.val) {
            l1.next = mergeTwoLists(l1.next, l2);
            return l1;
        } else {
            l2.next = mergeTwoLists(l1, l2.next);
            return l2;
        }
    }

    //83-Remove Duplicates from Sorted List
    //Given the head of a sorted linked list, delete all duplicates such that each element appears only once. Return the linked list sorted as well.
    //Input: head = [1,1,2]
    //Output: [1,2]
    public ListNode deleteDuplicates(ListNode head) {
        ListNode cur = head;
        while (cur != null && cur.next != null) {
            if (cur.val == cur.next.val) {
                cur.next = cur.next.next;
            } else {
                cur = cur.next;
            }
        }
        return  head;
    }

    //445-Add Two Numbers II
    //You are given two non-empty linked lists representing two non-negative integers. The most significant digit comes first and each of their nodes contain a single digit. Add the two numbers and return it as a linked list.
    //Input: (7 -> 2 -> 4 -> 3) + (5 -> 6 -> 4)
    //Output: 7 -> 8 -> 0 -> 7
    public ListNode addTwoNumbers1(ListNode l1, ListNode l2) {
        Deque stack1 = new ArrayDeque();
        Deque stack2 = new ArrayDeque();

        while (l1 != null) {
            stack1.push(l1.val);
            l1 = l1.next;
        }
        while (l2 != null) {
            stack2.push(l2.val);
            l2 = l2.next;
        }
        int carry = 0;
        Deque stack = new ArrayDeque();
        while (!(stack1.isEmpty()&&stack2.isEmpty()&&carry == 0)) {
            int s1 = stack1.isEmpty()? 0: (int) stack1.pop();
            int s2 = stack2.isEmpty()? 0: (int) stack2.pop();
            int sum = s1+s2+carry;
            carry = sum/10;
            int val = sum%10;
            stack.push(val);
        }
        ListNode dummy = new ListNode();
        ListNode cur = dummy;
        while (!stack.isEmpty()) {
            ListNode node = new ListNode();
            node.val = (int)stack.pop();
            cur.next = node;
            cur = node;
        }
        return dummy.next;
    }

    //1436-Destination City
    //You are given the array paths, where paths[i] = [cityAi, cityBi] means there exists a direct path going from cityAi to cityBi. Return the destination city, that is, the city without any path outgoing to another city.
    //Input: paths = [["London","New York"],["New York","Lima"],["Lima","Sao Paulo"]]
    //Output: "Sao Paulo"
    public String destCity(List<List<String>> paths) {
        HashSet<String> set = new HashSet<String>();
        for (List<String> list : paths) {
            set.add(list.get(1));
        }
        for (List<String> list : paths) {
            set.remove(list.get(0));
        }
        return set.iterator().next();
    }

    //20-Valid Parentheses
    //Given a string s containing just the characters '(', ')', '{', '}', '[' and ']', determine if the input string is valid.
    //Input: s = "()[]{}"
    //Output: true
    public boolean isValid(String s) {
        char[] arr = s.toCharArray();
        Deque stack = new ArrayDeque();
        for (char c: arr) {
            if (c == '['||c == '{'||c == '(')  stack.push(c);
            else if (stack.isEmpty()) return false;
            else {
                if (c == ']') { if (!stack.peek().equals('[')) { return false;} else stack.pop(); }
                if (c == '}') { if (!stack.peek().equals('{')) { return false;} else stack.pop(); }
                if (c == ')') { if (!stack.peek().equals('(')) { return false;} else stack.pop(); }
            }
        }
        if (!stack.isEmpty()) return false;
        return true;
    }

    //739-Daily Temperatures
    //Given a list of daily temperatures T, return a list such that, for each day in the input, tells you how many days you would have to wait until a warmer temperature. If there is no future day for which this is possible, put 0 instead.
    public static int[] dailyTemperatures(int[] T) {
        Deque<Integer> stack = new ArrayDeque<Integer>();
        int[] ans = new int[T.length];
        for (int i = T.length-1; i>=0; i--) {

            while (!stack.isEmpty()&&T[i] >= T[stack.peek()]) stack.pop();
            ans[i] = stack.isEmpty()? 0:stack.peek()-i;
            stack.push(i);
        }
        return ans;
    }

    //451-Sort Characters By Frequency
    //Given a string, sort it in decreasing order based on the frequency of characters.
    //敏捷开发，abstract class final？, error和exception，abstract class和interface
    public String frequencySort(String s) {
        HashMap<Character,Integer> counts = new HashMap<Character, Integer>();
        for (char c: s.toCharArray()) {
            counts.put(c,counts.getOrDefault(c, 0)+1);
        }
        List<Character>[] bucket = new List[s.length()+1];
        bucket[0] = null;
        for (char c : counts.keySet()) {
            int freq = counts.get(c);
            if (bucket[freq] == null)  bucket[freq] = new ArrayList<Character>();
            bucket[freq].add(c);
        }
        StringBuilder str = new StringBuilder();
        for (int i = bucket.length-1; i>=0;i--) {
            if (bucket[i] == null) continue;
            for (char c : bucket[i]) {
                for (int j = 0; j < i; j++)  str.append(c);
            }
        }
        return  str.toString();
    }

    //205-Isomorphic Strings
    //Given two strings s and t, determine if they are isomorphic.
    //Input: s = "egg", t = "add"
    //Output: true
    public boolean isIsomorphic(String s, String t) {
        int[] arr = new int[512];
        for (int i = 0; i< arr.length; i++) arr[i] = 0;
        for (int i = 0; i < s.length(); i++) {
            System.out.println(arr[s.charAt(i)] + " / " +  arr[t.charAt(i)+256]);
            if (arr[s.charAt(i)] != arr[t.charAt(i)+256]) return false;
            arr[i] += 1;
            arr[i+256] += 1;
        }
        return true;
    }

    //696-Count Binary Substrings
    //Give a string s, count the number of non-empty (contiguous) substrings that have the same number of 0's and 1's, and all the 0's and all the 1's in these substrings are grouped consecutively.
    //Input: "00110011"
    //Output: 6
    public int countBinarySubstrings(String s) {
        int[] counts = new int[s.length()];
        int t = 0;
        counts[0] = 1;
        for (int i = 1; i < s.length();i++) {
            if (s.charAt(i-1) == s.charAt(i)) counts[t]++;
            else counts[++t] = 1;
        }
        int ans = 0;
        for (int i = 1; i <= t; i++) ans += Math.min(counts[i],counts[i-1]);
        return ans;
    }

    //647-Palindromic Substrings
    //Given a string, your task is to count how many palindromic substrings in this string.
    //Input: "abc"
    //Output: 3
    int count647 = 0;
    public int countSubstrings(String s) {
        for (int i = 0; i < s.length(); i++) {
            isPalindromic647(s,i, i);
            isPalindromic647(s, i, i+1);
        }
        return count647;
    }
    public void isPalindromic647(String str, int left, int right) {
        char[] arr = str.toCharArray();
        while (left>=0&&right<arr.length&&arr[left] == arr[right]) {
            count647 += 1;
            left--;
            right++;
        }
    }

    //9-Palindrome Number
    //Given an integer x, return true if x is palindrome integer.
    //Input: x = 121
    //Output: true
    public boolean isPalindrome(int x) {
        String x_str = String.valueOf(x);
        System.out.println(x_str.length());
        Boolean isOdd = x_str.length()%2==0 ? false:true;
        System.out.println(isOdd);
        int left = 0, right = 0;
        if (isOdd == true) {left = x_str.length()/2; right = left;}
        else {left = x_str.length()/2-1; right = x_str.length()/2;}

        return isPalindromic9(x_str,left,right);
    }
    public boolean isPalindromic9(String str, int left, int right){
        char[] arr = str.toCharArray();
        while (left>=0&&right<arr.length) {
            if (arr[left] != arr[right]) return false;
            left--;
            right++;
        }
        return true;
    }
    public boolean isPalindrome1(int x) {
        if (x < 0) return false;
        int y = x;
        int rev = 0;
        while (y != 0) {
            rev = rev*10 + x%10;
            y = y/10;
        }
        return y == x;
    }

    //503-Next Greater Element II
    //Given a circular integer array nums (i.e., the next element of nums[nums.length - 1] is nums[0]), return the next greater number for every element in nums.
    //Input: nums = [1,2,3,4,3]
    //Output: [2,3,4,-1,4]
    public int[] nextGreaterElements(int[] nums) {
        int[] ans = new int[nums.length];
        Deque<Integer> stack = new ArrayDeque<>();
        for (int i = nums.length-1; i>=0; i--) {
            while (!stack.isEmpty()&&nums[stack.peek()] <= nums[i]) {
                stack.pop();
            }
            ans[i] = stack.isEmpty() ? -1 : nums[stack.peek()];
            stack.push(i);
        }
        for (int i = nums.length-1; i>=0; i--) {
            while (!stack.isEmpty()&&nums[stack.peek()] <= nums[i]) {
                stack.pop();
            }
            ans[i] = stack.isEmpty() ? -1 : nums[stack.peek()];
            stack.push(i);
        }
        return ans;
    }

    //252-Meeting Rooms
    //Given an array of meeting time intervals where intervals[i] = [starti, endi], determine if a person could attend all meetings.
    //Input: intervals = [[0,30],[5,10],[15,20]]
    //Output: false
    public boolean canAttendMeetings(int[][] intervals) {
        Arrays.sort(intervals, (a,b) -> Integer.compare(a[0],b[0]));
        for (int i = 0; i < intervals.length-1;i++) {
            if (intervals[i][1] > intervals[i+1][0]) return false;
        }
        return true;
    }


    //232- Implement Queue using Stacks
    //Implement a first in first out (FIFO) queue using only two stacks. The implemented queue should support all the functions of a normal queue (push, peek, pop, and empty).
    //Input
    //["MyQueue", "push", "push", "peek", "pop", "empty"]
    //[[], [1], [2], [], [], []]
    //Output
    //[null, null, null, 1, 1, false]
    class MyQueue {
        private Stack<Integer> in = new Stack<>();
        private Stack<Integer> out = new Stack<>();
        /** Initialize your data structure here. */
        public MyQueue() {}
        /** Push element x to the back of queue. */
        public void push(int x) {
            in.push(x);
        }
        /** Removes the element from in front of queue and returns that element. */
        public int pop() {
            if (out.isEmpty()) in2out();
            return out.pop();
        }

        /** Get the front element. */
        public int peek() {
            if (out.isEmpty()) in2out();
            return out.peek();
        }

        /** Returns whether the queue is empty. */
        public boolean empty() {
            if (in.isEmpty()&&out.isEmpty()) return true;
            return false;
        }

        public void in2out() {
            while (!in.isEmpty()) {
                out.push(in.pop());
            }
        }
    }

    //225-Implement Stack using Queues
    //Implement a last in first out (LIFO) stack using only two queues. The implemented stack should support all the functions of a normal queue (push, top, pop, and empty).
    //Input:["MyStack", "push", "push", "top", "pop", "empty"]
    //[[], [1], [2], [], [], []]
    //Output:[null, null, null, 2, 2, false]
    class MyStack {
        Queue<Integer> q = new LinkedList<Integer>();
        /** Initialize your data structure here. */
        public MyStack() {}
        /** Push element x onto stack. */
        public void push(int x) {
            q.add(x);
            for (int i = 1; i < q.size(); i++) {
                q.add(q.remove());
            }
        }
        /** Removes the element on top of the stack and returns that element. */
        public int pop() {
            return q.remove();
        }
        /** Get the top element. */
        public int top() {
            return q.peek();
        }
        /** Returns whether the stack is empty. */
        public boolean empty() {
            return q.isEmpty();
        }
    }

    //283-Move Zeroes
    //Given an integer array nums, move all 0's to the end of it while maintaining the relative order of the non-zero elements.
    //Input: nums = [0,1,0,3,12]
    //Output: [1,3,12,0,0]
    public void moveZeroes1(int[] nums) {
        int left = 0; int right = 1;
        while (right<nums.length) {
            if (nums[right] != 0)right++;
            else {
                while (nums[left] != 0 && left < right) left++;
                int num = nums[right];
                nums[right] = nums[left];
                nums[left] = num;
            }
        }
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

    //378-Kth Smallest Element in a Sorted Matrix
    //Given an n x n matrix where each of the rows and columns are sorted in ascending order, return the kth smallest element in the matrix
    //Input: matrix = [[1,5,9],[10,11,13],[12,13,15]], k = 8
    //Output: 13
    public int kthSmallest(int[][] matrix, int k) {
        int r = matrix.length;
        int c = matrix[0].length;
        int[] arr = new int[c*r];
        for (int i = 0; i<r; i++) {
            for (int j = 0; j < c; j++) {
                arr[i*c+j] = matrix[i][j];
            }
        }
        Arrays.sort(arr);
        return arr[k-1];
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
        HashMap<Integer,Integer> map = new HashMap<Integer, Integer>();
        for (int i = 0; i < nums.length; i++) {
            int n = nums[i];
            map.put(n,map.getOrDefault(n,0)+1);
        }

        for (int i = 0; i < map.size(); i++) {
            
        }



    }




    //645-Set Mismatch


    //455-Assign Cookies
    //Assume you are an awesome parent and want to give your children some cookies. But, you should give each child at most one cookie.
    //Each child i has a greed factor g[i], which is the minimum size of a cookie that the child will be content with; and each cookie j has a size s[j]. If s[j] >= g[i], we can assign the cookie j to the child i, and the child i will be content. Your goal is to maximize the number of your content children and output the maximum number.
    //Input: g = [1,2,3], s = [1,1]
    //Output: 1
    public int findContentChildren(int[] g, int[] s) {
        return 1;
    }

    //435-Non-overlapping Intervals
    //Given an array of intervals intervals where intervals[i] = [starti, endi], return the minimum number of intervals you need to remove to make the rest of the intervals non-overlapping.
    //Input: intervals = [[1,2],[2,3],[3,4],[1,3]]
    //Output: 1

    //394-Decode String
    //Given an encoded string, return its decoded string.
    //The encoding rule is: k[encoded_string], where the encoded_string inside the square brackets is being repeated exactly k times. Note that k is guaranteed to be a positive integer.
    //You may assume that the input string is always valid; No extra white spaces, square brackets are well-formed, etc.
    //Furthermore, you may assume that the original data does not contain any digits and that digits are only for those repeat numbers, k. For example, there won't be input like 3a or 2[4].
    //Input: s = "3[a]2[bc]"
    //Output: "aaabcbc"
}