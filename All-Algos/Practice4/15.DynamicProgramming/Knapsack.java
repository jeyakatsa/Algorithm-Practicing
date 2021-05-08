public class Knapsack {

    public int solveKnapsack(int[] profits, int[] weights, int capacity) {
        return this.knapsackRecursive(profits, weights, capacity, 0);
    }    
    public int knapsackRecursive(int[] profits, int[] weights, int capacity,
    int currentIndex) {
        // for each item 'i' 
        //     create a new set which INCLUDES item 'i' if the total weight does not exceed the capacity, and 
        //         recursively process the remaining capacity and items
        //     create a new set WITHOUT item 'i', and recursively process the remaining items 
        // return the set from the above two sets with higher profit         

        // base checks
        if (capacity <= 0 || currentIndex >= profits.length)
        return 0;

        // recursive call after choosing the element at the currentIndex
        // if the weight of the element at currentIndex exceeds the capacity, we shouldn't process this
        int profit1 = 0;
        if( weights[currentIndex] <= capacity )
            profit1 = profits[currentIndex] + knapsackRecursive(profits, weights,
                    capacity - weights[currentIndex], currentIndex + 1);

        // recursive call after excluding the element at the currentIndex
        int profit2 = knapsackRecursive(profits, weights, capacity, currentIndex + 1);

        return Math.max(profit1, profit2);
    }

    public boolean canPartition(int[] num) {
        int sum = 0;
        for (int i = 0; i < num.length; i++)
          sum += num[i];
    
        // if 'sum' is a an odd number, we can't have two subsets with equal sum
        if(sum % 2 != 0)
          return false;
    
        return this.canPartitionRecursive(num, sum/2, 0);
    }
    private boolean canPartitionRecursive(int[] num, int sum, int currentIndex) {
        // base check
        if (sum == 0)
            return true;

        if(num.length == 0 || currentIndex >= num.length)
            return false;

        // recursive call after choosing the number at the currentIndex
        // if the number at currentIndex exceeds the sum, we shouldn't process this
        if( num[currentIndex] <= sum ) {
            if(canPartitionRecursive(num, sum - num[currentIndex], currentIndex + 1))
            return true;
        }

        // recursive call after excluding the number at the currentIndex
        return canPartitionRecursive(num, sum, currentIndex + 1);
    }    

    public static void main(String[] args) {
        Knapsack ks = new Knapsack();
        int[] profits = {1, 6, 10, 16};
        int[] weights = {1, 2, 3, 5};
        int maxProfit = ks.solveKnapsack(profits, weights, 7);
        System.out.println("Total knapsack profit ---> " + maxProfit);
        maxProfit = ks.solveKnapsack(profits, weights, 6);
        System.out.println("Total knapsack profit ---> " + maxProfit);

        Knapsack ps = new Knapsack();
        int[] num = {1, 2, 3, 4};
        System.out.println(ps.canPartition(num));
        num = new int[]{1, 1, 3, 4, 7};
        System.out.println(ps.canPartition(num));
        num = new int[]{2, 3, 4, 6};
        System.out.println(ps.canPartition(num));        
    }    
}