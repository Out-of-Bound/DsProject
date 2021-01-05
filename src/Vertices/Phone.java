package Vertices;
import Graph.DirectedGraph;

public class Phone extends DirectedGraph.Vertex {
    private String ownerSsn, operatorName; //id = number

    public Phone(String ownerSsn, String number, String operatorName) {
        super(number);
        this.ownerSsn = ownerSsn;
        this.operatorName = operatorName;
    }

    public String getOwnerSsn() {
        return ownerSsn;
    }

    public String getOperatorName() {
        return operatorName;
    }
}
