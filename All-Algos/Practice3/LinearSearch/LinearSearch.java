public class LinearSearch {
    public static void main (String[] args) {
        int[] arr = {22,3,4,2,40,22,66};

        // System.out.println(linearS(arr, -15));
        // System.out.println(linearS(arr, -1));

        System.out.println(iterativeBinarySearch(arr, -15));
        System.out.println(iterativeBinarySearch(arr, 1));
        System.out.println(iterativeBinarySearch(arr, 2));
    }

    public static int linearS(int[] arr, int value) {
        for (int i = 0; i < arr.length; i++) {
            if (value == arr[i]) {
                return i;
            }
        }
        return -1;
    }

    public static int iterativeBinarySearch(int[] input, int value) {
        int start = 0;
        int end = input.length;

        while(start < end) {
            int midpoint = (start + end) / 2;
            System.out.println("midpoint = " + midpoint);
            if (input[midpoint] == value) {
                return midpoint;
            }
            else if (input[midpoint] < value) {
                start = midpoint + 1;
            }
            else {
                end = midpoint;
            }
        }

        return -1;
    }

    public static int recursiveBinarySearch(int[] input, int value) {
        return recursiveBinarySearch(input, 0, input.length, value);
    }

    public static int recursiveBinarySearch(int[] input, int start, int end, int value) {
        if (start >= end) {
            return -1;
        }

        int midpoint = (start + end) / 2;
        System.out.println("midpoint = " + midpoint);

        if (input[midpoint] == value) {
            return midpoint;
        }
        else if (input[midpoint] < value) {
            return recursiveBinarySearch(input, midpoint + 1, end, value);
        }
        else {
            return recursiveBinarySearch(input, start, midpoint, value);
        }
    }

}