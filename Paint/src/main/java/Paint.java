import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class Paint {

    private DrawArea drawArea;
    private JButton colorBtn;
    private JButton clearBtn;
    private JSlider slider;
    private JButton fill;
    private JButton imageFill;
    private JButton affine;
    private JButton rotate;
    private JButton animation;


    ActionListener actionListener = new ActionListener() {
        public void actionPerformed(ActionEvent e) {{
                drawArea.clear();
            }
        }
    };


    public static void main(String[] args) {
        new Paint().show();
    }


    public void show() {

        final JFrame frame = new JFrame("Paint");

        frame.setLayout(new BorderLayout());

        drawArea = new DrawArea();

        //drawArea.setSize(1500,1500);

        frame.getContentPane().add(drawArea, BorderLayout.CENTER);

        JMenuBar buttons = new JMenuBar();

        buttons.setLayout(new BoxLayout(buttons,BoxLayout.X_AXIS));

        colorBtn = new JButton("ChooseColor");

        colorBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Color c  = JColorChooser.showDialog(null,"Choose colorBtn",Color.black);
                drawArea.setColor(c);
            }
        });

        clearBtn = new JButton("Clear");

        clearBtn.addActionListener(actionListener);

        slider = new JSlider(0,20);

        slider.setMinimumSize(new Dimension(200,0));

        slider.setValue(2);

        fill = new JButton("Fill");

        imageFill = new JButton("Image Fill");

        affine = new JButton("Affine");

        affine.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                drawArea.setAffine();
            }
        });

        rotate = new JButton("Rotate");

        animation = new JButton("Animation");

        animation.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                drawArea.doAnimation();
            }
        });

        rotate.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                drawArea.setRotate();
            }
        });

        imageFill.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    JFileChooser fileopen = new JFileChooser();

                    int ret = fileopen.showDialog(null, "Выберите фото");

                    File file = fileopen.getSelectedFile();


                    int x = frame.getWidth();

                    int y = frame.getHeight();

                    drawArea.fillImage(x,y,file);
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }
        });
        fill.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                Color c  = JColorChooser.showDialog(null,"Choose colorBtn",Color.black);

                int x = frame.getWidth();

                int y = frame.getHeight();

                drawArea.fill(c,x,y);

            }
        });

        slider.addChangeListener(new ChangeListener() {
            public void stateChanged(ChangeEvent e) {

                JSlider source = (JSlider)e.getSource();

                int fps = (int)source.getValue();

                drawArea.setSize(fps);

            }
        });

        buttons.add(colorBtn);

        buttons.add(clearBtn);

        buttons.add(fill);

        buttons.add(imageFill);

        buttons.add(affine);

        buttons.add(rotate);

        buttons.add(animation);

        buttons.add(slider);


        frame.getContentPane().add(buttons, BorderLayout.NORTH);

        frame.setSize(600, 600);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        frame.setVisible(true);


    }

}