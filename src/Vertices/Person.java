package Vertices;

import App.GUI;
import Graph.DirectedGraph;
import java.util.HashSet;

public class Person extends DirectedGraph.Vertex {

    private String firstName, lastName, birthDay, city, work; //id = ssn
    private static HashSet<Person> allPerson = new HashSet<>();
    private HashSet<String> relations = new HashSet<>();
    private HashSet<String> ownersEdge = new HashSet<>();

    public Person(String firstName, String lastName, String ssn, String birthDay, String city, String work) {
        super(ssn);
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthDay = birthDay;
        this.city = city;
        this.work = work;
        allPerson.add(this);
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

    public static HashSet<Person> getAllPerson() {
        return allPerson;
    }
    public HashSet<String> getRelations() {
        return relations;
    }
    public HashSet<String> getOwnersEdge() {
        return ownersEdge;
    }

    public void addToRels(String relID){
        relations.add(relID);
    }
    public void addToOwners(String ownID){
        ownersEdge.add(ownID);
    }

    public static void print(HashSet<Person> allPeople){
        String[] tableColumn = {"ردیف", "نام و نام خانوادگی", "کد ملی", "تاریخ تولد", "شهر", "شغل"};
        String[][] data = new String[allPeople.size()][tableColumn.length];
        int i =0;
        for (Person person : allPeople) {
            data[i][0] = (i+1) + "";
            data[i][1] = person.getFirstName() + " " + person.getLastName();
            data[i][2] = person.getId();
            data[i][3] = person.getBirthDay();
            data[i][4] = person.getCity();
            data[i][5] = person.getWork();
            i++;
        }
        GUI.showJTable("Suspected People" , tableColumn , data);
    }
    public static void print() {
        print(allPerson);
    }
}