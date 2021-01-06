package Vertices;
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


}
