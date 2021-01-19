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
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
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
    private JLabel head;

    public PhaseOne(){

        JFrame phaseOneFrame = new JFrame("نمایش اطلاعات");
        phaseOneFrame.setContentPane(panel);
        phaseOneFrame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        phaseOneFrame.setSize(1920,1080);
        phaseOneFrame.setVisible(true);
        phaseOneFrame.setLocationRelativeTo(null);
        phaseOneFrame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        head.setBackground(new Color(49, 44, 81));

        Image image = null;
        try {
            BufferedImage orgImg = ImageIO.read(new File(".\\img\\phase1.jpg"));
            image = orgImg.getScaledInstance(636, 606, Image.SCALE_SMOOTH);
        } catch (IOException e) {
            e.printStackTrace();
        }
        imgView.setIcon( new ImageIcon(image) );
        imgView.setBorder(new EmptyBorder( 0, 50, 0, 0));

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
                phaseOneFrame.setVisible(false);
            }
        };

        Color buttonColor = accountsButton.getBackground();

        MouseAdapter mouseAdapter = new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                JButton button = (JButton) e.getComponent();
                if (button.isEnabled())
                    button.setBackground(button.getBackground().brighter());
            }

            @Override
            public void mouseExited(MouseEvent e) {
                e.getComponent().setBackground(buttonColor);
            }
        };

        JButton[] buttons = {peopleButton , phonesButton , callsButton , accountsButton , transactionsButton
        , backButton , carsButton , ownershipsButton , relationshipsButton , homesButton};

        for (JButton button : buttons) {
            button.addActionListener(btnActionListener);
            button.addMouseListener(mouseAdapter);
        }


    }
}
