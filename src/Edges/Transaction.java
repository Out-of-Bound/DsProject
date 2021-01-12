package Edges;

import App.GUI;
import App.Main;
import Graph.DirectedGraph;
import Vertices.Account;
import Vertices.People;

import java.util.HashSet;

public class Transaction extends DirectedGraph.Edge {
    private static HashSet<Transaction> allTransactions = new HashSet<>();
    private String date,amount; //id = transactionId

    public Transaction(DirectedGraph.Vertex startingNode, DirectedGraph.Vertex finishingNode, String transactionId, String date, String amount) {
        super(transactionId , startingNode, finishingNode);
        this.date = date;
        this.amount = amount;
        allTransactions.add(this);
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

    public static void print() {
        String[] tableColumn = {"ردیف", "از", "به", "ID" , "تاریخ", "مقدار"};
        String[][] data = new String[allTransactions.size()][tableColumn.length];
        int i =0;
        for (Transaction transaction : allTransactions) {

            Account account1 = (Account) Main.directedGraph.getVertexByID(transaction.getStartingVertex().getId());
            String ownerSSn1 = account1.getOwnerSsn();
            People people1 = (People) Main.directedGraph.getVertexByID(ownerSSn1);
            Account account2 = (Account) Main.directedGraph.getVertexByID(transaction.getFinishingVertex().getId());
            String ownerSSn2 = account2.getOwnerSsn();
            People people2 = (People) Main.directedGraph.getVertexByID(ownerSSn2);

            data[i][0] = (i+1) + "";
            data[i][1] = people1.getFirstName() + " " + people1.getLastName();
            data[i][2] = people2.getFirstName() + " " + people2.getLastName();
            data[i][3] = transaction.getId();
            data[i][4] = transaction.getDate();
            data[i][5] = transaction.getAmount();
            i++;
        }
        GUI.showJTable("Relationships" , tableColumn , data);
    }
}
