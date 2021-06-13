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
        Node() {}
        Node(int val) { this.val = val; }
        Node(int val, Node left, Node right, Node parent) {
            this.val = val;
            this.left = left;
            this.right = right;
            this.parent = parent;
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

    
    
}
