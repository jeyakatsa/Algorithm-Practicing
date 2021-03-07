public class Arrays {
    public static void main (String[] args) {
        int[] arr = new int[7];

        arr[0] = 7;
        arr[1] = 6;
        arr[2] = 5;
        arr[3] = 4;
        arr[4] = 3;
        arr[5] = 2;
        arr[6] = 1;

        // // Run array
        // for(int i = 0; i <arr.length; i++) {
        //     System.out.println(arr[i]);
        // }

        // //Print Index
        // int index = -1;
        // for (int i = 0; i < arr.length; i++) {
        //     if (arr[i] == 7) {
        //         index = i;
        //         break;
        //     }
        // }

        // System.out.println("index: " + index);

        // //Bubble Sort O(n^2)
        // for (int lastUnsortedIndex = arr.length - 1; lastUnsortedIndex > 0; 
        //     lastUnsortedIndex--) {
        //     for(int i = 0; i < lastUnsortedIndex; i++) {
        //         if(arr[i] > arr[i + 1]) {
        //             swap(arr, i, i + 1);
        //         }
        //     }
        // }
        // for (int i = 0; i < arr.length; i++){
        //     System.out.println(arr[i]);
        // }

        // //Selection Sort O(n^2)
        // for (int lastUnsortedIndex = arr.length - 1; lastUnsortedIndex > 0; 
        //     lastUnsortedIndex--) {
        //     int largest = 0;
        //     for (int i = 0; i <= lastUnsortedIndex; i++) {
        //         if(arr[i] > arr[largest]) {
        //             largest = i;
        //         }
        //     }
        //     swap(arr, largest, lastUnsortedIndex);
        // }
        // for (int i = 0; i < arr.length; i++){
        //     System.out.println(arr[i]);
        // }

        // //Insertion Sort O(n^2)
        // for (int firstUnsortedIndex = 1; firstUnsortedIndex < arr.length;
        //     firstUnsortedIndex++) {
        //     int newElement = arr[firstUnsortedIndex];

        //     int i;

        //     for(i = firstUnsortedIndex; i > 0 && arr[i-1] > newElement; i--) {
        //         arr[i] = arr[i -1];
        //     }

        //     arr[i] = newElement;
        // }
        // for (int i = 0; i < arr.length; i++) {
        //     System.out.println(arr[i]);
        // }

        // //Shell Sort O(n^2), worst case, but can be better...
        // for (int gap = arr.length / 2; gap > 0; gap /=2) {
        //     for(int i = gap; i < arr.length; i++) {
        //         int newElement = arr[i];

        //         int j = i;

        //         while(j >= gap && arr[j - gap] > newElement) {
        //             arr[j] = arr[j - gap];
        //             j -= gap;
        //         }
        //         arr[j] = newElement;
        //     }
        // }

        mergeSort(arr, 0, arr.length);

        for (int i = 0; i < arr.length; i++) {
            System.out.println(arr[i]);
        }



    }

    //swap method
    public static void swap(int[] arr, int i, int j) {
        if (i == j) {
            return;
        }

        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    //Recursive Factorial
    public static int recursiveFactorial (int num) {
        if (num == 0) {
            return 1;
        }

        return num * recursiveFactorial(num - 1);
    }

    //Iterative Factorial
    public static int iterativeFactorial(int num) {
        if(num == 0){
            return 1;
        }

        int factorial = 1;
        for (int i = 1; i <= num; i++){
            factorial *= i;
        }

        return factorial;
    }

    //Merge Sort O(nlogn)
    public static void mergeSort(int[] input, int start, int end) {
        if(end - start < 2) {
            return;
        }

        int mid = (start + end) / 2;
        mergeSort(input, start, mid);
        mergeSort(input, mid, end);
        merge(input, start, mid, end);
    }
    public static void merge(int[] input, int start, int mid, int end) {
        if(input[mid-1] <= input[mid]) {
            return;
        }

        int i = start;
        int j = mid;
        int tempIndex = 0;

        int[] temp = new int[end - start];
        while(i < mid && j < end) {
            temp[tempIndex++] = input[i] <= input[j] ? input[i++] : input[j++];
        }
        System.arraycopy(input, i, input, start + tempIndex, mid - i);
        System.arraycopy(temp, 0, input, start, tempIndex);
    }
}