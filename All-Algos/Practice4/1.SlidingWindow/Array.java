import java.util.Arrays;

public class Array {
    public static void main (String[] args){
        int[] arr = {1,3,2,6,-1,4,1,8,2};
        int k = 5;
        double[] result = Array.findAverages(k, arr);
        System.out.print("Averages of subarrays of size K: " + Arrays.toString(result));    }

    //find contiguous subarrays of size '5'
    public static double[] findAverages(int k, int[] arr) {
        double[] result = new double[arr.length - k + 1];
        for(int i = 0; i <= arr.length - k; i++) {
            //find sum of next 'K' elements
            double sum = 0;
            for (int j = i; j < i + k; j++)
                sum += arr[j];
            result[i] = sum / k; // calculate average    
        }
        return result;
    }

}