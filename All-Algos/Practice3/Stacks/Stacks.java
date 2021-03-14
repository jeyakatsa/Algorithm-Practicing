import java.util.EmptyStackException;
import java.util.LinkedList;
import java.util.ListIterator;

public class Stacks {

    public static void main (String[] args){

        LinkedStack stack = new LinkedStack();

        stack.push (new Employee("Jane", "Jones", 123));
        stack.push(new Employee("John", "Doe", 546));
        stack.push(new Employee("Mary", "Smith", 22));

        stack.printStack();

        
    }

    public static class ArrayStack {
        private Employee[] stack;
        private int top;

        public ArrayStack(int capacity) {
            stack = new Employee[capacity];
        }

        public void push(Employee employee) {
            if (top == stack.length) {
                //need to resize backing array
                Employee[] newArray = new Employee[2 * stack.length];
                System.arraycopy(stack, 0, newArray, 0, stack.length);
                stack = newArray;
            }

            stack[top++] = employee;
        }

        public Employee pop() {
            if (isEmpty()) {
                throw new EmptyStackException();
            }

            Employee employee = stack[--top];
            stack[top] = null;
            return employee;

        }

        public Employee peek() {
            if (isEmpty()) {
                throw new EmptyStackException();
            }

            return stack[top - 1];
        }

        public int size() {
            return top;
        }

        public boolean isEmpty() {
            return top == 0;
        }

        public void printStack() {
            for(int i = top - 1; i >= 0; i--) {
                System.out.println(stack[i]);
            }
        }
    }

    public static class LinkedStack {
        private LinkedList<Employee> stack;

        public LinkedStack() {
            stack = new LinkedList<Employee>();
        }

        public void push (Employee employee) {
            stack.push(employee);
        }

        public Employee pop() {
            return stack.pop();
        }

        public Employee peek() {
            return stack.peek();
        }

        public boolean isEmpty() {
            return stack.isEmpty();
        }

        public void printStack() {
            ListIterator<Employee> iterator = stack.listIterator();
            while (iterator.hasNext()) {
                System.out.println(iterator.next());
            }
        }
    }

}