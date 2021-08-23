import java.util.*;

public class LinkedList1 {
    public static void main (String[] args) {
        Node head = new Node(1);
        head.next = new Node(2);
        head.next.next = new Node(3);

        // ListNode result = reverseList(head);
        // while(result != null) {
        //     System.out.println(result.val);
        //     result = result.next;

        // }
    }

    public static class Node {
        int val;
        Node next;
        Node prev;
        Node child;
        public int label;
        public Object random;
        Node() {}
        Node(int val) { this.val = val; }
        Node(int val, Node next) { this.val = val; this.next = next; }
        Node(int val, Node next, Node prev) { this.prev = prev; this.val = val; this.next = next; }
        Node(int _val,Node _prev,Node _next,Node _child) {
            val = _val;
            prev = _prev;
            next = _next;
            child = _child;
        }        
    }




    //Max Stack
    Stack<Integer> stack;
    Stack<Integer> maxStack;
    /** initialize your data structure here. */
    public LinkedList1() {
        stack = new Stack<>();
        maxStack = new Stack<>();
    }
    public void push(int x) {
        int max = maxStack.isEmpty() ? x : maxStack.peek();
        maxStack.push(max > x ? max : x);
        stack.push(x);
    }
    public int pop() {
        maxStack.pop();
        return stack.pop();
    }
    public int top() {
        return stack.peek();
    }
    public int peekMax() {
        return maxStack.peek();
    }
    public int popMax() {
        int max = peekMax();
        Stack<Integer> buffer = new Stack<>();
        while (top() != max) buffer.push(pop());
        pop();
        while (!buffer.isEmpty()) push(buffer.pop());
        return max;
    }
    
    
    //Design Twitter
    class Twitter {
        int time;
        Map<Integer, List<int[]>> tweetMap;
        Map<Integer, Set<Integer>> followMap;
        public Twitter() {
            time = 0;
            tweetMap = new HashMap<> ();
            followMap = new HashMap<> ();
        } 
        public void postTweet(int userId, int tweetId) {   
            ++time;
            List<int[]> list = tweetMap.getOrDefault (userId, new ArrayList<> ());
            list.add (new int[] {time, tweetId});
            tweetMap.put (userId, list);
        }
        public List<Integer> getNewsFeed(int userId) {   
            PriorityQueue<int[]> maxHeap = new PriorityQueue<> ((arr1, arr2) -> arr2[0] - arr1[0]);  
            if (tweetMap.containsKey (userId)) {
                for (int[] arr : tweetMap.get (userId)) {
                    maxHeap.offer (arr);
                }
            }
            if (followMap.containsKey (userId)) {
                for (int followee : followMap.get (userId)) {
                    if (tweetMap.containsKey (followee)) {
                        for (int[] arr : tweetMap.get (followee)) {
                            maxHeap.offer (arr);
                        }
                    }
                }
            }
            List<Integer> answer = new ArrayList<> ();
            while (!maxHeap.isEmpty () && answer.size () < 10) {
                int[] arr = maxHeap.poll ();
                answer.add (arr[1]);
            }
            return answer;
        }
        public void follow(int followerId, int followeeId) {
            Set<Integer> set = followMap.getOrDefault (followerId, new HashSet<> ());
            set.add (followeeId);
            followMap.put (followerId, set);
        }
        public void unfollow(int followerId, int followeeId) {
            Set<Integer> set = followMap.getOrDefault (followerId, new HashSet<> ());
            set.remove (followeeId);
            followMap.put (followerId, set);
        }
    }

    //Reverse Linked List II
    public Node reverseBetween(Node head, int m, int n) {
        // Empty list
        if (head == null) {
            return null;
        }
        // Move the two pointers until they reach the proper starting point
        // in the list.
        Node cur = head, prev = null;
        while (m > 1) {
            prev = cur;
            cur = cur.next;
            m--;
            n--;
        }
        // The two pointers that will fix the final connections.
        Node con = prev, tail = cur;
        // Iteratively reverse the nodes until n becomes 0.
        Node third = null;
        while (n > 0) {
            third = cur.next;
            cur.next = prev;
            prev = cur;
            cur = third;
            n--;
        }
        // Adjust the final connections as explained in the algorithm
        if (con != null) {
            con.next = prev;
        } else {
            head = prev;
        }
        tail.next = cur;
        return head;
    }



    //Design Circular Queue
    class MyCircularQueue {

        private int[] queue;
        private int headIndex;
        private int count;
        private int capacity;
      
        /** Initialize your data structure here. Set the size of the queue to be k. */
        public MyCircularQueue(int k) {
          this.capacity = k;
          this.queue = new int[k];
          this.headIndex = 0;
          this.count = 0;
        }
      
        /** Insert an element into the circular queue. Return true if the operation is successful. */
        public boolean enQueue(int value) {
          if (this.count == this.capacity)
            return false;
          this.queue[(this.headIndex + this.count) % this.capacity] = value;
          this.count += 1;
          return true;
        }
      
        /** Delete an element from the circular queue. Return true if the operation is successful. */
        public boolean deQueue() {
          if (this.count == 0)
            return false;
          this.headIndex = (this.headIndex + 1) % this.capacity;
          this.count -= 1;
          return true;
        }
      
        /** Get the front item from the queue. */
        public int Front() {
          if (this.count == 0)
            return -1;
          return this.queue[this.headIndex];
        }
      
        /** Get the last item from the queue. */
        public int Rear() {
          if (this.count == 0)
            return -1;
          int tailIndex = (this.headIndex + this.count - 1) % this.capacity;
          return this.queue[tailIndex];
        }
      
        /** Checks whether the circular queue is empty or not. */
        public boolean isEmpty() {
          return (this.count == 0);
        }
      
        /** Checks whether the circular queue is full or not. */
        public boolean isFull() {
          return (this.count == this.capacity);
        }
    }  



    //Intersection of Two Linked Lists
    public Node getIntersectionNode(Node headA, Node headB) {
        while (headA != null) {
            Node pB = headB;
            while (pB != null) {
                if (headA == pB) return headA;
                pB = pB.next;
            }
            headA = headA.next;
        }
        return null;
    }

    




    

    

}
