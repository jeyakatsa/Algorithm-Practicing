import java.util.*;

public class Trees {
    public class TreeNode {
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


    
    //CHECK IF BST is VALID
    public boolean isValidBST(TreeNode root) {
        if (root == null) return true;
        Stack<TreeNode> stack = new Stack<>();
        TreeNode pre = null;
        while (root != null || !stack.isEmpty()) {
           while (root != null) {
              stack.push(root);
              root = root.left;
           }
           root = stack.pop();
           if(pre != null && root.val <= pre.val) {
               return false; 
            }
           pre = root;
           root = root.right;
        }
        return true;
    }



    //ZIG ZAG LEVEL TRAVERSAL
    public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
        TreeNode c=root;
        List<List<Integer>> ans =new ArrayList<List<Integer>>();
        if(c==null) return ans;
        Stack<TreeNode> s1=new Stack<TreeNode>();
        Stack<TreeNode> s2=new Stack<TreeNode>();
        s1.push(root);
        while(!s1.isEmpty()||!s2.isEmpty())
        {
            List<Integer> tmp=new ArrayList<Integer>();
             while(!s1.isEmpty())
             {
                 c=s1.pop();
                 tmp.add(c.val);
                 if(c.left!=null) s2.push(c.left);
                 if(c.right!=null) s2.push(c.right);
             }
             ans.add(tmp);
             tmp=new ArrayList<Integer>();
             while(!s2.isEmpty())
             {
                 c=s2.pop();
                 tmp.add(c.val);
                 if(c.right!=null)s1.push(c.right);
                 if(c.left!=null)s1.push(c.left);
             }
             if(!tmp.isEmpty()) ans.add(tmp);
        }
        return ans;
    }   


    //CONSTRUCT TREE FROM INORDER, PREORDER
    int preorderIndex;
    Map<Integer, Integer> inorderIndexMap;
    public TreeNode buildTree(int[] preorder, int[] inorder) {
        preorderIndex = 0;
        // build a hashmap to store value -> its index relations
        inorderIndexMap = new HashMap<>();
        for (int i = 0; i < inorder.length; i++) {
            inorderIndexMap.put(inorder[i], i);
        }

        return arrayToTree(preorder, 0, preorder.length - 1);
    }
    private TreeNode arrayToTree(int[] preorder, int left, int right) {
        // if there are no elements to construct the tree
        if (left > right) return null;

        // select the preorder_index element as the root and increment it
        int rootValue = preorder[preorderIndex++];
        TreeNode root = new TreeNode(rootValue);

        // build left and right subtree
        // excluding inorderIndexMap[rootValue] element because it's the root
        root.left = arrayToTree(preorder, left, inorderIndexMap.get(rootValue) - 1);
        root.right = arrayToTree(preorder, inorderIndexMap.get(rootValue) + 1, right);
        return root;
    }  
    
    

    //RETURN RIGHT SIDE OF TREE... DFS solution:
    public List<Integer> rightSideView(TreeNode root) {
        List<Integer> result = new ArrayList<Integer>();
        rightView(root, result, 0);
        return result;
    }
    public void rightView(TreeNode curr, List<Integer> result, int currDepth){
        if(curr == null){
            return;
        }
        if(currDepth == result.size()){
            result.add(curr.val);
        }
        
        rightView(curr.right, result, currDepth + 1);
        rightView(curr.left, result, currDepth + 1);   
    } 

     


    //LOWEST COMMON ANCESTOR!!!!.......
    private TreeNode ans;
    public Trees() {
        // Variable to store LCA node.
        this.ans = null;
    }
    private boolean recurseTree(TreeNode currentNode, TreeNode p, TreeNode q) {
        // If reached the end of a branch, return false.
        if (currentNode == null) {
            return false;
        }
        // Left Recursion. If left recursion returns true, set left = 1 else 0
        int left = this.recurseTree(currentNode.left, p, q) ? 1 : 0;
        // Right Recursion
        int right = this.recurseTree(currentNode.right, p, q) ? 1 : 0;
        // If the current node is one of p or q
        int mid = (currentNode == p || currentNode == q) ? 1 : 0;
        // If any two of the flags left, right or mid become True
        if (mid + left + right >= 2) {
            this.ans = currentNode;
        }
        // Return true if any one of the three bool values is True.
        return (mid + left + right > 0);
    }
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        // Traverse the tree
        this.recurseTree(root, p, q);
        return this.ans;
    }




    //CHECK IF TREE HAS SUBTREE
    public boolean isSubtree(TreeNode s, TreeNode t) { // takes O(m x n)
        if (s == null) {
            return t == null;
        }
        return isSame(s, t) || isSubtree(s.left, t) || isSubtree(s.right, t);
    }

    private boolean isSame(TreeNode t1, TreeNode t2) { // takes O(n)
        if (t1 == null && t2 == null) {
            return true;
        }
        if (t1 == null || t2 == null) {
            return false;
        }        
        if (t1.val != t2.val) {
            return false;
        }
        return isSame(t1.left, t2.left) 
        && isSame(t1.right, t2.right);
    }



    //SYMMETRIC TREE
    public boolean isSymmetric(TreeNode root) {
        return isMirror(root, root);
    }
    public boolean isMirror(TreeNode t1, TreeNode t2) {
        if (t1 == null && t2 == null) 
            return true;
        if (t1 == null || t2 == null) 
            return false;
        return (t1.val == t2.val)
            && isMirror(t1.right, t2.left)
            && isMirror(t1.left, t2.right);
    } 

    
    
    //COUNT GOOD NODES IN BST
    public int goodNodes(TreeNode root) {
        return preorder(root, root.val);
    }
    private int preorder(TreeNode n, int v) {
        if (n == null) // base cases.
            return 0;
        int max = Math.max(n.val, v); // maximum so far on the path.
        return (n.val >= v ? 1 : 0) 
        + preorder(n.left, max) 
        + preorder(n.right, max); // recurse to children.
    } 




    //BINARY SEARCH ITERATOR
    ArrayList<Integer> nodesSorted;
    int index;
    public void BSTIterator(TreeNode root) {
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
    public int next() {
        return this.nodesSorted.get(++this.index);
    }
    public boolean hasNext() {
        return this.index + 1 < this.nodesSorted.size();
    }
      



}
