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

        inTree.traverseInOrder();
        System.out.println();

        System.out.println(inTree.get(50));
        System.out.println(inTree.get(25));
        System.out.println(inTree.get(40));

        System.out.println("----------------------------");

        System.out.println(inTree.min());
        System.out.println(inTree.max());

    }

    //Binary Search Tree (left nodes less, right nodes greater)
    public class TreeNode {
        private int data;
        private TreeNode leftChild;
        private TreeNode rightChild;

        public TreeNode(int data) {
            this.data = data;
        }

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

        public void traverseInOrder() {
            if (leftChild != null) {
                leftChild.traverseInOrder();
            } 
            System.out.print(data + ", ");
            if (rightChild != null) {
                rightChild.traverseInOrder();
            }
        }

        public TreeNode get(int value) {
            if (value == data) {
                return this;
            }
            if (value < data) {
                if (leftChild != null) {
                    return leftChild.get(value);
                }
            }
            else {
                if (rightChild != null) {
                    return rightChild.get(value);
                }
            }
            return null;
        }

        public int min() {
            if (leftChild == null) {
                return data;
            }
            else {
                return leftChild.min();
            }
        }

        public int max() {
            if (rightChild == null) {
                return data;
            }
            else {
                return rightChild.max();
            }
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

    public int max() {
        if (root == null) {
            return Integer.MAX_VALUE;
        }
        else {
            return root.max();
        }
    }

    public int min() {
        if (root == null) {
            return Integer.MIN_VALUE;
        }
        else {
            return root.min();
        }
    }

    public TreeNode get(int value) {
        if (root != null) {
            return root.get(value);
        }
        return null;
    }

    public void traverseInOrder() {
        if (root != null) {
            root.traverseInOrder();
        }
    }

}