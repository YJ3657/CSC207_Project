package main.java.use_case.reminder;

import main.java.entity.Reminder;

import java.util.Map;

public interface ReminderInputBoundary {
    public Map<String, Reminder> execute(ReminderInputData reminderInputData);
}
