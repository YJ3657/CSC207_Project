import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class OpeningPage extends JWindow {
    public OpeningPage() {
        this.setSize(300, 400);

        GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
        Point centerPoint = ge.getCenterPoint();
        int leftTopX = centerPoint.x - this.getWidth()/2;
        int leftTopY = centerPoint.y - this.getHeight()/2;
        this.setLocation(leftTopX, leftTopY);

        JLabel label = new JLabel();
        label.setIcon(new ImageIcon(getClass().getResource("Opening.png")));
        getContentPane().add(label, BorderLayout.CENTER);

        this.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                setVisible(false);
            }
        });
    }
}