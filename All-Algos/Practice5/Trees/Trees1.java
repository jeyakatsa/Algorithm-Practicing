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


    


}
