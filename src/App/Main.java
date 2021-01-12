package App;

import Graph.DirectedGraph;
import java.util.Scanner;

public class Main {
    public static DirectedGraph directedGraph = new DirectedGraph();
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        /*try {
            ReadFiles.start();
        } catch (IOException e) {
            e.printStackTrace();
        }*/
        new Menu();
    }
}
