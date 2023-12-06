package main.java.view;

import main.java.app.Constants;
import main.java.entity.Notes;
import main.java.entity.Reminder;
import main.java.interface_adapter.instructions.InstructionsController;
import main.java.interface_adapter.logout.LogoutController;
import main.java.interface_adapter.home.HomeState;
import main.java.interface_adapter.home.HomeViewModel;
import main.java.interface_adapter.notes.NotesState;
import main.java.interface_adapter.notes.OpenNotesController;
import main.java.interface_adapter.reminder.ReminderController;


import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class HomeView extends JPanel implements ActionListener, PropertyChangeListener {
    public final String viewName = "home";
    private final JButton notes;
    private final OpenNotesController openNotesController;
    private final LogoutController logoutController;
    private final InstructionsController instructionsController;
    private final ReminderController reminderController;
    private HomeViewModel homeViewModel;
    private final JButton reminder;
    private final JButton instructions;
    private final JButton logout;
    private final JButton chatbotButton;

    public HomeView(HomeViewModel homeViewModel, OpenNotesController openNotesController, LogoutController
            logoutController, InstructionsController instructionsController, ReminderController reminderController, ChatbotPanel chatbotPanel) throws InterruptedException {
        this.openNotesController = openNotesController;
        this.homeViewModel = homeViewModel;
        this.logoutController = logoutController;
        this.instructionsController = instructionsController;
        this.reminderController = reminderController;
        homeViewModel.addPropertyChangeListener(this);

//        JLabel title = new JLabel(HomeViewModel.TITLE_LABEL);
//        title.setAlignmentX(Component.CENTER_ALIGNMENT);
        ImageIcon imageIcon = new ImageIcon("Code snippets/resources/jlabel.png");
        JLabel logo = new JLabel(imageIcon);
        JPanel buttons = new JPanel();
        notes = new JButton(HomeViewModel.NOTES_LABEL);
        buttons.add(notes);
        reminder = new JButton(HomeViewModel.REMINDERS_LABEL);
        buttons.add(reminder);
        instructions = new JButton(HomeViewModel.INSTRUCTIONS_LABEL);
        buttons.add(instructions);
        logout = new JButton(HomeViewModel.LOGOUT_LABEL);
        buttons.add(logout);

        ImageIcon chatbotIcon = new ImageIcon("chatbot.png");
        Image image = chatbotIcon.getImage();
        Image newImg = image.getScaledInstance(30, 30,  java.awt.Image.SCALE_SMOOTH); // scale it the smooth way
        ImageIcon newChatbotIcon = new ImageIcon(newImg);
        chatbotButton = new JButton(newChatbotIcon);
        chatbotButton.setMargin(new Insets(1,1,1,1));
        buttons.add(chatbotButton);

        this.add(logo);
        this.add(buttons);
        this.add(getReminderPanel());
//        this.add(title);

        chatbotButton.addActionListener( new ActionListener() {
            @Override public void actionPerformed(ActionEvent e) {
                if (e.getSource().equals(chatbotButton)) {
                    chatbotPanel.setVisible(true);
                }
            }
        }
        );

        notes.addActionListener(this);
        logout.addActionListener( new ActionListener() {
            @Override public void actionPerformed(ActionEvent e) {
            if (e.getSource().equals(logout)) {
                logoutController.execute();
            }
            }
        }
        );
        instructions.addActionListener( new ActionListener() {
          @Override public void actionPerformed(ActionEvent e) {
              if (e.getSource().equals(instructions)) {
                  instructionsController.execute();
              }
          }
        }
        );
        reminder.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (e.getSource().equals(reminder)) {
                    HomeState homeViewState = homeViewModel.getState();
                    reminderController.execute(homeViewState.getUsername());
                }
            }
        });
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource().equals(notes)) {
            HomeState currentstate = homeViewModel.getState();
            openNotesController.execute();
        }
//        if (e.getSource().equals(notes)) {
//            HomeState currentstate = homeViewModel.getState();
//            openNotesController.execute(currentstate.getUsername());
//        } else if (e.getSource().equals(practice)){
//
//        }

    }

    private synchronized JPanel getReminderPanel() throws InterruptedException {
        System.out.println("executed");
        String[] reminderList = new String[0];
        JPanel reminderPanel = new JPanel(new BorderLayout());
        while(Constants.CURRENT_USER == null) Thread.sleep(1000);;

            Map<String, Reminder> reminders = this.reminderController.execute(Constants.CURRENT_USER);
            reminderList = new String[reminders.keySet().size()];
            int idx = 0;

            for (String courseid : reminders.keySet()) {
                if (courseid.equals("NONE")) continue;
                StringBuilder message = new StringBuilder(courseid + " : ");
                if (reminders.get(courseid).getReviewMaterials().isEmpty()) {
                    message.append("You don't have anything to review yet!");
                    reminderList[idx] = message.toString();
                    idx += 1;
                    continue;
                }
                for (int chapter : reminders.get(courseid).getReviewMaterials().keySet()) {
                    message.append("(chap").append(chapter).append(" ").append(reminders.get(courseid).getReviewMaterials().get(chapter)).append(")");
                }
                reminderList[idx] = message.toString();
                idx += 1;
            }

        JList<String> homeList = new JList<>(reminderList);
        homeList.setFixedCellHeight(80);
        homeList.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createTitledBorder("What to Review"),
                BorderFactory.createEmptyBorder(10, 10, 10, 10)));
        reminderPanel.add(new JScrollPane(homeList), BorderLayout.CENTER);
        reminderPanel.setPreferredSize(new Dimension(800, 400));
        return reminderPanel;
    }


    @Override
    public void propertyChange(PropertyChangeEvent evt) {

    }
}