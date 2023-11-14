package main.java.view;

import main.java.app.Constants;
import main.java.entity.Course;
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
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class NotesView extends JPanel implements ActionListener, PropertyChangeListener {
    public final String viewName = "Notes";
    private final NotesViewModel notesViewModel;
    private final ViewManagerModel viewManagerModel;
    private final JLabel notesDisplay;

    private final JTabbedPane coursesDisplay;
    private final AddCourseController addCourseController;

    public NotesView(NotesViewModel notesViewModel,
                     ViewManagerModel viewManagerModel,
                     AddCourseController addCourseController) {
        super(new BorderLayout());
        this.notesViewModel = notesViewModel;
        this.viewManagerModel = viewManagerModel;
        this.notesViewModel.addPropertyChangeListener(this);
//        this.viewManagerModel.addPropertyChangeListener(this);
        this.addCourseController = addCourseController;
        this.notesDisplay = new JLabel("Notes");
        this.coursesDisplay = new JTabbedPane();
        coursesDisplay.setTabPlacement(JTabbedPane.TOP);

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.LEADING));

        JLabel title = new JLabel(Constants.NOTES_VIEWNAME);

        JButton back = new JButton(notesViewModel.BACK_BUTTON_LABEL);

        JButton addCourse = new JButton("Add Course");

//        this.add(notesDisplay);

        addCourse.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        if (e.getSource().equals(addCourse)) {
//                            Icon defaultIcon = UIManager.getIcon("OptionPane.questionIcon");
                            String courseID = (String)JOptionPane.showInputDialog(
                                    NotesView.this,
                                    "Course ID: \n",
                                    "Dialog",
                                    JOptionPane.INFORMATION_MESSAGE,
                                    null, // TODO: For Jerry: change this Icon
                                    null,
                                    "");
                            if (courseID != null) {
                                addCourseController.execute(courseID);
                            }
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
        buttonPanel.add(title);
        buttonPanel.add(addCourse);
        buttonPanel.add(back);
        this.add(buttonPanel, BorderLayout.NORTH);
        this.add(coursesDisplay, BorderLayout.CENTER);
        this.add(coursesDisplay);
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
        } else if (evt.getPropertyName().equals(Constants.ADD_COURSE_ERROR)) {
            JOptionPane.showMessageDialog(this, Constants.ADD_COURSE_ERROR);
        }
    }

    private void setCoursesDisplay(NotesState state) {
        this.coursesDisplay.removeAll();
        for (String course : state.getCourses()) {
            coursesDisplay.addTab(course, getTab());
        }
        this.coursesDisplay.revalidate();
        this.coursesDisplay.repaint();

    }

    private JPanel getTab() {
        JPanel tabPanel = new JPanel(new BorderLayout());
        JEditorPane notePad = new JTextPane();
        JScrollPane textArea = new JScrollPane(notePad);
//        tabPanel.add(notePad);

        JScrollPane noteTopics = new JScrollPane();

        JSplitPane splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, noteTopics, textArea);

        splitPane.setDividerLocation(Constants.NOTE_TOPICS_SIZE); // This sets the divider at 1/6th of the width of the split pane
        splitPane.setResizeWeight(Constants.NOTE_TOPICS_SIZE);

        tabPanel.add(splitPane, BorderLayout.CENTER);
        return tabPanel;
    }

    private void setNotesDisplay(NotesState state) {
        Map<String, Course> notes = state.getNotes();
        Set<String> courses = notes.keySet();
        this.coursesDisplay.removeAll();
        for (String course : courses) {
            coursesDisplay.addTab(course, getTab());
        }
    }
}
