import java.util.*;


public class Recursion {

    public static class TreeNode {
        int val;
        TreeNode right;
        TreeNode left;
        TreeNode () {}
        TreeNode (int val) {this.val = val;}
        TreeNode (int val, TreeNode right, TreeNode left) {
            this.left = left;
            this.right = right;
            this.val = val;
        }
    }

    //RANGE SUM OF BST
    public int rangeSumBST(TreeNode root, int low, int high) {
        if (root == null) return 0;
        int sum = 0;
        if (root.val >= low && root.val <= high) sum += root.val;
        if (root.val > low) sum += rangeSumBST(root.left, low, high);
        if (root.val < high) sum += rangeSumBST(root.right, low, high);

        return sum;
    } 

    //KTH SYMBOL
    public int kthGrammar(int N, int K) {
        return Integer.bitCount(K - 1) & 1;
    }
    // think of the problem like this
    //n = row, k = column
    /*         0
          /        \
         0           1
       /   \       /   \
      0     1     1     0
     / \   / \   / \   / \
    0  1  1   0  1  0  0  1
    */ 
    

    
    //PARTITION TO K EQUAL SUM SUBSETS
    public boolean canPartitionKSubsets(int[] nums, int k) {
        int sum=0;
        for(int num:nums) sum+=num;
        
        if(sum%k!=0) return false;
        int target=sum/k;
        
        Arrays.sort(nums);
        if(target<nums[nums.length-1]) return false; 
        boolean[] used=new boolean[nums.length];
        
        return partition(nums,used,0,0,0,target,k);
    }
    
    private boolean partition(int[] nums,boolean[] used,int idx,int currentSum,int currentSet,int targetSum,int targetSet){
        if(currentSet==targetSet) return true;
        
        for(int i=idx;i<nums.length;i++){
		    /*
			   2 2 2 3
			   F T 
			*/
            if(used[i] || (i<nums.length-1 && nums[i]==nums[i+1] && used[i+1])) continue;
            
            int sum=currentSum+nums[i];
            if(sum>targetSum) break;
            
             used[i]=true;
            if(sum==targetSum && partition(nums,used,0,0,currentSet+1,targetSum,targetSet)){
                return true;
            }else if(partition(nums,used,i+1,sum,currentSet,targetSum,targetSet)){
                return true;
            }
            used[i]=false;
        }
        return false;
    }    
}