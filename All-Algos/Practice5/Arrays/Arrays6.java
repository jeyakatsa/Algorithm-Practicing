import java.util.*;

public class Arrays6 {

  //Gas Station
  public int canCompleteCircuit(int[] gas, int[] cost) {
      int n = gas.length;
  
      int total_tank = 0;
      int curr_tank = 0;
      int starting_station = 0;
      for (int i = 0; i < n; ++i) {
        total_tank += gas[i] - cost[i];
        curr_tank += gas[i] - cost[i];
        // If one couldn't get here,
        if (curr_tank < 0) {
          // Pick up the next station as the starting one.
          starting_station = i + 1;
          // Start with an empty tank.
          curr_tank = 0;
        }
      }
      return total_tank >= 0 ? starting_station : -1;
  }   


  //Permutations... 
  public void backtrack(int n,
                        ArrayList<Integer> nums,
                        List<List<Integer>> output,
                        int first) {
    // if all integers are used up
    if (first == n)
    output.add(new ArrayList<Integer>(nums));
    for (int i = first; i < n; i++) {
    // place i-th integer first 
    // in the current permutation
    Collections.swap(nums, first, i);
    // use next integers to complete the permutations
    backtrack(n, nums, output, first + 1);
    // backtrack
    Collections.swap(nums, first, i);
    }
  }
  public List<List<Integer>> permute(int[] nums) {
      // init output list
      List<List<Integer>> output = new LinkedList<>();

      // convert nums into list since the output is a list of lists
      ArrayList<Integer> nums_lst = new ArrayList<Integer>();
      for (int num : nums)
      nums_lst.add(num);

      int n = nums.length;
      backtrack(n, nums_lst, output, 0);
      return output;
  }   
  
  
  //Interval List Intersections...
  public int[][] intervalIntersection(int[][] A, int[][] B) {
      List<int[]> ans = new ArrayList<>();
      int i = 0, j = 0;
  
      while (i < A.length && j < B.length) {
        // Let's check if A[i] intersects B[j].
        // lo - the startpoint of the intersection
        // hi - the endpoint of the intersection
        int lo = Math.max(A[i][0], B[j][0]);
        int hi = Math.min(A[i][1], B[j][1]);
        if (lo <= hi)
          ans.add(new int[]{lo, hi});
  
        // Remove the interval with the smallest endpoint
        if (A[i][1] < B[j][1])
          i++;
        else
          j++;
      }
  
      return ans.toArray(new int[ans.size()][]);
  }
  
  
  //Sign of the Product in Array
  public int arraySign(int[] nums) {
      int sign = 1;
      for (int i : nums) {
          if (i == 0) return 0;
          if (i < 0) sign *= -1;
      }
      return sign;
  }

  
  
  //Minimum Moves Equal Array
  public int minMoves(int[] nums) {
      Arrays.sort(nums);
      int count = 0;
      for (int i = nums.length - 1; i > 0; i--) {
          count += nums[i] - nums[0];
      }
      return count;
  } 
  
  


  //Shortest Path in Binary Matrix
  private static final int[][] directions = 
  new int[][]{{-1, -1}, {-1, 0}, {-1, 1}, {0, -1}, {0, 1}, {1, -1}, {1, 0}, {1, 1}};
  public int shortestPathBinaryMatrix(int[][] grid) {
    // Firstly, we need to check that the start and target cells are open.
    if (grid[0][0] != 0 || grid[grid.length - 1][grid[0].length - 1] != 0) {
        return -1;
    }
    // Set up the BFS.
    Queue<int[]> queue = new ArrayDeque<>();
    grid[0][0] = 1;
    queue.add(new int[]{0, 0});
    // Carry out the BFS
    while (!queue.isEmpty()) {
      int[] cell = queue.remove();
      int row = cell[0];
      int col = cell[1];
      int distance = grid[row][col];
      if (row == grid.length - 1 && col == grid[0].length - 1) {
          return distance;
      }
      for (int[] neighbour : getNeighbours(row, col, grid)) {
          int neighbourRow = neighbour[0];
          int neighbourCol = neighbour[1];
          queue.add(new int[]{neighbourRow, neighbourCol});
          grid[neighbourRow][neighbourCol] = distance + 1;
      }
    }
    // The target was unreachable.
    return -1;
  }
  private List<int[]> getNeighbours(int row, int col, int[][] grid) {
    List<int[]> neighbours = new ArrayList<>();
    for (int i = 0; i < directions.length; i++) {
        int newRow = row + directions[i][0];
        int newCol = col + directions[i][1];
        if (newRow < 0 || newCol < 0 || newRow >= grid.length 
                || newCol >= grid[0].length
                || grid[newRow][newCol] != 0) {
            continue;    
        }
        neighbours.add(new int[]{newRow, newCol});
    }
    return neighbours; 
  }  


