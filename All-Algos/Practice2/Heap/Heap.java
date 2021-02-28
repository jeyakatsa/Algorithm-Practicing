import java.util.Arrays;

public class Heap {
    public static void main (String[] args){
        Heap heap = new Heap();
        int[] heapArray = {1, 4, 7, 12, 15, 14, 9, 2, 3, 16 };

        // new Heap().buildMaxHeap(heapArray, heapArray.length);
        // System.out.println("Max Heap: " + Arrays.toString(heapArray));
        // // new Heap().buildMinHeap(heapArray, heapArray.length);
        // heap.convertMax(heapArray);
        // System.out.println("Min Heap: " + Arrays.toString(heapArray));

        int[] output = findKSmallest(heapArray, 2);
        for(int i = 0; i < output.length; i++)
            System.out.println(output[i]);

    }

    public static void maxHeap(int[] heapArray, int index, int heapSize) {
        int largest = index;
        while(largest < heapSize / 2) { // check parent nodes onlie
            int left = (2 * index) + 1; //left child
            int right = (2 * index) + 2; //right child

            if(left< heapSize && heapArray[left] > heapArray[index]) {
                largest = left;
            }
            if(right < heapSize && heapArray[right] > heapArray[largest]) {
                largest = right;
            }
            if(largest != index) { // swap parent with largest child
                int temp = heapArray[index];
                heapArray[index] = heapArray[largest];
                heapArray[largest] = temp;
                index = largest;
            }
            else
                break; //if heap property is satisfied
        }

    }
    public static void buildMaxHeap(int[] heapArray, int heapSize) {
        //swap largest child to parent node
        for(int i = (heapSize -1)/ 2; i >= 0; i--) {
            maxHeap(heapArray, i, heapSize);
        }
    }

    private static void minHeapify(int[] heapArray, int index, int heapSize) {
        int smallest = index;

        while(smallest < heapSize / 2) { //check parent nodes only
            int left = (2 * index) + 1; //left child
            int right = (2 * index) + 2; //right child
            if (left < heapSize && heapArray[left] < heapArray[index]) {
                smallest = left;
            }

            if (right < heapSize && heapArray[right] < heapArray[smallest]) {
                smallest = right;
            }

            if (smallest != index) { // swap parent with smallest child
                int temp = heapArray[index];
                heapArray[index] = heapArray[smallest];
                heapArray[smallest] = temp;
                index = smallest;
            } else {
                break;
            }
        }
    }

    public static void buildMinHeap(int[] heapArray, int heapSize) {
        //Swap smallest child to parent node
        for(int i = (heapSize - 1)/ 2; i >= 0; i--) {
            minHeapify(heapArray, i, heapSize);
        }
    }

    public static void convertMax(int[] maxHeap) {
        //Consider maxHeap just an ordinary unsorted array 
		//Build minHeap of the given array. (We already covered that in previous lesson)
		//Return converted array in String format
        buildMinHeap(maxHeap, maxHeap.length);

    }

    public static int[] findKSmallest(int[] arr, int k) {

        int arraySize = arr.length;
        if(k <= arraySize) {
            int[] result = new int[k];
            for (int i = 0; i < k; i++) {
                result[i] = removeMin(arr, arraySize);
                --arraySize;
            }
            return result;
        }
        System.out.println("Value of k = " + k + "out of bounds!");
        return arr;
    }

    public static int removeMin(int[] heapArray, int heapSize) {
        buildMinHeap(heapArray, heapSize);
        int min = heapArray[0];
        heapArray[0] = heapArray[heapSize-2];
        return min;
    }


}