import java.util.NoSuchElementException;

public class LinkedList {
    private class Node {
        private int value;
        private Node next;
        private Node prev;

        public Node(int value){
            this.value = value;
        }
    }

    private Node first;
    private Node last;
    private int size;

    //addLast
    public void addLast(int item){
        var node = new Node(item);

        if (isEmpty())
            first = last = node;
        else {
            last.next = node;
            last = node;
        } 
        
        size++;

    }

    //addFirst
    public void addFirst(int item){
        var node = new Node(item);

        if (isEmpty())
            first = last = node;
        else {
            node.next = first;
            first = node;
        }
        size++;
    }   
    
    private boolean isEmpty() {
        return first == null;
    } 

    //deleteFirst
    public void removeFirst(){
        if (isEmpty())
            throw new NoSuchElementException();

        if(first == last){
            first = last = null;
            return;
        }
        var second = first.next;
        first.next = null;
        first = second;

        size--;

    }

    //deleteLast
    public void removeLast(){
        if (isEmpty())
            throw new NoSuchElementException();

        if (first == last) {
            first = last = null;
            size = 0;
            return;
        }    

        var previous = getPrevious(last);
        last = previous;
        last.next = null;

        size--;
    }

    private Node getPrevious(Node node){
        var current = first;
        while (current != null) {
            if (current.next == last) return current;
            current = current.next;
        }
        return null;
    }

    //contains
    public boolean contains(int item){
        return indexOf(item) != -1;
    }

    //indexOf
    public int indexOf(int item) {
        int index = 0;
        var current = first;
        while (current != null){
            if (current.value == item)
            return index;
            current = current.next;
            index++;
        }
        return -1;
    }

    public int size() {
        return size;
    }

    public int[] toArray(){
        int[] array = new int[size];
        var current = first;
        var index = 0;
        while (current != null) {
            array[index++] = current.value;
            current = current.next;
        }
        return array;
    }

    public int[] reverse() {
        int[] array = new int[size];
        var current = last;
        int index = 0;
        while (current != null) {
            array[index++] = current.value;
            current = current.prev;
        }
        return array;
    }

}