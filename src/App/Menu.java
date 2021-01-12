package App;

import javax.swing.*;

public class Menu {

    private JPanel panel;
    private JButton phase1;
    private JButton phase2;
    private JButton phase3;
    private JButton phase4;
    private JButton logout;

    public Menu() {
        JFrame MenuFrame = new JFrame("Menu");
        MenuFrame.setContentPane(panel);
        MenuFrame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        MenuFrame.setSize(600,500);
        MenuFrame.setVisible(true);
        MenuFrame.setLocationRelativeTo(null);

        phase1.addActionListener(e -> {
            new PhaseOne(MenuFrame);
            MenuFrame.setEnabled(false);
        });
        phase2.addActionListener(e -> {
            PhaseTow.show();
            MenuFrame.setEnabled(false);
        });
        phase3.addActionListener(e -> {

            MenuFrame.setEnabled(false);
        });
        phase4.addActionListener(e -> {

            MenuFrame.setEnabled(false);
        });
        logout.addActionListener(e -> {
            if (JOptionPane.showConfirmDialog(MenuFrame, "Are you sure you want to exit?" , "Exit", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE) == JOptionPane.YES_OPTION)
                System.exit(0);
        });

        MenuFrame.addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosing(java.awt.event.WindowEvent windowEvent) {
                if (JOptionPane.showConfirmDialog(MenuFrame, "Are you sure you want to exit?" , "Exit", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE) == JOptionPane.YES_OPTION)
                    System.exit(0);
            }
        });
    }

}
