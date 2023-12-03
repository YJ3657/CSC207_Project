package main.java.interface_adapter.reminder;

import main.java.interface_adapter.home.HomeState;
import main.java.use_case.reminder.ReminderInputBoundary;
import main.java.use_case.reminder.ReminderInputData;

public class ReminderController {

    final ReminderInputBoundary userReminderUseCaseInteractor;

    public ReminderController(ReminderInputBoundary userReminderUseCaseInteractor) {
        this.userReminderUseCaseInteractor = userReminderUseCaseInteractor;
    }

    public void execute(String userid) {
        ReminderInputData reminderInputData = new ReminderInputData(userid);

        this.userReminderUseCaseInteractor.execute(reminderInputData);
    }

}
