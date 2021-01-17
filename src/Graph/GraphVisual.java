package Graph;



import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;


public class GraphVisual {

    public GraphVisual() {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                createAndShowGUI();
            }
        });

    }

    private static void createAndShowGUI() {
        System.out.println("Created GUI on EDT? "+
                SwingUtilities.isEventDispatchThread());
        JFrame f = new JFrame("Swing Paint Demo");
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.add(new MyPanel());
        f.pack();
        f.setVisible(true);
    }
}

class MyPanel extends JPanel {
    private BufferedImage[] images = new BufferedImage[7];
    String[] imageNames = { "black", "red", "white", "green", "blue", "orange", "yellow" };
    int yOffset = 40, xOffset = 20;

    public MyPanel() {

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


    }

    public Dimension getPreferredSize() {
        return new Dimension(250,200);
    }

    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        int[] tmp = {0, 1, 2, 3 ,4 ,5 ,6, 1, 0, 1, 2, 3 ,4 ,5 ,6, 1, 0, 1, 2, 3 ,4 ,5 ,6, 1};
        Vertex[] vertices = specifyLocations(0, tmp,300,300, 200, 360);
        drawLines(g, vertices);
        drawPins(g, vertices);
    }

    private Vertex[] specifyLocations ( int centerColor, int[] colors, int xCenter, int yCenter, int rad, int sweep){
        int my = yCenter - rad;
        int levels = colors.length/2 + colors.length % 2;
        int ratio = 360 / sweep;
        int space = (2 * rad / ratio) / levels;
        Vertex[] vertices = new Vertex[colors.length + 1];
        System.out.println(vertices[0]);
        vertices[0] = new Vertex(xCenter, yCenter, centerColor);

        for (int i = 0; i < colors.length; i++){


            int a = (int) Math.abs(Math.pow(rad, 2) - Math.pow( my - yCenter , 2));
            int b = (int) (Math.pow(a, 1/2d));

            vertices[i+1] = new Vertex(b + xCenter, my, colors[i]);

            if (my > yCenter - rad && my < yCenter + rad){
                i++;

                if (i > colors.length - 1)
                    break;

                vertices[i+1] = new Vertex( xCenter - b, my, colors[i]);

            }

            my += space;

            if (my > yCenter + rad )
                my = yCenter + rad;
        }

        return vertices;
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
        for ( Vertex v : vertices) {
            g.drawImage(images[v.color] , v.x, v.y, this);
        }
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
