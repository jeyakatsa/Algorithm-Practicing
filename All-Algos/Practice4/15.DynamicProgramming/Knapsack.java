public class Knapsack {
    public int solveKnapsack(int[] profits, int[] weights, int capacity) {
        // for each item 'i' 
        //     create a new set which INCLUDES item 'i' if the total weight does not exceed the capacity, and 
        //         recursively process the remaining capacity and items
        //     create a new set WITHOUT item 'i', and recursively process the remaining items 
        // return the set from the above two sets with higher profit         
        // TODO: Write your code here
        return -1;
      }
    
      public static void main(String[] args) {
        Knapsack ks = new Knapsack();
        int[] profits = {1, 6, 10, 16};
        int[] weights = {1, 2, 3, 5};
        int maxProfit = ks.solveKnapsack(profits, weights, 7);
        System.out.println("Total knapsack profit ---> " + maxProfit);
        maxProfit = ks.solveKnapsack(profits, weights, 6);
        System.out.println("Total knapsack profit ---> " + maxProfit);
      }    
}