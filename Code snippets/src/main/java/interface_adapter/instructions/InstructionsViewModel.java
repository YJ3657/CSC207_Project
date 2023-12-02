package main.java.interface_adapter.instructions;

import main.java.interface_adapter.ViewModel;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class InstructionsViewModel extends ViewModel {

    private InstructionsState state = new InstructionsState();
    public final String BACK_BUTTON_LABEL = "back";
    public InstructionsViewModel() {super("instructions");
    }

    public void setState(InstructionsState state) {
        this.state = state;
    }
    private final PropertyChangeSupport support = new PropertyChangeSupport(this);

    public void firePropertyChanged() {
        support.firePropertyChange("state", null, this.state);
    }

    public void addPropertyChangeListener(PropertyChangeListener listener) {
        support.addPropertyChangeListener(listener);
    }

    public InstructionsState getState() {
        return state;
    }
}
