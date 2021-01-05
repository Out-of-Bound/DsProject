

import java.util.HashMap;
import java.util.HashSet;

/*The nodes are also referred as vertex and arcs as Directed edges*/

public class DirectedGraph {
    // node class consists of node name a.k.a id of node ,set of directed edges a.k.a arcs

    // contains set of nodes
    private HashSet<Vertex> nodes = new HashSet<>();

    // set of directed edges
    private HashSet<Edge> edges = new HashSet<>();

    // maps a nodeId -> node
    private HashMap<String, Vertex> nodeMap = new HashMap<>();

    public static class Vertex {
        private String id;
        private HashSet<Edge> edges;

        public Vertex(String id) {
            this.id = id;
            edges = new HashSet<>();
        }

        public Vertex(Vertex anotherNode) {
            this.id = anotherNode.id;
            this.edges = anotherNode.edges;
        }

        public String getId() {
            return id;
        }

        public HashSet<Edge> getEdges() {
            return edges;
        }
    }

    // definition of arc a.k.a edge
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

    public void addEdges(String id, Vertex startingNode, Vertex finishingNode) {
        if (!nodes.contains(startingNode))
            addVertex(startingNode);

        if (!nodes.contains(finishingNode))
            addVertex(finishingNode);

        Edge edge = new Edge(id , startingNode, finishingNode);
        edges.add(edge);
        startingNode.edges.add(edge);
    }

    public HashMap<String, Vertex> getNodeMap() {
        return nodeMap;
    }

    public void setNodeMap(HashMap<String, Vertex> nodeMap) {
        this.nodeMap = nodeMap;
    }

    public Vertex getParticularNode(int id) {
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
