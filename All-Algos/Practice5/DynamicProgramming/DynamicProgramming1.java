import java.util.*;

public class DynamicProgramming1 {
    public static void main(String[] args) {
        String string = "leetcode";

    }

    //MAXIMUM SUBARRAY
    public static int maxSubarray(int[] nums) {
        int currMax=nums[0],max=nums[0];
        for(int i=1;i<nums.length;i++){
            currMax=Math.max(currMax+nums[i],nums[i]);
            max=Math.max(max,currMax);
        }
        return max;
    }   

    //LONGEST PALINDROMIC STRING
    public static String longestPalindrome(String s) {
        if (s == null || s.length() < 1) return "";
        int start = 0, end = 0;
        for (int i = 0; i < s.length(); i++) {
            int len1 = expandAroundCenter(s, i, i);
            int len2 = expandAroundCenter(s, i, i + 1);
            int len = Math.max(len1, len2);
            if (len > end - start) {
                start = i - (len - 1) / 2;
                end = i + len / 2;
            }
        }
        return s.substring(start, end + 1);
    }
    private static int expandAroundCenter(String s, int left, int right) {
        int L = left, R = right;
        while (L >= 0 && R < s.length() && s.charAt(L) == s.charAt(R)) {
            L--;
            R++;
        }
        return R - L - 1;
    }      
    
    
    //DECODE LETTERS TO NUMBERS
    // Input: s = "226"
    // Output: 3
    // Explanation: "226" could be decoded as "BZ" (2 26), "VF" (22 6), or "BBF" (2 2 6).
    public static int numDecodings(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }
        int n = s.length();
        int[] dp = new int[n + 1];
        dp[0] = 1;
        dp[1] = s.charAt(0) != '0' ? 1 : 0;
        for (int i = 2; i <= n; i++) {
            int first = Integer.valueOf(s.substring(i - 1, i));
            int second = Integer.valueOf(s.substring(i - 2, i));
            if (first >= 1 && first <= 9) {
               dp[i] += dp[i-1];  
            }
            if (second >= 10 && second <= 26) {
                dp[i] += dp[i-2];
            }
        }
        return dp[n];
    }
    
    //WORD BREAK
    public static boolean wordBreak(String s, List<String> wordDict) {
        // put all words into a hashset
        Set<String> set = new HashSet<>(wordDict);
        return wb(s, set);
    }
    private static boolean wb(String s, Set<String> set) {
        int len = s.length();
        if (len == 0) {
            return true;
        }
        for (int i = 1; i <= len; ++i) {
            if (set.contains(s.substring(0, i)) && wb(s.substring(i), set)) {
                return true;
            }
        }
        return false;
    }


    //MAXIMAL SQUARE
    //[0, 0, 1, 1, 1]
    //[0, 1, 1, 1, 0] = 4 else == 1;
    public int maximalSquare(char[][] matrix) {
        //Possible Brute Force Approach:
        //loop through array
        //if (matrix[i[i]] && matrix[i[i++]]) == 1 && (matrix[i++[i]] && matrix[i++[i++]]) == 1
        //return 4
        //else return 1
        
        //Correct Approach
        int rows = matrix.length, cols = rows > 0 ? matrix[0].length : 0;
        int[][] dp = new int[rows + 1][cols + 1];
        int maxsqlen = 0;
        for (int i = 1; i <= rows; i++) {
            for (int j = 1; j <= cols; j++) {
                if (matrix[i-1][j-1] == '1'){
                    dp[i][j] = Math.min(Math.min(dp[i][j - 1], dp[i - 1][j]), dp[i - 1][j - 1]) + 1;
                    maxsqlen = Math.max(maxsqlen, dp[i][j]);
                }
            }
        }
        return maxsqlen * maxsqlen;        
    }

    //COIN CHANGE Few Number of coins...
    // Input: coins = [1,2,5], amount = 11
    // Output: 3
    // Explanation: 11 = 5 + 5 + 1
    public int coinChange(int[] coins, int amount) {
        if (coins == null || coins.length == 0) {
            return -1;
        }
        int min = Integer.MAX_VALUE;
        int total = 1;
        int[] dp = new int[amount + 1];
        dp[0] = 0;
        while (total <= amount) {
            min = Integer.MAX_VALUE;
            for (int i = 0; i < coins.length; i++) {
                int diff = total - coins[i];
                if (diff > 0 && dp[diff] > 0 || diff == 0) {
                    min = Math.min(min, dp[diff] + 1);
                }
            }
            dp[total++] = (min == Integer.MAX_VALUE ? -1 : min);
        }
        return dp[amount];
        
    }    
}