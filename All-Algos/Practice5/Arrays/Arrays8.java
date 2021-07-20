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
    
}
