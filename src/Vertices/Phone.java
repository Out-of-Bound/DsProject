package Vertices;
import Graph.DirectedGraph;
import java.util.HashSet;

public class Phone extends DirectedGraph.Vertex {
    private static HashSet<Phone> allPhones = new HashSet<>();
    private String ownerSsn, operatorName; //id = number

    public Phone(String ownerSsn, String number, String operatorName) {
        super(number);
        this.ownerSsn = ownerSsn;
        this.operatorName = operatorName;
        allPhones.add(this);
    }

    public String getOwnerSsn() {
        return ownerSsn;
    }

    public String getOperatorName() {
        return operatorName;
    }

    public static HashSet<Phone> getAllPhones() {
        return allPhones;
    }


}
