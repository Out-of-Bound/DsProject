package Vertices;

public class Car extends DirectedGraph.Vertex {
    private String ownerSsn,model,color; //id = plate

    public Car(String plate, String ownerSsn, String model, String color) {
        super(plate);
        this.ownerSsn = ownerSsn;
        this.model = model;
        this.color = color;
    }
}
