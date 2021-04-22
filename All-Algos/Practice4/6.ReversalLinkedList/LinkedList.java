class ListNode {
  int value = 0;
  ListNode next;

  ListNode(int value) {
      this.value = value;
  }
}

public class LinkedList {
  public static ListNode reverse(ListNode head) {
    if(head == null) {
        throw new IllegalArgumentException("No Numbers/Integers Available!");
    }

    ListNode current = head;
    ListNode previous = null;
    ListNode next = null;
    
    while (current != null) {
        next = current.next;
        current.next = previous;
        previous = current;
        current = next;
    }
      
    return previous;    
  }

  public static ListNode reverseSub(ListNode head, int p, int q) {
    if (p == q)
      return head;

    // after skipping 'p-1' nodes, current will point to 'p'th node
    ListNode current = head, previous = null;
    for (int i = 0; current != null && i < p - 1; ++i) {
      previous = current;
      current = current.next;
    }

    // we are interested in three parts of the LinkedList, part before index 'p', part between 'p' and 
    // 'q', and the part after index 'q'
    ListNode lastNodeOfFirstPart = previous; // points to the node at index 'p-1'
    // after reversing the LinkedList 'current' will become the last node of the sub-list
    ListNode lastNodeOfSubList = current;
    ListNode next = null; // will be used to temporarily store the next node
    // reverse nodes between 'p' and 'q'
    for (int i = 0; current != null && i < q - p + 1; i++) {
      next = current.next;
      current.next = previous;
      previous = current;
      current = next;
    }

    // connect with the first part
    if (lastNodeOfFirstPart != null)
      lastNodeOfFirstPart.next = previous; // 'previous' is now the first node of the sub-list
    else // this means p == 1 i.e., we are changing the first node (head) of the LinkedList
      head = previous;

    // connect with the last part
    lastNodeOfSubList.next = current;

    return head;
  }

  //Reverse K,
  public static ListNode reverseK(ListNode head, int k) {
    if (k <= 1 || head == null)
      return head;

    ListNode current = head, previous = null;
    while (true) {
      ListNode lastNodeOfPreviousPart = previous;
      // after reversing the LinkedList 'current' will become the last node of the sub-list
      ListNode lastNodeOfSubList = current;
      ListNode next = null; // will be used to temporarily store the next node
      // reverse 'k' nodes
      for (int i = 0; current != null && i < k; i++) {
        next = current.next;
        current.next = previous;
        previous = current;
        current = next;
      }

      // connect with the previous part
      if (lastNodeOfPreviousPart != null)
        lastNodeOfPreviousPart.next = previous; // 'previous' is now the first node of the sub-list
      else // this means we are changing the first node (head) of the LinkedList
        head = previous;

      // connect with the next part
      lastNodeOfSubList.next = current;

      if (current == null) // break, if we've reached the end of the LinkedList
        break;
      // prepare for the next sub-list
      previous = lastNodeOfSubList;
    }

    return head;
  }

  //Reverse every other K element.
  public static ListNode reverseOtherK(ListNode head, int k) {
    if (head == null || k <= 1){
      return head;
    }

    ListNode current = head;
    ListNode previous = null;

    while (current != null) {

      ListNode lastNodeOfPreviousPart = previous;
      ListNode lastNodeOfSubList = current;
      ListNode next = null;

      for (int i = 0; current != null && i < k; i++) {
        next = current.next;
        current.next = previous;
        previous = current;
        current = next;
      }

      if(lastNodeOfPreviousPart != null) {
        lastNodeOfPreviousPart.next = previous;
      }
      else {
        head = previous;
      }

      lastNodeOfSubList.next = current;

      for (int i = 0; current != null && i < k; ++i) {
        previous = current;
        current = current.next;
      }
    }

    return head;
  }

  //Rotate LinkedList

  public static ListNode rotate(ListNode head, int rotations) {
    if (head == null || head.next == null || rotations <= 0) {
      return head;
    }

    // find the length and the last node of the list
    ListNode lastNode = head;
    int listLength = 1;
    while (lastNode.next != null) {
      lastNode = lastNode.next;
      listLength++;
    }

    lastNode.next = head; // connect the last node with the head to make it a circular list
    rotations %= listLength; // no need to do rotations more than the length of the list
    int skipLength = listLength - rotations;
    ListNode lastNodeOfRotatedList = head;
    for (int i = 0; i < skipLength - 1; i++)
      lastNodeOfRotatedList = lastNodeOfRotatedList.next;

    // 'lastNodeOfRotatedList.next' is pointing to the sub-list of 'k' ending nodes
    head = lastNodeOfRotatedList.next;
    lastNodeOfRotatedList.next = null;
    return head;
  }

  public static void main(String[] args) {
    ListNode head = new ListNode(1);
    head.next = new ListNode(2);
    head.next.next = new ListNode(3);
    head.next.next.next = new ListNode(4);
    head.next.next.next.next = new ListNode(5);
    head.next.next.next.next.next = new ListNode(6);
    head.next.next.next.next.next.next = new ListNode(7);
    head.next.next.next.next.next.next.next = new ListNode(8);

    // ListNode result = reverse(head);
    
    // ListNode result2 = reverseSub(head, 2, 4);

    ListNode result3 = rotate(head, 2);
    System.out.print("Nodes of the reversed LinkedList are: ");
    while(result3 != null) {
        System.out.print(result3.value + " ");
        result3 = result3.next;
    }
  }



}