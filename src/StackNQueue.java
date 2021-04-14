import java.util.*;
public class StackNQueue {

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

    //155-Min Stack
    //Design a stack that supports push, pop, top, and retrieving the minimum element in constant time.
    //Input:["MinStack","push","push","push","getMin","pop","top","getMin"]
    //[[],[-2],[0],[-3],[],[],[],[]]
    //Output: [null,null,null,null,-3,null,0,-2]
    class MinStack {
        Deque<Integer> stack = new ArrayDeque<Integer>();
        Deque<Integer> minStack = new ArrayDeque<Integer>();

        /** initialize your data structure here. */
        public MinStack() {}

        public void push(int val) {
            stack.push(val);
            if (minStack.isEmpty()) minStack.push(val);
            else if (minStack.peek() >= val) minStack.push(val);
        }

        public void pop() {
            if (stack.peek().equals(minStack.peek())) minStack.pop();
            stack.pop();
        }

        public int top() {
            return stack.peek();
        }

        public int getMin() {
            return minStack.peek();
        }
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

}
