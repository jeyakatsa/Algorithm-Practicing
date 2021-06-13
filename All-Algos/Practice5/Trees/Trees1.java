import java.util.*;

public class Trees1 {
    public static void main (String[] args) {
        Node root = new Node(2);
        root.left = new Node(3);
        root.right = new Node(4);

        // invertTree(root);

        while (root != null) {
            System.out.print(root.val + " " + root.left.val + " " + root.right.val);
            break;
        }

    }



    public static class Node {
        int val;
        Node left;
        Node right;
        Node parent;
        Node() {}
        Node(int val) { this.val = val; }
        Node(int val, Node left, Node right, Node parent) {
            this.val = val;
            this.left = left;
            this.right = right;
            this.parent = parent;
        }
    }


    //CONVERT BST INTO DOUBLY LINKEDLIST
    // the smallest (first) and the largest (last) nodes
    Node first = null;
    Node last = null;

    public void helper(Node node) {
        if (node != null) {
        // left
        helper(node.left);
        // node 
        if (last != null) {
            // link the previous node (last)
            // with the current one (node)
            last.right = node;
            node.left = last;
        }
        else {
            // keep the smallest node
            // to close DLL later on
            first = node;
        }
        last = node;
        // right
        helper(node.right);
        }
    }
    public Node treeToDoublyList(Node root) {
        if (root == null) return null;

        helper(root);
        // close DLL
        last.right = first;
        first.left = last;
        return first;
    }   




    //BOUNDARY OF BINARY TREE
    public boolean isLeaf(Node t) {
        return t.left == null && t.right == null;
    }
    public void addLeaves(List<Integer> res, Node root) {
        if (isLeaf(root)) {
            res.add(root.val);
        } else {
            if (root.left != null) {
                addLeaves(res, root.left);
            }
            if (root.right != null) {
                addLeaves(res, root.right);
            }
        }
    }
    public List<Integer> boundaryOfBinaryTree(Node root) {
        ArrayList<Integer> res = new ArrayList<>();
        if (root == null) {
            return res;
        }
        if (!isLeaf(root)) {
            res.add(root.val);
        }
        Node t = root.left;
        while (t != null) {
            if (!isLeaf(t)) {
                res.add(t.val);
            }
            if (t.left != null) {
                t = t.left;
            } else {
                t = t.right;
            }
        }
        addLeaves(res, root);
        Stack<Integer> s = new Stack<>();
        t = root.right;
        while (t != null) {
            if (!isLeaf(t)) {
                s.push(t.val);
            }
            if (t.right != null) {
                t = t.right;
            } else {
                t = t.left;
            }
        }
        while (!s.empty()) {
            res.add(s.pop());
        }
        return res;
    } 


    //LONELIEST COMMON ANCESTOR OF A BINARY TREE
    public Node lowestCommonAncestor(Node p, Node q) {
        Node a = p, b = q;
        while (a != b) {
            a = a == null? q : a.parent;
            b = b == null? p : b.parent;    
        }
        return a;
    }



    //MINIMUM COST TREE FROM LEAF
    public int findMax(int l, int h, int[]arr){
        int tmp = -1;
        for (int i = l; i <= h; i++) tmp = Math.max(tmp, arr[i]);
        return tmp;
    }
    
    public int mctFromLeafValues(int[] arr) {
        if (arr == null || arr.length <= 1) return 0;
        
        int[][] dp = new int[arr.length][arr.length];
        
        for (int j = 0; j < arr.length; j++){
            for (int i = j; i >= 0; i--){
                for (int k = i; k < j; k++){
                    int tmp = dp[i][k] + dp[k+1][j] + findMax(i,k,arr) * findMax(k+1,j,arr);
                    if (dp[i][j] == 0) 
                        dp[i][j] = tmp;
                    else 
                        dp[i][j] = Math.min(dp[i][j], tmp);
                }
            }
        }
        
        return dp[0][arr.length - 1];
    }  

    
    
    //BINARY TREE LEVEL ORDER TRAVERSAL
    public List<List<Integer>> levelOrder(Node root) {
		List<List<Integer>> res = new ArrayList<>();
		if (root == null)
			return res;
		Queue<Node> q = new LinkedList<>();
		q.add(root);
		while(!q.isEmpty()) {
			int levelSize = q.size();
			List<Integer> currLevel = new ArrayList<>();
			for(int i = 0; i < levelSize; i++) {
				Node currNode = q.poll();
				currLevel.add(currNode.val);
				if (currNode.left != null)
					q.add(currNode.left);
				if (currNode.right != null)
					q.add(currNode.right);
			}
			res.add(currLevel);
		}
		return res;
	} 


    //Construct Binary Tree from Preorder and Postorder Traversal
    int pre[];
    int post[];
    public Node constructFromPrePost(int[] pre, int[] post) {
        this.pre = pre;
        this.post = post;
        return helper(0, pre.length-1, 0, post.length-1);
    }
    
    public Node helper(int pre_start, int pre_end, int post_start, int post_end){
        if (post_start > post_end){
            return null;
        }
        
        int cur = pre[pre_start];
        Node root = new Node(cur);
        if (pre_start == pre_end){
            return root;
        }
        int left = pre[pre_start+1];
        int index = 0;
        for(int i = post_start; i<=post_end; i++){
            if (post[i]==left){
                index = i;
            }
        }
        root.left = helper(pre_start+1, pre_start+index-post_start+1, post_start, index);
        root.right = helper(pre_start+index-post_start+2, pre_end, index+1, post_end-1);
        return root;
    }    

    


    


}
