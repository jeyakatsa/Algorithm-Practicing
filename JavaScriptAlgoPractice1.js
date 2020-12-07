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
