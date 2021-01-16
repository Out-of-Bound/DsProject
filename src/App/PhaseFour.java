package App;

import Edges.Call;
import Vertices.Person;
import Vertices.Phone;
import java.util.HashSet;

public class PhaseFour {

    public static final String SMUGGLER = "قاچاقچی";

    public static void start(){

        HashSet<Person> suspectedPhase2 = PhaseTow.getSuspectedPeople();
        if (suspectedPhase2.isEmpty())
            suspectedPhase2 = PhaseTow.find();
        HashSet<Person> smugglers = Person.getSmugglers();
        HashSet<Person> suspectedPeople = new HashSet<>();

        for (Person person : suspectedPhase2) {
                HashSet<Call> calls = person.getCalls();
                for (Call call: calls) {
                    Person person1 = (Person) Main.directedGraph.getVertexByID(((Phone)call.getFinishingVertex()).getOwnerSsn());
                    Person person2 = (Person) Main.directedGraph.getVertexByID(((Phone)call.getStartingVertex()).getOwnerSsn());
                    if (smugglers.contains(person1))
                        suspectedPeople.add(person);
                    else if (smugglers.contains(person2))
                        suspectedPeople.add(person);
                }
            }
        Person.print(suspectedPeople);
    }

}
