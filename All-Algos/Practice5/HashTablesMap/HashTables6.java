import java.util.*;

public class HashTables6 {

    //Cinema Seat Allocation
    public int maxNumberOfFamilies(int n, int[][] reservedSeats) {
        // Just code for rules, no need to merge intervals
		// Make sure you seat 2 families for rows with no reservations
        // Greedy approach
        // First possibility: Split seating at  [2, 3     4, 5]
        // Second possibility: Split seating at [6, 7     8, 9]
        // Only possibility:  Center four seats [  4, 5, 6, 7 ]
        
        int result = 0;
        Map<Integer, List<Integer>> rowToSeatRes = new HashMap<>();
        
        for(int[] row : reservedSeats) {
            if(rowToSeatRes.containsKey(row[0])) {
                rowToSeatRes.get(row[0]).add(row[1]);
            } else {
                rowToSeatRes.put(row[0], new ArrayList<Integer>(Arrays.asList(row[1])));
            }
        }
		
        // System.out.println(rowToSeatRes);
        result = (n - rowToSeatRes.size()) * 2;         // These rows do not contain any reservations
        
       for(List<Integer> res : rowToSeatRes.values()) { // Check possible family seating in each row 
            boolean flag = false;
            
			// Check first possibility
            if(!res.contains(2) &&
              !res.contains(3) &&
              !res.contains(4) &&
              !res.contains(5)) {
                result++;
                flag = true;
            }
            
            // Check second possibility
            if(!res.contains(6) &&
              !res.contains(7) &&
              !res.contains(8) &&
              !res.contains(9)) {
                result++;
                flag = true;
            }
            
            // Check middle seats only if first two are not used
            if(!flag) {
                if(!res.contains(4) &&
                   !res.contains(5) &&
                   !res.contains(6) &&
                   !res.contains(7))
                result++;
            }
        }
        
        return result;
    }   



    //Add Bold Tag In String
    public String addBoldTag(String s, String[] dict) {
        int n = s.length();
        int[] mark = new int[n+1];
        for(String d : dict) {
            int i = -1;
            while((i = s.indexOf(d, i+1)) >= 0) {
                mark[i]++;
                mark[i + d.length()]--;
            }
        }
        StringBuilder sb = new StringBuilder();
        int sum = 0;
        for(int i = 0; i <= n; i++) {
            int cur = sum + mark[i];
            if (cur > 0 && sum == 0) sb.append("<b>");
            if (cur == 0 && sum > 0) sb.append("</b>");
            if (i == n) break;
            sb.append(s.charAt(i));
            sum = cur;
        }
        return sb.toString();
    }


    //Number of Distinct Islands
    private List<List<int[]>> uniqueIslands = new ArrayList<>(); // All known unique islands.
    private List<int[]> currentIsland = new ArrayList<>(); // Current Island
    private int[][] grid; // Input grid
    private boolean[][] seen; // Cells that have been explored. 
     
    public int numDistinctIslands(int[][] grid) {   
        this.grid = grid;
        this.seen = new boolean[grid.length][grid[0].length];   
        for (int row = 0; row < grid.length; row++) {
            for (int col = 0; col < grid[0].length; col++) {
                dfs(row, col);
                if (currentIsland.isEmpty()) {
                    continue;
                }
                // Translate the island we just found to the top left.
                int minCol = grid[0].length - 1;
                for (int i = 0; i < currentIsland.size(); i++) {
                    minCol = Math.min(minCol, currentIsland.get(i)[1]);
                }
                for (int[] cell : currentIsland) {
                    cell[0] -= row;
                    cell[1] -= minCol;
                }
                // If this island is unique, add it to the list.
                if (currentIslandUnique()) {
                    uniqueIslands.add(currentIsland);
                }
                currentIsland = new ArrayList<>();
            }
        }
        return uniqueIslands.size();
    }
    
    private void dfs(int row, int col) {
        if (row < 0 || col < 0 || row >= grid.length || col >= grid[0].length) return;
        if (seen[row][col] || grid[row][col] == 0) return;
        seen[row][col] = true;
        currentIsland.add(new int[]{row, col});
        dfs(row + 1, col);
        dfs(row - 1, col);
        dfs(row, col + 1);
        dfs(row, col - 1);
    }
    
    private boolean currentIslandUnique() {
        for (List<int[]> otherIsland : uniqueIslands) {
            if (currentIsland.size() != otherIsland.size()) continue;
            if (equalIslands(currentIsland, otherIsland)) {
                return false;
            }
        }
        return true;
    }
    
    private boolean equalIslands(List<int[]> island1, List<int[]> island2) {
        for (int i = 0; i < island1.size(); i++) {
            if (island1.get(i)[0] != island2.get(i)[0] || island1.get(i)[1] != island2.get(i)[1]) {
                return false;
            }
        }
        return true;
    }    

    
    
}
