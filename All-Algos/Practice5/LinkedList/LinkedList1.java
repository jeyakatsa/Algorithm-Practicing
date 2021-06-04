import java.util.*;

public class LinkedList1 {
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

    

}
