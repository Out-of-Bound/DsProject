package App;

import Edges.Call;
import Vertices.Person;
import Vertices.Phone;
import java.util.HashSet;

public class PhaseFour {
    public static final String SMUGGLER = "قاچاقچی";

    public static void show(){
        Person.print(find());
    }

    public static HashSet<Person> find(){
        HashSet<Person> suspectedPhase3 = PhaseThree.getSuspectedPhase3();
        if (suspectedPhase3.isEmpty())
            suspectedPhase3 = PhaseThree.find();
        HashSet<Person> smugglers = Person.getSmugglers();
        HashSet<Person> suspectedPeople = new HashSet<>();

        for (Person person : suspectedPhase3) {
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
        return suspectedPeople;
    }

}
