public class HeapTree {
    public static void main(String[] args) {
        HeapTree tree = new HeapTree();
        tree.insert(10);
        tree.insert(20);
        tree.insert(30);
        // Heap
        // int[]
        // insert(int)
        // remove()
    }

    class HeapNode {
        int value;
        HeapNode leftChild;
        HeapNode rightChild;
        public int height;

        HeapNode (int value) {
            this.value = value;
            rightChild = null;
            leftChild = null;
        }

        @Override
        public String toString(){
            return "Value=" + this.value;
        }
    }

    HeapTree root;

    //Recursively Insert
    public void insert(int value) {
        root = insert(root, value);
    }

    private HeapNode insert(HeapTree root, int value){
        //Base Case (to pull out of recursive)
        if(root == null)
            return new HeapNode(value);
        
        if (value < root.value)
            root.leftChild = insert(root.leftChild, value);
        else
            root.rightChild = insert(root.rightChild, value);

        return root;    
    }
}