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


        return new int[] { -1, -1};
    }

    public static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;

    }



    public static void main (String[] args) {
        int[] arr = {2, 2, 4, 5};
    }
}
