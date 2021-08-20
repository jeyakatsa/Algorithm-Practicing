import java.util.*;

public class Recursion2 {

    public static void main (String[] args) {
        TreeNode head = new TreeNode(1);
        head.next = new TreeNode(4);
        head.next.next = new TreeNode(7);
        head.next.next.next = new TreeNode(10);

        TreeNode head2 = new TreeNode(2);
        head2.next = new TreeNode(3);
        head2.next.next = new TreeNode(5);
        head2.next.next.next = new TreeNode(8);

        //TreeNode result = mergeSorted(head, head2);

        //List<Integer> result2 = rightSideView(head);        
        // System.out.print("Merged Sorted List: ");
        // while(result != null) {
        //     System.out.print(result.val + " ");
        //     result = result.next;
        // }
    }

    public static class TreeNode {
        int val;
        TreeNode right;
        TreeNode left;
        TreeNode next;
        TreeNode () {}
        TreeNode (int val) {this.val = val;}
        TreeNode (int val, TreeNode right, TreeNode left, TreeNode next) {
            this.left = left;
            this.right = right;
            this.val = val;
            this.next = next;
        }
    }  
    
    //MAX DEPTH OF BINARY TREE
    public int maxDepth(TreeNode root) {
        if (root == null) {
          return 0;
        } else {
          int left_height = maxDepth(root.left);
          int right_height = maxDepth(root.right);
          return Math.max(left_height, right_height) + 1;
        }
    }


    //SWAP NODES IN PAIRS
    public TreeNode swapPairs(TreeNode head) {

        // If the list has no node or has only one node left.
        if ((head == null) || (head.next == null)) {
            return head;
        }

        // Nodes to be swapped
        TreeNode firstNode = head;
        TreeNode secondNode = head.next;

        // Swapping
        firstNode.next  = swapPairs(secondNode.next);
        secondNode.next = firstNode;

        // Now the head is the second node
        return secondNode;
    } 
    
    
    //IS PALINDROME
    public boolean isPalindrome(TreeNode head) {
        List<Integer> vals = new ArrayList<>();

        // Convert LinkedList into ArrayList.
        TreeNode currentNode = head;
        while (currentNode != null) {
            vals.add(currentNode.val);
            currentNode = currentNode.next;
        }

        // Use two-pointer technique to check for palindrome.
        int front = 0;
        int back = vals.size() - 1;
        while (front < back) {
            // Note that we must use ! .equals instead of !=
            // because we are comparing Integer, not int.
            if (!vals.get(front).equals(vals.get(back))) {
                return false;
            }
            front++;
            back--;
        }
        return true;
    } 
    
    
    //Reverse String
    public void helper(char[] s, int left, int right) {
        if (left >= right) return;
        char tmp = s[left];
        s[left++] = s[right];
        s[right--] = tmp;
        helper(s, left, right);
    }
    public void reverseString(char[] s) {
        helper(s, 0, s.length - 1);
    }  



    //Fibonacci Number  
    public int fib(int n) {
        if (n <= 1) {
            return n;
        }
        return fib(n - 1) + fib(n - 2);
        
    }

    //Different Ways to Add Parentheses
    public List<Integer> diffWaysToCompute(String input) {
        List<Integer> ret = new LinkedList<Integer>();
        for (int i=0; i<input.length(); i++) {
            if (input.charAt(i) == '-' ||
                input.charAt(i) == '*' ||
                input.charAt(i) == '+' ) {
                String part1 = input.substring(0, i);
                String part2 = input.substring(i+1);
                List<Integer> part1Ret = diffWaysToCompute(part1);
                List<Integer> part2Ret = diffWaysToCompute(part2);
                for (Integer p1 :   part1Ret) {
                    for (Integer p2 :   part2Ret) {
                        int c = 0;
                        switch (input.charAt(i)) {
                            case '+': c = p1+p2;
                                break;
                            case '-': c = p1-p2;
                                break;
                            case '*': c = p1*p2;
                                break;
                        }
                        ret.add(c);
                    }
                }
            }
        }
        if (ret.size() == 0) {
            ret.add(Integer.valueOf(input));
        }
        return ret;
    }


    //Reorder List
    public void reorderList(TreeNode head) {
        if (head == null) return;
    
        // find the middle of linked list [Problem 876]
        // in 1->2->3->4->5->6 find 4 
        TreeNode slow = head, fast = head;
        while (fast != null && fast.next != null) {
          slow = slow.next;
          fast = fast.next.next;
        }
    
        // reverse the second part of the list [Problem 206]
        // convert 1->2->3->4->5->6 into 1->2->3->4 and 6->5->4
        // reverse the second half in-place
        TreeNode prev = null, curr = slow, tmp;
        while (curr != null) {
          tmp = curr.next;
    
          curr.next = prev;
          prev = curr;
          curr = tmp;
        }
    
        // merge two sorted linked lists [Problem 21]
        // merge 1->2->3->4 and 6->5->4 into 1->6->2->5->3->4
        TreeNode first = head, second = prev;
        while (second.next != null) {
          tmp = first.next;
          first.next = second;
          first = tmp;
    
          tmp = second.next;
          second.next = first;
          second = tmp;
        }
    }    




}
