//package main.java.use_case.reminder;
//
//import main.java.app.Constants;
//
//public class ReminderInteractor implements ReminderInputBoundary {
//
//    ReminderDataAccessInterface reminderDAO;
//    ReminderOutputBoundary reminderPresenter;
//
//    public ReminderInteractor(ReminderDataAccessInterface reminderDAO, ReminderOutputBoundary reminderPresenter) {
//        this.reminderDAO = reminderDAO;
//        this.reminderPresenter = reminderPresenter;
//    }
//
//    @Override
//    public void execute(ReminderInputData reminderInputData) {
//        String userid = reminderInputData.getUserid();
//        ReminderOutputData reminderOutputData = new ReminderOutputData(reminderDAO.getReviewChapters(userid));
//        if(!reminderOutputData.getReviewChapters().isEmpty()) {
//            reminderPresenter.prepareSuccessView(reminderOutputData);
//        } else {
//            reminderPresenter.prepareFailView(Constants.REMINDER_ERROR);
//        }
//    }
//}
