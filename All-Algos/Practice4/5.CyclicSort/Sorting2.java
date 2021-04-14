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



    public static void main (String[] args) {
        int[] arr = findCorruptPair(new int[] {2,3,4,1,1,5});
        System.out.println(arr[0] + ", " + arr[1]);
    }
}
