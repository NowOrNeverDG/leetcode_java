public class PointedOffer {
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

    //offer22
    //输入一个链表，输出该链表中倒数第k个节点。为了符合大多数人的习惯，本题从1开始计数，即链表的尾节点是倒数第1个节点。
    //给定一个链表: 1->2->3->4->5, 和 k = 2.
    //返回链表 4->5.
    public ListNode getKthFromEnd(ListNode head, int k) {
        ListNode left = head;
        ListNode right = head;
        for (int i = 0; i< k; i++) { right = right.next; }
        while (right.next != null) {
            left = left.next;
            right = right.next;
        }
        return left;
    }

    //offer10
    //一只青蛙一次可以跳上1级台阶，也可以跳上2级台阶。求该青蛙跳上一个 n 级的台阶总共有多少种跳法。
    //输入：n = 7
    //输出：21
    public int numWays(int n) {
        if (n == 0) return 1;
        if (n == 1) return 1;

        int second = 1;
        int first = 1;
        for (int i = 2; i<n; i++) {
            int firstTmp = second;
            second += first;
            first = second;

        }
        return second;
    }



}
