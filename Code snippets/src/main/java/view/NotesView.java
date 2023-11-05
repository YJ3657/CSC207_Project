package main.java.view;

import main.java.app.Constants;
import main.java.interface_adapter.home.HomeViewModel;
import main.java.interface_adapter.notes.AddCourseController;
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

    private final JPanel coursesDisplay;
    private final AddCourseController addCourseController;

    public NotesView(NotesViewModel notesViewModel,
                     ViewManagerModel viewManagerModel,
                     AddCourseController addCourseController) {
        this.notesViewModel = notesViewModel;
        this.viewManagerModel = viewManagerModel;
        this.notesViewModel.addPropertyChangeListener(this);
//        this.viewManagerModel.addPropertyChangeListener(this);
        this.addCourseController = addCourseController;
        this.notesDisplay = new JLabel("Notes");
        this.coursesDisplay = new JPanel();

        JLabel title = new JLabel("Notes Screen");

        JButton back = new JButton(notesViewModel.BACK_BUTTON_LABEL);

        JButton addCourse = new JButton("Add Course");

        addCourse.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        if (e.getSource().equals(addCourse)) {
//                            Icon defaultIcon = UIManager.getIcon("OptionPane.informationIcon");
                            String courseID = (String)JOptionPane.showInputDialog(
                                    NotesView.this,
                                    "Course ID: \n",
                                    "Dialog",
                                    JOptionPane.INFORMATION_MESSAGE,
                                    null, // TODO: Change this Icon
                                    null,
                                    "");
                            addCourseController.execute(courseID); // TODO: add the controller to the attributes
//                            courseID
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
                    viewManagerModel.setActiveView(Constants.HOME_VIEWNAME);
                    viewManagerModel.firePropertyChanged();
                }
            }
        });
        this.add(addCourse);
        this.add(back);
        this.add(title);
        this.add(coursesDisplay);
        this.add(notesDisplay);
    }


    @Override
    public void actionPerformed(ActionEvent e) {

    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        NotesState state = (NotesState) evt.getNewValue();
        if (evt.getPropertyName().equals(Constants.STATE_PROPNAME)) {
            setNotesDisplay(state);
        } else if (evt.getPropertyName().equals(Constants.COURSES_PROPNAME)) {
            setCoursesDisplay(state);
        }


    }

    private void setCoursesDisplay(NotesState state) {
        this.coursesDisplay.removeAll();

//        this.coursesDisplay.setLayout(new BoxLayout(coursesDisplay, BoxLayout.Y_AXIS));
//        Rectangle rec = this.getBounds();
//        rec.setSize(rec.width / 5, rec.height);
//        this.coursesDisplay.setBounds(rec);
        for (String course : state.getCourses()) {
            addButton(coursesDisplay, course);
        }
        this.coursesDisplay.revalidate();
        this.coursesDisplay.repaint();

    }

    private void addButton(JPanel coursesDisplay, String course) {
        JButton CourseButton = new JButton(course);
        coursesDisplay.add(CourseButton);
    }

    private void setNotesDisplay(NotesState state) {
        HashMap <String, String> notes = state.getNotes();
        notesDisplay.setText(notes.keySet() + notes.values().toString()); //TODO: Fix the UI, Jerry
        this.add(notesDisplay);
    }
}
