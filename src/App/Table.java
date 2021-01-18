package App;

import Graph.GraphVisual;
import Vertices.Person;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.*;
import java.awt.*;
import java.awt.event.InputMethodEvent;
import java.awt.event.InputMethodListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Table {



    public static void showJTable(String tableName , String [] tableColumn , String[][] data){



        JFrame jFrame = new JFrame(tableName);

        jFrame.setLocationRelativeTo(null);
        DefaultTableModel model = new DefaultTableModel(data, tableColumn) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        JTable table = new JTable(model) {
            public Component prepareRenderer(TableCellRenderer renderer, int row, int column) {
                Component returnComp = super.prepareRenderer(renderer, row, column);
                Color alternateColor = new Color(227, 228, 254);
                Color whiteColor = Color.WHITE;
                if (!returnComp.getBackground().equals(getSelectionBackground())) {
                    Color bg = (row % 2 == 0 ? whiteColor : alternateColor);
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
        table.setEnabled(true);

        table.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                try{
                    Person person = (Person) Main.directedGraph.getVertexByID((String) table.getValueAt(table.getSelectedRow(), 2));
                    new GraphVisual(person);
                }catch (ClassCastException e1){
                    //e1.printStackTrace();
                }
            }
        });

        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment( JLabel.CENTER );
        for (int i = 0; i < tableColumn.length; i++) {
            table.getColumnModel().getColumn(i).setCellRenderer( centerRenderer );
        }
        table.getColumnModel().getColumn(0).setMaxWidth(50);

        table.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                String id = data[table.getSelectedRow()][1];
                System.out.println(id);
            }
        });

        JTextField searchField = new JTextField();
        searchField.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
        searchField.setFont(new Font("Vazir", Font.PLAIN, 14));
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

        JLabel label = new JLabel("جستجو: ");
        label.setFont(new Font("Vazir", Font.PLAIN, 14));
        JPanel panel = new JPanel(new BorderLayout());
        panel.add(label , BorderLayout.EAST);
        panel.add(searchField , BorderLayout.CENTER);
        jFrame.add(panel , BorderLayout.NORTH);
        jFrame.add(sp , BorderLayout.CENTER);
        jFrame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        jFrame.setVisible(true);
    }


}
