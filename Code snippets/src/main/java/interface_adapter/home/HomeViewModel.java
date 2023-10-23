package main.java.interface_adapter.home;

import main.java.interface_adapter.ViewModel;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
public class HomeViewModel extends ViewModel{

    public static final String TITLE_LABEL = "Home View";

    public static final String NOTES_LABEL = "Notes";
    public static final String PRACTICE_LABEL = "Practice";
    private HomeState state = new HomeState();
    private final PropertyChangeSupport support = new PropertyChangeSupport(this);

    public HomeViewModel() {
        super("home");
    }
    // This is what the Home Presenter will call to let the ViewModel know
    // to alert the View
    public void firePropertyChanged() {
        support.firePropertyChange("state", null, this.state);
    }

    public void addPropertyChangeListener(PropertyChangeListener listener) {
        support.addPropertyChangeListener(listener);
    }
}
