import java.util.*;
import java.util.function.BiFunction;

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

    //Maximum Number of Events That Can Be Attended
    public int maxEvents(int[][] A) {
        PriorityQueue<Integer> pq = new PriorityQueue<Integer>();
        Arrays.sort(A, (a, b) -> Integer.compare(a[0], b[0]));
        int i = 0, res = 0, n = A.length;
        for (int d = 1; d <= 100000; ++d) {
            while (!pq.isEmpty() && pq.peek() < d)
                pq.poll();
            while (i < n && A[i][0] == d)
                pq.offer(A[i++][1]);
            if (!pq.isEmpty()) {
                pq.poll();
                ++res;
            }
        }
        return res;
    } 



    //Candy Crush
    public int[][] candyCrush(int[][] board) {
        int R = board.length, C = board[0].length;
        boolean todo = false;
        for (int r = 0; r < R; ++r) {
            for (int c = 0; c + 2 < C; ++c) {
                int v = Math.abs(board[r][c]);
                if (v != 0 && v == Math.abs(board[r][c+1]) && v == Math.abs(board[r][c+2])) {
                    board[r][c] = board[r][c+1] = board[r][c+2] = -v;
                    todo = true;
                }
            }
        }
        for (int r = 0; r + 2 < R; ++r) {
            for (int c = 0; c < C; ++c) {
                int v = Math.abs(board[r][c]);
                if (v != 0 && v == Math.abs(board[r+1][c]) && v == Math.abs(board[r+2][c])) {
                    board[r][c] = board[r+1][c] = board[r+2][c] = -v;
                    todo = true;
                }
            }
        }

        for (int c = 0; c < C; ++c) {
            int wr = R - 1;
            for (int r = R-1; r >= 0; --r)
                if (board[r][c] > 0)
                    board[wr--][c] = board[r][c];
            while (wr >= 0)
                board[wr--][c] = 0;
        }

        return todo ? candyCrush(board) : board;
    }

    //Car Fleet
    public int carFleet(int target, int[] pos, int[] speed) {
        Map<Integer, Double> m = new TreeMap<>(Collections.reverseOrder());
        for (int i = 0; i < pos.length; ++i)
            m.put(pos[i], (double)(target - pos[i]) / speed[i]);
        int res = 0; double cur = 0;
        for (double time : m.values()) {
            if (time > cur) {
                cur = time;
                res++;
            }
        }
        return res;
    }


    //Search Suggestions System
    int lower_bound(String[] products, int start, String word) {
        int i = start, j = products.length, mid;
        while (i < j) {
            mid = (i + j) / 2;
            if (products[mid].compareTo(word) >= 0)
                j = mid;
            else
                i = mid + 1;
        }
        return i;
    }
    public List<List<String>> suggestedProducts(String[] products, String searchWord) {
        Arrays.sort(products);
        List<List<String>> result = new ArrayList<>();
        int start = 0, bsStart = 0, n = products.length;
        String prefix = new String();
        for (char c : searchWord.toCharArray()) {
            prefix += c;

            // Get the starting index of word starting with `prefix`.
            start = lower_bound(products, bsStart, prefix);

            // Add empty vector to result.
            result.add(new ArrayList<>());

            // Add the words with the same prefix to the result.
            // Loop runs until `i` reaches the end of input or 3 times or till the
            // prefix is same for `products[i]` Whichever comes first.
            for (int i = start; i < Math.min(start + 3, n); i++) {
                if (products[i].length() < prefix.length() || !products[i].substring(0, prefix.length()).equals(prefix))
                    break;
                result.get(result.size() - 1).add(products[i]);
            }

            // Reduce the size of elements to binary search on since we know
            bsStart = Math.abs(start);
        }
        return result;
    }  

    //Subsets
    public List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> output = new ArrayList<>();
        output.add(new ArrayList<Integer>());
    
        for (int num : nums) {
          List<List<Integer>> newSubsets = new ArrayList<>();
          for (List<Integer> curr : output) {
            newSubsets.add(new ArrayList<Integer>(curr){{add(num);}});
          }
          for (List<Integer> curr : newSubsets) {
            output.add(curr);
          }
        }
        return output;
    }    


    //Minimum Cost to Connect Sticks
    public int connectSticks(int[] sticks) {
        int totalCost = 0;
 
        PriorityQueue<Integer> pq = new PriorityQueue<>();
 
        // add all sticks to the min heap.
        for (int stick : sticks) {
            pq.add(stick);
        }
 
        // combine two of the smallest sticks until we are left with just one.
        while (pq.size() > 1) {
            int stick1 = pq.remove();
            int stick2 = pq.remove();
            
            int cost = stick1 + stick2;
            totalCost += cost;
            
            pq.add(stick1 + stick2);
        }
 
        return totalCost;
    }    


    //Invalid Transactions
    public List<String> invalidTransactions(String[] transactions) {
        int n = transactions.length;
        List<String> res = new ArrayList<>();
        if (n == 0) {
            return res;
        }
        String[] names = new String[n];
        int[] times = new int[n];
        int[] amounts = new int[n];
        String[] citys = new String[n];
        boolean[] add = new boolean[n];
        for (int i = 0; i < transactions.length; i++) {
            String[] subs = transactions[i].split(",");
            names[i] = subs[0];
            times[i] = Integer.parseInt(subs[1]);
            amounts[i] = Integer.parseInt(subs[2]);
            citys[i] = subs[3];
        }
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                if (names[i].equals(names[j]) && Math.abs(times[i] - times[j]) <= 60 && !citys[i].equals(citys[j])) {
                    add[i] = true;
                    add[j] = true;
                }
            }
            if (amounts[i] > 1000) {
                add[i] = true;
            }
            if (add[i]) {
                res.add(transactions[i]);
            }
        }
        return res;
    } 



    //Evaluate Reverse Polish Notation
    public int evalRPN(String[] tokens) {
        
        Stack<Integer> stack = new Stack<>();
        
        for (String token : tokens) {
            
            if (!"+-*/".contains(token)) {
                stack.push(Integer.valueOf(token));
                continue;
            }
            
            int number2 = stack.pop();
            int number1 = stack.pop();
            
            int result = 0;
            
            switch (token) {
                case "+":
                    result = number1 + number2;
                    break;
                case "-":
                    result = number1 - number2;
                    break;
                case "*":
                    result = number1 * number2;
                    break;
                case "/":
                    result = number1 / number2;
                    break;
            }
            
            stack.push(result);
            
        }
        
        return stack.pop();
    }       



    
}
