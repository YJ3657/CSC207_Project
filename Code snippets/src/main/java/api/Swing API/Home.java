import java.awt.GraphicsEnvironment;
import java.awt.Point;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;

public class Home extends JFrame {
    public Home() {
        this.setSize(600, 600);

        this.setTitle("Home");

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
        Point centerPoint = ge.getCenterPoint();
        int leftTopX = centerPoint.x - this.getWidth()/2;
        int leftTopY = centerPoint.y - this.getWidth()/2;
        this.setLocation(leftTopX, leftTopY);
    }
}
