package App;

import Edges.Call;
import Edges.Ownership;
import Edges.Relationship;
import Edges.Transaction;
import Graph.DirectedGraph;
import Vertices.*;

import java.io.IOException;
import java.util.Scanner;

public class Main {
    public static DirectedGraph directedGraph = new DirectedGraph();
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        try {
            ReadFiles.start();
        } catch (IOException e) {
            e.printStackTrace();
        }

        new Menu();
        /*System.out.print("1:Phase1 \n" + "2:Phase2 \n" + "3:Phase3 \n" + "4:Phase4 \nand Enter another fot exit: ");

        int code = scanner.nextInt();
        if (code == 1) {
            System.out.println("\n1:people \n" + "2:phones \n" + "3:homes \n" +
                    "4:cars \n" + "5:accounts \n" + "6:calls \n" +
                    "7:relationships \n" + "8:transactions \n" + "9:ownerships \n" );
            PhaseOne(scanner.nextInt());
        }
        else if (code == 2){}
        else if (code == 3){}
        else if (code == 4){}
        else
            System.exit(0);
*/
    }

   /* public static void PhaseOne(int n){
        switch (n){
            case 1:{
                People.print();
                break;
            }
            case 2:{
                Phone.print();
                break;
            }
            case 3:{
                Home.print();
                break;
            }
            case 4:{
                Car.print();
                break;
            }
            case 5:{
                Account.print();
                break;
            }
            case 6:{
                Call.print();
                break;
            }
            case 7:{
                for (Relationship relationship : Relationship.getAllRelationships()) {
                    System.out.println(relationship.getStartingVertex().getId() + " " + relationship.getFinishingVertex().getId()
                             + " " + relationship.getRelation() + " " + relationship.getDate());
                }
                break;
            }
            case 8:{
                for (Transaction transaction : Transaction.getAllTransactions()) {
                    System.out.println(transaction.getStartingVertex().getId() + " " + transaction.getFinishingVertex().getId()
                            + " " + transaction.getId() + " " + transaction.getDate() + " " + transaction.getAmount());
                }
                break;
            }
            case 9:{
                for (Ownership ownership : Ownership.getAllOwnerships()) {
                    System.out.println(ownership.getStartingVertex().getId() + " " + ownership.getFinishingVertex().getId()
                            + " " + ownership.getId() + " " + ownership.getDate() + " " + ownership.getAmount());
                }
                break;
            }
        }
        main(null);
    }*/

}
