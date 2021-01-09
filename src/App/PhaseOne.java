package App;

import Edges.Call;
import Edges.Relationship;
import Vertices.*;
import javax.swing.*;

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

    public PhaseOne(JFrame MenuFrame){
        JFrame PhaseOneFrame = new JFrame("PhaseOne");
        PhaseOneFrame.setContentPane(panel);
        PhaseOneFrame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        PhaseOneFrame.setSize(600,700);
        PhaseOneFrame.setVisible(true);
        PhaseOneFrame.setLocationRelativeTo(null);

        peopleButton.addActionListener(e -> {
            People.print();
        });
        phonesButton.addActionListener(e -> {
            Phone.print();
        });
        homesButton.addActionListener(e -> {
            Home.print();
        });
        carsButton.addActionListener(e -> {
            Car.print();
        });
        accountsButton.addActionListener(e -> {
            Account.print();
        });
        callsButton.addActionListener(e -> {
            Call.print();
        });
        relationshipsButton.addActionListener(e -> {
            Relationship.print();
        });
        ownershipsButton.addActionListener(e -> {

        });
        transactionsButton.addActionListener(e -> {

        });

        PhaseOneFrame.addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosing(java.awt.event.WindowEvent windowEvent) {
                if (JOptionPane.showConfirmDialog(PhaseOneFrame, "Are you sure you want to exit?" , "Exit", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE) == JOptionPane.YES_OPTION) {
                    PhaseOneFrame.setVisible(false);
                    MenuFrame.setEnabled(true);
                }
            }
        });

    }
}
