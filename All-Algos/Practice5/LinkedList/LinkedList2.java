public class LinkedList2 {

    public static class ListNode {
        int val;
        ListNode next;
        ListNode prev;
        ListNode child;
        public int label;
        public Object random;
        ListNode() {}
        ListNode(int val) { this.val = val; }
        ListNode(int val, ListNode next) { this.val = val; this.next = next; }
        ListNode(int val, ListNode next, ListNode prev) { this.prev = prev; this.val = val; this.next = next; }
        ListNode(int _val, ListNode _prev, ListNode _next, ListNode _child) {
            val = _val;
            prev = _prev;
            next = _next;
            child = _child;
        }        
    }

    //Odd Even Linked List
    public ListNode oddEvenList(ListNode head) {
        if (head == null) return null;
        ListNode odd = head, even = head.next, evenHead = even;
        while (even != null && even.next != null) {
            odd.next = even.next;
            odd = odd.next;
            even.next = odd.next;
            even = even.next;
        }
        odd.next = evenHead;
        return head;
    }    


    
}
