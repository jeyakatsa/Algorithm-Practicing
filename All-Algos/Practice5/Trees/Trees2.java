import java.util.*;

public class Trees2 {

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
        Node next;
        Node() {}
        Node(int val) { this.val = val; }
        Node(int val, Node left, Node right, Node parent, Node next) {
            this.val = val;
            this.left = left;
            this.right = right;
            this.parent = parent;
            this.next = next;
        }
    }
    
    

    //UNIQUE BINARY TREES
    public int numTrees(int n) {
        int[] G = new int[n + 1];
        G[0] = 1;
        G[1] = 1;
    
        for (int i = 2; i <= n; ++i) {
          for (int j = 1; j <= i; ++j) {
            G[i] += G[j - 1] * G[i - j];
          }
        }
        return G[n];
    } 


    //Recover Binary Search Tree
    public void swap(Node a, Node b) {
        int tmp = a.val;
        a.val = b.val;
        b.val = tmp;
    }
    
    public void recover (Node root) {
        Deque<Node> stack = new ArrayDeque<>();
        Node x = null, y = null, pred = null;

        while (!stack.isEmpty() || root != null) {
            while (root != null) {
            stack.add(root);
            root = root.left;
            }
            root = stack.removeLast();
            if (pred != null && root.val < pred.val) {
            y = root;
            if (x == null) x = pred;
            else break;
            }
            pred = root;
            root = root.right;
        }

        swap(x, y);
    }    



    //PATH SUM III
    int count = 0;
    int k;
    HashMap<Integer, Integer> h = new HashMap<>();
    
    public void preorder(Node node, int currSum) {
        if (node == null)
            return;
        
        // current prefix sum
        currSum += node.val;

        // here is the sum we're looking for
        if (currSum == k)
            count++;
        
        // number of times the curr_sum − k has occured already, 
        // determines the number of times a path with sum k 
        // has occured upto the current node
        count += h.getOrDefault(currSum - k, 0);
        
        // add the current sum into hashmap
        // to use it during the child nodes processing
        h.put(currSum, h.getOrDefault(currSum, 0) + 1);

        // process left subtree
        preorder(node.left, currSum);
        // process right subtree
        preorder(node.right, currSum);

        // remove the current sum from the hashmap
        // in order not to use it during 
        // the parallel subtree processing
        h.put(currSum, h.get(currSum) - 1);
    }    
            
    public int pathSum(Node root, int sum) {
        k = sum;
        preorder(root, 0);
        return count;
    }  
    
    

    //CONVERT SORTED ARRAY TO BINARY TREE
    int[] nums;
    public Node helper(int left, int right) {
      if (left > right) return null;
      // always choose left middle node as a root
      int p = (left + right) / 2;
      // preorder traversal: node -> left -> right
      Node root = new Node(nums[p]);
      root.left = helper(left, p - 1);
      root.right = helper(p + 1, right);
      return root;
    }
    public Node sortedArrayToBST(int[] nums) {
      this.nums = nums;
      return helper(0, nums.length - 1);
    }  


    

    //Populating Next Right Pointers in Each Node
    public Node connect(Node root) {    
        if (root == null) {
            return root;
        }    
        // Initialize a queue data structure which contains
        // just the root of the tree
        Queue<Node> Q = new LinkedList<Node>(); 
        Q.add(root);    
        // Outer while loop which iterates over 
        // each level
        while (Q.size() > 0) {        
            // Note the size of the queue
            int size = Q.size();     
            // Iterate over all the nodes on the current level
            for(int i = 0; i < size; i++) {         
                // Pop a node from the front of the queue
                Node node = Q.poll();      
                // This check is important. We don't want to
                // establish any wrong connections. The queue will
                // contain nodes from 2 levels at most at any
                // point in time. This check ensures we only 
                // don't establish next pointers beyond the end
                // of a level
                if (i < size - 1) {
                    node.next = Q.peek();
                }    
                // Add the children, if any, to the back of
                // the queue
                if (node.left != null) {
                    Q.add(node.left);
                }
                if (node.right != null) {
                    Q.add(node.right);
                }
            }
        }  
        // Since the tree has now been modified, return the root node
        return root;
    }
    
    


    //Delete Nodes And Return Forest
    List<Node> roots;
    Set<Integer> delSet;
    public List<Node> delNodes(Node root, int[] to_delete) {
        delSet = new HashSet<>();
        roots = new ArrayList<>();
        for (int nodeToDelete : to_delete) {
            delSet.add(nodeToDelete);
        }
        if (!delSet.contains(root.val)) {
            roots.add(root);
        }
        delNodesUtil(root);
        return roots;
    }
    public Node delNodesUtil(Node root) {
        if (root == null) {
            return null;
        }
        root.left = delNodesUtil(root.left);
        root.right = delNodesUtil(root.right);
        if (delSet.contains(root.val)) {
            if (root.left != null) {
                roots.add(root.left);
            }
            if (root.right != null) {
                roots.add(root.right);
            }
            return null;
        }
        return root;
    }  



    //Flatten Binary Tree to Linked List
    private Node flattenTree(Node node) {
        // Handle the null scenario
        if (node == null) {
            return null;
        }         
        // For a leaf node, we simply return the
        // node as is.
        if (node.left == null && node.right == null) {
            return node;
        } 
        //Recursively flatten the left subtree
        Node leftTail = this.flattenTree(node.left);
        // Recursively flatten the right subtree
        Node rightTail = this.flattenTree(node.right);
        // If there was a left subtree, we shuffle the connections
        // around so that there is nothing on the left side
        // anymore.
        if (leftTail != null) {
            leftTail.right = node.right;
            node.right = node.left;
            node.left = null;
        }
        // We need to return the "rightmost" node after we are
        // done wiring the new connections. 
        return rightTail == null ? leftTail : rightTail;
    }
    public void flatten(Node root) {   
        this.flattenTree(root);
    }

    
    
    
}
