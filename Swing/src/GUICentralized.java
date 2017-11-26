import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GUICentralized extends JFrame implements ActionListener {

    // GUICentralized Stuff
    private JFrame frame;
    private JButton btn = new JButton("Increase");

    public GUICentralized() {

        frame = new JFrame("Increase");
        JPanel jp = new JPanel();

        // set Layout
        jp.setLayout(new FlowLayout());

        // add Button functionality
        btn.addActionListener(this);

        // add Button
        jp.add(btn);

        // print the frame
        frame.setContentPane(jp);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);

    }


    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        Object src = actionEvent.getSource();
        if(btn == src) {
            System.out.println("BTN pressed - centralized");
            Main.zahl++;
            btn.setText(Main.zahl + "");
        }
    }
}