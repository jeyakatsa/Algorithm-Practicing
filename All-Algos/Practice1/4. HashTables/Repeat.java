import java.util.HashSet;
import java.util.Set;

public class Repeat {
    public char findFirstRepeatingChar(String str) {
        Set<Character> set = new HashSet<>();

        for (var ch : str.toCharArray()) {
            if (set.contains(ch))
                return ch;

            set.add(ch);    
        }

        return Character.MIN_VALUE;
    }

    public static void main(String[] args) {
        Repeat finder = new Repeat();
        var ch = finder.findFirstRepeatingChar("a green apple");
        System.out.println(ch);
        
    }
}