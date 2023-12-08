package main.java.view;

import main.java.app.Constants;
import main.java.entity.Notes;
import main.java.interface_adapter.add_Question_Definition.DefQuesController;
import main.java.interface_adapter.add_course.AddCourseController;
import main.java.interface_adapter.notes.*;
import main.java.interface_adapter.ViewManagerModel;
import main.java.interface_adapter.quiz.QuizController;

import javax.swing.*;
import javax.swing.event.*;
import java.awt.*;
import java.awt.event.*;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class NotesView extends JPanel implements ActionListener, PropertyChangeListener {
    public final String viewName = "Notes";
    private final NotesViewModel notesViewModel;
    private final ViewManagerModel viewManagerModel;
    private final JLabel notesDisplay;

    public final JTabbedPane coursesDisplay;
    private final AddCourseController addCourseController;
    private final CreateNotesController createNotesController;
    private final DeleteNotesController deleteNotesController;
    private final DefQuesController defQuesController;

    private final JButton markAsDefinition;
    private final JButton markAsQuestion;

    // used to debug

    public NotesView(NotesViewModel notesViewModel,
                     ViewManagerModel viewManagerModel,
                     AddCourseController addCourseController,
                     CreateNotesController createNotesController,
                     QuizController quizController, DefQuesController defQuesController,
                     DeleteNotesController deleteNotesController) {
        super(new BorderLayout());
        this.notesViewModel = notesViewModel;
        this.viewManagerModel = viewManagerModel;
        this.createNotesController = createNotesController;
        this.deleteNotesController = deleteNotesController;
        this.defQuesController = defQuesController;
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

        JButton addNotes = new JButton("+");

        JButton saveNotes = new JButton("Save");

        JButton deleteNotes = new JButton("Delete");

        JButton generateQuiz = new JButton("Generate Quiz");

        this.markAsDefinition = new JButton("Mark as Definition");

        this.markAsQuestion = new JButton("Mark as Question");

        generateQuiz.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (e.getSource().equals(generateQuiz)) {
                    int activeIndex = coursesDisplay.getSelectedIndex();
                    String courseId = coursesDisplay.getTitleAt(activeIndex);  //checks what course currently on
                    quizController.execute(courseId);
                }
            }
        });

        markAsDefinition.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int activeIndex = coursesDisplay.getSelectedIndex();
                String courseId = coursesDisplay.getTitleAt(activeIndex);
                JPanel panel = (JPanel) NotesView.this.coursesDisplay.getSelectedComponent();
                JSplitPane scrollPane = (JSplitPane) panel.getComponent(0);
                JScrollPane notePad = (JScrollPane) scrollPane.getRightComponent();
                JTextPane textPane = (JTextPane) notePad.getViewport().getView();

                if (e.getSource().equals(markAsDefinition)){
                    String potDefinition = textPane.getSelectedText();
                    String[] components = splitHighlightedText(potDefinition, ":");
                    defQuesController.execute(components[0], components[1], courseId, ":");
                }

            }
        });

        markAsQuestion.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int activeIndex = coursesDisplay.getSelectedIndex();
                String courseId = coursesDisplay.getTitleAt(activeIndex);
                JPanel panel = (JPanel) NotesView.this.coursesDisplay.getSelectedComponent();
                JSplitPane scrollPane = (JSplitPane) panel.getComponent(0);
                JScrollPane notePad = (JScrollPane) scrollPane.getRightComponent();
                JTextPane textPane = (JTextPane) notePad.getViewport().getView();

                if (e.getSource().equals(markAsQuestion)){
                    String potQuestion = textPane.getSelectedText();
                    String[] components = splitHighlightedText(potQuestion, "?");
                    defQuesController.execute(components[0], components[1], courseId, "?");
                }

            }


        });

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
                            NotesState currentstate = notesViewModel.getState();
                            currentstate.setSelectedcourse(courseID);
                            notesViewModel.setState(currentstate);
                            if (courseID != null) {
                                addCourseController.execute(courseID);
                            }
                        }
                    }
                }

        );

        coursesDisplay.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                int selectedIndex = coursesDisplay.getSelectedIndex();

                if (selectedIndex >= 0) {
                    String selectedTabTitle = coursesDisplay.getTitleAt(selectedIndex);

                    NotesState currentstate = notesViewModel.getState();
                    currentstate.setSelectedcourse(selectedTabTitle);
                    notesViewModel.setState(currentstate);
                }
            }
        });

        addNotes.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (e.getSource().equals(addNotes)){
                    JTextField notesTitle = new JTextField(null, 10);
                    JTextField chapterNo = new JTextField(null, 3);
                    JTextField notesContent = new JTextField(null,15);
                    JPanel title = new JPanel();
                    title.add(new JLabel("Topic"));
                    title.add(notesTitle);
                    title.add(new JLabel("#"));
                    title.add(chapterNo);
                    JPanel content = new JPanel();
                    content.add(notesContent);
                    notesTitle.addKeyListener(new KeyListener() {
                        @Override
                        public void keyTyped(KeyEvent e) {
                            NotesState currentState = notesViewModel.getState();
                            currentState.setNotesTitle(notesTitle.getText() + e.getKeyChar());
                            notesViewModel.setState(currentState);
                        }

                        @Override
                        public void keyPressed(KeyEvent e) {
                        }

                        @Override
                        public void keyReleased(KeyEvent e) {
                        }
                    });
                    chapterNo.addKeyListener(new KeyListener() {
                        @Override
                        public void keyTyped(KeyEvent e) {
                            NotesState currentState = notesViewModel.getState();
                            currentState.setChapterNo(chapterNo.getText() + e.getKeyChar());
                            notesViewModel.setState(currentState);
                        }

                        @Override
                        public void keyPressed(KeyEvent e) {
                        }

                        @Override
                        public void keyReleased(KeyEvent e) {
                        }
                    });

                    int result = JOptionPane.showConfirmDialog(null, title, "Topic",
                            JOptionPane.OK_CANCEL_OPTION);
                    if (result == JOptionPane.OK_OPTION){
                        NotesState currentstate = notesViewModel.getState();
                        int errorResult = 0;
                        for (String course : currentstate.getCourses()){
                            for(Notes notes : currentstate.getAllNotes().get(course)){
                                if (notes.getTitle().equals(currentstate.getNotesTitle())){
                                    errorResult += 1;
                                }
                            }
                        }
                        if (errorResult > 0){
                            JOptionPane.showMessageDialog(null,
                                    "Note already exists. Please enter different title."
                                    , "Warning", JOptionPane.ERROR_MESSAGE);
                        }else {
                            if (currentstate.getNotesContent().isEmpty()) {
                                currentstate.setNotesContent(" ");
                            }
                            createNotesController.execute(currentstate.getNotesTitle(), "",
                                    currentstate.getSelectedCourse(), Integer.parseInt(currentstate.getChapterNo()));
                            setNotesDisplay(currentstate);
                        }
                    }
                }

            }
        });

        back.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (e.getSource().equals(back)) {
                    viewManagerModel.setActiveView(Constants.HOME_VIEWNAME);
                    viewManagerModel.firePropertyChanged();
                }
            }
        });
        deleteNotes.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (e.getSource().equals(deleteNotes)) {
                    int result = JOptionPane.showConfirmDialog(null, "Are you sure you want to delete this note?",
                            "Warning", JOptionPane.OK_CANCEL_OPTION);
                    if (result == JOptionPane.OK_OPTION) {
                        NotesState currentstate = notesViewModel.getState();
                        Notes tbd = null;
                        for(Notes note : currentstate.getAllNotes().get(currentstate.getSelectedCourse())){
                            if (currentstate.getNotesTitle().equals(note.getTitle())){
                                tbd = note;
                            }
                        }
                        currentstate.getAllNotes().get(currentstate.getSelectedCourse()).remove(tbd);
                        deleteNotesController.execute(currentstate.getNotesTitle(), currentstate.getSelectedCourse(), tbd);
                        setNotesDisplay(currentstate);
                    }
                }
            }
        });
        saveNotes.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (e.getSource().equals(saveNotes)) {
                    NotesState currentstate = notesViewModel.getState();
                    if(currentstate.getAllTopics().get(currentstate.getSelectedCourse()).contains(currentstate.getNotesTitle())){
                        createNotesController.execute(currentstate.getNotesTitle(), currentstate.getNotesContent(),
                                currentstate.getSelectedCourse(), true);
                    }else{
                        JOptionPane.showMessageDialog(null,
                                "Please select a note.", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                }
            }
        });
        buttonPanel.add(title);
        buttonPanel.add(addCourse);
        buttonPanel.add(addNotes);
        buttonPanel.add(generateQuiz);
        buttonPanel.add(back);
        buttonPanel.add(saveNotes);
        buttonPanel.add(deleteNotes);
        buttonPanel.add(markAsDefinition);
        buttonPanel.add(markAsQuestion);

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
            coursesDisplay.addTab(course, getTab(course));
        }
        this.coursesDisplay.revalidate();
        this.coursesDisplay.repaint();
    }

    private JPanel getTab(String course) {
        JPanel tabPanel = new JPanel(new BorderLayout());
        JEditorPane notePad = new JTextPane();
        JScrollPane textArea = new JScrollPane(notePad);
        NotesState currentState = notesViewModel.getState();
//        tabPanel.add(notePad);

        ArrayList<String> topics = new ArrayList<>();
        Map<String, String> content = new HashMap<>();
        if ((currentState.getAllNotes() != null) && !(currentState.getAllNotes().isEmpty()) && !(currentState.getAllNotes().get(course) == null)) {
            for (Notes note : currentState.getAllNotes().get(course)) {
                topics.add(note.getTitle());
                content.put(note.getTitle(), note.getContents());
            }
        }
        String[] t = topics.toArray(new String[]{});
        JList<String> topicsList = new JList<>(t);

        topicsList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        topicsList.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                // Handle selection change here
                if (!e.getValueIsAdjusting()) {
                    String selectedTopic = topicsList.getSelectedValue();
                    content.remove(currentState.getNotesTitle());
                    content.put(currentState.getNotesTitle(), currentState.getNotesContent());
                    currentState.setNotesTitle(selectedTopic);
                    currentState.setNotesContent(content.get(selectedTopic));
                    updateNotePad(content.get(selectedTopic),notePad);
                }
            }
        });
        notePad.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
                NotesState currentState = notesViewModel.getState();
                currentState.setNotesContent(notePad.getText() + e.getKeyChar());
                notesViewModel.setState(currentState);
            }

            @Override
            public void keyPressed(KeyEvent e) {
            }

            @Override
            public void keyReleased(KeyEvent e) {
            }
        });

        JScrollPane noteTopics = new JScrollPane(topicsList);

        JSplitPane splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, noteTopics, textArea);

        splitPane.setDividerLocation(Constants.NOTE_TOPICS_SIZE); // This sets the divider at 1/6th of the width of the split pane
        splitPane.setResizeWeight(Constants.NOTE_TOPICS_SIZE);

        tabPanel.add(splitPane, BorderLayout.CENTER);
        return tabPanel;
    }

    private String[] splitHighlightedText(String text, String symbol){
        String[] components = new String[2];
        int indexOfColon;
        if (text == null){
            indexOfColon = -1;
        }else{
            indexOfColon = text.indexOf(symbol);}
        if (indexOfColon == -1){
            components[0] = "";
            components[1] = "";
        } else { //colon exists, find it!
            String before = text.substring(0, indexOfColon);
            String after = text.substring(indexOfColon + 1);
            String regex = "\\s*";
            if (before.matches(regex)){
                components[0] = "";
            }else{
                components[0] = before;
            }

            if (after.matches(regex)){
                components[1] = "";
            }else{
                components[1] = after;
            }
        }
        return components;
    }

    private void setNotesDisplay(NotesState state) {
        ArrayList<String> courses = state.getCourses();
        this.coursesDisplay.removeAll();
        for (String course : courses) {
            coursesDisplay.addTab(course, getTab(course));
        }
    }

    private void updateNotePad(String selectedTopic, JEditorPane notePad) {
        notePad.setText(selectedTopic);
    }


}




