import java.util.*;

public class Recursion {

    public static void main (String[] args) {
        TreeNode head = new TreeNode(1);
        head.next = new TreeNode(4);
        head.next.next = new TreeNode(7);
        head.next.next.next = new TreeNode(10);

        TreeNode head2 = new TreeNode(2);
        head2.next = new TreeNode(3);
        head2.next.next = new TreeNode(5);
        head2.next.next.next = new TreeNode(8);

        TreeNode result = mergeSorted(head, head2);

        List<Integer> result2 = rightSideView(head);        
        System.out.print("Merged Sorted List: ");
        while(result != null) {
            System.out.print(result.val + " ");
            result = result.next;
        }
    }

    public static class TreeNode {
        int val;
        TreeNode right;
        TreeNode left;
        TreeNode next;
        TreeNode () {}
        TreeNode (int val) {this.val = val;}
        TreeNode (int val, TreeNode right, TreeNode left, TreeNode next) {
            this.left = left;
            this.right = right;
            this.val = val;
            this.next = next;
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

    //LETTER COMBINATIONS OF NUMBERS
    Map<Integer, List<String>> digitMap = new HashMap<Integer, List<String>>();
    Recursion() {
        digitMap.put(2, Arrays.asList(new String[] {"a", "b", "c"}));
        digitMap.put(3, Arrays.asList(new String[] {"d", "e", "f"}));
        digitMap.put(4, Arrays.asList(new String[] {"g", "h", "i"}));
        digitMap.put(5, Arrays.asList(new String[] {"j", "k", "l"}));
        digitMap.put(6, Arrays.asList(new String[] {"m", "n", "o"}));
        digitMap.put(7, Arrays.asList(new String[] {"p", "q", "r", "s"}));
        digitMap.put(8, Arrays.asList(new String[] {"t", "u", "v"}));
        digitMap.put(9, Arrays.asList(new String[] {"w", "x", "y", "z"}));
    }
    public List<String> letterCombinations(String digits) {
        List<String> result = new ArrayList<String>();
        if(digits.length() == 0)
            return result;
        if(digits.length() == 1)
            return digitMap.get(digits.charAt(0) - '0');
        List<String> intermediate = letterCombinations(digits.substring(1, digits.length()));
        for(String first : digitMap.get(digits.charAt(0) - '0'))
            for(String rest : intermediate)
                result.add(first + rest);
        return result;
    } 

    //ADD TWO NUMBERS
    public static TreeNode addTwoNumbers(TreeNode l1, TreeNode l2) {
        TreeNode curr = new TreeNode(0);
        int carry = 0;
        while (l1 != null || l2 != null) {
            int x = (l1 != null) ? l1.val : 0;
            int y = (l2 != null) ? l2.val : 0;
            int sum = carry + x + y;
            carry = sum / 10;
            curr.next = new TreeNode(sum % 10);
            curr = curr.next;
            if (l1 != null) l1 = l1.next;
            if (l2 != null) l2 = l2.next;
        }
        if (carry > 0) {
            curr.next = new TreeNode(carry);
        }
        return curr.next;
    }

    //MERGE TWO SORTED LISTS
    public static TreeNode mergeSorted(TreeNode l1, TreeNode l2) {
        if (l1 == null || l2 == null) {
            return null;
        }
        //Create result TreeNode
        //Sorted here...
        if (l1.val <= l2.val) {
			l1.next = mergeSorted(l1.next, l2);
			return l1;
		}
        else {
			l2.next = mergeSorted(l1, l2.next);
			return l2;
		}    
    }

    //BINARY TREE RIGHT SIDE VIEW
    //Depth First Search
    public static List<Integer> rightSideView(TreeNode root) {
        if (root == null) {
            return null;           
        } 
        //Create List Integer
        List<Integer> rightSide = new ArrayList<>();
        showRightSideView(rightSide, root, 0);
        return rightSide;         
    } 
    public static void showRightSideView(List<Integer> rightSide, TreeNode node, int depth) {
        if (node == null)
            return;
        if (depth == rightSide.size()) {
            rightSide.add(node.val);
        }
        showRightSideView(rightSide, node.right, depth + 1);
        showRightSideView(rightSide, node.left, depth + 1);
    }          




}