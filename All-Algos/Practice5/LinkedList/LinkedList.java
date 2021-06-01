import java.util.*;

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
        public int label;
        public Object random;
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
        ListNode dummyHead = new ListNode(0);
        ListNode p = l1, q = l2, curr = dummyHead;
        int carry = 0;
        while (p != null || q != null) {
            int x = (p != null) ? p.val : 0;
            int y = (q != null) ? q.val : 0;
            int sum = carry + x + y;
            carry = sum / 10;
            curr.next = new ListNode(sum % 10);
            curr = curr.next;
            if (p != null) p = p.next;
            if (q != null) q = q.next;
        }
        if (carry > 0) {
            curr.next = new ListNode(carry);
        }
        return dummyHead.next;
    }
    
    
    

    //ADD TWO NUMBERS II
    public ListNode addTwoNumbersTwo(ListNode l1, ListNode l2) {
        Stack<Integer> s1 = new Stack<Integer>();
        Stack<Integer> s2 = new Stack<Integer>();
        
        while(l1 != null) {
            s1.push(l1.val);
            l1 = l1.next;
        };
        while(l2 != null) {
            s2.push(l2.val);
            l2 = l2.next;
        }
        
        int sum = 0;
        ListNode list = new ListNode(0);
        while (!s1.empty() || !s2.empty()) {
            if (!s1.empty()) sum += s1.pop();
            if (!s2.empty()) sum += s2.pop();
            list.val = sum % 10;
            ListNode head = new ListNode(sum / 10);
            head.next = list;
            list = head;
            sum /= 10;
        }
        
        return list.val == 0 ? list.next : list;
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





    //RANDOM LIST
    public ListNode copyRandomList(ListNode head) {
        if (head == null)
            return null;
        
        Map<ListNode, ListNode> map =  new HashMap<ListNode, ListNode>();
    
        ListNode curNode = head;
        while(curNode != null)
        {
            map.put(curNode, new ListNode(curNode.val));
            curNode = curNode.next;
        }
        
        for(Map.Entry<ListNode, ListNode> entry : map.entrySet())  // key -> value;  node -> copy
        {
            ListNode node = entry.getValue();
            node.next = map.get(entry.getKey().next);
            node.random = map.get(entry.getKey().random);
        }
        
        return map.get(head);
    }  





    //REVERSE LINKED LIST BASED ON NODES
    public ListNode reverseBetween(ListNode head, int left, int right) {
        if (head == null || left <= 0 || right <= 0) {
            throw new IllegalArgumentException("Nope! Try again!");
        }
        //head.val == left || head.val == right
        // Move the two pointers until they reach the proper starting point
        // in the list.
        ListNode cur = head, prev = null;
        while (left > 1) {
            prev = cur;
            cur = cur.next;
            left--;
            right--;
        }
        // The two pointers that will fix the final connections.
        ListNode con = prev, tail = cur;
        // Iteratively reverse the nodes until right becomes 0.
        ListNode third = null;
        while (right > 0) {
            third = cur.next;
            cur.next = prev;
            prev = cur;
            cur = third;
            right--;
        }
        // Adjust the final connections as explained in the algorithm
        if (con != null) {
            con.next = prev;
        } else {
            head = prev;
        }
        tail.next = cur;
        return head;
    }



    //REMOVE LINKED LIST ELEMENTS (Value)
    public ListNode removeElements(ListNode head, int val) {
        ListNode sentinel = new ListNode(0);
        sentinel.next = head;
    
        ListNode prev = sentinel, curr = head;
        while (curr != null) {
          if (curr.val == val) prev.next = curr.next;
          else prev = curr;
          curr = curr.next;
        }
        return sentinel.next;
    }    


    
  
       
}