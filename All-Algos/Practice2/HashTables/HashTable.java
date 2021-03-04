import java.util.*;

public class HashTable {
    public static void main (String[] args){
        // int[][] arr = {{1, 2}, {3, 4}, {5, 9}, {4, 3}, {9, 5}};
        // String symmetric = findSymmetric(arr);
        // System.out.println(symmetric);




        // HashMap<String,String> hMap = new HashMap<String, String>();  

        // hMap.put("NewYork","Chicago");
        // hMap.put("Boston","Texas");
        // hMap.put("Missouri","NewYork");
        // hMap.put("Texas","Missouri");
    
        // String actual_output = HashTable.tracePath(hMap);
    
        // System.out.println(actual_output);

        int[] arr = {3, 4, 7, 1, 12, 9};
        System.out.println(findSubZero(arr));


    }

    //Class to represent entire hash table
    class HashEntry {
        //to store chains of slots
        String key;
        int value;

        //Reference to next entry
        HashEntry next;

        //Constructor
        public HashEntry(String key, int value) {
            this.key = key;
            this.value = value;
        }
    }

    private ArrayList <HashEntry> bucket;
    private int slots;
    private int size;

    //Consturoctor
    public HashTable() {
        bucket = new ArrayList < HashEntry >();
        slots = 10;
        size = 0;
        //initialize buckets
        for(int i =0; i < slots; i++)
        bucket.add(null);
    }

    public int size(){
        return size;
    }
    public boolean isEmpty() {
        return size() == 0;
    }

    private int getIndex(String key) {
        //hashCode is a built in function of Strings
        int hashCode = key.hashCode();
        int index = hashCode % slots;
        //check if index is neg
        if (index < 0) {
            index = (index + slots) % slots;
        }
        return index;

    }

    public void insert (String key, int value) {
        //find head of chain
        int b_Index = getIndex(key);
        HashEntry head = bucket.get(b_Index);

        //Checks if the key exists
        while(head != null) {
            if(head.key.equals(key)) {
                head.value = value;
                return;
            }
            head = head.next;
        }

        //Inserts key in the chain
        size++;
        head = bucket.get(b_Index);
        HashEntry new_slot = new HashEntry(key, value);
        new_slot.next = head;
        bucket.set(b_Index, new_slot);

        //Checks if array > 60% of the array that gets filled
        if ((1.0*size)/slots >= 0.6) {
            ArrayList<HashEntry> temp = bucket;
            bucket = new ArrayList();
            slots = 2 * slots;
            size = 0;
            for (int i = 0; i < slots; i++)
                bucket.add(null);

            for (HashEntry head_Node : temp) {
                while(head_Node != null) {
                    insert(head_Node.key, head_Node.value);
                    head_Node = head_Node.next;
                }
            }    

        }
    }

    //Return value for a given key
    public int getValue(String key) {

        //find the head of chain
        int b_Index = getIndex(key);
        HashEntry head = bucket.get(b_Index);

        //Search key in the slots
        while(head != null) {
            if(head.key.equals(key))
                return head.value;
            head = head.next;    
        }

        //If key not found
        return -1;
    }

    //Remove a value based with key
    public int delete(String key) {

        //Find index
        int b_Index = getIndex(key);

        //Get head of the chain for that index
        HashEntry head = bucket.get(b_Index);

        //Find the key in slots
        HashEntry prev = null;
        while (head != null) {
            //if key exists
            if (head.key.equals(key))
                break;
            
            //Else keep moving in chain
            prev = head;
            head = head.next;    
        }

        //If key doesn't exist
        if(head == null)
            return -1;

        //Decrease size by one
        size--;
        
        //remove key
        if(prev != null)
            prev.next = head.next;
        else
            bucket.set(b_Index, head.next);
            
        return head.value;    
    }

    public static boolean isSubset(int[] arr1, int[] arr2) {
        HashSet<Integer> hset = new HashSet<>();
        //hset stores all the values of arr1
        for(int i = 0; i < arr1.length; i++) {
            if(!hset.contains(arr1[i]))
                hset.add(arr1[i]);
        }

        //loop to check if all elements of arr2 also
        //lies in arr1
        for(int i = 0; i < arr2.length; i++) {
            if(!hset.contains(arr2[i]))
                return false;
        }
        return true;
    }

