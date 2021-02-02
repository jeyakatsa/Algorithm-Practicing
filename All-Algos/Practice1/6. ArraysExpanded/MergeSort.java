import java.util.Arrays;

public class MergeSort {
    public static void main(String[] args) {
        int[] numbers = { 8, 4, 3, 5, 3, 2, 1 };
        var sorter = new MergeSort();
        sorter.sort(numbers);
        System.out.println(Arrays.toString(numbers));
    }

    public void sort (int[] array) {
        //recursion base
        if(array.length < 2)
            return;


        // Divide this array into half
        var middle = array.length / 2;

        int[] left = new int[middle];
        for(var i = 0; i < middle; i++)
            left[i] = array[i];

        int[] right = new int[array.length - middle];
        for(var i = middle; i < array.length; i++)  
            right[i - middle] = array[i]; 

        //Sort each half (recursion)
        sort(left);
        sort(right);

        //Merge the result
        merge(left, right, array);
    }

    private void merge(int[] left, int[] right, int[] result) {
        int i = 0, j = 0, k = 0;

        while (i < left.length && j < right.length) {
            if(left[i] <= right[j])
                result[k++] = left[i++];
            else
                result[k++] = right[j++];    
        }

        while(i < left.length)
            result[k++] = left[i++];

        while(j < right.length)
            result[k++] = right[j++];
    }
}
