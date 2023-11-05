package main.java.interface_adapter.notes;

import main.java.use_case.notes.AddCourseInputBoundary;
import main.java.use_case.notes.AddCourseInputData;

public class AddCourseController {
    final AddCourseInputBoundary addCourseInteractor;

    public AddCourseController(AddCourseInputBoundary addCourseInteractor) {
        this.addCourseInteractor = addCourseInteractor;
    }

    public void execute(String courseID) {
        AddCourseInputData addCourseInputData = new AddCourseInputData(courseID);
        addCourseInteractor.execute(addCourseInputData);
    }
}
