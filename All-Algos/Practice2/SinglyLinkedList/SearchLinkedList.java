public class SearchLinkedList<T> {
    public class Node {
        public T data;
        public Node nextNode;
    }

    public Node headNode;
    public int size;

    public SearchLinkedList() {
        headNode = null;
        size = 0;
    }

    public boolean isEmpty() {
        if(headNode == null) return true;
        return false;
    }


    //Search linked list for data if true
    public boolean searchNode(T data) {
        //start from first element
        Node currentNode = this.headNode;

        //Traverse linked list, if true. return
        //else return false
        while (currentNode != null) {
            if(currentNode.data.equals(data))
                return true; //value found

            currentNode = currentNode.nextNode;    
        }
        return false; //value not found
    }


    //Deletes data from the head of list
    public void deleteAtHead() {
        //if list is empty then simply return
        if(isEmpty())
            return;
        //make the nextNode of the headNode equal to new headNode
        headNode = headNode.nextNode;
        size--;    
    }


    //........................................................
    public void printList() {
        if(isEmpty()) {
            System.out.println("List is Empty!");
            return;
        }

        Node temp = headNode;
        System.out.print("List : ");
        while(temp.nextNode != null) {
            System.out.print(temp.data.toString() + " -> ");
            temp = temp.nextNode;
        }
        System.out.println(temp.data.toString() + " -> null");
    }
    
}
