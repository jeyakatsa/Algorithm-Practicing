import java.util.*;

public class Arrays1 {

    public static void main (String[] args) {
        int[] nums = {1, 4, 6, 8, 20};
        int[] nums2 = {5, 9, 10};
        System.out.println(twoSum(nums, 7));
        System.out.println(buildTree(nums, nums2));


    }


    //TWO SUM
    //O(n) brute force solution...
    public static int[] twoSum(int[] nums, int target) {
        if (nums == null || nums.length == 0) {
            throw new IllegalArgumentException("No two sum solution");
        }
        for (int i = 0; i < nums.length; i++) {
            for (int j = i + 1; j < nums.length; j++) {
                if (nums[j] + nums[i] == target) {
                    return new int[] { i, j };
                }
            }
        }
        return nums; 
    }








    //MAXIMUM SUBARRAY1
    public int maxSubArray(int[] nums) {
        int currMax=nums[0],max=nums[0];
        for(int i=1;i<nums.length;i++){
            currMax=Math.max(currMax+nums[i],nums[i]);
            max=Math.max(max,currMax);
        }
        return max;
    }







    //MERGE INTERVALS    
    public int[][] merge(int[][] intervals) {
    
        //create empty dynamic array
        ArrayList<int[]> ans = new ArrayList<>();
        
        //Base conditions when no value present in intervals array
        if(intervals.length == 0 || intervals == null)
        {
            return ans.toArray(new int[0][]);
        }
        
        //Using comparable class and sort Pairs in ascending order
        Arrays.sort(intervals , (a , b) -> a[0] - b[0]);
        
        //Then create two pointers 1. start 2.end in pair
        int start = intervals[0][0];
        int end = intervals[0][1];
        
        //Using for loop to occurence in intervals one by one
        for(int i = 1; i < intervals.length; i++)
        {
            //Basically no overlap condition
            if(intervals[i][0] > end)
            {
                ans.add(new int[]{start , end});
                start = intervals[i][0];
                end = intervals[i][1];
            }
            
            //overlap condition
            else if(intervals[i][1] >= end)
            {
                end = intervals[i][1];
            }
        }
        
        //final pair
        ans.add(new int[]{start , end});
        return ans.toArray(new int[0][]);
    } 






    //MERGE SORTED ARRAY:
    //
    //
    // /**
    //  * @param {number[]} nums1
    //  * @param {number} m
    //  * @param {number[]} nums2
    //  * @param {number} n
    //  * @return {void} Do not return anything, modify nums1 in-place instead.
    //  */
    //My Soluton :WRONG:
    int[] nums1 = {1,2,3,4,5};
    int[] nums2 = {6,7,8,9,10};
    public void merge(int[]nums1, int m, int[]nums2, int n) {
        //brute force approach
        //basecase if nothing in nums1 and nums 2 arrays..
        //sort num1
        //if num1.length != m, subtract first m points, else break
        //sort num2
        //if num2.length != n, subtract first n points, else break
        //create result array
        //merge num1 + num2 into result array
        
        // if (nums1 == null || nums1.length == 0 || nums2 == null || nums2.length == 0) {
        //     return null;
        // }
        for (int i = 0; i < nums1.length; i++) {
            if (nums1[i] > nums1[i++]){
                int temp = nums1[i];
                nums1[i] = nums1[i++];
                nums1[i++] = temp;
            }
            if (nums1.length != m) {
                //will return to this once I figure out how to remove m elements from nums1.length
            }
        }
        for (int i = 0; i < nums2.length; i++) {
            if (nums2[i] > nums2[i++]){
                int temp = nums2[i];
                nums2[i] = nums2[i++];
                nums2[i++] = temp;
            }
            if (nums2.length != m) {
                //will return to this once I figure out how to remove m elements from nums2.length
            }
        }
        int [] result = {nums1, nums2};
    };    
    //Correct Solution: RIGHT:
    public void merge2(int[] nums1, int m, int[] nums2, int n) {
        int i = m-1, j = n-1, k = m+n-1;
        while (i>=0 && j>=0) {
             nums1[k--] = nums1[i]>nums2[j]?nums1[i--]:nums2[j--];
        }
        while (i==-1 && j>=0) 
            nums1[j] = nums2[j--];
    }   
    








    
    //CONSTRUCT BINARY TREE FROM INORDER, PREORDER TRAVERSAL
    static int preorderIndex;
    static Map<Integer, Integer> inorderIndexMap;
    public static TreeNode buildTree(int[] preorder, int[] inorder) {
        preorderIndex = 0;
        // build a hashmap to store value -> its index relations
        inorderIndexMap = new HashMap<>();
        for (int i = 0; i < inorder.length; i++) {
            inorderIndexMap.put(inorder[i], i);
        }
        return arrayToTree(preorder, 0, preorder.length - 1);
    }
    private static TreeNode arrayToTree(int[] preorder, int left, int right) {
        // if there are no elements to construct the tree
        if (left > right) return null;

        // select the preorder_index element as the root and increment it
        int rootValue = preorder[preorderIndex++];
        TreeNode root = new TreeNode(rootValue);

        // build left and right subtree
        // excluding inorderIndexMap[rootValue] element because it's the root
        root.left = arrayToTree(preorder, left, inorderIndexMap.get(rootValue) - 1);
        root.right = arrayToTree(preorder, inorderIndexMap.get(rootValue) + 1, right);
        return root;
    }
  


  
}