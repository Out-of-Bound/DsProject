package Vertices;
import Graph.DirectedGraph;
import java.util.HashSet;

public class Home extends DirectedGraph.Vertex {
    private static HashSet<Home> allHomes = new HashSet<>();
    private String ownerSsn, price ,size , address; //id = postalCode

    public Home(String ownerSsn, String price, String postalCode, String size, String address) {
        super(postalCode);
        this.ownerSsn = ownerSsn;
        this.price = price;
        this.size = size;
        this.address = address;
        allHomes.add(this);
    }

    public String getOwnerSsn() {
        return ownerSsn;
    }

    public String getPrice() {
        return price;
    }

    public String getSize() {
        return size;
    }

    public String getAddress() {
        return address;
    }

    public static HashSet<Home> getAllHomes() {
        return allHomes;
    }

}
