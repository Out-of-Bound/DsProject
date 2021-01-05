package Edges;

import Graph.DirectedGraph;
public class Ownership extends DirectedGraph.Edge {
    private String date,amount; //id = ownershipID

    public Ownership(DirectedGraph.Vertex startingNode, DirectedGraph.Vertex finishingNode,
                     String ownershipId, String date, String amount) {
        super(ownershipId, startingNode, finishingNode);
        this.date = date;
        this.amount = amount;
    }

    public String getDate() {
        return date;
    }

    public String getAmount() {
        return amount;
    }
}
