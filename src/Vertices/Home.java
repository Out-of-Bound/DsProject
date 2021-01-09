package Vertices;

import App.GUI;
import App.Main;
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

    public static void print() {
        String[] tableColumn = {"ردیف", "کد ملی صاحب خانه", "نام و نام خانوادگی صاحب خانه" , "قیمت", "کد پستی", "متراژ", "آدرس"};
        String[][] data = new String[allHomes.size()][tableColumn.length];
        int i =0;
        for (Home home : allHomes) {
            People owner = (People) Main.directedGraph.getVertexByID(home.getOwnerSsn());
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
