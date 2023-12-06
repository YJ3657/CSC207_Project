package main.java.view;

import javax.swing.*;
import main.java.interface_adapter.ViewManagerModel;
import main.java.interface_adapter.chatbot.ChatbotController;
import main.java.interface_adapter.chatbot.ChatbotState;
import main.java.interface_adapter.chatbot.ChatbotViewModel;
import main.java.interface_adapter.login.LoginState;
import main.java.interface_adapter.notes.NotesState;
import main.java.interface_adapter.signup.SignupState;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class ChatbotPanel extends JPanel implements ActionListener, PropertyChangeListener {

    public final String viewName = "chatbot";
    private final ChatbotViewModel chatbotViewModel;
    private final JScrollPane msgArea;
    private final JTextField promptArea;

    private final JButton minimize;
    private final JButton send;

    private final ChatbotController chatbotController;

    public ChatbotPanel(ChatbotViewModel chatbotViewModel, ChatbotController chatbotController){
        this.chatbotViewModel = chatbotViewModel;
        this.chatbotController = chatbotController;

        JLabel title = new JLabel("Chatbot");
        title.setAlignmentX(Component.CENTER_ALIGNMENT);

        JPanel buttons = new JPanel();
        minimize = new JButton(chatbotViewModel.MINIMIZE_BUTTON_LABEL);
        buttons.add(minimize);
        send = new JButton(chatbotViewModel.SEND_BUTTON_LABEL);
        buttons.add(send);

        minimize.addActionListener(  // This creates an anonymous subclass of ActionListener and instantiates it.
                new ActionListener() {
                    public void actionPerformed(ActionEvent evt) {
                        if (evt.getSource().equals(minimize)) {
                            ChatbotPanel.this.setVisible(false);
                        }
                    }
                }
        );

        send.addActionListener(  // This creates an anonymous subclass of ActionListener and instantiates it.
                new ActionListener() {
                    public void actionPerformed(ActionEvent evt) {
                        if (evt.getSource().equals(send)) {
                            chatbotController.execute(chatbotViewModel.getState().getPromptArea());
                        }
                    }
                }
        );

        msgArea = new JScrollPane();
        msgArea.setEnabled(false);

        promptArea = new JTextField();

        promptArea.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
                ChatbotState currentState = chatbotViewModel.getState();
                currentState.setPromptArea(promptArea.getText() + e.getKeyChar());
                chatbotViewModel.setState(currentState);
            }

            @Override
            public void keyPressed(KeyEvent e) {
            }

            @Override
            public void keyReleased(KeyEvent e) {
            }
        });
    }
    @Override
    public void actionPerformed(ActionEvent e) {
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
    }
}
