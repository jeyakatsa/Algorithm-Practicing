public class Queue<V> {
    public static void main (String[] args) {
        Queue<Integer> queue = new Queue<Integer>(5);
        //enqueue numbers ascending at end
        queue.enqueue(2);
        queue.enqueue(4);
        queue.enqueue(6);
        queue.enqueue(8);
        queue.enqueue(10);

        reverseK(queue, 3);

        System.out.println("Queue:");
        while(!queue.isEmpty()) {
            System.out.print(queue.dequeue() + " ");
        }

        // String[] output = findBin(8);
        // for(int i = 0; i < 8; i++)
        //     System.out.print(output[i] + " ");

    }

    private int maxSize;
    private V[] array;
    private int front;
    private int back;
    private int currentSize;

    /*
    Java does not allow generic type arrays. So we have used an
    array of Object type and type-casted it to the generic type V.
    This type-casting is unsafe and produces a warning.
    Comment out the line below and execute again to see the warning.
    */
    @SuppressWarnings("unchecked")
    public Queue(int maxSize) {
        this.maxSize = maxSize;
        array = (V[]) new Object[maxSize];
        front = 0;
        back = -1;
        currentSize = 0;
    }

    public int getMaxSize() {
        return maxSize;
    }

    public int getCurrentSize() {
        return currentSize;
    }

    public boolean isEmpty() {
        return currentSize == 0;
    }

    public boolean isFull() {
        return currentSize == maxSize;
    }

    public V top() {
        return array[front];
    }

    public void enqueue(V value) {
        if(isFull())
            return;
            back = (back + 1) % maxSize; // to keep index in range
            array[back] = value;
            currentSize++;
    }

    public V dequeue() {
        if(isEmpty())
            return null;

        V temp = array[front];
        front = (front + 1) % maxSize; //to keep index in range
        currentSize--;

        return temp;
    }

    public static String[] findBin(int number) {
        String[] result = new String[number];
        Queue<String> queue = new Queue<String>(number + 1);

        queue.enqueue("1");

        for (int i = 0; i < number; i++) {
            result[i] = queue.dequeue();
            String s1 = result[i] + "0";
            String s2 = result[i] + "1";
            queue.enqueue(s1);
            queue.enqueue(s2);
        }
        return result;
    }

    public static <V> void reverseK(Queue<V> queue, int k) {
        //1. push first k elements in queue in a stack.
        //2. pop stack elements and enqueue to end of queue
        //3. Dequeue queue elements till "k" and append at end
        //4. Dequeue remaining elements and enqueue them again to append to end of queue
        if (queue.isEmpty() || k <= 0)
            return;
        Stack<V> stack = new Stack<>(k);  
        
        while(!stack.isFull())
            stack.push(queue.dequeue());

        while(!stack.isEmpty())
            queue.enqueue(stack.pop()); 
            
        int size = queue.getCurrentSize();
        for(int i = 0; i < size - k; i++)
            queue.enqueue(queue.dequeue());    
    }

}
