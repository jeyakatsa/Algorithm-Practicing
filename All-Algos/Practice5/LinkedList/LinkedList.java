import java.util.*;

public class LinkedList {
    public static void main (String[] args) {
        ListNode head = new ListNode(1);
        head.next = new ListNode(2);
        head.next.next = new ListNode(3);

        // ListNode result = reverseList(head);
        // while(result != null) {
        //     System.out.println(result.val);
        //     result = result.next;

        // }
    }

    public static class ListNode {
        int val;
        ListNode next;
        public int label;
        public Object random;
        ListNode() {}
        ListNode(int val) { this.val = val; }
        ListNode(int val, ListNode next) { this.val = val; this.next = next; }
    }


    //LRU Cache
    class LRUCache extends LinkedHashMap<Integer, Integer>{
        private int capacity;
        
        public LRUCache(int capacity) {
            super(capacity, 0.75F, true);
            this.capacity = capacity;
        }
    
        public int get(int key) {
            return super.getOrDefault(key, -1);
        }
    
        public void put(int key, int value) {
            super.put(key, value);
        }
    
        @Override
        protected boolean removeEldestEntry(Map.Entry<Integer, Integer> eldest) {
            return size() > capacity; 
        }
    }  
    
    
    //Design HashMap
    class MyHashMap {
        int[] data;
        public MyHashMap() {
            data = new int[1000001];
            Arrays.fill(data, -1);
        }
        public void put(int key, int val) {
            data[key] = val;
        }
        public int get(int key) {
            return data[key];
        }
        public void remove(int key) {
            data[key] = -1;
        }
    }


   



    
  
       
}