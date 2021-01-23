import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class WeightedGraph {
    public static void main(String[] args) {
        var graph = new WeightedGraph();
        graph.addNode("A");
        graph.addNode("B");
        graph.addEdge("A", "B", 2);
    }
    
    private class Node {
        private String label;

        public Node(String label) {
            this.label = label;
        }

        @Override
        public String toString(){
            return label;
        }
    }

    private Map<String, Node> nodes = new HashMap<>();
    private Map<Node, List<Node>> adjacencyList = new HashMap<>();

    public void addNode(String label) {
        var node = new Node(label);
        nodes.putIfAbsent(label, node);
        adjacencyList.putIfAbsent(node, new ArrayList<>());
    }

    public void addEdge(String from, String to, int weight) {
        var fromNode = nodes.get(from);
        if(fromNode == null)
            throw new IllegalArgumentException();

        var toNode = nodes.get(to);
        if (toNode == null)
            throw new IllegalArgumentException();

        adjacencyList.get(fromNode).add(toNode);    
    }
}
