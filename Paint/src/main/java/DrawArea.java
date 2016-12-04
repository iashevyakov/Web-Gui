import java.awt.*;
import java.awt.event.*;
import java.awt.geom.AffineTransform;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.*;

import static java.lang.Thread.sleep;

public class DrawArea extends JComponent {


    private Image image;

    private Graphics2D g2;

    private int currentX, currentY, oldX, oldY;


    private Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();

    public DrawArea() {
        setDoubleBuffered(false);
        addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent e) {

                oldX = e.getX();
                oldY = e.getY();
                if (g2 != null) {

                    g2.drawLine(oldX, oldY, oldX, oldY);

                    repaint();
                }

            }
        });

        addMouseMotionListener(new MouseMotionAdapter() {
            public void mouseDragged(MouseEvent e) {

                currentX = e.getX();

                currentY = e.getY();

                if (g2 != null) {

                    g2.drawLine(oldX, oldY, currentX, currentY);

                    repaint();

                    oldX = currentX;
                    oldY = currentY;
                }
            }
        });
    }

    protected void paintComponent(Graphics g) {
        if (image == null) {
            image = createImage(getWidth(), getHeight());
            g2 = (Graphics2D) image.getGraphics();
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            clear();
        }

        g.drawImage(image, 0, 0, null);

    }

    public void clear() {
        g2.setPaint(Color.white);
        g2.fillRect(0, 0, getWidth(), getHeight());
        g2.setPaint(Color.black);
        repaint();
    }

    public void fill(Color c, int x, int y) {
        image = createImage(x, y);
        g2 = (Graphics2D) image.getGraphics();
        Color prevColor = (Color) g2.getPaint();
        g2.setPaint(c);
        g2.fillRect(0, 0, x, y);
        repaint();
        g2.setPaint(prevColor);
    }

    public void fillImage(int x, int y, File file) throws IOException {
        Color prevColor = (Color) g2.getPaint();
        image = createImage(x, y);
        g2 = (Graphics2D) image.getGraphics();
        Image im = ImageIO.read(file);
        g2.drawImage(im, 0, 0, x, y, this);
        repaint();
        g2.setPaint(prevColor);
    }

    public void setAffine() {
        clear();
        setImage();
        AffineTransform prevTransform = g2.getTransform();
        AffineTransform affineTransform = new AffineTransform(1, 0, 1 / Math.sqrt(3), 1, 0, 0);
        g2.transform(affineTransform);
        repaint();
    }

    public void setImage() {

        g2.fillRect(250, 250, 120, 100);

        repaint();

    }

    public void setRotate() {

        clear();
        setImage();
        AffineTransform affineTransform = g2.getTransform();
        affineTransform = AffineTransform.getRotateInstance(Math.PI / 4, 250, 250);
        g2.transform(affineTransform);
        repaint();

    }

    public void doAnimation() {
        Thread t = new Thread(new Runnable() {
            public void run() {
                while (true) {
                    setRotate();
                    try {
                        sleep(50);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    repaint();
                }
            }
        });
        t.start();
    }


    public void setColor(Color c) {
        g2.setPaint(c);
    }

    public void setSize(int size) {
        g2.setStroke(new BasicStroke(size));
    }

    public int getHeight() {
        return screenSize.height;
    }

    public int getWidth() {
        return screenSize.width;
    }


}