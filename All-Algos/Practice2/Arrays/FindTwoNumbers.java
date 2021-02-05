public class FindTwoNumbers {

    // //^Intuitive but O(n)
    // public static int[] findSum(int[] arr, int n){
    //     int[] result = new int[2];
        // //Loop over array.
        // //if j element in array + i element in array = n, return n
        // //else return array
        // for (int i = 0; i < arr.length; i++) {
        //     for(int j = arr.length-1; j < arr.length; j--) {
        //         if (arr[i] + arr[j] == n){
        //             return n;
        //         }
        //         else {
        //             return arr;
        //         }
        //     }
        // }
    // }

    //O(nlogn) ... More Space, but less time...
    //Helper function to sort given Array (Quick Sort)
    public static int partition(int[] arr, int low, int high) {
        int pivot = arr[high];
        int i = (low - 1); // index of smaller element
        for (int j = low; j < high; j++) {
            //if current element is <= to pivot
            if (arr[j] <= pivot) {
                i++;

                //swap arr[i] and arr[j]
                int temp = arr[i];
                arr[i] = arr[j];
                arr[j] = temp;
            }
        }
    
        //swap arr[i+1] and arr[high] (or pivot)
        int temp = arr[i + 1];
        arr[i + 1] = arr[high];
        arr[high] = temp;

        return i + 1;
    }

    public static void sort(int[] arr, int low, int high) {
        if (low < high) {
            int pi = partition(arr, low, high);
            sort(arr, low, pi - 1);
            sort(arr, pi + 1, high);
        }
    }

    //return 2 elements of arr that sum to given value
    public static int[] findSum(int[] arr, int n) {
        //helper sort function that uses QuickSort Algo
        sort(arr, 0, arr.length - 1); //Sort the array in Ascending order

        int Pointer1 = 0; //Pinter 1 - > at start
        int Pointer2 = arr.length - 1; //Pointer 1 -> at end

        int[] result = new int[2];
        int sum = 0;

        while (Pointer1 != Pointer2) {

            sum = arr[Pointer1] + arr[Pointer2]; // Calculate Sum of Pointer 1 and 2

            if (sum < n)
                Pointer1++; //if Sum is less than given value => Move pointer 1 to Right
            else if (sum > n)
                Pointer2--;
            else {
                result[0] = arr[Pointer1];
                result[1] = arr[Pointer2];
                return result; // containing 2 numbers
            }    
        }
        return arr;
    }

    public static void main(String args[]) {

        int n = 14;
        int[] arr1 = {10,2,3,4,5};
        if(arr1.length > 0){
          int[] arr2 = findSum(arr1, n);
          int num1 = arr2[0];
          int num2 = arr2[1];
    
          if((num1 + num2) != n)
            System.out.println("Not Found");
          else {
            System.out.println("Number 1 = " + num1);
            System.out.println("Number 2 = " + num2);
            System.out.println("Sum = " +  (n) );
    
          }
        } else {
          System.out.println("Input Array is Empty!");
        }
    }
}
