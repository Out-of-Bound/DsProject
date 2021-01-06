package Vertices;
import Graph.DirectedGraph;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.HashSet;

public class Account extends DirectedGraph.Vertex {
    private static HashSet<Account> allAccounts = new HashSet<>();
    private String ownerSsn, bankName, accountId; // id = iBan;
    private static String[] FIELDS = {"Row", "Owner ssn", "Bank name", "iBan", "Account id"};

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
        JFrame accountsJFrame = new JFrame("Accounts");
        accountsJFrame.setLocationRelativeTo(null);
        String[][] row = new String[allAccounts.size()][FIELDS.length];
        System.out.println(allAccounts.size());
        int i =0;
        for (Account account : allAccounts) {
            row[i][0] = (i+1) + "";
            row[i][1] = account.getOwnerSsn();
            row[i][2] = account.getBankName();
            row[i][3] = account.getId();
            row[i][4] = account.getAccountId();
            i++;
        }
        JTable jt = new JTable(row, FIELDS);
        jt.setFont(new Font("Arial", Font.PLAIN, 14));
        jt.setBounds(30, 40, 400, 300);
        JScrollPane sp = new JScrollPane(jt);
        jt.setEnabled(false);
        accountsJFrame.add(sp);
        accountsJFrame.setSize(900, 800);
        accountsJFrame.setLocationRelativeTo(null);
        accountsJFrame.setVisible(true);
    }
}
