public class MinValArray {
    public static int findMinimum(int[] arr){
        int smallest = Integer.MAX_VALUE;
        for (int i = 0; i < arr.length; i++){
            if (smallest > arr[i]) {
                smallest = arr[i];
            }  
             
        }
    }

    public static void main (String[] args) {
        int[] arr = {1,2,3,4,5,6,7};
        int result = findMinimum(arr);
        System.out.println(result);
    }
    
}
