package Edges;
import Graph.DirectedGraph;
import java.util.HashSet;

public class Transaction extends DirectedGraph.Edge {
    private static HashSet<Transaction> allTransactions = new HashSet<>();
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

    public static HashSet<Transaction> getAllTransactions() {
        return allTransactions;
    }
}
