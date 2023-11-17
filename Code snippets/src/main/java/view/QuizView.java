package main.java.view;

import main.java.app.Constants;
import main.java.entity.Course;
import main.java.interface_adapter.ViewManagerModel;
import main.java.interface_adapter.notes.AddCourseController;
import main.java.interface_adapter.notes.NotesState;
import main.java.interface_adapter.notes.NotesViewModel;
import main.java.interface_adapter.quiz.QuizViewModel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.Map;
import java.util.Set;

public class QuizView extends JPanel implements ActionListener, PropertyChangeListener {
    public final String viewName = "Quiz";
    private final QuizViewModel quizViewModel;
    private final ViewManagerModel viewManagerModel;

    private final JScrollPane quizSpace;
    private final JLabel quizDisplay;

    public QuizView(QuizViewModel quizViewModel,
                     ViewManagerModel viewManagerModel) {
        super(new BorderLayout());
        this.quizViewModel = quizViewModel;
        this.viewManagerModel = viewManagerModel;
        this.quizViewModel.addPropertyChangeListener(this);
//        this.viewManagerModel.addPropertyChangeListener(this);
        this.quizDisplay = new JLabel("Quiz");
        this.quizSpace = new JScrollPane();

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.LEADING));

        JLabel title = new JLabel(Constants.QUIZ_VIEWNAME);

        JButton back = new JButton(quizViewModel.BACK_BUTTON_LABEL);

//        this.add(notesDisplay);
        back.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (e.getSource().equals(back)) {
                    viewManagerModel.setActiveView(Constants.NOTES_VIEWNAME);
                    viewManagerModel.firePropertyChanged();
                }
            }
        });
        buttonPanel.add(title);
        buttonPanel.add(back);
        this.add(buttonPanel, BorderLayout.NORTH);
        this.add(quizSpace, BorderLayout.CENTER);

//        this.add(quizSpace);
    }


    @Override
    public void actionPerformed(ActionEvent e) {

    }


    // TODO: Do property changes below
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