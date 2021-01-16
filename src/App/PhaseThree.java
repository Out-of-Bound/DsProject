package App;

import Edges.Transaction;
import Vertices.Person;

import java.util.HashSet;

public class PhaseThree {

    public static final String SMUGGLER = "قاچاقچی";
    public static HashSet<Person> suspectedPeoplePhase3 = new HashSet<>();
    public static HashSet<Person> checkedPeople = new HashSet<>();

    public static void start(){

        HashSet<Person> suspectedPhase2 = PhaseTow.getSuspectedPeople();

        if (suspectedPhase2.isEmpty())
            suspectedPhase2 = PhaseTow.find();


        for (Person person : suspectedPhase2) {
            checkTrans(person, person);
        }

        Person.print(suspectedPeoplePhase3);

    }

    public static void checkTrans( Person startingPerson, Person person ){
        checkedPeople.add(person);
        HashSet< String > personRelations = person.getRelations();
        HashSet< Person > mustCheckRelations = new HashSet<>();
        HashSet< String > personTransID = person.getPersonTransactions();

        for ( String tranID : personTransID ) {
            Transaction transaction = (Transaction) Main.directedGraph.getEdgeByID(tranID);
            Person from = transaction.getPersonFrom();

            if ( from.getWork().equals(SMUGGLER) ){
                suspectedPeoplePhase3.add( startingPerson );
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



}
