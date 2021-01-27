import java.util.Arrays;

public class CountingSort {
    public static void main (String[] args) {
        int[] numbers = {3,10,20,23,13,5,10,4};
        var sorted = new CountingSort();
        sorted.sort(numbers, 23);
        System.out.println(Arrays.toString(numbers));
    }

    public void sort(int[] array, int max) {
        int[] counts = new int[max + 1];    
        for (var item : array)
            counts[item]++;    

        var k = 0;    
        for(var i = 0; i < counts.length; i++)
            for(var j = 0; j < counts[i]; j++)
                array[k++] = i;
    }
}
