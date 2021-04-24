import java.util.Queue;

class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;
    TreeNode next;

    TreeNode(int x){
        val = x;
        left = right = next = null;
    }

  // level order traversal using 'next' pointer
  void printLevelOrder() {
        TreeNode nextLevelRoot = this;
        while (nextLevelRoot != null) {
            TreeNode current = nextLevelRoot;
            nextLevelRoot = null;
            while (current != null) {
                System.out.print(current.val + " ");
                if (nextLevelRoot == null) {
                    if (current.left != null)
                        nextLevelRoot = current.left;
                    else if (current.right != null)
                        nextLevelRoot = current.right;
                }
                current = current.next;
            }
            System.out.println();
        }
    }
    public void printTree() {
        TreeNode current = this;
        System.out.print("Traversal using 'next' pointer: ");
        while (current != null) {
          System.out.print(current.val + " ");
          current = current.next;
        }
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
        List<Double> result = new ArrayList<>();
        if (root == null){
            return result;
        }

        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            int levelSize = queue.size();
            double levelSum = 0;
            for (int i = 0; i < levelSize; i++) {
                TreeNode currentNode = queue.poll();
                // add the node's value to the running sum
                levelSum += currentNode.val;
                // insert the children of current node to the queue
                if (currentNode.left != null)
                queue.offer(currentNode.left);
                if (currentNode.right != null)
                queue.offer(currentNode.right);
            }
            // append the current level's average to the result array
            result.add(levelSum / levelSize);
        }

        return result;
    }

    //Level Averages in a Binary Tree
    public static List<List<Integer>> averageOfLevels(ListNode root) {
        //base case if root node is null
        //while currentlevel is not null
        //add left + right then divide by # of leafs
        //return result
        //move down tree
        //once reached end of tree, break
        List<Double> result = new ArrayList<>();
        if (root == null)
          return result;
    
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        while (!queue.isEmpty()) {
          int levelSize = queue.size();
          double levelSum = 0;
          for (int i = 0; i < levelSize; i++) {
            TreeNode currentNode = queue.poll();
            // add the node's value to the running sum
            levelSum += currentNode.val;
            // insert the children of current node to the queue
            if (currentNode.left != null)
              queue.offer(currentNode.left);
            if (currentNode.right != null)
              queue.offer(currentNode.right);
          }
          // append the current level's average to the result array
          result.add(levelSum / levelSize);
        }
    
        return result;        
    }

    //Minimum Depth of a Binary Tree
    public static int findDepth(TreeNode root) {
        if (root == null)
          return 0;
    
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        int minimumTreeDepth = 0;
        while (!queue.isEmpty()) {
            minimumTreeDepth++;
            int levelSize = queue.size();
            for (int i = 0; i < levelSize; i++) {
                TreeNode currentNode = queue.poll();
        
                // check if this is a leaf node
                if (currentNode.left == null && currentNode.right == null)
                return minimumTreeDepth;
        
                // insert the children of current node in the queue
                if (currentNode.left != null)
                queue.add(currentNode.left);
                if (currentNode.right != null)
                queue.add(currentNode.right);
            }
        }
        return minimumTreeDepth;
    }    

    //Level Order Successor
    public static int successor(TreeNode root){
        if (root == null)
            return null;
  
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            TreeNode currentNode = queue.poll();
            // insert the children of current node in the queue
            if (currentNode.left != null)
                queue.offer(currentNode.left);
            if (currentNode.right != null)
                queue.offer(currentNode.right);
    
            // break if we have found the key
            if (currentNode.val == key)
                break;
        }
  
        return queue.peek(); 
    }

    public static void connect(TreeNode root) {
        if (root == null)
            return;
  
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            TreeNode previousNode = null;
            int levelSize = queue.size();
            // connect all nodes of this level
            for (int i = 0; i < levelSize; i++) {
                TreeNode currentNode = queue.poll();
                if (previousNode != null)
                    previousNode.next = currentNode;
                previousNode = currentNode;
        
                // insert the children of current node in the queue
                if (currentNode.left != null)
                    queue.offer(currentNode.left);
                if (currentNode.right != null)
                    queue.offer(currentNode.right);
            }
        }
    }
    
    public static void connect2(TreeNode root) {
        if (root == null)
            return;
  
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        TreeNode currentNode = null, previousNode = null;
        while (!queue.isEmpty()) {
            currentNode = queue.poll();
            if (previousNode != null)
                previousNode.next = currentNode;
            previousNode = currentNode;
    
            // insert the children of current node in the queue
            if (currentNode.left != null)
                queue.offer(currentNode.left);
            if (currentNode.right != null)
                queue.offer(currentNode.right);
        }
    }

    public static List<TreeNode> traverseRight(TreeNode root) {
        List<TreeNode> result = new ArrayList<>();
        if (root == null)
          return result;
    
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        while (!queue.isEmpty()) {
          int levelSize = queue.size();
          for (int i = 0; i < levelSize; i++) {
            TreeNode currentNode = queue.poll();
            // if it is the last node of this level, add it to the result
            if (i == levelSize - 1)
              result.add(currentNode);
            // insert the children of current node in the queue
            if (currentNode.left != null)
              queue.offer(currentNode.left);
            if (currentNode.right != null)
              queue.offer(currentNode.right);
          }
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
        // TreeNode result = LevelOrderSuccessor.findSuccessor(root, 12);
        // if (result != null)
        //   System.out.println(result.val + " ");
        // result = LevelOrderSuccessor.findSuccessor(root, 9);
        // if (result != null)
        //   System.out.println(result.val + " ");
        connect2(root);
        System.out.println("Level order traversal using 'next' pointer: ");
        root.printTree();        
    }        
}