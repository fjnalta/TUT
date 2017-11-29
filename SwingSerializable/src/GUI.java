import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GUI extends JFrame implements ActionListener {

    // Swing Components
    JFrame frame;
    JPanel jp;
    JTextField txField;
    Button btnWrite, btnRead;

    MyReader reader;

    public GUI() {

        // initialize Reader
        reader = new MyReader();

        frame = new JFrame("FIS FOS");
        jp = new JPanel();

        // set Layout
        jp.setLayout(new FlowLayout());


        createButtons();


        // print the frame
        frame.setContentPane(jp);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();

        frame.setVisible(true);
    }

    private void createButtons() {

        btnWrite = new Button("Write to File");
        btnRead = new Button("Read from File");
        txField = new JTextField("aasdhgsoifhjgsrguh");

        // add Button functionality
        btnWrite.addActionListener(this);
        btnRead.addActionListener(this);

        // add Layout
        jp.add(btnWrite);
        jp.add(btnRead);
        jp.add(txField);
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        Object src = actionEvent.getSource();
        if(btnWrite == src) {
            reader.writeObjectToFile(txField.getText());

            // reset Text Field
            txField.setText("");
            frame.revalidate();

        }
        if(btnRead == src) {
            txField.setText(reader.readObjectFromFile());
            frame.revalidate();
        }
    }
}
