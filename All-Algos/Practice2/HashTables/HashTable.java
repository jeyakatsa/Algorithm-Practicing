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


        

        // int[] arr = {2, 54, 7, 2, 6, 54};

        // System.out.println("Array: " + Arrays.toString(arr));

        // int unique = findFirstUnique(arr);
        // System.out.print("First Unique in an Array: " + unique);




        // SinglyLinkedList<Integer> list = new SinglyLinkedList<Integer>();

        // for(int i = 1; i <= 8; i++) {
        //     list.insertAtHead(i); //inserting data in list
        // }
        // //inserting duplicates
        // list.insertAtHead(2);
        // list.insertAtHead(4);
        // list.insertAtHead(1);
        
        // System.out.println("List before deleting duplicates from list :");
        // list.printList();
        // removeDuplicatesWithHashing(list); // calling removeDuplicatesWithHashing function
        // System.out.println("List after deleting duplicates from list :");
        // list.printList();




        // SinglyLinkedList<Integer> list1 = new SinglyLinkedList<Integer>();
        // for(int i=7; i>3; i--){
        //     list1.insertAtHead(i);
        // }
        // System.out.print("1st ");
        // list1.printList();
        // SinglyLinkedList<Integer> list2 = new SinglyLinkedList<Integer>();
        // for(int i=0; i<5; i++){
        //     list2.insertAtHead(i);
        // }
        // System.out.print("2nd ");
        // list2.printList();
        // System.out.print("After Intersection ");
        // intersectionWithHashing(list1, list2).printList();
        // System.out.print("After Union ");
        // unionWithHashing(list1, list2).printList();

        int n = 0;
        int[] arr1 = {};
        if(arr1.length > 0) {
            int[] arr2 = findSum(arr1, n);
            int num1 = arr2[0];
            int num2 = arr2[1];

            if ((num1 + num2) != n)
                System.out.println("Not Found");
            else {
                System.out.println("Number 1 = " + num1);
                System.out.println("Number 2 = " + num2);
                System.out.println("Sum = " + (n));
            }    
        } else {
            System.out.println("Input Array is Empty!");
        }



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

    public static int findFirstUnique(int[] arr) {

        HashMap<Integer, Integer> countElements = new HashMap<Integer, Integer>();
        //If the element does not exist in the HashMap
        //Add that element with value = 0
        //or else update the number of occurrences of that element by adding 1
        for (int i = 0; i < arr.length; i++) {
            if(countElements.containsKey(arr[i])){
                int occurence = countElements.get(arr[i]) + 1;
                countElements.put(arr[i], occurence);
            }
            else
                countElements.put(arr[i], 0);
        }
        //Traverse the array and check the number of occurences
        //Return the first element with 0 occurences
        for (int i = 0; i < arr.length; i++){
            if(countElements.get(arr[i]) == 0) {
                return arr[i];
            }
        }
        return -1;
    }

    //Remove Duplicate from a LinkedList with Hashing
    public static <V> void removeDuplicatesWithHashing(SinglyLinkedList<V> list) {
        SinglyLinkedList<V>.Node current = list.getHeadNode();
        SinglyLinkedList<V>.Node prevNode = list.getHeadNode();
        //will store all the elements that we observe once
        HashSet<V> visitedNodes = new HashSet<V>();

        if(!list.isEmpty() && current.nextNode != null) {
            while(current != null) {
                //check if already visited then delete node
                if(visitedNodes.contains(current.data)) {
                    //deleting the node by undating the pointer of previous node
                    prevNode.nextNode = current.nextNode;
                    current = current.nextNode;
                }
                else {
                    //if node was not already visited then add it to the visited set
                    visitedNodes.add(current.data);
                    //moving on to next element in the list
                    prevNode = current;
                    current = current.nextNode;
                }
            }
        }
    }

    //Find two numbers that Add up to "n"
    public static int[] findSum(int[] arr, int n) {

        //Iterate arr
        //if i + i++ == n, return i + i++ into new result arr
        //else return null

        int[] result = new int[2];
        Set<Integer> set = new HashSet<Integer>();
        for (int i: arr) {
            if(set.contains(n - i)) {
                result[0] = i;
                result[1] = n - i;
                break;
            }
            set.add(i);
        }


        return result;
    }

    //Performs union of two lists
    public static <T> SinglyLinkedList<T> unionWithHashing(
        SinglyLinkedList<T> list1, SinglyLinkedList<T> list2) {

        SinglyLinkedList<T> result = new SinglyLinkedList<T>();    

        HashSet<T> visited = new HashSet<T>();
        
        SinglyLinkedList<T>.Node current = list1.getHeadNode(); //start from list1's head
        //Keep iterating list1
        while(current!=null) {
            //add elements of list1 into the result if they haven't been visited before
            if(!visited.contains(current.data)) {
                result.insertAtHead(current.data);
                visited.add(current.data);
            }
            current = current.nextNode;
        }

        //now start from the head of list2
        current = list2.getHeadNode();

        //Keep iterating list2
        while(current != null) {
            //add elements of list2 into the result if they haven't been visited
            if(!visited.contains(current.data)) {
                result.insertAtHead(current.data);
                visited.add(current.data);
            }
        }
        
        return result;
    }

    //Performs intersection between list
    public static <T> SinglyLinkedList<T> intersectionWithHashing(
        SinglyLinkedList<T> list1, SinglyLinkedList<T> list2) {

        SinglyLinkedList<T> result = new SinglyLinkedList<T>();
        
        HashSet<T> visited = new HashSet<T>();
        //start from the head of list1
        SinglyLinkedList<T>.Node current = list1.getHeadNode();
        //Keep iterating list1
        while(current != null) {
            //Add elements to visited set if they have not been visited
            if(!visited.contains(current.data)){
                visited.add(current.data);
            }
            current = current.nextNode;
        }
        //take head of list2
        current = list2.getHeadNode();
        //iterate list2
        while(current != null) {
            //add elements visited into result
            if(visited.contains(current.data)) {
                result.insertAtHead(current.data);
                visited.remove(current.data);
            }
            current = current.nextNode;
        }
        return result;    

    }


























    public static class SinglyLinkedList<T> {
        //Node inner class for SLL
        public class Node {
            public T data;
            public Node nextNode;
    
        }
    
        //head node of the linked list
        public Node headNode;
        public int size;
    
        //constructor
        public SinglyLinkedList() {
            headNode = null;
            size = 0;
        }
    
         public Node getHeadNode() {
            return headNode;
        }
    
        public void setHeadNode(Node headNode) {
            this.headNode = headNode;
        }
    
        public int getSize() {
            return size;
        }
    
        public void setSize(int size) {
            this.size = size;
        }
        
        public boolean isEmpty() {
    
            if (headNode == null) return true;
            return false;
        }
    
        //Inserts new data at the start of the linked list
        public void insertAtHead(T data) {
            //Creating a new node and assigning it the new data value
            Node newNode = new Node();
            newNode.data = data;
            //Linking head to the newNode's nextNode
            newNode.nextNode = headNode;
            headNode = newNode;
            size++;
        }
    
        //Inserts new data at the end of the linked list
        public void insertAtEnd(T data) {
            //if the list is empty then call insertATHead()
            if (isEmpty()) {
                insertAtHead(data);
                return;
            }
            //Creating a new Node with value data
            Node newNode = new Node();
            newNode.data = data;
            newNode.nextNode = null;
    
            Node last = headNode;
            //iterate to the last element
            while (last.nextNode != null) {
                last = last.nextNode;
            }
            //make newNode the next element of the last node
            last.nextNode = newNode;
            size++;
        }
    
        //inserts data after the given prev data node
        public void insertAfter(T data, T previous) {
    
            //Creating a new Node with value data
            Node newNode = new Node();
            newNode.data = data;
            //Start from head node
            Node currentNode = this.headNode;
            //traverse the list until node having data equal to previous is found
            while (currentNode != null && currentNode.data != previous) {
                currentNode = currentNode.nextNode;
            }
            //if such a node was found
            //then point our newNode to currentNode's nextElement
            if (currentNode != null) {
                newNode.nextNode = currentNode.nextNode;
                currentNode.nextNode = newNode;
                size++;
            }
        }
    
        public void printList() {
            if (isEmpty()) {
                System.out.println("List is Empty!");
                return;
            }
    
            Node temp = headNode;
            System.out.print("List : ");
    
            while (temp.nextNode != null) {
                System.out.print(temp.data.toString() + " -> ");
                temp = temp.nextNode;
            }
    
            System.out.println(temp.data.toString() + " -> null");
        }
    
        //Searches a value in the given list.
        public boolean searchNode(T data) {
            //Start from first element
            Node currentNode = this.headNode;
    
            //Traverse the list till you reach end
            while (currentNode != null) {
                if (currentNode.data.equals(data))
                    return true; //value found
    
                currentNode = currentNode.nextNode;
            }
            return false; //value not found
        }
    
        //Deletes data from the head of list
        public void deleteAtHead() {
            //if list is empty then simply return
            if (isEmpty())
                return;
            //make the nextNode of the headNode equal to new headNode
            headNode = headNode.nextNode;
            size--;
        }
    
        //Deletes data given from the linked list
        public void deleteByValue(T data) {
            //if empty then simply return
            if (isEmpty())
                return;
    
            //Start from head node
            Node currentNode = this.headNode;
            Node prevNode = null; //previous node starts from null
    
            if(currentNode.data.equals(data)) {
                //data is at head so delete from head
                deleteAtHead();
                return;
            }
            //traverse the list searching for the data to delete
            while (currentNode != null) {
                //node to delete is found
                if (data.equals(currentNode.data)){
                    prevNode.nextNode = currentNode.nextNode;
                }
                prevNode = currentNode;
                currentNode = currentNode.nextNode;
            }
        }
        //Reverses the linked list
        public void reverse() {
            Node prev = null; //To keep track of the previous element, will be used in swapping links
            Node current = this.headNode; //firstElement
            Node next = null;
    
            //While Traversing the list, swap links
            while (current != null) {
                next = current.nextNode;
                current.nextNode = prev;
                prev = current;
                current = next;
            }
            //Linking Head Node with the new First Element
            this.headNode = prev;
        }
    
        public void removeDuplicates() {
            Node current = headNode; // will be used for outer loop
            Node compare = null;     // will be used for inner loop
    
            while (current != null && current.nextNode != null) {
                compare = current;
    
                while (compare.nextNode != null) {
                    if (current.data == compare.nextNode.data) {
                        compare.nextNode = compare.nextNode.nextNode;
                    } else {
                        compare = compare.nextNode;
                    }
                }
                current = current.nextNode;
            }
        }
    }



}