import java.util.*;


class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;
  
    TreeNode(int x) {
      val = x;
    }
}
  
class TreePathSum {

    //Sum of Paths
    public static boolean hasPath(TreeNode root, int sum) {
        if (root == null) {
            return false;
        }

        if(root.val == sum && root.left == null && root.right == null) {
            return true;
        }

        return hasPath(root.left, sum - root.val) ||
        hasPath(root.right, sum - root.val);
    }



    //Find All Tree Paths
    public static List<List<Integer>> findPaths (TreeNode root, int sum) {
        List<List<Integer>> allPaths = new ArrayList<>();
        List<Integer> currentPath = new ArrayList<>();
        findPathsRecursive(root, sum, currentPath, allPaths);
        return allPaths;
    }
    public static void findPathsRecursive(TreeNode currentNode, int sum, 
    List<Integer> currentPath, List<List<Integer>> allPaths) {
        if (currentNode == null) {
            return;
        }

        //add current node to path
        currentPath.add(currentNode.val);

        //if current node is a leaf and its value is equal to sum, save path
        if (currentNode.val == sum && currentNode.left == null
        && currentNode.right == null) {
            allPaths.add(new ArrayList<Integer>(currentPath));
        } 
        else {
            //traverse left subtree
            findPathsRecursive(currentNode.left, sum - currentNode.val,
            currentPath, allPaths);
            //traverse right subtree
            findPathsRecursive(currentNode.right, sum - currentNode.val, 
            currentPath, allPaths);
        }

        currentPath.remove(currentPath.size() - 1);
    }



    //Sum of Path
    public static int findSumOfPathNumbers(TreeNode root) {
        return findSumOfPath(root, 0);
    }
    public static int findSumOfPath(TreeNode currentNode, int pathSum) {
        if (currentNode == null) {
            return 0;
        }

        //calculate the path number of the current node
        pathSum = 10 * pathSum + currentNode.val;

        //if the current node is a lead, return the current path sum.
        if (currentNode.left == null && currentNode.right == null) {
            return pathSum;
        }

        //traverse the left and the right subtree
        return findSumOfPath(currentNode.left, pathSum) +
        findSumOfPath(currentNode.right, pathSum);
    }

    //Find with Given Sequence.
    public static boolean findSequence(TreeNode root, int[] sequence) {
        if (root == null) {
            return sequence.length == 0;
        }
        return findSequenceRecursive(root, sequence, 0);
    }
    public static boolean findSequenceRecursive(TreeNode currentNode, 
    int[] sequence, int sequenceIndex) {
        if (currentNode == null) {
            return false;
        }
        if (sequenceIndex >= sequence.length || 
        currentNode.val != sequence[sequenceIndex]) {
            return false;
        }
        //if the current node is a leaf, add it is the end of the sequence,
        //we found a path!
        if (currentNode.left == null && currentNode.right == null
        && sequenceIndex == sequence.length -1) {
            return true;
        }
        //Recursively call to traverse left and right sub-tree
        //return true if any of the two recursive call return true
        return findSequenceRecursive(currentNode.left, sequence, sequenceIndex + 1) ||
        findSequenceRecursive(currentNode.right, sequence, sequenceIndex + 1);
    }
  
    public static void main (String[] args){
      TreeNode root = new TreeNode(12);
      root.left = new TreeNode(7);
      root.right = new TreeNode(1);
      root.left.left = new TreeNode(9);
      root.right.left = new TreeNode(10);
      root.right.right = new TreeNode(5);
    //   int sum = 23;
    //   List<List<Integer>> result = findPaths(root, sum);
    //   System.out.println("Tree paths with sum: " + sum + 
    //   ": " + result);

    // System.out.println("Sum of path: " + findSumOfPathNumbers(root));

    System.out.println("Tree has path in Sequence: " + findSequence(root, new int[] {12, 7, 9}));
    }
}