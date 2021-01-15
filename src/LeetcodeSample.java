import java.util.*;

public class LeetcodeSample {
    public static void main(String[] args) {
        int[] nums = new int[]{7,7,7,9,6,6,6,3,3,3,3,2,2};

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

        TreeNode() {
        }

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

    //TIQ-22 Backtracking
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
    public static int[] topKFrequent_QuickSort(int[] nums, int k) {
        return null;
    }

    int[] unique;
    Map<Integer,Integer> count;

    public void swap(int a, int b) {
        int tmp = unique[a];
        unique[a] = unique[b];
        unique[b] = tmp;
    }

    public int partition(int left, int right, int pivot_index) {
        int pivot_freq = count.get(unique[pivot_index]);
        int store_index = left;
        swap(pivot_index,right);

        for (int i = left; i<=right; i++){
            if (count.get(unique[left]) < pivot_freq) {
                swap(i, store_index);
                store_index++;
            }
        }

        swap(store_index, right);
        return store_index;
    }

    public void quick_select(int left, int right, int k_smallest) {
        //if one element array input
        if (left == right) return;

        //Select a random pivot_index
        Random random_num = new Random();
        int pivot_index = left + random_num.nextInt(right-left);

        //partition
        pivot_index = partition(left,right,pivot_index);

        if (k_smallest == pivot_index) return;
        else if (k_smallest < pivot_index) quick_select(left, pivot_index-1,k_smallest);
        else if (k_smallest > pivot_index ) quick_select(pivot_index+1, right, k_smallest);
    }

    public int[] topKFrequent(int[] nums, int k) {
        //build hash map: character and how often it appears
        count = new HashMap<>();
        for (int num:nums) {
            count.put(num, count.getOrDefault(num, 0)+1);
        }

        //array of unique element
        int n = count.size();
        unique = new int[n];
        int i = 0;
        for (int num:count.keySet()) {
            unique[i] = num;
            i++;
        }

        quick_select(0,n-1,n-k);
        return Arrays.copyOfRange(unique,n-k,n);
    }


}
