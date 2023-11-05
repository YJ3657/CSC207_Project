package main.java.interface_adapter.notes;

import main.java.app.Constants;
import main.java.interface_adapter.ViewModel;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class NotesViewModel extends ViewModel {
    public final String TITLE_LABEL = "Notes View";

    public final String BACK_BUTTON_LABEL = "Back";
    private NotesState state = new NotesState();

    public NotesViewModel() {
        super(Constants.NOTES_VIEWNAME);
    }

    public void setState(NotesState state) {
        this.state = state;
    }

    public NotesState getState() {
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
