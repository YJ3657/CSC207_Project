import java.awt.BorderLayout;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.JButton;
import javax.swing.JFrame;


public class BasicWindow extends JFrame {
    public BasicWindow() {
        setTitle("Swing App");
        setSize(300, 100);
        getContentPane().add(new JButton("Ok"), BorderLayout.SOUTH);

        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });
    }

    public static void main(String[] args) {
        BasicWindow app = new BasicWindow();
        app.setVisible(true);
    }
}