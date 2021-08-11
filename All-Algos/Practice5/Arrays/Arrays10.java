import java.util.*;

public class Arrays10 {

    //Maximum Sum of Two Non-Overlapping Subarrays
    // this technique is based on finding max sum from left and right for both first and second len
    // then find combination which produce max sum
    public int maxSumTwoNoOverlap(int[] nums, int firstLen, int secondLen) {
        int ans = 0;
        int[] right = maxSumFromRight(nums, secondLen);
        int[] left = maxSumFromLeft(nums, firstLen);
        for(int i = 0; i < nums.length - 1; i++) {
            ans = Math.max(left[i] + right[i + 1], ans);
        }
  
        right = maxSumFromRight(nums, firstLen);
        left = maxSumFromLeft(nums, secondLen);
        for(int i = 0; i < nums.length - 1; i++) {
            ans = Math.max(left[i] + right[i + 1], ans);
        }
    
        return ans;
    }
    public int[] maxSumFromRight(int[] nums, int len) {
        int n = nums.length;
        int[] right = new int[n];
        int max = 0;
        for(int i = n - 1; i >= len - 1; i--) {
            int sum = 0;
            for(int j = i; j > i - len; j--) sum += nums[j];
            max = Math.max(max, sum);
            right[i - len + 1] = max;
        }
        
        return right;  
    } 
    public int[] maxSumFromLeft(int[] nums, int len) {
        int n = nums.length;
        int[] left = new int[n];
        int max = 0;
        for(int i = 0; i <= n - len; i++) {
            int sum = 0;
            for(int j = i; j < i + len; j++) sum += nums[j];
            max = Math.max(max, sum);
            left[i + len - 1] = sum;
        }

        return left;
    }
    
    
    //Next Greater Element II
    public int[] nextGreaterElements(int[] nums) {
        int[] res = new int[nums.length];
        for (int i = 0; i < nums.length; i++) {
            res[i] = -1;
            for (int j = 1; j < nums.length; j++) {
                if (nums[(i + j) % nums.length] > nums[i]) {
                    res[i] = nums[(i + j) % nums.length];
                    break;
                }
            }
        }
        return res;
    }


    //Can Place Flowers
    public boolean canPlaceFlowers(int[] flowerbed, int n) {
        int i = 0, count = 0;
        while (i < flowerbed.length) {
            if (flowerbed[i] == 0 && (i == 0 || flowerbed[i - 1] == 0) && (i == flowerbed.length - 1 || flowerbed[i + 1] == 0)) {
                flowerbed[i] = 1;
                count++;
            }
            i++;
        }
        return count >= n;
    } 


    //Partition Array for Maximum Sum 
    public int maxSumAfterPartitioning(int[] arr, int k) {
        int N=arr.length;
        if(N==1) return arr[0];
        int[] dp = new int[N+1];
        
        for(int i=1;i<=N;i++){
            int currMax=0; int maxSumK=0;
            for(int sub=1;sub<=k && i-sub>=0;sub++){
                currMax=Math.max(currMax,arr[i-sub]);
                maxSumK=Math.max(maxSumK,dp[i-sub]+(currMax*sub));
            }
            dp[i]=maxSumK;
        }
        return dp[N];
    }  


    //Merge Intervals
    public int[][] merge(int[][] intervals) {
        Arrays.sort(intervals, (a, b) -> Integer.compare(a[0], b[0]));
        LinkedList<int[]> merged = new LinkedList<>();
        for (int[] interval : intervals) {
            // if the list of merged intervals is empty or if the current
            // interval does not overlap with the previous, simply append it.
            if (merged.isEmpty() || merged.getLast()[1] < interval[0]) {
                merged.add(interval);
            }
            // otherwise, there is overlap, so we merge the current and previous
            // intervals.
            else {
                merged.getLast()[1] = Math.max(merged.getLast()[1], interval[1]);
            }
        }
        return merged.toArray(new int[merged.size()][]);
    }

    
}
