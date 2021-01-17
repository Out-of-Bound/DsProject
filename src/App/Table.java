package App;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import java.awt.*;
import java.awt.event.InputMethodEvent;
import java.awt.event.InputMethodListener;
import java.util.List;

public class Table {



    public static void showJTable(String tableName , String [] tableColumn , String[][] data){
        JFrame accountsJFrame = new JFrame(tableName);
        JPanel panel = new JPanel(new BorderLayout());
        accountsJFrame.setLocationRelativeTo(null);
        JTable table = new JTable(data, tableColumn) {
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
        table.getTableHeader().setFont(new Font("Vazir", Font.BOLD , 15));
        table.getTableHeader().setBackground(new Color(97, 85, 166));
        table.getTableHeader().setForeground(Color.WHITE);
        table.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
        table.setFont(new Font("Vazir", Font.PLAIN, 14));
        table.setBounds(30, 40, 400, 300);
        table.setRowHeight(25);
        JScrollPane sp = new JScrollPane(table);
        table.setEnabled(false);

        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment( JLabel.CENTER );
        for (int i = 0; i < tableColumn.length; i++) {
            table.getColumnModel().getColumn(i).setCellRenderer( centerRenderer );
        }
        table.getColumnModel().getColumn(0).setMaxWidth(50);

        JTextField searchField = new JTextField();
        TableRowSorter<TableModel> rowSorter = new TableRowSorter<>(table.getModel());

        searchField.getDocument().addDocumentListener(new DocumentListener(){

            @Override
            public void insertUpdate(DocumentEvent e) {
                String text = searchField.getText();

                if (text.trim().length() == 0) {
                    rowSorter.setRowFilter(null);
                } else {
                    rowSorter.setRowFilter(RowFilter.regexFilter("(?i)" + text));
                }
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                String text = searchField.getText();

                if (text.trim().length() == 0) {
                    rowSorter.setRowFilter(null);
                } else {
                    rowSorter.setRowFilter(RowFilter.regexFilter("(?i)" + text));
                }
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }

        });
        searchField.addInputMethodListener(new InputMethodListener() {
            @Override
            public void inputMethodTextChanged(InputMethodEvent event) {
                String text = searchField.getText();
                if (text.trim().length() == 0) {
                    rowSorter.setRowFilter(null);
                } else {
                    rowSorter.setRowFilter(RowFilter.regexFilter("(?i)" + text));
                }
            }

            @Override
            public void caretPositionChanged(InputMethodEvent event) {

            }
        });

        table.setRowSorter(rowSorter);

        panel.add(sp , BorderLayout.CENTER);
        panel.add(searchField , BorderLayout.NORTH);
        accountsJFrame.add(panel);
        accountsJFrame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        accountsJFrame.setVisible(true);
    }
}
