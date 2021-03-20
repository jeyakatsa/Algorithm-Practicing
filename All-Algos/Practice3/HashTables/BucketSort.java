import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class BucketSort {
    public static void main (String[] args) {
        int[] arr = {54, 46, 93, 66, 95, 92, 43};

        bucketSort(arr);

        for(int i = 0; i < arr.length; i++) {
            System.out.println(arr[i]);
        }
    }

    public static void bucketSort(int[] input) {
        List<Integer>[] buckets = new List[10];

        for (int i = 0; i < buckets.length; i++) {
            buckets[i] = new ArrayList<Integer>();
        }

        for (int i = 0; i < input.length; i++) {
            buckets[hash(input[i])].add(input[i]);
        }

        for (List bucket: buckets) {
            Collections.sort(bucket);
        }

        int j = 0;
        for (int i = 0; i < buckets.length; i++) {
            for (int value: buckets[i]) {
                input[j++] = value;
            }
        }

    }

    private static int hash(int value) {
        return value / (int) 10;
    }
}
