import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;

public class EventDispatch extends JFrame {
    public EventDispatch() {
        setTitle("Swing App");
        setSize(300,100);
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });
    }
    // Pushing the current thread to queue for later invoking
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                EventDispatch app = new EventDispatch();
                app.setVisible(true);
                System.out.println(Thread.currentThread().getName());
            }
        });
    }



}
