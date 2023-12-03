package main.java.use_case.reminder;

public interface ReminderOutputBoundary {

    public void prepareSuccessView(ReminderOutputData reminderOutputData);

    public void prepareFailView(String error);
}
