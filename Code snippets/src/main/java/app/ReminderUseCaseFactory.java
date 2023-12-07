package main.java.app;

import main.java.interface_adapter.ViewManagerModel;
import main.java.interface_adapter.instructions.InstructionsViewModel;
import main.java.interface_adapter.reminder.ReminderViewModel;
import main.java.view.InstructionsView;
import main.java.view.ReminderView;

public class ReminderUseCaseFactory {
    private ReminderUseCaseFactory() {
    }

    public static ReminderView create(ViewManagerModel viewManagerModel,
                                      ReminderViewModel reminderViewModel) {
        return new ReminderView(reminderViewModel, viewManagerModel);
    }
}
