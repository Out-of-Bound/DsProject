package App;

import Graph.DirectedGraph;
import Graph.GraphVisual;
import Vertices.Person;
import java.io.File;
import java.io.IOException;
import java.util.Formatter;
import java.util.HashSet;

public class Main {
    public static DirectedGraph directedGraph = new DirectedGraph();
    public static Menu menu;

    public static void main(String[] args) {

        //************************************
        new GraphVisual();
        // TODO: 1/17/2021 complete
        //************************************
        menu = new Menu();
        if(ReadFiles.isFilesReady()) {
            try {
                File file = new File(".\\Responses.txt");
                file.createNewFile();
                Formatter formatter = new Formatter(file);
                formatter.format("%s \n", "Ghachaghchi Ha:");
                saveToFile(Person.getSmugglers(), file);
                formatter.format("%s \n", "Maznoonin Faze2:");
                saveToFile(PhaseTow.find(), file);
                formatter.format("%s \n", "Maznoonin Faze3:");
                saveToFile(PhaseThree.find(), file);
                formatter.format("%s \n", "Maznoonin Faze4:");
                saveToFile(PhaseFour.find(), file);
                formatter.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private static void saveToFile(HashSet<Person> suspectedPeople, File file){
        try {
            Formatter formatter = new Formatter(file);
            for (Person person : suspectedPeople) {
                formatter.format("%s %s %s %s %s %s\n", person.getFirstName(), person.getLastName(),
                        person.getId(), person.getBirthDay(), person.getCity() , person.getWork());
            }
            //formatter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
