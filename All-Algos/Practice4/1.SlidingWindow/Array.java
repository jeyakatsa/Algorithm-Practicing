import java.util.*;

public class Array {
    public static void main (String[] args){
        int[] arr = {1,3,2,6,-1,4,1,8,2};
        int k = 7;
        // int result = Array.smallestSubArray(k, arr);
        // System.out.print("Smallest subarrays of sum s: " + result);
        
        // String str = "aaarli";
        // int k = 2;
        // System.out.println("Length of Longest Substring: " + Array.longestSubString(str, k)); 

        // System.out.println("Maximum number of fruits: " + 
        // Array.findLength(new char[] { 'A', 'B', 'C', 'A', 'C' }));
        // System.out.println("Maximum number of fruits: " + 
        // Array.findLength(new char[] { 'A', 'B', 'C', 'B', 'B', 'C' }));

        int [] result = Array.search(arr, k);
        System.out.print("Pair in array whose sum equals target: ["
         + result[0] + ", " + result[1] + "]");
    }

    //find contiguous subarrays of size '5' O(n * k)
    public static double[] findAverages(int k, int[] arr) {
        double[] result = new double[arr.length - k + 1];
        for(int i = 0; i <= arr.length - k; i++) {
            //find sum of next 'K' elements
            double sum = 0;
            for (int j = i; j < i + k; j++)
                sum += arr[j];
            result[i] = sum / k; // calculate average    
        }
        return result;
    }

    //SLIDING WINDOW find contiguous subarrays of size '5' O(n) time
    public static double[] findAvgs(int k, int[] arr) {
        double [] result = new double[arr.length - k + 1];
        double windowSum = 0;
        int windowStart = 0;
        for (int windowEnd = 0; windowEnd < arr.length; windowEnd++) {
          windowSum += arr[windowEnd]; // add the next element
          // slide the window, we don't need to slide if we've not hit the required window size of 'k'
          if (windowEnd >= k - 1) {
            result[windowStart] = windowSum / k; // calculate the average
            windowSum -= arr[windowStart]; // subtract the element going out
            windowStart++; // slide the window ahead
          }
        }
        return result;
    }

    //Maximum Sum Subarray of Size K
    public static int findMaxSumSubArray(int k, int[] arr) {
        int maxSum = 0;
        int windowSum = 0;
        int windowStart = 0;
        for (int windowEnd = 0; windowEnd < arr.length; windowEnd++) {
            windowSum += arr[windowEnd];
            if (windowEnd >= k - 1) {
                maxSum = Math.max(maxSum, windowSum);
                windowSum -= arr[windowStart];
                windowStart++;
            }
        }
        return maxSum;
    }

    //Smallest Subarray length whose Sum >= S 
    public static int smallestSubArray(int s, int[] arr) {
        int minLength = Integer.MAX_VALUE;
        int windowSum = 0;
        int windowStart = 0;
        for (int windowEnd = 0; windowEnd < arr.length; windowEnd++) {
            windowSum += arr[windowEnd];
            if(windowSum >= s){
                minLength = Math.min(minLength, windowEnd - windowStart + 1);
                windowSum -= arr[windowStart];
                windowStart++;
            }
        }
        return minLength == Integer.MAX_VALUE ? 0 : minLength;
    }
    
    //Longest Substring with K Distinct Characters
    public static int longestSubString(String str, int k) {
        if (str == null || str.length() == 0 || str.length() < k)
        throw new IllegalArgumentException();

        int windowStart = 0, maxLength = 0;
        Map<Character, Integer> charFrequencyMap = new HashMap<>();
        for(int windowEnd = 0; windowEnd < str.length(); windowEnd++) {
            char rightChar = str.charAt(windowEnd);
            charFrequencyMap.put(rightChar, charFrequencyMap.getOrDefault(rightChar, 0) + 1);
            while (charFrequencyMap.size() > k) {
                char leftChar = str.charAt(windowStart);
                charFrequencyMap.put(leftChar, charFrequencyMap.get(leftChar) - 1);
                if (charFrequencyMap.get(leftChar) == 0) {
                    charFrequencyMap.remove(leftChar);
                }
                windowStart++;
            }
            maxLength = Math.max(maxLength, windowEnd - windowStart + 1);
        }
        return maxLength; 
    }

    //Maximum Number of Fruits in Each Basket 1 fruit(letter), 1 basket.
    public static int findLength(char[] arr) {
        if (arr == null || arr.length == 0) {
            throw new IllegalArgumentException();
        }
        int windowStart = 0, maxLength = 0;
        Map<Character, Integer> fruitFrequencyMap = new HashMap<>();
        // try to extend the range [windowStart, windowEnd]
        for (int windowEnd = 0; windowEnd < arr.length; windowEnd++) {
            fruitFrequencyMap.put(arr[windowEnd], fruitFrequencyMap.getOrDefault(arr[windowEnd], 0) + 1);
            // shrink the sliding window, until we are left with '2' fruits in the frequency map
            while (fruitFrequencyMap.size() > 2) {
                fruitFrequencyMap.put(arr[windowStart], fruitFrequencyMap.get(arr[windowStart]) - 1);
                if (fruitFrequencyMap.get(arr[windowStart]) == 0) {
                    fruitFrequencyMap.remove(arr[windowStart]);
                }
                windowStart++;
            }
            maxLength = Math.max(maxLength, windowEnd - windowStart + 1);
        }
        return maxLength;
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

}