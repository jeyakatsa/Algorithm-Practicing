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
        setHeight(root);

        return balance(root);
    }

    //Check Balance of Tree
    private AVLNode balance(AVLNode root) {
        if (isLeftHeavy(root)){
            if(balanceFactor(root.leftChild) < 0)
                root.leftChild = rotateLeft(root.leftChild);
            return rotateRight(root);
        }
        else if (isRightHeavy(root)) {
            if (balanceFactor(root.rightChild) > 0)
                root.rightChild = rotateRight(root.rightChild);
            return rotateLeft(root);    
        }
        return root;

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

    //Rotate Nodes Right or Left to Balance Tree
    private AVLNode rotateLeft(AVLNode root) {
        var newRoot = root.rightChild;

        root.rightChild = newRoot.leftChild;
        newRoot.leftChild = root;

        setHeight(root);
        setHeight(newRoot);

        return newRoot;
    }

    private AVLNode rotateRight(AVLNode root) {
        var newRoot = root.leftChild;

        root.leftChild = newRoot.rightChild;
        newRoot.rightChild = root;

        setHeight(root);
        setHeight(newRoot);

        return newRoot;
    }

    private void setHeight(AVLNode node) {
        node.height = Math.max(
            height(root.leftChild),
            height(root.rightChild)) + 1;        
    }

    //Height
    private int height(AVLNode node){
        return (node == null) ? -1 : node.height;
    }

}