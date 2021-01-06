package Vertices;
import App.GUI;
import App.Main;
import Graph.DirectedGraph;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

public class Account extends DirectedGraph.Vertex {
    private static HashMap<String , Account> allAccounts = new HashMap<>();
    private String ownerSsn, bankName, accountId; // id = iBan;

    public Account(String ownerSsn, String bankName, String iBan, String accountId) {
        super(iBan);
        this.ownerSsn = ownerSsn;
        this.bankName = bankName;
        this.accountId = accountId;
        allAccounts.put(accountId , this);
    }

    public String getOwnerSsn() {
        return ownerSsn;
    }

    public String getBankName() {
        return bankName;
    }

    public String getAccountId() {
        return accountId;
    }

    public static HashMap<String , Account> getAllAccounts() {
        return allAccounts;
    }

    public static void print() {
        String[] tableColumn = {"ردیف", "کد ملی صاحب حساب", "نام و نام خانوادگی صاحب حساب" , "نام بانک", "شماره شبا", "شماره حساب"};
        String[][] data = new String[allAccounts.size()][tableColumn.length];
        int i =0;
        for (Account account : new ArrayList<>(allAccounts.values()) ) {
            People owner = (People) Main.directedGraph.getVertexByID(account.getOwnerSsn());
            data[i][0] = (i+1) + "";
            data[i][1] = account.getOwnerSsn();
            data[i][2] = owner.getFirstName() + " "+ owner.getLastName();
            data[i][3] = account.getBankName();
            data[i][4] = account.getId();
            data[i][5] = account.getAccountId();
            i++;
        }
        GUI.showJTable("Accounts" ,tableColumn , data);
    }
}
