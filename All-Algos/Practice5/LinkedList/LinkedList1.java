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

    




    

    

}
