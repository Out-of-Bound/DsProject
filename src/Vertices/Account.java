package Vertices;

import App.GUI;
import App.Main;
import Graph.DirectedGraph;
import java.util.HashMap;

public class Account extends DirectedGraph.Vertex {
    private static HashMap<String , Account> allAccounts = new HashMap<>();
    private String ownerSsn, bankName, iBan; // id = accountId

    public Account(String ownerSsn, String bankName, String iBan, String accountId) {
        super(accountId);
        this.ownerSsn = ownerSsn;
        this.bankName = bankName;
        this.iBan = iBan;
        allAccounts.put(accountId , this);
    }

    public String getOwnerSsn() {
        return ownerSsn;
    }

    public String getBankName() {
        return bankName;
    }

    public String getiBan() {
        return iBan;
    }

    public static HashMap<String , Account> getAllAccounts() {
        return allAccounts;
    }

    public static void print() {
        String[] tableColumn = {"ردیف", "کد ملی صاحب حساب", "نام و نام خانوادگی صاحب حساب" , "نام بانک", "شماره شبا", "شماره حساب"};
        String[][] data = new String[allAccounts.size()][tableColumn.length];
        int i =0;
        for (Account account : allAccounts.values() ) {
            Person owner = (Person) Main.directedGraph.getVertexByID(account.getOwnerSsn());
            data[i][0] = (i+1) + "";
            data[i][1] = account.getOwnerSsn();
            data[i][2] = owner.getFirstName() + " " + owner.getLastName();
            data[i][3] = account.getBankName();
            data[i][4] = account.getiBan();
            data[i][5] = account.getId();
            i++;
        }
        GUI.showJTable("Accounts" ,tableColumn , data);
    }

}
