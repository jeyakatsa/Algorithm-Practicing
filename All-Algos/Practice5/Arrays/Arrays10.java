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

    //Number of Islands
    void dfs(char[][] grid, int r, int c) {
        int nr = grid.length;
        int nc = grid[0].length;
    
        if (r < 0 || c < 0 || r >= nr || c >= nc || grid[r][c] == '0') {
          return;
        }
    
        grid[r][c] = '0';
        dfs(grid, r - 1, c);
        dfs(grid, r + 1, c);
        dfs(grid, r, c - 1);
        dfs(grid, r, c + 1);
    }
    
    public int numIslands(char[][] grid) {
        if (grid == null || grid.length == 0) {
          return 0;
        }
    
        int nr = grid.length;
        int nc = grid[0].length;
        int num_islands = 0;
        for (int r = 0; r < nr; ++r) {
          for (int c = 0; c < nc; ++c) {
            if (grid[r][c] == '1') {
              ++num_islands;
              dfs(grid, r, c);
            }
          }
        }
    
        return num_islands;
    }   

    //Meeting Rooms II 
    public int minMeetingRooms(int[][] intervals) {
        
        // Check for the base case. If there are no intervals, return 0
        if (intervals.length == 0) {
          return 0;
        }
    
        Integer[] start = new Integer[intervals.length];
        Integer[] end = new Integer[intervals.length];
    
        for (int i = 0; i < intervals.length; i++) {
          start[i] = intervals[i][0];
          end[i] = intervals[i][1];
        }
    
        // Sort the intervals by end time
        Arrays.sort(
            end,
            new Comparator<Integer>() {
              public int compare(Integer a, Integer b) {
                return a - b;
              }
            });
    
        // Sort the intervals by start time
        Arrays.sort(
            start,
            new Comparator<Integer>() {
              public int compare(Integer a, Integer b) {
                return a - b;
              }
            });
    
        // The two pointers in the algorithm: e_ptr and s_ptr.
        int startPointer = 0, endPointer = 0;
    
        // Variables to keep track of maximum number of rooms used.
        int usedRooms = 0;
    
        // Iterate over intervals.
        while (startPointer < intervals.length) {
    
          // If there is a meeting that has ended by the time the meeting at `start_pointer` starts
          if (start[startPointer] >= end[endPointer]) {
            usedRooms -= 1;
            endPointer += 1;
          }
    
          // We do this irrespective of whether a room frees up or not.
          // If a room got free, then this used_rooms += 1 wouldn't have any effect. used_rooms would
          // remain the same in that case. If no room was free, then this would increase used_rooms
          usedRooms += 1;
          startPointer += 1;
    
        }
        return usedRooms;
    }    


    //3 Sum
    public List<List<Integer>> threeSum(int[] nums) {
        Arrays.sort(nums);
        List<List<Integer>> res = new ArrayList<>();
        for (int i = 0; i < nums.length && nums[i] <= 0; ++i)
            if (i == 0 || nums[i - 1] != nums[i]) {
                twoSumII(nums, i, res);
            }
        return res;
    }
    void twoSumII(int[] nums, int i, List<List<Integer>> res) {
        int lo = i + 1, hi = nums.length - 1;
        while (lo < hi) {
            int sum = nums[i] + nums[lo] + nums[hi];
            if (sum < 0) {
                ++lo;
            } else if (sum > 0) {
                --hi;
            } else {
                res.add(Arrays.asList(nums[i], nums[lo++], nums[hi--]));
                while (lo < hi && nums[lo] == nums[lo - 1])
                    ++lo;
            }
        }
    }
    
    //K Closest Points to Origin
    public int[][] kClosest(int[][] points, int K) {
        if(K == points.length) return points;
        
        PriorityQueue<int[]> pq = new PriorityQueue<>(K, new Comparator<int[]>() {
            public int compare(int[] a, int[] b) {
                return (b[0]*b[0] + b[1]*b[1]) - (a[0]*a[0] + a[1]*a[1]);
            }
        });
        
        for(int[] point: points) {
            pq.add(point);
            if(pq.size() > K) pq.poll();
        }
        return pq.toArray(new int[0][0]);
    }    
    

    
}
