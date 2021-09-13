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


    // //Binary Tree Vertical Order Traversal
    // public List<List<Integer>> verticalOrder(Node root) {
    //     List<List<Integer>> output = new ArrayList<>();
    //     if (root == null) {
    //       return output;
    //     }
    
    //     Map<Integer, ArrayList> columnTable = new HashMap<>();
    //     Queue<Pair<Node, Integer>> queue = new ArrayDeque<>();
    //     int column = 0;
    //     queue.offer(new Pair(root, column));
    
    //     while (!queue.isEmpty()) {
    //       Pair<Node, Integer> p = queue.poll();
    //       root = p.getKey();
    //       column = p.getValue();
    
    //       if (root != null) {
    //         if (!columnTable.containsKey(column)) {
    //           columnTable.put(column, new ArrayList<Integer>());
    //         }
    //         columnTable.get(column).add(root.val);
    
    //         queue.offer(new Pair(root.left, column - 1));
    //         queue.offer(new Pair(root.right, column + 1));
    //       }
    //     }
    
    //     List<Integer> sortedKeys = new ArrayList<Integer>(columnTable.keySet());
    //     Collections.sort(sortedKeys);
    //     for(int k : sortedKeys) {
    //       output.add(columnTable.get(k));
    //     }
    
    //     return output;
    // }   
    
    
    //Invert Binary Tree
    public Node invertTree(Node root) {
        if (root == null) return null;
        Queue<Node> queue = new LinkedList<Node>();
        queue.add(root);
        while (!queue.isEmpty()) {
            Node current = queue.poll();
            Node temp = current.left;
            current.left = current.right;
            current.right = temp;
            if (current.left != null) queue.add(current.left);
            if (current.right != null) queue.add(current.right);
        }
        return root;
    }   


    //Recover Binary Search Tree
    public void swap(Node a, Node b) {
        int tmp = a.val;
        a.val = b.val;
        b.val = tmp;
      }
    
    public void recoverTree(Node root) {
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


    //Balance a Binary Search Tree
    List<Node> list = new ArrayList<>();                                         //declaring list globally so that it can be accessed thoughout the program.
    public void InOrder(Node root) {                                           //InOrder traveral of the tree.
        
        if(root == null)
            return;
        else
        {
            InOrder(root.left);
            list.add(root);
            InOrder(root.right);    
        }
    }
    public Node balancedBST(int start,int end) {                                   //creating new tree with the help of list.
        if(start>end)
            return null;
        int mid = (start+end)/2;
        Node root = list.get(mid);
        root.left = balancedBST(start,mid-1);
        root.right = balancedBST(mid+1,end);
        return root;
    }
    public Node balanceBST(Node root) {
        if(root ==null)
            return null;
        InOrder(root);
        return balancedBST(0,list.size()-1);
    } 
    
    
    //Boundary of Binary Tree
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





    


}
