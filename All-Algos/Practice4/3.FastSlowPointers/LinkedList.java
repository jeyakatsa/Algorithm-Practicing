public class LinkedList {
    public static void main(String[] args) {
        LinkedList head = new LinkedList(1);
        head.next = new LinkedList(2);
        head.next.next = new LinkedList(3);
        head.next.next.next = new LinkedList(4);
        head.next.next.next.next = new LinkedList(5);
        head.next.next.next.next.next = new LinkedList(6);
        System.out.println("LinkedList has cycle: " + hasCycle(head));
    
        head.next.next.next.next.next.next = head.next.next;
        System.out.println("LinkedList has cycle: " + hasCycle(head));
    
        head.next.next.next.next.next.next = head.next.next.next;
        System.out.println("LinkedList has cycle: " + hasCycle(head));
    }


    int value = 0;
    LinkedList next;
    
    LinkedList(int value) {
        this.value = value;
    }

    public static boolean hasCycle(LinkedList head) {
        LinkedList slow = head;
        LinkedList fast = head;
        while (fast != null && fast.next != null) {
          fast = fast.next.next;
          slow = slow.next;
          if (slow == fast)
            return true; // found the cycle
        }
        return false;
    }
}