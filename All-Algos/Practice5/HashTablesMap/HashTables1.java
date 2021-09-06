import java.util.*;

public class HashTables1 {

    //Subdomain Visit Count  
    public List<String> subdomainVisits(String[] cpdomains) {
        Map<String, Integer> counts = new HashMap<>();
        for (String domain: cpdomains) {
            String[] cpinfo = domain.split("\\s+");
            String[] frags = cpinfo[1].split("\\.");
            int count = Integer.valueOf(cpinfo[0]);
            String cur = "";
            for (int i = frags.length - 1; i >= 0; --i) {
                cur = frags[i] + (i < frags.length - 1 ? "." : "") + cur;
                counts.put(cur, counts.getOrDefault(cur, 0) + count);
            }
        }

        List<String> ans = new ArrayList<>();
        for (String dom: counts.keySet())
            ans.add("" + counts.get(dom) + " " + dom);
        return ans;
    }

    //Letter Combinations of a Phone Number
    private List<String> combinations = new ArrayList<>();
    private Map<Character, String> letters = Map.of(
        '2', "abc", '3', "def", '4', "ghi", '5', "jkl", 
        '6', "mno", '7', "pqrs", '8', "tuv", '9', "wxyz");
    private String phoneDigits;
    
    public List<String> letterCombinations(String digits) {
        // If the input is empty, immediately return an empty answer array
        if (digits.length() == 0) {
            return combinations;
        }
        
        // Initiate backtracking with an empty path and starting index of 0
        phoneDigits = digits;
        backtrack(0, new StringBuilder());
        return combinations;
    }
    
    private void backtrack(int index, StringBuilder path) {
        // If the path is the same length as digits, we have a complete combination
        if (path.length() == phoneDigits.length()) {
            combinations.add(path.toString());
            return; // Backtrack
        }
        
        // Get the letters that the current digit maps to, and loop through them
        String possibleLetters = letters.get(phoneDigits.charAt(index));
        for (char letter: possibleLetters.toCharArray()) {
            // Add the letter to our current path
            path.append(letter);
            // Move on to the next digit
            backtrack(index + 1, path);
            // Backtrack by removing the letter before moving onto the next
            path.deleteCharAt(path.length() - 1);
        }
    }  

    
    
    //Analyze User Website Visit
    public List<String> mostVisitedPattern(String[] username, int[] timestamp, String[] website) {
        Map<String, ArrayList<Visit>> map = new HashMap<>();
        for(int i=0; i<username.length; ++i){
            if(!map.containsKey(username[i])){
                map.put(username[i], new ArrayList<Visit>());
            }
            Visit visit = new Visit(website[i],timestamp[i]);
            ArrayList<Visit> tmp = map.get(username[i]);
            tmp.add(visit);
        }
        sort(map);
        Map<String,HashSet<String>> sequences = generateSequences(map);
        List<String> threeSequenceAsArray = pickMaxSequenceAndReformat(sequences);
        return threeSequenceAsArray;        
    }
    private List<String> pickMaxSequenceAndReformat(Map<String,HashSet<String>> sequences){
            String maxSequence = "";
            int max =0;
            for(String sequence : sequences.keySet()){
                int totalUsers = sequences.get(sequence).size();
                if(totalUsers > max ){
                    max = totalUsers;
                    maxSequence=sequence;
                }
                else if(totalUsers == max){
                    if(sequence.compareTo(maxSequence) < 0){
                        maxSequence = sequence;
                    }
                }
            }
            List<String> threeSequenceAsArray = new ArrayList<>();
            String [] splitSequence = maxSequence.split(" ");
            for(String s : splitSequence){
                threeSequenceAsArray.add(s);
            }
            return threeSequenceAsArray;                 
    }
    private Map<String,HashSet<String>> generateSequences(Map<String,ArrayList<Visit>> map){
        Map<String, HashSet<String>> sequences = new HashMap<>();
        for(String user : map.keySet()){
            ArrayList<Visit> current = map.get(user);
            for(int i=0; i< current.size()-2; i++){
                for(int j=i+1;j< current.size()-1; j++){
                    for(int k=j+1 ;k<current.size(); k++){
                        String threeSequence = current.get(i).site +" "+ current.get(j).site +" "+current.get(k).site;
                        if(!sequences.containsKey(threeSequence)){
                            HashSet<String> tmp = new HashSet<>();
                            tmp.add(user);
                            sequences.put(threeSequence,tmp);
                        }
                        else{
                            HashSet<String> tmp = sequences.get(threeSequence);
                            tmp.add(user);
                            sequences.put(threeSequence,tmp);
                        }
                    }
                }
            }                      
        }
        return sequences;
    }      
    public void sort(Map<String,ArrayList<Visit>> map){
        for(String user : map.keySet()){
            ArrayList<Visit> visits = map.get(user);
            Collections.sort(visits);
        }
    }        
    class Visit implements Comparable <Visit>{
        String site; 
        int time;
        public Visit(String site, int time){
            this.site=site; 
            this.time=time;
        }
        public int compareTo(Visit v){
            if(v.time==this.time){
                return 0;
            }
            else{
                return this.time > v.time ? 1 : -1;
            }                
        }
    }   
    
    

    

}
