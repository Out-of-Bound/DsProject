package Vertices;

import App.Table;
import App.Main;
import Graph.DirectedGraph;
import java.util.HashSet;

public class Car extends DirectedGraph.Vertex {

    private String ownerSsn, model, color; //id = plate
    private static HashSet<Car> allCars = new HashSet<>();

    public Car(String[] data) {
        //plate = data[0];
        super(data[0]);
        this.ownerSsn = data[1];
        this.model = data[2];
        this.color = data[3];
        this.colorId = 5; //orange
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
        Table.showJTable("Cars" , tableColumn , data);
    }


}