  //Find Peak Element
  public int findPeakElement(int[] nums) {
    return search(nums, 0, nums.length - 1);
  }
  public int search(int[] nums, int l, int r) {
      if (l == r)
          return l;
      int mid = (l + r) / 2;
      if (nums[mid] > nums[mid + 1])
          return search(nums, l, mid);
      return search(nums, mid + 1, r);
  }


  //Moving Average from Data Structure
  int size, windowSum = 0, count = 0;
  Deque queue = new ArrayDeque<Integer>();
  public void MovingAverage(int size) {
    this.size = size;
  }
  public double next(int val) {
    ++count;
    // calculate the new sum by shifting the window
    queue.add(val);
    int tail = count > size ? (int)queue.poll() : 0;
    windowSum = windowSum - tail + val;
    return windowSum * 1.0 / Math.min(size, count);
  }  



  //Diagonal Traverse
  public int[] findDiagonalOrder(int[][] matrix) {
        
    // Check for empty matrices
    if (matrix == null || matrix.length == 0) {
        return new int[0];
    }
    
    // Variables to track the size of the matrix
    int N = matrix.length;
    int M = matrix[0].length;
    
    // The two arrays as explained in the algorithm
    int[] result = new int[N*M];
    int k = 0;
    ArrayList<Integer> intermediate = new ArrayList<Integer>();
    
    // We have to go over all the elements in the first
    // row and the last column to cover all possible diagonals
    for (int d = 0; d < N + M - 1; d++) {
        
        // Clear the intermediate array every time we start
        // to process another diagonal
        intermediate.clear();
        
        // We need to figure out the "head" of this diagonal
        // The elements in the first row and the last column
        // are the respective heads.
        int r = d < M ? 0 : d - M + 1;
        int c = d < M ? d : M - 1;
        
        // Iterate until one of the indices goes out of scope
        // Take note of the index math to go down the diagonal
        while (r < N && c > -1) {
            
            intermediate.add(matrix[r][c]);
            ++r;
            --c;
        }
            
        // Reverse even numbered diagonals. The
        // article says we have to reverse odd 
        // numbered articles but here, the numbering
        // is starting from 0 :P
        if (d % 2 == 0) {
            Collections.reverse(intermediate);
        }
        
        for (int i = 0; i < intermediate.size(); i++) {
            result[k++] = intermediate.get(i);
        }
    }
    return result;
  }  


  //Search a 2D Matrix
  public boolean searchMatrix(int[][] matrix, int target) {
    int m = matrix.length;
    if (m == 0)
        return false;
    int n = matrix[0].length;

    // binary search
    int left = 0, right = m * n - 1;
    int pivotIdx, pivotElement;
    while (left <= right) {
        pivotIdx = (left + right) / 2;
        pivotElement = matrix[pivotIdx / n][pivotIdx % n];
        if (target == pivotElement)
            return true;
        else {
            if (target < pivotElement)
                right = pivotIdx - 1;
            else
                left = pivotIdx + 1;
        }
    }
    return false;
  }  


















    
}
