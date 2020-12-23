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



}


//139. Word Break
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


//322. Coin Change
var coinChange = function(coins, amount) {
    const dp = Array(amount+1).fill(Infinity);
    dp[0] = 0;
    
    for(let i = 1; i < dp.length; i++) {
        for(let coin of coins) {
            if(i-coin >= 0) dp[i] = Math.min(dp[i], dp[i-coin]+1);
        }
    }
    return dp[amount] === Infinity ? -1 : dp[amount];
};


//199. Binary Tree Right Side View
var rightSideView = function(root) {
    let depth = [];
    
    function inner(root, d) {
      if(!root) {
        return;
      }
      depth[d] = root.val;
      inner(root.left, d + 1);
      inner(root.right, d + 1);
    }
    inner(root, 0);
    return depth;
};



//236. Lowest Common Ancestor of a Binary Tree
  const lowestCommonAncestor = (root, p, q) => {
    if (!root || root === p || root === q) return root
    const left = lowestCommonAncestor(root.left, p, q)
    const right = lowestCommonAncestor(root.right, p, q)
    if (!left) return right  // p and q are in the right subtree
    if (!right) return left  // p and q are in the left subtree
    return root              // p is in one side and q is in the other
};


//105. Construct Binary Tree from Preorder and Inorder Traversal
var buildTree = function(preorder, inorder) {
    const map = new Map();
    let preIdx = 0;
    
    for(let i = 0; i < inorder.length; i++) {
        map.set(inorder[i], i)
    }
    
    function callDFS(start, end) {
        if(end < start) return null;
        
        const node = new TreeNode(preorder[preIdx]);
        const idx = map.get(preorder[preIdx]);
        preIdx++;
        
        node.left = callDFS(start, idx-1);
        node.right = callDFS(idx+1, end);
        
        return node;
    }
    return callDFS(0, inorder.length-1);
};


//987.	Vertical Order Traversal of a Binary Tree
var verticalTraversal = function(root) {
    const nodeInfos = []; // holds the x, y, & val information of each node traversed
    
    getNodeInfos(root, 0, 0);
	
	// sort by the following order of importance:
	//  1. x - coordinate
	//  2. y - coordinate precedence given to higher value
	//  3. node val in ascending order
	
    nodeInfos.sort((a, b) => a[0] - b[0] || b[1] - a[1] || a[2] - b[2]);
    
    const map = new Map();
    
    for (const [x, y, val] of nodeInfos) {
        if (!map.has(x)) map.set(x, []);
        map.get(x).push(val);
    }
    
    return [...map.values()];
    
    function getNodeInfos(node, x, y) {
        if (node) {
            getNodeInfos(node.left, x - 1, y - 1); // traverse left
			nodeInfos.push([x, y, node.val]);
            getNodeInfos(node.right, x + 1, y - 1); // traverse right
        }
    }
};

//103. Binary Tree Zigzag Level Order Traversal
var zigzagLevelOrder = function(root) {
    if(!root) return [];
    let queue = [root];
    let output = [];
    let deep = 0;
    while(queue.length > 0){
      const size = queue.length;
      const level = [];
      
      for(let i=0; i< size; i++){
        const node = queue.shift();
        if(deep % 2 == 0) level.push(node.val);
        else level.unshift(node.val);
        
        if(node.left) queue.push(node.left)
        if(node.right) queue.push(node.right)
      }
      output.push(level)
      deep++;
    }
    
    
    return output
    
    
};

//863. All Nodes Distance K in Binary Tree
  var distanceK = function(root, target, K) {
    if(!root) return []
    const node = findTarget(root, null, target)  
    const res = []
    findAllKApart(node, K, res)
    return res
 };
  
  function findTarget(root, parent, target){
    if(!root) return null
    root.parent = parent
    if(root === target){    
     return root 
    }    
    return findTarget(root.left, root, target) || findTarget(root.right, root, target)    
  }
  
  function findAllKApart(root, k, res){
    if(!root || root.visited) return res
    if(k == 0){
      res.push(root.val)
      return res
    }   
    root.visited = true
    findAllKApart(root.left, k-1, res)
    findAllKApart(root.right, k-1, res)
    findAllKApart(root.parent, k-1, res)
    return res
}



//2. Add Two Numbers
  var addTwoNumbers = function(l1, l2) {
    if(l1.val == undefined) return l2;
    else if(l2.val == undefined) return l1;
    
    let ret = new ListNode(0);
    let ptr = ret;
    let sum=0, tmp=0;
    
    while(l1!=null || l2!=null ||sum>0){
    	if(l1!=null) { sum+=l1.val; l1=l1.next; }
    	if(l2!=null) { sum+=l2.val; l2=l2.next; }
    	if(sum>9) { tmp=1; sum-=10; }
    	ptr.next = new ListNode(sum);
    	ptr=ptr.next;
    	sum=tmp;
    	tmp=0;
    }
    return ret.next;
};

