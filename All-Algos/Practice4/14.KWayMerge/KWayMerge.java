import java.util.*;

public class KWayMerge {

    public static class ListNode {
        int value;
        ListNode next;
        
        ListNode(int value) {
            this.value = value;
        }
    }        
    //Merge K Sorted Elements, if [l1 = 1345] & [l2 = 1257] == 11234557
    public static ListNode merge(ListNode[] lists) {
        PriorityQueue<ListNode> minHeap = new PriorityQueue<ListNode>((n1, n2) -> n1.value - n2.value);

        // put the root of each list in the min heap
        for (ListNode root : lists)
          if (root != null)
            minHeap.add(root);
    
        // take the smallest (top) element form the min-heap and add it to the result; 
        // if the top element has a next element add it to the heap
        ListNode resultHead = null, resultTail = null;
        while (!minHeap.isEmpty()) {
          ListNode node = minHeap.poll();
          if (resultHead == null) {
            resultHead = resultTail = node;
          } else {
            resultTail.next = node;
            resultTail = resultTail.next;
          }
          if (node.next != null)
            minHeap.add(node.next);
        }
    
        return resultHead;
    }




    

    static class Node {
        int elementIndex;
        int arrayIndex;

        Node(int elementIndex, int arrayIndex) {
            this.elementIndex = elementIndex;
            this.arrayIndex = arrayIndex;
        }
    }
    public static int findKthSmallest(List<Integer[]> lists, int k) {
        PriorityQueue<Node> minHeap = new PriorityQueue<Node>(
            (n1, n2) -> lists.get(n1.arrayIndex)[n1.elementIndex] - lists.get(n2.arrayIndex)[n2.elementIndex]);
    
        // put the 1st element of each array in the min heap
        for (int i = 0; i < lists.size(); i++)
          if (lists.get(i) != null)
            minHeap.add(new Node(0, i));
    
        // take the smallest (top) element form the min heap, if the running count is equal to k return the number
        // if the array of the top element has more elements, add the next element to the heap
        int numberCount = 0, result = 0;
        while (!minHeap.isEmpty()) {
          Node node = minHeap.poll();
          result = lists.get(node.arrayIndex)[node.elementIndex];
          if (++numberCount == k)
            break;
          node.elementIndex++;
          if (lists.get(node.arrayIndex).length > node.elementIndex)
            minHeap.add(node);
        }
        return result;
      }
      
      
      
      //Kth Smallest Number in M Sorted Lists



    
    public static void main(String[] args) {
        // ListNode l1 = new ListNode(2);
        // l1.next = new ListNode(6);
        // l1.next.next = new ListNode(8);
    
        // ListNode l2 = new ListNode(3);
        // l2.next = new ListNode(6);
        // l2.next.next = new ListNode(7);
    
        // ListNode l3 = new ListNode(1);
        // l3.next = new ListNode(3);
        // l3.next.next = new ListNode(4);
    
        // ListNode result = merge(new ListNode[] { l1, l2, l3 });
        // System.out.print("Here are the elements form the merged list: ");
        // while (result != null) {
        // System.out.print(result.value + " ");
        // result = result.next;

        Integer[] l1 = new Integer[] { 2, 6, 8 };
        Integer[] l2 = new Integer[] { 3, 6, 7 };
        Integer[] l3 = new Integer[] { 1, 3, 4 };
        List<Integer[]> lists = new ArrayList<Integer[]>();
        lists.add(l1);
        lists.add(l2);
        lists.add(l3);
        int result = findKthSmallest(lists, 5);
        System.out.print("Kth smallest number is: " + result);        
    }
}