import java.util.*;

public class Arrays1 {

  public static void main (String[] args) {

  }


  //TWO SUM
  //O(n^2) brute force solution...
  class Solution {
      public int[] twoSum(int[] nums, int target) {
          for (int i = 0; i < nums.length; i++) {
              for (int j = i + 1; j < nums.length; j++) {
                  if (nums[j] == target - nums[i]) {
                      return new int[] { i, j };
                  }
              }
          }
          // In case there is no solution, we'll just return null
          return null;
      }
  }
  //if including HashMap, then can separate for loops into solution O(n)

  //MERGE INTERVALS
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
  // O(MxN)
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

  //Subarray Sum Equals K O(n^2) "Window Method"
  public int subarraySum(int[] nums, int k) {
    int count = 0;
    for (int start = 0; start < nums.length; start++) {
      for (int end = start + 1; end <= nums.length; end++) {
        int sum = 0;
        for (int i = start; i < end; i++)
          sum += nums[i];
        if (sum == k)
          count++;
      }
    }
    return count;
  }

  //Best Time to Buy and Sell Stock
  public int maxProfit(int prices[]) {
    int maxprofit = 0;
    for (int i = 0; i < prices.length - 1; i++) {
        for (int j = i + 1; j < prices.length; j++) {
            int profit = prices[j] - prices[i];
            if (profit > maxprofit)
                maxprofit = profit;
        }
    }
    return maxprofit;
}
  
}