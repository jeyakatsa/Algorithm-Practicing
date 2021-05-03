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