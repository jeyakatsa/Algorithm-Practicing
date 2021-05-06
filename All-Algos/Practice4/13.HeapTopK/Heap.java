import java.util.*;

public class Heap {
    public static List<Integer> findKLargestNumbers(int[] nums, int k) {
        PriorityQueue<Integer> minHeap = new PriorityQueue<Integer>((n1, n2) -> n1 - n2);
        // put first 'K' numbers in the min heap
        for (int i = 0; i < k; i++)
          minHeap.add(nums[i]);
    
        // go through the remaining numbers of the array, if the number from the array is bigger than the
        // top (smallest) number of the min-heap, remove the top number from heap and add the number from array
        for (int i = k; i < nums.length; i++) {
          if (nums[i] > minHeap.peek()) {
            minHeap.poll();
            minHeap.add(nums[i]);
          }
        }
    
        // the heap has the top 'K' numbers, return them in a list
        return new ArrayList<>(minHeap);
    } 

    public static int findKthSmallestNumber(int[] nums, int k) {
        PriorityQueue<Integer> maxHeap = new PriorityQueue<Integer>((n1, n2) -> n2 - n1);
        // put first k numbers in the max heap
        for (int i = 0; i < k; i++)
          maxHeap.add(nums[i]);
    
        // go through the remaining numbers of the array, if the number from the array is smaller than the
        // top (biggest) number of the heap, remove the top number from heap and add the number from array
        for (int i = k; i < nums.length; i++) {
          if (nums[i] < maxHeap.peek()) {
            maxHeap.poll();
            maxHeap.add(nums[i]);
          }
        }
    
        // the root of the heap has the Kth smallest number
        return maxHeap.peek();
    }  

    public static class Point {
        int x;
        int y;
      
        public Point(int x, int y) {
          this.x = x;
          this.y = y;
        }
      
        public int distFromOrigin() {
          // ignoring sqrt
          return (x * x) + (y * y);
        }
    }
    public static List<Point> findClosestPoints(Point[] points, int k) {
        PriorityQueue<Point> maxHeap = new PriorityQueue<>((p1, p2) -> p2.distFromOrigin() - p1.distFromOrigin());
        // put first 'k' points in the max heap
        for (int i = 0; i < k; i++)
          maxHeap.add(points[i]);
    
        // go through the remaining points of the input array, if a point is closer to the origin than the top point 
        // of the max-heap, remove the top point from heap and add the point from the input array
        for (int i = k; i < points.length; i++) {
          if (points[i].distFromOrigin() < maxHeap.peek().distFromOrigin()) {
            maxHeap.poll();
            maxHeap.add(points[i]);
          }
        }
    
        // the heap has 'k' points closest to the origin, return them in a list
        return new ArrayList<>(maxHeap);
    }
    
    //MinHeap RopeLength
    public static int minimumCostToConnectRopes(int[] ropeLengths) {
        PriorityQueue<Integer> minHeap = new PriorityQueue<Integer>((n1, n2) -> n1 - n2);
        // add all ropes to the min heap
        for (int i = 0; i < ropeLengths.length; i++)
          minHeap.add(ropeLengths[i]);
    
        // go through the values of the heap, in each step take top (lowest) rope lengths from the min heap
        // connect them and push the result back to the min heap. 
        // keep doing this until the heap is left with only one rope
        int result = 0, temp = 0;
        while (minHeap.size() > 1) {
          temp = minHeap.poll() + minHeap.poll();
          result += temp;
          minHeap.add(temp);
        }
    
        return result;
    }
    
    //Find K Closest Points.
    public static List<Point> findKClosestPoints(Point[] points, int k) {
        PriorityQueue<Point> maxHeap = new PriorityQueue<>((p1, p2) -> p2.distFromOrigin() - p1.distFromOrigin());
        // put first 'k' points in the max heap
        for (int i = 0; i < k; i++)
          maxHeap.add(points[i]);
    
        // go through the remaining points of the input array, if a point is closer to the origin than the top point 
        // of the max-heap, remove the top point from heap and add the point from the input array
        for (int i = k; i < points.length; i++) {
          if (points[i].distFromOrigin() < maxHeap.peek().distFromOrigin()) {
            maxHeap.poll();
            maxHeap.add(points[i]);
          }
        }
    
        // the heap has 'k' points closest to the origin, return them in a list
        return new ArrayList<>(maxHeap);
    }  
    
    public static List<Integer> findTopKFrequentNumbers(int[] nums, int k) {
        // find the frequency of each number
        Map<Integer, Integer> numFrequencyMap = new HashMap<>();
        for (int n : nums)
          numFrequencyMap.put(n, numFrequencyMap.getOrDefault(n, 0) + 1);
    
        PriorityQueue<Map.Entry<Integer, Integer>> minHeap = new PriorityQueue<Map.Entry<Integer, Integer>>(
            (e1, e2) -> e1.getValue() - e2.getValue());
    
        // go through all numbers of the numFrequencyMap and push them in the minHeap, which will have 
        // top k frequent numbers. If the heap size is more than k, we remove the smallest (top) number 
        for (Map.Entry<Integer, Integer> entry : numFrequencyMap.entrySet()) {
          minHeap.add(entry);
          if (minHeap.size() > k) {
            minHeap.poll();
          }
        }
    
        // create a list of top k numbers
        List<Integer> topNumbers = new ArrayList<>(k);
        while (!minHeap.isEmpty()) {
          topNumbers.add(minHeap.poll().getKey());
        }
        return topNumbers;
    }

