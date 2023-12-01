package main.java.use_case.reminder;


import main.java.entity.Reminder;

import java.util.List;
import java.util.Map;

public interface ReminderDataAccessInterface {
    public Map<String, Reminder> getUserReviewChapters(String userid);
}
