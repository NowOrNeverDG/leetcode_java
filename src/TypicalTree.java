import com.sun.source.tree.Tree;

import javax.swing.*;
import java.sql.ClientInfoStatus;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

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
    //Input: root = [1,2,3], targetSum = 5
    //Output: false
    public boolean hasPathSum(TreeNode root, int targetSum) {
        if (root == null) return false;
        if (root.left == null&&root.right == null&&targetSum == root.val) return true;

        boolean left = hasPathSum(root.left, targetSum - root.val);
        boolean right = hasPathSum(root.right, targetSum - root.val);
        return left||right;
    }

    //114-Flatten Binary Tree to Linked List
    //Input: root = [1,2,5,3,4,null,6]
    //Output: [1,null,2,null,3,null,4,null,5,null,6]
    public void flatten(LeetcodeSample.TreeNode root) {
        if(root == null) return;

        flatten(root.right);
        flatten(root.left);

        LeetcodeSample.TreeNode left = root.left;
        LeetcodeSample.TreeNode right = root.right;

        root.right = root.left;
        root.left = null;

        LeetcodeSample.TreeNode temp = root;
        while (temp.right != null) {
            temp = temp.right;
        }
        temp.right = right;
    }

    //116-Populating Next Right Pointers in Each Node
    //Input: root = [1,2,3,4,5,6,7]
    //Output: [1,#,2,3,#,4,5,6,7,#]
    public LeetcodeSample.Node connect(LeetcodeSample.Node root) {
        if (root == null) return  null;
        connectTwoNode(root.left,root.right);
        return root;
    }
    private void connectTwoNode(LeetcodeSample.Node node1, LeetcodeSample.Node node2) {
        if (node1 == null || node2 == null) { return; }
        node1.next = node2;
        connectTwoNode(node1.left,node1.right);
        connectTwoNode(node2.left,node2.right);
        connectTwoNode(node1.right,node2.left);
    }

    //572-Subtree of Another Tree
    public boolean isSubtree(TreeNode s, TreeNode t) {
        if (s == null) return false;
        return helper572(s,t)||isSubtree(s.left,t)||isSubtree(s.right,t);
    }
    public boolean helper572(TreeNode s, TreeNode t) {
        if (s== null&&t==null) return true;
        if (s==null ||t==null) return false;
        if (s.val != t.val) return false;
        return helper572(s.left,t.left)&&helper572(s.right,t.right);
    }

    //101-Symmetric Tree
    //Input: root = [1,2,2,3,4,4,3]
    //Output: true
    public boolean isSymmetric(TreeNode root) {
        if (root == null) return true;
        return helper101(root.left,root.right);
    }
    public boolean helper101(TreeNode left, TreeNode right) {
        if (left == null&&right==null) return true;
        if (left == null||right==null) return false;
        if (left.val != right.val) return false;
        return helper101(left.left,left.right)&&helper101(right.left,right.right);
    }

    //111-Minimum Depth of Binary Tree
    public int minDepth3(TreeNode root) {
        if (root == null) return 0;
        int left = minDepth3(root.left);
        int right = minDepth3(root.right);
        if (left == 0 || right == 0) return left+right+1;
        return Math.min(left,right)+1;
    }

    //404-Sum of Left Leaves
    public int sumOfLeftLeaves(TreeNode root) {
        if (root == null) return 0;
        if (isLeaf404(root.left)) return root.left.val + sumOfLeftLeaves(root.right);
        return sumOfLeftLeaves(root.right) + sumOfLeftLeaves(root.left);
    }
    private boolean isLeaf404(TreeNode root) {
        if (root == null) return false;
        if (root.left == null&&root.right == null) return true;
        return false;
    }

    //687-Longest Univalue Path
    //Input: root = [5,4,5,1,1,5]
    //Output: 2
    public int longestUnivaluePath(TreeNode root) {
        if (root == null) return 0;
        return Math.max(helper687(root.left),helper687(root.right));
    }
    public int helper687(TreeNode root) {
        if (root == null) return 0;
        if (root.val == root.left.val) return helper687(root.left)+1;
        if (root.val == root.right.val) return helper687(root.right)+1;

        List<String> list = new ArrayList<String>();
        list.add("rose");
        list.add("pop");
        return Math.max(helper687(root.left),helper687(root.right));

    }

    public static class Builder {
        private String name;
        private String address;
        private String contact;
        private int sizeOfEmployee;
        private Date createdTime;

        public void Builder() {}
        public Builder name(String name) { map.put("name",this.name); return this;}
        public Builder address(String address) { map.put("address", this.address);return this;}
        public Builder createdTime(Date createdTime) { map.put("createdTime", this.createdTime);return this;}
        public Builder sizeOfEmployee(int sizeOfEmployee) { map.put("createdTime", this.createdTime);return this;}
        public Builder contact(String contract) {map.put("contract", this.contact);return this;}

    }

    private Company(Builder builder) {
        this.name = builder.name;
        this.address = builder.address;
        this.createdTime = builder.createdTime;
        this.sizeOfEmployee = builder.sizeOfEmployee;
        this.contact = builder.contact;
    }
    public String build() {
        return new Company(this).toString;
    }


}

