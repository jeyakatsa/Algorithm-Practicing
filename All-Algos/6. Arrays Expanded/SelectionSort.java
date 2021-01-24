import java.util.Arrays;

public class SelectionSort {
    public static void main(String[] args){
        int[] numbers = {2, 4, 0, 10, 20, 30, 6, 3, 1};
        var sorter = new SelectionSort();
        sorter.sort(numbers);
        System.out.println(Arrays.toString(numbers));
    }

    public void sort(int[]array) {
        for(var i = 0; i < array.length; i++) {
            var minIndex = i;
            for(var j = i; j < array.length; j++)
                if (array[j] < array[minIndex])
                    minIndex = j;
            swap(array, minIndex, i);
        }
    }

    private void swap(int[] array, int index1, int index2){
        var temp = array[index1];
        array[index1] = array[index2];
        array[index2] = temp;
    }


}
