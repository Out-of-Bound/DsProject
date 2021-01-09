package Edges;

import Graph.DirectedGraph;
import java.util.HashSet;

public class Ownership extends DirectedGraph.Edge {
    private static HashSet<Ownership> allOwnerships = new HashSet<>();
    private String date,amount; //id = ownershipID

    public Ownership(DirectedGraph.Vertex startingNode, DirectedGraph.Vertex finishingNode, String ownershipId, String date, String amount) {
        super(ownershipId, startingNode, finishingNode);
        this.date = date;
        this.amount = amount;
        allOwnerships.add(this);
    }

    public String getDate() {
        return date;
    }

    public String getAmount() {
        return amount;
    }

    public static HashSet<Ownership> getAllOwnerships() {
        return allOwnerships;
    }
}
