import main.java.data_access.InMemAddCourseDAO;
import main.java.data_access.NotesDataAccessObject;
import main.java.use_case.courses.AddCourseDataAccessInterface;
import main.java.use_case.notes.*;
import org.junit.Test;

import static org.junit.jupiter.api.Assertions.*;

public class AddCourseInteractorTest {
    @Test
    public void SuccessTest() {
        AddCourseInputData inputData = new AddCourseInputData("MAT137");
        AddCourseDataAccessInterface addCourseRepo = new NotesDataAccessObject();
        AddCourseOutputBoundary successPresenter = new AddCourseOutputBoundary() {
            @Override
            public void prepareSuccessView(AddCourseOutputData courseData) {
                assertEquals("MAT137", courseData.getCourseID());
                assertNotNull(courseData.getCreationTime());
                assertTrue(addCourseRepo.existsByID("MAT137"));
            }

            @Override
            public void prepareFailView(String error) {
                assertEquals("Course already exists", error);

            }
        };
        AddCourseInputBoundary interactor = new AddCourseInteractor(addCourseRepo, successPresenter);
        interactor.execute(inputData);
    }
}
