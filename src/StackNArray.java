import java.util.*;

public class StackNArray {

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

        //225-Implement Stack using Queues
        //Implement a last in first out (LIFO) stack using only two queues. The implemented stack should support all the functions of a normal queue (push, top, pop, and empty).
        //Input:["MyStack", "push", "push", "top", "pop", "empty"]
        //[[], [1], [2], [], [], []]
        //Output:[null, null, null, 2, 2, false]
        class MyStack {
            Queue<Integer> in = new LinkedList<Integer>();
            Queue<Integer> out = new LinkedList<Integer>();
            /** Initialize your data structure here. */
            public MyStack() { }
            /** Push element x onto stack. */
            public void push(int x) {
                in.add(x);
            }
            /** Removes the element on top of the stack and returns that element. */
            public int pop() {
                if (out.isEmpty()) in2out();
                return out.remove();
            }

            /** Get the top element. */
            public int top() {
                if (out.isEmpty()) in2out();
                return out.peek();
            }

            /** Returns whether the stack is empty. */
            public boolean empty() {
                if (in.isEmpty()&&out.isEmpty()) return true;
                return false;
            }

            public void in2out() {
                while (!in.isEmpty()) {
                    out.add(in.remove());
                }
            }
        }
    }


}
