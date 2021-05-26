import java.util.*;


public class Recursion {

    public static class TreeNode {
        int val;
        TreeNode right;
        TreeNode left;
        TreeNode () {}
        TreeNode (int val) {this.val = val;}
        TreeNode (int val, TreeNode right, TreeNode left) {
            this.left = left;
            this.right = right;
            this.val = val;
        }
    }

    //RANGE SUM OF BST
    public int rangeSumBST(TreeNode root, int low, int high) {
        if (root == null) return 0;
        int sum = 0;
        if (root.val >= low && root.val <= high) sum += root.val;
        if (root.val > low) sum += rangeSumBST(root.left, low, high);
        if (root.val < high) sum += rangeSumBST(root.right, low, high);

        return sum;
    } 

    //KTH SYMBOL
    public int kthGrammar(int N, int K) {
        return Integer.bitCount(K - 1) & 1;
    }
    // think of the problem like this
    /*        0
          /       \
         0          1
       /   \      /    \
      0     1    1      0
     / \     / \   / \   / \
    0  1   1   0  1  0  0  1
    */      
}