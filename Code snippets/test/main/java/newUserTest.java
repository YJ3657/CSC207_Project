package main.java;

import main.java.app.Constants;
import main.java.app.Main;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import main.java.view.LoginView;

import javax.swing.*;

import java.awt.*;

import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.fail;

public class newUserTest {

    @Test
    void run(){
        Constants.CURRENT_USER = null;
        Constants.CURRENT_USER_OBJ = null;
        String[] args = new String[0];
        try {
            Main.main(args);
        } catch (InterruptedException e){
            JButton loginButton = getLoginButton();
        }
    }

    public JButton getLoginButton(){
        JFrame app = null;
        Window[] windows = Window.getWindows();
        for (Window window : windows) {
            if (window instanceof JFrame) {
                app = (JFrame) window;
            }
        }
        assertNotNull(app);
        Component root = app.getComponent(0);

        Component cp = ((JRootPane) root).getContentPane();

        JPanel jp = (JPanel) cp;

        JPanel jp2 = (JPanel) jp.getComponent(0);

        LoginView sv = (LoginView) jp2.getComponent(0);

        JPanel buttons = (JPanel) sv.getComponent(4);

        return (JButton) buttons.getComponent(2); // this should be the login button
    }
}
