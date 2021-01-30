import java.util.Arrays;

public class InsertionSort {
    public static void main(String[] args) {
        int[] numbers = {5,4,3,2,1};
        var sorter = new InsertionSort();
        sorter.sort(numbers);
        System.out.println(Arrays.toString(numbers));
    }

    public void sort(int[]array) {
        for(var i = 0; i < array.length; i++) {
            var current = array[i];
            var j = i - 1;
            while (j >= 0 && array[j] > current) {
                array[j + 1] = array[j];
                j--;
            }
            array[j + 1] = current;
        }
    }
}
