import javax.swing.*;
import javax.swing.table.TableModel;
import java.awt.*;

public class Window {



        public static void main(String[] args){

            JFrame frame = new JFrame();

            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

            Object rowData[][] = { { "Row1-Column1", "Row1-Column2", "Row1-Column3" },
                    { "Row2-Column1", "Row2-Column2", "Row2-Column3" } };
            Object columnNames[] = { "Column One", "Column Two", "Column Three" };

            JTable table = new JTable(rowData, columnNames);

            JScrollPane scrollPane = new JScrollPane(table);
            frame.add(scrollPane, BorderLayout.WEST);
            JPanel panel = new JPanel();
            frame.add(panel,BorderLayout.EAST);
            placeComponents(panel);
            frame.setSize(300, 150);
            frame.setVisible(true);

        }
    private static void placeComponents(JPanel panel) {

        panel.setLayout(new BoxLayout(panel,BoxLayout.Y_AXIS));

        JLabel userLabel = new JLabel("Id");
        userLabel.setBounds(10, 10, 80, 25);
        panel.add(userLabel);

        JTextField idText = new JTextField(10);
        idText.setBounds(100, 10, 160, 25);
        panel.add(idText);

        JLabel titleLabel = new JLabel("Title");
        titleLabel.setBounds(10, 40, 80, 25);
        panel.add(titleLabel);

        JTextField titleField = new JTextField(20);
        titleField.setBounds(100, 40, 160, 25);
        panel.add(titleField);

        JLabel authorLabel = new JLabel("Author");
        authorLabel.setBounds(10, 40, 80, 25);
        panel.add(authorLabel);

        JTextField authorField = new JTextField(20);
        authorField.setBounds(100, 40, 160, 25);
        panel.add(authorField);

        JLabel numberLabel = new JLabel("Number");
        numberLabel.setBounds(10, 40, 80, 25);
        panel.add(numberLabel);

        JTextField numberField = new JTextField(20);
        numberField.setBounds(100, 40, 160, 25);
        panel.add(numberField);

        JLabel booleanLable = new JLabel("isPrinted");
        booleanLable.setBounds(10, 40, 80, 25);
        panel.add(booleanLable);

        JTextField booleanField = new JTextField(20);
        booleanField.setBounds(100, 40, 160, 25);
        panel.add(booleanField);

        JButton loginButton = new JButton("Save");
        loginButton.setBounds(10, 80, 80, 25);
        panel.add(loginButton);

    }


}
