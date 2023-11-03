package main.java.view;

import main.java.interface_adapter.home.HomeViewModel;
import main.java.interface_adapter.notes.NotesState;
import main.java.interface_adapter.notes.NotesViewModel;
import main.java.interface_adapter.ViewManagerModel;

import javax.swing.*;
import java.awt.*;
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

        JButton addCourse = new JButton("Add Course");

        addCourse.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        if (e.getSource().equals(addCourse)) {
//                            Icon defaultIcon = UIManager.getIcon("OptionPane.informationIcon");
                            String s = (String)JOptionPane.showInputDialog(
                                    NotesView.this,
                                    "Course ID: \n",
                                    "Dialog",
                                    JOptionPane.INFORMATION_MESSAGE,
                                    null, // TODO: Change this Icon
                                    null,
                                    "");
//
////If a string was returned, say so.
//                            if ((s != null) && (s.length() > 0)) {
//                                setLabel("Green eggs and... " + s + "!");
//                                return;
//                            }
//
////If you're here, the return value was null/empty.
//                            setLabel("Come on, finish the sentence!");
                        }
                    }
                }
        );


        back.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (e.getSource().equals(back)) {
                    viewManagerModel.setActiveView(homeViewModel.getViewName());
                    viewManagerModel.firePropertyChanged();
                }
            }
        });
        this.add(addCourse);
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
