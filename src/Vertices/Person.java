package Vertices;
import Graph.DirectedGraph;
public class Person extends DirectedGraph.Vertex {
    private String firstName, lastName, birthDay, birthPlace , workPlace; //id = ssn

    public Person(String ssn, String firstName, String lastName, String birthDay, String birthPlace, String workPlace) {
        super(ssn);
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthDay = birthDay;
        this.birthPlace = birthPlace;
        this.workPlace = workPlace;
    }

}
