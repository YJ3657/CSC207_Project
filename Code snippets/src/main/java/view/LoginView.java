package main.java.view;

import main.java.interface_adapter.login.LoginController;
import main.java.interface_adapter.login.LoginState;
import main.java.interface_adapter.login.LoginViewModel;
import main.java.interface_adapter.signup.SignupController;
import main.java.interface_adapter.signup.SignupState;
import main.java.interface_adapter.signup.SignupViewModel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class LoginView extends JPanel implements ActionListener, PropertyChangeListener {
    public final String viewName = "log in";
    private final LoginViewModel loginViewModel;

    private final SignupViewModel signupViewModel;

    final JTextField usernameInputField = new JTextField(15);
    private final JLabel usernameErrorField = new JLabel();

    final JPasswordField passwordInputField = new JPasswordField(15);
    private final JLabel passwordErrorField = new JLabel();

    final JTextField signUpusernameInputField = new JTextField(15);

    final JPasswordField signUppasswordInputField = new JPasswordField(15);

    final JPasswordField signUprepasswordInputField = new JPasswordField(15);

    final JButton logIn;
    final JButton cancel;
    final JButton signUp;
    private final LoginController loginController;

    private final SignupController signupController;

    public LoginView(LoginViewModel loginViewModel, LoginController controller, SignupController signupController,
                     SignupViewModel signupViewModel) {

        this.loginController = controller;
        this.loginViewModel = loginViewModel;
        this.loginViewModel.addPropertyChangeListener(this);
        this.signupController = signupController;
        this.signupViewModel = signupViewModel;

        JLabel title = new JLabel("Login Screen");
        title.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel usernameLabel = new JLabel("Username");
        JTextField usernameTextField = usernameInputField;

        JLabel passwordLabel = new JLabel("Password");
        final JTextField[] passwordTextField = {passwordInputField};

        JPanel buttons = new JPanel();
        logIn = new JButton(loginViewModel.LOGIN_BUTTON_LABEL);
        buttons.add(logIn);
        cancel = new JButton(loginViewModel.CANCEL_BUTTON_LABEL);
        buttons.add(cancel);
        signUp = new JButton(loginViewModel.SIGNUP_BUTTON_LABEL);
        buttons.add(signUp);


        logIn.addActionListener(  // This creates an anonymous subclass of ActionListener and instantiates it.
                new ActionListener() {
                    public void actionPerformed(ActionEvent evt) {
                        if (evt.getSource().equals(logIn)) {
                            LoginState currentState = loginViewModel.getState();
                            loginController.execute(
                                    currentState.getUsername(),
                                    currentState.getPassword()
                            );
                        }
                    }
                }
        );

        cancel.addActionListener(this);

        signUp.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        JPanel signupPanel = new JPanel();
                        signupPanel.add(new JLabel("Username"));
                        signupPanel.add(signUpusernameInputField);
                        signupPanel.add(new JLabel("Password"));
                        signupPanel.add(signUppasswordInputField);
                        signupPanel.add(new JLabel("Retype Password"));
                        signupPanel.add(signUprepasswordInputField);
                        if (e.getSource().equals(signUp)){
                            int result = JOptionPane.showConfirmDialog(null, signupPanel, "Signup",
                                    JOptionPane.OK_CANCEL_OPTION);
                            SignupState currentState = signupViewModel.getState();
                            if (result == JOptionPane.OK_OPTION){
                                signupController.execute(currentState.getUsername(), currentState.getPassword(), currentState.getRepeatPassword());
                            }
                        }
                    }
                }
        );

        usernameInputField.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
                LoginState currentState = loginViewModel.getState();
                currentState.setUsername(usernameInputField.getText() + e.getKeyChar());
                loginViewModel.setState(currentState);
            }

            @Override
            public void keyPressed(KeyEvent e) {
            }

            @Override
            public void keyReleased(KeyEvent e) {
            }
        });
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        passwordInputField.addKeyListener(
                new KeyListener() {
                    @Override
                    public void keyTyped(KeyEvent e) {
                        LoginState currentState = loginViewModel.getState();
                        currentState.setPassword(passwordInputField.getText() + e.getKeyChar());
                        loginViewModel.setState(currentState);
                    }

                    @Override
                    public void keyPressed(KeyEvent e) {
                    }

                    @Override
                    public void keyReleased(KeyEvent e) {
                    }
                });

        signUpusernameInputField.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
                SignupState currentState = signupViewModel.getState();
                currentState.setUsername(signUpusernameInputField.getText() + e.getKeyChar());
                signupViewModel.setState(currentState);
            }

            @Override
            public void keyPressed(KeyEvent e) {
            }

            @Override
            public void keyReleased(KeyEvent e) {
            }
        });
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        signUppasswordInputField.addKeyListener(
                new KeyListener() {
                    @Override
                    public void keyTyped(KeyEvent e) {
                        SignupState currentState = signupViewModel.getState();
                        currentState.setPassword(signUppasswordInputField.getText() + e.getKeyChar());
                        signupViewModel.setState(currentState);
                    }

                    @Override
                    public void keyPressed(KeyEvent e) {
                    }

                    @Override
                    public void keyReleased(KeyEvent e) {
                    }
                });
        signUprepasswordInputField.addKeyListener(
                new KeyListener() {
                    @Override
                    public void keyTyped(KeyEvent e) {
                        SignupState currentState = signupViewModel.getState();
                        currentState.setRepeatPassword(signUprepasswordInputField.getText() + e.getKeyChar());
                        signupViewModel.setState(currentState);
                    }

                    @Override
                    public void keyPressed(KeyEvent e) {
                    }

                    @Override
                    public void keyReleased(KeyEvent e) {
                    }
                });

        this.add(title);
        this.add(usernameLabel);
        this.add(usernameTextField);
        this.add(usernameErrorField);
        this.add(passwordLabel);
        this.add(passwordTextField[0]);
        this.add(passwordErrorField);
        this.add(buttons);
    }



    /**
     * React to a button click that results in evt.
     */
    public void actionPerformed(ActionEvent evt) {
        System.out.println("Click " + evt.getActionCommand());
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        LoginState state = (LoginState) evt.getNewValue();
        setFields(state);
    }

    private void setFields(LoginState state) {
        usernameInputField.setText(state.getUsername());
    }


}
