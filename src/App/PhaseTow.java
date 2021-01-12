package App;

import Edges.Ownership;
import Edges.Relationship;
import Graph.DirectedGraph;
import Vertices.People;

import java.util.HashSet;

public class PhaseTow {
    public static final String GOMROK = "گمرک";
    public static final String BANDER = "سازمان بنادر";

    public static void show(){
        People.print(find());
    }

    private static HashSet<People> find(){
        HashSet<People> suspectedPeople = new HashSet<>();

        for (People person : People.getAllPeoples()) {
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
                    People personRel = (People) Main.directedGraph.getVertexByID(personRelID);
                    for (String relID : personRel.getOwnersEdge()) {
                        Ownership ownership = (Ownership) Main.directedGraph.getEdgeByID(relID);
                        String[] buyDate = ownership.getDate().split("-");
                        if(Integer.parseInt(buyDate[0])>=2018){
                            suspectedPeople.add(person);

                            break;
                        }
                    }
                }


/*
                if (person.getInEdges().size() + person.getOutEdges().size() > Relationship.getAllRelationships().size())
                for (DirectedGraph.Edge edge: person.getInEdges())
                    if (edge instanceof Relationship)
                        people.add((People)edge.getStartingVertex());

                for (DirectedGraph.Edge edge: person.getOutEdges())
                    if (edge instanceof Relationship)
                        people.add((People)edge.getFinishingVertex());

 */
            }
        }
        return suspectedPeople;
    }

}
