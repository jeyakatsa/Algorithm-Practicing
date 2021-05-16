public class DynamicProgramming1 {
    public static void main(String[] args) {

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
    public String longestPalindrome(String s) {
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
    private int expandAroundCenter(String s, int left, int right) {
        int L = left, R = right;
        while (L >= 0 && R < s.length() && s.charAt(L) == s.charAt(R)) {
            L--;
            R++;
        }
        return R - L - 1;
    }           
}