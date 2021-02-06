

public class FindSecondMaximum {

    // //O(nlogn) solution (Smaller Space, Longer Time)
    // public static int findSecondMaximum(int[] arr) {
    //     //sort array from smallest integer to largest integer
    //     //return second to last integer.
    //     int n = arr.length;

    //     //Test Case
    //     if(n < 2) {
    //         System.out.println("Second largest element does not exist");
    //     }

    //     Arrays.sort(arr);
    //     int secondLargest = arr[n-2];

    //     return secondLargest;
    // }
    
    //O(n) solution, Larger space, shorter time.
    public static int findSecondMaximum(int[] arr) {

        int max = Integer.MIN_VALUE;;
        int secondmax = Integer.MIN_VALUE;
    
        // Find the maximum value in the array by comparing each index with max
        // If an element is greater than max then update max to be equal to it
        for (int i = 0; i < arr.length; i++) {
          if (arr[i] > max) 
            max = arr[i];
    
        }//end of for-loop
    
        // Find the second maximum value by comparing each index with secondmax
        // If an element is greater than secondmax and not equal to previously 
        // calculated max, then update secondmax to be equal to that element.
        for (int i = 0; i < arr.length; i++) {
          if (arr[i] > secondmax && arr[i] < max) 
            secondmax = arr[i];
    
        }//end of for-loop
    
        return secondmax;
      } 

    public static void main(String[] args){
        int[] arr = {-1,2,10,45,22,0,1};
        int result = findSecondMaximum(arr);
        System.out.println(result);
    }
    
}
