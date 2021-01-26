import java.util.Arrays;

public class QuickSort {
    public static void main(String[] args) {
        int[] numbers = {10,20,50,14,2,4,7,2,3,4,5};
        var sorted = new QuickSort();
        sorted.sort(numbers);
        System.out.println(Arrays.toString(numbers));
    }

    public void sort(int[] array){
        //recursive base
        if(array.length < 2)
            return;

    }
}