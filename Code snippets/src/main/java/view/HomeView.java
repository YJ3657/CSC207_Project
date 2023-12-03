package main.java.view;

import main.java.app.Constants;
import main.java.entity.Notes;
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
    private final JButton practice;
    private final JButton reminder;
    private final JButton instructions;
    private final JButton logout;

    public HomeView(HomeViewModel homeViewModel, OpenNotesController openNotesController, LogoutController
            logoutController, InstructionsController instructionsController, ReminderController reminderController) {
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
        practice = new JButton(HomeViewModel.PRACTICE_LABEL);
        buttons.add(practice);
        reminder = new JButton(HomeViewModel.REMINDERS_LABEL);
        buttons.add(reminder);
        instructions = new JButton(HomeViewModel.INSTRUCTIONS_LABEL);
        buttons.add(instructions);
        logout = new JButton(HomeViewModel.LOGOUT_LABEL);
        buttons.add(logout);
        this.add(logo);
        this.add(buttons);
        this.add(getReminderPanel());
//        this.add(title);


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
        practice.addActionListener(this);

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

    private JPanel getReminderPanel() {
        JPanel reminderPanel = new JPanel(new BorderLayout());
        String[] reminderList = new String[5];
        reminderList[0] = "Sample Reminder 1";
        reminderList[1] = "Sample Reminder 2";
        reminderList[2] = "Sample Reminder 3";
        reminderList[3] = "Sample Reminder 4";
        reminderList[4] = "Sample Reminder 5";
        JList<String> homeList = new JList<>(reminderList);
        homeList.setFixedCellHeight(80);
        homeList.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createTitledBorder("To Do"),
                BorderFactory.createEmptyBorder(10, 10, 10, 10)));
        reminderPanel.add(new JScrollPane(homeList), BorderLayout.CENTER);
        reminderPanel.setPreferredSize(new Dimension(800, 400));
        return reminderPanel;
    }


    @Override
    public void propertyChange(PropertyChangeEvent evt) {

    }
}