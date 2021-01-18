package Vertices;

import App.Table;
import App.Main;
import Graph.DirectedGraph;
import java.util.HashSet;

public class Phone extends DirectedGraph.Vertex {

    private String ownerSsn, operatorName; //id = number
    private static HashSet<Phone> allPhones = new HashSet<>();

    public Phone(String[] data ) {
        //number = data[1]
        super(data[1]);
        this.ownerSsn = data[0];
        this.operatorName = data[2];
        this.colorId = 4; //blue
        allPhones.add(this);
        ((Person)Main.directedGraph.getVertexByID(ownerSsn)).setPhone(this);
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

    public static void print() {
        String[] tableColumn = {"ردیف", "نام و نام خانوادگی" , "کد ملی", "شماره همراه", "اپراتور"};
        String[][] data = new String[allPhones.size()][tableColumn.length];

        int i =0;
        for (Phone phone : allPhones) {
            Person owner = (Person) Main.directedGraph.getVertexByID(phone.ownerSsn);
            data[i][0] = (i+1) + "";
            data[i][1] = owner.getFirstName() + " " + owner.getLastName();
            data[i][2] = phone.getOwnerSsn();
            data[i][3] = phone.getId();
            data[i][4] = phone.getOperatorName();
            i++;
        }
        Table.showJTable("Phones",tableColumn , data);
    }

}
