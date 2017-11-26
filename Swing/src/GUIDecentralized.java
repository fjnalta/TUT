import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GUIDecentralized extends JFrame {

    // GUICentralized Stuff
    private JFrame frame;
    private JButton btn = new JButton("Increase");

    public GUIDecentralized() {

        frame = new JFrame("Increase");
        JPanel jp = new JPanel();

        // set Layout
        jp.setLayout(new FlowLayout());

        // add Button functionality
        btn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                System.out.println("BTN pressed - decentralized");
                Main.zahl2++;
                btn.setText(Main.zahl2 + "");
            }
        });

        // add Button
        jp.add(btn);

        // print the frame
        frame.setContentPane(jp);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }
}
