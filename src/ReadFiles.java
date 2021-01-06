import Edges.Call;
import Edges.Ownership;
import Graph.DirectedGraph;
import Vertices.*;
import javax.swing.*;
import java.io.*;

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

    public static void start() throws IOException {
                //getDataFolderFromSystem().getPath();
        String folderPath = "C:\\Users\\ebtekar\\Downloads\\Documents\\ساختمان داده\\data";
        readAccountFile(folderPath);
        readCarFile(folderPath);
        readHomeFile(folderPath);
        readPeopleFile(folderPath);
        readPhoneFile(folderPath);
        readCallFile(folderPath);
    }

    private static File getDataFolderFromSystem(){
        JFileChooser chooser = new JFileChooser();
        chooser.setDragEnabled(true);
        chooser.setDialogTitle("پوشه حاوی دیتا ها را انتخاب کنید");
        chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        int choice = chooser.showOpenDialog(null);
        if (choice != JFileChooser.APPROVE_OPTION) {
            System.out.println("File not selected");
        }
        return chooser.getSelectedFile();
    }

    private static void readAccountFile(String filePath) throws IOException {
        filePath += ACCOUNT_FILE_NAME;
        BufferedReader csvReader = new BufferedReader(new FileReader(new File(filePath)));
        String row;
        csvReader.readLine(); //first line
        while ((row = csvReader.readLine()) != null) {
            String[] data = row.split(",");
            Main.directedGraph.addVertex(new Account(data[0] , data[1] , data[2] , data[3]));
        }
        csvReader.close();
    }

    private static void readCarFile(String filePath) throws IOException {
        filePath += CAR_FILE_NAME;
        BufferedReader csvReader = new BufferedReader(new FileReader(new File(filePath)));
        String row;
        csvReader.readLine(); //first line
        while ((row = csvReader.readLine()) != null) {
            String[] data = row.split(",");
            Main.directedGraph.addVertex(new Car(data[0] , data[1] , data[2] , data[3]));
        }
        csvReader.close();
    }

    private static void readHomeFile(String filePath) throws IOException {
        filePath += HOME_FILE_NAME;
        BufferedReader csvReader = new BufferedReader(new FileReader(new File(filePath)));
        String row;
        csvReader.readLine(); //first line
        while ((row = csvReader.readLine()) != null) {
            String[] data = row.split(",");
            Main.directedGraph.addVertex(new Home(data[0], data[1], data[2], data[3], data[4]));
        }
        csvReader.close();
    }

    private static void readPeopleFile(String filePath) throws IOException {
        filePath += PERSON_FILE_NAME;
        BufferedReader csvReader = new BufferedReader(new FileReader(new File(filePath)));
        String row;
        csvReader.readLine(); //first line
        while ((row = csvReader.readLine()) != null) {
            String[] data = row.split(",");
            Main.directedGraph.addVertex(new People(data[0], data[1], data[2], data[3], data[4], data[5]));
        }
        csvReader.close();
    }

    private static void readPhoneFile(String filePath) throws IOException {
        filePath += PHONE_FILE_NAME;
        BufferedReader csvReader = new BufferedReader(new FileReader(new File(filePath)));
        String row;
        csvReader.readLine(); //first line
        while ((row = csvReader.readLine()) != null) {
            String[] data = row.split(",");
            Main.directedGraph.addVertex(new Phone(data[0], data[1], data[2]));
        }
        csvReader.close();
    }

    private static void readCallFile(String filePath) throws IOException {
        filePath += CALL_FILE_NAME;
        BufferedReader csvReader = new BufferedReader(new FileReader(new File(filePath)));
        String row;
        csvReader.readLine(); //first line
        while ((row = csvReader.readLine()) != null) {
            String[] data = row.split(",");
            String from = data[0];
            String to = data[1];
            DirectedGraph.Vertex start = Main.directedGraph.getVertexByID(from);
            DirectedGraph.Vertex finish = Main.directedGraph.getVertexByID(to);
            Main.directedGraph.addEdges(new Call(start , finish , data[2] , data[3] , data[4]));
        }
        csvReader.close();
    }

    private static void readOwnershipFile(String filePath) throws IOException {
        filePath += OWNERSHIP_FILE_NAME;
        BufferedReader csvReader = new BufferedReader(new FileReader(new File(filePath)));
        String row;
        csvReader.readLine(); //first line
        while ((row = csvReader.readLine()) != null) {
            String[] data = row.split(",");
            String from = data[0];
            String to = data[1];
            DirectedGraph.Vertex start = Main.directedGraph.getVertexByID(from);
            DirectedGraph.Vertex finish = Main.directedGraph.getVertexByID(to);
            Main.directedGraph.addEdges(new Ownership(start , finish , data[2] , data[3] , data[4]));
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
