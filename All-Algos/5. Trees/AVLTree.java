public class AVLTree {
    public static void main(String[] args) {
        AVLTree tree = new AVLTree();
        tree.insert(10);
        tree.insert(20);
        tree.insert(30);
    }

    class AVLNode {
        int value;
        AVLNode leftChild;
        AVLNode rightChild;
        public int height;

        AVLNode (int value) {
            this.value = value;
            rightChild = null;
            leftChild = null;
        }

        @Override
        public String toString(){
            return "Value=" + this.value;
        }
    }

    AVLNode root;

    //Recursively Insert
    public void insert(int value) {
        root = insert(root, value);
    }

    private AVLNode insert(AVLNode root, int value){
        if (root == null)
            return new AVLNode(value);

        if (value < root.value)
            root.leftChild = insert(root.leftChild, value);
        else
            root.rightChild = insert(root.rightChild, value);
        
        //Height Test
        root.height = Math.max(
            height(root.leftChild),
            height(root.rightChild)) + 1;

        balance(root);    

        return root;  

    }

    private void balance(AVLNode root) {
        //Check Balance of tree
        if (isLeftHeavy(root))
            System.out.println(root.value + " is left heavy");
        else if (isRightHeavy(root)) {
            if (balanceFactor(root.rightChild) > 0)
            System.out.println(root.value + " is right heavy");
        }

    }

    private boolean isLeftHeavy(AVLNode node) {
        return balanceFactor(node) > 1;
    }

    private boolean isRightHeavy(AVLNode node) {
        return balanceFactor(node) < -1;
    }

    private int balanceFactor(AVLNode node) {
        return (node == null) ? 0 : height(node.leftChild) - height(node.rightChild);
    }

    //Height
    private int height(AVLNode node){
        return (node == null) ? -1 : node.height;
    }

}