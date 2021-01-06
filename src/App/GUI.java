package App;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import java.awt.*;

public class GUI {
    public static void showJTable(String [] tableColumn , String[][] data){
        JFrame accountsJFrame = new JFrame("Accounts");
        accountsJFrame.setLocationRelativeTo(null);
        JTable jt = new JTable(data, tableColumn);
        jt.setFont(new Font("Vazir", Font.PLAIN, 14));
        jt.setBounds(30, 40, 400, 300);
        jt.setRowHeight(25);
        JScrollPane sp = new JScrollPane(jt);
        jt.setEnabled(false);
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment( JLabel.CENTER );
        for (int i = 0; i < tableColumn.length; i++) {
            jt.getColumnModel().getColumn(i).setCellRenderer( centerRenderer );
        }
        jt.getColumnModel().getColumn(0).setMaxWidth(30);
        accountsJFrame.add(sp);
        accountsJFrame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        accountsJFrame.setVisible(true);
    }
}
