import java.util.Arrays;

public class BubbleSort {
    public static void main(String[] args) {
        var sorter = new BubbleSort();
        int[] numbers = {4, 5, 6, 7, 1, 0};
        sorter.sort(numbers);
        System.out.println(Arrays.toString(numbers));
    }

    public void sort(int[] array) {
        boolean isSorted;
        for (var i = 0; i < array.length; i++) {
            isSorted = true;
            for (var j = 1; j < array.length - i; j++){
                if (array[j] < array[j - 1]) {
                    swap(array, j, j - 1);
                    isSorted = false;
                }
            if (isSorted)
                return;    
            }
        }
    }
    private void swap(int[] array, int index1, int index2){
        var temp = array[index1];
        array[index1] = array[index2];
        array[index2] = temp;
    }
}