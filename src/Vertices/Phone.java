package Vertices;

public class Phone extends DirectedGraph.Vertex {
    private String ownerSsn, operatorName; //id = number

    public Phone(String ownerSsn, String number, String operatorName) {
        super(number);
        this.ownerSsn = ownerSsn;
        this.operatorName = operatorName;
    }
}
