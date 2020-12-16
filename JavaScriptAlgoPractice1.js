//merge arrays of num1 & num 2
var nums1 = [1,2,3,4,5];
var nums2 = [6,7,8,9,10];



var merge = function (nums1, m, nums2, n) {

    let a = m - 1, b = n - 1, c = m + n - 1;
    while (a >= 0 && b >= 0) {

        if (nums1[a] > nums2[b]) {
            nums1[c--] = nums1[a--];
        }
        else {
            nums1[c--] = nums2[b--];
        }
    }

    while (a >= 0) {
        nums1[c--] = nums1[a--];
    }

    while (b >= 0) {

        nums1[c--] = nums2[b--];
    }
};





//returning running sum of Array
var nums = [1,2,3,4]; 
var runningSum = function(nums) {
    for(let i=1;i<nums.length;i++){
        nums[i]+=nums[i-1]
    }
    return nums
};





//Given an array of integers nums and an integer k,
//return the number of unique k-diff pairs in the array.
var nums = [1,2,3,4,5];
var k = 1;
// nums   K-diff
// 1 -- 1 + 1 = 2
// 2 -- 2 + 1 = 3
// 3 -- 3 + 1 = 4
// 4 -- 4 + 1 = 5
//so Output should be 4... So on and so forth...
var findPairs = function(nums, k) {
    nums.sort((a,b) => (a - b))
    let result = 0
    for(let i = 0; i < nums.length; i++){
        if(i > 0 && nums[i] === nums[i - 1])
            continue
        for(let j = i + 1; j < nums.length; j++){
            let diff = nums[j] - nums[i]
            if(diff === k){
                result += 1
                break
            }
            if(diff > k) break
        }
    }
    return result
};




//Given an array of integers nums and an integer target, 
//return indices of the two numbers such that they add up to target.
var twoSum = function(nums, target) {
    const map = new Map()
    for(let i = 0; i<nums.length; i++){
        const complement = target - nums[i]
        if(complement in map){
            return [map[complement],i]
        }
        map[nums[i]] = i
    }
};




//Given an integer array nums, find the contiguous subarray 
//(containing at least one number) which has the largest 
//sum and return its sum.
var nums = [-1,2,-3,4,5];
var maxSubArray = function(nums) {
  var prev = 0;
  var max = -Number.MAX_VALUE;

  for (var i = 0; i < nums.length; i++) {
    prev = Math.max(prev + nums[i], nums[i]);
    max = Math.max(max, prev);
  }
  return max;
}




//Given an array nums of n integers where n > 1, 
//return an array output such that output[i] is equal to the 
//product of all the elements of nums except nums[i].
var productExceptSelf = function(nums) {
    var output = [];
    var leftMult = 1;
    var rightMult = 1;
    for (var i=nums.length - 1; i >= 0; i--) {
        output[i] = rightMult;
        rightMult *= nums[i];
    }
    for (var j=0; j < nums.length; j++) {
        output[j] *= leftMult;
        leftMult *= nums[j];
    }
    return output;
};




//91. Decode Ways
//Given a non-empty string containing only digits, determine 
//the total number of ways to decode it.
function numDecodings(s) {
    if (s == null || s.length === 0) return 0;
    if (s[0] === '0') return 0;
  
    const dp = new Array(s.length + 1).fill(0);
  
    dp[0] = 1;
    dp[1] = 1;
  
    for (let i = 2; i <= s.length; i++) {
      const a = Number(s.slice(i - 1, i));  // last one digit
      if (a >= 1 && a <= 9) {
        dp[i] += dp[i - 1];
      }
  
      const b = Number(s.slice(i - 2, i));  // last two digits
      if (b >= 10 && b <= 26) {
        dp[i] += dp[i - 2];
      }
    }
  
    return dp[s.length];
  }




  //5. Longest palindrome.
  var longestPalindrome = function(s) {
    var max = '';
    for (var i = 0; i < s.length; i++) {
      for (var j = 0; j < 2; j++) {
        var left = i;
        var right = i + j;
        while (s[left] && s[left] === s[right]) {
          left--;
          right++;
        }
        if ((right - left - 1) > max.length) {
          max = s.substring(left + 1, right);
        }
      }
    }
    return max;
  };




  //121. Best time to buy stock
  var maxProfit = function(prices) {
    let min = prices[0], max = 0;
    
    for(let i = 1; i < prices.length; i++){
        if(prices[i] - min > max){
            max = prices[i] - min;
        }
        
        if(prices[i] < min) min = prices[i]
    }
    
    return max;
};




//221. Maximal Square
function maximalSquare(matrix) {
  // max square we've seen
  let max = 0;

  // create empty dp array matching matrix size
  const dp = [
      ...Array(matrix.length),
  ].map((e) => Array(matrix[0].length));

  // iterate i by j thru matrix
  for (let i = 0; i < matrix.length; i++) {
      for (let j = 0; j < matrix[0].length; j++) {
          // check if this is first row, or col
          if (i === 0 || j === 0) {
              // check if it's a 1 to update max square to 1
              if (matrix[i][j] === '1') {
                  dp[i][j] = 1;
                  max = Math.max(max, 1);
              }
              else {
                  // just write a 0 to dp
                  dp[i][j] = 0;
              }
          } else {
              // check if 1
              if (matrix[i][j] === '1') {
                  // number at this dp cell, is min of left, top, top left + 1
                  // this ensures, will only be a square if all of those are 1s
                  dp[i][j] =
                      Math.min(dp[i][j - 1], dp[i - 1][j], dp[i - 1][j - 1]) + 1;
                  // update max if necessary
                  max = Math.max(max, dp[i][j]);
              }
              else {
                  // just write a 0 to dp
                  dp[i][j] = 0;
              }
          }
      }
  }

  // return max squared (cuz its a square :))
  return max ** 2;



}//139. Word Break
var wordBreak = function(s, wordDict) {
    const words = new Set(wordDict);
    const wordLens = new Set(wordDict.map((word) => word.length))
    const starts = new Set([0])
    for (let start of starts) {
        for (let len of wordLens) {
            if (words.has(s.slice(start, start + len))) {
                starts.add(start + len)
            }
        }
    }
    return starts.has(s.length)
};
