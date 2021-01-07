public class Main {

    public static void main (String[] args) {
        String str = "((<1] + <2>)) [a]";
        Expression express = new Expression();
        var result = express.isBalanced(str);

        System.out.println(result);
    }
}