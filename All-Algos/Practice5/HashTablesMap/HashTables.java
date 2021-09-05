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
    
    //Design Underground System
    class UndergroundSystem {
        Map<String, Integer> totalTravels = new HashMap<>();// keeps count of travel from x-> y
        Map<String, Long> totalTimeStay = new HashMap<>();// keeps time stay from x-> y
        Map<Integer, String> stationName = new HashMap<>();//keeps stationName status of customer ID
        Map<Integer, Integer> time = new HashMap<>();//keeps checkin time of customer ID
    
        public UndergroundSystem() {
    
        }
    
        public void checkIn(int id, String stationName, int t) {
            this.stationName.put(id, stationName);// where is custID
            time.put(id, t);// put check in time
        }
    
        public void checkOut(int id, String stationName, int t) {
            String code = this.stationName.get(id) + "*" + stationName;// custID start-end
            int cha = t - time.get(id);
            this.stationName.remove(id);
            time.remove(id);
            if (!totalTravels.containsKey(code)) //travelled 1st time
                totalTravels.put(code, 0);
            totalTravels.put(code, totalTravels.get(code) + 1);// update total 
            if (!totalTimeStay.containsKey(code)) 
                totalTimeStay.put(code, 0L);
            totalTimeStay.put(code, totalTimeStay.get(code) + cha);
        }
    
        public double getAverageTime(String startStation, String endStation) {
            String code = startStation + "*" + endStation;
            double x = totalTimeStay.get(code);
            return x / totalTravels.get(code);
        }
    }

    //Time Based Key-Value Store
    class TimeMap {
        Map<String,Queue<TimeStamp>> map;
       /** Initialize your data structure here. */
       public TimeMap() {
           map = new HashMap<>();
       }
       
       public void set(String key, String value, int timestamp) {
           //instantiate a timestamp object with the time and the value.
           TimeStamp ts = new TimeStamp(timestamp,value);
           if(map.containsKey(key)){
               Queue<TimeStamp> current = map.get(key);
               //if we have seen this key before at to its queue.
               current.add(ts);
               map.put(key,current);
           }
           else{
               //if we havent seen it, add it and create a new queue with its value.
               //default ordering of priority queue in java is minheap, use collections to fix this.
               Queue<TimeStamp> current = new PriorityQueue<>(Collections.reverseOrder());
               current.add(ts);
               map.put(key, current);
           }
       }
       
       public String get(String key, int timestamp) {
           //this key is not found.
           if(!map.containsKey(key)){
               return "";
           }
           else{
               Queue<TimeStamp> current = map.get(key);
               List<TimeStamp> copy = new ArrayList<>();
               //search for greatest timestamp less than or equal to the one we are looking for.
               while(!current.isEmpty() && current.peek().time > timestamp){
                   //copy the elements that we have popped to re add them later.
                   copy.add(current.poll());
               }
               //while loop breaks at first time stamp less than or equal to our timestamp. return current.peek UNLESS the queue is empty which means every time stamp 
               //was greater. In this case return empty string.
               String val = current.isEmpty() ? "" : current.peek().value;
               //add back values we popped in our search.
               current.addAll(copy);
               return val;
           }
           
       }
   }
   class TimeStamp implements Comparable<TimeStamp>{
       //timestamp object maintains both time and value.
       public int time; 
       public String value;
       TimeStamp(int time, String value){
         this.time=time;
         this.value=value;
       }
       //compare the nodes based on time so that the heap will be ordered correctly. This is an interface in Java, if not clear research comparable interface.
       public int compareTo(TimeStamp t ){
           if(this.time==t.time){
               return 0;
           }
           else{
               return this.time > t.time ? 1 : -1;
           }
       }
           
   }   
    

 


}