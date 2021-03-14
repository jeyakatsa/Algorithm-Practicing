import java.util.NoSuchElementException;

public class ArrayQueue {

    public static void main (String[] args) {

        Employee janeJones = new Employee("Jane", "Jones", 5540);
        Employee samSmith = new Employee("Sam", "Smith", 4470);
        Employee beeDee = new Employee("Bee", "Dee", 1498);

        ArrayQueue queue = new ArrayQueue(10);
        queue.add(janeJones);
        queue.add(samSmith);
        queue.add(beeDee);

        queue.printQueue();

        queue.remove();

        System.out.println(queue.peek());
        queue.printQueue();

    }

    private Employee[] queue;
    private int front;
    private int back;

    public ArrayQueue(int capacity) {
        queue = new Employee[capacity];
    }

    public void add(Employee employee) {
        if(back == queue.length) {
            Employee[] newArray = new Employee[2 * queue.length];
            System.arraycopy(queue, 0, newArray, 0, queue.length);
            queue = newArray;
        }

        queue[back] = employee;
        back++;
    }

    public Employee remove() {
        if (size() == 0) {
            throw new NoSuchElementException();
        }

        Employee employee = queue[front];
        queue[front] = null;
        front++;
        if(size() == 0) {
            front = 0;
            back = 0;
        }

        return employee;
    }

    public Employee peek() {
        if(size() == 0) {
            throw new NoSuchElementException();
        }

        return queue[front];
    }

    public int size() {
        return back - front;
    }

    public void printQueue() {
        for (int i = front; i < back; i++) {
            System.out.println(queue[i]);
        }
    }



}