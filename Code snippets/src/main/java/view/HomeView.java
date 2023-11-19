package main.java.view;

import main.java.interface_adapter.home.HomeState;
import main.java.interface_adapter.home.HomeViewModel;
import main.java.interface_adapter.notes.OpenNotesController;


import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class HomeView extends JPanel implements ActionListener, PropertyChangeListener {
    public final String viewName = "home";
    private final JButton notes;
    private final OpenNotesController openNotesController;
    private HomeViewModel homeViewModel;
    private final JButton practice;
    private final JButton reminder;

    public HomeView(HomeViewModel homeViewModel, OpenNotesController openNotesController) {
        this.openNotesController = openNotesController;
        this.homeViewModel = homeViewModel;
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
        this.add(logo);
        this.add(buttons);
//        this.add(title);


        notes.addActionListener(this);
        practice.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource().equals(notes)) {
            HomeState currentstate = homeViewModel.getState();
            openNotesController.execute(currentstate.getUsername());
        } else if (e.getSource().equals(practice)){

        }
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {

    }
}