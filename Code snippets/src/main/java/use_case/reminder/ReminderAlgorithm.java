package main.java.use_case.reminder;

import main.java.data_access.DBDataAccessObject;
import main.java.entity.Reminder;

import java.util.Map;

public interface ReminderAlgorithm {

    public void getReminders(DBDataAccessObject dataAccessObject, String userid);
}
