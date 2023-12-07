package main.java;

import main.java.app.Constants;
import main.java.data_access.DBDataAccessObject;
import main.java.entity.*;
import main.java.use_case.reminder.*;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class ReminderInteractorTest {
    @Test
    public void SuccessTest() {
        DBDataAccessObject dbDataAccessObject = new DBDataAccessObject(new DefaultUserFactory(), new NotesFactory(),
                new CourseFactory(), new StudentFactory(), new QuestionFactory(), new DefinitionFactory(), new ReminderFactory());
        Map<String, Reminder> userReviewChapters = setTestData();
        ReminderOutputData expectedReminderOutputData = new ReminderOutputData(userReviewChapters);
        ReminderOutputBoundary successPresenter = new ReminderOutputBoundary() {
            @Override
            public void prepareSuccessView(ReminderOutputData reminderOutputData) {
                assertEquals(reminderOutputData.reviewContents.keySet(), expectedReminderOutputData.reviewContents.keySet());
            }
            @Override
            public void prepareFailView(String error) {
                fail("Failure not supposed to happen");
            }
        };
        ReminderInputBoundary interactor = new ReminderInteractor(dbDataAccessObject, successPresenter);
        ReminderInputData reminderInputData = new ReminderInputData("sample");
        interactor.execute(reminderInputData);
    }

    @Test
    public void FailTest() {
        DBDataAccessObject dbDataAccessObject = new DBDataAccessObject(new DefaultUserFactory(), new NotesFactory(),
                new CourseFactory(), new StudentFactory(), new QuestionFactory(), new DefinitionFactory(), new ReminderFactory());
        ReminderInputData reminderInputData = new ReminderInputData("sample1");
        ReminderOutputBoundary failPresenter = new ReminderOutputBoundary() {
            @Override
            public void prepareSuccessView(ReminderOutputData reminderOutputData) {
                fail("SuccessView is not expected to occur");
            }

            @Override
            public void prepareFailView(String error) {
                assertEquals(Constants.REMINDER_ERROR, error);
            }
        };
        ReminderInputBoundary interactor = new ReminderInteractor(dbDataAccessObject, failPresenter);
        interactor.execute(reminderInputData);
    }

    private Map<String, Reminder> setTestData(){
        Map<String, Reminder> userReviewChapters = new HashMap<>();
        Map<Integer, String> reviewMaterialsCSC236 = new HashMap<>();
        Reminder rmCSC236= new Reminder("CSC236", reviewMaterialsCSC236);
        userReviewChapters.put("CSC236", rmCSC236);
        return userReviewChapters;

    }
}
