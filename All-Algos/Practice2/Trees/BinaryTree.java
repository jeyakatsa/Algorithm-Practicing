public class BinaryTree {
    public static void main (String[] args){
        BinaryTree bsT = new BinaryTree();
		bsT.add(6);
		bsT.add(4);
		bsT.add(9);
		bsT.add(5);
		bsT.add(2);
		bsT.add(8);
		bsT.add(12);
		bsT.add(10);
		bsT.add(14);
        System.out.println(">> Tree <<");
		bsT.printTree(bsT.getRoot());

        Node temp = bsT.search(5);
        if(temp != null) {
            System.out.println("\n" + temp.getData() + " found in Tree!");
        }
        else
            System.out.println("\n Not found in Tree!");

        temp = bsT.search(51);
        if (temp != null) {
            System.out.println("\n" + temp.getData() + " found in Tree!");
        }   
        else
            System.out.println("\n Not found in Tree!"); 
    }

    public class Node {
        private int data;
        private Node leftChild;
        private Node rightChild;
    

        Node(int value) {
            this.leftChild = null;
            this.rightChild = null;
            this.data = value;
        }

        public Node getLeftChild(){
            return leftChild;
        }

        public Node getRightChild() {
            return rightChild;
        }

        public int getData() {
            return data;
        }

        public void setData(int value) {
            this.data = value;
        }

        public void setLeftChild(Node left) {
            this.leftChild = left;
        }

        public void setRightCHild(Node right) {
            this.rightChild = right;
        }

    }

    private Node root;

    public Node getRoot() {
        return root;
    }

    public void setRoot(Node root) {
        this.root = root;
    }

    //wrapper function for recursive search
    public Node search(int value) {

        if(isEmpty()) 
            return null;

        //return the output of the recursive search
        return searchRecursive(root, value);    
    }

    //Recursive search function
    public Node searchRecursive(Node currentNode, int value) {
        //if node is null or value is found then return node
        if (currentNode == null || currentNode.getData() == value)
            return currentNode;

        //if value is greater than node's data then search left sub-tree
        if (currentNode.getData() > value) {
            return searchRecursive(currentNode.getLeftChild(), value);
        } 
        else {
        //if value is less than node's data then search right sub-tree
            return searchRecursive(currentNode.getRightChild(), value);
        }
    }

    //Recursive function to insert a value in BST
    public Node recursive_insert(Node currentNode, int value) {
        //Base Case
        if(currentNode == null) {
            return new Node(value);
        }

        if (value < currentNode.getData()) {
            currentNode.setLeftChild(
                recursive_insert(currentNode.getLeftChild(), value)
            );
        }
        else if (value > currentNode.getData()) {
            currentNode.setRightCHild(
                recursive_insert(currentNode.getRightChild(), value)
            );
        }
        else {
            return currentNode;
        }

        return currentNode;
    }

    //function to call recursive insert
    public boolean add(int value){
        root = recursive_insert(this.root, value);
        return true;
    }

    //function to check if tree is empty
    public boolean isEmpty() {
        return root == null;
    }

    //For testing purposes
    public void printTree(Node current) {
        if (current == null) return;

        System.out.print(current.getData() + ",");
        printTree(current.getLeftChild());
        printTree(current.getRightChild());
    }

}