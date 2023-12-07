package main.java.interface_adapter.add_course;

import main.java.use_case.courses.add_course.AddCourseInputBoundary;
import main.java.use_case.courses.add_course.AddCourseInputData;

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
