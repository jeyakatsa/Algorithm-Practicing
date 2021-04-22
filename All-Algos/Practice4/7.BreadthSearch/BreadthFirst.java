import java.util.*;

class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;

    TreeNode(int x){
        val = x;
    }
}


class LevelOrderTraversal {

    //Level Order Traversal
    //Given a binary tree, populate an array to represent its level traversal
    public static List<List<Integer>> traverse(TreeNode root) {
        List<List<Integer>> result = new ArrayList<List<Integer>>();
        if (root == null)
            return result;
    
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            int levelSize = queue.size();
            List<Integer> currentLevel = new ArrayList<>(levelSize);
            for (int i = 0; i < levelSize; i++) {
                TreeNode currentNode = queue.poll();
                // add the node to the current level
                currentLevel.add(currentNode.val);
                // insert the children of current node in the queue
                if (currentNode.left != null)
                    queue.offer(currentNode.left);
                if (currentNode.right != null)
                    queue.offer(currentNode.right);
            }
            result.add(currentLevel);
        }
    
        return result;
    }

    //Reverse Level Order Traversal
    //Given binary tree, populate an array to represent its level in reverse order
    public static List<List<Integer>> reverseOrder(TreeNode root){
        List<List<Integer>> result = new ArrayList<List<Integer>>();
        //base case, if root equals null, return result
        if (root == null) {
            return result;
        }
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        while(!queue.isEmpty()) {
            int levelSize = queue.size();
            List<Integer> currentLevel = new ArrayList<>(levelSize);
            for (int i = 0; i < levelSize; i++) {
                TreeNode currentNode = queue.poll();
                // add node to current level
                currentLevel.add(currentNode.val);
                //Insert the children of current node to the queue
                if(currentNode.left != null)
                    queue.offer(currentNode.left);
                if(currentNode.right != null)
                    queue.offer(currentNode.right);    
            }
            //Append current level at beginning
            result.add(0, currentLevel);
        }
        return result;        
    }

    //Zigzag Traversal
    public static List<List<Integer>> zigZag(TreeNode root) {
        List<List<Integer>> result = new ArrayList<List<Integer>>();
        if (root == null) {
            return result;
        }
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        boolean leftToRight = true;
        while(!queue.isEmpty()){
            int levelSize = queue.size();
            List<Integer> currentLevel = new ArrayList<>(levelSize);
            for (int i = 0; i < levelSize; i++) {
                TreeNode currentNode = queue.poll();

                //add the node to the current level based on the traverse direction
                if(leftToRight)
                    currentLevel.add(currentNode.val);
                else
                    currentLevel.add(0, currentNode.val);

                //insert the children of current node in the queue
                if(currentNode.left != null)
                    queue.offer(currentNode.left);
                if(currentNode.right != null)
                    queue.offer(currentNode.right);    
            }
            result.add(currentLevel);
            //reverse traversal direction
            leftToRight = !leftToRight;
        }
        return result;
    }


    public static void main(String[] args) {
        TreeNode root = new TreeNode(12);
        root.left = new TreeNode(7);
        root.right = new TreeNode(1);
        root.left.left = new TreeNode(9);
        root.right.left = new TreeNode(10);
        root.right.right = new TreeNode(5);
        root.right.left.left = new TreeNode(20);
        root.right.left.right = new TreeNode(17);
        List<List<Integer>> result = LevelOrderTraversal.zigZag(root);
        System.out.println("Level order traversal: " + result);
    }        
}