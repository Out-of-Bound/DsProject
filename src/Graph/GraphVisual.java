package Graph;

import Vertices.Person;
import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;

public class GraphVisual {

    public GraphVisual(Person person) {
        SwingUtilities.invokeLater(() -> createAndShowGUI(person));
    }

    private static void createAndShowGUI(Person person) {
        JFrame visual = new JFrame("Graph Visual");
        visual.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        visual.add(new MyPanel(person));
        visual.setSize(1920, 1080);
        visual.setExtendedState(JFrame.MAXIMIZED_BOTH);
        visual.setVisible(true);
    }

}

class MyPanel extends JPanel {
    private Person person;
    private BufferedImage[] images = new BufferedImage[7];
    String[] imageNames = { "black", "red", "white", "green", "blue", "orange", "yellow" };
    int yOffset = 40, xOffset = 20;

    public MyPanel(Person person) {
        this.person = person;
        int i = 0;
        for ( String name : imageNames) {
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
        this.setBackground(new Color(255, 255, 255));
    }

    public Dimension getPreferredSize() {
        return new Dimension(250,200);
    }

    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        int h = getHeight();
        int w = getWidth();

        g.setFont(new Font("Vazir", Font.PLAIN, 14));
        specifyLocationsAndDraw(g, person, person.getRelOwns(),250,h/3, 200, 360);
        if (!person.getPhones().isEmpty())
            specifyLocationsAndDraw(g, person.getPhone(), person.getPhones(),1250,h/3, 200, 360);
        else
            g.drawString("تماسی وجود ندارد", 1250, h / 3);
        if (!person.getAccounts().isEmpty())
            specifyLocationsAndDraw(g,person.getAccount(), person.getAccounts(),750,h/3, 200, 360);
        else
            g.drawString("تراکنشی وجود ندارد",750, h/3);

    }

    private void specifyLocationsAndDraw (Graphics g, DirectedGraph.Vertex vertex, ArrayList<DirectedGraph.Vertex> array, int xCenter, int yCenter, int rad, int sweep){
        int my = yCenter - rad;
        int levels = array.size()/2 + array.size() % 2;
        int ratio = 360 / sweep;
        int space = (2 * rad / ratio) / levels;
        Vertex[] vertices = new Vertex[array.size() + 1];
        System.out.println(vertices[0]);
        vertices[0] = new Vertex(xCenter, yCenter, vertex.colorId);

        for (int i = 0; i < array.size(); i++){
            int a = (int) Math.abs(Math.pow(rad, 2) - Math.pow( my - yCenter , 2));
            int b = (int) (Math.pow(a, 1/2d));

            vertices[i+1] = new Vertex(b + xCenter, my, array.get(i).colorId);

            if (my > yCenter - rad && my < yCenter + rad){
                i++;

                if (i > array.size() - 1)
                    break;

                vertices[i+1] = new Vertex( xCenter - b, my, array.get(i).colorId);
            }

            my += space;

            if (my > yCenter + rad )
                my = yCenter + rad;
        }

        drawLines(g, vertices);
        drawPins(g, vertices);
    }

    private void drawLines(Graphics g, Vertex[] vertices){

        Graphics2D g2 = (Graphics2D) g;
        g2.setStroke(new BasicStroke(3));
        Vertex vc = vertices[0];
        for (int i = 1; i < vertices.length; i++) {
            Vertex v = vertices[i];
            g2.drawLine(v.x + xOffset, v.y + yOffset, vc.x + xOffset, vc.y + yOffset);
        }

    }

    private void drawPins(Graphics g, Vertex[] vertices){
        for ( Vertex v : vertices)
            g.drawImage(images[v.color] , v.x, v.y, this);
    }

    public static BufferedImage resize(BufferedImage img, int newW, int newH) {
        Image tmp = img.getScaledInstance(newW, newH, Image.SCALE_SMOOTH);
        BufferedImage dimg = new BufferedImage(newW, newH, BufferedImage.TYPE_INT_ARGB);

        Graphics2D g2d = dimg.createGraphics();
        g2d.drawImage(tmp, 0, 0, null);
        g2d.dispose();

        return dimg;
    }

}

class Vertex {

    int x ;
    int y ;
    int color ;

    public Vertex(int x, int y, int color) {
        this.x = x;
        this.y = y;
        this.color = color;
    }
}
