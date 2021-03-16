import com.sun.source.tree.Tree;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

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

    //111. Minimum Depth of Binary Tree
    //Input: root = [3,9,20,null,null,15,7]
    //Output: 2
    public int minDepth(TreeNode root) {
        if (root == null) return 0;
        Queue<TreeNode> q = new LinkedList<TreeNode>();
        q.offer(root);
        int depth = 1;
        while (!q.isEmpty()) {
            System.out.println(q.size());
            //当前队列的所有节点向四周扩散
            int size = q.size();
            for (int i = 0; i < size; i++) {
                TreeNode cur = q.poll();
                if (cur.left == null && cur.right == null) return depth;//判断是否到达终点
                //将cur的相邻节点假如队列
                if (cur.left != null) q.offer(cur.left);
                if (cur.right != null) q.offer(cur.right);
            }
            depth++;
        }
        return -1;//unreachable
    }

    //752 - Open the lock（2-end BFS）
    //Input: deadends = ["8888"], target = "0009"
    //Output: 1
    public int openLock(String[] deadends, String target) {
        Set<String> deads = new HashSet<>();
        for (String s : deadends) deads.add(s);

        Set<String> q1 = new HashSet<>();
        Set<String> q2 = new HashSet<>();
        Set<String> visited = new HashSet<>();

        int step = 0;
        q1.add("0000");
        q2.add(target);

        while (!q1.isEmpty() && !q2.isEmpty()) {
            Set<String> temp = new HashSet<>();

            for (String cur : q1) {
                if (deads.contains(cur)) continue;
                if (q2.contains(cur)) return step;
                visited.add(cur);

                for (int j = 0; j < 4; j++) {
                    String up = plusone(cur, j);
                    if (!visited.contains(up)) temp.add(up);
                    String down = minusOne(cur, j);
                    if (!visited.contains(down)) temp.add(down);
                }
            }
            step++;
            //扩散换人
            q1 = q2;
            q2 = temp;
        }
        return -1;
    }
    private String plusone(String s, int j) {
        char[] ch = s.toCharArray();
        if (ch[j] == '9') {
            ch[j] = '0';
        } else {
            ch[j]++;
        }
        return new String(ch);
    }
    private String minusOne(String s, int j) {
        char[] ch = s.toCharArray();
        if (ch[j] == '0') ch[j] = '9';
        else ch[j]--;
        return new String(ch);
    }
    private int openLockBFS(String[] deadends, String target) {
        Set<String> deads = new HashSet<String>();
        for (String dead : deadends) deads.add(dead);
        int step = 0;
        Queue<String> q = new LinkedList<String>();
        q.add("0000");
        Set<String> visited = new HashSet<String>();
        visited.add("0000");
        while (!q.isEmpty()) {
            int size = q.size();
            for (int i = 0; i < size; i++) {
                String cur = q.poll();
                if (deads.contains(cur)) continue;
                if (cur.equals(target)) return step;

                for (int j = 0; j < 4; j++) {
                    String up = plusone(cur, j);
                    if (!visited.contains(up)) {
                        visited.add(up);
                        q.offer(up);
                    }
                    String down = minusOne(cur, j);
                    if (!visited.contains(down)) {
                        visited.add(down);
                        q.offer(down);
                    }
                }
            }
            step++;
        }
        return -1;
    }
}
