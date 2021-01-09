package Edges;

import App.GUI;
import App.Main;
import Graph.DirectedGraph;
import Vertices.Home;
import Vertices.People;
import Vertices.Phone;

import java.util.HashSet;

public class Ownership extends DirectedGraph.Edge {
    private static HashSet<Ownership> allOwnerships = new HashSet<>();
    private String date,amount; //id = ownershipID

    public Ownership(DirectedGraph.Vertex startingNode, DirectedGraph.Vertex finishingNode, String ownershipId, String date, String amount) {
        super(ownershipId, startingNode, finishingNode);
        this.date = date;
        this.amount = amount;
        allOwnerships.add(this);
        ((People)startingNode).addToOwners(getId());
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
    public static void print() {
        String[] tableColumn = {"ردیف", "از", "به" , "شناسه", "تاریخ خرید", "مبلغ"};
        String[][] data = new String[allOwnerships.size()][tableColumn.length];
        int i =0;
        for (Ownership ownership : allOwnerships) {
            People owner = (People) Main.directedGraph.getVertexByID(ownership.getStartingVertex().getId());
            DirectedGraph.Vertex x = Main.directedGraph.getVertexByID(ownership.getFinishingVertex().getId());
            data[i][0] = (i+1) + "";
            data[i][1] = owner.getFirstName() + " " + owner.getLastName();
            data[i][2] = x.getId();
            data[i][3] = ownership.getId();
            data[i][4] = ownership.getDate();
            data[i][5] = ownership.getAmount();
            i++;
        }
        GUI.showJTable("Ownerships" , tableColumn , data);
    }
}
