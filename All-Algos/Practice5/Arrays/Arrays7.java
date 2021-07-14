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
    
}
