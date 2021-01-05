

import Vertices.*;

import javax.swing.*;
import java.io.*;


public class ReadFiles {
    private static final String ACC_FILE_NAME = "\\accounts.csv";
    private static final String CAR_FILE_NAME = "\\cars.csv";
    private static final String HOME_FILE_NAME = "\\homes.csv";
    private static final String PERSON_FILE_NAME = "\\people.csv";
    private static final String PHONE_FILE_NAME = "\\phones.csv";


    public static void start() throws IOException {
        String folderPath = getDataFolderFromSystem().getPath();
        readAccFile(folderPath);
        readCarFile(folderPath);
        readHomeFile(folderPath);
        readPersonFile(folderPath);
        readPhoneFile(folderPath);
    }
    private static void readAccFile(String filePath) throws IOException {
        filePath += ACC_FILE_NAME;
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

    private static void readPersonFile(String filePath) throws IOException {
        filePath += PERSON_FILE_NAME;
        BufferedReader csvReader = new BufferedReader(new FileReader(new File(filePath)));
        String row;
        csvReader.readLine(); //first line
        while ((row = csvReader.readLine()) != null) {
            String[] data = row.split(",");
            Main.directedGraph.addVertex(new Person(data[0], data[1], data[2], data[3], data[4], data[5]));
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
}
