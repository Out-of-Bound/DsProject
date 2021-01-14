package Edges;

import App.GUI;
import App.Main;
import Graph.DirectedGraph;
import Vertices.Account;
import Vertices.Person;
import java.util.HashSet;

public class Transaction extends DirectedGraph.Edge {
    private static HashSet<Transaction> allTransactions = new HashSet<>();
    private String date,amount; //id = transactionId

    public Transaction(DirectedGraph.Vertex startingNode, DirectedGraph.Vertex finishingNode, String[] data) {
        //transaction ID = data[2]
        super(data[2] , startingNode, finishingNode);
        this.date = data[3];
        this.amount = data[4];
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
        String[] tableColumn = {"ردیف", "از", "به", "شناسه" , "تاریخ", "مبلغ"};
        String[][] data = new String[allTransactions.size()][tableColumn.length];
        int i =0;
        for (Transaction transaction : allTransactions) {
            Account account1 = (Account) Main.directedGraph.getVertexByID(transaction.getStartingVertex().getId());
            Account account2 = (Account) Main.directedGraph.getVertexByID(transaction.getFinishingVertex().getId());
            Person person1 = (Person) Main.directedGraph.getVertexByID(account1.getOwnerSsn());
            Person person2 = (Person) Main.directedGraph.getVertexByID(account2.getOwnerSsn());

            data[i][0] = (i+1) + "";
            data[i][1] = person1.getFirstName() + " " + person1.getLastName();
            data[i][2] = person2.getFirstName() + " " + person2.getLastName();
            data[i][3] = transaction.getId();
            data[i][4] = transaction.getDate();
            data[i][5] = transaction.getAmount();
            i++;
        }
        GUI.showJTable("Transaction" , tableColumn , data);
    }
}
