import java.util.*;

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



    //Find the Duplicate Number
    public int findDuplicate(int[] nums) {
        Arrays.sort(nums);        
        if (nums == null || nums.length == 0) {
            return -1;
        }

        for (int i = 1; i < nums.length; i++) {
            if (nums[i] == nums[i-1]) {            
            // (my solution... So CLOSE!) 
            // for (int i = 0..... if(nums[i] == nums[i++]) {
                return nums[i];
            }
        }
        return -1;
        
    }

    //Combination Sum
    protected void backtrack(
            int remain,
            LinkedList<Integer> comb,
            int start, int[] candidates,
            List<List<Integer>> results) {

        if (remain == 0) {
            // make a deep copy of the current combination
            results.add(new ArrayList<Integer>(comb));
            return;
        } else if (remain < 0) {
            // exceed the scope, stop exploration.
            return;
        }

        for (int i = start; i < candidates.length; ++i) {
            // add the number into the combination
            comb.add(candidates[i]);
            this.backtrack(remain - candidates[i], comb, i, candidates, results);
            // backtrack, remove the number from the combination
            comb.removeLast();
        }
    }

    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        List<List<Integer>> results = new ArrayList<List<Integer>>();
        LinkedList<Integer> comb = new LinkedList<Integer>();

        this.backtrack(target, comb, 0, candidates, results);
        return results;
    } 



    //Number Of Islands
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
    
    
    //K Closest Points To Origin
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
    
    
    // //Kth Largest Element in Array
    // My Solution:
    // public int findKthLargest(int[] nums, int k) {
    //     if (nums == null || nums.length == 0) {
    //         return -1;
    //     }
    //     Arrays.sort(nums);
    //     for (int i = 0; i < nums.length; i++) {
    //         if (k == i) {
    //             return nums[i];
    //         }
    //     }
    //     return -1;
        
    // }
    public int findKthLargest(int[] nums, int k) {
        final int N = nums.length;
        Arrays.sort(nums);
        return nums[N - k];       
    }      


    
}