//21. Merge Two Sorted Lists
var mergeTwoLists = function(l1, l2) {
    if(!l1 || !l2) return (l1? l1:l2);
    if(l1.val < l2.val) {
      l1.next = mergeTwoLists(l1.next, l2);
      return l1;
    } else {
      l2.next = mergeTwoLists(l1, l2.next);
      return l2;
    }
};

//206. Reverse Linked List
  var reverseList = function(head){
  
    var tmp = null;
    var newHead = null;
    while(head !== null){
      tmp = head;
      head = head.next;
      tmp.next = newHead;
      newHead = tmp;
    }
    
    return newHead;
}

//138. Copy List with Random Pointer
  var copyRandomList = function(head) {
    if(!head) {
      return null;
    }
    const clones = new Map();
    let n = head;
    while(n) {
      clones.set(n, new Node(n.val));
      n = n.next
    }
    n = head;
    while(n) {
      clones.get(n).next = clones.get(n.next) || null;
      clones.get(n).random = clones.get(n.random) || null;
      n = n.next
    }
    return clones.get(head);
};

//234. Palindrome Linked List
var isPalindrome = function(head) {
    let curr = head;
    const traverse = node => {
        if (node === null) {
            return true;
        }
        // traverse to the end of the list first
        const prevIsSame = traverse(node.next);
        // when the call stack bounces back, compare the values 
        // from the head and from the bottom up
        const currIsSame = curr.val === node.val;
        curr = curr.next;
        return prevIsSame && currIsSame;
    }
    return traverse(head);
};

//445. Add Two Numbers II
var addTwoNumbers = function(l1, l2) {
    let stack1 = [];
    let stack2 = [];
    while(l1) {
        stack1.push(l1.val);
        l1 = l1.next;
    }
    while(l2) {
        stack2.push(l2.val);
        l2 = l2.next;
    }
    let l3 = new ListNode(0);
    while(stack1.length || stack2.length) {
        let sum = 0;
        if(stack1.length) sum += stack1.pop();
        if(stack2.length) sum += stack2.pop();
        sum += l3.val;
        l3.val = sum%10;
        let head = new ListNode(Math.floor(sum/10));
        head.next = l3;
        l3 = head;
    }
    return (l3.val === 0) ? l3.next : l3;
};

//17. Letter Combinations of a Phone Number
const letterCombinations = (digits) => {
    if (digits == null || digits.length === 0) return [];
  
    const map = {
      2: 'abc',
      3: 'def',
      4: 'ghi',
      5: 'jkl',
      6: 'mno',
      7: 'pqrs',
      8: 'tuv',
      9: 'wxyz',
    };
  
    const res = [];
    const go = (i, s) => {
      if (i === digits.length) {
        res.push(s);
        return;
      }
  
      for (const c of map[digits[i]]) {
        go(i + 1, s + c);
      }
    };
  
    go(0, '');
    return res;
};



//124. Binary Tree Maximum Path Sum
  var maxPathSum = function(root) {
    var max = -Infinity // Initialize to a very small number to handle a path of negative values
    getMaxSum(root) // Call our recursive fn to start the program
    
    return max // Once we have popped out of our recursive calls, `max` contains our maximum path sum
    
    function getMaxSum(tree) {
      if (tree == null) { return 0 } // Termination condition
      
      const leftBranch = Math.max(0, getMaxSum(tree.left)) // calculate the root to leaf sum where root is the left node
      const rightBranch = Math.max(0, getMaxSum(tree.right)) // calculate the root to leaf sum where root is the right node
      const currentPath = leftBranch + tree.val + rightBranch  // Sum the path: left -> root -> right (leaf to leaf)
      
      max = Math.max(max, currentPath) // if the current path is greater than the previous value of `max`, update `max` to the current path sum
      return tree.val + Math.max(leftBranch, rightBranch)
    }
};



//98. Validate Binary Search Tree
  var isValidBST = function(root, min=null, max=null) {
    if (!root) return true;
    if (min && root.val <= min.val) return false;
    if (max && root.val >= max.val) return false;
    return isValidBST(root.left, min, root) && isValidBST(root.right, root, max);
};

