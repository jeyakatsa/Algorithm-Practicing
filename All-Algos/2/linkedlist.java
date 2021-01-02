public class LinkedList {
    private Node first;
    private Node last;

    //addFirst
    public void addFirst(int item){
        if (item == null) {
             throw new NullPointerException("The first argument for addFirst() is null.");
            }
        if (!isEmpty()) {
            Node prev = first;
            first = new Node(item, null);
            prev.next = last;
        }
        else {
            
        } 
    }

    //addLast
    public void addLast(int item){
        var node = new Node();
    }

    //deleteFirst
    public void deleteFirst(){

    }

    //deleteLast
    public void deleteLast(){

    }

    //contains
    public void contains(){

    }

    //indexOf
    public voi

}