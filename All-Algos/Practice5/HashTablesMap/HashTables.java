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

}