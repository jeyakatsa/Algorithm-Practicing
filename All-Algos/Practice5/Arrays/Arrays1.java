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
              grid[r][c] = '0'; // mark as visited
              Queue<Integer> neighbors = new LinkedList<>();
              neighbors.add(r * nc + c);
              while (!neighbors.isEmpty()) {
                int id = neighbors.remove();
                int row = id / nc;
                int col = id % nc;
                if (row - 1 >= 0 && grid[row-1][col] == '1') {
                  neighbors.add((row-1) * nc + col);
                  grid[row-1][col] = '0';
                }
                if (row + 1 < nr && grid[row+1][col] == '1') {
                  neighbors.add((row+1) * nc + col);
                  grid[row+1][col] = '0';
                }
                if (col - 1 >= 0 && grid[row][col-1] == '1') {
                  neighbors.add(row * nc + col-1);
                  grid[row][col-1] = '0';
                }
                if (col + 1 < nc && grid[row][col+1] == '1') {
                  neighbors.add(row * nc + col+1);
                  grid[row][col+1] = '0';
                }
              }
            }
          }
        }
    
        return num_islands;
      }
  
}