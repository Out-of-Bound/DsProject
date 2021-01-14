package App;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.datatransfer.DataFlavor;
import java.awt.dnd.DnDConstants;
import java.awt.dnd.DropTarget;
import java.awt.dnd.DropTargetDropEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.List;

public class Menu {

    private JPanel panel;
    private JButton phase1;
    private JButton phase2;
    private JButton phase3;
    private JButton phase4;
    private JLabel imgView;
    private JLabel labelChooseFolder;


    public Menu() {
        JFrame menuFrame = new JFrame("Menu");
        menuFrame.setContentPane(panel);
        menuFrame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        menuFrame.setSize(1920,1080);
        menuFrame.setLocationRelativeTo(null);
        menuFrame.setExtendedState(JFrame.MAXIMIZED_BOTH);

        Image image = null;
        try {
            BufferedImage orgImg = ImageIO.read(new File(".\\img\\main.jpg"));
            image = orgImg.getScaledInstance(850, 751,
                    Image.SCALE_SMOOTH);
        } catch (IOException e) {
            e.printStackTrace();
        }
        imgView.setIcon( new ImageIcon(image) );
        imgView.setBorder(new EmptyBorder( 0, 80, 0, 0));
        panel.setBorder(new EmptyBorder( 0, 0, 0, 90));

        menuFrame.setVisible(true);


        menuFrame.setDropTarget(new DropTarget(){
            public synchronized void drop(DropTargetDropEvent evt) {
                try {
                    evt.acceptDrop(DnDConstants.ACTION_COPY);
                    List<File> droppedFiles = (List<File>) evt.getTransferable().getTransferData(DataFlavor.javaFileListFlavor);
                    ReadFiles.startWithDragAndDrop(droppedFiles);
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        });







        phase1.addActionListener(e -> {
            new PhaseOne(menuFrame);
            menuFrame.setEnabled(false);
        });

        phase2.addActionListener(e -> {
            PhaseTow.show();
        });
        phase3.addActionListener(e -> {

        });
        phase4.addActionListener(e -> {

        });
        /*logout.addActionListener(e -> {
            if (JOptionPane.showConfirmDialog(menuFrame, "Are you sure you want to exit?" , "Exit", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE) == JOptionPane.YES_OPTION)
                System.exit(0);
        });*/

        menuFrame.addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosing(java.awt.event.WindowEvent windowEvent) {
                if (JOptionPane.showConfirmDialog(menuFrame, "Are you sure you want to exit?" , "Exit", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE) == JOptionPane.YES_OPTION)
                    System.exit(0);
            }
        });

        labelChooseFolder.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                super.mouseEntered(e);
                labelChooseFolder.setForeground(new Color(0 , 0 , 0));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                super.mouseExited(e);
                labelChooseFolder.setForeground(new Color(95 , 95, 95));
            }
        });
        Color mouseEnteredColor =  new Color(87 , 78 , 144);
        Color mouseExitedColor =  new Color(49 , 44 , 81);
        MouseAdapter mouseAdapter = new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                e.getComponent().setBackground(mouseEnteredColor);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                e.getComponent().setBackground(mouseExitedColor);
            }
        };

        phase1.addMouseListener(mouseAdapter);
        phase2.addMouseListener(mouseAdapter);
        phase3.addMouseListener(mouseAdapter);
        phase4.addMouseListener(mouseAdapter);
    }

}
