import main.java.app.Constants;
import main.java.entity.*;
import main.java.use_case.courses.add_course.*;
import main.java.use_case.notes.add_course.*;
import org.junit.Test;

import static org.junit.jupiter.api.Assertions.*;

public class AddCourseInteractorTest {
    @Test
    public void SuccessTest() {
        AddCourseInputData inputData = new AddCourseInputData("MAT137");
        Constants.CURRENT_USER = Constants.TEST_USERNAME;
        InMemAddCourseDAO addCourseRepo = new InMemAddCourseDAO();

        AddCourseOutputBoundary successPresenter = new AddCourseOutputBoundary() {
            @Override
            public void prepareSuccessView(AddCourseOutputData courseData) {
                assertEquals("MAT137", courseData.getCourseID());
                String courseName = courseData.getCourseID();
                assertNotNull(courseData.getCreationTime());
                assertTrue(addCourseRepo.existsByID(courseName));
                Course courseAdded = addCourseRepo.getCourse(courseName);
                User userObj = addCourseRepo.accounts.get(Constants.CURRENT_USER);
                assertTrue(userObj.getCourseId().contains(courseName)); // Test if user has MAT137 as a course
                assertTrue(userObj.getNotes().containsKey(courseName)); // Test if user has MAT137 as a note
                assertTrue(courseAdded.getStudents().contains(courseData.getStudentAdded()));// Test if MAT137 Course has Current User as a Student
            }

            @Override
            public void prepareFailView(String error) {
                fail("FailView not expected");

            }
        };
        AddCourseInputBoundary interactor = new AddCourseInteractor(addCourseRepo, addCourseRepo, successPresenter, new CourseFactory(), new StudentFactory());
        interactor.execute(inputData);
    }


    @Test
    public void FailTest() {
        Constants.CURRENT_USER = Constants.TEST_USERNAME;
        AddCourseInputData inputData = new AddCourseInputData("MAT137");
        InMemAddCourseDAO addCourseRepo = new InMemAddCourseDAO();
        Course addedCourse = new Course("MAT137");
        Student studentToAdd = new Student(Constants.CURRENT_USER, "PLACEHOLDER");
        addedCourse.addStudent(studentToAdd);
        addCourseRepo.save(addedCourse);
        addCourseRepo.addCourse("MAT137");
        AddCourseOutputBoundary successPresenter = new AddCourseOutputBoundary() {
            @Override
            public void prepareSuccessView(AddCourseOutputData courseData) {
                fail("SuccessView not expected");
            }

            @Override
            public void prepareFailView(String error) {
                assertEquals(Constants.ADD_COURSE_ERROR, error);

            }
        };
        AddCourseInputBoundary interactor = new AddCourseInteractor(addCourseRepo, addCourseRepo, successPresenter, new CourseFactory(), new StudentFactory());
        interactor.execute(inputData);
    }
}
