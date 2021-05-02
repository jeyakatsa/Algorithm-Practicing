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

    class Point {
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
    
    public static void main(String[] args) {
        List<Integer> result = findKLargestNumbers(new int[] { 3, 1, 5, 12, 2, 11 }, 3);
        System.out.println("Here are the top K numbers: " + result);

        result = findKLargestNumbers(new int[] { 5, 12, 11, -1, 12 }, 3);
        System.out.println("Here are the top K numbers: " + result);
    }
}