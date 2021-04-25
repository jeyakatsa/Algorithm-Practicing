public class Heap {
    public void insertNum(int num) {
        // TODO: Write your code here
      }
    
      public double findMedian() {
        // TODO: Write your code here
        return -1;
      }
    
      public static void main(String[] args) {
        Heap medianOfAStream = new Heap();
        medianOfAStream.insertNum(3);
        medianOfAStream.insertNum(1);
        System.out.println("The median is: " + medianOfAStream.findMedian());
        medianOfAStream.insertNum(5);
        System.out.println("The median is: " + medianOfAStream.findMedian());
        medianOfAStream.insertNum(4);
        System.out.println("The median is: " + medianOfAStream.findMedian());
      }    
}