import java.util.*;

public class Main {
    public static void main (String[] args){
        var list = new LinkedList();
        list.addLast(10);
        list.addLast(20);
        list.addLast(30);
        list.reverse();
        var array = list.toArray();
        System.out.println(Arrays.toString(array));

    }
}