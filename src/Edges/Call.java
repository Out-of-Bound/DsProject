package Edges;

import Graph.DirectedGraph;

public class Call extends DirectedGraph.Edge {
    private String date , duration; // id = callId

    public Call(DirectedGraph.Vertex startingNode, DirectedGraph.Vertex finishingNode, String callId, String date, String duration) {

        super(callId, startingNode, finishingNode);
        this.date = date;
        this.duration = duration;
    }

    public String getDate() {
        return date;
    }

    public String getDuration() {
        return duration;
    }
}
