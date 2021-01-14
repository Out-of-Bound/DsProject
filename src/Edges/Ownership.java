package Edges;

import App.GUI;
import App.Main;
import Graph.DirectedGraph;
import Vertices.Person;

import java.util.HashSet;

public class Ownership extends DirectedGraph.Edge {
    private static HashSet<Ownership> allOwnerships = new HashSet<>();
    private String date,amount; //id = ownershipID

    public Ownership(DirectedGraph.Vertex startingNode, DirectedGraph.Vertex finishingNode, String[] data) {
        //ownershipId = data[2];
        super(data[2], startingNode, finishingNode);
        this.date = data[3];
        this.amount = data[4];
        allOwnerships.add(this);
        ((Person)startingNode).addToOwners(getId());
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
            Person owner = (Person) Main.directedGraph.getVertexByID(ownership.getStartingVertex().getId());
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
