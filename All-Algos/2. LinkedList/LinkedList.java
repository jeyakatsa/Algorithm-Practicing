import java.util.*;

public class LinkedList {

    private class Node {
        private int value;
        private Node next;

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

    public void reverse() {

        // [10 <--- 20 <---- 30]
        // prev    curr     next
        // n = c.next
        // c.next = p
        //below needs debugging...
        if (isEmpty()) return;
        var previous = first;
        var current = first.next;
        while (current != null) {
            var next = current.next;
            previous = current;
            current = next;
        }
        last = first;
        last.next = null;
        first = previous;
    }

    public int getKthFromTheEnd(int k) {
        // getKthFromTheEnd(k)
        if (isEmpty())
            throw new IllegalStateException();
        var a = first;
        var b = first;
        //Find the Kth node from the end
        for (int i = 0; i < k-1; i++)
            b = b.next;
            if (b == null)
                throw new IllegalArgumentException();
        while (b != last) {
            a = a.next;
            b = b.next;
        }
        return a.value; 
    }

}