    public static String sortCharacterByFrequency(String str) {
        // find the frequency of each character
        Map<Character, Integer> characterFrequencyMap = new HashMap<>();
        for (char chr : str.toCharArray()) {
          characterFrequencyMap.put(chr, characterFrequencyMap.getOrDefault(chr, 0) + 1);
        }
    
        PriorityQueue<Map.Entry<Character, Integer>> maxHeap = new PriorityQueue<Map.Entry<Character, Integer>>(
            (e1, e2) -> e2.getValue() - e1.getValue());
    
        // add all characters to the max heap
        maxHeap.addAll(characterFrequencyMap.entrySet());
    
        // build a string, appending the most occurring characters first
        StringBuilder sortedString = new StringBuilder(str.length());
        while (!maxHeap.isEmpty()) {
          Map.Entry<Character, Integer> entry = maxHeap.poll();
          for (int i = 0; i < entry.getValue(); i++)
            sortedString.append(entry.getKey());
        }
        return sortedString.toString();
    } 
    
    // //Kth Largest Number in Stream (Issues with this one...)
    // PriorityQueue<Integer> minHeap = new PriorityQueue<Integer>((n1, n2) -> n1 - n2);
    // final int kk;
  
    // public KthLargestNumberInStream(int[] nums, int k) {
    //   this.kk = k;
    //   // add the numbers in the min heap
    //   for (int i = 0; i < nums.length; i++)
    //     add(nums[i]);
    // }
  
    // public int add(int num) {
    //   // add the new number in the min heap
    //   minHeap.add(num);
  
    //   // if heap has more than 'k' numbers, remove one number
    //   if (minHeap.size() > this.k)
    //     minHeap.poll();
  
    //   // return the 'Kth largest number
    //   return minHeap.peek();
    // }

  // public static List<Integer> findClosestElements(int[] arr, int K, Integer X) {
  //   int index = binarySearch(arr, X);
  //   int low = index - K, high = index + K;
  //   low = Math.max(low, 0); // 'low' should not be less than zero
  //   high = Math.min(high, arr.length - 1); // 'high' should not be greater the size of the array

  //   PriorityQueue<Entry> minHeap = new PriorityQueue<>((n1, n2) -> n1.key - n2.key);
  //   // add all candidate elements to the min heap, sorted by their absolute difference from 'X'
  //   for (int i = low; i <= high; i++)
  //     minHeap.add(new Entry(Math.abs(arr[i] - X), i));

  //   // we need the top 'K' elements having smallest difference from 'X'
  //   List<Integer> result = new ArrayList<>();
  //   for (int i = 0; i < K; i++)
  //     result.add(arr[minHeap.poll().value]);

  //   Collections.sort(result);
  //   return result;
  // }
  // private static int binarySearch(int[] arr, int target) {
  //   int low = 0;
  //   int high = arr.length - 1;
  //   while (low <= high) {
  //     int mid = low + (high - low) / 2;
  //     if (arr[mid] == target)
  //       return mid;
  //     if (arr[mid] < target) {
  //       low = mid + 1;
  //     } else {
  //       high = mid - 1;
  //     }
  //   }
  //   if (low > 0) {
  //     return low - 1;
  //   }
  //   return low;
  // }

  //Maximum Distinct Elements
  public static int findMaximumDistinctElements(int[] nums, int k) {
    int distinctElementsCount = 0;
    if (nums.length <= k)
      return distinctElementsCount;

    // find the frequency of each number
    Map<Integer, Integer> numFrequencyMap = new HashMap<>();
    for (int i : nums)
      numFrequencyMap.put(i, numFrequencyMap.getOrDefault(i, 0) + 1);

    PriorityQueue<Map.Entry<Integer, Integer>> minHeap = new PriorityQueue<Map.Entry<Integer, Integer>>(
        (e1, e2) -> e1.getValue() - e2.getValue());

    // insert all numbers with frequency greater than '1' into the min-heap
    for (Map.Entry<Integer, Integer> entry : numFrequencyMap.entrySet()) {
      if (entry.getValue() == 1)
        distinctElementsCount++;
      else
        minHeap.add(entry);
    }

    // following a greedy approach, try removing the least frequent numbers first from the min-heap
    while (k > 0 && !minHeap.isEmpty()) {
      Map.Entry<Integer, Integer> entry = minHeap.poll();
      // to make an element distinct, we need to remove all of its occurrences except one
      k -= entry.getValue() - 1;
      if (k >= 0)
        distinctElementsCount++;
    }

    // if k > 0, this means we have to remove some distinct numbers
    if (k > 0)
      distinctElementsCount -= k;

    return distinctElementsCount;
  }

  //Sum of Elements
  
    
  public static void main(String[] args) {
    List<Integer> result = findKLargestNumbers(new int[] { 3, 1, 5, 12, 2, 11 }, 3);
    System.out.println("Here are the top K numbers: " + result);

    result = findKLargestNumbers(new int[] { 5, 12, 11, -1, 12 }, 3);
    System.out.println("Here are the top K numbers: " + result);

    Point[] points = new Point[] { new Point(1, 3), new Point(3, 4), new Point(2, -1) };
    List<Point> results = findClosestPoints(points, 2);
    System.out.print("Here are the k points closest the origin: ");
    for (Point p : results)
      System.out.print("[" + p.x + " , " + p.y + "] ");        
  }


}