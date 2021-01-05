package Edges;

import Graph.DirectedGraph;

import java.util.HashMap;
import java.util.HashSet;

public class Call extends DirectedGraph.Edge {
    private static HashSet<Call> allCalls = new HashSet<>();
    private String date , duration; // id = callId

    public Call(DirectedGraph.Vertex startingNode, DirectedGraph.Vertex finishingNode, String callId, String date, String duration) {

        super(callId, startingNode, finishingNode);
        this.date = date;
        this.duration = duration;
        allCalls.add(this);
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
}
