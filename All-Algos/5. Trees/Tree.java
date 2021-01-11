public class Tree {

    //left < parent < right
    //[10,5,15,6,1,8,12,18,17]
    // Sort them in order first     
    // 1,5,6,8,10,12,15,17,18
    // root of tree would be median of all numbers summed
    // 10 = root
    // left of 10 = left child, right of 10 = right child
    // left child = median of all numbers of left of 10
    // 6 = left child
    // 8 = right of 6
    // 1 = left of 5 = left of 6
    // right child = median of all numbers right of 10
    // 15 = right child
    // 12 = left of 15
    // 18 = right of 17 = right of 15

    public static void main (String[] args){
        Tree tree = new Tree();
        tree.insert(7);
        tree.insert(4);
        tree.insert(9);
        tree.insert(1);
        tree.insert(6);
        tree.insert(8);
        tree.insert(10);
        System.out.println(tree.min());
    }

    class Node {
        int value;
        Node leftChild;
        Node rightChild;

        Node (int value) {
            this.value = value;
            rightChild = null;
            leftChild = null;
        }
    }

    Node root;

    private void insert(int value) {
        var node = new Node(value);

        if (root == null) {
            root = node;
            return;
        }

        var current = root;
        while (true) {
            if (value < current.value) {
                if (current.leftChild == null) {
                    current.leftChild = node;
                    break;
                }
                current = current.leftChild;
            }
            else {
                if (current.rightChild == null) {
                    current.rightChild = node;
                    break;
                }
                current = current.rightChild;
            }
        }
    }

    public boolean find(int value) {
        var current = root;
        while (current != null) {
            if (value < current.value)
                current = current.leftChild;
            else if (value > current.value)
                current = current.rightChild;
            else 
                return true;
        }
        return false;
    }

    //in-order traversal
    public void traverseInOrder(){
        traverseInOrder(root);
    }

    private void traverseInOrder(Node root){
        if (root == null)
            return;

        traverseInOrder(root.leftChild);
        System.out.println(root.value);  
        traverseInOrder(root.rightChild);  
    }

    //post-order traversal
    public void traversePostOrder(){
        traversePostOrder(root);
    }

    private void traversePostOrder(Node root){
        if (root == null)
            return;

        traversePostOrder(root.leftChild);
        traversePostOrder(root.rightChild);
        System.out.println(root.value); 
    }

    //Pre-order traversal
    public void traversePreOrder(){
        traversePreOrder(root);
    }

    private void traversePreOrder(Node root){
        if (root == null)
            return;
        
        System.out.println(root.value);
        traversePreOrder(root.leftChild);
        traversePreOrder(root.rightChild);    
    }

    //height of tree
    public int height (){
        return height(root);
    }

    private int height (Node root) {
        if (isLeaf(root))
            return 0;

        return 1 + Math.max(
            height(root.leftChild),
            height(root.rightChild));
    }

    //Minimum Value in tree
    public int min() {
        //for Binary Tree
        return min(root);

        // //0(log n)
        // //for Binary Search Tree
        // if (root == null)
        // throw new IllegalStateException();

        // var current = root; 
        // var last = current;
        // while (current != null) {
        //     last = current;
        //     current = current.leftChild;
        // }
        // return last.value;
    }

    //0(n)
    private int min (Node root) {
        if (isLeaf(root))
            return root.value;

        var left = min(root.leftChild);
        var right = min(root.rightChild);

        return Math.min(Math.min(left, right), root.value);
    }

    private boolean isLeaf(Node node) {
        return node.leftChild == null && node.rightChild == null;
    }

}