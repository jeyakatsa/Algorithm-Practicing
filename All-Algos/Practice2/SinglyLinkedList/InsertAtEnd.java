public class InsertAtEnd<T> {
    //Node inner class for SLL
    public class Node {
        public T data;
        public Node nextNode;
    }
    
    public Node headNode; //head node of the linked list
    public int size; //size of the linked list

    //Constructor - initializes headNode and size
    public InsertAtEnd() {
        headNode = null;
        size = 0;
    }

    //Helper Function that checks if List is empty or not
    public boolean isEmpty() {
        if(headNode == null) return true;
        return false;
    }

    //Inserts new data at End
    public void InsertAtEnd(T data) {
        //if list is empty then call insertAtHead()
        if(isEmpty()) {
            InsertAtEnd(data);
            return;
        }
        //Creating a new Node with value data
        Node newNode = new Node();
        newNode.data = data;
        newNode.nextNode = null;

        Node last = headNode;
        //iterate to the last element
        while(last.nextNode != null) {
            last = last.nextNode;
        }
        //make newNode the next element of the last node
        last.nextNode = newNode;
        size++;
        
    }

    //Helper function to printList
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
}