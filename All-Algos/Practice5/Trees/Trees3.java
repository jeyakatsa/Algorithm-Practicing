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
    
    

    
}
