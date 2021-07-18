import java.util.*;

public class HashTables6 {

    //Cinema Seat Allocation
    public int maxNumberOfFamilies(int n, int[][] reservedSeats) {
        // Just code for rules, no need to merge intervals
		// Make sure you seat 2 families for rows with no reservations
        // Greedy approach
        // First possibility: Split seating at  [2, 3     4, 5]
        // Second possibility: Split seating at [6, 7     8, 9]
        // Only possibility:  Center four seats [  4, 5, 6, 7 ]
        
        int result = 0;
        Map<Integer, List<Integer>> rowToSeatRes = new HashMap<>();
        
        for(int[] row : reservedSeats) {
            if(rowToSeatRes.containsKey(row[0])) {
                rowToSeatRes.get(row[0]).add(row[1]);
            } else {
                rowToSeatRes.put(row[0], new ArrayList<Integer>(Arrays.asList(row[1])));
            }
        }
		
        // System.out.println(rowToSeatRes);
        result = (n - rowToSeatRes.size()) * 2;         // These rows do not contain any reservations
        
       for(List<Integer> res : rowToSeatRes.values()) { // Check possible family seating in each row 
            boolean flag = false;
            
			// Check first possibility
            if(!res.contains(2) &&
              !res.contains(3) &&
              !res.contains(4) &&
              !res.contains(5)) {
                result++;
                flag = true;
            }
            
            // Check second possibility
            if(!res.contains(6) &&
              !res.contains(7) &&
              !res.contains(8) &&
              !res.contains(9)) {
                result++;
                flag = true;
            }
            
            // Check middle seats only if first two are not used
            if(!flag) {
                if(!res.contains(4) &&
                   !res.contains(5) &&
                   !res.contains(6) &&
                   !res.contains(7))
                result++;
            }
        }
        
        return result;
    }   



    //Add Bold Tag In String
    public String addBoldTag(String s, String[] dict) {
        int n = s.length();
        int[] mark = new int[n+1];
        for(String d : dict) {
            int i = -1;
            while((i = s.indexOf(d, i+1)) >= 0) {
                mark[i]++;
                mark[i + d.length()]--;
            }
        }
        StringBuilder sb = new StringBuilder();
        int sum = 0;
        for(int i = 0; i <= n; i++) {
            int cur = sum + mark[i];
            if (cur > 0 && sum == 0) sb.append("<b>");
            if (cur == 0 && sum > 0) sb.append("</b>");
            if (i == n) break;
            sb.append(s.charAt(i));
            sum = cur;
        }
        return sb.toString();
    }


    //Number of Distinct Islands
    private List<List<int[]>> uniqueIslands = new ArrayList<>(); // All known unique islands.
    private List<int[]> currentIsland = new ArrayList<>(); // Current Island
    private int[][] grid; // Input grid
    private boolean[][] seen; // Cells that have been explored. 
     
    public int numDistinctIslands(int[][] grid) {   
        this.grid = grid;
        this.seen = new boolean[grid.length][grid[0].length];   
        for (int row = 0; row < grid.length; row++) {
            for (int col = 0; col < grid[0].length; col++) {
                dfs(row, col);
                if (currentIsland.isEmpty()) {
                    continue;
                }
                // Translate the island we just found to the top left.
                int minCol = grid[0].length - 1;
                for (int i = 0; i < currentIsland.size(); i++) {
                    minCol = Math.min(minCol, currentIsland.get(i)[1]);
                }
                for (int[] cell : currentIsland) {
                    cell[0] -= row;
                    cell[1] -= minCol;
                }
                // If this island is unique, add it to the list.
                if (currentIslandUnique()) {
                    uniqueIslands.add(currentIsland);
                }
                currentIsland = new ArrayList<>();
            }
        }
        return uniqueIslands.size();
    }
    
    private void dfs(int row, int col) {
        if (row < 0 || col < 0 || row >= grid.length || col >= grid[0].length) return;
        if (seen[row][col] || grid[row][col] == 0) return;
        seen[row][col] = true;
        currentIsland.add(new int[]{row, col});
        dfs(row + 1, col);
        dfs(row - 1, col);
        dfs(row, col + 1);
        dfs(row, col - 1);
    }
    
    private boolean currentIslandUnique() {
        for (List<int[]> otherIsland : uniqueIslands) {
            if (currentIsland.size() != otherIsland.size()) continue;
            if (equalIslands(currentIsland, otherIsland)) {
                return false;
            }
        }
        return true;
    }
    
    private boolean equalIslands(List<int[]> island1, List<int[]> island2) {
        for (int i = 0; i < island1.size(); i++) {
            if (island1.get(i)[0] != island2.get(i)[0] || island1.get(i)[1] != island2.get(i)[1]) {
                return false;
            }
        }
        return true;
    } 




