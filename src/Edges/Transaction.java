package Edges;

import App.Table;
import App.Main;
import Graph.DirectedGraph;
import Vertices.Account;
import Vertices.Person;
import java.util.HashSet;

public class Transaction extends DirectedGraph.Edge {

    private String date,amount; //id = transactionId
    private Person personFrom, personTo;
    private static HashSet<Transaction> allTransactions = new HashSet<>();


    public Transaction(DirectedGraph.Vertex startingNode, DirectedGraph.Vertex finishingNode, String[] data) {
        //transaction ID = data[2]
        super(data[2] , startingNode, finishingNode);
        this.date = data[3];
        this.amount = data[4];
        Account account1 = (Account) Main.directedGraph.getVertexByID(startingNode.getId());
        Account account2 = (Account) Main.directedGraph.getVertexByID(finishingNode.getId());
        personFrom = (Person) Main.directedGraph.getVertexByID(account1.getOwnerSsn());
        personFrom.addToTrans(data[2]);
        personTo = (Person) Main.directedGraph.getVertexByID(account2.getOwnerSsn());
        personTo.addToTrans(data[2]);
        allTransactions.add(this);
    }

    public String getDate() {
        return date;
    }
    public String getAmount() {
        return amount;
    }

    public Person getPersonFrom() {
        return personFrom;
    }

    public static HashSet<Transaction> getAllTransactions() {
        return allTransactions;
    }

    public static void print() {
        String[] tableColumn = {"ردیف", "از", "به", "شناسه" , "تاریخ", "مبلغ"};
        String[][] data = new String[allTransactions.size()][tableColumn.length];
        int i =0;
        for (Transaction transaction : allTransactions) {
            data[i][0] = (i+1) + "";
            data[i][1] = transaction.personFrom.getFirstName() + " " + transaction.personFrom.getLastName();
            data[i][2] = transaction.personTo.getFirstName() + " " + transaction.personTo.getLastName();
            data[i][3] = transaction.getId();
            data[i][4] = transaction.getDate();
            data[i][5] = transaction.getAmount();
            i++;
        }
        Table.showJTable("Transaction" , tableColumn , data);
    }

}
