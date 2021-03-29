import com.sun.source.tree.Tree;

public class TypicalTree {
    public static void main(String[] args) {

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

    //104-Maximum Depth of Binary Tree
    //Input: root = [3,9,20,null,null,15,7]
    //Output: 3
    public int maxDepth(TreeNode root) {
        if (root == null) return 0;
        return Math.max(maxDepth(root.left),maxDepth(root.right))+1;
    }

    //110-Balanced Binary Tree
    //Input: root = [3,9,20,null,null,15,7]
    //Output: true
    private boolean res110 = true;
    public boolean isBalanced(TreeNode root) {
        maxDepth110(root);
        return res110;
    }
    private int maxDepth110(TreeNode root) {
        if (root == null) return 0;
        int left = maxDepth110(root.left);
        int right = maxDepth110(root.right);
        if (Math.abs(right-left) > 1) res110 = false;
        return 1+Math.max(left,right);
    }

    //543-Diameter of Binary Tree
    //Input: root = [1,2,3,4,5]
    //Output: 3
    //Explanation: 3is the length of the path [4,2,1,3] or [5,2,1,3].
    private int res543 = 0;
    public int diameterOfBinaryTree(TreeNode root) {
        helper543(root);
        return res543;
    }
    private int helper543(TreeNode root) {
        if (root == null) return 0;
        int left = helper543(root.left);
        int right = helper543(root.right);
        res543 = Math.max(res543,left+right);
        return Math.max(left,right)+1;
    }

    //226-Invert Binary Tree
    //Input: root = [4,2,7,1,3,6,9]
    //Output: [4,7,2,9,6,3,1]
    public TreeNode invertTree(TreeNode root) {
        if (root == null) return null;
        TreeNode left = root.left;
        root.left = invertTree(root.right);
        root.right = invertTree(left);
        return root;
    }

    //617-Merge Two Binary Trees
    //Input: root1 = [1,3,2,5], root2 = [2,1,3,null,4,null,7]
    //Output: [3,4,5,5,4,null,7]
    public TreeNode mergeTrees(TreeNode root1, TreeNode root2) {
        if (root1 == null && root2 == null) return null;
        if (root1 == null) return root2;
        if (root2 == null) return root1;
        TreeNode root = new TreeNode(root1.val+root2.val);
        root.left = mergeTrees(root1.left,root2.left);
        root.right = mergeTrees(root1.right, root2.right);
        return root;
    }

    //112-Path Sum
    public boolean hasPathSum(TreeNode root, int targetSum) {
        if (root == null) return false;
        if (root.left == null && root.right == null && root.val == targetSum) return true;
        return hasPathSum(root.left, targetSum - root.val) || hasPathSum(root.right, targetSum - root.val);
    }

}

