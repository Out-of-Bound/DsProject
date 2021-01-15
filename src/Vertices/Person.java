package Vertices;

import App.GUI;
import Edges.Call;
import Graph.DirectedGraph;
import java.util.HashSet;

public class Person extends DirectedGraph.Vertex {

    private String firstName, lastName, birthDay, city, work; //id = ssn
    private static HashSet<Person> allPerson = new HashSet<>();
    private HashSet<String> relations = new HashSet<>();
    private HashSet<String> ownersEdge = new HashSet<>();
    private HashSet<String> personTransactions = new HashSet<>();
    private HashSet<Call> calls = new HashSet<>();
    private static HashSet<Person> smugglers = new HashSet<>();

    public static HashSet<Person> getSmugglers() {
        return smugglers;
    }

    public static void addToSmuggler(Person person){
        smugglers.add(person);
    }

    public HashSet<Call> getCalls() {
        return calls;
    }
    public void addToCalls(Call call){
        calls.add(call);
    }

    public Person(String[] data) {
        // ssn = data[2]
        super(data[2]);
        this.firstName = data[0];
        this.lastName = data[1];
        this.birthDay = data[3];
        this.city = data[4];
        this.work = data[5];
        allPerson.add(this);
        if (this.work.equals("قاچاقچی"))
            addToSmuggler(this);
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

    public void addToTrans(String tranID){
        personTransactions.add(tranID);
    }

    public HashSet<String> getPersonTransactions() {
        return personTransactions;
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
