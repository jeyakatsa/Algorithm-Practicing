import java.util.*;

public class HashTables {


    //SUBARRAY SUM EQUALS K
    // public int[] twoSum(int[] nums, int target) {
    //     if (nums == null || nums.length == 0) {
    //         throw new IllegalArgumentException("Nope!");
    //     }
    //     HashMap<Integer, Integer> result = new HashMap<>();
    //     int[] ans = new int[2];
    //     for (int i = 0; i < nums.length; i++) {
    //         result.add(nums[i], nums[i++]);
    //         if (result.get(i) + list.get(i++) == target){
    //             ans{i, i++};            
    //         }
    //     } return ans;
    // } 
    public int[] twoSum(int[] nums, int target) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            int complement = target - nums[i];
            if (map.containsKey(complement)) {
                return new int[] { map.get(complement), i };
            }
            map.put(nums[i], i);
        }
        // In case there is no solution, we'll just return null
        return null;
    }

    //Longest Substring Without Repeating Characters
    public int lengthOfLongestSubstring(String s) {
        int[] chars = new int[128];

        int left = 0;
        int right = 0;

        int res = 0;
        while (right < s.length()) {
            char r = s.charAt(right);
            chars[r]++;

            while (chars[r] > 1) {
                char l = s.charAt(left);
                chars[l]--;
                left++;
            }

            res = Math.max(res, right - left + 1);

            right++;
        }
        return res;
    }    
 


}