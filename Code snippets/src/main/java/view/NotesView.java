package main.java.view;

import main.java.interface_adapter.NotesState;
import main.java.interface_adapter.NotesViewModel;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.HashMap;

public class NotesView extends JPanel implements ActionListener, PropertyChangeListener {
    public final String viewName = "Notes";
    private final NotesViewModel notesViewModel;

    public NotesView(NotesViewModel notesViewModel) {
        this.notesViewModel = notesViewModel;
        this.notesViewModel.addPropertyChangeListener(this);

        JLabel title = new JLabel("Notes Screen");
        HashMap <String, String> notes = notesViewModel.getState().getNotes();
        JLabel notesDisplay = new JLabel(notes.keySet() + notes.values().toString()); //TODO: I will change this later
        JButton back = new JButton(notesViewModel.BACK_BUTTON_LABEL);
        this.add(notesDisplay);
        this.add(back);
        this.add(title);
    }


    @Override
    public void actionPerformed(ActionEvent e) {

    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {

    }

    private void setNotesDisplay(NotesState state) {
        //TODO: Implement this method and call it in the propertyChange method
    }
}
