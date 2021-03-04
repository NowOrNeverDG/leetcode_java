import java.util.LinkedList;
import java.util.Queue;

public class BFS {
    public static void main(String[] args) {

    }
    public class ListNode {
        int val;
        LeetcodeSample.ListNode next;

        ListNode() {
        }

        ListNode(int val) {
            this.val = val;
        }

        ListNode(int val, LeetcodeSample.ListNode next) {
            this.val = val;
            this.next = next;
        }
    }
    public static class TreeNode {
        int val;
        LeetcodeSample.TreeNode left;
        LeetcodeSample.TreeNode right;
        TreeNode() {}
        TreeNode(int val) {
            this.val = val;
        }
        TreeNode(int val, LeetcodeSample.TreeNode left, LeetcodeSample.TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }

    //111. Minimum Depth of Binary Tree
    //Input: root = [3,9,20,null,null,15,7]
    //Output: 2
    public int minDepth(TreeNode root) {
        if (root == null) return 0;

        Queue<LeetcodeSample.TreeNode> q = new LinkedList<>();
        q.offer(root);
        int depth = 1;

        while (!q.isEmpty()) {
            for (int i = 0; i < q.size(); i++) {
                LeetcodeSample.TreeNode cur = q.poll();
                //判断是否到达终点
                if (cur.left == null && cur.right == null) return depth;
                if (cur.left != null) q.offer(cur.left);
                if (cur.right != null) q.offer(cur.right);


            }
        }
    }
}
