import java.util.*;

public class HashTables5 {

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


  //Degree of an Array
  public int findShortestSubArray(int[] nums) {
    Map<Integer, Integer> left = new HashMap<>(),
        right = new HashMap<>(), count = new HashMap<>();

    for (int i = 0; i < nums.length; i++) {
        int x = nums[i];
        if (left.get(x) == null) left.put(x, i);
        right.put(x, i);
        count.put(x, count.getOrDefault(x, 0) + 1);
    }

    int ans = nums.length;
    int degree = Collections.max(count.values());
    for (int x: count.keySet()) {
        if (count.get(x) == degree) {
            ans = Math.min(ans, right.get(x) - left.get(x) + 1);
        }
    }
    return ans;
}  
    
}
