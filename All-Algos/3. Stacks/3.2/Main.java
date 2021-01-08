public class Main {

    public static void main(String[] args) {
        ArrayStack stack = new ArrayStack();
        stack.push(10);
        stack.push(20);
        stack.push(30);
        System.out.println(stack);

        // [10,20,0,0,0]
        // count = 1
        // push(20)
        //array[count] = 20
    }
}