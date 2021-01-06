package Vertices;
import App.Main;
import Graph.DirectedGraph;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.HashSet;

public class Account extends DirectedGraph.Vertex {
    private static HashSet<Account> allAccounts = new HashSet<>();
    private String ownerSsn, bankName, accountId; // id = iBan;


    public Account(String ownerSsn, String bankName, String iBan, String accountId) {
        super(iBan);
        this.ownerSsn = ownerSsn;
        this.bankName = bankName;
        this.accountId = accountId;
        allAccounts.add(this);
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

    public static HashSet<Account> getAllAccounts() {
        return allAccounts;
    }


    public static void print() {
        String[] tableColumn = {"ردیف", "کد ملی صاحب حساب", "نام و نام خانوادگی صاحب حساب" , "نام بانک", "شماره شبا", "شماره حساب"};
        JFrame accountsJFrame = new JFrame("Accounts");
        accountsJFrame.setLocationRelativeTo(null);
        String[][] row = new String[allAccounts.size()][tableColumn.length];
        System.out.println(allAccounts.size());
        int i =0;
        for (Account account : allAccounts) {
            People owner = (People) Main.directedGraph.getVertexByID(account.getOwnerSsn());
            row[i][0] = (i+1) + "";
            row[i][1] = account.getOwnerSsn();
            row[i][2] = owner.getFirstName() + " "+ owner.getLastName();
            row[i][3] = account.getBankName();
            row[i][4] = account.getId();
            row[i][5] = account.getAccountId();
            i++;
        }
        JTable jt = new JTable(row, tableColumn);
        jt.setFont(new Font("Vazir", Font.PLAIN, 14));
        jt.setBounds(30, 40, 400, 300);
        jt.setRowHeight(25);
        JScrollPane sp = new JScrollPane(jt);
        jt.setEnabled(false);
        accountsJFrame.add(sp);
        accountsJFrame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        accountsJFrame.setVisible(true);
    }
}
