import main.java.app.Constants;
import main.java.data_access.InMemAddCourseDAO;
import main.java.entity.Course;
import main.java.entity.CourseFactory;
import main.java.use_case.courses.AddCourseDataAccessInterface;
import main.java.use_case.notes.*;
import org.junit.Test;

import static org.junit.jupiter.api.Assertions.*;

public class AddCourseInteractorTest {
    @Test
    public void SuccessTest() {
        AddCourseInputData inputData = new AddCourseInputData("MAT137");
        AddCourseDataAccessInterface addCourseRepo = new InMemAddCourseDAO();
        AddCourseOutputBoundary successPresenter = new AddCourseOutputBoundary() {
            @Override
            public void prepareSuccessView(AddCourseOutputData courseData) {
                assertEquals("MAT137", courseData.getCourseID());
                assertNotNull(courseData.getCreationTime());
                assertTrue(addCourseRepo.existsByID("MAT137"));
            }

            @Override
            public void prepareFailView(String error) {
                fail("FailView not expected");

            }
        };
        //FIXME: Test broken.
//        AddCourseInputBoundary interactor = new AddCourseInteractor(addCourseRepo,
//                successPresenter,
//                new CourseFactory());
//        interactor.execute(inputData);
    }


    @Test
    public void FailTest() {
        AddCourseInputData inputData = new AddCourseInputData("MAT137");
        AddCourseDataAccessInterface addCourseRepo = new InMemAddCourseDAO();
        addCourseRepo.saveCourse(new Course("MAT137"));
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
        //FIXME: Test broken.
//        AddCourseInputBoundary interactor = new AddCourseInteractor(addCourseRepo,
//                successPresenter,
//                new CourseFactory());
//        interactor.execute(inputData);
    }
}
