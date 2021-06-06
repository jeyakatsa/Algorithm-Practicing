import java.util.*;

public class Arrays2 {



    // MAX AREA (Horizontal vs Vertical)
    // We will use long instead of int to prevent overflow
    public int maxArea(int h, int w, int[] horizontalCuts, int[] verticalCuts) {
        // Start by sorting the inputs
        Arrays.sort(horizontalCuts);
        Arrays.sort(verticalCuts);
        int n = horizontalCuts.length;
        int m = verticalCuts.length;
        
        // Consider the edges first
        long maxHeight = Math.max(horizontalCuts[0], h - horizontalCuts[n - 1]);
        for (int i = 1; i < n; i++) {
            // horizontalCuts[i] - horizontalCuts[i - 1] represents the distance between
            // two adjacent edges, and thus a possible height
            maxHeight = Math.max(maxHeight, horizontalCuts[i] - horizontalCuts[i - 1]);
        }
        
        // Consider the edges first
        long maxWidth = Math.max(verticalCuts[0], w - verticalCuts[m - 1]);
        for (int i = 1; i < m; i++){
            // verticalCuts[i] - verticalCuts[i - 1] represents the distance between
            // two adjacent edges, and thus a possible width
            maxWidth = Math.max(maxWidth, verticalCuts[i] - verticalCuts[i - 1]);
        }

        // Be careful of overflow, and don't forget the modulo!
        return (int) ((maxWidth * maxHeight) % (1e9 + 7));
    }



    //MOVE ZEROES TO END OF ARRAY
    public void moveZeroes(int[] nums) {
        if (nums == null || nums.length == 0) return;        

        int snowBallSize = 0; 
        for (int i=0;i<nums.length;i++){
	        if (nums[i]==0){
                snowBallSize++; 
            }
            else if (snowBallSize > 0) {
	            int t = nums[i];
	            nums[i]=0;
	            nums[i-snowBallSize]=t;
            }
        }
    }



    //FIBONACCI NUMBER
    public int fib(int N) {
        if (N <= 1) {
            return N;
        }
        if (N == 2) {
            return 1;
        }

        int current = 0;
        int prev1 = 1;
        int prev2 = 1;

        for (int i = 3; i <= N; i++) {
            current = prev1 + prev2;
            prev2 = prev1;
            prev1 = current;
        }
        return current;
    }




    //HIGH FIVE (Average of the top 5 grades of each student)
    private int K;
    public int[][] highFive(int[][] items) {
        this.K = 5;
        TreeMap<Integer, Queue<Integer>> allScores = new TreeMap<>();
        for (int[] item : items) {
            int id = item[0];
            int score = item[1];
            if (!allScores.containsKey(id))
                // max heap
                allScores.put(id, new PriorityQueue<>((a,b) -> b - a));
            // Add score to the max heap
            allScores.get(id).add(score);
        }
        List<int[]> solution = new ArrayList<>();
        for (int id : allScores.keySet()) {
            int sum = 0;
            // obtain the top k scores (k = 5)
            for (int i = 0; i < this.K; ++i)
                sum += allScores.get(id).poll();
            solution.add(new int[] {id, sum / this.K});
        }
        int[][] solutionArray = new int[solution.size()][];
        return solution.toArray(solutionArray);
    }
    
    
    //NEXT PERMUTATION
    public void nextPermutation(int[] nums) {
        int i = nums.length - 2;
        while (i >= 0 && nums[i + 1] <= nums[i]) {
            i--;
        }
        if (i >= 0) {
            int j = nums.length - 1;
            while (nums[j] <= nums[i]) {
                j--;
            }
            swap(nums, i, j);
        }
        reverse(nums, i + 1);
    }
    private void reverse(int[] nums, int start) {
        int i = start, j = nums.length - 1;
        while (i < j) {
            swap(nums, i, j);
            i++;
            j--;
        }
    }
    private void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }
    



    //BEST TIME TO BUY/SELL STOCK II
    public int maxProfit(int[] prices) {
        int maxprofit = 0;
        for (int i = 1; i < prices.length; i++) {
            if (prices[i] > prices[i - 1])
                maxprofit += prices[i] - prices[i - 1];
        }
        return maxprofit;
    }


    //PIVOT INDEX
    public int pivotIndex(int[] nums) {
        
        int leftSum = 0;
        int totalSum = 0;
        
        int i = 0;
        while(i < nums.length) {
            totalSum += nums[i];
            i++;
        }
        
        i = 0;
        while(i < nums.length) {
            if(totalSum - nums[i] == leftSum) {
                return i;
            } else {
                leftSum += nums[i];
                totalSum -= nums[i];
            }
            i++;
        }
        
        return -1;
    }



    //3 SUM
    public List<List<Integer>> threeSum(int[] nums) {
        Arrays.sort(nums);
        List<List<Integer>> res = new ArrayList<>();
        for (int i = 0; i < nums.length && nums[i] <= 0; ++i)
            if (i == 0 || nums[i - 1] != nums[i]) {
                twoSumII(nums, i, res);
            }
        return res;
    }
    void twoSumII(int[] nums, int i, List<List<Integer>> res) {
        int lo = i + 1, hi = nums.length - 1;
        while (lo < hi) {
            int sum = nums[i] + nums[lo] + nums[hi];
            if (sum < 0) {
                ++lo;
            } else if (sum > 0) {
                --hi;
            } else {
                res.add(Arrays.asList(nums[i], nums[lo++], nums[hi--]));
                while (lo < hi && nums[lo] == nums[lo - 1])
                    ++lo;
            }
        }
    }



    //TOP K FREQUENT WORDS [O N log N]
    public List<String> topKFrequent(String[] words, int k) {
        // Hande null data and edge cases
        if (words == null | words.length == 0) return new ArrayList<>();
        
        // Use a map to keep track of counts
        Map<String, Integer> map = new HashMap<>();
        // Use a list to keep track of words, to then sort them
        List<String> list = new ArrayList<>();
        
        for (String word : words) {
            // Count the # of times a words appears
            map.put(word, map.getOrDefault(word, 0) + 1);
            // Load only unique words to list
            if (!list.contains(word)) list.add(word);
        }
        
        Collections.sort(list, (String a, String b) -> {
                int aCount = map.get(a);
                int bCount = map.get(b);
            
                // If the counts are equal, then use String.compareTo to lexigraphically compare the strings
                if (aCount == bCount) {
                    return a.compareTo(b);
                } else {  // Else sort by greatest count
                    return bCount - aCount;
                }
            });
        
        // Return a list with only up to k elements
        return list.subList(0, k);
    }



}
