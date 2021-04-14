public class Sorting2 {

    //Find the Corrupt Pair
    //base argument
    //if a number is duplicated or if a number is missing,
    //return duplicated number + missing number.
    public static int[] findCorruptPair(int[] arr) {
        if (arr == null || arr.length == 0) {
            throw new IllegalArgumentException();
        }
        int i = 0;
        while (i < arr.length){
            if (arr[i] != arr[arr[i] - 1]) {
                swap(arr, i, arr[i] - 1);
            }
            else {
                i++;
            }
        }
        for (i = 0; i < arr.length; i++)
            if (arr[i] != i + 1)
            return new int[] { arr[i], i + 1 };

        return new int[] { -1, -1};
    }
    public static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    //Find the Smallest Missing Positive Number

    //Base Case
    //Array Sorting.
    public static int smallestMissingPositiveNumber(int[] arr){
        if (arr == null || arr.length == 0) {
            throw new IllegalArgumentException("No Array Detected!");
        }

        int i = 0;
        while(i < arr.length) {
            if(arr[i] > 0 && arr[i] <= arr.length && arr[i] != arr[arr[i] - 1]) {
                swap(arr, i, arr[i] - 1);
            }
            else {
                i++;
            }
        }
        for (i = 0; i < arr.length; i++) {
            if ((arr[i] != i + 1 )){
                return i + 1;
            }
        }
        return arr.length + 1;

    }



    public static void main (String[] args) {
        // int[] arr = findCorruptPair(new int[] {-1,-2,0,2,3,4,5});
        // System.out.println(arr[0] + ", " + arr[1]);

        int[] arr = {-1,-2,0,2,3,4,5};
        System.out.println(smallestMissingPositiveNumber(arr));
    }
}
