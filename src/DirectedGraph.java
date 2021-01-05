import org.jgrapht.Graph;

import java.util.HashMap;
import java.util.HashSet;

/*The nodes are also referred as vertex and arcs as Directed edges*/

public class DirectedGraph {
    // node class consists of node name a.k.a id of node ,set of directed edges a.k.a arcs

    // contains set of nodes
    private HashSet<NodeClass> nodes = new HashSet<>();

    // set of directed edges
    private HashSet<edgeContents> edges = new HashSet<>();

    // maps a nodeId -> node
    private HashMap<Integer, NodeClass> nodeMap = new HashMap<>();

    public static class NodeClass {
        int id;
        HashSet<edgeContents> edges;

        public NodeClass(int name) {
            id = name;
            edges = new HashSet<>();
        }

        // copy constructor
        public NodeClass(NodeClass anotherNode) {
            this.id = anotherNode.id;
            this.edges = anotherNode.edges;
        }
    }

    // definition of arc a.k.a edge
    public static class edgeContents {
        NodeClass startingNode;
        NodeClass finishingNode;

        public edgeContents(NodeClass startingNode, NodeClass finishingNode) {
            this.startingNode = startingNode;
            this.finishingNode = finishingNode;
        }

        @Override
        public boolean equals(Object obj) {
            edgeContents e = (edgeContents) obj;
            return e.finishingNode == finishingNode && e.startingNode == startingNode;
        }
    }

    public static void addNode(DirectedGraph g, int nodeId) {
        NodeClass nodeToAdd = new NodeClass(nodeId);
        g.nodes.add(nodeToAdd);
        g.nodeMap.put(nodeId, nodeToAdd);
    }

    public void addNode(DirectedGraph g, NodeClass nodeToAdd) {
        g.nodes.add(nodeToAdd);
        g.nodeMap.put(nodeToAdd.id, nodeToAdd);
    }

    public void addEdges(DirectedGraph g, NodeClass startingNode, NodeClass finishingNode) {
        edgeContents edge = new edgeContents(startingNode, finishingNode);
        g.edges.add(edge);
        startingNode.edges.add(edge);
        nodes.add(startingNode);
    }

    public HashMap<Integer, NodeClass> getNodeMap() {
        return nodeMap;
    }

    public void setNodeMap(HashMap<Integer, NodeClass> nodeMap) {
        this.nodeMap = nodeMap;
    }

    public NodeClass getParticularNode(int id) {
        if (nodeMap.containsKey(id))
            return nodeMap.get(id);
        return null;
    }

    public HashSet<NodeClass> getAllNodes() {
        return nodes;
    }

    public HashSet<edgeContents> getEdges() {
        return edges;
    }
}
