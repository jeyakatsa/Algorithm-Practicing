import java.util.*;

public class One {

    public static void main(String[] args) {
        Array numbers = new Array(3);
        numbers.insert(10);
        numbers.insert(20);
        numbers.insert(30);
        numbers.insert(40);
        numbers.removeAt(0);
        System.out.println(numbers);
        numbers.print();        
    }
    
}
