package main.java.view;

import main.java.interface_adapter.home.HomeViewModel;
import main.java.interface_adapter.notes.NotesState;
import main.java.interface_adapter.notes.NotesViewModel;
import main.java.interface_adapter.ViewManagerModel;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.HashMap;

public class NotesView extends JPanel implements ActionListener, PropertyChangeListener {
    public final String viewName = "Notes";
    private final NotesViewModel notesViewModel;
    private final ViewManagerModel viewManagerModel;
    private final JLabel notesDisplay;

    public NotesView(NotesViewModel notesViewModel, HomeViewModel homeViewModel, ViewManagerModel viewManagerModel) {
        this.notesViewModel = notesViewModel;
        this.viewManagerModel = viewManagerModel;
        this.notesViewModel.addPropertyChangeListener(this);
        this.viewManagerModel.addPropertyChangeListener(this);
        this.notesDisplay = new JLabel("Notes");

        JLabel title = new JLabel("Notes Screen");

        JButton back = new JButton(notesViewModel.BACK_BUTTON_LABEL);
        back.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (e.getSource().equals(back)) {
                    viewManagerModel.setActiveView(homeViewModel.getViewName());
                    viewManagerModel.firePropertyChanged();
                }
            }
        });
        this.add(back);
        this.add(title);
        this.add(notesDisplay);
    }


    @Override
    public void actionPerformed(ActionEvent e) {

    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        NotesState state = notesViewModel.getState();
        setNotesDisplay(state);
    }

    private void setNotesDisplay(NotesState state) {
        HashMap <String, String> notes = state.getNotes();
        notesDisplay.setText(notes.keySet() + notes.values().toString()); //TODO: I will change this later
        this.add(notesDisplay);
    }
}
