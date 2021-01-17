package App;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableCellRenderer;
import java.awt.*;

public class Table {

    public static void showJTable(String tableName ,String [] tableColumn , String[][] data){
        JFrame accountsJFrame = new JFrame(tableName);
        accountsJFrame.setLocationRelativeTo(null);
        JTable jt = new JTable(data, tableColumn) {
            public Component prepareRenderer(TableCellRenderer renderer, int row, int column) {
                Component returnComp = super.prepareRenderer(renderer, row, column);
                Color alternateColor = new Color(227, 228, 254);
                Color whiteColor = Color.WHITE;
                if (!returnComp.getBackground().equals(getSelectionBackground())) {
                    Color bg = (row % 2 == 0 ? alternateColor : whiteColor);
                    returnComp.setBackground(bg);
                }
                return returnComp;
            }
        };
        jt.getTableHeader().setFont(new Font("Vazir", Font.BOLD , 15));
        jt.getTableHeader().setBackground(new Color(97, 85, 166));
        jt.getTableHeader().setForeground(Color.WHITE);
        jt.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
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
        jt.getColumnModel().getColumn(0).setMaxWidth(50);

        accountsJFrame.add(sp);
        accountsJFrame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        accountsJFrame.setVisible(true);
    }
}
