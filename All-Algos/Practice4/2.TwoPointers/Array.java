import java.util.*;

public class Array {
    public static void main (String[] args){
        int[] arr = {1,3,2,6,-1,4,1,8,2};
        int[] sortedArr = {1 ,1 ,2 ,3, 4, 5, 9, 9};
        int[] dutchArr = {0, 1, 2, 2, 2, 1, 0, 1};
        int k = 10;

        // int [] result = Array.search(arr, k);
        // System.out.print("Pair in array whose sum equals target: ["
        //  + result[0] + ", " + result[1] + "]");

        // System.out.println(Array.remove(sortedArr));

        // int[] result = Array.makeSquares(arr);
        // for (int num : result)
        //     System.out.print(num + " ");
        // System.out.println();

        // sort(dutchArr);
        // for (int num : dutchArr) {
        //     System.out.print(num + " ");
        // }

        System.out.print(quadruplets(arr, k));
    }


        //Find pair in array whose given sum is targetSum
    public static int[] search(int[] arr, int targetSum) {
        //Base case addition incase arr has nothing. return exception argument
        if (arr == null || arr.length == 0) {
            throw new IllegalArgumentException();
        }

        // //MY SOLUTION: not optimal for array pairs far away from each other.
        // //first pointer 's' to start at beginning of array
        // int s = 0; 
        // //2nd pointer 'x' to start at end of array
        // int x = arr.length - 1;
        // //for loop to loop through entire array
        // //1st pointer loops first, 2nd pointer doesn't loop
        // for (s = 0; s < arr.length; s++) {
        //     //if sum of 1st pointer and 2nd pointer equals targetSum, 
        //     if (targetSum == arr[s] + arr[x]) {
        //         //else if sum of 1st pointer and 2nd pointer doesn't equal targetSum,
        //         //.. decrement 2nd pointer and repeat If statement.
        //         if (targetSum != arr[s] + arr[x]) {
        //             x--;
        //         }
        //         return new int[] {arr[s], arr[x]};
        //          //...return new integer with 1st pointer and 2nd pointer
        //     }
        // }
        // //else return -1
        // return new int[] { -1, -1 };

        //Optimal Solution:
        int left = 0, right = arr.length - 1;
        while (left < right) {
          int currentSum = arr[left] + arr[right];
          if (currentSum == targetSum)
            return new int[] { left, right }; // found the pair
    
          if (targetSum > currentSum)
            left++; // we need a pair with a bigger sum
          else
            right--; // we need a pair with a smaller sum
        }
        return new int[] { -1, -1 };
    }

    //Remove duplicates from an array of sorted numbers, return arr.length
    public static int remove(int[] arr) {
        //base case for if array has no numbers, return illegalargumentexception
        //set left target (beginning of array) and right target (end of array)
        //if left target == right target, pop(remove left target & right target)
        //return arr.length
        //else arr.length;

        // MY SOLUTION: Wrong, no pop?
        // if (arr == null || 0 == arr.length) {
        //     throw new IllegalArgumentException();
        // }
        // int left = 0, right = arr.length - 1;
        // for (left = 0; left < arr.length; left++) {
        //     if (left == right) {
        //         arr.pop(arr[left], arr[right]);
        //         return arr.length;
        //     }
        //     if (left != right) {
        //         right--;
        //     }

        // }
        // return arr.length;

        //OTHER SOLUTION: right
        int nextNonDuplicate = 1; // index of the next non-duplicate element
        for (int i = 1; i < arr.length; i++) {
          if (arr[nextNonDuplicate - 1] != arr[i]) {
            arr[nextNonDuplicate] = arr[i];
            nextNonDuplicate++;
          }
        }
    
        return nextNonDuplicate;
    }

