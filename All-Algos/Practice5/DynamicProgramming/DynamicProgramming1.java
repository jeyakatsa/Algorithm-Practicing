import java.util.*;

public class DynamicProgramming1 {
    public static void main(String[] args) {
        String string = "leetcode";

    }

    //Best Time To Buy/Sell Stock
    public int maxProfit(int prices[]) {
        int maxprofit = 0;
        for (int i = 0; i < prices.length - 1; i++) {
            for (int j = i + 1; j < prices.length; j++) {
                int profit = prices[j] - prices[i];
                if (profit > maxprofit)
                    maxprofit = profit;
            }
        }
        return maxprofit;
    }    
    
}