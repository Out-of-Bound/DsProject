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
        Scanner s = new Scanner(System.in);
        try {
            ReadFiles.start();
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println("1:Faze1 \n" + "2:Faze2 \n" + "3:Faze3 \n" + "4:Faze4 \n");

        if (s.nextInt() == 1) {
            System.out.println("1:people \n" + "2:phones \n" + "3:homes \n" +
                    "4:cars \n" + "5:accounts \n" + "6:calls \n" +
                    "7:relationships \n" + "8:transactions \n" + "9:ownerships \n" );
            FazeOne(s.nextInt());
        }
        if (s.nextInt() == 2){}
        if (s.nextInt() == 3){}
        if (s.nextInt() == 4){}

    }

    public static void FazeOne(int n){
        switch (n){
            case 1:{
                for (People people : People.getAllPeoples()) {
                    System.out.println(people.getFirstName() + " " + people.getLastName() + " " + people.getId()
                            + " " + people.getBirthDay() + " " + people.getCity() + " " + people.getWork());
                }
                break;
            }
            case 2:{
                for (Phone phone: Phone.getAllPhones()) {
                    System.out.println(phone.getOwnerSsn() + " " + phone.getId() + " " + phone.getOperatorName());
                }
                break;
            }
            case 3:{
                for (Home home : Home.getAllHomes()) {
                    System.out.println(home.getOwnerSsn() + " " + home.getPrice() + " " + home.getId()
                            + " " + home.getSize() + " " + home.getAddress());
                }
                break;
            }
            case 4:{
                for (Car car : Car.getAllCars()) {
                    System.out.println(car.getId() + " " + car.getOwnerSsn() + " " + car.getModel() + " " + car.getColor());
                }
                break;
            }
            case 5:{
                Account.print();
                break;
            }
            case 6:{
                for (Call call : Call.getAllCalls()) {
                    System.out.println(call.getStartingVertex().getId() + " " + call.getFinishingVertex().getId()
                            + " " + call.getId() + " " + call.getDate() + " " + call.getDuration());
                }
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
    }

}
