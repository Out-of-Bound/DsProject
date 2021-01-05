package Edges;

import Graph.DirectedGraph;

public class Transaction extends DirectedGraph.Edge {
    private String date,amount; //id = transactionId

    public Transaction(DirectedGraph.Vertex startingNode, DirectedGraph.Vertex finishingNode,
                      String transactionId, String date, String amount) {
        super(transactionId , startingNode, finishingNode);
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
