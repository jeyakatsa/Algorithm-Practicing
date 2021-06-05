import java.util.*;

public class HashTables1 {


    //GROUP ANAGRAMS...    
    public List<List<String>> groupAnagrams(String[] strs) {
        Map<String,List<String>> map = new HashMap<>();
        for(String str : strs){
            char[] c= str.toCharArray();
            Arrays.sort(c);
            String temp = Arrays.toString(c);
            if(!map.containsKey(temp)){
                map.put(temp,new LinkedList<>());
            }
            map.get(temp).add(str);
        }

        return new ArrayList<>(map.values());
    }


    //DAILY TEMPERATURES!
    public int[] dailyTemperatures(int[] temperatures) {
        Stack<Integer> stack = new Stack<>();
        int[] ret = new int[temperatures.length];
        for(int i = 0; i < temperatures.length; i++) {
            while(!stack.isEmpty() && temperatures[i] > temperatures[stack.peek()]) {
                int idx = stack.pop();
                ret[idx] = i - idx;
            }
            stack.push(i);
        }
        return ret;
    }   


}
