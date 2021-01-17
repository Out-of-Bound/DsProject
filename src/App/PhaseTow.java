package App;

import Edges.Ownership;
import Vertices.Person;
import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

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
                for (String ownID : person.getOwners()) {
                    Ownership ownership = ((Ownership) Main.directedGraph.getEdgeByID(ownID));
                    if(is2YearsAgo(ownership)){
                        suspectedPeople.add(person);
                        suspected = true;
                        break;
                    }
                }

                if (suspected)
                    continue;

                for (String personRelID: person.getRelations()) {
                    Person personRel = (Person) Main.directedGraph.getVertexByID(personRelID);
                    for (String relID : personRel.getOwners()) {
                        Ownership ownership = (Ownership) Main.directedGraph.getEdgeByID(relID);
                        if(is2YearsAgo(ownership)){
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
            for (Person person : suspectedPeople) {
                formatter.format("%s %s %s %s %s %s\n", person.getFirstName(), person.getLastName(),
                        person.getId(), person.getBirthDay(), person.getCity() , person.getWork());
            }
            formatter.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static boolean is2YearsAgo(Ownership ownership) {
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            Date date = sdf.parse(ownership.getDate());
            Date today = new Date();
            today.setYear(today.getYear()-2);
            boolean b = today.getTime() <= date.getTime();
            // System.out.println(date.toString() + " " + b);
            return b;
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return false;
    }

    public static HashSet<Person> getSuspectedPeople() {
        return suspectedPeople;
    }
}
