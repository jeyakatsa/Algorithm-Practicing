public class Graph {
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

    void printGraph(); //Prints Graph (Adjacency List)

    void addEdge(int source, int destination) {
        if (source < vertices && destination < vertices) {
            this.adjacencyList[source].insertAtEnd(destination);

            //for undirected graph uncomment line below
            //this.adjacencyList[destination].insertAtEnd(source);
        }
    } //Adds an Edge from source vertex to destination vertex
}