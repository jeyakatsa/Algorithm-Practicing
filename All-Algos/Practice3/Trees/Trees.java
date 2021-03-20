public class Trees {
    public static void main (String[] arg){
        Trees inTree = new Trees();
        inTree.insert(25);
        inTree.insert(4);
        inTree.insert(60);
        inTree.insert(243);
        inTree.insert(40);
        inTree.insert(0);
        inTree.insert(3);
        inTree.insert(409);
        inTree.insert(64);

    }

    //Binary Search Tree (left nodes less, right nodes greater)
    public class TreeNode {
        private int data;
        private TreeNode leftChild;
        private TreeNode rightChild;

        public void insert(int value) {
            if(value == data) {
                return;
            }

            if (value < data) {
                if (leftChild == null) {
                    leftChild = new TreeNode(value);
                }
                else {
                    leftChild.insert(value);
                }
            }
            else {
                if (rightChild == null) {
                    rightChild = new TreeNode(value);
                }
                else {
                    rightChild.insert(value);
                }
            }
        }

        public TreeNode(int data) {
            this.data = data;
        }

        public int getData(){
            return data;
        }

        public void setData(int data) {
            this.data = data;
        }

        public TreeNode getLeftChild(){
            return leftChild;
        }

        public void setLeftChild(TreeNode leftChild) {
            this.leftChild = leftChild;
        }

        public TreeNode getRightChild() {
            return rightChild;
        }

        public void setRightChild(TreeNode rightChild) {
            this.rightChild = rightChild;
        }

    }

    private TreeNode root;

    public void insert (int value) {
        if (root == null) {
            root = new TreeNode(value);
        }
        else {
            root.insert(value);
        }

    }

}