    public static boolean isDisjoint(int[] arr1, int[] arr2) {

        //Create HashSet and store all values of arr1
        HashSet<Integer> hset = new HashSet<>();

        for(int i = 0; i < arr1.length; i++) {
            if(!hset.contains(arr1[i]))
                hset.add(arr1[i]);
        }

        //Traverse arr2 and check if arr1 contains any arr2 element
        for (int i = 0; i < arr2.length; i++) {
            if(hset.contains(arr2[i]))
                return false;
        } 
        return true;
    }

    public static String findSymmetric(int [][] arr) {
        //Create an empty Hash Map
        //Traverse given Array
        //Look for second element of each pair in the hash. i.e for pair (1,2) look for key 2 in map.
        //If found then store it in the result array, otherwise insert the pair in hash
        HashMap<Integer, Integer> hashMap = new HashMap<Integer, Integer>();

        String result = "";

        for(int i = 0; i < arr.length; i++) {
            int first = arr[i][0];
            int second = arr[i][1];

            Integer value = hashMap.get(second);

            if(value != null && value == first) {
                //Symetric Pair found
                result += "{" + String.valueOf(second) + "," + String.valueOf(first) + "}"; 
            }
            else
                hashMap.put(first, second);
        }
        return result;
    }

    public static String tracePath(Map<String, String> map) {
        String result = "";

        //Create a reverse Map of given map i.e if given map has (N,C) then reverse map will have (C,N) as key value pair
        //Traverse original map and see if corresponding key exist in reverse Map
        //If it doesn't exist then we found our starting point.
        //After starting point is found, simply trace the complete path from original map.

        HashMap<String, String> reverseMap = new HashMap<String, String>();

        //To Fill reverse map, iterate thrugh given map
        for (Map.Entry <String, String> entry : map.entrySet())
            reverseMap.put(entry.getValue(), entry.getKey());

        //Find the starting point of itinerary
        String from = "";
        
        //Check if graph is disconnected
        int count = 0;

        for (Map.Entry <String, String> entry: map.entrySet()) {
            if (!reverseMap.containsKey(entry.getKey())){
                count++;
                from = entry.getKey();
                //break;
            }
        }

        if(count > 1) {
            return "null"; //Disconnected graph
        }

        //Trace complete path
        String to = map.get(from);

        while (to != null) {
            result += from + "->" + to + ", ";
            from = to;
            to = map.get(to);
        }
        return result;

    }

    public static String findPair(int[] arr) {
        //Got the Brute Force solution down....
        //loop through arr with i integer starting from beginning to end
        //create result for arr with i result implementing it
        //loop through arr with j integer+ starting from  beginning tp end
        //create result for arr with j result implementing it
        //if result1 = result 2, return them both,
        //else return null

        String result = "";
        // Create HashMap with Key being sum and value being a pair i.e key = 3 , value = {1,2}
        // Traverse all possible pairs in given arr and store sums in map
        // If sum already exist then print out the two pairs.
        HashMap <Integer, int[]> hMap = new HashMap <>();

        for (int i = 0; i < arr.length; i++) {
            for (int j = i + 1; j < arr.length; ++j) {
                int sum = arr[i] + arr[j]; //calculate sum

                if(!hMap.containsKey(sum)) {
                    //If sum is not present in Map then insert it along with pair
                    hMap.put(sum, new int[] {arr[i],arr[j]});
                }
                else {
                    //Sum already present in Map
                    int[] prev_pair = hMap.get(sum);

                    //Since array elements are distinct, we don't
                    //need to check if any element is common among pair
                    result += "{" + prev_pair[0] + "," + prev_pair[1] + "}{" + arr[i] + "," + arr[j] + "}";

                    return result;
                }
            }
        }
        return result;
    }

    public static boolean findSubZero(int[] arr) {

        HashMap<Integer, Integer> hMap = new HashMap<Integer, Integer>();
        //Brute force solution correct.
        //traverse arr i 
        //create temp arr and add i to temp arr,
        //if values in tempArr = 0,
        //return true, else return false
        //O(n)

        int sum = 0;

        for (var i = 0; i < arr.length; i++) {
            sum += arr[i];

            if(arr[i] == 0 || sum == 0 || hMap.get(sum) != null)
                return true;

            hMap.put(sum, i);    
        }

        return false;

    }



}