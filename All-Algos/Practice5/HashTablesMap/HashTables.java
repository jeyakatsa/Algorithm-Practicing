import java.util.*;

public class HashTables {

    //SUBARRAY SUM EQUALS K
    public int subarraySum(int[] nums, int k) {
        //Possible Brute Force Solution:
        //base case if nums and k equal null, return illegalargumentexception
        //create result
        //loop through array
        //if int[i] + int[i++] == k (window)
        //return result
        int count = 0, sum = 0;
        HashMap < Integer, Integer > map = new HashMap < > ();
        map.put(0, 1);
        for (int i = 0; i < nums.length; i++) {
            sum += nums[i];
            if (map.containsKey(sum - k))
                count += map.get(sum - k);
            map.put(sum, map.getOrDefault(sum, 0) + 1);
        }
        return count;        
    } 

    //TWO SUM (Which Subarrays Equal Target)
    public int[] twoSum(int[] nums, int target) {
    // MY SOLUTION    
    //   if (nums == null || nums.length == 0 || target == 0) {
    //       throw new IllegalArgumentException("Error: Check if array or target has proper integers!");
    //   }
    //   int sum = 0;
    //   int[] result;
    //   HashMap<Integer, Integer> map = new HashMap <>();
    //   for (int i = 0; i < nums.length; i++) {

    //   }
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            int complement = target - nums[i];
            if (map.containsKey(complement)) {
                return new int[] { map.get(complement), i };
            }
            map.put(nums[i], i);
        }
        throw new IllegalArgumentException("No two sum solution");
    }   
    
    //LONGEST SUBSTRING WITHOUT REPEATING CHARACTERS
    public int lengthOfLongestSubstring(String s) {
        int ret = 0;
        Map<Character, Integer> map = new HashMap<>();
        for (int i = 0, start = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (map.containsKey(c)) 
                start = Math.max(map.get(c)+1, start);
            ret = Math.max(ret, i-start+1); 
            map.put(c, i);
        }
        return ret;
    }    

}