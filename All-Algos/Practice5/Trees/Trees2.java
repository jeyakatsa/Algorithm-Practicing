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
    
}
