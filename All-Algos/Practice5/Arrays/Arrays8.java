import java.util.*;

public class Arrays8 {

    //Buildings With an Ocean View
    public int[] findBuildings(int[] heights) {
        List<Integer> temp = new ArrayList<>();
        int nextBiggest = 0;
        int j = heights.length - 1;
        while (j >= 0) {
            if (heights[j] > nextBiggest) {
                nextBiggest = heights[j];
                temp.add(j);
            }
            j--;
        }
        int[] answer = new int[temp.size()];
        int index = 0;
        for(int i = temp.size() - 1; i >= 0; i--) {
            answer[index] = temp.get(i);
            index++;
        }
        return answer;
        
    }

    //Maximum Number of Events That Can Be Attended
    public int maxEvents(int[][] A) {
        PriorityQueue<Integer> pq = new PriorityQueue<Integer>();
        Arrays.sort(A, (a, b) -> Integer.compare(a[0], b[0]));
        int i = 0, res = 0, n = A.length;
        for (int d = 1; d <= 100000; ++d) {
            while (!pq.isEmpty() && pq.peek() < d)
                pq.poll();
            while (i < n && A[i][0] == d)
                pq.offer(A[i++][1]);
            if (!pq.isEmpty()) {
                pq.poll();
                ++res;
            }
        }
        return res;
    } 



    //Candy Crush
    public int[][] candyCrush(int[][] board) {
        int R = board.length, C = board[0].length;
        boolean todo = false;
        for (int r = 0; r < R; ++r) {
            for (int c = 0; c + 2 < C; ++c) {
                int v = Math.abs(board[r][c]);
                if (v != 0 && v == Math.abs(board[r][c+1]) && v == Math.abs(board[r][c+2])) {
                    board[r][c] = board[r][c+1] = board[r][c+2] = -v;
                    todo = true;
                }
            }
        }
        for (int r = 0; r + 2 < R; ++r) {
            for (int c = 0; c < C; ++c) {
                int v = Math.abs(board[r][c]);
                if (v != 0 && v == Math.abs(board[r+1][c]) && v == Math.abs(board[r+2][c])) {
                    board[r][c] = board[r+1][c] = board[r+2][c] = -v;
                    todo = true;
                }
            }
        }

        for (int c = 0; c < C; ++c) {
            int wr = R - 1;
            for (int r = R-1; r >= 0; --r)
                if (board[r][c] > 0)
                    board[wr--][c] = board[r][c];
            while (wr >= 0)
                board[wr--][c] = 0;
        }

        return todo ? candyCrush(board) : board;
    }



    
}
