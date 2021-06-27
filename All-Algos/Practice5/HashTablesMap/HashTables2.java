import java.util.*;

public class HashTables2 {



    //4 SUM
    public List<List<Integer>> fourSum(int[] nums, int target) {
        Arrays.sort(nums);
        return kSum(nums, target, 0, 4);
    }
    public List<List<Integer>> kSum(int[] nums, int target, int start, int k) {
        List<List<Integer>> res = new ArrayList<>();
        if (start == nums.length || nums[start] * k > target || target > nums[nums.length - 1] * k)
            return res;
        if (k == 2)
            return twoSum(nums, target, start);
        for (int i = start; i < nums.length; ++i)
            if (i == start || nums[i - 1] != nums[i])
                for (var set : kSum(nums, target - nums[i], i + 1, k - 1)) {
                    res.add(new ArrayList<>(Arrays.asList(nums[i])));
                    res.get(res.size() - 1).addAll(set);
                }
        return res;
    }
    public List<List<Integer>> twoSum(int[] nums, int target, int start) {
        List<List<Integer>> res = new ArrayList<>();
        Set<Integer> s = new HashSet<>();
        for (int i = start; i < nums.length; ++i) {
            if (res.isEmpty() || res.get(res.size() - 1).get(1) != nums[i])
                if (s.contains(target - nums[i]))
                    res.add(Arrays.asList(target - nums[i], nums[i]));
            s.add(nums[i]);
        }
        return res;
    }   


    //LONGEST STRING CHAIN
    public int longestStrChain(String[] words) {
        Map<String, Integer> dp = new HashMap<>();

        // Sorting the list in terms of the word length.
        Arrays.sort(words, (a, b) -> a.length() - b.length());

        int longestWordSequenceLength = 1;

        for (String word : words) {
            int presentLength = 1;
            // Find all possible predecessors for the current word by removing one letter at a time.
            for (int i = 0; i < word.length(); i++) {
                StringBuilder temp = new StringBuilder(word);
                temp.deleteCharAt(i);
                String predecessor = temp.toString();
                int previousLength = dp.getOrDefault(predecessor, 0);
                presentLength = Math.max(presentLength, previousLength + 1);
            }
            dp.put(word, presentLength);
            longestWordSequenceLength = Math.max(longestWordSequenceLength, presentLength);
        }
        return longestWordSequenceLength;
    }


    //Find Duplicate File in System
    public List < List < String >> findDuplicate(String[] paths) {
        List < String[] > list = new ArrayList < > ();
        for (String path: paths) {
            String[] values = path.split(" ");
            for (int i = 1; i < values.length; i++) {
                String[] name_cont = values[i].split("\\(");
                name_cont[1] = name_cont[1].replace(")", "");
                list.add(new String[] {
                    values[0] + "/" + name_cont[0], name_cont[1]
                });
            }
        }
        boolean[] visited = new boolean[list.size()];
        List < List < String >> res = new ArrayList < > ();
        for (int i = 0; i < list.size() - 1; i++) {
            if (visited[i])
                continue;
            List < String > l = new ArrayList < > ();
            for (int j = i + 1; j < list.size(); j++) {
                if (list.get(i)[1].equals(list.get(j)[1])) {
                    l.add(list.get(j)[0]);
                    visited[j] = true;
                }
            }
            if (l.size() > 0) {
                l.add(list.get(i)[0]);
                res.add(l);
            }
        }
        return res;
    }


    //First Unique Character in a String
    public int firstUniqChar(String s) {
        HashMap<Character, Integer> count = new HashMap<Character, Integer>();
        int n = s.length();
        // build hash map : character and how often it appears
        for (int i = 0; i < n; i++) {
            char c = s.charAt(i);
            count.put(c, count.getOrDefault(c, 0) + 1);
        }
        
        // find the index
        for (int i = 0; i < n; i++) {
            if (count.get(s.charAt(i)) == 1) 
                return i;
        }
        return -1;
    }



