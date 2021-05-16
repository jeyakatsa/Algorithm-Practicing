public class DynamicProgramming1 {
    public static void main(String[] args) {

    }

    //MAXIMUM SUBARRAY
    public static int maxSubarray(int[] nums) {
        int currMax=nums[0],max=nums[0];
        for(int i=1;i<nums.length;i++){
            currMax=Math.max(currMax+nums[i],nums[i]);
            max=Math.max(max,currMax);
        }
        return max;
    }        
}