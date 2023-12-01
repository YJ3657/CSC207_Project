package main.java.use_case.reminder;


import java.util.List;
import java.util.Map;

public interface ReminderDataAccessInterface {
    public Map<String, List<String>> getReviewChapters(String userid);
}
