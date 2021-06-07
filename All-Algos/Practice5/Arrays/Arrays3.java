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



}
