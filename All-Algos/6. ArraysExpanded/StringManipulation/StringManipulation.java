import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class StringManipulation {
    public static void main(String[] args) {
        int count = StringManipulation.vowels("Hello World");
        String reversed = StringManipulation.reverse("blue");
        String reversedWords = StringManipulation.reverseWords("I did it!");
        var rotations = StringManipulation.areRotations("ABCD", "ACDB");
        String duplicates = StringManipulation.removeDuplicates("Trees are nice");
        char repeatedChar = StringManipulation.getMaxOcurringChar("Trees are nice");
        String capitalize = StringManipulation.capitalize("This is a great day");
        var anagram = StringManipulation.anagram("ABCd", "ABCD");
        var anagram2 = StringManipulation.anagram2("ABCD", "DiBa");
        System.out.println(anagram2);
    }

    //Find number of Vowels in a String
    public static int vowels(String str) {
        if (str == null)
            return 0;

        int count = 0;
        String vowels = "aeiou";

        for(var ch : str.toLowerCase().toCharArray())
            if (vowels.indexOf(ch) != -1)
                count++;

        return count;        
    }

    //Reverse A String
    public static String reverse(String str) {
        StringBuilder reversed = new StringBuilder();
        if (str == null)
            return "";

        for (var i = str. length() - 1; i >= 0; i--) 
            reversed.append(str.charAt(i));

        return reversed.toString();
    }

    //Reverse Words
    public static String reverseWords(String sentence) {
        if (sentence == null)
            return "";

        String[] words = sentence.trim().split(" ");
        Collections.reverse(Arrays.asList(words));
        return String.join(" ", words);
    }

    //Check if Letters/Characters are rotations of each other.
    public static boolean areRotations(String str1, String str2) {
        if (str1 == null || str2 == null)
            return false;

        return(str1.length() == str2.length() &&
        (str1 + str1).contains(str2));
    }

    //Remove duplicate characters
    public static String removeDuplicates(String str) {
        StringBuilder output = new StringBuilder();
        Set<Character> seen = new HashSet<>();

        if (str == null)
            return "";

        for (var ch : str.toCharArray()) {
            if(!seen.contains(ch)) {
                seen.add(ch);
                output.append(ch);
            }
        }

        return output.toString();
    }

    //Most Repeated Character
    public static char getMaxOcurringChar(String str) {
        if (str == null || str.isEmpty())
            throw new IllegalArgumentException("No Character");

        final int ASCII_SIZE = 256;
        int[] frequencies = new int[ASCII_SIZE];
        for (var ch : str.toCharArray())
            frequencies[ch]++;

        int max = 0;    
        char result = ' ';
        for (var i = 0; i < frequencies.length; i++)
            if(frequencies[i] > max){
                max = frequencies[i];
                result = (char) i;
            }
        return result;    


        // Map<Character, Integer> frequencies = new HashMap<>();
        // for (var ch : str.toCharArray()){
        //     if (frequencies.containsKey(ch))
        //         frequencies.replace(ch, frequencies.get(ch) + 1);
        //     else
        //         frequencies.put(ch, 1);    
        // }
    }

    //Capitalize Beginning of Words
    public static String capitalize(String sentence) {
        if (sentence == null || sentence.trim().isEmpty())
            return "";

        String[] words = sentence.trim().replaceAll(" +", " ").split(" ");
        for (var i = 0; i < words.length; i++) {
            words[i] = words[i].substring(0, 1).toUpperCase()
             + words[i].substring(1).toLowerCase();            
        }

        return String.join(" ", words);
    }

    //Anagram = Words that have same letters
    public static boolean anagram(String first, String second) {
        if (first == null || second == null)
            return false;
       
        // 0(n)    
        var array1 = first.toLowerCase().toCharArray();
        // 0(n log n)
        Arrays.sort(array1);

        var array2 = second.toLowerCase().toCharArray();
        Arrays.sort(array2);

        return Arrays.equals(array1, array2);
    }

    //Anagram Historgramming...
    public static boolean anagram2(String first, String second) {
        if (first == null || second == null)
            return false;

        final int ENGLISH_ALPHABET = 26;
        int[] frequencies = new int[ENGLISH_ALPHABET];

        first = first.toLowerCase();
        //0()
        for (var i = 0; i < first.length(); i++)
            frequencies[first.charAt(i) - 'a']++;

        second = second.toLowerCase();
        for(var i = 0; i < second.length(); i++) {
            var index = second.charAt(i) - 'a';
            if(frequencies[index] == 0)
                return false; 
        }
        
        return true;

    }
}