public class FirstInteger {
    public static int findFirstUnique(int[] arr){
        int result = arr[0];
        //loop through array with i
        //make inner loop through array with j
        //if integer i != j, return and break
        //O(n^2), intuitive approach
        for(var i = 0; i < arr.length; i++){
            for(var j = 0; j < arr.length; j++) {
                if(arr[i] != arr[j]){
                    result = arr[i];
                }
            }
        }
        return result;
    }

    public static void main(String[] args) {
        int[] arr = {9,2,3,2,6,6};
        int result = findFirstUnique(arr);
        System.out.println(result);
    }
    
}
