package main.java.use_case.courses.add_course;

public interface AddCourseOutputBoundary {
    public void prepareSuccessView(AddCourseOutputData addCourseOutputData);
    public void prepareFailView(String error);
}
