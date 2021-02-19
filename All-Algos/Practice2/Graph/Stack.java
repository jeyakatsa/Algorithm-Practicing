public class Stack<V> {
    private int maxSize;
    private int top;
    private V[] array;
    private int currentSize;

    @SuppressWarnings("unchecked")
    public Stack(int maxSize) {
        this.maxSize = maxSize;
        this.top = -1; //initially when stack is empty
        array = (V[]) new Object[maxSize]; //type casting Object[] to V[]
        this.currentSize = 0;
    }
    
    public int getCurrentSize() {
        return currentSize;
    }

    //returns the meximum size capacity
    public int getMaxSize() {
        return currentSize;
    }

    //returns true if Stack is empty
    public boolean isEmpty() {
        return top == -1;
    }

    //returns true if Stack is full
    public boolean isFull() {
        return top == maxSize -1;
    }

    //returns value at top of Stack
    public V top() {
        if(isEmpty())
            return null;
        return array[top];    
    }

    //inserts a value to the top of Stack
    public void push(V value) {
        if(isFull()) {
            System.err.println("Stack is Full");
            return;
        }
        array[++top] = value; //increments the top and adds valye to the updated top
        currentSize++;
    }

    //removes a value from top of Stack and returns
    public V pop() {
        if(isEmpty())
            return null;
        currentSize--;
        return array[top--]; //returns value and top and decrements the top    
    }
}
