package main.java.use_case.reminder;

import main.java.entity.Reminder;

import java.util.List;
import java.util.Map;

public class ReminderOutputData {

    public  Map<String, Reminder> reviewContents;

    public ReminderOutputData( Map<String, Reminder> reviewContents) {
        this.reviewContents = reviewContents;
    }

    public  Map<String, Reminder> getReviewChapters() {
        return reviewContents;
    }
}
