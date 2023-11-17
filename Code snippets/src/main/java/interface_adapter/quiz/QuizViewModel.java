package main.java.interface_adapter.quiz;

import main.java.app.Constants;
import main.java.interface_adapter.ViewModel;
import main.java.interface_adapter.notes.NotesState;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class QuizViewModel extends ViewModel{
    public final String TITLE_LABEL = "Quiz View";

    public final String BACK_BUTTON_LABEL = "Back";

    public final String SHOW_ANSWERS_BUTTON_LABEL = "Show Answers";
    private QuizState state = new QuizState();

    public QuizViewModel() {
        super(Constants.QUIZ_VIEWNAME);
    }

    public void setState(QuizState state) {
        this.state = state;
    }

    public QuizState getState() {
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