    //Given sorted Array, create new array containing squares of all numbers 
    //in sorted order...
    public static int[] makeSquares(int[] arr) {
        if (arr == null || 0 == arr.length) {
            throw new IllegalArgumentException();
        }
        int n = arr.length;
        int[] squares = new int[n];
        int highestSquareIdx = n - 1;
        int left = 0, right = arr.length - 1;
        while (left <= right) {
          int leftSquare = arr[left] * arr[left];
          int rightSquare = arr[right] * arr[right];
          if (leftSquare > rightSquare) {
            squares[highestSquareIdx--] = leftSquare;
            left++;
          } else {
            squares[highestSquareIdx--] = rightSquare;
            right--;
          }
        }
        return squares;
    }

    //Triplet Sum to Zero.
    //Given an array of unsorted numbers, find all unique triplets in
    //that add up to zero
    public static List<List<Integer>> searchTriplets(int[]arr){
        if (arr == null || 0 == arr.length) {
            throw new IllegalArgumentException();
        }
        Arrays.sort(arr);
        List<List<Integer>> triplets = new ArrayList<>();
        for (int i = 0; i < arr.length - 2; i++) {
            if (i > 0 && arr[i] == arr[i - 1])
                continue;
            searchPair(arr, -arr[i], i + 1, triplets);    
        }
        return triplets;
    }
    public static void searchPair(int[] arr, int targetSum, int left, 
    List<List<Integer>> triplets) {
        int right = arr.length - 1;
        while (left < right) {
            int currentSum = arr[left] + arr[right];
            if(currentSum == targetSum) { //found the triplet
                triplets.add(Arrays.asList(-targetSum, arr[left], arr[right]));
                left++;
                right--;
                while (left < right && arr[left] == arr[left - 1]){
                    left++; // skip same element to avoid duplicate triplets
                }
                while (left < right && arr[right] == arr[right + 1]){
                    right--; //skip same elements to avoid duplicate triplets
                }
            }
            else if (targetSum > currentSum) {
                left++;
            }
            else {
                right--;
            }
        }
    }

    //Triplet Sum Close to Target
    //Given an array of unsorted numbers and a target number, find a triplet
    //in array whose sum is as close to target as possible
    public static int searchSumTriplet (int[] arr, int targetSum) {
        if (arr == null || arr.length < 3) {
            throw new IllegalArgumentException();
        }
        Arrays.sort(arr);
        int smallestDifference = Integer.MAX_VALUE;
        for (int i = 0; i < arr.length - 2; i++) {
          int left = i + 1, right = arr.length - 1;
          while (left < right) {
                // comparing the sum of three numbers to the 'targetSum' can cause overflow
                // so, we will try to find a target difference
                int targetDiff = targetSum - arr[i] - arr[left] - arr[right];
                if (targetDiff == 0) //  we've found a triplet with an exact sum
                return targetSum - targetDiff; // return sum of all the numbers
        
                // the second part of the above 'if' is to handle the smallest sum when we have more than one solution
                if (Math.abs(targetDiff) < Math.abs(smallestDifference)
                    || (Math.abs(targetDiff) == Math.abs(smallestDifference) && targetDiff > smallestDifference))
                smallestDifference = targetDiff; // save the closest and the biggest difference  
        
                if (targetDiff > 0)
                left++; // we need a triplet with a bigger sum
                else
                right--; // we need a triplet with a smaller sum
            }
        }
        return targetSum - smallestDifference;
    }

    //Triplets Sum is less than target
    public static int lessTriplets (int[] arr, int target) {
        if (arr == null || arr.length < 3) {
            throw new IllegalArgumentException();
        }
        // //My Attempted Solution:
        // //Sort Array first...
        // //set 3 target pointers...
        // //while left is less than right, find sum that's less than target sum
        // Arrays.sort(arr);        
        // int i = 0, left = i + 1, right = arr.length - 1;
        // for(i = 0; i < arr.length - 2; i++){
        //     while (left < right) {
        //         int sum = arr[i] + arr[left] + arr[right];
        //         if (sum < targetSum) {

        //         }
        //     }
        // }

        //Their Convoluted Solution:
        Arrays.sort(arr);
        int count = 0;
        for (int i = 0; i < arr.length - 2; i++) {
          count += searchPair(arr, target - arr[i], i);
        }
        return count;
    }
    private static int searchPair(int[] arr, int targetSum, int first) {
        int count = 0;
        int left = first + 1, right = arr.length - 1;
        while (left < right) {
          if (arr[left] + arr[right] < targetSum) { // found the triplet
            // since arr[right] >= arr[left], therefore, we can replace arr[right] by any number between 
            // left and right to get a sum less than the target sum
            count += right - left;
            left++;
          } else {
            right--; // we need a pair with a smaller sum
          }
        }
        return count;
    }

