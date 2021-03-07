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

        //Insertion Sort O(n^2)
        for (int firstUnsortedIndex = 1; firstUnsortedIndex < arr.length;
            firstUnsortedIndex++) {
            int newElement = arr[firstUnsortedIndex];

            int i;

            for(i = firstUnsortedIndex; i > 0 && arr[i-1] > newElement; i--) {
                arr[i] = arr[i -1];
            }

            arr[i] = newElement;
        }
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
}