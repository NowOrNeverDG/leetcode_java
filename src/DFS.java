public class DFS {
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

    //111. Minimum Depth of Binary Tree
    //Input: root = [3,9,20,null,null,15,7]
    //Output: 2
    public int minDepth(TreeNode root) {
        if (root == null) return 0;
        if (root.left == null) return minDepth(root.right)+1;
        if (root.right == null) return minDepth(root.left)+1;
        return Math.min(minDepth(root.left), minDepth(root.right)) +1;
    }
}
