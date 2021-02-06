import java.util.Arrays;

public class FindSecondMaximum {

    //O(nlogn) solution
    public static int findSecondMaximum(int[] arr) {
        //sort array from smallest integer to largest integer
        //return second to last integer.
        int n = arr.length;

        //Test Case
        if(n < 2) {
            System.out.println("Second largest element does not exist");
        }

        Arrays.sort(arr);
        int secondLargest = arr[n-2];

        return secondLargest;
    }

    public static void main(String[] args){
        int[] arr = {-1,2,10,45,22,0,1};
        int result = findSecondMaximum(arr);
        System.out.println(result);
    }
    
}
