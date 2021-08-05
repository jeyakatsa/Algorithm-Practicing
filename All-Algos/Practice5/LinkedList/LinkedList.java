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
        public ListNode random;
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


    //Copy List with Random Pointer
    // HashMap which holds old nodes as keys and new nodes as its values.
    HashMap<ListNode, ListNode> visitedHash = new HashMap<ListNode, ListNode>();
    public ListNode copyRandomList(ListNode head) {

        if (head == null) {
        return null;
        }

        // If we have already processed the current node, then we simply return the cloned version of
        // it.
        if (this.visitedHash.containsKey(head)) {
        return this.visitedHash.get(head);
        }

        // Create a new node with the value same as old node. (i.e. copy the node)
        ListNode node = new ListNode(head.val, null);

        // Save this value in the hash map. This is needed since there might be
        // loops during traversal due to randomness of random pointers and this would help us avoid
        // them.
        this.visitedHash.put(head, node);

        // Recursively copy the remaining linked list starting once from the next pointer and then from
        // the random pointer.
        // Thus we have two independent recursive calls.
        // Finally we update the next and random pointers for the new node created.
        node.next = this.copyRandomList(head.next);
        node.random = this.copyRandomList(head.random);

        return node;
    }



   



    
  
       
}