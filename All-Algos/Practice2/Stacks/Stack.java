
public class Stack<V> {
    public static void main(String[] args) {
        Stack<Integer> stack = new Stack<Integer>(10);
        // System.out.print("Elements pushed in the stack: ");
        // for(int i = 0; i < 10; i++) {
        //     stack.push(i); //pushes 10 elements (0-10 inclusive) to the stack
        //     System.out.print(i + " ");
        // }
        // System.out.println("\nIs Stack full? \n" + stack.isFull());
        // System.out.print("Elements popped from the Stack: ");
        // for (int i = 0; i < 5; i++) {
        //     System.out.print(stack.pop() + " "); //pops all 10 elements from stack
        // }

        // System.out.println("\nIs Stack empty? \n" + stack.isEmpty());


        // stack.push(2);
        // stack.push(97);
        // stack.push(4);
        // stack.push(42);
        // stack.push(12);
        // stack.push(60);
        // stack.push(23);
        // sortStack(stack);
        // while(!stack.isEmpty()){
        //     System.out.println(stack.pop());
        // }

        System.out.println(evaluatePostFix("921*-8-4+"));
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

    public static void sortStack(Stack<Integer> stack) {
        //1. use a second tempStack.
        //2. pop value from mainStack.
        //3. If the value is greater or equal to the top of tempStack, then push value in tempStack
        //else pop all values from tempStack and push them in mainStack and in the end push value in tempStack and repeat from step 2.
        //till mainStack is not empty.
        //4. When mainStack will be empty, tempStack will have sorted values in descending order.
        //5. Now transfer values from tempStack to mainStack to make values sorted in ascending order.
        Stack<Integer> newStack = new Stack<>(stack.getMaxSize());
        while (!stack.isEmpty()) {
            Integer value = stack.pop();
            if(!newStack.isEmpty() && value >= newStack.top()) {
                newStack.push(value);
            } else {
                while (!newStack.isEmpty() && newStack.top() > value)
                    stack.push(newStack.pop());
                newStack.push(value);    
            }
        }
        while (!newStack.isEmpty())
            stack.push(newStack.pop());
    }

    public static int evaluatePostFix(String expression) {
        Stack<Integer> stack = new Stack<>(expression.length());
        //1.Scan expression character by character,
        //2.If character is a number push it in stack
        //3.If character is operator then pop two elements from stack
        //perform the operation and put the result back in stack
        //At the end, stack will contain result of whole expression.
        for(int i = 0; i < expression.length(); i++) {
            char character = expression.charAt(i);

            if(!Character.isDigit(character)) {
                Integer x = stack.pop();
                Integer y = stack.pop();

                switch (character) {
                    case '+':
                        stack.push(y + x);
                        break;
                    case '-':
                        stack.push(y - x);
                        break;
                    case  '*':
                        stack.push(y * x);
                        break;
                    case '/':
                        stack.push(y / x);
                        break;               
                }
            }else
                stack.push(Character.getNumericValue(character));
        }
        return stack.pop();
    }

}