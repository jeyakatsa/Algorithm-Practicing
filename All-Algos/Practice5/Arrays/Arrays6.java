import java.util.*;

public class Arrays6 {

  //Gas Station
  public int canCompleteCircuit(int[] gas, int[] cost) {
      int n = gas.length;
  
      int total_tank = 0;
      int curr_tank = 0;
      int starting_station = 0;
      for (int i = 0; i < n; ++i) {
        total_tank += gas[i] - cost[i];
        curr_tank += gas[i] - cost[i];
        // If one couldn't get here,
        if (curr_tank < 0) {
          // Pick up the next station as the starting one.
          starting_station = i + 1;
          // Start with an empty tank.
          curr_tank = 0;
        }
      }
      return total_tank >= 0 ? starting_station : -1;
  }   


  //Permutations... 
  public void backtrack(int n,
                        ArrayList<Integer> nums,
                        List<List<Integer>> output,
                        int first) {
    // if all integers are used up
    if (first == n)
    output.add(new ArrayList<Integer>(nums));
    for (int i = first; i < n; i++) {
    // place i-th integer first 
    // in the current permutation
    Collections.swap(nums, first, i);
    // use next integers to complete the permutations
    backtrack(n, nums, output, first + 1);
    // backtrack
    Collections.swap(nums, first, i);
    }
  }
  public List<List<Integer>> permute(int[] nums) {
      // init output list
      List<List<Integer>> output = new LinkedList<>();

      // convert nums into list since the output is a list of lists
      ArrayList<Integer> nums_lst = new ArrayList<Integer>();
      for (int num : nums)
      nums_lst.add(num);

      int n = nums.length;
      backtrack(n, nums_lst, output, 0);
      return output;
  }   
  
  
  //Interval List Intersections...
  public int[][] intervalIntersection(int[][] A, int[][] B) {
      List<int[]> ans = new ArrayList<>();
      int i = 0, j = 0;
  
      while (i < A.length && j < B.length) {
        // Let's check if A[i] intersects B[j].
        // lo - the startpoint of the intersection
        // hi - the endpoint of the intersection
        int lo = Math.max(A[i][0], B[j][0]);
        int hi = Math.min(A[i][1], B[j][1]);
        if (lo <= hi)
          ans.add(new int[]{lo, hi});
  
        // Remove the interval with the smallest endpoint
        if (A[i][1] < B[j][1])
          i++;
        else
          j++;
      }
  
      return ans.toArray(new int[ans.size()][]);
  }
  
  
  //Sign of the Product in Array
  public int arraySign(int[] nums) {
      int sign = 1;
      for (int i : nums) {
          if (i == 0) return 0;
          if (i < 0) sign *= -1;
      }
      return sign;
  }

  
  
  //Minimum Moves Equal Array
  public int minMoves(int[] nums) {
      Arrays.sort(nums);
      int count = 0;
      for (int i = nums.length - 1; i > 0; i--) {
          count += nums[i] - nums[0];
      }
      return count;
  } 
  
  
  //Valid Sudoku
  public boolean isValidSudoku(char[][] board) {
    Set seen = new HashSet<>();
    for (int i=0; i<9; ++i) {
      for (int j=0; j<9; ++j) {
        char number = board[i][j];
        if (number != '.')
          if (!seen.add(number + " in row " + i) ||
            !seen.add(number + " in column " + j) ||
            !seen.add(number + " in block " + i/3 + "-" + j/3))
            return false;
      }
    }
    return true;
  }    


  //Longest Consecutive Sequence
  public int longestConsecutive(int[] nums) {
    Set<Integer> num_set = new HashSet<Integer>();
    for (int num : nums) {
      num_set.add(num);
    }

    int longestStreak = 0;

    for (int num : num_set) {
      if (!num_set.contains(num-1)) {
        int currentNum = num;
        int currentStreak = 1;

        while (num_set.contains(currentNum+1)) {
          currentNum += 1;
          currentStreak += 1;
        }

        longestStreak = Math.max(longestStreak, currentStreak);
      }
    }

    return longestStreak;
  }    


  //Design Leaderboard
  class Leaderboard {

    private HashMap<Integer, Integer> scores;
    
    public Leaderboard() {
        this.scores = new HashMap<Integer, Integer>();
    }
    
    public void addScore(int playerId, int score) {
        
        if (!this.scores.containsKey(playerId)) {
            this.scores.put(playerId, 0);
        }
        
        this.scores.put(playerId, this.scores.get(playerId) + score);
    }
    
    public int top(int K) {
        
        List<Integer> values = new ArrayList<Integer>(this.scores.values());
        Collections.sort(values, Collections.reverseOrder());
        
        int total = 0;
        for (int i = 0; i < K; i++) {
            total += values.get(i);            
        }
        
        return total;
    }
    
    public void reset(int playerId) {
        this.scores.put(playerId, 0);
    }
  }


  //Partition Labels
  public List<Integer> partitionLabels(String S) {
    int[] last = new int[26];
    for (int i = 0; i < S.length(); ++i)
        last[S.charAt(i) - 'a'] = i;
    
    int j = 0, anchor = 0;
    List<Integer> ans = new ArrayList<>();
    for (int i = 0; i < S.length(); ++i) {
        j = Math.max(j, last[S.charAt(i) - 'a']);
        if (i == j) {
            ans.add(i - anchor + 1);
            anchor = i + 1;
        }
    }
    return ans;
  }  








    
}
