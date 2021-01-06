package Vertices;
import App.GUI;
import App.Main;
import Graph.DirectedGraph;
import java.util.HashSet;

public class People extends DirectedGraph.Vertex {
    private static HashSet<People> allPeoples = new HashSet<>();
    private String firstName, lastName, birthDay, city, work; //id = ssn

    public People(String firstName, String lastName, String ssn, String birthDay, String city, String work) {
        super(ssn);
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthDay = birthDay;
        this.city = city;
        this.work = work;
        allPeoples.add(this);
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getBirthDay() {
        return birthDay;
    }

    public String getCity() {
        return city;
    }

    public String getWork() {
        return work;
    }

    public static HashSet<People> getAllPeoples() {
        return allPeoples;
    }

    public static void print() {
        String[] tableColumn = {"ردیف", "نام", "نام خانوادگی" , "کد ملی", "تاریخ تولد", "شهر", "شغل"};
        String[][] data = new String[allPeoples.size()][tableColumn.length];
        int i =0;
        for (People people : allPeoples) {
            data[i][0] = (i+1) + "";
            data[i][1] = people.firstName;
            data[i][2] = people.lastName;
            data[i][3] = people.getId();
            data[i][4] = people.birthDay;
            data[i][5] = people.city;
            data[i][6] = people.work;
            i++;
        }
        GUI.showJTable("People" , tableColumn , data);
    }


}
