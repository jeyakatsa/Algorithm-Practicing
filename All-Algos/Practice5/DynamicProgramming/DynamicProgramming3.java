import java.util.*;

public class DynamicProgramming3 {


    //LONGEST INCREASING SUBSEQUENCE
    public int lengthOfLIS(int[] nums) {
        int memo[][] = new int[nums.length + 1][nums.length];
        for (int[] l : memo) {
            Arrays.fill(l, -1);
        }
        return lengthofLIS(nums, -1, 0, memo);
    }
    public int lengthofLIS(int[] nums, int previndex, int curpos, int[][] memo) {
        if (curpos == nums.length) {
            return 0;
        }
        if (memo[previndex + 1][curpos] >= 0) {
            return memo[previndex + 1][curpos];
        }
        int taken = 0;
        if (previndex < 0 || nums[curpos] > nums[previndex]) {
            taken = 1 + lengthofLIS(nums, curpos, curpos + 1, memo);
        }

        int nottaken = lengthofLIS(nums, previndex, curpos + 1, memo);
        memo[previndex + 1][curpos] = Math.max(taken, nottaken);
        return memo[previndex + 1][curpos];
    }




    //KNIGHT DIALER (CHESS)
    public int knightDialer(int N) {
        int modulo = (int)Math.pow(10,9)+7;
        HashMap<Integer, int[]> map = new HashMap<>();
        map.put(0,new int[] {4,6}); //Knight at 0, can jump to 4 & 6 on keypad...
        map.put(1,new int[] {6,8});
        map.put(2,new int[] {7,9});
        map.put(3,new int[] {4,8});
        map.put(4,new int[] {0,9,3});
        map.put(5,new int[] {});
        map.put(6,new int[] {1,7,0});
        map.put(7,new int[] {2,6});
        map.put(8,new int[] {1,3});
        map.put(9,new int[] {2,4});
        
        int[][] dp = new int[N+1][10];
        Arrays.fill(dp[1],1);
        int dial = dp[0].length;
        
        for(int i=2;i<=N;i++){
            for(int j=0;j<dial;j++){
                for(int keyNum : map.get(j)){
                    dp[i][j] += dp[i-1][keyNum];
                    dp[i][j] %= modulo;
                }
            }
        }
        int count = 0;
        for(int c=0;c<dial;c++){
            count += dp[N][c];
            count %= modulo;
        }
        
        return count;
    }    
    
    


    //Count Square Submatrices with All Ones
    public int countSquares(int[][] matrix) {
        int count = 0;
        int numRow = matrix.length;
        int numCol = matrix[0].length;
        
        for (int i=0; i<numRow; ++i) {
            for (int j=0; j<numCol; ++j) {
                if (i != 0 && j != 0 && matrix[i][j] != 0) {
                    matrix[i][j] = Math.min(matrix[i-1][j], Math.min(matrix[i][j-1], matrix[i-1][j-1])) + 1;
                };
                count += matrix[i][j];
            }
        }
        return count;
    } 


    //Minimum Cost for Tickets
    int[] costs;
    Integer[] memo;
    Set<Integer> dayset;

    public int mincostTickets(int[] days, int[] costs) {
        this.costs = costs;
        memo = new Integer[366];
        dayset = new HashSet<>();
        for (int d: days) dayset.add(d);

        return dp(1);
    }

    public int dp(int i) {
        if (i > 365)
            return 0;
        if (memo[i] != null)
            return memo[i];

        int ans;
        if (dayset.contains(i)) {
            ans = Math.min(dp(i+1) + costs[0],
                               dp(i+7) + costs[1]);
            ans = Math.min(ans, dp(i+30) + costs[2]);
        } else {
            ans = dp(i+1);
        }

        memo[i] = ans;
        return ans;
    } 

    
    //Range Sum Query 2D - Immutable
    private int[][] data;

    public DynamicProgramming3(int[][] matrix) {
        data = matrix;
    }
    
    public int sumRegion(int row1, int col1, int row2, int col2) {
        int sum = 0;
        for (int r = row1; r <= row2; r++) {
            for (int c = col1; c <= col2; c++) {
                sum += data[r][c];
            }
        }
        return sum;
    }   


    //Unique Paths
    public int uniquePathsWithObstacles(int[][] obstacleGrid) {
        int R = obstacleGrid.length;
        int C = obstacleGrid[0].length;
        // If the starting cell has an obstacle, then simply return as there would be
        // no paths to the destination.
        if (obstacleGrid[0][0] == 1) {
            return 0;
        }
        // Number of ways of reaching the starting cell = 1.
        obstacleGrid[0][0] = 1;
        // Filling the values for the first column
        for (int i = 1; i < R; i++) {
            obstacleGrid[i][0] = (obstacleGrid[i][0] == 0 && obstacleGrid[i - 1][0] == 1) ? 1 : 0;
        }
        // Filling the values for the first row
        for (int i = 1; i < C; i++) {
            obstacleGrid[0][i] = (obstacleGrid[0][i] == 0 && obstacleGrid[0][i - 1] == 1) ? 1 : 0;
        }
        // Starting from cell(1,1) fill up the values
        // No. of ways of reaching cell[i][j] = cell[i - 1][j] + cell[i][j - 1]
        // i.e. From above and left.
        for (int i = 1; i < R; i++) {
            for (int j = 1; j < C; j++) {
                if (obstacleGrid[i][j] == 0) {
                    obstacleGrid[i][j] = obstacleGrid[i - 1][j] + obstacleGrid[i][j - 1];
                } else {
                    obstacleGrid[i][j] = 0;
                }
            }
        }
        // Return value stored in rightmost bottommost cell. That is the destination.
        return obstacleGrid[R - 1][C - 1];
    }   



    //Matrix Block Sum
    public int[][] matrixBlockSum(int[][] mat, int K) {
        int m = mat.length;
        int n = mat[0].length;
        
        int[][] ans = new int[m][n];
        
        int[][] t = new int[m + 1][n + 1];
        
        for (int i = 1; i < t.length; i++) {
            for (int j = 1; j < t[0].length; j++) {
                t[i][j] = t[i - 1][j] + t[i][j - 1] + mat[i - 1][j - 1] - t[i - 1][j - 1];
            }
        }
        
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                int r1 = Math.max(0, i - K) + 1;
                int c1 = Math.max(0, j - K) + 1;
                int r2 = Math.min(m, i + K + 1);
                int c2 = Math.min(n, j + K + 1);
                ans[i][j] = t[r2][c2] - t[r1 - 1][c2] - t[r2][c1 - 1] + t[r1 - 1][c1 - 1];
            }
        }
        
        return ans;
    }    
    





    
}
