import java.util.*;

public class Subsets {

    //Subsets
    public static List<List<Integer>> findSubsets(int[] nums){
        List<List<Integer>> subsets = new ArrayList<>();      
        if (nums == null) {
            return subsets;
        }
        subsets.add(new ArrayList<>());
        for(int currentNumber : nums) {
            //we will take all existing subsets and insert the current 
            //number in them to create new subsets
            int n = subsets.size();
            for (int i = 0; i < n; i++) {
                //create a new subset from the existing subset and 
                //insert the current element to it
                List<Integer> set = new ArrayList<>(subsets.get(i));
                set.add(currentNumber);
                subsets.add(set);
            }
        }

        return subsets;
    }

    //Subsets With Duplicates
    public static List<List<Integer>> findSubsetDuplicates(int[] nums) {
        // sort the numbers to handle duplicates
        Arrays.sort(nums);
        List<List<Integer>> subsets = new ArrayList<>();
        subsets.add(new ArrayList<>());
        int startIndex = 0, endIndex = 0;
        for (int i = 0; i < nums.length; i++) {
            startIndex = 0;
            // if current and the previous elements are same, 
            // create new subsets only from the subsets 
            // added in the previous step
            if (i > 0 && nums[i] == nums[i - 1])
                startIndex = endIndex + 1;
            endIndex = subsets.size() - 1;
            for (int j = startIndex; j <= endIndex; j++) {
                // create a new subset from the existing subset 
                // and add the current element to it
                List<Integer> set = new ArrayList<>(subsets.get(j));
                set.add(nums[i]);
                subsets.add(set);
            }
        }
        return subsets;
    }

    //Number Permutations.
    public static List<List<Integer>> findPermutations(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        Queue<List<Integer>> permutations = new LinkedList<>();
        permutations.add(new ArrayList<>());
        for (int currentNumber : nums) {
            //we will take all existing permutations and add
            //the current number to new permutations
            int n = permutations.size();
            for (int i = 0; i < n; i++) {
                List<Integer> oldPermutation = permutations.poll();
                for (int j = 0; j <= oldPermutation.size(); j++) {
                    List<Integer> newPermutation = new ArrayList<Integer>(oldPermutation);
                    newPermutation.add(j, currentNumber);
                    if(newPermutation.size() == nums.length) {
                        result.add(newPermutation);
                    }
                    else {
                        permutations.add(newPermutation);
                    }
                }
            }
        }
        return result;
    }

    //String Permutations
    public static List<String> stringPermutation(String str) {
        List<String> permutations = new ArrayList<>();
        permutations.add(str);

        //Process every character
        for (int i = 0; i < str.length(); i++) {
            if (Character.isLetter(str.charAt(i))) { // only process characters, skip digits
                // we will take all existing permutations and change the letter case appropriately
                int n = permutations.size();
                for (int j = 0; j < n; j++) {
                    char[] chs = permutations.get(j).toCharArray();
                    //if current character is in upper case change it to lower case
                    if (Character.isUpperCase(chs[i])) {
                        chs[i] = Character.toLowerCase(chs[i]);
                    } else {
                        chs[i] = Character.toUpperCase(chs[i]);
                    }
                    permutations.add(String.valueOf(chs));
                }
            }
        }
        return permutations;
        
    }



    public static void main(String[] args) {
        List<List<Integer>> result = Subsets.findSubsets(new int[] { 1, 3 });
        System.out.println("Here is the list of subsets: " + result);
    
        result = Subsets.findPermutations(new int[] { 1, 5, 3 });
        System.out.println("Here is the list of permutations: " + result);

        List<String> result2 = stringPermutation("Alk9");
        System.out.println("String permutations are: " + result2);
      }    
}