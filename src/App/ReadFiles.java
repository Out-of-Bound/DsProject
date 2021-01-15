package App;

import Edges.Call;
import Edges.Ownership;
import Edges.Relationship;
import Edges.Transaction;
import Graph.DirectedGraph;
import Vertices.*;
import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.util.HashSet;
import java.util.List;

public class ReadFiles {
    private static final String[] allVertexFileName = {"\\accounts.csv", "\\cars.csv", "\\homes.csv",
            "\\people.csv", "\\phones.csv" };
    private static final String[] allEdgeFileName = {"\\calls.csv" , "\\ownerships.csv",
            "\\relationships.csv", "\\transactions.csv" };

    private static boolean filesReady;
    private static String folderPath;
    static private volatile boolean personRead , phoneRead , accRead, carRead, homeRead;
    static private volatile boolean callRead , ownershipRead, relationshipRead , transactionsRead;

    public static void start() {
        try {
            String tempFolderPath = getDataFolderFromSystem().getPath();
            readFiles(tempFolderPath);
            folderPath = tempFolderPath;
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static void fastStart(){
        try {
            readFiles(".\\data");
            folderPath = ".\\data";
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void startWithDragAndDrop(List<File> files) {
        for (File file : files) {
            try {
                String filePath = file.getPath();
                readFiles(filePath);
                folderPath = filePath;
            } catch (IOException e) {
                e.printStackTrace();
            }
            break;
        }
    }

    private static File getDataFolderFromSystem() {
        JFileChooser chooser = new JFileChooser();
        chooser.setSelectedFile(new File(".\\data"));
        chooser.setDialogTitle("Choose Data folder");
        chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        int choice = chooser.showOpenDialog(null);
        if (choice != JFileChooser.APPROVE_OPTION) {
            System.out.println("File not selected");

        }
        return chooser.getSelectedFile();
    }

    private static HashSet<String[]> readDataFromFile(BufferedReader csvReader) throws IOException {
        csvReader.readLine(); //first line
        String row;
        HashSet<String[]> data = new HashSet<>();
        while ((row = csvReader.readLine()) != null) {
            String[] t = row.split(",");
            for (int i = 0; i < t.length; i++) {
                t[i] = t[i].substring(1, t[i].length() - 1);
            }
            data.add(t);
        }
        return data;
    }

    private static void readVertexFile (int index, String filePath) throws IOException {
        System.out.println(allVertexFileName[index] + "read start");
        BufferedReader csvReader = new BufferedReader(new FileReader(new File(filePath)));
        HashSet<String[]> data = readDataFromFile(csvReader);

        switch (index){
            case 0 :
                for (String[] datum : data) {
                    Main.directedGraph.addVertex(new Account(datum));
                }
                accRead = true;
                System.out.println("acc read end");
                break;
            case 1 :
                for (String[] datum : data) {
                    Main.directedGraph.addVertex(new Car(datum));
                }
                carRead = true;
                System.out.println("car read end");
                break;
            case 2 :
                for (String[] datum : data) {
                    Main.directedGraph.addVertex(new Home(datum));
                }
                homeRead = true;
                System.out.println("home read end");
                break;
            case 3 :
                for (String[] datum : data) {
                    Main.directedGraph.addVertex(new Person(datum));
                }
                personRead = true;
                System.out.println("person Read end");
                break;
            case 4 :
                for (String[] datum : data) {
                    Main.directedGraph.addVertex(new Phone(datum));
                }
                phoneRead = true;
                System.out.println("phone read end");
        }

        csvReader.close();
    }

    private static void readEdgeFile (int index, String filePath) throws IOException {

        BufferedReader csvReader = new BufferedReader(new FileReader(new File(filePath)));

        HashSet<String[]> data = readDataFromFile(csvReader);

        switch (index){
            case 0 :
                System.out.println(allEdgeFileName[0] + "read start");
                for (String[] datum : data) {
                    String from = datum[0];
                    String to = datum[1];
                    DirectedGraph.Vertex start = Main.directedGraph.getVertexByID(from);
                    DirectedGraph.Vertex finish = Main.directedGraph.getVertexByID(to);
                    Main.directedGraph.addEdges(new Call(start, finish, datum));
                }
                callRead = true;
                System.out.println("call read end");
                break;
            case 1 :
                System.out.println(allEdgeFileName[1] + "read start");
                for (String[] datum : data) {
                    String from = datum[0];
                    String to = datum[1];
                    DirectedGraph.Vertex start = Main.directedGraph.getVertexByID(from);
                    DirectedGraph.Vertex finish = Main.directedGraph.getVertexByID(to);
                    Main.directedGraph.addEdges(new Ownership(start, finish, datum));
                }
                ownershipRead = true;
                System.out.println("ownership read end");
                break;
            case 2 :
                System.out.println(allEdgeFileName[2] + "read start");
                for (String[] datum : data) {
                    String from = datum[0];
                    String to = datum[1];
                    DirectedGraph.Vertex start = Main.directedGraph.getVertexByID(from);
                    DirectedGraph.Vertex finish = Main.directedGraph.getVertexByID(to);
                    Main.directedGraph.addEdges(new Relationship(start, finish, datum));
                }
                relationshipRead = true;
                System.out.println("rel read end");
                break;
            case 3 :
                System.out.println(allEdgeFileName[3] + "read start");
                for (String[] datum : data) {
                    String from = datum[0];
                    String to = datum[1];
                    DirectedGraph.Vertex start = Main.directedGraph.getVertexByID(from);
                    DirectedGraph.Vertex finish = Main.directedGraph.getVertexByID(to);
                    Main.directedGraph.addEdges(new Transaction(start, finish, datum));
                }
                transactionsRead = true;
                System.out.println("trans read end");
        }
        csvReader.close();
    }

    private static void readFiles(String filePath) throws IOException {

      /*  for (int i = 0; i < allVertexFileName.length ; i++ ) {

            int finalI = i;
            new Thread(new Runnable() {
                @Override
                public void run() {
                    String fileName = allVertexFileName[finalI];
                    try {
                        readVertexFile( finalI ,filePath + fileName);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }).start();

        }

        for (int i = 0; i < allEdgeFileName.length ; i++ ) {

            int finalI = i;
            new Thread(new Runnable() {
                @Override
                public void run() {

                    while ( !accRead || !carRead || !homeRead || !personRead || !phoneRead ){
                        try {
                            Thread.sleep(500);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }

                    String fileName = allEdgeFileName[finalI];
                    try {
                        readEdgeFile( finalI ,filePath + fileName);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }).start();

        }

        new Thread(new Runnable() {
            @Override
            public void run() {
                while ( !callRead || !relationshipRead || !transactionsRead || !ownershipRead ){
                    try {
                        Thread.sleep(500);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                filesReady = true;
                System.out.println("read end");
                new PhaseOne(new JFrame());
            }
        }).start();*/
        //**********************************************************************************************************

        /*for (int i = 0; i < allVertexFileName.length ; i++ ) {

            String fileName = allVertexFileName[i];
            readVertexFile(i,filePath + fileName);

        }

        for (int i = 0; i < allEdgeFileName.length ; i++ ) {

            String fileName = allEdgeFileName[i];
            readEdgeFile(i,filePath + fileName);

        }

        filesReady = true;
        System.out.println("read end");*/

        //*****************************************************************************************




        new Thread(new Runnable() {
            @Override
            public void run() {
                Main.menu.fileReadStart();
                try {
                    for (int i = 0; i < allVertexFileName.length ; i++ ) {
                        String fileName = allVertexFileName[i];
                        readVertexFile( i ,filePath + fileName);
                    }

                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();





        new Thread(new Runnable() {
            @Override
            public void run() {
                while ( !accRead || !carRead || !homeRead || !personRead || !phoneRead ){
                    try {
                        Thread.sleep(500);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }

                try {
                    for (int i = 0; i < allEdgeFileName.length ; i++ ) {
                        String fileName = allEdgeFileName[i];
                        readEdgeFile( i, filePath + fileName);
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();


        new Thread(new Runnable() {
            @Override
            public void run() {
                while ( !callRead || !relationshipRead || !transactionsRead || !ownershipRead ){
                    try {
                        Thread.sleep(500);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                filesReady = true;
                System.out.println("read end");
                Main.menu.fileReadEnd();
            }
        }).start();

    }

    public static boolean isFilesReady() {
        return filesReady;
    }
    public static String getFolderName(){
        return new File(folderPath).getName();
    }
}
