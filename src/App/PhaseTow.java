package App;

import Edges.Ownership;
import Vertices.Person;
import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Formatter;
import java.util.HashSet;

public class PhaseTow {
    public static final String GOMROK = "گمرک";
    public static final String BANDER = "سازمان بنادر";
    private static HashSet<Person> suspectedPeople = new HashSet<>();

    public static void show(){
        Person.print(find());
    }

    public static HashSet<Person> find(){
        for (Person person : Person.getAllPerson()) {
            if (person.getWork().equals(GOMROK) || person.getWork().equals(BANDER)){
                boolean suspected = false;
                for (String ownID : person.getOwnersEdge()) {
                    Ownership ownership = ((Ownership) Main.directedGraph.getEdgeByID(ownID));
                    long t = getTime(ownership);
                    if(t <= 2){
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
                        long t = getTime(ownership);
                        if(t<=2){
                            suspectedPeople.add(person);
                            break;
                        }
                    }
                }
            }
        }
        saveToFile(suspectedPeople);
        return suspectedPeople;
    }

    private static void saveToFile(HashSet<Person> suspectedPeople){
        try {
            File file = new File(".\\Responses.txt");
            file.createNewFile();
            Formatter formatter = new Formatter(file);
            for (Person person :
                    suspectedPeople) {
                formatter.format("%s %s %s %s %s %s\n", person.getFirstName(), person.getLastName(),
                        person.getId(), person.getBirthDay(), person.getCity() , person.getWork());
            }
            formatter.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static long getTime(Ownership ownership) {
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            Date date = sdf.parse(ownership.getDate());
            long millis = date.getTime();
            long t = System.currentTimeMillis() - millis;
            t = t / 1000 / 60 / 60 / 24 / 30 / 12;
            return t;
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public static HashSet<Person> getSuspectedPeople() {
        return suspectedPeople;
    }
}
