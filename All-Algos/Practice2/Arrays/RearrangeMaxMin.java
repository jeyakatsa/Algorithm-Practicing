public class RearrangeMaxMin {

    //A bit Slow... O(n)?
    public static void maxMin(int[] arr) {
        //create newArr
        //take last integer from last array, push it into new Arr
        //take first integer and push to new Arr...
        //O(n^2) intuitive solution (maybe...)

        int[] newArr = new int[arr.length];

        int pointerSmall = 0; //PointerSmall => Start of arr
        int pointerLarge = arr.length - 1; //PointerLarge => End of arr

        //Flag which will help in switching between two pointers
        boolean switchPointer = true;

        for(int i = 0; i < arr.length; i ++) {
            if(switchPointer)
                newArr[i] = arr[pointerLarge--]; // copy large values
            else
                newArr[i] = arr[pointerSmall++]; // copy small values
                
            switchPointer = !switchPointer; // switching between SM and LG    
        }

        for(int j = 0; j < arr.length; j++) {
            arr[j] = newArr[j]; //copying to original array
        }
    }

    public static void main(String[] args){
        int[] arr = {1,2,3,4,10,20,50,6,5,-2,-1};
        maxMin(arr);
        System.out.println("ReArranged Array: ");
        for(int i = 0; i < arr.length; i++){
            System.out.print(arr[i] + " ");
        }
    }
}