package Vertices;

public class Home extends DirectedGraph.Vertex {
    private String ownerSsn, price ,size , address; //id = postalCode

    public Home(String ownerSsn, String price, String postalCode, String size, String address) {
        super(postalCode);
        this.ownerSsn = ownerSsn;
        this.price = price;
        this.size = size;
        this.address = address;
    }
}
