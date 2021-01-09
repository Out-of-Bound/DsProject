package Vertices;

import App.GUI;
import App.Main;
import Edges.Ownership;
import Graph.DirectedGraph;
import java.util.HashSet;

public class People extends DirectedGraph.Vertex {
    private static HashSet<People> allPeoples = new HashSet<>();
    private HashSet<String> relations = new HashSet<>();
    private HashSet<String> ownersEdge = new HashSet<>();


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
        print(allPeoples);
    }

    public static void print(HashSet<People> allPeoples){
        String[] tableColumn = {"ردیف", "نام و نام خانوادگی", "کد ملی", "تاریخ تولد", "شهر", "شغل"};
        String[][] data = new String[allPeoples.size()][tableColumn.length];
        People p = (People) Main.directedGraph.getVertexByID("19081257821");
        for (String relID :p.getRelations()){
            People relp = ((People)Main.directedGraph.getVertexByID(relID));
            System.out.println(relp.getLastName());
            for (String ownId : relp.getOwnersEdge()) {
                System.out.println(((Ownership)Main.directedGraph.getEdgeByID(ownId)).getDate());
            }
        }

        int i =0;
        for (People people : allPeoples) {
            data[i][0] = (i+1) + "";
            data[i][1] = people.getFirstName() + " " + people.getLastName();
            data[i][2] = people.getId();
            data[i][3] = people.getBirthDay();
            data[i][4] = people.getCity();
            data[i][5] = people.getWork();
            i++;
        }
        GUI.showJTable("Suspected People" , tableColumn , data);
    }

    public void addToRels(String relID){
        relations.add(relID);
    }

    public HashSet<String> getRelations() {
        return relations;
    }

    public void addToOwners(String ownID){
        ownersEdge.add(ownID);
    }

    public HashSet<String> getOwnersEdge() {
        return ownersEdge;
    }
}
