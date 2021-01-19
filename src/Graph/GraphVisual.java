package Graph;

import Vertices.Person;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;

public class GraphVisual {

    private BufferedImage[] images = new BufferedImage[7];
    String[] imageNames = {"black", "red", "white", "green", "blue", "orange", "yellow"};

    public GraphVisual(Person person) {
        int i = 0;
        for (String name : imageNames) {
            URL resource = getClass().getResource(name + ".png");
            try {
                BufferedImage tmp = ImageIO.read(resource);
                tmp = resize(tmp, 40, 60);
                images[i] = tmp;
                i++;
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        SwingUtilities.invokeLater(() -> createAndShowGUI(person));
    }

    private BufferedImage resize(BufferedImage img, int newW, int newH) {
        Image tmp = img.getScaledInstance(newW, newH, Image.SCALE_SMOOTH);
        BufferedImage dimg = new BufferedImage(newW, newH, BufferedImage.TYPE_INT_ARGB);

        Graphics2D g2d = dimg.createGraphics();
        g2d.drawImage(tmp, 0, 0, null);
        g2d.dispose();

        return dimg;
    }

    private JLabel getLabel(String str){
        JLabel text = new JLabel(str);
        text.setFont(new Font("Vazir", Font.PLAIN, 18));
        text.setForeground(Color.WHITE);
        text.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
        return text;
    }

    private void createAndShowGUI(Person person) {
        JFrame visual = new JFrame("Graph Visual");
        visual.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        visual.add(new MyPanel(person), BorderLayout.CENTER);
        JPanel panelHelp = new JPanel(new GridLayout(1, 8));
        JPanel panel = new JPanel(new BorderLayout());
        Color panelBack = new Color(56, 56, 56);
        panelHelp.setBackground(panelBack);
        panelHelp.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
        String[] works = {"قاچاقچی", "مضنون", "شخص عادی", "ماشین", "خونه", "شماره تلفن", "حساب بانکی"};
        Color[] colors = {Color.BLACK, Color.RED, Color.WHITE, Color.orange, Color.blue, Color.yellow, Color.green};
        for (int i = 0; i < 3; i++) {
            JPanel panel1 = new JPanel(new GridLayout(3, -1));
            for (int j = 3 * i; j <3*i+3 ; j++) {
                if (j>6)break;
                JLabel icon = new JLabel("\uD83D\uDCCC");
                ImageIcon imageIcon = new ImageIcon(images[j].getScaledInstance(32, 32, Image.SCALE_SMOOTH));
                icon.setForeground(colors[j]);
                icon.setFont(new Font("", Font.PLAIN, 30));
                icon.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
                panel1.add(getLabel(works[j]));
                panel1.add(icon);
            }
            panel1.setBackground(panelBack);
            if (i==2){
                for (int j = 0; j < 4; j++) {
                    panel1.add(new Label());
                }
            }
            if (i!= 0 && i != 2) {
                panelHelp.add(new Panel());
            }
            panel.setBorder(new EmptyBorder(50,0,50,50) {
            });
            panelHelp.add(panel1);
        }

        panel.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
        panel.setBackground(panelBack);
        panel.add(panelHelp , BorderLayout.EAST);

        visual.add(panel, BorderLayout.SOUTH);
        visual.setSize(1920, 1080);
        visual.setExtendedState(JFrame.MAXIMIZED_BOTH);
        visual.setVisible(true);
    }

    class MyPanel extends JPanel {
        private Person person;
        int yOffset = 40, xOffset = 20;

        public MyPanel(Person person) {
            this.person = person;

            this.setBackground(new Color(255, 255, 255));
        }

        public Dimension getPreferredSize() {
            return new Dimension(250, 200);
        }

        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            int h = getHeight();
            int w = getWidth();

            g.setFont(new Font("Vazir", Font.PLAIN, 14));


            if (!person.getPhones().isEmpty())
                specifyLocationsAndDraw(g, person, person.getRelOwns(), 250, h / 3, 200, 360);
            else
                g.drawString("خویشاندی و مالکیتی وجود ندارد", 250, h / 3);

            if (!person.getPhones().isEmpty())
                specifyLocationsAndDraw(g, person.getPhone(), person.getPhones(), 1250, h / 3, 200, 360);
            else
                g.drawString("تماسی وجود ندارد", 1250, h / 3);

            if (!person.getAccounts().isEmpty())
                specifyLocationsAndDraw(g, person.getAccount(), person.getAccounts(), 750, h / 3, 200, 360);
            else
                g.drawString("تراکنشی وجود ندارد", 750, h / 3);

        }

        private void specifyLocationsAndDraw(Graphics g, DirectedGraph.Vertex vertex, ArrayList<DirectedGraph.Vertex> array, int xCenter, int yCenter, int rad, int sweep) {
            int my = yCenter - rad;
            int levels = array.size() / 2 + array.size() % 2;
            int ratio = 360 / sweep;
            int space = (2 * rad / ratio) / levels;
            Vertex[] vertices = new Vertex[array.size() + 1];

            vertices[0] = new Vertex(xCenter, yCenter, vertex.colorId);

            for (int i = 0; i < array.size(); i++) {
                int a = (int) Math.abs(Math.pow(rad, 2) - Math.pow(my - yCenter, 2));
                int b = (int) (Math.pow(a, 1 / 2d));

                vertices[i + 1] = new Vertex(b + xCenter, my, array.get(i).colorId);

                if (my > yCenter - rad && my < yCenter + rad) {
                    i++;

                    if (i > array.size() - 1)
                        break;

                    vertices[i + 1] = new Vertex(xCenter - b, my, array.get(i).colorId);
                }

                my += space;

                if (my > yCenter + rad)
                    my = yCenter + rad;
            }

            drawLines(g, vertices);
            drawPins(g, vertices);
        }

        private void drawLines(Graphics g, Vertex[] vertices) {

            Graphics2D g2 = (Graphics2D) g;
            g2.setStroke(new BasicStroke(3));
            Vertex vc = vertices[0];
            for (int i = 1; i < vertices.length; i++) {
                Vertex v = vertices[i];
                g2.drawLine(v.x + xOffset, v.y + yOffset, vc.x + xOffset, vc.y + yOffset);
            }

        }

        private void drawPins(Graphics g, Vertex[] vertices) {
            for (Vertex v : vertices)
                g.drawImage(images[v.color], v.x, v.y, this);
        }


    }

    class Vertex {

        int x;
        int y;
        int color;

        public Vertex(int x, int y, int color) {
            this.x = x;
            this.y = y;
            this.color = color;
        }
    }

}


