package App;

import Edges.Transaction;
import Vertices.Person;
import java.util.HashSet;

import static App.PhaseFour.SMUGGLER;

public class PhaseThree {

    public static HashSet<Person> suspectedPhase3 = new HashSet<>();
    public static HashSet<Person> checkedPeople ;
    public  static boolean find ;


    public static void show(){
        Person.print(find());
    }

    public static HashSet<Person> find(){

        HashSet<Person> suspectedPhase2 = PhaseTow.getSuspectedPeople();

        if (suspectedPhase2.isEmpty())
            suspectedPhase2 = PhaseTow.find();

        for (Person person : suspectedPhase2) {
            checkedPeople = new HashSet<>();
            find = false;
            checkTrans(0 ,person, person);
        }

        return suspectedPhase3;
    }

    public static void checkTrans( int m , Person startingPerson, Person person ){
        System.out.println(person.getFirstName() + "  " + person.getLastName());


        m++;
        if (m > 5 ){
            System.out.println("reachMax");
            return;
        }

        checkedPeople.add(person);
        HashSet< Person > mustCheck = new HashSet<>();
        HashSet< String > personTransID = person.getTransactions();

        for ( String tranID : personTransID ) {

            Transaction transaction = (Transaction) Main.directedGraph.getEdgeByID(tranID) ;
            Person from = transaction.getPersonFrom() ;

            if ( from.getWork().equals( SMUGGLER ) ){

                System.out.println("find it !!!" + from.getFirstName() + " " + from.getLastName() +
                        "    " +startingPerson.getFirstName() + " " + startingPerson.getLastName());
                find = true;
                suspectedPhase3.add( startingPerson );
                System.out.println("end");
                return;
            }

            if ( from != person ){
                mustCheck.add(from);
            }

        }

        for (Person personCheck : mustCheck) {

            if ( checkedPeople.contains(personCheck) )
                continue;

            checkTrans( m + 1 , startingPerson, personCheck );

            if (find)
                break;

            System.out.println(personCheck.getFirstName() + personCheck.getLastName());

        }
    }

    public static HashSet<Person> getSuspectedPhase3() {
        return suspectedPhase3;
    }

}
