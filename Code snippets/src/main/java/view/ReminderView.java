package main.java.view;

import main.java.interface_adapter.ViewManagerModel;
import main.java.interface_adapter.add_Question_Definition.DefQuesController;
import main.java.interface_adapter.notes.AddCourseController;
import main.java.interface_adapter.notes.CreateNotesController;
import main.java.interface_adapter.notes.NotesViewModel;
import main.java.interface_adapter.quiz.QuizController;
import main.java.interface_adapter.reminder.ReminderController;
import main.java.interface_adapter.reminder.ReminderPresenter;
import main.java.interface_adapter.reminder.ReminderViewModel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class ReminderView extends JPanel implements ActionListener, PropertyChangeListener {

   public final String viewName = "Reminder";
   private final ReminderViewModel reminderViewModel;
   private final ViewManagerModel viewManagerModel;
   private final JLabel reminderDisplay;
    private final JTextField courseTitle = new JTextField(15);

    private final JTextField reviewContent = new JTextField(15);

    private final JTabbedPane coursesDisplay;
    private final ReminderController reminderController;


   public ReminderView(ReminderViewModel reminderViewModel,
                       ViewManagerModel viewManagerModel,
                       ReminderController reminderController) {
    super(new BorderLayout());
    this.reminderViewModel = reminderViewModel;
    this.viewManagerModel = viewManagerModel;
    this.reminderController = reminderController;

    this.reminderDisplay = new JLabel("Reminder");
    this.coursesDisplay = new JTabbedPane();
    coursesDisplay.setTabPlacement(JTabbedPane.TOP);
   }
    @Override
    public void actionPerformed(ActionEvent e) {

    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {

    }
}
