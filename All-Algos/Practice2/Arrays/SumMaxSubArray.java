public class SumMaxSubArray {
    public static int findMaxSumSubArray(int[] arr) {
        if (arr.length < 1) {
            return 0;
        }

        int currMax = arr[0];
        int globalMax = arr[0];
        int lengtharray = arr.length;
        for (int i = 1; i < lengtharray; i++) {
            if (currMax < 0) {
                currMax = arr[i];
            } else {
                currMax += arr[i];
            }

            if(globalMax < currMax) {
                globalMax = currMax;
            }
        }
        return globalMax;
    }

    public static void main (String[] args) {
        int[] arr = {1};
        int result = findMaxSumSubArray(arr);
        System.out.println(result);
    }
}
