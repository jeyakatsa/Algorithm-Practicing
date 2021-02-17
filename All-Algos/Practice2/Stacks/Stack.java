import java.util.Arrays;

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

        // System.out.println(evaluatePostFix("921*-8-4+"));

        // int arr[] = {4,6,3,2,8,1,11};
        // System.out.println(Arrays.toString(arr));
        // int result[] = nextGreaterElement(arr);
        // System.out.println(Arrays.toString(result));

        // int [][] party1 = {
        //     {0,1,1,0},
        //     {1,0,1,1},
        //     {0,0,0,0},
        //     {0,1,1,0},   
        //   };
  
        //   int [][] party2 = {
        //     {0,1,1,0},
        //     {1,0,1,1},
        //     {0,0,0,1},
        //     {0,1,1,0},   
        //   };
  
        //   int [][] party3 = {
        //     {0,0,0,0},
        //     {1,0,0,1},
        //     {1,0,0,1},
        //     {1,1,1,0},   
        //   };
          
        //   System.out.println(findCelebrity(party1,4));
        //   System.out.println(findCelebrity(party2,4));
        //   System.out.println(findCelebrity(party3,4));

        System.out.println(isBalanced("{[()]}"));
        System.out.println(isBalanced("}["));
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

    //iterate through stack.
    //create new array
    //create two empty integers, x and y+1
    //in old array,
    // if x is < y+1, push y+1 into new array then increment x
    //else if x is > y+1 increment y
    //else push -1 into new array
    //^my solution for below...    
    public static int[] nextGreaterElement(int[] arr) {
        int[] result = new int[arr.length];
        int resultIndex = 0;
        Stack<Integer> stack = new Stack<>(arr.length);
        for(int i = arr.length - 1; i >= 0; i--) {
            if(!stack.isEmpty()) {
                while (!stack.isEmpty() && stack.top() <= arr[i]) {
                    stack.pop();
                }
            }
            if(stack.isEmpty()){
                result[i] = -1;
            }
            else
                result[i] = stack.top();
            stack.push(arr[i]);    
        }
        return result;
    }

    //returns true if x know y else returns false
    private static boolean aqStatus(int[][] party, int x, int y) {
        if(party[x][y] == 1) return true;
        return false;
    }

    public static int findCelebrity(int[][] party, int numPeople) {
        Stack<Integer> stack = new Stack<>(numPeople);
        int celebrity = -1;

        //Push all people in stack
        for(int i = 0; i < numPeople; i++) {
            stack.push(i);
        }

        while(!stack.isEmpty()) {

            //Take two people out of stack and check if they know each other
            //One who doesn't know the other, push it back in stack.
            int x = stack.pop();

            if(stack.isEmpty()) {
                celebrity = x;
                break;
            }

            int y = stack.pop();

            if(aqStatus(party, x, y)) {
                //x knows y, discard and push y in stack
                stack.push(y);
            } else stack.push(x);
        }

        //At this point, we will have last element of Stack as celebrity
        //Check it to make sure it's the right celebrity
        for(int j = 0; j < numPeople; j++) {

            //Celebrity knows no one while everyone knows celebrity
            if(celebrity != j && (aqStatus(party, celebrity, j) || !(aqStatus(party, j, celebrity))))
            return -1;
        }
        return celebrity;
    }

    public static boolean isBalanced(String exp) {
        //iterate through string"array"
        //if string[i]"opposite" is == to string[string.length]]
        //increment i and decrement string.length
        //continue irerating then once reached end of string, return true
        //else return false
        //^My solution

        //Their solution...v
        //Iterate through the string exp.
        //For each opening parentheses, push it into stack.
        //For every clothing patanthesis, check for its opening parentheses in stack
        //If you can't find the opening parentheses for any closing one then returns false.
        //and after complete traversal of string exp, if there's any opening parentheses left
        //in stack then also return false.
        //At the end return true if you haven't encountered any of the above false conditions.
        Stack<Character> stack = new Stack<>(exp.length());

        for(int i = 0; i < exp.length(); i++) {
            char character = exp.charAt(i);
            if(character == '}' || character == ')' || character == ']') {
                if(stack.isEmpty())
                    return false;
                if((character == '}' && stack.pop() != '{') || (character == ')' && stack.pop() != '(')
                || (character == ']' && stack.pop() != '['))
                    return false;    
            }
            else stack.push(character);
        }
        if(!stack.isEmpty()) 
            return false;

        return true;
    }


}