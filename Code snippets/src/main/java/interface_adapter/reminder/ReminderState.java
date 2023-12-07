package main.java.interface_adapter.reminder;

import main.java.entity.Reminder;
import main.java.interface_adapter.quiz.QuizState;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ReminderState {
    private  Map<String, Reminder> reviewContents;
    private List<String> courseNames;

    public ReminderState(ReminderState copy, List<String> coursesTaken) {
        reviewContents = copy.getReminder();
        courseNames = coursesTaken;
    }

    public ReminderState() {}

    public void setReminder( Map<String, Reminder> reviewContents) {
        this.reviewContents = reviewContents;
    }

    public  Map<String, Reminder> getReminder() {
        return this.reviewContents;
    }

    public List<String> getCourseNames() {
        return courseNames;
    }
    public void setCourseNames(List<String> courseNames) {
        this.courseNames = courseNames;
    }

}