    //Design Log Storage System
    ArrayList < long[] > list;
    public HashTables6() {
        list = new ArrayList < long[] > ();
    }

    public void put(int id, String timestamp) {
        int[] st = Arrays.stream(timestamp.split(":")).mapToInt(Integer::parseInt).toArray();
        list.add(new long[] {convert(st), id});
    }
    public long convert(int[] st) {
        st[1] = st[1] - (st[1] == 0 ? 0 : 1);
        st[2] = st[2] - (st[2] == 0 ? 0 : 1);
        return (st[0] - 1999L) * (31 * 12) * 24 * 60 * 60 + st[1] * 31 * 24 * 60 * 60 + st[2] * 24 * 60 * 60 + st[3] * 60 * 60 + st[4] * 60 + st[5];
    }
    public List < Integer > retrieve(String s, String e, String gra) {
        ArrayList < Integer > res = new ArrayList<>();
        long start = granularity(s, gra, false);
        long end = granularity(e, gra, true);
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i)[0] >= start && list.get(i)[0] < end)
                res.add((int) list.get(i)[1]);
        }
        return res;
    }

    public long granularity(String s, String gra, boolean end) {
        HashMap < String, Integer > h = new HashMap<>();
        h.put("Year", 0);
        h.put("Month", 1);
        h.put("Day", 2);
        h.put("Hour", 3);
        h.put("Minute", 4);
        h.put("Second", 5);
        String[] res = new String[] {"1999", "00", "00", "00", "00", "00"};
        String[] st = s.split(":");
        for (int i = 0; i <= h.get(gra); i++) {
            res[i] = st[i];
        }
        int[] t = Arrays.stream(res).mapToInt(Integer::parseInt).toArray();
        if (end)
            t[h.get(gra)]++;
        return convert(t);
    }    

    
    //Invalid Transactions
    public List<String> invalidTransactions(String[] transactions) {
        int n = transactions.length;
        int[][] table = new int[n][5];
        for(int i=0; i<n; ++i) table[i] = construct(transactions, i);
        Arrays.sort(table, new Comparator<int[]>() {
            public int compare(int[] a, int[] b) {
                return 1000*(a[2]-b[2]) + (a[0] - b[0]);
            }
        });
        HashSet<Integer> answers = new HashSet<>();
        for(int i=0; i<n; ++i) {
            int[] arr = table[i];
            if(arr[1] > 1000) answers.add(arr[4]);
            int j = i+1;
            while(j < n && table[j][2] == arr[2] && table[j][0] <= arr[0]+60) {
                if(table[j][3] != arr[3]) {
                    answers.add(arr[4]);
                    answers.add(table[j][4]);
                }
                ++j;
            }
        }
        List<String> ans = new ArrayList<>();
        for(int i: answers) ans.add(transactions[i]);
        return ans;
    }
    private HashMap<String, Integer> names = new HashMap<>(), cities = new HashMap<>();
    private int nId=0, cId=0;
    private int[] construct(String[] strings, int i) {
        String[] pieces = strings[i].split(",");
        int time = Integer.valueOf(pieces[1]), val = Integer.valueOf(pieces[2]), name=0, city=0;
        
        if(names.containsKey(pieces[0])) name = names.get(pieces[0]);
        else {
            name = nId++;
            names.put(pieces[0], name);
        }
        
        if(cities.containsKey(pieces[3])) city = cities.get(pieces[3]);
        else {
            city = cId++;
            cities.put(pieces[3], city);
        }
        
        return new int[] { time, val, name, city, i };
    }    



    class Node {
        public int val;
        public List<Node> neighbors;
        public Node() {
            val = 0;
            neighbors = new ArrayList<Node>();
        }
        public Node(int _val) {
            val = _val;
            neighbors = new ArrayList<Node>();
        }
        public Node(int _val, ArrayList<Node> _neighbors) {
            val = _val;
            neighbors = _neighbors;
        }
    }
    //Clone Graph
    private HashMap <Node, Node> visited = new HashMap <> ();
    public Node cloneGraph(Node node) {
        if (node == null) {
            return node;
        }

        // If the node was already visited before.
        // Return the clone from the visited dictionary.
        if (visited.containsKey(node)) {
            return visited.get(node);
        }

        // Create a clone for the given node.
        // Note that we don't have cloned neighbors as of now, hence [].
        Node cloneNode = new Node(node.val, new ArrayList<>());
        // The key is original node and value being the clone node.
        visited.put(node, cloneNode);

        // Iterate through the neighbors to generate their clones
        // and prepare a list of cloned neighbors to be added to the cloned node.
        for (Node neighbor: node.neighbors) {
            cloneNode.neighbors.add(cloneGraph(neighbor));
        }
        return cloneNode;
    }    

    
    
}
