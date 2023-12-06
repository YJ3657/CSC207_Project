package main.java.view;

import main.java.app.Constants;
import main.java.entity.Reminder;
import main.java.interface_adapter.ViewManagerModel;
import main.java.interface_adapter.reminder.ReminderState;
import main.java.interface_adapter.reminder.ReminderViewModel;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.List;
import java.util.Map;
import java.util.Vector;

public class ReminderView extends JPanel implements ActionListener, PropertyChangeListener, ListSelectionListener {

    public final String viewName = "Reminders";
    private final ReminderViewModel reminderViewModel;
    private final ViewManagerModel viewManagerModel;
    private final JTextField courseTitle = new JTextField(15);

    private final JTextField reviewContent = new JTextField(15);

    private JList courseNamesList;
    private JList contentList;
    private JSplitPane splitPane;

    private List<String> courseNames;
    private Map<String, Reminder> reminders;

    public ReminderView(ReminderViewModel reminderViewModel,
                        ViewManagerModel viewManagerModel) {
        super(new BorderLayout());
        this.reminderViewModel = reminderViewModel;
        this.viewManagerModel = viewManagerModel;
        reminderViewModel.addPropertyChangeListener(this);
        JPanel tfPanel = new JPanel();
        JButton back = new JButton(reminderViewModel.BACK_BUTTON_LABEL);
        tfPanel.add(back);
        back.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (e.getSource().equals(back)) {
                    viewManagerModel.setActiveView(Constants.HOME_VIEWNAME);
                    viewManagerModel.firePropertyChanged();
                }
            }
        });
        courseNamesList = new JList();
        courseNamesList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        courseNamesList.setSelectedIndex(0);
        courseNamesList.addListSelectionListener(this);
        JScrollPane courseScrollPane = new JScrollPane(courseNamesList);

        contentList = new JList();
        JScrollPane contentScrollPane = new JScrollPane(contentList);
        splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT,
                courseScrollPane, contentScrollPane);
        splitPane.setOneTouchExpandable(true);
        splitPane.setDividerLocation(150);

        //Provide minimum sizes for the two components in the split pane
        Dimension minimumSize = new Dimension(100, 50);
        courseScrollPane.setMinimumSize(minimumSize);
        contentScrollPane.setMinimumSize(minimumSize);

        //Provide a preferred size for the split pane
        splitPane.setPreferredSize(new Dimension(400, 200));
        this.add(tfPanel, BorderLayout.NORTH);
        this.add(splitPane, BorderLayout.CENTER);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        ReminderState state = (ReminderState) evt.getNewValue();
        courseNames = state.getCourseNames();
        Vector courseNames = parseList(state.getCourseNames());
        courseNamesList.setListData(courseNames);
        reminders = state.getReminder();
    }

    protected static Vector parseList(List<String> courseList) {
        Vector v = new Vector(10);
        for (String course: courseList) {
            v.addElement(course);
        }
        return v;
    }
    protected Vector parseContentList(String selectedCourse) {
        Vector v = new Vector(10);
        Map<Integer, String> reviewMaterials = reminders.get(selectedCourse).getReviewMaterials();
        for (Map.Entry<Integer, String> entry: reviewMaterials.entrySet()) {
            String value = entry.getKey() + " - " + entry.getValue();
            v.addElement(value);
        }
        return v;
    }

    @Override
    public void valueChanged(ListSelectionEvent e) {
        JList theList = (JList)e.getSource();
        if (theList.isSelectionEmpty()) {

        } else {
            int index = theList.getSelectedIndex();
            String course = courseNames.get(index);
            Vector content = parseContentList(course);
            contentList.setListData(content);
        }
    }
}
