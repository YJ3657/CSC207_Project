package main.java.view;

import main.java.interface_adapter.instructions.InstructionsController;
import main.java.interface_adapter.logout.LogoutController;
import main.java.interface_adapter.home.HomeState;
import main.java.interface_adapter.home.HomeViewModel;
import main.java.interface_adapter.notes.OpenNotesController;


import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class HomeView extends JPanel implements ActionListener, PropertyChangeListener {
    public final String viewName = "home";
    private final JButton notes;
    private final OpenNotesController openNotesController;
    private final LogoutController logoutController;
    private final InstructionsController instructionsController;
    private HomeViewModel homeViewModel;
    private final JButton practice;
    private final JButton reminder;
    private final JButton instructions;
    private final JButton logout;

    public HomeView(HomeViewModel homeViewModel, OpenNotesController openNotesController, LogoutController
            logoutController, InstructionsController instructionsController) {
        this.openNotesController = openNotesController;
        this.homeViewModel = homeViewModel;
        this.logoutController = logoutController;
        this.instructionsController = instructionsController;
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

    @Override
    public void propertyChange(PropertyChangeEvent evt) {

    }
}