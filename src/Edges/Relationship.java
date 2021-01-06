package Edges;
import Graph.DirectedGraph;
import java.util.HashSet;

public class Relationship extends DirectedGraph.Edge {
    private static HashSet<Relationship> allRelationships = new HashSet<>();
    private String relation, date; //id = from + to

    public Relationship(DirectedGraph.Vertex startingNode, DirectedGraph.Vertex finishingNode, String fromTo ,String relation, String date){
        super(fromTo, startingNode, finishingNode);
        this.relation = relation;
        this.date = date;
        allRelationships.add(this);
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
}
