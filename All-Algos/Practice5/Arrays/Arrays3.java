import java.util.*;

public class Arrays3 {



    //CONTAINER WITH MOST WATER
    public int maxArea(int[] height) {
        int maxarea = 0;
        for (int i = 0; i < height.length; i++)
            for (int j = i + 1; j < height.length; j++)
                maxarea = Math.max(maxarea, Math.min(height[i], height[j]) * (j - i));
        return maxarea;
    }




    //ROTATE IMAGE
    public void rotate(int[][] matrix) {
        int n = matrix.length;
        for (int i = 0; i < (n + 1) / 2; i ++) {
            for (int j = 0; j < n / 2; j++) {
                int temp = matrix[n - 1 - j][i];
                matrix[n - 1 - j][i] = matrix[n - 1 - i][n - j - 1];
                matrix[n - 1 - i][n - j - 1] = matrix[j][n - 1 -i];
                matrix[j][n - 1 - i] = matrix[i][j];
                matrix[i][j] = temp;
            }
        }
    } 
    
    
    //Pairs of Songs With Total Durations Divisible by 60
    public int numPairsDivisibleBy60(int[] time) {
        int count = 0, n = time.length;
        for (int i = 0; i < n; i++) {
            // j starts with i+1 so that i is always to the left of j
            // to avoid repetitive counting
            for (int j = i + 1; j < n; j++) {
                if ((time[i] + time[j]) % 60 == 0) {
                    count++;
                }
            }
        }
        return count;
    }




    //WORD SEARCH
    private char[][] board;
    private int ROWS;
    private int COLS;
    public boolean exist(char[][] board, String word) {
        this.board = board;
        this.ROWS = board.length;
        this.COLS = board[0].length;

        for (int row = 0; row < this.ROWS; ++row)
        for (int col = 0; col < this.COLS; ++col)
            if (this.backtrack(row, col, word, 0))
            return true;
        return false;
    }
    protected boolean backtrack(int row, int col, String word, int index) {
        /* Step 1). check the bottom case. */
        if (index >= word.length())
        return true;

        /* Step 2). Check the boundaries. */
        if (row < 0 || row == this.ROWS || col < 0 || col == this.COLS
            || this.board[row][col] != word.charAt(index))
        return false;

        /* Step 3). explore the neighbors in DFS */
        boolean ret = false;
        // mark the path before the next exploration
        this.board[row][col] = '#';

        int[] rowOffsets = {0, 1, 0, -1};
        int[] colOffsets = {1, 0, -1, 0};
        for (int d = 0; d < 4; ++d) {
        ret = this.backtrack(row + rowOffsets[d], col + colOffsets[d], word, index + 1);
        if (ret)
            break;
        }

        /* Step 4). clean up and return the result. */
        this.board[row][col] = word.charAt(index);
        return ret;
    }


    //LEFT MOST COLUMN ITH AT LEAST A ONE (confusing F'n problem...)
    // interface BinaryMatrix {
    //     public int get(int row, int col) {
            

    //     }
    //     public List<Integer> dimensions {

    //     }
    // }
    // public int leftMostColumnWithOne(BinaryMatrix binaryMatrix) {
    //     int rows = binaryMatrix.dimensions().get(0);
    //     int cols = binaryMatrix.dimensions().get(1);
    //     int smallestIndex = cols;
    //     for (int row = 0; row < rows; row++) {
    //         // Binary Search for the first 1 in the row.
    //         int lo = 0;
    //         int hi = cols - 1;
    //         while (lo < hi) {
    //             int mid = (lo + hi) / 2;
    //             if (binaryMatrix.get(row, mid) == 0) {
    //                 lo = mid + 1;
    //             }
    //             else {
    //                 hi = mid;
    //             }
    //         }
    //         // If the last element in the search space is a 1, then this row
    //         // contained a 1.
    //         if (binaryMatrix.get(row, lo) == 1) {
    //             smallestIndex = Math.min(smallestIndex, lo);
    //         }
    //     }
    //     // If smallest_index is still set to cols, then there were no 1's in 
    //     // the grid. 
    //     return smallestIndex == cols ? -1 : smallestIndex;
    // }




    


    //GAME OF LIFE
    public void gameOfLife(int[][] board) {

        // Neighbors array to find 8 neighboring cells for a given cell
        int[] neighbors = {0, 1, -1};

        int rows = board.length;
        int cols = board[0].length;

        // Create a copy of the original board
        int[][] copyBoard = new int[rows][cols];

        // Create a copy of the original board
        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < cols; col++) {
                copyBoard[row][col] = board[row][col];
            }
        }

        // Iterate through board cell by cell.
        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < cols; col++) {

                // For each cell count the number of live neighbors.
                int liveNeighbors = 0;

                for (int i = 0; i < 3; i++) {
                    for (int j = 0; j < 3; j++) {

                        if (!(neighbors[i] == 0 && neighbors[j] == 0)) {
                            int r = (row + neighbors[i]);
                            int c = (col + neighbors[j]);

                            // Check the validity of the neighboring cell.
                            // and whether it was originally a live cell.
                            // The evaluation is done against the copy, since that is never updated.
                            if ((r < rows && r >= 0) && (c < cols && c >= 0) && (copyBoard[r][c] == 1)) {
                                liveNeighbors += 1;
                            }
                        }
                    }
                }

                // Rule 1 or Rule 3
                if ((copyBoard[row][col] == 1) && (liveNeighbors < 2 || liveNeighbors > 3)) {
                    board[row][col] = 0;
                }
                // Rule 4
                if (copyBoard[row][col] == 0 && liveNeighbors == 3) {
                    board[row][col] = 1;
                }
            }
        }
    }  


    //3SUM CLOSEST  
    public int threeSumClosest(int[] nums, int target) {
        int diff = Integer.MAX_VALUE, sz = nums.length;
        Arrays.sort(nums);
        for (int i = 0; i < sz && diff != 0; ++i) {
            int lo = i + 1, hi = sz - 1;
            while (lo < hi) {
                int sum = nums[i] + nums[lo] + nums[hi];
                if (Math.abs(target - sum) < Math.abs(diff))
                    diff = target - sum;
                if (sum < target)
                    ++lo;
                else
                    --hi;
            }
        }
        return target - diff;
    }


    //TASK SCHEDULER
    public int leastInterval(char[] tasks, int n) {
        // frequencies of the tasks
        int[] frequencies = new int[26];
        for (int t : tasks) {
            frequencies[t - 'A']++;
        }

        Arrays.sort(frequencies);

        // max frequency
        int f_max = frequencies[25];
        int idle_time = (f_max - 1) * n;
        
        for (int i = frequencies.length - 2; i >= 0 && idle_time > 0; --i) {
            idle_time -= Math.min(f_max - 1, frequencies[i]); 
        }
        idle_time = Math.max(0, idle_time);

        return idle_time + tasks.length;
    }


}
