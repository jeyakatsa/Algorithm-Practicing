import java.util.HashSet;

public class SinglyLinkedList<T> {
    public static void main( String args[] ) {
        // SinglyLinkedList<Integer> sll = new SinglyLinkedList<Integer>(); 
        // for (int i = 1; i <= 10; i++) {
		// 	sll.insertAtHead(i);
        // }
        // sll.insertAtEnd(4);
        // sll.insertAtEnd(4);
        // sll.printList();
        // System.out.println("After duplicates removed: ");
        // removeDuplicates(sll);
        // sll.printList();
        SinglyLinkedList<Integer> list1 = new SinglyLinkedList<Integer>();
        for(int i=7; i>3; i--){
            list1.insertAtHead(i);
        }
        System.out.print("1st ");
        list1.printList();
        SinglyLinkedList<Integer> list2 = new SinglyLinkedList<Integer>();
        for(int i=0; i<5; i++){
            list2.insertAtHead(i);
        }
        System.out.print("2nd ");
        list2.printList();
        System.out.print("After Intersection ");
        intersection(list1, list2).printList();
        System.out.print("After Union ");
        union(list1, list2).printList();
    }







    //Node inner class for SLL
    public class Node {
        public T data;
        public Node nextNode;

    }

    //head node of the linked list
    public Node headNode;
    public int size;

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

    //constructor
    public SinglyLinkedList() {
        headNode = null;
        size = 0;
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
        if(isEmpty())
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
        while(currentNode != null) {
            //node to delete is found
            if(data.equals(currentNode.data)){
                prevNode.nextNode = currentNode.nextNode;
                size--;
                return;
            }
            prevNode = currentNode;
            currentNode = currentNode.nextNode;
        }
    }

    //Reverse Linked List
    public static <T> void reverse(SinglyLinkedList<T> list) {
        //Traverse linked list,
        //create new list from old linkedlist nodes
        SinglyLinkedList<T>.Node prev = null; //To keep track of the previous element, will be used in swapping links
            SinglyLinkedList<T>.Node current = list.headNode; // firstElement
            SinglyLinkedList<T>.Node next = null;

            //While Traversing the list, swap links
            while (current != null) {
                next = current.nextNode;
                current.nextNode = prev;
                prev = current;
                current = next;
            }
            //Linking Head Node with the new First Element
            list.headNode = prev;
    }

    public static <T> boolean detectLoop(SinglyLinkedList<T> list) {
        SinglyLinkedList<T>.Node slow = list.headNode;
        SinglyLinkedList<T>.Node fast = list.headNode;

        while(slow != null && fast != null && fast.nextNode != null) {
            slow = slow.nextNode; //slow pointer will jump 1 step
            fast = fast.nextNode.nextNode; //fast pointer will jump 2 steps
            //when pointers become equal then there must be a loop
            if(slow == fast) {
                return true;
            }
        }
        return false;

    }

    public static <T> Object findMiddle(SinglyLinkedList<T> list){
        //Don't forget base/test case!
        if(list.isEmpty())
            return null;

        SinglyLinkedList<T>.Node mid = list.headNode;
        SinglyLinkedList<T>.Node current = list.headNode;

        while(mid != null && current != null && current.nextNode != null){
            current = current.nextNode.nextNode;
            if(current != null) {
                mid = mid.nextNode;
            }
        }
        return mid.data;
    }

    public static <T> void removeDuplicates (SinglyLinkedList<T> list) {
        SinglyLinkedList<T>.Node current = list.headNode; //outer loop
        SinglyLinkedList<T>.Node compare = null; //inner loop

        while(current != null && current.nextNode != null) {
            compare = current;
            while (compare.nextNode != null) {
                if(current.data.equals(compare.nextNode.data)) {
                    compare.nextNode = compare.nextNode.nextNode;
                } else {
                    compare = compare.nextNode;
                }
            }
            current = current.nextNode;
        }
    }

    public void removeDuplicatesWithHashing() {
        Node current = this.headNode;
        Node prevNode = this.headNode;
        //will store all the elements that we observe once
        HashSet<T> visitedNodes = new HashSet<T>();

        if (!isEmpty() && current.nextNode != null) {
            while (current != null) {
                //check if already visited then delete this node
                if (visitedNodes.contains(current.data)) {
                    //deleting the node by undating the pointer of previous node
                    prevNode.nextNode = current.nextNode;
                    current = current.nextNode;
                } else {
                    //if node was not already visited then add it to the visited set
                    visitedNodes.add(current.data);
                    //moving on to next element in the list
                    prevNode = current;
                    current = current.nextNode;
                }
            }
        }
    }

    //Union & Intersection of Lists
    public static <T> SinglyLinkedList<T> union(SinglyLinkedList<T> list1,
    SinglyLinkedList<T> list2) {
        //if one of the list is empty then return other list
        if(list1.isEmpty())
            return list2;
        if(list2.isEmpty())
            return list1;
        //take the head of the first list        
        SinglyLinkedList<T>.Node last = list1.getHeadNode();
        //traverse it to the last element
        while (last.nextNode != null) {
            last = last.nextNode;
        }
        //attach the last element of list1 to head of list2
        last.nextNode = list2.getHeadNode();
        //remove duplicates that might have been added now
        list1.removeDuplicatesWithHashing();//Complexity of this is O(n)
        return list1;
    }


    //Helper function which checks if the element is present in the list
    public static <T> boolean contains(SinglyLinkedList<T> list, T data) {
        SinglyLinkedList<T>.Node current = list.getHeadNode();
        //traverses the whole list
        while (current != null) {
            //returns true if found
            if(current.data.equals(data))
                return true;
            current = current.nextNode;    
        }
        //returns false if not found
        return false;
    }

    public static <T> SinglyLinkedList<T> intersection(SinglyLinkedList<T> list1,
    SinglyLinkedList<T> list2) {
        SinglyLinkedList<T> result = new SinglyLinkedList<T>();
        //returns empty list if either list is empty
        if(list1.isEmpty() || list2.isEmpty())
            return result;

        SinglyLinkedList<T>.Node current = list1.getHeadNode();
        //traverses list1 and checks if each element is present in list2
        while (current != null) {
            if(contains(list2, current.data)) {
                //inserts in result if it is present in both
                result.insertAtHead(current.data);
            }
            current = current.nextNode;
        }    
        return result;
    }

    public static <T> Object nthElementFromEnd(SinglyLinkedList<T> list, int n) {

        int size = list.getSize();
        n = size - n + 1; // we can use the size variable to calculate distance from start
        if (size == 0 || n > size) {
            return null; //returns null if list is empty or n is greater than size
        }
        SinglyLinkedList<T>.Node current = list.getHeadNode();
        int count = 2;
        //traverse until count is not equal to n
        while(current != null) {
            if(count == n)
                return current.data;
            count++;
            current = current.nextNode;    
        }
        return null;
    }
}
