package Arrays;

public class CheckRemoveEven {

	public static int[] removeEven(int[] arr) {
        //Loop through all numbers
        //if #'s remainder(%) is 0, skip, else return into new array...

        int oddElements = 0;

        //find number of odd elements in arr
        for (int i = 0; i < arr.length; i++) {
            if(arr[i] % 2 != 0) oddElements++;
        }

        //Create result array with size equal to the number 
        //of odd elements in arr
        int[] result = new int[oddElements];
        int result_index = 0;

        //Put odd values from arr to the resulted array
        for(int i = 0; i < arr.length; i++) {
            if(arr[i] % 2 != 0)
        result[result_index++] = arr[i];    
        } //end of loop

		// Write - Your - Code- Here
		return result; // change this anpackage Arrays;
	}
}