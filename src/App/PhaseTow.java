package App;

import Edges.Ownership;
import Vertices.Person;
import java.util.HashSet;

public class PhaseTow {
    public static final String GOMROK = "گمرک";
    public static final String BANDER = "سازمان بنادر";

    public static void show(){
        Person.print(find());
    }

    private static HashSet<Person> find(){
        HashSet<Person> suspectedPeople = new HashSet<>();
        for (Person person : Person.getAllPerson()) {
            if (person.getWork().equals(GOMROK) || person.getWork().equals(BANDER)){
                boolean suspected = false;
                for (String ownID : person.getOwnersEdge()) {
                    Ownership ownership = ((Ownership) Main.directedGraph.getEdgeByID(ownID));
                    String[] buyDate = ownership.getDate().split("-");
                    if(Integer.parseInt(buyDate[0])>=2018){
                        suspectedPeople.add(person);
                        suspected = true;
                        break;
                    }
                }

                if (suspected)
                    continue;

                for (String personRelID: person.getRelations()) {
                    Person personRel = (Person) Main.directedGraph.getVertexByID(personRelID);
                    for (String relID : personRel.getOwnersEdge()) {
                        Ownership ownership = (Ownership) Main.directedGraph.getEdgeByID(relID);
                        String[] buyDate = ownership.getDate().split("-");
                        if(Integer.parseInt(buyDate[0])>=2018){
                            suspectedPeople.add(person);
                            break;
                        }
                    }
                }
            }
        }
        return suspectedPeople;
    }

}
