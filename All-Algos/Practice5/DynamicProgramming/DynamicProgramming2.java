import java.util.*;

public class DynamicProgramming2 {


    //CLIMBING STAIRS
    public int climbStairs(int n) {
        return climb_Stairs(0, n);
    }
    public int climb_Stairs(int i, int n) {
        if (i > n) {
            return 0;
        }
        if (i == n) {
            return 1;
        }
        return climb_Stairs(i + 1, n) + climb_Stairs(i + 2, n);
    }


    //IS SUBSEQUENCE OF STRINGS ("s = abc" - "t = abhhdc")
    public boolean isSubsequence(String s, String t) {
        Integer leftBound = s.length(), rightBound = t.length();
        Integer pLeft = 0, pRight = 0;

        while (pLeft < leftBound && pRight < rightBound) {
            // move both pointers or just the right pointer
            if (s.charAt(pLeft) == t.charAt(pRight)) {
                pLeft += 1;
            }
            pRight += 1;
        }
        return pLeft == leftBound;
    }

    
    //DELETE AND EARN (I don't understand this problem...)
    public int deleteAndEarn(int[] nums) {
        int[] count = new int[10001];
        for (int x: nums) count[x]++;
        int avoid = 0, using = 0, prev = -1;

        for (int k = 0; k <= 10000; ++k) if (count[k] > 0) {
            int m = Math.max(avoid, using);
            if (k - 1 != prev) {
                using = k * count[k] + m;
                avoid = m;
            } else {
                using = k * count[k] + avoid;
                avoid = m;
            }
            prev = k;
        }
        return Math.max(avoid, using);
    }   
    

    
    //MAXIMUM PRODUCT SUBARRAY O(n^2) Space Time
    public int maxProduct(int[] nums) {
        if (nums.length == 0) return 0;

        int result = nums[0];

        for (int i = 0; i < nums.length; i++) {
            int accu = 1;
            for (int j = i; j < nums.length; j++) {
                accu *= nums[j];
                result = Math.max(result, accu);
            }
        }

        return result;
    }


    //COUNT PALINDROMIC SUBSTRINGS
    public int countSubstrings(String s) {
        
        //base case for if String doesn't exist...
        //will need syntax to convert Strings into integers
        //separate 1 substring into a "palindrome"
        //if substring > 1, separate substrings into all adjacent numbers
        //Now how to solve this? (the syntax needed... You've got me...)
        int n = s.length(), ans = 0;

        if (n <= 0) 
            return 0;

        boolean[][] dp = new boolean[n][n];

        // Base case: single letter substrings
        for (int i = 0; i < n; ++i, ++ans) 
            dp[i][i] = true;

        // Base case: double letter substrings
        for (int i = 0; i < n - 1; ++i) {
            dp[i][i + 1] = (s.charAt(i) == s.charAt(i + 1));
            ans += (dp[i][i + 1] ? 1 : 0);
        }

        // All other cases: substrings of length 3 to n
        for (int len = 3; len <= n; ++len)
            for (int i = 0, j = i + len - 1; j < n; ++i, ++j) {
                dp[i][j] = dp[i + 1][j - 1] && (s.charAt(i) == s.charAt(j));
                ans += (dp[i][j] ? 1 : 0);
            }

        return ans;
    }



    //DIVISOR GAME
    public boolean divisorGame(int n) {
        if (n % 2 == 0) {
            return true;    
        }
        return false;
    }   
    
    
    //MAXUMUM LENGTH OF REPEATED SUBARRAY'
    public int findLength(int[] A, int[] B) {
        int ans = 0;
        Map<Integer, ArrayList<Integer>> Bstarts = new HashMap<>();
        for (int j = 0; j < B.length; j++) {
            Bstarts.computeIfAbsent(B[j], x -> new ArrayList<>()).add(j);
        }

        for (int i = 0; i < A.length; i++) if (Bstarts.containsKey(A[i])) {
            for (int j: Bstarts.get(A[i])) {
                int k = 0;
                while (i+k < A.length && j+k < B.length && A[i+k] == B[j+k]) {
                    k++;
                }
                ans = Math.max(ans, k);
            }
        }
        return ans;
    }  






}