//698. Partition to K Equal Sum Subsets
var canPartitionKSubsets = function(nums, k) {
    const total = nums.reduce((sum, num) => sum + num, 0);
    // return false right away when there's no equal sum among k subsets
    if (total % k !== 0) {
      return false;
    } 
    
    const target = total / k;
    const visited = new Array(nums.length).fill(false);
    
    const canPartition = (start, numberOfSubsets, currentSum) => {
      // base case
      if (numberOfSubsets === 1) {
        return true;
      }
      // when a subset is found, we launch another search to find the 
      // remaining subsets from the unvisited elements. 
      if (currentSum === target) {
        return canPartition(0, numberOfSubsets - 1, 0);
      }
      for (let i = start; i < nums.length; i++) {
        if (!visited[i]) {
          visited[i] = true;
          // launch a search to find other elements that will sum up to 
          // the target with the current element.
          if (canPartition(i + 1, numberOfSubsets, currentSum + nums[i])) {
            return true;
          }
          // reset to enable backtracking
          visited[i] = false;
        }
      }
      return false;
    };
    
    return canPartition(0, k, 0);
};

//938. Range Sum of BST
var rangeSumBST = function(root, L, R) {
  // check if value is in the given range
  const isInBetween = val => val >= L && val <= R;
  // sum the value if it's in the range
  const add = (val, sum) => isInBetween(val) ? sum += val : sum;
// traverse through the nodes and sum the values in range
  const preorder =(root, sum) => {
      if (!root) return sum;
      return add(root.val, sum) + preorder(root.left, sum) + preorder(root.right, sum);
  } 
  return preorder(root, 0)
};

//779. K-th Symbol in Grammar
var kthGrammar = function(N, K) {
    
  // Edge case
  if(K == 1 && N == 1) {
      return '0';
  }
   
  return f('0', K-1, Math.pow(2, N-1));
  
  /**
   * Helper function
   * s - previous symbol
   * k - adjusted index
   * p - length of the string on the level
   * Time: O(N) because f() will called log ( 2^(N-1) ) = N times
   * Space: O(N) because of recursion
   */
  function f (s, k, p) {
     
     /*
      Since initial value of p is 2^(N-1), dividing by 2 will gives a 1 in O(N) of time
     */
     if(p == 1) {
       return s;
     }
     /*
       Function litteraly moves to the left or to thre right based on a simple set of rules.
       
       Depending on the previous symbol (s), next symbol will be left or right:
       
         0         1
        / \       / \
       0   1     1   0
     
       In case, if k is in a right part - use rules for right, in other case - left
     */
     const dir = {
       'r': { '0': '1', '1': '0' },
       'l': { '0': '0', '1': '1'  }
     };
     /*
      To determine, if k is on the left or on the right from the center of the string
      we will just check if k >= p/2 , if true - k is in the right part. 
      
      For the right case, we have to adjust k by extracting p/2
     */
     return f(dir[k >= p/2 ? 'r': 'l' ][s], k >= p/2 ? k - p/2 : k, p/2);
  }
 
  /*
              LEFT                                       RIGHT
                                     0
                                    0 1
                                   01 10
                                 0110 1001
                            0110 1001 1001 0110
                  0110 1001 1001 0110 1001 0110 0110 1001    
0110 1001 1001 0110 1001 0110 0110 1001 1001 0110 0110 1001 0110 1001 1001 0110

  */
};

//56. Merge Intervals
var merge = function(intervals) {
  if(intervals.length < 2) return intervals; 
  intervals.sort((a,b) => a[0] - b[0]) //Arr have smaller element come first
  for(let i = 1; i < intervals.length; i += 1){
      curr = intervals[i];
      prev = intervals[i-1];
      if(curr[0] <= prev[1]){
          intervals[i] = [Math.min(prev[0],curr[0]), Math.max(prev[1],curr[1])]
          intervals.splice(i-1,1);
          i -= 1  // After merge, the arr become shorter
      }
  }
  return intervals
};

//3. Longest Substring Without Repeating Characters
var lengthOfLongestSubstring = function(s) {
  // keeps track of the most recent index of each letter.
  const seen = new Map();
  // keeps track of the starting index of the current substring.
  let start = 0;
  // keeps track of the maximum substring length.
  let maxLen = 0;
  
  for(let i = 0; i < s.length; i++) {
      // if the current char was seen, move the start to (1 + the last index of this char)
      // max prevents moving backward, 'start' can only move forward
      if(seen.has(s[i])) start = Math.max(seen.get(s[i]) + 1, start)
      seen.set(s[i], i);
      // maximum of the current substring length and maxLen
      maxLen = Math.max(i - start + 1, maxLen);
  } 
  
  return maxLen;  
};