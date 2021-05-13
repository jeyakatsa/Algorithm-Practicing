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
                if (nums[j] == target - nums[i]) {
                    return new int[] { i, j };
                }
            }
        }
        return nums; 
    }


  
}