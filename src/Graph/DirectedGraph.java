package Graph;

import java.util.HashMap;
import java.util.HashSet;

public class DirectedGraph {

    // contains set of vertices
    private HashSet<Vertex> vertices = new HashSet<>();
    public HashSet<Vertex> getVertices() {
        return vertices;
    }

    // set of directed edges
    private HashSet<Edge> edges = new HashSet<>();
    public HashSet<Edge> getEdges() {
        return edges;
    }

    // maps a vertexId -> vertex
    private HashMap<String, Vertex> verticesMap = new HashMap<>();
    public HashMap<String, Vertex> getVerticesMap() {
        return verticesMap;
    }

    private HashMap<String , Edge> edgeHashMap = new HashMap<>();

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
        vertices.add(nodeToAdd);
        verticesMap.put(nodeToAdd.id, nodeToAdd);
    }

    public void addEdges(Edge edgeToAdd) {
        edges.add(edgeToAdd);
        edgeHashMap.put(edgeToAdd.getId() , edgeToAdd);
        edgeToAdd.startingVertex.outEdges.add(edgeToAdd);
        edgeToAdd.finishingVertex.inEdges.add(edgeToAdd);
    }

    public Vertex getVertexByID(String id){
        if (verticesMap.containsKey(id))
            return verticesMap.get(id);

        System.out.println("this Vertex id notFound: " + id);
        return null;
    }

    public Edge getEdgeByID(String id){
        if (edgeHashMap.containsKey(id))
            return edgeHashMap.get(id);

        System.out.println("this edge ID notFound: " + id);
        return null;
    }

}
