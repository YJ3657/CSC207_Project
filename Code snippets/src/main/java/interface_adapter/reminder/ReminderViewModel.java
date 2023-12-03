package main.java.interface_adapter.reminder;

import main.java.app.Constants;
import main.java.entity.Reminder;
import main.java.interface_adapter.ViewModel;
import main.java.interface_adapter.quiz.QuizState;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class ReminderViewModel extends ViewModel {
    public final String TITLE_LABEL = "Quiz View";

    public final String BACK_BUTTON_LABEL = "Back";

    public final String SHOW_ANSWERS_BUTTON_LABEL = "Show Answers";
    private ReminderState state = new ReminderState();

    public ReminderViewModel() {
        super(Constants.QUIZ_VIEWNAME);
    }

    public void setState(ReminderState state) {
        this.state = state;
    }

    public ReminderState getState() {
        return state;
    }

    private final PropertyChangeSupport support = new PropertyChangeSupport(this);

    @Override
    public void firePropertyChanged() {
        support.firePropertyChange(Constants.STATE_PROPNAME, null, this.state);
    }

    public void firePropertyChanged(String propertyName) {
        support.firePropertyChange(propertyName, null, this.state);
    }

    @Override
    public void addPropertyChangeListener(PropertyChangeListener listener) {
        support.addPropertyChangeListener(listener);
    }
}
