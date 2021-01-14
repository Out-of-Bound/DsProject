package App;

import Edges.Call;
import Graph.DirectedGraph;
import Vertices.Person;
import Vertices.Phone;

import java.util.HashSet;

public class PhaseFour {
    public static final String GHACHAGHCHI = "قاچاقچی";
    public static void start(){
        HashSet<Person> suspectedPeople = PhaseTow.find();
        HashSet<Person> smugglers = Person.getSmugglers();
        for (Person person : suspectedPeople) {
                HashSet<Call> calls = person.getCalls();
                for (Call call: calls) {
                    Person person1 = (Person) Main.directedGraph.getVertexByID(((Phone)call.getFinishingVertex()).getOwnerSsn());
                    Person person2 = (Person) Main.directedGraph.getVertexByID(((Phone)call.getStartingVertex()).getOwnerSsn());
                    if (!smugglers.contains(person1))
                        suspectedPeople.remove(person1);
                    else if (!smugglers.contains(person2))
                        suspectedPeople.remove(person2);
                }
            }
        Person.print(suspectedPeople);
    }

}
