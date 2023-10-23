package main.java.interface_adapter;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class NotesViewModel extends ViewModel{
    public final String TITLE_LABEL = "Notes View";

    public final String BACK_BUTTON_LABEL = "Back";
    private NotesState state = new NotesState();

    public NotesViewModel() {
        super("Notes");
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
        support.firePropertyChange("state", null, this.state);
    }

    @Override
    public void addPropertyChangeListener(PropertyChangeListener listener) {
        support.addPropertyChangeListener(listener);
    }
}
