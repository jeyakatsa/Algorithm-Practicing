import javax.lang.model.util.ElementScanner14;

public class BuildBinaryTree {

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
        BuildBinaryTree tree = new BuildBinaryTree();
        tree.insert(7);
        tree.insert(4);
        tree.insert(9);
        tree.insert(1);
        tree.insert(6);
        tree.insert(8);
        tree.insert(10);
        System.out.println(tree.find(10));
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

}