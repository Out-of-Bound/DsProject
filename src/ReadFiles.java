import Vertices.Account;
import Vertices.Car;
import Vertices.Home;
import javax.swing.*;
import java.io.*;

public class ReadFiles {

    public static void readAccountFile() throws IOException {
        System.out.println("Choose Account File: ");
        File chosenFile = getFileFromSystem();
        BufferedReader csvReader = new BufferedReader(new FileReader(chosenFile.getPath()));
        String row;
        csvReader.readLine(); //first line
        while ((row = csvReader.readLine()) != null) {
            String[] data = row.split(",");
            Main.directedGraph.addVertex(new Account(data[0] , data[1] , data[2] , data[3]));
        }
        csvReader.close();
    }

    public static void readCarFile() throws IOException {
        System.out.println("Choose Car File: ");
        File chosenFile = getFileFromSystem();
        BufferedReader csvReader = new BufferedReader(new FileReader(chosenFile.getPath()));
        String row;
        csvReader.readLine(); //first line
        while ((row = csvReader.readLine()) != null) {
            String[] data = row.split(",");
            Main.directedGraph.addVertex(new Car(data[0] , data[1] , data[2] , data[3]));
        }
        csvReader.close();
    }

    public static void readHomeFile() throws IOException {
        System.out.println("Choose Home File: ");
        File chosenFile = getFileFromSystem();
        BufferedReader csvReader = new BufferedReader(new FileReader(chosenFile.getPath()));
        String row;
        csvReader.readLine(); //first line
        while ((row = csvReader.readLine()) != null) {
            String[] data = row.split(",");
            Main.directedGraph.addVertex(new Home(data[0], data[1], data[2], data[3], data[4]));
        }
        csvReader.close();
    }


    private static File getFileFromSystem(){
        JFileChooser chooser = new JFileChooser();
        int choice = chooser.showOpenDialog(null);
        if (choice != JFileChooser.APPROVE_OPTION)
            System.out.println("File not selected");
        return chooser.getSelectedFile();
    }

}
