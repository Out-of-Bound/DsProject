import Graph.DirectedGraph;

import java.io.IOException;
import java.util.Scanner;

public class Main {
    public static DirectedGraph directedGraph = new DirectedGraph();
    public static void main(String[] args) throws IOException {
        Scanner scanner = new Scanner(System.in);
        ReadFiles.readAccFile();
    }
}