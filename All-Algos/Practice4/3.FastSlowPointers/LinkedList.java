public class LinkedList {
  public static void main(String[] args) {
    LinkedList head = new LinkedList(1);
    head.next = new LinkedList(2);
    head.next.next = new LinkedList(3);
    head.next.next.next = new LinkedList(4);
    head.next.next.next.next = new LinkedList(5);
    head.next.next.next.next.next = new LinkedList(6);
    System.out.println("Is Palindrome? " +  isPalindrome(head));

    // System.out.println("LinkedList cycle start: " + findCycleStart(head).value);

    // head.next.next.next.next.next.next = head.next.next;
    // System.out.println("LinkedList cycle start: " + findCycleStart(head).value);

    // head.next.next.next.next.next.next = head.next.next.next;
    // System.out.println("LinkedList cycle start: " + findCycleStart(head).value);

    reorder(head);
    while (head != null) {
      System.out.print(head.value + " ");
      head = head.next;
    }
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

  //find middle
  public static LinkedList findMiddle(LinkedList head) {
    if (head == null) {
      throw new IllegalArgumentException("No Value in List");
    }

    LinkedList slow = head;
    LinkedList fast = head;

    while (fast != null && fast.next !=null) {
      slow = slow.next;
      fast = fast.next.next;
    }

    return slow;
  }

  //Palindrome
  public static boolean isPalindrome(LinkedList head) {
    if (head == null || head.next == null)
      return true;

    // find middle of the LinkedList
    LinkedList slow = head;
    LinkedList fast = head;
    while (fast != null && fast.next != null) {
      slow = slow.next;
      fast = fast.next.next;
    }

    LinkedList headSecondHalf = reverse(slow); // reverse the second half
    LinkedList copyHeadSecondHalf = headSecondHalf; // store the head of reversed part to revert back later

    // compare the first and the second half
    while (head != null && headSecondHalf != null) {
      if (head.value != headSecondHalf.value) {
        break; // not a palindrome
      }
      head = head.next;
      headSecondHalf = headSecondHalf.next;
    }

    reverse(copyHeadSecondHalf); // revert the reverse of the second half
    if (head == null || headSecondHalf == null) // if both halves match
      return true;
    return false;
  }
  private static LinkedList reverse(LinkedList head) {
    LinkedList prev = null;
    while (head != null) {
      LinkedList next = head.next;
      head.next = prev;
      prev = head;
      head = next;
    }
    return prev;
  }

  //Rearrange LinkedList
  public static void reorder(LinkedList head) {
    if (head == null || head.next == null)
      return;

    // find the middle of the LinkedList
    LinkedList slow = head, fast = head;
    while (fast != null && fast.next != null) {
      slow = slow.next;
      fast = fast.next.next;
    }

    // slow is now pointing to the middle node
    LinkedList headSecondHalf = reversed(slow); // reverse the second half
    LinkedList headFirstHalf = head;

    // rearrange to produce the LinkedList in the required order
    while (headFirstHalf != null && headSecondHalf != null) {
      LinkedList temp = headFirstHalf.next;
      headFirstHalf.next = headSecondHalf;
      headFirstHalf = temp;

      temp = headSecondHalf.next;
      headSecondHalf.next = headFirstHalf;
      headSecondHalf = temp;
    }
    // set the next of the last node to 'null'
    if (headFirstHalf != null)
      headFirstHalf.next = null;
  }
  private static LinkedList reversed(LinkedList head) {
    LinkedList prev = null;
    while (head != null) {
      LinkedList next = head.next;
      head.next = prev;
      prev = head;
      head = next;
    }
    return prev;
  }


}