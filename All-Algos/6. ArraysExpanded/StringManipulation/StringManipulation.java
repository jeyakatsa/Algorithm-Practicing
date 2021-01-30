public class StringManipulation {
    public static void main(String[] args) {
        int count = StringManipulation.vowels("Hello World");
        System.out.println(count);
    }

    //1- Find the number of vowels in a string. Vowels in English are A, E, O, U and I. 
    //Input: “hello”
    //Output: 2 
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
}