    //Subarrays with Product Less than a Target (contiguous)
    private static List<List<Integer>> contiguousArray(int[] arr, int target) {
        if (arr == null || arr.length == 0) {
            throw new IllegalArgumentException();
        }
        //My BRUTE FORCE APPROACH
        //base case for if array is empty
        //initiate subarray to return contiguous less than target
        //Sort Array (to simplify)
        //loop through array
        //if arr[i] < target
        //return subArray[arr[i]];
        //loop through array again (for output consistency)
        //if arr[i] * arr[i+1] < target
        //return subArray[arr[i], arr[i+1]]
        //else if arr[i] >= target or arr[i] * arr[i+1] >= target
        //return null


        //THEIR SOLUTION: (On^2 time, On space)
        List<List<Integer>> result = new ArrayList<>();
        int product = 1, left = 0;
        for (int right = 0; right < arr.length; right++) {
          product *= arr[right];
          while (product >= target && left < arr.length)
            product /= arr[left++];
          // since the product of all numbers from left to right is less than the target therefore,
          // all subarrays from left to right will have a product less than the target too; to avoid
          // duplicates, we will start with a subarray containing only arr[right] and then extend it
          List<Integer> tempList = new LinkedList<>();
          for (int i = right; i >= left; i--) {
            tempList.add(0, arr[i]);
            result.add(new ArrayList<>(tempList));
          }
        }
        return result;
    }

    //Dutch National Flag
    public static void sort(int[] arr){
        //base case to set 
        //sort array. then return array
        if (arr == null || arr.length == 0) {
            throw new IllegalArgumentException();
        }
        int low = 0, high = arr.length - 1;
        for (int i = 0; i <= high;) {
            if (arr[i] == 0) {
                swap(arr, i, low);
                i++;
                low++;
            }
            else if (arr[i] == 1) {
                i++;
            }
            else { //case for arr[i] == 2
                swap (arr, i, high);
                high--;
            }
        }
    }
    private static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }


    //Quadruple Sum to Target
    //Given array of unsorted numbers and target, find all unique
    //quadriplets in it, whose sum is equal to target
    public static List<List<Integer>> quadruplets(int[] arr, int target) {
        if (arr == null || arr.length < 4) {
            throw new IllegalArgumentException();
        }
        Arrays.sort(arr);
        List<List<Integer>> quadruplets = new ArrayList<>();
        for (int i = 0; i < arr.length - 3; i++) {
            if(i > 0 && arr[i] == arr[i - 1]) { // skip element to avoid duplicate quadruplets
                continue;
            }
            for (int j = i + 1; j < arr.length - 2; j++) {
                if (j > i + 1 && arr[j] == arr[j - 1]){
                    continue;
                }
                searchPairs(arr, target, i, j, quadruplets);    
            }    
        }
        return quadruplets;
    }

    private static void searchPairs(int[] arr, int targetSum, int first, int second, List<List<Integer>> quadruplets) {
        int left = second + 1;
        int right = arr.length - 1;
        while (left < right) {
          int sum = arr[first] + arr[second] + arr[left] + arr[right];
          if (sum == targetSum) { // found the quadruplet
            quadruplets.add(Arrays.asList(arr[first], arr[second], arr[left], arr[right]));
            left++;
            right--;
            while (left < right && arr[left] == arr[left - 1])
              left++; // skip same element to avoid duplicate quadruplets
            while (left < right && arr[right] == arr[right + 1])
              right--; // skip same element to avoid duplicate quadruplets
          } else if (sum < targetSum)
            left++; // we need a pair with a bigger sum
          else
            right--; // we need a pair with a smaller sum
        }
      }

    
}