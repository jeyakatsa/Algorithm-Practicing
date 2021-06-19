public class Arrays4 {


    //Find First and Last Position of Element in Sorted Array    
    // public int[] searchRange(int[] nums, int target) {
    //     int i;
    //     if (nums == null || nums.length ==0 || target!= nums[i]){
    //         return new int[] {-1,-1};
    //     }
    //     int[] result;        
    //     for (i = 0; i < nums.length; i++) {
    //         if (target == nums[i]) {
    //             result {i,i};
    //         }
    //     }
    //     return result;       
    // }     
    public int[] searchRange(int[] nums, int target) {      
        int firstOccurrence = this.findBound(nums, target, true);       
        if (firstOccurrence == -1) {
            return new int[]{-1, -1};
        }      
        int lastOccurrence = this.findBound(nums, target, false);      
        return new int[]{firstOccurrence, lastOccurrence};
    }  
    private int findBound(int[] nums, int target, boolean isFirst) {
        int N = nums.length;
        int begin = 0, end = N - 1;
        while (begin <= end) { 
            int mid = (begin + end) / 2;
            if (nums[mid] == target) {        
                if (isFirst) {            
                    // This means we found our lower bound.
                    if (mid == begin || nums[mid - 1] != target) {
                        return mid;
                    }                
                    // Search on the left side for the bound.
                    end = mid - 1;                  
                } else {                  
                    // This means we found our upper bound.
                    if (mid == end || nums[mid + 1] != target) {
                        return mid;
                    }                   
                    // Search on the right side for the bound.
                    begin = mid + 1;
                }               
            } else if (nums[mid] > target) {
                end = mid - 1;
            } else {
                begin = mid + 1;
            }
        }   
        return -1;
    } 
    
    

    //Dot Product of Two Sparse Vectors
    private int[] array;
    Arrays4(int[] nums) {
        array = nums;
    }
    public int dotProduct(Arrays4 vec) {
        int result = 0;
        for (int i = 0; i < array.length; i++) {
            result += array[i] * vec.array[i];
        }
        return result;
    }  


    
}
