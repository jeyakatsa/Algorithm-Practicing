public class DeleteByValue<T> {
    public class Node {
        public T data;
        public Node nextNode;
    }

    public Node headNode;
    public int size;

    public DeleteByValue() {
        headNode = null;
        size = 0;
    }

    public boolean isEmpty() {
        if(headNode == null) return true;
        return false;
    }

    public void deleteByValue(T data) {
        //if empty then simply return
        if(isEmpty()){
            return;
        }

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
            if (data.equals(currentNode.data)) {
                prevNode.nextNode = currentNode.nextNode;
                return;
            }
            prevNode = currentNode;
            currentNode = currentNode.nextNode;
        }
    }


    public void printList() {
        if(isEmpty()){
            System.out.println("List is empty");
            return;
        }

        Node temp = headNode;
        System.out.print("List : ");
        while(temp.nextNode != null) {
            System.out.println(temp.data.toString() + " -> ");
            temp = temp.nextNode;
        }            
        System.out.println(temp.data.toString() + " -> null");    
    }
    
}
