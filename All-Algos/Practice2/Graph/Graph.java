import java.util.Arrays;

public class Graph {
    public static void main(String[] args) {

        Graph g=new Graph(5);
        g.addEdge(0,1);
        g.addEdge(0,2);
        g.addEdge(1,3); 
        g.addEdge(3,4); 
        g.addEdge(1,4);
        System.out.println(findMin(g, 0, 2));
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
                    System.out.print("[" + temp.data + "] -> ");
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

    //find "mother vertex" in directed graph
    //if vertex does not point to previous vertex, return vertex
    public static int findMotherVertex(Graph g) {
        //visited[] is used for DFS. Initially all are
        //initialized as not visited
        boolean[] visited = new boolean[g.vertices];
        Arrays.fill(visited, false);

        //To store last finished vertex (or mother vertex)
        int lastV = 0;

        for (var i = 0; i < g.vertices; i++) {
            if(visited[i] == false) {
                DFS(g, i, visited);
                lastV = i;
            }
        }

        //If there exist mother vertex (or vertices) in given
        //graph, then lastV must be one (or one of them)

        // Now check if lastV is actually a mother vertex (or graph
        // has a mother vertex). We basically check if every vertex
        // is reachable from lastV or not.

        // Reset all values in visited[] as false and do
        // DFS beginning from lastV to check if all vertices are
        // reachable from it or not.
        Arrays.fill(visited, false);
        DFS(g, lastV, visited);

        for(int i = 0; i < visited.length; i++) {
            if(visited[i] == false) {
                return -1;
            }
        }

        return lastV;
    }

    //A recursive function to print DFS starting from v
    public static void DFS(Graph g, int node, boolean[] visited){

        visited[node] = true;
        DoublyLinkedList<Integer>.Node temp = null;
        if(g.adjacencyList[node] != null)
            temp = g.adjacencyList[node].headNode;

        while(temp != null) {
            if(visited[temp.data]){
                temp = temp.nextNode;
            }
            else {
                visited[temp.data] = true;
                DFS(g, temp.data, visited);
                temp = temp.nextNode;
            }
        }    
    }

    public static int numEdges(Graph g) {

        //For undirected graph, just sum up the size of all the
        //adjacency lists for each vertex and then divide it by 2.
        //It will give us total number ot edges in graph.
        int sum = 0;

        for (int i = 0; i < g.vertices; i++) {

            DoublyLinkedList<Integer>.Node temp = null;
            if (g.adjacencyList[i] != null)
                temp = g.adjacencyList[i].headNode;
            
            while(temp != null) {
                sum++;
                temp = temp.nextNode;
            }    
        }
        return sum/2;
    }

    //Check if a path ecists between two vertices
    //Perfrom DFS Traversal starting from source and if you reach destination
    //then it means that there exist a path between source and destination
    //so return true and if you traverse the graph but can't reach destination
    //then simply return false.
    public static boolean checkPath(Graph g, int source, int destination) {
        if (source == destination) {
            return true;
        }

        //Boolean Array to hold the history of visited nodes (by default-false)
        //Make a node visited whenever you push it into stack
        boolean[] visited = new boolean[g.vertices];

        //Create Stack
        Stack<Integer> stack = new Stack<>(g.vertices);

        stack.push(source);
        visited[source] = true;

        //traverse while stack is not empty
        while (!stack.isEmpty()) {

            //Pop a vertex/node from stack
            int current_node = stack.pop();

            //Get adjacent vertices to the current_node from the array,
            //and if only push unvisited adjacent vertices into stack
            //Before pushing into stack, check if it's the destination.
            DoublyLinkedList<Integer>.Node temp = null;
            if(g.adjacencyList[current_node] != null)
                temp = g.adjacencyList[current_node].headNode;
            
            while (temp != null) {
                if(!visited[temp.data]) {
                    if(temp.data == destination) {
                        return true;
                    }
                    stack.push(temp.data);
                    visited[temp.data] = true;
                }
                temp = temp.nextNode;
            }    
        }
        return false;
    }

    public static boolean isTree(Graph g){
        int root = 0;
        //Boolean Array to hold the history of visited nodes (by default-false)
        //Make a node visited whenever you enqueue it into queue
        boolean[] visited = new boolean[g.vertices];

        //Create Queue for Breadth First Traversal and enqueue root in it
        Queue<Integer> queue = new Queue<>(g.vertices);

        queue.enqueue(root);
        visited[root] = true;

        //Store the number of visited nodes to check at end if all are visited
        int numberOfVisited = 1;

        //traverse while queue is not empty
        while (!queue.isEmpty()) {

            //Dequeue a vertex/node from queue and add it to result
            int current_node = queue.dequeue();

            //Get adjacent vertices to the current_node from the array,
            //and if they are not already visited then enqueue them in the Queue
            DoublyLinkedList<Integer>.Node temp = null;
            if(g.adjacencyList[current_node] != null)
                temp = g.adjacencyList[current_node].headNode;

            while (temp != null) {
                if(!visited[temp.data]) {
                    queue.enqueue(temp.data);
                    visited[temp.data] = true;
                    numberOfVisited++;
                }
                else
                    return false;
                temp = temp.nextNode;
            }    
        }
        //If all vertices are visited then return true
        if (numberOfVisited == g.vertices)
            return true;

        return false;
    }

    //Find Length of Shorted Path.
    public static int findMin(Graph g, int source, int destination) {
        //for loop to traverse BFS
        //Max-value has to be integrated to check if vertices 
        if (source == destination) {
            return 0;
        }

        int num_of_vertices = g.vertices;

        //Boolean Array to hold the history of visited nodes (by default-false)
        //Make a node visited whenever you enqueue it into queue
        boolean[] visited = new boolean[num_of_vertices];

        //For keeping track of distance of current_node from source
        int[] distance = new int[num_of_vertices];

        //Create Queue for Breadth First Traversal and enqueue source in it
        Queue<Integer> queue = new Queue<Integer>(num_of_vertices);

        queue.enqueue(source);
        visited[source] = true;

        //Traverse while queue is not empty
        while (!queue.isEmpty()) {
            //dequeue a vertex/node from queue
            int current_node = queue.dequeue();

            //Get adjacent vertices to the current_node from the array,
            //and if they are not already visited then enqueue them in the Queue
            //and also update their distance from source by adding 1 in current_nodes's distance
            DoublyLinkedList<Integer>.Node temp = null;
            if(g.adjacencyList[current_node] != null)
                temp = g.adjacencyList[current_node].headNode;

            while (temp != null) {
                if(!visited[temp.data]) {
                    queue.enqueue(temp.data);
                    visited[temp.data] = true;
                    distance[temp.data] = distance[current_node] + 1;
                }
                if (temp.data == destination) {
                    return distance[destination];
                }
                temp = temp.nextNode;
            }    
        }   
        return -1;
    }
}