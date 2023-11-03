package main.java.use_case.notes;

public interface AddCourseOutputBoundary {
    public void prepareSuccessView(AddCourseOutputData addCourseOutputData);
    public void prepareFailView(AddCourseOutputData addCourseOutputData);
}