    //LRU Cache
    class DLinkedNode {
        int key;
        int value;
        DLinkedNode prev;
        DLinkedNode next;
    }
    private void addNode(DLinkedNode node) {
        /**
         * Always add the new node right after head.
         */
        node.prev = head;
        node.next = head.next;
    
        head.next.prev = node;
        head.next = node;
    }  
    private void removeNode(DLinkedNode node){
        /**
         * Remove an existing node from the linked list.
         */
        DLinkedNode prev = node.prev;
        DLinkedNode next = node.next;
    
        prev.next = next;
        next.prev = prev;
    }  
    private void moveToHead(DLinkedNode node){
        /**
         * Move certain node in between to the head.
         */
        removeNode(node);
        addNode(node);
    } 
    private DLinkedNode popTail() {
        /**
         * Pop the current tail.
         */
        DLinkedNode res = tail.prev;
        removeNode(res);
        return res;
    }  
    private Map<Integer, DLinkedNode> cache = new HashMap<>();
    private int size;
    private int capacity;
    private DLinkedNode head, tail; 
    public HashTables2(int capacity) {
        this.size = 0;
        this.capacity = capacity;
    
        head = new DLinkedNode();
        // head.prev = null;
    
        tail = new DLinkedNode();
        // tail.next = null;
    
        head.next = tail;
        tail.prev = head;
    }  
    public int get(int key) {
        DLinkedNode node = cache.get(key);
        if (node == null) return -1;
    
        // move the accessed node to the head;
        moveToHead(node);
    
        return node.value;
    }
    public void put(int key, int value) {
        DLinkedNode node = cache.get(key);
    
        if(node == null) {
          DLinkedNode newNode = new DLinkedNode();
          newNode.key = key;
          newNode.value = value;
    
          cache.put(key, newNode);
          addNode(newNode);
    
          ++size;
    
            if(size > capacity) {
                // pop the tail
                DLinkedNode tail = popTail();
                cache.remove(tail.key);
                --size;
            }
        } else {
            // update the value.
            node.value = value;
            moveToHead(node);
        }
    } 
    
    
    
    
    // MY GOD...
    // //DESIGN UNDERGROUND SYSTEM
    // private Map<String, Pair<Double, Double>> journeyData = new HashMap<>();
    // private Map<Integer, Pair<String, Integer>> checkInData = new HashMap<>();
    // public HashTables2() {
    // }
    // public void checkIn(int id, String stationName, int t) {
    //     checkInData.put(id, new Pair<>(stationName, t));
    // }
    // public void checkOut(int id, String stationName, int t) {
    //     // Look up the check in station and check in time for this id.
    //     // You could combine this "unpacking" into the other lines of code
    //     // to have less lines of code overall, but we've chosen to be verbose
    //     // here to make it easy for all learners to follow.
    //     Pair<String, Integer> checkInDataForId = checkInData.get(id);
    //     String startStation = checkInDataForId.getKey();
    //     Integer checkInTime = checkInDataForId.getValue();
        
    //     // Lookup the current travel time data for this route.
    //     String routeKey = stationsKey(startStation, stationName);
    //     Pair<Double, Double> routeStats  = journeyData.getOrDefault(routeKey, new Pair<>(0.0, 0.0));
    //     Double totalTripTime = routeStats.getKey();
    //     Double totalTrips = routeStats.getValue();
        
    //     // Update the travel time data with this trip.
    //     double tripTime = t - checkInTime;
    //     journeyData.put(routeKey, new Pair<>(totalTripTime + tripTime, totalTrips + 1));
        
    //     // Remove check in data for this id.
    //     // Note that this is optional, we'll talk about it in the space complexity analysis.
    //     checkInData.remove(id);
    // }
    // public double getAverageTime(String startStation, String endStation) {
    //     // Lookup how many times this journey has been made, and the total time.
    //     String routeKey = stationsKey(startStation, endStation);
    //     Double totalTime = journeyData.get(routeKey).getKey();
    //     Double totalTrips = journeyData.get(routeKey).getValue();
    //     // The average is simply the total divided by the number of trips.
    //     return totalTime / totalTrips;
    // }
    // private String stationsKey(String startStation, String endStation) {
    //     return startStation + "->" + endStation;
    // }    


    
}
