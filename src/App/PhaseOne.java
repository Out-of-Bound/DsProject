package App;

import Edges.Call;
import Edges.Ownership;
import Edges.Relationship;
import Edges.Transaction;
import Vertices.*;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PhaseOne {

    private JPanel panel;
    private JButton peopleButton;
    private JButton phonesButton;
    private JButton homesButton;
    private JButton carsButton;
    private JButton accountsButton;
    private JButton callsButton;
    private JButton relationshipsButton;
    private JButton ownershipsButton;
    private JButton transactionsButton;
    private JButton backButton;
    private JLabel imgView;

    public PhaseOne(JFrame MenuFrame){

        JFrame PhaseOneFrame = new JFrame("PhaseOne");
        PhaseOneFrame.setContentPane(panel);
        PhaseOneFrame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        PhaseOneFrame.setSize(600,550);
        PhaseOneFrame.setVisible(true);
        PhaseOneFrame.setLocationRelativeTo(null);



        ActionListener btnActionListener = e -> {
            Object source = e.getSource();
            if (peopleButton.equals(source)) {
                Person.print();
            } else if (phonesButton.equals(source)) {
                Phone.print();
            } else if (accountsButton.equals(source)) {
                Account.print();
            } else if (homesButton.equals(source)) {
                Home.print();
            } else if (carsButton.equals(source)) {
                Car.print();
            } else if (callsButton.equals(source)){
                Call.print();
            } else if (ownershipsButton.equals(source)){
                Ownership.print();
            } else if (transactionsButton.equals(source)){
                Transaction.print();
            } else if (relationshipsButton.equals(source)){
                Relationship.print();
            } else if (backButton.equals(source)){
                PhaseOneFrame.setVisible(false);
                MenuFrame.setEnabled(true);
            }
        };

        peopleButton.addActionListener(btnActionListener);
        homesButton.addActionListener(btnActionListener);
        phonesButton.addActionListener(btnActionListener);
        carsButton.addActionListener(btnActionListener);
        callsButton.addActionListener(btnActionListener);
        accountsButton.addActionListener(btnActionListener);
        ownershipsButton.addActionListener(btnActionListener);
        relationshipsButton.addActionListener(btnActionListener);
        transactionsButton.addActionListener(btnActionListener);
/*
        PhaseOneFrame.addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosing(java.awt.event.WindowEvent windowEvent) {
                if (JOptionPane.showConfirmDialog(PhaseOneFrame, "Are you sure you want to back Menu?" , "Exit", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE) == JOptionPane.YES_OPTION) {
                    PhaseOneFrame.setVisible(false);
                    MenuFrame.setEnabled(true);
                }
            }
        });


 */
    }
}
