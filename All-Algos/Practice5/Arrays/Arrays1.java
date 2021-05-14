import java.util.*;

public class Arrays1 {

    public static void main (String[] args) {
        int[] nums = {1, 4, 6, 8, 20};
        System.out.println(twoSum(nums, 7));
    }


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

    public int maxSubArray(int[] nums) {
        int currMax=nums[0],max=nums[0];
        for(int i=1;i<nums.length;i++){
            currMax=Math.max(currMax+nums[i],nums[i]);
            max=Math.max(max,currMax);
        }
        return max;
    }
    
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

    // /**
    //  * @param {number[]} nums1
    //  * @param {number} m
    //  * @param {number[]} nums2
    //  * @param {number} n
    //  * @return {void} Do not return anything, modify nums1 in-place instead.
    //  */
    var nums1 = [1,2,3,4,5];
    var nums2 = [6,7,8,9,10];
    var merge = function (nums1, m, nums2, n) {
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
        int [] result = {nums1 + nums2};
    };    

  
}