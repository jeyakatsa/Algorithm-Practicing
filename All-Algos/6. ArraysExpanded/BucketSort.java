import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class BucketSort {
    public static void main(String[] args){
        int[] numbers = {50,20,10,5,4,3,12,11};
        var sorter = new BucketSort();
        sorter.sort(numbers, 4);
        System.out.println(Arrays.toString(numbers));
    }

    public void sort(int[] array, int numberOfBuckets){
        var i = 0;    
        for (var bucket : createBuckets(array, numberOfBuckets)) {
            Collections.sort(bucket);
            for(var item : bucket)
                array[i++] = item;
        }    
    }

    private List<List<Integer>> createBuckets(int[] array, int numberOfBuckets) {
        List<List<Integer>> buckets = new ArrayList<>();
        for (var i = 0; i < numberOfBuckets; i++)
            buckets.add(new ArrayList<>());

        for(var item : array)
            buckets.get(item/ numberOfBuckets).add(item);

        return buckets;    
    }
}
