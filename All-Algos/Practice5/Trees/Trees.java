import java.util.*;

public class Trees {
    public static void main (String[] args) {
        TreeNode root = new TreeNode(2);
        root.left = new TreeNode(3);
        root.right = new TreeNode(4);

        // invertTree(root);

        while (root != null) {
            System.out.print(root.val + " " + root.left.val + " " + root.right.val);
            break;
        }

    }



    public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode() {}
        TreeNode(int val) { this.val = val; }
        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }

    //Count Good Nodes in Binary Tree
    private int numGoodNodes = 0;
    public int goodNodes(TreeNode root) {
        dfs(root, Integer.MIN_VALUE);
        return numGoodNodes;
    }
    private void dfs(TreeNode node, int maxSoFar) {
        if (maxSoFar <= node.val) {
            numGoodNodes++;
        }
        
        if (node.right != null) {
            dfs(node.right, Math.max(node.val, maxSoFar));
        }

        if (node.left != null) {
            dfs(node.left, Math.max(node.val, maxSoFar));
        }
    }

    //Flatten Nested List Iterator   
    // public class NestedIterator implements Iterator<Integer> {
        
    //     private List<Integer> integers = new ArrayList<Integer>();
    //     private int position = 0; // Pointer to next integer to return.
        
    //     public NestedIterator(List<Integer> nestedList) {
    //         flattenList(nestedList);
    //     }

    //     // Recursively unpacks a nested list in DFS order.
    //     private void flattenList(List<Integer> nestedList) {
    //         for (Integer nestedInteger : nestedList) {
    //             if (nestedInteger.isInteger()) {
    //                 integers.add(nestedInteger.getInteger());
    //             } else {
    //                 flattenList(nestedInteger.getList());
    //             }
    //         }
    //     }
        
    //     @Override
    //     public Integer next() {
    //         // As per Java specs, we should throw an exception if no more ints.
    //         if (!hasNext()) throw new NoSuchElementException();
    //         // Return int at current position, and then *after*, increment position.
    //         return integers.get(position++);
    //     }

    //     @Override
    //     public boolean hasNext() {
    //         return position < integers.size();
    //     }
    // }  


    



    


      



}
