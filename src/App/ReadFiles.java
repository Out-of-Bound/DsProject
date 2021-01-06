package App;

import Edges.Call;
import Edges.Ownership;
import Graph.DirectedGraph;
import Vertices.*;

import javax.swing.*;
import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class ReadFiles {
    private static final String ACCOUNT_FILE_NAME = "\\accounts.csv";
    private static final String CAR_FILE_NAME = "\\cars.csv";
    private static final String HOME_FILE_NAME = "\\homes.csv";
    private static final String PERSON_FILE_NAME = "\\people.csv";
    private static final String PHONE_FILE_NAME = "\\phones.csv";

    private static final String CALL_FILE_NAME = "\\calls.csv";
    private static final String OWNERSHIP_FILE_NAME = "\\ownerships.csv";
    private static final String RELATIONSHIP_FILE_NAME = "\\relationships.csv";
    private static final String TRANSACTION_FILE_NAME = "\\transactions.csv";

    private static Scanner scanner;

    public static void start() throws IOException {
                //getDataFolderFromSystem().getPath();
        String folderPath = ".\\data";
        scanner = new Scanner(System.in);
        readAccountFile(folderPath);
        readCarFile(folderPath);
        readHomeFile(folderPath);
        readPeopleFile(folderPath);
        readPhoneFile(folderPath);
        readCallFile(folderPath);
        readOwnershipFile(folderPath);
    }


    private static File getDataFolderFromSystem(){
        JFileChooser chooser = new JFileChooser();
        chooser.setSelectedFile(new File(".\\data"));
        chooser.setDialogTitle("Choose Data folder");
        chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        int choice = chooser.showOpenDialog(null);
        if (choice != JFileChooser.APPROVE_OPTION) {
            System.out.println("File not selected");
            System.out.print("Enter 1 for try again and any for exit: ");
            if(scanner.nextInt() == 1)
                getDataFolderFromSystem();
            else
                System.exit(0);
        }
        return chooser.getSelectedFile();
    }

    private static ArrayList<String[]> readDataFromFile(BufferedReader csvReader) throws IOException {
        csvReader.readLine(); //first line
        String row;
        ArrayList<String[]> data = new ArrayList<>();
        while ((row = csvReader.readLine()) != null) {
            String[] t = row.split(",");
            for (int i =0 ; i<t.length ; i++) {
                t[i] = t[i].substring(1 , t[i].length() -1);
            }
            data.add(t);
        }
        return data;
    }

    private static void readAccountFile(String filePath) throws IOException {
        filePath += ACCOUNT_FILE_NAME;
        BufferedReader csvReader = new BufferedReader(new FileReader(new File(filePath)));
        ArrayList<String[]> data = readDataFromFile(csvReader);
        for (String[] datum : data) {
            Main.directedGraph.addVertex(new Account(datum[0], datum[1], datum[2], datum[3]));
        }
        csvReader.close();
    }

    private static void readCarFile(String filePath) throws IOException {
        filePath += CAR_FILE_NAME;
        BufferedReader csvReader = new BufferedReader(new FileReader(new File(filePath)));
        ArrayList<String[]> data = readDataFromFile(csvReader);
        for (String[] datum : data) {
            Main.directedGraph.addVertex(new Car(datum[0], datum[1], datum[2], datum[3]));
        }
        csvReader.close();
    }

    private static void readHomeFile(String filePath) throws IOException {
        filePath += HOME_FILE_NAME;
        BufferedReader csvReader = new BufferedReader(new FileReader(new File(filePath)));
        ArrayList<String[]> data = readDataFromFile(csvReader);
        for (String[] datum : data) {
            Main.directedGraph.addVertex(new Home(datum[0], datum[1], datum[2], datum[3],datum[4]));
        }
        csvReader.close();
    }

    private static void readPeopleFile(String filePath) throws IOException {
        filePath += PERSON_FILE_NAME;
        BufferedReader csvReader = new BufferedReader(new FileReader(new File(filePath)));
        ArrayList<String[]> data = readDataFromFile(csvReader);
        for (String[] datum : data) {
            Main.directedGraph.addVertex(new People(datum[0], datum[1], datum[2], datum[3],datum[4],datum[5]));
        }
        csvReader.close();
    }

    private static void readPhoneFile(String filePath) throws IOException {
        filePath += PHONE_FILE_NAME;
        BufferedReader csvReader = new BufferedReader(new FileReader(new File(filePath)));
        ArrayList<String[]> data = readDataFromFile(csvReader);
        for (String[] datum : data) {
            Main.directedGraph.addVertex(new Phone(datum[0], datum[1], datum[2]));
        }
        csvReader.close();
    }

    private static void readCallFile(String filePath) throws IOException {
        filePath += CALL_FILE_NAME;
        BufferedReader csvReader = new BufferedReader(new FileReader(new File(filePath)));
        ArrayList<String[]> data = readDataFromFile(csvReader);

        for (String[] datum : data) {
            String from = datum[0];
            String to = datum[1];
            DirectedGraph.Vertex start = Main.directedGraph.getVertexByID(from);
            DirectedGraph.Vertex finish = Main.directedGraph.getVertexByID(to);
            Main.directedGraph.addEdges(new Call(start , finish , datum[2] , datum[3] , datum[4]));
        }
        csvReader.close();
    }

    private static void readOwnershipFile(String filePath) throws IOException {
        filePath += OWNERSHIP_FILE_NAME;
        BufferedReader csvReader = new BufferedReader(new FileReader(new File(filePath)));
        ArrayList<String[]> data = readDataFromFile(csvReader);

        for (String[] datum : data) {
            String from = datum[0];
            String to = datum[1];
            DirectedGraph.Vertex start = Main.directedGraph.getVertexByID(from);
            DirectedGraph.Vertex finish = Main.directedGraph.getVertexByID(to);
            Main.directedGraph.addEdges(new Ownership(start , finish , datum[2] , datum[3] , datum[4]));
        }
        csvReader.close();
    }


    /*public static void printAllEdges(){
        long t = System.currentTimeMillis();
        for (Call call : Call.getAllCalls()) {
            System.out.println(call.getStartingVertex().getId() + " " + call.getFinishingVertex().getId()
                    + " " + call.getId() + " " + call.getDate() + " " + call.getDuration());
        }
        System.out.println(System.currentTimeMillis() - t);
    }*/
}
