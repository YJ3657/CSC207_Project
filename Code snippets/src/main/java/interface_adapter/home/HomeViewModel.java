package main.java.interface_adapter.home;

import main.java.app.Constants;
import main.java.interface_adapter.ViewModel;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
public class HomeViewModel extends ViewModel{

    public static final String TITLE_LABEL = "Home View";

    public static final String NOTES_LABEL = "Notes";
    public static final String PRACTICE_LABEL = "Practice";

    public static final String REMINDERS_LABEL = "Reminders";
    public static final String LOGOUT_LABEL = "Logout";
    private HomeState state = new HomeState();
    private final PropertyChangeSupport support = new PropertyChangeSupport(this);

    public HomeViewModel() {
        super(Constants.HOME_VIEWNAME);
    }
    // This is what the Home Presenter will call to let the ViewModel know
    // to alert the View
    public void firePropertyChanged() {
        support.firePropertyChange(Constants.STATE_PROPNAME, null, this.state);
    }

    public void addPropertyChangeListener(PropertyChangeListener listener) {
        support.addPropertyChangeListener(listener);
    }


    //MATTHEW ADDED EVERYTHING BELOW
    public HomeState getState(){
        return state;
    }

    public void setState(HomeState state){
        this.state = state;
    }
}
