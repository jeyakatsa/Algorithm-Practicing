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
    
    //
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

    //Consruct Tree from Inorder, Preorder Traversal
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
    public Solution() {
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
}
