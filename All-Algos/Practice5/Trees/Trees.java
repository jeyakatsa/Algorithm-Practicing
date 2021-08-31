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
        TreeNode parent;
        TreeNode() {}
        TreeNode(int val) { this.val = val; }
        TreeNode(int val, TreeNode left, TreeNode right, TreeNode parent) {
            this.val = val;
            this.left = left;
            this.right = right;
            this.parent = parent;
        }
    }

    //Binary Tree Right Side Search
    public List<Integer> rightSideView(TreeNode root) {
        if (root == null) return new ArrayList<Integer>();
        
        ArrayDeque<TreeNode> nextLevel = new ArrayDeque<>() {{ offer(root); }};
        ArrayDeque<TreeNode> currLevel = new ArrayDeque<>();        
        List<Integer> rightside = new ArrayList<>();
        
        TreeNode node = null;
        while (!nextLevel.isEmpty()) {
            // prepare for the next level
            currLevel = nextLevel.clone();
            nextLevel.clear();

            while (! currLevel.isEmpty()) {
                node = currLevel.poll();

                // add child nodes of the current level
                // in the queue for the next level
                if (node.left != null) 
                    nextLevel.offer(node.left);    
                if (node.right != null) 
                    nextLevel.offer(node.right);
            }
            
            // The current level is finished.
            // Its last element is the rightmost one.
            if (currLevel.isEmpty()) 
                rightside.add(node.val);    
        }
        return rightside;
    }    


    //Lowest Common Ancestor of a Binary Tree III
    public TreeNode lowestCommonAncestor(TreeNode p, TreeNode q) {
        TreeNode p1 = p, p2 = q;
        while (p1 != p2) {
            p1 = p1 == null ? q : p1.parent;
            p2 = p2 == null ? p : p2.parent;    
        }
        return p1;
    }


    //Validate Binary Search Tree
    private Deque<TreeNode> stack = new LinkedList<>();
    private Deque<Integer> upperLimits = new LinkedList<>();
    private Deque<Integer> lowerLimits = new LinkedList<>();
    public void update(TreeNode root, Integer low, Integer high) {
        stack.add(root);
        lowerLimits.add(low);
        upperLimits.add(high);
    }
    public boolean isValidBST(TreeNode root) {
        Integer low = null, high = null, val;
        update(root, low, high);
        while (!stack.isEmpty()) {
            root = stack.poll();
            low = lowerLimits.poll();
            high = upperLimits.poll();
            if (root == null) continue;
            val = root.val;
            if (low != null && val <= low) {
                return false;
            }
            if (high != null && val >= high) {
                return false;
            }
            update(root.right, val, high);
            update(root.left, low, val);
        }
        return true;
    }   
    
    
    //Binary Search Tree Iterator
    class BSTIterator {
        ArrayList<Integer> nodesSorted;
        int index;    
        public BSTIterator(TreeNode root) {    
            // Array containing all the nodes in the sorted order
            this.nodesSorted = new ArrayList<Integer>();
            // Pointer to the next smallest element in the BST
            this.index = -1;
            // Call to flatten the input binary search tree
            this._inorder(root);
        }
        private void _inorder(TreeNode root) {
            if (root == null) {
                return;
            }
            this._inorder(root.left);
            this.nodesSorted.add(root.val);
            this._inorder(root.right);
        }
        /**
         * @return the next smallest number
         */
        public int next() {
            return this.nodesSorted.get(++this.index);
        }
        /**
         * @return whether we have a next smallest number
         */
        public boolean hasNext() {
            return this.index + 1 < this.nodesSorted.size();
        }
    } 



    //Diameter of Binary Tree
    private int diameter;
    public int diameterOfBinaryTree(TreeNode root) {
        diameter = 0;
        longestPath(root);
        return diameter;
    }
    private int longestPath(TreeNode node){
        if(node == null) return 0;
        // recursively find the longest path in
        // both left child and right child
        int leftPath = longestPath(node.left);
        int rightPath = longestPath(node.right);

        // update the diameter if left_path plus right_path is larger
        diameter = Math.max(diameter, leftPath + rightPath);

        // return the longest one between left_path and right_path;
        // remember to add 1 for the path connecting the node and its parent
        return Math.max(leftPath, rightPath) + 1;
    }    

 
    
    //Range Sum of BST
    public int rangeSumBST(TreeNode root, int L, int R) {
        int ans = 0;
        Stack<TreeNode> stack = new Stack<>();
        stack.push(root);
        while (!stack.isEmpty()) {
            TreeNode node = stack.pop();
            if (node != null) {
                if (L <= node.val && node.val <= R)
                    ans += node.val;
                if (L < node.val)
                    stack.push(node.left);
                if (node.val < R)
                    stack.push(node.right);
            }
        }
        return ans;
    }   
    
    
    //Binary Tree Zigzag Level Order Traversal
    protected void DFS(TreeNode node, int level, List<List<Integer>> results) {
        if (level >= results.size()) {
          LinkedList<Integer> newLevel = new LinkedList<Integer>();
          newLevel.add(node.val);
          results.add(newLevel);
        } else {
          if (level % 2 == 0)
            results.get(level).add(node.val);
          else
            results.get(level).add(0, node.val);
        }
    
        if (node.left != null) DFS(node.left, level + 1, results);
        if (node.right != null) DFS(node.right, level + 1, results);
    }
    public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
        if (root == null) {
            return new ArrayList<List<Integer>>();
        }
        List<List<Integer>> results = new ArrayList<List<Integer>>();
        DFS(root, 0, results);
        return results;
    }    


    



    


      



}
