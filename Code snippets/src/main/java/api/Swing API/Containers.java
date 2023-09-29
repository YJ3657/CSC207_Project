import javax.swing.SwingUtilities;

import static java.lang.Thread.sleep;

public class Containers {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                OpeningPage open = new OpeningPage();
                open.setVisible(true);
                try {
                    sleep(3000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                open.dispose();
                Home homepage = new Home();
                homepage.setVisible(true);
            }
        });

    }
}

