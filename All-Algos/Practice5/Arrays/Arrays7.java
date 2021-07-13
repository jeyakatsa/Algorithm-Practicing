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
    
}
