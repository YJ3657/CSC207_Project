package main.java.view;

import main.java.app.Constants;
import main.java.entity.Course;
import main.java.interface_adapter.ViewManagerModel;
import main.java.interface_adapter.notes.AddCourseController;
import main.java.interface_adapter.notes.NotesState;
import main.java.interface_adapter.notes.NotesViewModel;
import main.java.interface_adapter.quiz.QuizState;
import main.java.interface_adapter.quiz.QuizViewModel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;
import java.util.Map;
import java.util.Set;

public class QuizView extends JPanel implements ActionListener, PropertyChangeListener {
    public final String viewName = "Quiz";
    private final QuizViewModel quizViewModel;
    private final ViewManagerModel viewManagerModel;

    private final JPanel quizPanel;

    public QuizView(QuizViewModel quizViewModel,
                     ViewManagerModel viewManagerModel) {
        super(new BorderLayout());
        this.quizViewModel = quizViewModel;
        this.viewManagerModel = viewManagerModel;
        this.quizViewModel.addPropertyChangeListener(this);
        this.quizPanel = new JPanel();

        JScrollPane quizScrollPane = new JScrollPane();
        quizPanel.setLayout(new BoxLayout(quizPanel, BoxLayout.Y_AXIS));
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.LEADING));

        JLabel title = new JLabel(Constants.QUIZ_VIEWNAME);

        JButton back = new JButton(quizViewModel.BACK_BUTTON_LABEL);

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
        quizScrollPane.setViewportView(quizPanel);
        this.add(quizScrollPane, BorderLayout.CENTER);

        // Testing:
//        ArrayList<String> questions = new ArrayList<>();
//        questions.add("1. What is the definition of Limit:");
//        questions.add("2. What is the definition of Continuty:");
//        ArrayList<String> answers = new ArrayList<>();
//        answers.add("The definition of limit is this");
//        answers.add("The deifnition of Continuity is that");
//        setUpQuiz(questions, answers);
    }

    public JPanel addQuestion(String question, String answer) {
        JPanel questionComponent = new JPanel(new BorderLayout());
        JLabel questionArea = new JLabel(question);

        int height = Constants.FRAME_HEIGHT / 4;
        int width = Constants.FRAME_WIDTH / 3;

        Dimension preferredSize = new Dimension(width, height);
        questionComponent.setPreferredSize(preferredSize);
        questionComponent.setMinimumSize(preferredSize);
        questionComponent.setMaximumSize(preferredSize);
//        questionArea.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JPanel answerPanel = new JPanel();
        answerPanel.setLayout(new BoxLayout(answerPanel, BoxLayout.Y_AXIS));

        JTextArea answerArea = new JTextArea();
        JScrollPane answerScrollPane = new JScrollPane(answerArea);
        JTextArea solutionArea = new JTextArea(answer);
        solutionArea.setEditable(false);

        JScrollPane solutionScrollPane = new JScrollPane(solutionArea);
        solutionScrollPane.setVisible(false);
        Dimension solutionDim = new Dimension((int) width, (int) height / 3);
        solutionScrollPane.setMinimumSize(solutionDim);
        solutionScrollPane.setPreferredSize(solutionDim);
        answerPanel.add(answerScrollPane);
        answerPanel.add(solutionScrollPane);

        JButton showAnswerBtn = new JButton("Show Answer");
//        answerPanel.add(showAnswerBtn);

        showAnswerBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                boolean isVisible = solutionScrollPane.isVisible();
                solutionScrollPane.setVisible(!isVisible);
                if (isVisible) {
                    showAnswerBtn.setText("Show Answer");
                } else {
                    showAnswerBtn.setText("Hide Answer");
                }
                questionComponent.revalidate();
                questionComponent.repaint();
            }

        });

        questionComponent.add(questionArea, BorderLayout.NORTH);
        questionComponent.add(answerPanel, BorderLayout.CENTER);
        questionComponent.add(showAnswerBtn, BorderLayout.SOUTH);
        return questionComponent;
    }

    public void setUpQuiz(ArrayList<String> questions, ArrayList<String> answers) {
        quizPanel.removeAll();
        for (int i = 0; i < questions.size(); i++) {
            String question = questions.get(i);
            String answer = answers.get(i);
            quizPanel.add(addQuestion(question, answer));
            quizPanel.revalidate();
            quizPanel.repaint();
        }
    }



    @Override
    public void actionPerformed(ActionEvent e) {

    }
//
//
//    // TODO: Do property changes below
    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        QuizState state = (QuizState) evt.getNewValue();
        setUpQuiz(state.getQuestions(), state.getAnswers());


//        NotesState state = (NotesState) evt.getNewValue();
//        if (evt.getPropertyName().equals(Constants.STATE_PROPNAME)) {
//            setNotesDisplay(state);
//        } else if (evt.getPropertyName().equals(Constants.COURSES_PROPNAME)) {
//            setCoursesDisplay(state);
//        } else if (evt.getPropertyName().equals(Constants.ADD_COURSE_ERROR)) {
//            JOptionPane.showMessageDialog(this, Constants.ADD_COURSE_ERROR);
//        }
    }
//
//    private void setCoursesDisplay(NotesState state) {
//        this.coursesDisplay.removeAll();
//        for (String course : state.getCourses()) {
//            coursesDisplay.addTab(course, getTab());
//        }
//        this.coursesDisplay.revalidate();
//        this.coursesDisplay.repaint();
//    }
//
//    private JPanel getTab() {
//        JPanel tabPanel = new JPanel(new BorderLayout());
//        JEditorPane notePad = new JTextPane();
//        JScrollPane textArea = new JScrollPane(notePad);
////        tabPanel.add(notePad);
//
//        JScrollPane noteTopics = new JScrollPane();
//
//        JSplitPane splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, noteTopics, textArea);
//
//        splitPane.setDividerLocation(Constants.NOTE_TOPICS_SIZE); // This sets the divider at 1/6th of the width of the split pane
//        splitPane.setResizeWeight(Constants.NOTE_TOPICS_SIZE);
//
//        tabPanel.add(splitPane, BorderLayout.CENTER);
//        return tabPanel;
//    }
//
//    private void setNotesDisplay(NotesState state) {
//        Map<String, Course> notes = state.getNotes();
//        Set<String> courses = notes.keySet();
//        this.coursesDisplay.removeAll();
//        for (String course : courses) {
//            coursesDisplay.addTab(course, getTab());
//        }
//    }
}