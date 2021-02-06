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
        if(isEmpty()){
            return;
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
