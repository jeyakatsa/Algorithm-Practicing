import java.util.*;

public class Arrays9 {

    //Jump Game
    public boolean canJump(int[] nums) {
        int lastPos = nums.length - 1;
        for (int i = nums.length - 1; i >= 0; i--) {
            if (i + nums[i] >= lastPos) {
                lastPos = i;
            }
        }
        return lastPos == 0;
    }

    
    //Squares of a Sorted Array
    public int[] sortedSquares(int[] nums) {
        int[] result = new int[nums.length];
        for (int i = 0; i < nums.length; i++) {
            result[i] = nums[i] * nums[i];
        }
        Arrays.sort(result);        
        return result;
        
    }  

    //Shuffle the Array
    public int[] shuffle(int[] nums, int n) {
        int[] res = new int[2 * n];
        for (int i = 0, j = n, idx = 0; idx < res.length; i++, j++) {
            res[idx++] = nums[i];
            res[idx++] = nums[j];
        } 
        return res;
    }   
    
    
    //Shortest Bridge
    public int shortestBridge(int[][] A) {
        paint(A); //paint one island with int 2
        Queue<int[]> q = new LinkedList<>(); //queue contains coordinates to do bfs
        boolean[][] visited = new boolean[A.length][A[0].length];
        
        for(int i = 0; i < A.length; i ++){//initialize queue with all coordinates with number 2
            for(int j = 0; j < A[0].length; j ++){
                if(A[i][j] == 2){
                    q.add(new int[]{i,j});
                    visited[i][j] = true;
                }
            }
        }
        
        int level = 0;
        while(!q.isEmpty()){//level order bfs
            int size = q.size();
            for(int i = 0; i < size; i ++){
                int[] cur = q.poll();
                int x = cur[0];
                int y = cur[1];
                if(A[x][y] == 1){//found, then return
                    return level - 1;
                }
                if(x - 1 >= 0 && !visited[x - 1][y]){
                    q.add(new int[]{x - 1, y});
                    visited[x - 1][y] = true;
                }
                if(x + 1 < A.length && !visited[x + 1][y]){
                    q.add(new int[]{x + 1, y});
                    visited[x + 1][y] = true;
                }
                if(y - 1 >= 0 && !visited[x][y - 1]){
                    q.add(new int[]{x, y - 1});
                    visited[x][y - 1] = true;
                }
                if(y + 1 < A[0].length && !visited[x][y + 1]){
                    q.add(new int[]{x, y + 1});
                    visited[x][y + 1] = true;
                }
            }
            level ++; //next level
        }
        return -1;
    }
    
    public void paint(int[][] A){//paint one island with int 2
        for(int i = 0; i < A.length; i ++){
            for(int j = 0; j < A[0].length; j ++){
                if(A[i][j] == 1){
                    dfs(i, j, A);
                    return;
                }
            }
        }
    }
    
    public void dfs(int x, int y, int[][] A){//helper function for paint function
        if(x < 0 || x > A.length - 1 || y < 0 || y > A[0].length - 1 || A[x][y] != 1){
            return;
        }
        A[x][y] = 2;
        dfs(x - 1, y, A);
        dfs(x + 1, y, A);
        dfs(x, y - 1, A);
        dfs(x, y + 1, A);
    }


    //Sell Diminishing-Valued Colored Balls
    public int maxProfit(int[] inventory, int orders) {
        Arrays.sort(inventory);
        long ans = 0;
        int n = inventory.length-1;
        long count = 1;
        while(orders > 0){
            if(n > 0 && inventory[n] - inventory[n-1] > 0 && orders >= count * (inventory[n] - inventory[n-1])){
                ans += count * sumFromNtoX(inventory[n], inventory[n-1]);
                orders -= count * (inventory[n] - inventory[n-1]);
            }else if(n == 0 || inventory[n] - inventory[n-1] > 0){
                long a = orders / count;
                ans += count * sumFromNtoX(inventory[n], inventory[n]-a);
                long b = orders % count;
                ans += b * (inventory[n]-a);
                orders = 0;
            }
            ans %= 1000000007;
            n --;
            count ++;
        }
        return (int)ans;
    }
    
    private long sumFromNtoX(long n, long x){
        return (n * (n+1))/2 - (x * (x+1))/2;
    }  
  

    
}
