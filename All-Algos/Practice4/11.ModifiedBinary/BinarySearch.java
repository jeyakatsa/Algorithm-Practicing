public class BinarySearch {
  //Order Agnostic Binary Search
  //Given Sorted Array of numbers, find order in array for key

  //MY SOLUTION: Wrong AF.

  //Base case if there isn't anything in array
  //Loop through array,
  //if key found, return i
  public static int agnosticBinary(int[] arr, int key){
    if (arr == null || arr.length == 0) {
      return -1;
    }
    int i;
    for (i = 0; i < arr.length; i++) {
      if (key == arr[i]) {
        return i;
      }
      else {
        return -1;
      }
    }
    return i;
  }
  //OTHER'S SOLUTIONS...
  public static int agnosticBinarySearch(int[] arr, int key) {
    if (arr == null || arr.length == 0) {
        return -1;
    }
    int start = 0, end = arr.length - 1;
    boolean isAscending = arr[start] < arr[end];
    while (start <= end) {
      // calculate the middle of the current range
      int mid = start + (end - start) / 2;

      if (key == arr[mid])
        return mid;
  
      if (isAscending) { // ascending order
        if (key < arr[mid]) {
        end = mid - 1; // the 'key' can be in the first half
        } else { // key > arr[mid]
        start = mid + 1; // the 'key' can be in the second half
        }
      } else { // descending order        
        if (key > arr[mid]) {
        end = mid - 1; // the 'key' can be in the first half
        } else { // key < arr[mid]
        start = mid + 1; // the 'key' can be in the second half
        }
      }
    }
    return -1; // element not found        
  }
  
  //Ceiling of a Number
  public static int searchCeiling(int[] arr, int key) {
    if (arr == null || arr.length == 0 || key > arr[arr.length - 1]) {
      return -1;
    }
    int start = 0, end = arr.length - 1;
    while (start <= end) {
      int mid = start + (end - start) / 2;
      if (key < arr[mid]) {
        end = mid - 1;
      } else if (key > arr[mid]) {
        start = mid + 1;
      } else { // found the key
        return mid;
      }
    }
    // since the loop is running until 'start <= end', so at the end of the while loop, 'start == end+1'
    // we are not able to find the element in the given array, so the next big number will be arr[start]
    return start;
  }

  //Next Letter
  public static char searchNextLetter(char[] letters, char key) {
    int n = letters.length;
    if (key < letters[0] || key > letters[n - 1])
      return letters[0];

    int start = 0, end = n - 1;
    while (start <= end) {
      int mid = start + (end - start) / 2;
      if (key < letters[mid]) {
        end = mid - 1;
      } else { //if (key >= letters[mid])
        start = mid + 1;
      }
    }
    // since the loop is running until 'start <= end', so at the end of the while loop, 'start == end+1'
    return letters[start % n];
  }
  
  public static void main(String[] args) {
    System.out.println(BinarySearch.agnosticBinarySearch(new int[] { 4, 6, 10 }, 10));
    System.out.println(BinarySearch.agnosticBinarySearch(new int[] { 1, 2, 3, 4, 5, 6, 7 }, 5));
    System.out.println(BinarySearch.agnosticBinarySearch(new int[] { 10, 6, 4 }, 10));
    System.out.println(BinarySearch.agnosticBinarySearch(new int[] { 10, 6, 4 }, 4));
    System.out.println(NextLetter.searchNextLetter(new char[] { 'a', 'c', 'f', 'h' }, 'f'));
    System.out.println(NextLetter.searchNextLetter(new char[] { 'a', 'c', 'f', 'h' }, 'b'));
    System.out.println(NextLetter.searchNextLetter(new char[] { 'a', 'c', 'f', 'h' }, 'm'));
    System.out.println(NextLetter.searchNextLetter(new char[] { 'a', 'c', 'f', 'h' }, 'h'));        
  }
}