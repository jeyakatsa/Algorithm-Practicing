import java.util.ArrayList;
import java.util.HashSet;

public class HashTable {
    public static void main (String[] args){
        int[] arr1 = {9, 4, 7, 1, -2, 6, 5};
        int[] arr2 = {7, 1, -2};
        int[] arr3 = {10, 12};
    
        System.out.println(isDisjoint(arr1, arr2));
        System.out.println(isDisjoint(arr1, arr3));

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

}