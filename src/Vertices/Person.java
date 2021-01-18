package Vertices;

import App.Table;
import Edges.Call;
import Graph.DirectedGraph;
import java.util.ArrayList;
import java.util.HashSet;

public class Person extends DirectedGraph.Vertex {

    private DirectedGraph.Vertex phone;
    private DirectedGraph.Vertex account;
    private String firstName, lastName, birthDay, city, work; //id = ssn
    private static HashSet<Person> allPerson = new HashSet<>();
    private static HashSet<Person> smugglers = new HashSet<>();
    private HashSet<String> relations = new HashSet<>();
    private HashSet<String> owners = new HashSet<>();
    private HashSet<String> transactions = new HashSet<>();
    private HashSet<Call> calls = new HashSet<>();

    ArrayList<DirectedGraph.Vertex> relOwns = new ArrayList<>();
    public ArrayList<DirectedGraph.Vertex> getRelOwns() {
        return relOwns;
    }
    public void addToRelOwns(DirectedGraph.Vertex vertex){
        relOwns.add(vertex);
    }

    ArrayList<DirectedGraph.Vertex> phones = new ArrayList<>();
    public ArrayList<DirectedGraph.Vertex> getPhones() {
        return phones;
    }
    public void addToPhones(DirectedGraph.Vertex vertex) { phones.add(vertex); }

    ArrayList<DirectedGraph.Vertex> accounts = new ArrayList<>();
    public ArrayList<DirectedGraph.Vertex> getAccounts() {
        return accounts;
    }
    public void addToAccounts(DirectedGraph.Vertex vertex) { accounts.add(vertex); }

    public Person(String[] data) {
        // ssn = data[2]
        super(data[2]);
        this.firstName = data[0];
        this.lastName = data[1];
        this.birthDay = data[3];
        this.city = data[4];
        this.work = data[5];
        allPerson.add(this);
        if (this.work.equals("قاچاقچی")) {
            addToSmuggler(this);
            this.colorId = 0; //black
        }
        else if (this.work.equals("گمرک") || this.work.equals("سازمان بنادر"))
            this.colorId = 1; //red
        else
            this.colorId = 2; //white
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
    public DirectedGraph.Vertex getPhone() {
        return phone;
    }
    public void setPhone(DirectedGraph.Vertex phone) {
        this.phone = phone;
    }
    public DirectedGraph.Vertex getAccount() {
        return account;
    }
    public void setAccount(DirectedGraph.Vertex account) {
        this.account = account;
    }

    public static HashSet<Person> getAllPerson() {
        return allPerson;
    }
    public static HashSet<Person> getSmugglers() {
        return smugglers;
    }
    public HashSet<String> getRelations() {
        return relations;
    }
    public HashSet<String> getOwners() {
        return owners;
    }
    public HashSet<String> getTransactions() {
        return transactions;
    }
    public HashSet<Call> getCalls() {
        return calls;
    }

    public void addToRels(String relID){
        relations.add(relID);
    }
    public void addToOwners(String ownID){
        owners.add(ownID);
    }
    public void addToTrans(String tranID){
        transactions.add(tranID);
    }
    public static void addToSmuggler(Person person){
        smugglers.add(person);
    }
    public void addToCalls(Call call){
        calls.add(call);
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
        Table.showJTable("Suspected People" , tableColumn , data);
    }
    public static void print() {
        print(allPerson);
    }
}
