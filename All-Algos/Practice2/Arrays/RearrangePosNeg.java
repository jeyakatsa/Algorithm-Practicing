public class RearrangePosNeg {

    public static void reArrange(int[] arr){

        int[] newArr = new int[arr.length];
        int newArray_index = 0;

        //if i < 0, .push to newArr
        for(var i = 0; i < arr.length; i++) {
            if(arr[i] > 0){
                newArr[newArray_index++] = arr[i];
            }
        }
        //if i > 0 .push to newArr
        for(var i = 0; i < arr.length; i++){
            if(arr[i] < 0){
                newArr[newArray_index++] = arr[i];
            }
        }

        for(int j = 0; j < newArr.length; j++){
            arr[j] = newArr[j];
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
