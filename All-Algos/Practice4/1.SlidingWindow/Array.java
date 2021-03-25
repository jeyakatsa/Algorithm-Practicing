import java.util.*;

public class Array {
    public static void main (String[] args){
        // int[] arr = {1,3,2,6,-1,4,1,8,2};
        // int k = 5;
        // int result = Array.smallestSubArray(k, arr);
        // System.out.print("Smallest subarrays of sum s: " + result);
        String str = "aaarli";
        int k = 2;
        System.out.println("Length of Longest Substring: " + Array.longestSubString(str, k)); 
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

}