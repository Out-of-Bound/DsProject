package App;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.datatransfer.DataFlavor;
import java.awt.dnd.DnDConstants;
import java.awt.dnd.DropTarget;
import java.awt.dnd.DropTargetDropEvent;
import java.awt.event.ActionListener;
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
    private JButton btnChooseFolder;

    public Menu() {

        JFrame menuFrame = new JFrame("Menu");
        menuFrame.setContentPane(panel);
        menuFrame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        menuFrame.setSize(1920,1080);
        menuFrame.setLocationRelativeTo(null);
        menuFrame.setExtendedState(JFrame.MAXIMIZED_BOTH);

        if (ReadFiles.isFilesReady()){
            enableButtons();
        }

        Image image = null;
        try {
            BufferedImage orgImg = ImageIO.read(new File(".\\img\\main.jpg"));
            image = orgImg.getScaledInstance(850, 751, Image.SCALE_SMOOTH);
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
                    if (ReadFiles.isFilesReady())
                        enableButtons();
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        });

        ActionListener btnActionListener = e -> {

            Object source = e.getSource();
            if (phase1.equals(source)) {
                new PhaseOne();
            } else if (phase2.equals(source)) {
                PhaseTow.show();
            } else if (phase3.equals(source)) {
                PhaseThree.show();
            } else if (phase4.equals(source)) {
                PhaseFour.start();
            } else if (btnChooseFolder.equals(source)) {
                ReadFiles.start();
                if (ReadFiles.isFilesReady())
                    enableButtons();
            }
        };

        btnChooseFolder.addActionListener(btnActionListener);
        phase1.addActionListener(btnActionListener);
        phase2.addActionListener(btnActionListener);
        phase3.addActionListener(btnActionListener);
        phase4.addActionListener(btnActionListener);

        menuFrame.addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosing(java.awt.event.WindowEvent windowEvent) {
                if (JOptionPane.showConfirmDialog(menuFrame, "Are you sure you want to exit?" , "Exit", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE) == JOptionPane.YES_OPTION)
                    System.exit(0);
            }
        });


        Color mouseEnteredColor =  new Color(87 , 78 , 144);
        Color mouseExitedColor =  new Color(49 , 44 , 81);
        /*MouseAdapter mouseAdapter = new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                JButton button = (JButton) e.getComponent();
                if (button.isEnabled())
                button.setBackground(mouseEnteredColor);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                e.getComponent().setBackground(mouseExitedColor);
            }
        };

        phase1.addMouseListener(mouseAdapter);
        phase2.addMouseListener(mouseAdapter);
        phase3.addMouseListener(mouseAdapter);
        phase4.addMouseListener(mouseAdapter);*/

    }

    private void enableButtons(){
        phase1.setEnabled(true);
        phase2.setEnabled(true);
        phase3.setEnabled(true);
        phase4.setEnabled(true);
    }

    public void fileReadStart () {
        btnChooseFolder.setIcon( new ImageIcon(this.getClass().getResource("searching-anim.gif")) );
        btnChooseFolder.setText("");
        btnChooseFolder.setBorder(null);
        btnChooseFolder.setBackground(new Color(255,255,255));
    }

    public void fileReadEnd (){
        Image image = null;
        try {
            BufferedImage orgImg = ImageIO.read(new File(".\\img\\file.jpg"));
            image = orgImg.getScaledInstance(78, 100, Image.SCALE_SMOOTH);
        } catch (IOException e) {
            e.printStackTrace();
        }
        btnChooseFolder.setIcon( new ImageIcon(image) );
        btnChooseFolder.setText("");
        btnChooseFolder.setBackground(new Color(255, 255 , 255));
        btnChooseFolder.setBorder(null);
        labelChooseFolder.setText("فایل " + ReadFiles.getFolderName() + " خوانده شد");
        enableButtons();
    }

    public void changeLabel(String fileName){
        labelChooseFolder.setText("در حال خواندن فایل " + fileName);
    }

    private void createUIComponents() {
        // TODO: place custom component creation code here
    }
}
