package Vertices;
import Graph.DirectedGraph;
import java.util.HashSet;

public class Car extends DirectedGraph.Vertex {
    private static HashSet<Car> allCars = new HashSet<>();
    private String ownerSsn, model, color; //id = plate

    public Car(String plate, String ownerSsn, String model, String color) {
        super(plate);
        this.ownerSsn = ownerSsn;
        this.model = model;
        this.color = color;
    }

    public String getOwnerSsn() {
        return ownerSsn;
    }

    public String getModel() {
        return model;
    }

    public String getColor() {
        return color;
    }

    public static HashSet<Car> getAllCars() {
        return allCars;
    }
}
