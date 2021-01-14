package Vertices;

import App.GUI;
import App.Main;
import Graph.DirectedGraph;
import java.util.HashSet;

public class Home extends DirectedGraph.Vertex {
    private static HashSet<Home> allHomes = new HashSet<>();
    private String ownerSsn, price ,size , address; //id = postalCode

    public Home(String[] data) {
        //postalCode = data[2]
        super(data[2]);
        this.ownerSsn = data[0];
        this.price = data[1];
        this.size = data[3];
        this.address = data[4];
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

    public static void print() {
        String[] tableColumn = {"ردیف", "کد ملی صاحب خانه", "نام و نام خانوادگی صاحب خانه" , "قیمت", "کد پستی", "متراژ", "آدرس"};
        String[][] data = new String[allHomes.size()][tableColumn.length];
        int i =0;
        for (Home home : allHomes) {
            Person owner = (Person) Main.directedGraph.getVertexByID(home.getOwnerSsn());
            data[i][0] = (i+1) + "";
            data[i][1] = home.getOwnerSsn();
            data[i][2] = owner.getFirstName() + " " + owner.getLastName();
            data[i][3] = home.getPrice();
            data[i][4] = home.getId();
            data[i][5] = home.getSize();
            data[i][6] = home.getAddress();
            i++;
        }
        GUI.showJTable("Homes",tableColumn , data);
    }


}
