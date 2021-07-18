import java.util.*;

public class Arrays8 {

    //Buildings With an Ocean View
    public int[] findBuildings(int[] heights) {
        List<Integer> temp = new ArrayList<>();
        int nextBiggest = 0;
        int j = heights.length - 1;
        while (j >= 0) {
            if (heights[j] > nextBiggest) {
                nextBiggest = heights[j];
                temp.add(j);
            }
            j--;
        }
        int[] answer = new int[temp.size()];
        int index = 0;
        for(int i = temp.size() - 1; i >= 0; i--) {
            answer[index] = temp.get(i);
            index++;
        }
        return answer;
        
    }
    
}
