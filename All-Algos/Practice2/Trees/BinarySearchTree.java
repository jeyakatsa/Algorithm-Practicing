public class BinarySearchTree {
    public static void main (String[] args){
        BinarySearchTree bsT = new BinarySearchTree();
		bsT.add(6);
		bsT.add(7);
		bsT.add(8);
		bsT.add(12);
		bsT.add(1);
		bsT.add(15);

        // Node temp = bsT.search(5);
        // if(temp != null) {
        //     System.out.println("\n" + temp.getData() + " found in Tree!");
        // }
        // else
        //     System.out.println("\n Not found in Tree!");

        // temp = bsT.search(51);
        // if (temp != null) {
        //     System.out.println("\n" + temp.getData() + " found in Tree!");
        // }   
        // else
        //     System.out.println("\n Not found in Tree!"); 

        ////Tree Traversal
        // System.out.print("\nDeleting Node 6: ");
        // bsT.delete(6, bsT.getRoot());
        // bsT.printTree(bsT.getRoot());

        // System.out.print("\nDeleting Node 15: ");
        // bsT.delete(15, bsT.getRoot());
        // bsT.printTree(bsT.getRoot());

        // System.out.print("\nDeleting Node 1: ");
        // bsT.delete(1, bsT.getRoot());
        // bsT.printTree(bsT.getRoot());

        System.out.print(findKNodes(bsT.getRoot(), 1));
    }

    public class Node {
        private int data;
        private Node leftChild;
        private Node rightChild;
        boolean isRed;
    

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

        public void setRightChild(Node right) {
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
            currentNode.setRightChild(
                recursive_insert(currentNode.getRightChild(), value)
            );
        }
        else {
            return currentNode;
        }

        return currentNode;
    }

    public boolean add(int value){
        // //function to call recursive insert
        // root = recursive_insert(this.root, value);
        // return true;

        //If tree is empty then insert Root with the given value inside Tree
        if(isEmpty()) {
            root = new Node(value);
            return true;
        }

        //Starting from root
        Node currentNode = root;

        //Traversing the tree until valid position to insert the value
        while(currentNode != null) {

            Node leftChild = currentNode.getLeftChild();
            Node rightChild = currentNode.getRightChild();

            //If the value to insert is less than root value
            //then move to left subtree else move to right subtree of root
            //and before moving check if subtree is null, if it is then insert value

            if(currentNode.getData() > value) {
                if(leftChild == null) {
                    leftChild = new Node(value);
                    currentNode.setLeftChild(leftChild);
                    return true;
                }
                currentNode = leftChild;
            }
            else {
                if (rightChild == null) {
                    rightChild = new Node(value);
                    currentNode.setRightChild(rightChild);
                    return true;
                }
                currentNode = rightChild;
            }
        }
        return false;
    }

    boolean delete (int value, Node currentNode) {
        //base case
        if (root == null) {
            return false;
        }

        Node parent = null; //To Store parent of currentNode
        while(currentNode != null && (currentNode.getData() != value)) {
            parent = currentNode;
            if(currentNode.getData() > value)
                currentNode = currentNode.getLeftChild();
            else
                currentNode = currentNode.getRightChild();  
        }

        if(currentNode == null) {
            return false;
        }
        else if(currentNode.getLeftChild() == null && currentNode.getRightChild() == null) {
            //1. Node is Leaf Node
            //if that leaf node is the root (a tree with just root)
            if(root.getData() == currentNode.getData()) {
                setRoot(null);
                return true;
            }
            else if (currentNode.getData() < parent.getData()) {
                parent.setLeftChild(null);
                return true;
            }
            else {
                parent.setLeftChild(null);
                return true;
            }
        }
        else if (currentNode.getRightChild() == null) {

            if(root.getData() == currentNode.getData()) {
                setRoot(currentNode.getLeftChild());
                return true;
            }
            else if (currentNode.getData() < currentNode.getData()) {
                parent.setLeftChild(currentNode.getLeftChild());
                return true;
            }
            else {
                parent.setRightChild(currentNode.getLeftChild());
                return true;
            }
        }
        else if (currentNode.getLeftChild() == null) {

            if(root.getData() == currentNode.getData()){
                setRoot(currentNode.getRightChild());
                return true;
            }
            else if (currentNode.getData() < parent.getData()) {
                parent.setLeftChild(currentNode.getRightChild());
                return true;
            }
            else {
                parent.setRightChild(currentNode.getRightChild());
                return true;
            }
        }
        else {
            //Find Least Value Node in right-subtree of current Node
            Node leastNode = findLeastNode(currentNode.getRightChild());
            //Set currentNode's Data to the least value in its right-subtree
            int temp = leastNode.getData();
            delete(temp, root);
            currentNode.setData(temp);
            //Delete the leafNode which had the least value
            return true;
        }
    }

    //Helper function to find least value node in right-subtree of currentNode
    private Node findLeastNode(Node currentNode) {

        Node temp = currentNode;

        while (temp.getLeftChild() != null) {
            temp = temp.getLeftChild();
        }
        return temp;
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

    //Pre-order traversal
    public void preTraverse(Node root) {
        if (root == null) 
        return;

        System.out.print(root.getData() + ",");
        preTraverse(root.getLeftChild());
        preTraverse(root.getRightChild());    
    }

    //In-order Traversal
    public void inTraverse(Node root) {
        if(root == null)
        return;

        inTraverse(root.getLeftChild());
        System.out.print(root.getData() + ",");
        inTraverse(root.getRightChild());
    }

    //Post-order Traversal
    public void postTraverse(Node root) {
        if(root == null)
        return;

        postTraverse(root.getLeftChild());
        postTraverse(root.getRightChild());
        System.out.print(root.getData() + ",");
    }

    public static int findMin(Node root) {
        // In Binary Search Tree, all values in current node's left subtree are smaller 
        // than the current node's value.
        // So keep traversing (in order) towards left till you reach leaf node, and then return leaf node's value
        if (root == null) {
            return -1;
        }

        while(root.getLeftChild() != null) {
            root = root.getLeftChild();
        }

        return root.getData();

    }

    public static int findKthMax(Node root, int k) {
        //Perform In-Order Traversal to get sorted array. (ascending order)
        //Return value at index [length - k]
        StringBuilder result = new StringBuilder(); // StringBuilder is mutable
        result = inOrderTraversal(root, result);

        String[] array = result.toString().split(","); // Spliting String into array of strings
        if((array.length - k) >= 0) 
            return Integer.parseInt(array[array.length -k]);

        return -1;
    }

    //Helper recursive function to traverse tree using inorder traversal
    //and return result in StringBuilder
    public static StringBuilder inOrderTraversal(Node root, StringBuilder result) {
        if(root.getLeftChild() != null)
            inOrderTraversal(root.getLeftChild(), result);

        result.append(root.getData() + ",");
        
        if(root.getRightChild() != null) 
            inOrderTraversal(root.getRightChild(), result);

        return result;    
    }

    public static String findAncestors(Node root, int k) {

        String result = "";
        Node tempNode = root;
        while(tempNode != null && tempNode.getData() != k) {
            result = result + tempNode.getData() + ",";
            if(k <= tempNode.getData()) {
                tempNode = tempNode.getLeftChild();
            } else {
                tempNode = tempNode.getRightChild();
            }
        }
        if (tempNode == null) {
            return "";
        }
        return "";
    }

    public static int findHeight(Node root) {
        if (root == null) 
            return -1;
        else {
            return 1 + Math.max(
                findHeight(root.getLeftChild()),
                findHeight(root.getRightChild()));
            //find Height of left subtree right subtree
            //return greater height value of left or right subtree (plus 1)    
        }        
    }

    //Find Nodes from distance of root

    public static String findKNodes(Node root, int k) {
        StringBuilder result = new StringBuilder();
        result = findK(root, k, result);

        return result.toString();
    }

    //Helper recursive function to traverse tree and append all the nodes
    //at k distance into result StringBuilder
    public static StringBuilder findK(Node root, int k, StringBuilder result) {
        if (root == null)
            return null;

        if (k == 0) {
            result.append(root.getData() + ",");
        }
        else {
            //Decrement k at each step till you reach at the leaf node
            //or when k == 0 then append the Node's data into result string
            findK(root.getLeftChild(), k - 1, result);
            findK(root.getRightChild(), k - 1, result);
        }
        return result;
    }

}