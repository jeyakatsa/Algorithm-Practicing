import java.util.*;

public class Array {
    public static void main (String[] args){
        int[] arr = {1,3,2,6,-1,4,1,8,2};
        int[] sortedArr = {1 ,1 ,2 ,3, 4, 5, 9, 9};
        int k = 7;

        // int [] result = Array.search(arr, k);
        // System.out.print("Pair in array whose sum equals target: ["
        //  + result[0] + ", " + result[1] + "]");

        // System.out.println(Array.remove(sortedArr));

        // int[] result = Array.makeSquares(arr);
        // for (int num : result)
        //     System.out.print(num + " ");
        // System.out.println();

        System.out.println(searchTriplets(arr));
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
    //in array hose sum is as close to target as possible
    public static int[] searchSumTripplet (int[] arr, int target) {
        if (arr == null || 0 == arr.length) {
            throw new IllegalArgumentException();
        }

    }
}