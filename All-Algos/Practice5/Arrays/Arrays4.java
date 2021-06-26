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


    
}
