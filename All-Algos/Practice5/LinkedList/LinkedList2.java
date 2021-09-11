public class LinkedList2 {

    public static class ListNode {
        int val;
        ListNode next;
        ListNode prev;
        ListNode child;
        ListNode left;
        ListNode right;
        public int label;
        public Object random;
        ListNode() {}
        ListNode(ListNode left, ListNode right){
            this.left = left;
            this.right = right;
        }
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

    //Flatten Binary Tree to LinkedList
    public void flatten(ListNode root) {
        // Handle the null scenario
        if (root == null) {
            return;
        }
        ListNode node = root;
        while (node != null) {
            // If the node has a left child
            if (node.left != null) {
                // Find the rightmost node
                ListNode rightmost = node.left;
                while (rightmost.right != null) {
                    rightmost = rightmost.right;
                }
                // rewire the connections
                rightmost.right = node.right;
                node.right = node.left;
                node.left = null;
            }
            // move on to the right side of the tree
            node = node.right;
        }
    }

    //Linked List Cycle
    public boolean hasCycle(ListNode head) {
        if (head == null) {
            return false;
        }

        ListNode slow = head;
        ListNode fast = head.next;
        while (slow != fast) {
            if (fast == null || fast.next == null) {
                return false;
            }
            slow = slow.next;
            fast = fast.next.next;
        }
        return true;
    }    


    
}
