package main.java.use_case.reminder;

import java.util.List;
import java.util.Map;

public class ReminderOutputData {

    public Map<String, List<String>> reviewContents;

    public ReminderOutputData(Map<String, List<String>> reviewContents) {
        this.reviewContents = reviewContents;
    }

    public Map<String, List<String>> getReviewChapters() {
        return reviewContents;
    }
}
