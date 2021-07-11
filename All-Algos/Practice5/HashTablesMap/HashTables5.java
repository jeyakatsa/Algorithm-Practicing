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
  
  
  //Happy Number
  private int getNext(int n) {
    int totalSum = 0;
    while (n > 0) {
        int d = n % 10;
        n = n / 10;
        totalSum += d * d;
    }
    return totalSum;
  }
  public boolean isHappy(int n) {
    Set<Integer> seen = new HashSet<>();
    while (n != 1 && !seen.contains(n)) {
        seen.add(n);
        n = getNext(n);
    }
    return n == 1;
  } 


  //Find All Duplicates in an Array
  public List<Integer> findDuplicates(int[] nums) {
    if(nums == null || nums.length == 0) {
        throw new IllegalArgumentException("Nope!");
    }
    List<Integer> result = new ArrayList<Integer>();
    Arrays.sort(nums);
    for(int i = 1; i < nums.length; i++) {
        if (nums[i] == nums[i-1]) {
            result.add(nums[i]);
            i++;
        }
    }
    return result;
    
  }


  //Subarray Sums Divisible by K
  public int subarraysDivByK(int[] A, int K) {
    Map<Integer, Integer> map = new HashMap<>();
    map.put(0, 1);
    int count = 0, sum = 0;
    for(int a : A) {
        sum = (sum + a) % K;
        if(sum < 0) sum += K;  // Because -1 % 5 = -1, but we need the positive mod 4
        count += map.getOrDefault(sum, 0);
        map.put(sum, map.getOrDefault(sum, 0) + 1);
    }
    return count;
  }



  // //Binary Tree Vertical Order Traversal...
  // public class TreeNode {
  //       int val;
  //       TreeNode left;
  //       TreeNode right;
  //       TreeNode() {}
  //       TreeNode(int val) { this.val = val; }
  //       TreeNode(int val, TreeNode left, TreeNode right) {
  //           this.val = val;
  //           this.left = left;
  //           this.right = right;
  //       }
  // }
  // public List<List<Integer>> verticalOrder(TreeNode root) {
  //   List<List<Integer>> output = new ArrayList<>();
  //   if (root == null) {
  //     return output;
  //   }

  //   Map<Integer, ArrayList> columnTable = new HashMap<>();
  //   Queue<Pair<TreeNode, Integer>> queue = new ArrayDeque<>();
  //   int column = 0;
  //   queue.offer(new Pair(root, column));

  //   while (!queue.isEmpty()) {
  //     Pair<TreeNode, Integer> p = queue.poll();
  //     root = p.getKey();
  //     column = p.getValue();

  //     if (root != null) {
  //       if (!columnTable.containsKey(column)) {
  //         columnTable.put(column, new ArrayList<Integer>());
  //       }
  //       columnTable.get(column).add(root.val);

  //       queue.offer(new Pair(root.left, column - 1));
  //       queue.offer(new Pair(root.right, column + 1));
  //     }
  //   }

  //   List<Integer> sortedKeys = new ArrayList<Integer>(columnTable.keySet());
  //   Collections.sort(sortedKeys);
  //   for(int k : sortedKeys) {
  //     output.add(columnTable.get(k));
  //   }

  //   return output;
  // }




    
}
