public class DoublyLinkedList<T> {
    public static void main(String[] args){
        // DoublyLinkedList<Integer> dll = new DoublyLinkedList<>();
        // for(int i = 1; i <= 10; i++) {
        //     dll.insertAtHead(i);
        // }

        // System.out.println(dll.length());

        // System.out.print("Original ");
        // dll.printList();
        // System.out.print("After deleting 10 ");
        // dll.deleteByValue(10);
        // dll.printList();
        // System.out.print("After deleting 1 ");
        // dll.deleteByValue(1);
        // dll.printList();
        // System.out.print("After deleting 5 ");
        // dll.deleteByValue(5);
        // dll.printList();

        DoublyLinkedList<Integer> list1 = new DoublyLinkedList<Integer>();
        list1.insertAtEnd(1);
        list1.insertAtEnd(2);
        list1.insertAtEnd(2);
        list1.insertAtEnd(1);
        System.out.print("1st ");
        list1.printList();
        DoublyLinkedList<Integer> list2 = new DoublyLinkedList<Integer>();
        list2.insertAtEnd(6);
        list2.insertAtEnd(8);
        list2.insertAtEnd(6);
        list2.insertAtEnd(6);
        System.out.print("2nd ");
        list2.printList();
        System.out.println("Is 1st list a palindrome?  " + isPalindrome(list1));
        System.out.println("Is 2nd list a palindrome?  " + isPalindrome(list2));
    }


    public class Node {
        public T data;
        public Node nextNode;
        public Node prevNode;
    }

    public Node headNode;
    public Node tailNode;
    public int size;

    public DoublyLinkedList() {
        this.headNode = null;
        this.tailNode = null;
    }

    //Checks if the list is empty
    public boolean isEmpty() {
        if(headNode == null) return true;
            return false;
    }

    public Node getHeadNode() {
        return headNode;
    }

    public Node getTailNode() {
        return tailNode;
    }

    public int getSize() {
        return size;
    }

    public void insertAtHead(T data) {
        //create node and put in the data
        Node newNode = new Node();
        newNode.data = data;
        //Make next of new node as head and previous as NULL
        newNode.nextNode = this.headNode;
        newNode.prevNode = null;
        //Change previous of head node to new node
        if(headNode != null)
            headNode.prevNode = newNode;
        this.headNode = newNode;
        size++;    
    }

    public void insertAtEnd(T data) {
        if(isEmpty()) {
            insertAtHead(data);
            return;
        }
        Node newNode = new Node();
        newNode.data = data;
        newNode.nextNode = null;
        newNode.prevNode = tailNode;
        tailNode.nextNode = newNode;
        tailNode = newNode;
        size++;
    }

    public void printList() {
        if (isEmpty()) {
            System.out.println("List is Empty!");
            return;
        }

        Node temp = headNode;
        System.out.print("List : null <- ");

        while (temp.nextNode != null) {
            System.out.print(temp.data.toString() + " <-> ");
            temp = temp.nextNode;
        }

        System.out.println(temp.data.toString() + " -> null");
    }

    public void printListReverse() {
        if(isEmpty()) {
            System.out.println("List is Empty!");
            return;
        }

        Node temp = tailNode;
        System.out.print("List : null <-");

        while(temp.prevNode != null) {
            System.out.print(temp.data.toString() + " <-> ");
            temp = temp.prevNode;
        }
        System.out.println(temp.data.toString() + " -> null");
    }

    //deletes the first element
    public void deleteAtHead() {
        //if list is empty do nothing
        if(isEmpty())
            return;

        //if List is not empty then link head to nextElement of firstEl
        headNode = headNode.nextNode;
        headNode.prevNode = null;
        size--;   
    }

    public void deleteAtTail() {
        if (isEmpty())
            return;
        tailNode = tailNode.prevNode;
        if(tailNode == null)
            headNode = null;
        else
            tailNode.nextNode = null;  
        size--;          
    }

    public void deleteByValue(T data) {
        //if empty then simply return
        if(isEmpty())
            return;

        //Start from head node
        Node currentNode = this.headNode;
        
        if(currentNode.data.equals(data)) {
            //data is at head so delete from head
            deleteAtHead();
            return;
        }
        //traverse the list searching for the data to delete
        while (currentNode != null) {
            //node to delete is found
            if(data.equals(currentNode.data)) {
                currentNode.prevNode.nextNode = currentNode.nextNode;
                if(currentNode.nextNode != null)
                    currentNode.nextNode.prevNode = currentNode.prevNode;
                size--;    
            }
            currentNode = currentNode.nextNode;
        }
    }

    //Calculate length
    public int length() {
        int count = 0;

        Node currentNode = this.headNode;

        //traverse linkedlist
        while(currentNode != null) {
            //increment count by number of nodes in list
            count++;
            currentNode = currentNode.nextNode;
        }

        return count;
    }

    public static <T> boolean isPalindrome(DoublyLinkedList<T> list) {
        DoublyLinkedList<T>.Node start = list.getHeadNode(); // get the head pointer
        DoublyLinkedList<T>.Node end = list.getTailNode(); // get tail
        if (start == null) { // if list is empty, it is a palindrome
            return true;
        }
        while(start != null) { //otherwise traverse list from both sides
            if (start.data != end.data) {//if data mismatches at any point list is not a palindrome
                return false;
            }
            start = start.nextNode;
            end = end.prevNode;
        }
        return true; // if data doens't mismatcch any point, true.
    }


    
}
