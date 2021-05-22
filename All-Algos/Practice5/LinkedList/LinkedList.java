public class LinkedList {
    public static void main (String[] args) {
        ListNode head = new ListNode(1);
        head.next = new ListNode(2);
        head.next.next = new ListNode(3);

        ListNode result = reverseList(head);
        while(result != null) {
            System.out.println(result.val);
            result = result.next;

        }
    }

    public static class ListNode {
        int val;
        ListNode next;
        ListNode() {}
        ListNode(int val) { this.val = val; }
        ListNode(int val, ListNode next) { this.val = val; this.next = next; }
    }


    //IS PALINDROME?
    public static boolean isPalindrome(ListNode head) {
        /*
        Approach: Use dual pointer method to get the middle of the linked list. The idea is, have a slow pointer and a fast
                  pointer, and every time move the slow pointer by 1 step, and the fast pointer by 2 steps. By the time the 
                  fast pointer reaches the end of the list, the slow pointer will reach the middle of the list.
                  Also, while we're finding the middle, reverse the slow pointer nodes, essentially reversing the first half
                  of the linked list.
                  Now have two pointers at the middle of the linked list pointing to each half. For linked list with even
                  length like [2,4,5,7] these will start at 4 and 5. For odd length lists like [2,4,5,7,8], these will start
                  at 4 and 7, skipping the middle element.
                  Now traverse both the halves and compare the values.
                  P.S. Here we are modifying the list (next nodes) by reversing the first half to have O(1) space complexity.
                  We could restore the list to its original state by again reversing the first half while comparing with second
                  half, if the problem statement asked us to do so.
                  
        Complexity analysis: Time: O(n), Space: O(1)          
        */  
        if(head==null || head.next==null) return true;   
        //  Reverse the first half
        ListNode prevSlowNode = null;
        ListNode slowNode = head;
        ListNode fastNode = head;
        while(fastNode!=null && fastNode.next !=null){
            fastNode = fastNode.next.next;                                      //Move fast pointer
            //Reverse
            ListNode nextSlowNode = slowNode.next;                              //Store next node in a variable
            slowNode.next = prevSlowNode;                                       //Point current node to prev node
            prevSlowNode = slowNode;                                            //Move prev node to next (curr) node
            slowNode = nextSlowNode;                                            //Move curr node to next node
        }
        //  Find the pointers to the two halves
        ListNode firstHalfPointer = prevSlowNode;                               //Point to prevSlowNode, like like 4 in
                                                                                //[2,4,5,7] or [2,4,5,7,8]
        ListNode secondHalfPointer = null;
        if(fastNode==null){                                                     //List is of even length, like [2,4,5,7]
            secondHalfPointer = slowNode;                                       //Point to slowNode, like 5 in [2,4,5,7]
        }
        else if(fastNode.next==null){                                           //List is of odd length, like [2,4,5,7,8]
            secondHalfPointer = slowNode.next;                                  //Point to slowNode.next, like 7 in [2,4,5,7,8]
        }
        //  Compare the two halves
        while(firstHalfPointer!=null && secondHalfPointer!=null){
            if(firstHalfPointer.val != secondHalfPointer.val){
                return false;
            }
            firstHalfPointer = firstHalfPointer.next;
            secondHalfPointer = secondHalfPointer.next;
        }
        return true;
    }





    //REVERSE LINKEDLIST
    public static ListNode reverseList(ListNode head) {
        ListNode prev = null;
        ListNode curr = head;
        while (curr != null) {
            ListNode nextTemp = curr.next;
            curr.next = prev;
            prev = curr;
            curr = nextTemp;
        }
        return prev;
    }   
    
    

    //ADD TWO NUMBERS
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        // We will use sizes to understand which list's nodes should be frozen for a while.
        int s1 = size(l1);
        int s2 = size(l2);
        ListNode resHead = null;
        ListNode n = null;
        while (l1 != null || l2 != null) {
            int v1 = 0;
            int v2 = 0;
            if (s1 >= s2) {
                v1 = l1 != null ? l1.val : 0;
                l1 = l1.next;
                s1--;
            }
            // Comparing with s1 + 1 since s1 might be decremented previously
            if (s2 >= s1 + 1) {
                v2 = l2 != null ? l2.val : 0;
                l2 = l2.next;
                s2--;
            }
            // Creating the resulting list in the reversed order.
            n = new ListNode(v1 + v2);
            n.next = resHead;
            resHead = n;
        }
        int carry = 0;
        resHead = null;
        // Now, let's perform the normalization.
        while (n != null) {
            n.val += carry;
            if (n.val >= 10) {
                n.val = n.val % 10;
                carry = 1;
            } else {
                carry = 0;
            }
            ListNode buf = n.next;
            n.next = resHead;
            resHead = n;
            n = buf;
        }
        if (carry > 0) {
            n = new ListNode(1);
            n.next = resHead;
            resHead = n;
        }
        return resHead;
    }
    private int size(ListNode l) {
        int s = 0;
        while (l != null) {
            l = l.next;
            s++;
        }
        return s;
    }  
    
    
    //MERGE TWO SORTED LISTS
    public ListNode mergeTwoLists(ListNode l1, ListNode l2){
		if(l1 == null) return l2;
		if(l2 == null) return l1;
		if(l1.val < l2.val){
			l1.next = mergeTwoLists(l1.next, l2);
			return l1;
		} else{
			l2.next = mergeTwoLists(l1, l2.next);
			return l2;
		}
    }    
       
}