package main.java.interface_adapter.reminder;

import main.java.entity.Reminder;
import main.java.interface_adapter.ViewManagerModel;
import main.java.interface_adapter.home.HomeState;
import main.java.interface_adapter.home.HomeViewModel;
import main.java.interface_adapter.notes.NotesState;
import main.java.interface_adapter.signup.SignupState;
import main.java.interface_adapter.signup.SignupViewModel;
import main.java.use_case.reminder.ReminderOutputBoundary;
import main.java.use_case.reminder.ReminderOutputData;
import main.java.use_case.signup.SignupOutputData;
import main.java.view.ReminderView;

import javax.swing.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ReminderPresenter implements ReminderOutputBoundary{
    private final ReminderViewModel reminderViewModel;
    private ViewManagerModel viewManagerModel;

    public ReminderPresenter(ViewManagerModel viewManagerModel,
                             ReminderViewModel reminderViewModel) {
        this.viewManagerModel = viewManagerModel;
        this.reminderViewModel = reminderViewModel;
    }

    @Override
    public void prepareSuccessView(ReminderOutputData response) {
        ReminderState reminderState = reminderViewModel.getState();
        Map<String, Reminder> reviewContents = response.getReviewChapters();
        //Saving reminder object in state
        reminderState.setReminder(reviewContents);
        List<String> userCourses = new ArrayList<>();
        Map<Integer, String> reviewMaterials = new HashMap<>();
        for (Map.Entry<String, Reminder> entry : reviewContents.entrySet()) {
            String key = entry.getKey();
            userCourses.add(key);
            Reminder value = entry.getValue();
            reviewMaterials = value.getReviewMaterials();
        }
        //Saving user enrolled courses in state to read in view
        reminderState.setCourseNames(userCourses);
        this.reminderViewModel.setState(reminderState);
        this.reminderViewModel.firePropertyChanged();
        this.viewManagerModel.setActiveView(reminderViewModel.getViewName());
        this.viewManagerModel.firePropertyChanged();
    }

    @Override
    public void prepareFailView(String error) {
        JOptionPane.showMessageDialog(null, error, "Error", JOptionPane.ERROR_MESSAGE);
        this.viewManagerModel.firePropertyChanged();
    }
}
