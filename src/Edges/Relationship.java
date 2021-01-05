package Edges;



public class Relationship extends DirectedGraph.Edge {
    private String relation, date; //id = from + to

    public Relationship(DirectedGraph.Vertex startingNode, DirectedGraph.Vertex finishingNode,
                        String fromTo ,String relation, String date) {
        super(fromTo, startingNode, finishingNode);
        this.relation = relation;
        this.date = date;
    }

}
