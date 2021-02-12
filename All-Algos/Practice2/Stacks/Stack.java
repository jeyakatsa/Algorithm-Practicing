public class Stack<V> {
    public static void main(String[] args) {
        Stack<Integer> stack = new Stack<Integer>(10);
        System.out.print("Elements pushed in the stack: ");
        for(int i = 0; i < 10; i++) {
            stack.push(i); //pushes 10 elements (0-10 inclusive) to the stack
            System.out.print(i + " ");
        }
        System.out.println("\nIs Stack full? \n" + stack.isFull());
        System.out.print("Elements popped from the Stack: ");
        for (int i = 0; i < 5; i++) {
            System.out.print(stack.pop() + " "); //pops all 10 elements from stack
        }

        System.out.println("\nIs Stack empty? \n" + stack.isEmpty());
    }


    private int maxSize;
    private int top;
    private V arr[];
    /*
    Java does not allow generic type arrays. So we have used an 
    array of Object type and type-casted it to the generic type V.
    This type-casting is unsafe and produces a warning.
    Comment out the line below and execute again to see the warning.
    */
    @SuppressWarnings("unchecked")
    public Stack(int max_size) {
        this.maxSize = max_size;
        this.top = -1; //initially when stack is empty
        arr = (V[]) new Object[max_size]; //type casting Object[] to V[]
    }
    public int getCapacity() {
        return maxSize;
    }

    //returns the meximum size capacity
    public int getMaxSize() {
        return maxSize;
    }

    //returns true if Stack is empty
    public boolean isEmpty() {
        return top == -1;
    }

    //returns true if Stack is full
    public boolean isFull() {
        return top == maxSize -1;
    }

    //returns the value at top of Stack
    public V top(){
        if(isEmpty())
            return null;
        return arr[top];    
    }

    //inserts a value to the top of Stack
    public void push(V value) {
        if(isFull()) {
            System.err.println("Stack is full");
            return;
        }
        arr[++top] = value; //increments the top and adds value to updated top
    }

    //removed a value from top of Stack and returns
    public V pop() {
        if(isEmpty())
            return null;
        return arr[top--]; //returns value and top and decrements top    
    }

}