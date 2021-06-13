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
        public int label;
        public Object random;
        Node() {}
        Node(int val) { this.val = val; }
        Node(int val, Node next) { this.val = val; this.next = next; }
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
    

    

}
