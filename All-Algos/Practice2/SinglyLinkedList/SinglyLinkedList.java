public class SinglyLinkedList<T> {
    public static void main( String args[] ) {
        SinglyLinkedList<Integer> sll = new SinglyLinkedList<Integer>(); 
        for (int i = 1; i <= 10; i++) {
			sll.insertAtHead(i);
        }
        System.out.println("Middle of LL: " + findMiddle(sll));
    }







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
}