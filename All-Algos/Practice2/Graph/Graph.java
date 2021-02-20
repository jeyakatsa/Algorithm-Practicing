import org.graalvm.compiler.serviceprovider.SpeculationReasonGroup.SpeculationContextObject;

public class Graph {
    public static void main(String[] args) {
        Graph g = new Graph(4);
        g.addEdge(0, 1);
        g.addEdge(0, 2);
        g.addEdge(1, 3);
        g.addEdge(2, 3);
        System.out.println("Graph1:");
        g.printGraph();
        System.out.println("DFS traversal of Graph1 : " + bfs(g));
        System.out.println();

        Graph g2 = new Graph(4);
        g2.addEdge(0, 1);
        g2.addEdge(1, 2);
        g2.addEdge(2, 3);
        g2.addEdge(3, 0);
        g2.printGraph();
        System.out.println(detectCycle(g2));
    }


    int vertices; //Total number of vertices in graph
    DoublyLinkedList<Integer> adjacencyList[]; //An array of linked lists to store adjacent vertices

    @SuppressWarnings("unchecked")
    public Graph(int vertices) {
        this.vertices = vertices;
        adjacencyList = new DoublyLinkedList[vertices];

        for(int i = 0; i < vertices; i++){
            adjacencyList[i] = new DoublyLinkedList<>();
        }
    }

    void addEdge(int source, int destination) {
        if (source < vertices && destination < vertices) {
            this.adjacencyList[source].insertAtEnd(destination);

            //for undirected graph uncomment line below
            //this.adjacencyList[destination].insertAtEnd(source);
        }
    } //Adds an Edge from source vertex to destination vertex

    public void printGraph() {
        System.out.println(">>Adjacency List of Directed Graph<<");
        for(int i = 0; i < vertices; i++) {
            if (adjacencyList[i] != null){
                System.out.println("|" + "| => ");

                DoublyLinkedList<Integer>.Node temp = adjacencyList[i].getHeadNode();
                while (temp != null){
                    System.out.print("[" + temp.data + "] ->");
                    temp = temp.nextNode;
                }
                System.out.println("null");
            }
            else{
                System.out.println("|" + i + "| =>" + "null");
            }
        }
    }

    //Breadth First Traversal of Graph g
    public static String bfs(Graph g) {
        String result = "";
        //Checking if the graph has no vertices
        if (g.vertices < 1) {
            return result;
        }

        //Boolean Array to hold the history of visited nodes (by default-false)
        boolean[] visited = new boolean[g.vertices];

        for(int i = 0; i < g.vertices; i++) {
            //Checking whether the node is visited or not
            if(!visited[i]){
                result = result + bfsVisit(g, i, visited);
            }
        }
        return result;
    }
    
    public static String bfsVisit(Graph g, int source, boolean[] visited) {
        String result  = "";

        //Create Queue for Breadth First Traversal and enqueue source in it
        Queue<Integer> queue = new Queue<>(g.vertices);

        queue.enqueue(source);
        visited[source] = true;

        //Traverse while queue is not empty
        while (!queue.isEmpty()) {

            //Dequeue a vertex/node from queue and add it to result
            int current_node = queue.dequeue();

            result += String.valueOf(current_node);

            //Get adjacent vertices to the current_node from the array,
            //and if they are not already visited then enqueue them in Queue
            DoublyLinkedList<Integer>.Node temp = null;
            if(g.adjacencyList[current_node] != null)
                temp = g.adjacencyList[current_node].headNode;

            while(temp != null) {
                if(!visited[temp.data]) {
                    queue.enqueue(temp.data);
                    visited[temp.data] = true; //Visit the current Node
                }
                temp = temp.nextNode;
            }    
        }
        return result;
    }

    //Depth First Search of Graph g
    public static String dfs(Graph g) {
        String result = "";
        //Typical "base case" if null then return...
        if(g.vertices < 1) {
            return result;
        }
        
        boolean[] visited = new boolean[g.vertices];

        for(int i = 0; i < g.vertices; i++) {
            //Checking whether the node is visited or not
            if(!visited[i]) {
                result = result + dfsVisit(g, i, visited);
            }
        }
        return result;
    }

    public static String dfsVisit(Graph g, int source, boolean[] visited) {
        String result = "";
         
        //Create Stack
        Stack<Integer> stack = new Stack<Integer>(g.vertices);

        stack.push(source);

        //Traverse while stack is not empty
        while(!stack.isEmpty()) {

            //Pop a vertex/node from stack and add it to the result
            int current_node = stack.pop();
            result += String.valueOf(current_node);

            //Get adjacent vertices to the current_node from the array,
            //and if they are not already visited then push them in the stack
            DoublyLinkedList<Integer>.Node temp = null;
            if(g.adjacencyList[current_node] !=null)
                temp = g.adjacencyList[current_node].headNode;

            while(temp != null) {

                if(!visited[temp.data]) {
                    stack.push(temp.data);
                }
                temp = temp.nextNode;
            }
            //Visit the node
            visited[current_node] = true;  
        }
        return result;
    }

    public static boolean detectCycle(Graph g){
        int num_of_vertices = g.vertices;

        //Boolean Array to hold the history of visited nodes (by default-false)
        boolean[] visited = new boolean [num_of_vertices];
        //Holds a flag if the node is currently in the Stack or not (by default-false)
        boolean[] stackFlag = new boolean[num_of_vertices];

        for (int i = 0; i < num_of_vertices; i++) {
            //Check cyclic on each node
            if (cyclic(g, i, visited, stackFlag)) {
                return true;
            }
        }
        return false;
    }

    public static boolean cyclic(Graph g, int v, boolean[] visited, boolean[] stackFlag) {
        //if node is currently in stack that means we have found a cycle
        if(stackFlag[v])
            return true;

        //if it is already visited (and not in stack) then there is no cycle
        if (visited[v])
            return false;
            
        visited[v] = true;
        stackFlag[v] = true;
        
        //check adjacency list of the node
        DoublyLinkedList<Integer>.Node temp = null;
        if (g.adjacencyList[v] != null)
            temp = g.adjacencyList[v].headNode;

        while (temp != null) {
            //run cyclic function recursively on each outgoing path
            if(cyclic(g, temp.data, visited, stackFlag)) {
                return true;
            }
            temp = temp.nextNode;
        }
        stackFlag[v] = false;

        return false;
    }
}