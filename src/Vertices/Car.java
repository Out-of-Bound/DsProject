package Vertices;

import App.GUI;
import App.Main;
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
        allCars.add(this);
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

    public static void print() {
        String[] tableColumn = {"ردیف", "کد ملی صاحب ماشین", "نام و نام خانوادگی صاحب ماشین" , "شماره پلاک", "مدل", "رنگ"};
        String[][] data = new String[allCars.size()][tableColumn.length];
        int i =0;
        for (Car car : allCars) {
            Person owner = (Person) Main.directedGraph.getVertexByID(car.getOwnerSsn());
            data[i][0] = (i+1) + "";
            data[i][1] = car.getOwnerSsn();
            data[i][2] = owner.getFirstName() + " " + owner.getLastName();
            data[i][3] = car.getId();
            data[i][4] = car.getModel();
            data[i][5] = car.getColor();
            i++;
        }
        GUI.showJTable("Cars" , tableColumn , data);
    }


}
