

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


    public static void main(String[] args) {
        TreeNode root = new TreeNode(12);
        root.left = new TreeNode(7);
        root.right = new TreeNode(1);
        root.left.left = new TreeNode(9);
        root.right.left = new TreeNode(10);
        root.right.right = new TreeNode(5);
        root.right.left.left = new TreeNode(20);
        root.right.left.right = new TreeNode(17);
        List<Double> result = LevelOrderTraversal.averageOfLevels(root);
        System.out.println("Level order traversal: " + result);
    }        
}