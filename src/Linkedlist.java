import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashSet;

public class Linkedlist {

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
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
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

    //234-Palindrome Linked List
    //Given the head of a singly linked list, return true if it is a palindrome.
    //Input: head = [1,2,2,1]
    //Output: true


    //725-Split Linked List in Parts
    //Given a (singly) linked list with head node root, write a function to split the linked list into k consecutive linked list "parts".
    //root = [1, 2, 3], k = 5
    //Output: [[1],[2],[3],[],[]]
}
