public class RightRotate {
    public static void rotateArray(int[] arr) {
        //Store last element of Array.
        //Start from second last and right shift array by one
        //Store last element saved on the first index of the array.
        int lastElement = arr[arr.length - 1];

        for (int i = arr.length - 1; i > 0; i--){
            arr[i] = arr[i - 1];
        }

        arr[0] = lastElement;
    }

    public static void main(String[] args) {
        int[] arr = {1,2,3,4,5,6,7};
        rotateArray(arr);
        System.out.println("Array after rotation: ");
        for(int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + " ");
        }

    }
}
