public class LinkedList {
  public static void main(String[] args) {
    LinkedList head = new LinkedList(1);
    head.next = new LinkedList(2);
    head.next.next = new LinkedList(3);
    head.next.next.next = new LinkedList(4);
    head.next.next.next.next = new LinkedList(5);
    head.next.next.next.next.next = new LinkedList(6);
    System.out.println("LinkedList cycle start: " + findCycleStart(head).value);

    head.next.next.next.next.next.next = head.next.next;
    System.out.println("LinkedList cycle start: " + findCycleStart(head).value);

    head.next.next.next.next.next.next = head.next.next.next;
    System.out.println("LinkedList cycle start: " + findCycleStart(head).value);
  }


  int value = 0;
  LinkedList next;
  
  LinkedList(int value) {
    this.value = value;
  }

  //Check if List has cycle
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

  //Start of LinkedList Cycle...
  public static LinkedList findCycleStart(LinkedList head) {
    int cycleLength = 0;
    // find the LinkedList cycle
    LinkedList slow = head;
    LinkedList fast = head;
    while (fast != null && fast.next != null) {
      fast = fast.next.next;
      slow = slow.next;
      if (slow == fast) { // found the cycle
        cycleLength = calculateCycleLength(slow);
        break;
      }
    }

    return findStart(head, cycleLength);
  }    
  private static int calculateCycleLength(LinkedList slow) {
    LinkedList current = slow;
    int cycleLength = 0;
    do {
        current = current.next;
        cycleLength++;
    } while (current != slow);
    
    return cycleLength;
  }    
  private static LinkedList findStart(LinkedList head, int cycleLength) {
    LinkedList pointer1 = head, pointer2 = head;
    // move pointer2 ahead 'cycleLength' nodes
    while (cycleLength > 0) {
      pointer2 = pointer2.next;
      cycleLength--;
    }

    // increment both pointers until they meet at the start of the cycle
    while (pointer1 != pointer2) {
      pointer1 = pointer1.next;
      pointer2 = pointer2.next;
    }

    return pointer1;
  }

  //Happy Number
}