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
    public int subarraySum(int[] nums, int k) {
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


    //Verifying Alien Dictionary
    public boolean isAlienSorted(String[] words, String order) {
        int[] orderMap = new int[26];
        for (int i = 0; i < order.length(); i++){
            orderMap[order.charAt(i) - 'a'] = i;
        }

        for (int i = 0; i < words.length - 1; i++) {

            for (int j = 0; j < words[i].length(); j++) {
                // If we do not find a mismatch letter between words[i] and words[i + 1],
                // we need to examine the case when words are like ("apple", "app").
                if (j >= words[i + 1].length()) return false;

                if (words[i].charAt(j) != words[i + 1].charAt(j)) {
                    int currentWordChar = words[i].charAt(j) - 'a';
                    int nextWordChar = words[i + 1].charAt(j) - 'a';
                    if (orderMap[currentWordChar] > orderMap[nextWordChar]) return false;
                    // if we find the first different letter and they are sorted,
                    // then there's no need to check remaining letters
                    else break;
                }
            }
        }

        return true;
    } 


    //Insert Delete GetRandom
    Map<Integer, Integer> dict;
    List<Integer> list;
    Random rand = new Random();
    /** Initialize your data structure here. */
    public HashTables() {
      dict = new HashMap<>();
      list = new ArrayList<>();
    }
    /** Inserts a value to the set. Returns true if the set did not already contain the specified element. */
    public boolean insert(int val) {
      if (dict.containsKey(val)) return false;
  
      dict.put(val, list.size());
      list.add(list.size(), val);
      return true;
    }
    /** Removes a value from the set. Returns true if the set contained the specified element. */
    public boolean remove(int val) {
      if (! dict.containsKey(val)) return false;
  
      // move the last element to the place idx of the element to delete
      int lastElement = list.get(list.size() - 1);
      int idx = dict.get(val);
      list.set(idx, lastElement);
      dict.put(lastElement, idx);
      // delete the last element
      list.remove(list.size() - 1);
      dict.remove(val);
      return true;
    }
    /** Get a random element from the set. */
    public int getRandom() {
      return list.get(rand.nextInt(list.size()));
    }  
    
    
    //Group Anagrams
    public List<List<String>> groupAnagrams(String[] strs) {
        if (strs.length == 0) return new ArrayList<>();
        Map<String, List> ans = new HashMap<String, List>();
        for (String s : strs) {
            char[] ca = s.toCharArray();
            Arrays.sort(ca);
            String key = String.valueOf(ca);
            if (!ans.containsKey(key)) ans.put(key, new ArrayList<>());
            ans.get(key).add(s);
        }
        return new ArrayList<>(ans.values());
    }    
 


}