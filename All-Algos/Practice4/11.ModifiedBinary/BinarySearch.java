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

  //Number Range
  public static int[] findRange(int[] arr, int key) {
    int[] result = new int[] { -1, -1 };
    result[0] = search(arr, key, false);
    if (result[0] != -1) // no need to search, if 'key' is not present in the input array
      result[1] = search(arr, key, true);
    return result;
  }

  // modified Binary Search
  private static int searchItt(int[] arr, int key, boolean findMaxIndex) {
    int keyIndex = -1;
    int start = 0, end = arr.length - 1;
    while (start <= end) {
      int mid = start + (end - start) / 2;
      if (key < arr[mid]) {
        end = mid - 1;
      } else if (key > arr[mid]) {
        start = mid + 1;
      } else { // key == arr[mid]
        keyIndex = mid;
        if (findMaxIndex)
          start = mid + 1; // search ahead to find the last index of 'key'
        else
          end = mid - 1; // search behind to find the first index of 'key'
      }
    }
    return keyIndex;
  }

  //Search in a Sorted Infinite Array
  class ArrayReader {
    int[] arr;
    ArrayReader(int[] arr) {
      this.arr = arr;
    }
    public int get(int index) {
      if (index >= arr.length)
        return Integer.MAX_VALUE;
      return arr[index];
    }
  }
  public static int searchIt(ArrayReader reader, int key) {
    // find the proper bounds first
    int start = 0, end = 1;
    while (reader.get(end) < key) {
      int newStart = end + 1;
      end += (end - start + 1) * 2; // increase to double the bounds size
      start = newStart;
    }
    return binarySearch(reader, key, start, end);
  }
  private static int binarySearch(ArrayReader reader, int key, int start, int end) {
    while (start <= end) {
      int mid = start + (end - start) / 2;
      if (key < reader.get(mid)) {
        end = mid - 1;
      } else if (key > reader.get(mid)) {
        start = mid + 1;
      } else { // found the key
        return mid;
      }
    }
    return -1;
  }

  //Search Minimum Difference.
  public static int searchMinDiffElement(int[] arr, int key) {
    if (key < arr[0])
      return arr[0];
    if (key > arr[arr.length - 1])
      return arr[arr.length - 1];

    int start = 0, end = arr.length - 1;
    while (start <= end) {
      int mid = start + (end - start) / 2;
      if (key < arr[mid]) {
        end = mid - 1;
      } else if (key > arr[mid]) {
        start = mid + 1;
      } else {
        return arr[mid];
      }
    }

    // at the end of the while loop, 'start == end+1'
    // we are not able to find the element in the given array
    // return the element which is closest to the 'key'
    if ((arr[start] - key) < (key - arr[end]))
      return arr[start];
    return arr[end];
  }


  //Bitonic Array Maximum
  public static int findMax(int[] arr) {

    //MY SOLUTION: Wrong AF
    //base case if array doesn't exist
    //sort array
    //loop through array
    //if (arr[i] > arr[i+1]) temp = arr[i], arr[i] = arr[i+1], arr[i+1] = temp;
    ///exit loop then return array.length - 1
    if (arr == null || arr.length == 0) {
      return -1;
    }
    for (int i = 0; i < arr.length; i++) {
      if (arr[i] > arr[i+1]) {
        int temp = arr[i];
        arr[i] = arr[i+1];
        arr[i+1] = temp;
      }
      else {
        
      }

    }
    return arr[arr.length - 1];

    //OTHER SOLUTION...
    int start = 0, end = arr.length - 1;
    while (start < end) {
      int mid = start + (end - start) / 2;
      if (arr[mid] > arr[mid + 1]) {
        end = mid;
      } else {
        start = mid + 1;
      }
    }

    // at the end of the while loop, 'start == end'
    return arr[start];
  }

  //Bitonic Array Search
  public static int searchMaxBitonic(int[] arr, int key) {
    int maxIndex = findMaxBitonic(arr);
    int keyIndex = binarySearching(arr, key, 0, maxIndex);
    if (keyIndex != -1)
      return keyIndex;
    return binarySearching(arr, key, maxIndex + 1, arr.length - 1);
  }
  public static int findMaxBitonic(int[] arr) {
    if (arr == null || arr.length == 0) {
      return -1;
    }
    int start = 0, end = arr.length - 1;
    while (start <= end) {
      int mid = start + (end - start) / 2;
      if (arr[mid] > arr[mid + 1]){
        end = mid;
      } else {
        start = mid + 1;
      }
    }
    return start;
  } 
  // order-agnostic binary search
  private static int binarySearching(int[] arr, int key, int start, int end) {
    while (start <= end) {
      int mid = start + (end - start) / 2;

      if (key == arr[mid])
        return mid;

      if (arr[start] < arr[end]) { // ascending order
        if (key < arr[mid]) {
          end = mid - 1;
        } else { // key > arr[mid]
          start = mid + 1;
        }
      } else { // descending order        
        if (key > arr[mid]) {
          end = mid - 1;
        } else { // key < arr[mid]
          start = mid + 1;
        }
      }
    }
    return -1; // element is not found
  }   

  //Search in Rotared Array
  public static int search(int[] arr, int key) {
    int start = 0, end = arr.length - 1;
    while (start <= end) {
      int mid = start + (end - start) / 2;
      if (arr[mid] == key)
        return mid;

      if (arr[start] <= arr[mid]) { // left side is sorted in ascending order
        if (key >= arr[start] && key < arr[mid]) {
          end = mid - 1;
        } else { //key > arr[mid]
          start = mid + 1;
        }
      } else { // right side is sorted in ascending order       
        if (key > arr[mid] && key <= arr[end]) {
          start = mid + 1;
        } else {
          end = mid - 1;
        }
      }
    }

    // we are not able to find the element in the given array
    return -1;
  }

  //Rotation Count
  public static int countRotations(int[] arr) {
  //   //base case
  //   //rotate start, end etc.
  //   //if start to mid is greater than mid to end
  //   //return count of how many integers from start to mid
  //  // else return -1
  //   if (arr == null || arr.length == 0) {
  //     return -1;
  //   }
  //   int start = 0, end = arr.length - 1;
  //   while (start < end) {
  //     int mid = start + (end - start) / 2;

  //     if (start)// STUCK
  //   }
  //   return -1; 

    int start = 0, end = arr.length - 1;
    while (start < end) {
      int mid = start + (end - start) / 2;

      if (mid < end && arr[mid] > arr[mid + 1]) // if mid is greater than the next element
        return mid + 1;
      if (mid > start && arr[mid - 1] > arr[mid]) // if mid is smaller than the previous element
        return mid;

      if (arr[start] < arr[mid]) { // left side is sorted, so the pivot is on right side
        start = mid + 1;
      } else { // right side is sorted, so the pivot is on the left side     
        end = mid - 1;
      }
    }

    return 0; // the array has not been rotated  
  }  


  
  public static void main(String[] args) {
    // System.out.println(BinarySearch.agnosticBinarySearch(new int[] { 4, 6, 10 }, 10));
    // System.out.println(BinarySearch.agnosticBinarySearch(new int[] { 1, 2, 3, 4, 5, 6, 7 }, 5));
    // System.out.println(BinarySearch.agnosticBinarySearch(new int[] { 10, 6, 4 }, 10));
    // System.out.println(BinarySearch.agnosticBinarySearch(new int[] { 10, 6, 4 }, 4));
    // System.out.println(NextLetter.searchNextLetter(new char[] { 'a', 'c', 'f', 'h' }, 'f'));
    // System.out.println(NextLetter.searchNextLetter(new char[] { 'a', 'c', 'f', 'h' }, 'b'));
    // System.out.println(NextLetter.searchNextLetter(new char[] { 'a', 'c', 'f', 'h' }, 'm'));
    // System.out.println(NextLetter.searchNextLetter(new char[] { 'a', 'c', 'f', 'h' }, 'h'));
    // int[] result = FindRange.findRange(new int[] { 4, 6, 6, 6, 9 }, 6);
    // System.out.println("Range: [" + result[0] + ", " + result[1] + "]");
    // result = FindRange.findRange(new int[] { 1, 3, 8, 10, 15 }, 10);
    // System.out.println("Range: [" + result[0] + ", " + result[1] + "]");
    // result = FindRange.findRange(new int[] { 1, 3, 8, 10, 15 }, 12);
    // System.out.println("Range: [" + result[0] + ", " + result[1] + "]"); 
    // ArrayReader reader = new ArrayReader(new int[] { 4, 6, 8, 10, 12, 14, 16, 18, 20, 22, 24, 26, 28, 30 });
    // System.out.println(SearchInfiniteSortedArray.search(reader, 16));
    // System.out.println(SearchInfiniteSortedArray.search(reader, 11));
    // reader = new ArrayReader(new int[] { 1, 3, 8, 10, 15 });
    // System.out.println(SearchInfiniteSortedArray.search(reader, 15));
    // System.out.println(SearchInfiniteSortedArray.search(reader, 200));   

    System.out.println(countRotations(new int[] {5, 6, 8, 1, 2, 3}));
  }
}