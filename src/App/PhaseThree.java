package App;

import Edges.Transaction;
import Vertices.Person;
import java.util.HashSet;

import static App.PhaseFour.SMUGGLER;

public class PhaseThree {

    public static HashSet<Person> suspectedPhase3 = new HashSet<>();
    public static HashSet<Person> checkedPeople = new HashSet<>();

    public static void show(){
        Person.print(find());
    }

    public static HashSet<Person> find(){
        HashSet<Person> suspectedPhase2 = PhaseTow.getSuspectedPeople();
        if (suspectedPhase2.isEmpty())
            suspectedPhase2 = PhaseTow.find();

        for (Person person : suspectedPhase2) {
            checkTrans(person, person);
        }

        return suspectedPhase3;
    }

    public static void checkTrans( Person startingPerson, Person person ){
        checkedPeople.add(person);
        HashSet< String > personRelations = person.getRelations();
        HashSet< Person > mustCheckRelations = new HashSet<>();
        HashSet< String > personTransID = person.getTransactions();

        for ( String tranID : personTransID ) {
            Transaction transaction = (Transaction) Main.directedGraph.getEdgeByID(tranID);
            Person from = transaction.getPersonFrom();

            if ( from.getWork().equals(SMUGGLER) ){
                suspectedPhase3.add( startingPerson );
                return;
            }

            if ( from != person){
                if ( personRelations.contains( from.getId() )){
                    mustCheckRelations.add(from);
                }
            }
        }

        for (Person personRel : mustCheckRelations) {
            if ( checkedPeople.contains(personRel) )
                continue;
            checkTrans(startingPerson, personRel );
        }
    }

    public static HashSet<Person> getSuspectedPhase3() {
        return suspectedPhase3;
    }
}
