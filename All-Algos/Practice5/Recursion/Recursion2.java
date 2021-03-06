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


}
