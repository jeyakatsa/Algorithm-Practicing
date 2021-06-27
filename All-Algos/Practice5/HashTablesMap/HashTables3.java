import java.util.*;

public class HashTables3 {


    //Tweet Counts Per Frequency
    Map<String, List<Integer>> map;
    public HashTables3() {
        map = new HashMap<>(); 
    }
    public void recordTweet(String tweetName, int time) {
        if(!map.containsKey(tweetName)){
            List<Integer> temp = new ArrayList<>();
            temp.add(time);
            map.put(tweetName, temp);
        }else{
            map.get(tweetName).add(time);
        }
    }
    public List<Integer> getTweetCountsPerFrequency(String freq, String tweetName, int startTime, int endTime) {
        int interval = 60;
        if(freq.equals("hour"))
            interval = interval * 60;
        if(freq.equals("day"))
            interval = interval * 60 * 24;
        List<Integer> res = new ArrayList<>();
        // get the number of possible intervals, 
        // if startTime = 30 and endTime = 150 with minute as freq
        // (150 - 30) / 60 = 2, this means there will be 3 intervals
        // [30, 90); [90, 150); [150, 150)
        for(int i = 0; i <= (endTime - startTime) / interval; i++)
            res.add(0);
        List<Integer> times = map.get(tweetName);
        for(int time : times){
            if(startTime <= time && time <= endTime){
                // get the index of which interval at current time
                int idx = (time - startTime) / interval;
                res.set(idx, res.get(idx)+1);
            }
        }
        return res;
    }


    //Roman Numerals to Integers.
    static Map<String, Integer> values = new HashMap<>();
    static {
        values.put("M", 1000);
        values.put("D", 500);
        values.put("C", 100);
        values.put("L", 50);
        values.put("X", 10);
        values.put("V", 5);
        values.put("I", 1);
    }
    public int romanToInt(String s) {
        int sum = 0;
        int i = 0;
        while (i < s.length()) {
            String currentSymbol = s.substring(i, i + 1);
            int currentValue = values.get(currentSymbol);
            int nextValue = 0;
            if (i + 1 < s.length()) {
                String nextSymbol = s.substring(i + 1, i + 2);
                nextValue = values.get(nextSymbol);
            }
            if (currentValue < nextValue) {
                sum += (nextValue - currentValue);
                i += 2;
            }
            else {
                sum += currentValue;
                i += 1;
            }
        }
        return sum;
    }    


    //ReOrganize String
    public String reorganizeString(String S) {
        int[] hash = new int[26];
        for (int i = 0; i < S.length(); i++) {
            hash[S.charAt(i) - 'a']++;
        } 
        int max = 0, letter = 0;
        for (int i = 0; i < hash.length; i++) {
            if (hash[i] > max) {
                max = hash[i];
                letter = i;
            }
        }
        if (max > (S.length() + 1) / 2) {
            return ""; 
        }
        char[] res = new char[S.length()];
        int idx = 0;
        while (hash[letter] > 0) {
            res[idx] = (char) (letter + 'a');
            idx += 2;
            hash[letter]--;
        }
        for (int i = 0; i < hash.length; i++) {
            while (hash[i] > 0) {
                if (idx >= res.length) {
                    idx = 1;
                }
                res[idx] = (char) (i + 'a');
                idx += 2;
                hash[i]--;
            }
        }
        return String.valueOf(res);
    }    




    //Integer to Roman
    private static final int[] valuess = {1000, 900, 500, 400, 100, 90, 50, 40, 10, 9, 5, 4, 1};    
    private static final String[] symbols = {"M","CM","D","CD","C","XC","L","XL","X","IX","V","IV","I"};
    public String intToRoman(int num) {
        StringBuilder sb = new StringBuilder();
        // Loop through each symbol, stopping if num becomes 0.
        for (int i = 0; i < valuess.length && num > 0; i++) {
            // Repeat while the current symbol still fits into num.
            while (valuess[i] <= num) {
                num -= valuess[i];
                sb.append(symbols[i]);
            }
        }
        return sb.toString();
    }


}
