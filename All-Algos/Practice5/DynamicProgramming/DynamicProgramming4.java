import java.util.*;

public class DynamicProgramming4 {

    //Generate Parenthesis
    public List<String> generateParenthesis(int n) {
        List<String> combinations = new ArrayList<>();
        generateAll(new char[2 * n], 0, combinations);
        return combinations;
    }
    public void generateAll(char[] current, int pos, List<String> result) {
        if (pos == current.length) {
            if (valid(current))
                result.add(new String(current));
        } else {
            current[pos] = '(';
            generateAll(current, pos+1, result);
            current[pos] = ')';
            generateAll(current, pos+1, result);
        }
    }
    public boolean valid(char[] current) {
        int balance = 0;
        for (char c: current) {
            if (c == '(') balance++;
            else balance--;
            if (balance < 0) return false;
        }
        return (balance == 0);
    }


    //Minimum Deletion Cost to Avoid Repeating Letters
    public int minCost(String s, int[] cost) {
        int res = 0, max_cost = 0, sum_cost = 0, n = s.length();
        for (int i = 0; i < n; ++i) {
            if (i > 0 && s.charAt(i) != s.charAt(i - 1)) {
                res += sum_cost - max_cost;
                sum_cost = max_cost = 0;
            }
            sum_cost += cost[i];
            max_cost = Math.max(max_cost, cost[i]);
        }
        res += sum_cost - max_cost;
        return res;  
    }


    //Coin Change 2
    public int change(int amount, int[] coins) {
        int[] dp = new int[amount + 1];
        dp[0] = 1;
    
        for (int coin : coins) {
          for (int x = coin; x < amount + 1; ++x) {
            dp[x] += dp[x - coin];
          }
        }
        return dp[amount];
    }
    

    //Jump Game II
    public int jump(int[] nums) {
        int jumps = 0, currentJumpEnd = 0, farthest = 0;
        for (int i = 0; i < nums.length - 1; i++) {
            // we continuously find the how far we can reach in the current jump
            farthest = Math.max(farthest, i + nums[i]);
            // if we have come to the end of the current jump,
            // we need to make another jump
            if (i == currentJumpEnd) {
                jumps++;
                currentJumpEnd = farthest;
            }
        }
        return jumps;
    }


    //Count Number of Teams
    public int numTeams(int[] rating) {
        int res = 0;
        for (int i = 1; i < rating.length - 1; ++i) {
            int less[] = new int[2], greater[] = new int[2];
            for (int j = 0; j < rating.length; ++j) {
                if (rating[i] < rating[j])
                    ++less[j > i ? 1 : 0];
                if (rating[i] > rating[j])
                    ++greater[j > i ? 1 : 0];
            }
            res += less[0] * greater[1] + greater[0] * less[1];
        }
        return res;
    }  









    
}
