import java.util.*;

public class Arrays1 {

    public static void main (String[] args) {

    }


    //TWO SUM
    //O(n^2) brute force solution...
    class Solution {
        public int[] twoSum(int[] nums, int target) {
            for (int i = 0; i < nums.length; i++) {
                for (int j = i + 1; j < nums.length; j++) {
                    if (nums[j] == target - nums[i]) {
                        return new int[] { i, j };
                    }
                }
            }
            // In case there is no solution, we'll just return null
            return null;
        }
    }
    //if including HashMap, then can separate for loops into solution O(n)


    //LRU Cache
    class LRUCache extends LinkedHashMap<Integer, Integer>{
        private int capacity;
        
        public LRUCache(int capacity) {
            super(capacity, 0.75F, true);
            this.capacity = capacity;
        }
    
        public int get(int key) {
            return super.getOrDefault(key, -1);
        }
    
        public void put(int key, int value) {
            super.put(key, value);
        }
    
        @Override
        protected boolean removeEldestEntry(Map.Entry<Integer, Integer> eldest) {
            return size() > capacity; 
        }
    }
  
}