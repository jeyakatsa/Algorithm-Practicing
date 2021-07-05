import java.util.*;

public class Arrays6 {

    //Gas Station
    public int canCompleteCircuit(int[] gas, int[] cost) {
        int n = gas.length;
    
        int total_tank = 0;
        int curr_tank = 0;
        int starting_station = 0;
        for (int i = 0; i < n; ++i) {
          total_tank += gas[i] - cost[i];
          curr_tank += gas[i] - cost[i];
          // If one couldn't get here,
          if (curr_tank < 0) {
            // Pick up the next station as the starting one.
            starting_station = i + 1;
            // Start with an empty tank.
            curr_tank = 0;
          }
        }
        return total_tank >= 0 ? starting_station : -1;
    }   


    //Permutations... 
    public void backtrack(int n,
                            ArrayList<Integer> nums,
                            List<List<Integer>> output,
                            int first) {
        // if all integers are used up
        if (first == n)
        output.add(new ArrayList<Integer>(nums));
        for (int i = first; i < n; i++) {
        // place i-th integer first 
        // in the current permutation
        Collections.swap(nums, first, i);
        // use next integers to complete the permutations
        backtrack(n, nums, output, first + 1);
        // backtrack
        Collections.swap(nums, first, i);
        }
    }

    public List<List<Integer>> permute(int[] nums) {
        // init output list
        List<List<Integer>> output = new LinkedList<>();

        // convert nums into list since the output is a list of lists
        ArrayList<Integer> nums_lst = new ArrayList<Integer>();
        for (int num : nums)
        nums_lst.add(num);

        int n = nums.length;
        backtrack(n, nums_lst, output, 0);
        return output;
    }    


    
}
