import java.util.HashMap;
import java.util.Map;

public class RepeatCharacter {
    public char findFirstNonRepeatingChar(String str) {
        Map<Character, Integer> map = new HashMap<>();
        // A Green Apple
        // Iterate through string
        var chars = str.toCharArray();
        for(var ch : str.toCharArray()){
            var count = map.containsKey(ch) ? map.get(ch) : 0;
            map.put(ch, count + 1);
        }
        // Example: if string[target string] == string[any other string]
        // return null, else if string[target string] !== string[any other string]
        // return string, break.    
        for (var ch : chars)
            if (map.get(ch) == 1)
                return ch;
        
        return Character.MIN_VALUE;
    }
}
