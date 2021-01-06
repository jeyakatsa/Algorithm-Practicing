import java.util.*;

public class Main {

    public static void main (String[] args) {
        String str = "abcd";

        Stacks reverser = new Stacks();
        var result = reverser.reverse(str);

        System.out.println(result);
    }
}