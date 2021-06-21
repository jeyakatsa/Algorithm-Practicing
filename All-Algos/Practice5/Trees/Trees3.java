import java.util.*;

public class Trees3 {

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
    
    

    //Diameter of Binary Tree
    private int diameter;
    public int diameterOfBinaryTree(Node root) {
        diameter = 0;
        longestPath(root);
        return diameter;
    }
    private int longestPath(Node node){
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


    
    //Closest Binary Search Tree Value
    public void inorder(Node root, List<Integer> nums) {
        if (root == null) return;
        inorder(root.left, nums);
        nums.add(root.val);
        inorder(root.right, nums);
    }
    public int closestValue(Node root, double target) {
        List<Integer> nums = new ArrayList<>();
        inorder(root, nums);
        return Collections.min(nums, new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
            return Math.abs(o1 - target) < Math.abs(o2 - target) ? -1 : 1;
            }
        });
    } 



    //Kill Process
    public List<Integer> killProcess (List<Integer> pid, List<Integer> ppid, int kill) {
        List<Integer> l = new ArrayList<>();
        if (kill == 0)
            return l;
        l.add(kill);
        for (int i = 0; i < ppid.size(); i++)
            if (ppid.get(i) == kill)
                l.addAll(killProcess(pid, ppid, pid.get(i)));
        return l;
    }   



}
