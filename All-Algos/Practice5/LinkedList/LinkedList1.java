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




    //Insert into a Sorted Circular Linked List
    public Node insert(Node head, int insertVal) {
        if (head == null) {
          Node newNode = new Node(insertVal, null);
          newNode.next = newNode;
          return newNode;
        }
    
        Node prev = head;
        Node curr = head.next;
        boolean toInsert = false;
    
        do {
          if (prev.val <= insertVal && insertVal <= curr.val) {
            // Case 1).
            toInsert = true;
          } else if (prev.val > curr.val) {
            // Case 2).
            if (insertVal >= prev.val || insertVal <= curr.val)
              toInsert = true;
          }
    
          if (toInsert) {
            prev.next = new Node(insertVal, curr);
            return head;
          }
    
          prev = curr;
          curr = curr.next;
        } while (prev != head);
    
        // Case 3).
        prev.next = new Node(insertVal, curr);
        return head;
    }  
    
    


    //Flatten a Multilevel Doubly Linked List
    public Node flatten(Node head) {
        if (head == null) return head;
        // pseudo head to ensure the `prev` pointer is never none
        Node pseudoHead = new Node(0, null, head, null);

        flattenDFS(pseudoHead, head);

        // detach the pseudo head from the real head
        pseudoHead.next.prev = null;
        return pseudoHead.next;
    }
    /* return the tail of the flatten list */
    public Node flattenDFS(Node prev, Node curr) {
        if (curr == null) return prev;
        curr.prev = prev;
        prev.next = curr;

        // the curr.next would be tempered in the recursive function
        Node tempNext = curr.next;

        Node tail = flattenDFS(curr, curr.child);
        curr.child = null;

        return flattenDFS(tail, tempNext);
    }   




    

    

}
