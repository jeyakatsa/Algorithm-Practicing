public class MergeSorted {
    public static int[] mergeArrays(int[] arr1, int[] arr2){
        //create array3
        //loop through array1, push numbers into array3
        //loop through array2, push numbers into array3
        int[] arr3 = {};

        for (int i = 0; i < arr1.length; i++) {
            arr3.push(arr1[i]);
        }

        for (int i = 0; i < arr2.length; i++) {
            arr3.push(arr2[i]);
        }


        return arr3;
    }


}
