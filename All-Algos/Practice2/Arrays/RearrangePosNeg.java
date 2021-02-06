public class RearrangePosNeg {

    public static void reArrange(int[] arr){
        //if i < 0, .push to newArr
        int[] newArr = {};
        for(var i = 0; i < arr.length; i++) {
            if(arr[i] > 0){
                newArr.push(arr[i]);
            }
        }
        //if i > 0 .push to newArr
        for(var i = 0; i < arr.length; i++){
            if(arr[i] < 0){
                newArr.push(arr[i]);
            }
        }
    }

    public static void main(String[] args){
        int[] arr = {-1,-2,-3,-4,5,7,8,9,0,-7};
        reArrange(arr);
        System.out.println("Array after Rearrangement: ");
        for (int i = 0; i < arr.length; i++){
            System.out.print(arr[i] + " ");
        }
    }
    
}
