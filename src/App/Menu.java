package App;

import javax.swing.*;
import java.awt.datatransfer.DataFlavor;
import java.awt.dnd.DnDConstants;
import java.awt.dnd.DropTarget;
import java.awt.dnd.DropTargetDropEvent;
import java.io.File;
import java.util.List;

public class Menu {

    private JPanel panel;
    private JButton phase1;
    private JButton phase2;
    private JButton phase3;
    private JButton phase4;
    private JButton logout;

    public Menu() {
        JFrame menuFrame = new JFrame("Menu");
        menuFrame.setContentPane(panel);
        menuFrame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        menuFrame.setSize(600,400);
        menuFrame.setVisible(true);
        menuFrame.setLocationRelativeTo(null);
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
        logout.addActionListener(e -> {
            if (JOptionPane.showConfirmDialog(menuFrame, "Are you sure you want to exit?" , "Exit", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE) == JOptionPane.YES_OPTION)
                System.exit(0);
        });

        menuFrame.addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosing(java.awt.event.WindowEvent windowEvent) {
                if (JOptionPane.showConfirmDialog(menuFrame, "Are you sure you want to exit?" , "Exit", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE) == JOptionPane.YES_OPTION)
                    System.exit(0);
            }
        });
    }

}
