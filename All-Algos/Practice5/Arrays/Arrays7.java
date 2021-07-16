import java.util.*;

public class Arrays7 {

    //Asteroid Collision
    public int[] asteroidCollision(int[] a) {
        Stack<Integer> stack = new Stack<>();
        for (int i = 0; i < a.length; i++) {
            if (stack.isEmpty() || a[i] > 0) {
                stack.push(a[i]);
                continue;
            }
            
            while (true) {
                int prev = stack.peek();
                if (prev < 0) {
                    stack.push(a[i]);
                    break;
                }
                if (prev == -a[i]) {
                    stack.pop();
                    break;
                }
                if (prev > -a[i]) {
                    break;
                }
                stack.pop();
                if (stack.isEmpty()) {
                    stack.push(a[i]);
                    break;
                }
            }
        }
        
        int[] res = new int[stack.size()];
        for (int i = stack.size() - 1; i >= 0; i--) {
            res[i] = stack.pop();
        }
        
        return res;
    }

    //Minimum Difference Between Largest and Smallest Value in Three Moves
    public int minDifference(int[] A) {
        int n = A.length, res = Integer.MAX_VALUE;
        if (n < 5) return 0;
        Arrays.sort(A);
        for (int i = 0; i < 4; ++i) {
            res = Math.min(res, A[n - 4 + i] - A[i]);
        }
        return res;
    }
    
    
    //Snakes and Ladders
    public int snakesAndLadders(int[][] board) {
        if (board == null || board.length == 0 || board[0].length == 0) {
            return -1;
        }
        int rows = board.length;
        int cols = board[0].length;
        int dest = rows * cols;
        Queue<Integer> queue = new LinkedList<>();
        queue.offer(1);
        Set<Integer> set = new HashSet<>();
        set.add(1);
        int steps = 0;
        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                int curr = queue.poll();
                if (curr == dest) {
                    return steps;
                }
                for (int diff = 1; diff <= 6 && curr + diff <= dest; diff++) {
                    int[] pos = getCoordinate(curr + diff, rows, cols);
                    int next = board[pos[0]][pos[1]] == -1 ? curr + diff : board[pos[0]][pos[1]];
                    if (!set.contains(next)) {
                        queue.offer(next);
                        set.add(next);
                    }
                }
            }
            steps++;
        }
        return -1;
    }
    
    public int[] getCoordinate(int n, int rows, int cols) {
        int r = rows - 1 - (n - 1) / cols;
        int c = (n - 1) % cols;
        if (r % 2 == rows % 2) {
            return new int[]{r, cols - 1 - c};
        } else {
            return new int[]{r, c};
        }
    }


    //Maximum Units on a Truck
    int maximumUnits(int[][] boxTypes, int truckSize) {
        PriorityQueue<int[]> queue = new PriorityQueue<>((a, b)->b[1] - a[1]);
        queue.addAll(Arrays.asList(boxTypes));
        int unitCount = 0;
        while (!queue.isEmpty()) {
            int[] top = queue.poll();
            int boxCount = Math.min(truckSize, top[0]);
            unitCount += boxCount * top[1];
            truckSize -= boxCount;
            if(truckSize == 0)
                break;
        }
        return unitCount;
    }


    //Pacific Atlantic Water Flow
    private static final int[][] DIRECTIONS = new int[][]{{0, 1}, {1, 0}, {-1, 0}, {0, -1}};
    private int numRows;
    private int numCols;
    private int[][] landHeights;
    public List<List<Integer>> pacificAtlantic(int[][] matrix) {
        // Check if input is empty
        if (matrix.length == 0 || matrix[0].length == 0) {
            return new ArrayList<>();
        }

        // Save initial values to parameters
        numRows = matrix.length;
        numCols = matrix[0].length;
        landHeights = matrix;
        
        // Setup each queue with cells adjacent to their respective ocean
        Queue<int[]> pacificQueue = new LinkedList<>();
        Queue<int[]> atlanticQueue = new LinkedList<>();
        for (int i = 0; i < numRows; i++) {
            pacificQueue.offer(new int[]{i, 0});
            atlanticQueue.offer(new int[]{i, numCols - 1});
        }
        for (int i = 0; i < numCols; i++) {
            pacificQueue.offer(new int[]{0, i});
            atlanticQueue.offer(new int[]{numRows - 1, i});
        }
        
        // Perform a BFS for each ocean to find all cells accessible by each ocean
        boolean[][] pacificReachable = bfs(pacificQueue);
        boolean[][] atlanticReachable = bfs(atlanticQueue);
        
        // Find all cells that can reach both oceans
        List<List<Integer>> commonCells = new ArrayList<>();
        for (int i = 0; i < numRows; i++) {
            for (int j = 0; j < numCols; j++) {
                if (pacificReachable[i][j] && atlanticReachable[i][j]) {
                    commonCells.add(List.of(i, j));
                }
            }
        }
        return commonCells;
    }
    private boolean[][] bfs(Queue<int[]> queue) {
        boolean[][] reachable = new boolean[numRows][numCols];
        while (!queue.isEmpty()) {
            int[] cell = queue.poll();
            // This cell is reachable, so mark it
            reachable[cell[0]][cell[1]] = true;
            for (int[] dir : DIRECTIONS) { // Check all 4 directions
                int newRow = cell[0] + dir[0];
                int newCol = cell[1] + dir[1];
                // Check if new cell is within bounds
                if (newRow < 0 || newRow >= numRows || newCol < 0 || newCol >= numCols) {
                    continue;
                }
                // Check that the new cell hasn't already been visited
                if (reachable[newRow][newCol]) {
                    continue;
                }
                // Check that the new cell has a higher or equal height,
                // So that water can flow from the new cell to the old cell
                if (landHeights[newRow][newCol] < landHeights[cell[0]][cell[1]]) {
                    continue;
                }
                // If we've gotten this far, that means the new cell is reachable
                queue.offer(new int[]{newRow, newCol});
            }
        }
        return reachable;
    }    



    
}
