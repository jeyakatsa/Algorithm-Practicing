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
    

    
    //COPY AND RETURN LIST "RANDOM POINTER"
    class Node {
        int val;
        Node next;
        Node random;
    
        public Node(int val) {
            this.val = val;
            this.next = null;
            this.random = null;
        }
    }    
    public Node copyRandomList(Node head) {

        // HashMap which holds old nodes as keys and new nodes as its values. 
        HashMap<Node, Node> map = new HashMap<Node, Node>();
        
        //copy list nodes into hash table
        Node p = head;
        while(p != null){
            map.put(p, new Node(p.val));
            p = p.next;
        }
        
        //deep copy using hash table
        Node q = head;
        while(q != null){
            map.get(q).next = map.get(q.next);
            map.get(q).random = map.get(q.random);
            q = q.next;
        }
        
        //return new head
        return map.get(head);
    }



    //INSERT DELETE GETRANDOM
    /** Initialize your data structure here. */
    HashMap<Integer,Integer> list=null;
    int[] array=null;
    int index=0;
    Random random=null;
    /** Initialize your data structure here. */
    public HashTables() {
        list=new HashMap<Integer,Integer>();
        array=new int[100001];
        random=new Random();
    }  
    /** Inserts a value to the set. Returns true if the set did not already contain the specified element. */
    public boolean insert(int val) {
        if(list.containsKey(val)){
            return false;
        }
        else{
            array[index]=val;
            list.put(val,index);
            index++;
            return true;
        }
    } 
    /** Removes a value from the set. Returns true if the set contained the specified element. */
    public boolean remove(int val) {
        if(!list.containsKey(val)){
            return false;
        }
        else{
            int pos=list.remove(val);
            array[pos]=array[index-1];
            if(list.containsKey(array[index-1])){
            list.put(array[index-1],pos);
            }
            index--;
            return true;
        }
    }  
    /** Get a random element from the set. */
    public int getRandom() {
        return array[random.nextInt(index)];
    }


    //VERIFYING ALIEN DICTIONARY
    Map<Character, Integer> map;
    public boolean isAlienSorted(String[] words, String order) {
        map = new HashMap<>();
        for (int i = 0; i < order.length(); i++) {
            map.put(order.charAt(i), i);
        }
        for (int i = 0; i < words.length - 1; i++) {
            if (!compare(words[i], words[i + 1])) return false;
        }
        return true;
    }
    private boolean compare(String s1, String s2) {
        int l1 = s1.length(), l2 = s2.length();
        for (int i = 0, j = 0; i < l1 && j < l2; i++, j++) {
            if (s1.charAt(i) != s2.charAt(j)) {
                if (map.get(s1.charAt(i)) > map.get(s2.charAt(j))) {
                    return false;
                } else {
                    return true;
                }
            }
        }
        if (l1 > l2) return false;
        return true;
    }


    //SUBDOMAIN VISITS
    public List<String> subdomainVisits(String[] cpdomains) {
        Map<String, Integer> counts = new HashMap<>();
        for (String domain: cpdomains) {
            String[] cpinfo = domain.split("\\s+");
            String[] frags = cpinfo[1].split("\\.");
            int count = Integer.valueOf(cpinfo[0]);
            String cur = "";
            for (int i = frags.length - 1; i >= 0; --i) {
                cur = frags[i] + (i < frags.length - 1 ? "." : "") + cur;
                counts.put(cur, counts.getOrDefault(cur, 0) + count);
            }
        }

        List<String> ans = new ArrayList<>();
        for (String dom: counts.keySet())
            ans.add("" + counts.get(dom) + " " + dom);
        return ans;
    }  


}