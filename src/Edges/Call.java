package Edges;

import App.GUI;
import App.Main;
import Graph.DirectedGraph;
import Vertices.Person;
import Vertices.Phone;
import java.util.HashSet;

public class Call extends DirectedGraph.Edge {

    private static HashSet<Call> allCalls = new HashSet<>();
    private String date , duration; // id = callId

    public Call(DirectedGraph.Vertex startingNode, DirectedGraph.Vertex finishingNode, String callId, String date, String duration) {
        super(callId, startingNode, finishingNode);
        this.date = date;
        this.duration = duration;
        allCalls.add(this);
        ((Person)Main.directedGraph.getVertexByID(((Phone)startingNode).getOwnerSsn())).addToCalls(this);
        ((Person)Main.directedGraph.getVertexByID(((Phone)finishingNode).getOwnerSsn())).addToCalls(this);
    }

    public String getDate() {
        return date;
    }

    public String getDuration() {
        return duration;
    }

    public static HashSet<Call> getAllCalls() {
        return allCalls;
    }

    public static void print() {
        String[] tableColumn = {"ردیف", "از", "به" , "شناسه تماس", "تاریخ تماس", "مدت تماس"};
        String[][] data = new String[allCalls.size()][tableColumn.length];
        int i =0;
        for (Call call : allCalls) {
            Phone number1 = (Phone) Main.directedGraph.getVertexByID(call.getStartingVertex().getId());
            Phone number2 = (Phone) Main.directedGraph.getVertexByID(call.getFinishingVertex().getId());
            Person owner1 = (Person) Main.directedGraph.getVertexByID(number1.getOwnerSsn());
            Person owner2 = (Person) Main.directedGraph.getVertexByID(number2.getOwnerSsn());
            data[i][0] = (i+1) + "";
            data[i][1] = owner1.getFirstName() + " " + owner1.getLastName();
            data[i][2] = owner2.getFirstName() + " " + owner2.getLastName();
            data[i][3] = call.getId();
            data[i][4] = call.getDate();
            data[i][5] = call.getDuration();
            i++;
        }
        GUI.showJTable("Calls" , tableColumn , data);
    }

}
