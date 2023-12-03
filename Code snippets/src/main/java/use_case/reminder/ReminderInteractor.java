package main.java.use_case.reminder;

import main.java.app.Constants;
import main.java.entity.Reminder;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class ReminderInteractor implements ReminderInputBoundary {

    ReminderDataAccessInterface reminderDAO;
    ReminderOutputBoundary reminderPresenter;

    public ReminderInteractor(ReminderDataAccessInterface reminderDAO, ReminderOutputBoundary reminderPresenter) {
        this.reminderDAO = reminderDAO;
        this.reminderPresenter = reminderPresenter;
    }

    @Override
    public void execute(ReminderInputData reminderInputData) {
        String userid = reminderInputData.getUserid();
        Map<String, Reminder> userReviewChapters =  reminderDAO.getUserReviewChapters(userid);
        ReminderOutputData reminderOutputData = new ReminderOutputData(userReviewChapters);

        if(!reminderOutputData.getReviewChapters().isEmpty()) {
            reminderPresenter.prepareSuccessView(reminderOutputData);
        } else {
            reminderPresenter.prepareFailView(Constants.REMINDER_ERROR);
        }
    }
}
