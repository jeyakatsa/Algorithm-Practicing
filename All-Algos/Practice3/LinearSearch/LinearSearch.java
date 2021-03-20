public class LinearSearch {
    public static void main (String[] args) {
        int[] arr = {22,3,4,-1,40,22,66};

        System.out.println(linearS(arr, -15));
        System.out.println(linearS(arr, -1));
    }

    public static int linearS(int[] arr, int value) {
        for (int i = 0; i < arr.length; i++) {
            if (value == arr[i]) {
                return i;
            }
        }
        return -1;
    }
}