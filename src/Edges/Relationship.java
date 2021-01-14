package Edges;

import App.GUI;
import App.Main;
import Graph.DirectedGraph;
import Vertices.Person;

import java.util.HashSet;

public class Relationship extends DirectedGraph.Edge {

    private static HashSet<Relationship> allRelationships = new HashSet<>();
    private String relation, date; //id = from + to

    public Relationship(DirectedGraph.Vertex startingNode, DirectedGraph.Vertex finishingNode, String[] data){
        //from to = data[0] + data[1]
        super(data[0] + data[1] , startingNode, finishingNode);
        this.relation = data[2];
        this.date = data[3];
        allRelationships.add(this);
        ((Person)startingNode).addToRels(finishingNode.getId());
        ((Person)finishingNode).addToRels(startingNode.getId());
    }

    public String getRelation() {
        return relation;
    }

    public String getDate() {
        return date;
    }

    public static HashSet<Relationship> getAllRelationships() {
        return allRelationships;
    }

    public static void print() {
        String[] tableColumn = {"ردیف", "از", "به" , "نوع رابطه", "تاریخ شروع"};
        String[][] data = new String[allRelationships.size()][tableColumn.length];
        int i =0;
        for (Relationship relationship : allRelationships) {
            Person owner1 = (Person) Main.directedGraph.getVertexByID(relationship.getStartingVertex().getId());
            Person owner2 = (Person) Main.directedGraph.getVertexByID(relationship.getFinishingVertex().getId());
            data[i][0] = (i+1) + "";
            data[i][1] = owner1.getFirstName() + " " + owner1.getLastName();
            data[i][2] = owner2.getFirstName() + " " + owner2.getLastName();
            data[i][3] = relationship.getRelation();
            data[i][4] = relationship.getDate();
            i++;
        }
        GUI.showJTable("Relationships" , tableColumn , data);
    }

}
