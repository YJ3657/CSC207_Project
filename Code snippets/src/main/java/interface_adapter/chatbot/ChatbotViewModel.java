package main.java.interface_adapter.chatbot;

import main.java.app.Constants;
import main.java.interface_adapter.ViewModel;
import main.java.interface_adapter.home.HomeState;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class ChatbotViewModel extends ViewModel {

    public static final String SEND_BUTTON_LABEL = "Send";
    public static final String MINIMIZE_BUTTON_LABEL = "Minimize";

    private final PropertyChangeSupport support = new PropertyChangeSupport(this);
    private ChatbotState state = new ChatbotState();
    public ChatbotViewModel(){
        super(Constants.CHATBOT_VIEWNAME);
    }

    public ChatbotState getState(){
        return this.state;
    }

    public void setState(ChatbotState chatbotState){
        this.state  = chatbotState;
    }
    @Override
    public void firePropertyChanged() {
        support.firePropertyChange(Constants.STATE_PROPNAME, null, this.state);
    }

    @Override
    public void addPropertyChangeListener(PropertyChangeListener listener) {

    }
}
