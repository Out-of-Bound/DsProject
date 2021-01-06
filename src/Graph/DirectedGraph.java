package Graph;
import java.util.HashMap;
import java.util.HashSet;

public class DirectedGraph {

    // contains set of nodes
    private HashSet<Vertex> nodes = new HashSet<>();

    // set of directed edges
    private HashSet<Edge> edges = new HashSet<>();

    // maps a nodeId -> node
    private HashMap<String, Vertex> nodeMap = new HashMap<>();

    public abstract static class Vertex {
        private String id;
        private HashSet<Edge> inEdges;
        private HashSet<Edge> outEdges;

        public Vertex(String id) {
            this.id = id;
            inEdges = new HashSet<>();
            outEdges = new HashSet<>();
        }

        public Vertex(Vertex anotherNode) {
            this.id = anotherNode.id;
            this.inEdges = anotherNode.inEdges;
            this.outEdges = anotherNode.outEdges;
        }

        public String getId() {
            return id;
        }

        public HashSet<Edge> getInEdges() {
            return inEdges;
        }

        public HashSet<Edge> getOutEdges() {
            return outEdges;
        }

    }

    public static class Edge {
        private String id;
        private Vertex startingVertex;
        private Vertex finishingVertex;

        public Edge(String id , Vertex startingVertex, Vertex finishingVertex) {
            this.id = id;
            this.startingVertex = startingVertex;
            this.finishingVertex = finishingVertex;
        }

        @Override
        public boolean equals(Object obj) {
            Edge e = (Edge) obj;
            return e.finishingVertex == finishingVertex && e.startingVertex == startingVertex;
        }

        public String getId() {
            return id;
        }

        public Vertex getStartingVertex() {
            return startingVertex;
        }

        public Vertex getFinishingVertex() {
            return finishingVertex;
        }
    }

    public void addVertex(Vertex nodeToAdd) {
        nodes.add(nodeToAdd);
        nodeMap.put(nodeToAdd.id, nodeToAdd);
    }

    public void addEdges(Edge edgeToAdd) {
        edges.add(edgeToAdd);
        edgeToAdd.startingVertex.outEdges.add(edgeToAdd);
        edgeToAdd.finishingVertex.inEdges.add(edgeToAdd);
    }

   // public void setNodeMap(HashMap<String, Vertex> nodeMap) {this.nodeMap = nodeMap;}

    public HashMap<String, Vertex> getNodeMap() {
        return nodeMap;
    }

    public Vertex getVertexByID(String id){
        if (nodeMap.containsKey(id))
            return nodeMap.get(id);
        return null;
    }

    public HashSet<Vertex> getAllNodes() {
        return nodes;
    }

    public HashSet<Edge> getEdges() {
        return edges;
    }

}
