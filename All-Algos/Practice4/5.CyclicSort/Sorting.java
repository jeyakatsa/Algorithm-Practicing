import java.util.ArrayList;
import java.util.List;

public class Sorting {

  //Find all duplicate numbers
  public static List<Integer> findAllDuplicate(int[] nums) {
    if(nums == null || nums.length == 0) {
      throw new IllegalArgumentException("No Array Found!");
    }
    int i = 0;
    while(i < nums.length) {
      if (nums[i] != nums[nums[i] - 1]) {
        swap(nums, i, nums[i] - 1);
      }
      else {
        i++;
      }
    }
    List<Integer> duplicateNumbers = new ArrayList<>();
    for (i = 0; i < nums.length; i++) {
      if (nums[i] != i + 1)
        duplicateNumbers.add(nums[i]);
    }

    return duplicateNumbers;
  }
  
  //Find Duplicate Number
  //Base Case if nothing in array
  //Sort Array
  //if nums[i] == nums[i]+1
  //return nums[i]
  public static int findDuplicate(int[] nums){
    if(nums == null || nums.length == 0) {
      throw new IllegalArgumentException();
    }
    int i = 0;
    while (i < nums.length) {
      if (nums[i] != i + 1) {
        if (nums[i] != nums[nums[i] - 1])
          swap(nums, i, nums[i] - 1);
        else // we have found the duplicate
          return nums[i];
      } else {
        i++;
      }
    }

    return -1;
  }

  //Find all Missing Numbers
  public static List<Integer> findAllMissing(int[] nums) {
    if (nums == null || nums.length == 0) {
      throw new IllegalArgumentException();
    }
    int i = 0;
    while(i < nums.length) {
      if (nums[i] != nums[nums[i] - 1])
        swap(nums, i, nums[i] - 1);
      else
        i++;
    }

    List<Integer> missingNumbers = new ArrayList<>();
    for (i = 0; i < nums.length; i++)
      if (nums[i] != i + 1)
        missingNumbers.add(i + 1);

    return missingNumbers;

  }


  //Find missing number [eg. 6,2,3,1,5 = 4]
  //sort array first
  //iterate through array
  //if subarray[i]+1 does not equal subarray[i]+1
  //return
  public static int findMissingNumber(int[] nums) {
    int i = 0;
    while (i < nums.length) {
      if (nums[i] < nums.length && nums[i] != nums[nums[i]])
        swap(nums, i, nums[i]);
      else
        i++;
    }
    // find the first number missing from its index, that will be our required number
    for (i = 0; i < nums.length; i++)
      if (nums[i] != i)
        return i;

    return nums.length;
  }



  //Sort Array...
  public static void sort(int[] nums) {
    int i = 0;
    while (i < nums.length) {
      int j = nums[i] - 1;
      if (nums[i] != nums[j])
        swap(nums, i, j);
      else
        i++;
    }
  }
  private static void swap(int[] arr, int i, int j) {
    int temp = arr[i];
    arr[i] = arr[j];
    arr[j] = temp;
  }

  
  public static void main(String[] args) {
    List<Integer> arr = findAllDuplicate(new int[] { 2, 1, 5, 4, 1, 2, 9, 0 });
  //   sort(arr);
  //   for (int num : arr)
  //     System.out.print(num + " ");
  //   System.out.println();

  //   arr = new int[] { 2, 6, 4, 3, 1, 5 };
  //   sort(arr);
  //   for (int num : arr)
  //     System.out.print(num + " ");
  //   System.out.println();

  //   arr = new int[] { 1, 5, 6, 4, 3, 2 };
  //   sort(arr);
  //   for (int num : arr)
  //     System.out.print(num + " ");
  //   System.out.println();

    // List<Integer> missing = findAllMissing(new int[] { 3, 1, 5, 4, 2, 9, 0 });
  
    // System.out.print("Missing numbers: " + missing);



    System.out.println("Duplicate Numbers: " + arr);
  }
}