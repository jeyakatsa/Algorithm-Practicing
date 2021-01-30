public class StringManipulation {
    public static void main(String[] args) {
        int count = StringManipulation.vowels("Hello World");
        String reversed = StringManipulation.reverse("blue");
        System.out.println(reversed);
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
}