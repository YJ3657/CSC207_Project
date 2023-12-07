package main.java.interface_adapter.reminder;

import main.java.entity.Reminder;
import main.java.interface_adapter.home.HomeState;
import main.java.use_case.reminder.ReminderInputBoundary;
import main.java.use_case.reminder.ReminderInputData;

import java.util.Map;

public class ReminderController {

    final ReminderInputBoundary userReminderUseCaseInteractor;

    public ReminderController(ReminderInputBoundary userReminderUseCaseInteractor) {
        this.userReminderUseCaseInteractor = userReminderUseCaseInteractor;
    }

    public Map<String, Reminder> execute(String userid) {
        ReminderInputData reminderInputData = new ReminderInputData(userid);

        return this.userReminderUseCaseInteractor.execute(reminderInputData);
    }

}
