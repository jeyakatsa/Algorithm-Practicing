public class Graph {
    public static void main(String[] args) {
        Graph g = new Graph(5);
        g.addEdge(0, 1);
        g.addEdge(0, 2);
        g.addEdge(1, 3);
        g.addEdge(2, 3);
        System.out.println("Graph1:");
        g.printGraph();
        System.out.println("BFS traversal of Graph1 : " + bfs(g));
        System.out.println();

        Graph g2 = new Graph(5);
        g2.addEdge(0, 1);
        g2.addEdge(0, 4);
        g2.addEdge(1, 2);
        g2.addEdge(3, 4);
        System.out.println("Graph1: ");
        g2.printGraph();
        System.out.println("BFS traversal of Graph1 : " + bfs(g2));
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
}