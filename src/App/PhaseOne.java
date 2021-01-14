package App;

import Edges.Call;
import Edges.Ownership;
import Edges.Relationship;
import Edges.Transaction;
import Vertices.*;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

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
        PhaseOneFrame.setSize(1920,1080);
        Image image = null;
        try {
            BufferedImage orgImg = ImageIO.read(new File(".\\img\\phase1.jpg"));
            image = orgImg.getScaledInstance(636, 606,
                    Image.SCALE_SMOOTH);
        } catch (IOException e) {
            e.printStackTrace();
        }
        imgView.setIcon( new ImageIcon(image) );
        /*backButton.setBorder(new EmptyBorder( 0, 80, 0, 0));*/

        PhaseOneFrame.setVisible(true);
        PhaseOneFrame.setLocationRelativeTo(null);

        ActionListener btnActionListener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

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
            }
        };



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
