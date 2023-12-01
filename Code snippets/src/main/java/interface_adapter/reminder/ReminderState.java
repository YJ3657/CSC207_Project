package main.java.interface_adapter.reminder;

import main.java.interface_adapter.quiz.QuizState;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ReminderState {
    private Map<String, List<String>> reviewContents;

    public ReminderState(ReminderState copy) {
        reviewContents = copy.getReminder();

    }

    public ReminderState() {}

    public void setReminder(Map<String, List<String>> reviewContents) {
        this.reviewContents = reviewContents;
    }

    public Map<String, List<String>> getReminder() {
        return this.reviewContents;
    }


}
