import main.java.app.Constants;
import main.java.data_access.DBDataAccessObject;
import main.java.entity.*;
import main.java.use_case.notes.create_notes.CreateNotesInputData;
import main.java.use_case.notes.create_notes.CreateNotesInteractor;
import main.java.use_case.notes.create_notes.CreateNotesOutputBoundary;
import main.java.use_case.notes.create_notes.CreateNotesOutputData;
import org.junit.Test;

import static org.junit.jupiter.api.Assertions.*;

public class CreateNotesTest {

    @Test
    public void SuccessTest() {
        Constants.CURRENT_USER = Constants.TEST_USERNAME;
        CreateNotesInputData notesInputData = new CreateNotesInputData("Functions",
                "", "MAT137", 1);
        DBDataAccessObject createNotesRepo = new DBDataAccessObject(new DefaultUserFactory(), new NotesFactory(),
                new CourseFactory(), new StudentFactory(), new QuestionFactory(), new DefinitionFactory(),
                new ReminderFactory());
        createNotesRepo.save(new User(Constants.TEST_USERNAME, Constants.TEST_USER_PW));

        CreateNotesOutputBoundary successPresenter = new CreateNotesOutputBoundary() {
            @Override
            public void prepareSuccessView(CreateNotesOutputData resultNote) {
                assertEquals("Functions", resultNote.getNotes().getTitle());
                String noteCourse = resultNote.getNotes().getCourseId();
                Notes note = resultNote.getNotes();
                assertNotNull(resultNote.getAllNotes());
                assertTrue(resultNote.getAllNotes().containsKey(noteCourse));
                assertTrue(resultNote.getAllNotes().get(noteCourse).contains(note));
                User userObj = createNotesRepo.get(Constants.CURRENT_USER);
                assertTrue(userObj.getNotes().containsKey(noteCourse)); // Test if user has MAT137 as a course in notes HashMap
                assertTrue(userObj.getNotes().get(noteCourse).contains(note)); // Test if user has added note object
            }

            @Override
            public void prepareFailView(String error) {
                fail("FailView not expected");

            }
        };
        CreateNotesInteractor interactor = new CreateNotesInteractor(createNotesRepo, successPresenter, new NotesFactory());
        interactor.execute(notesInputData);
    }


    @Test
    public void FailTest() {
        Constants.CURRENT_USER = Constants.TEST_USERNAME;
        CreateNotesInputData notesInputData = new CreateNotesInputData("Functions",
                "", "MAT137", 1);
        DBDataAccessObject createNotesRepo = new DBDataAccessObject(new DefaultUserFactory(), new NotesFactory(),
                new CourseFactory(), new StudentFactory(), new QuestionFactory(), new DefinitionFactory(),
                new ReminderFactory());
        createNotesRepo.save(new User(Constants.TEST_USERNAME, Constants.TEST_USER_PW));
        Notes addedNote = new Notes(Constants.CURRENT_USER, "MAT137", "", 1, "Functions");
        createNotesRepo.addNotes(addedNote, "MAT137");
        CreateNotesOutputBoundary successPresenter = new CreateNotesOutputBoundary() {
            @Override
            public void prepareSuccessView(CreateNotesOutputData resultNote) {
                fail("SuccessView not expected");
            }

            @Override
            public void prepareFailView(String error) {
                assertEquals("Note already exists.", error);

            }
        };
        CreateNotesInteractor interactor = new CreateNotesInteractor(createNotesRepo, successPresenter, new NotesFactory());
        interactor.execute(notesInputData);
    }

    @Test
    public void UpdateTest() {
        Constants.CURRENT_USER = Constants.TEST_USERNAME;
        CreateNotesInputData notesInputData = new CreateNotesInputData(Constants.CURRENT_USER,
                "MAT137", "New text", "Functions", true);
        DBDataAccessObject createNotesRepo = new DBDataAccessObject(new DefaultUserFactory(), new NotesFactory(),
                new CourseFactory(), new StudentFactory(), new QuestionFactory(), new DefinitionFactory(),
                new ReminderFactory());
        createNotesRepo.save(new User(Constants.TEST_USERNAME, Constants.TEST_USER_PW));
        Notes addedNote = new Notes(Constants.CURRENT_USER, "MAT137", "", 1, "Functions");
        createNotesRepo.addNotes(addedNote, "MAT137");
        CreateNotesOutputBoundary successPresenter = new CreateNotesOutputBoundary() {
            @Override
            public void prepareSuccessView(CreateNotesOutputData resultNote) {
                String updatedText = "New text";
                assertEquals("Functions", resultNote.getNotes().getTitle());
                String noteCourse = resultNote.getNotes().getCourseId();
                Notes note = resultNote.getNotes();
                assertNotNull(resultNote.getAllNotes());
                assertTrue(resultNote.getAllNotes().containsKey(noteCourse));
                assertTrue(resultNote.getAllNotes().get(noteCourse).contains(note));
                User userObj = createNotesRepo.get(Constants.CURRENT_USER);
                assertTrue(userObj.getNotes().containsKey(noteCourse)); // Test if user has MAT137 as a course in notes HashMap
                assertTrue(userObj.getNotes().get(noteCourse).contains(note)); // Test if user has added note object
                assertEquals(userObj.getNotes().get(noteCourse).get(0).getContents(), updatedText);
            }

            @Override
            public void prepareFailView(String error) {
                fail("FailView not expected");

            }
        };
        CreateNotesInteractor interactor = new CreateNotesInteractor(createNotesRepo, successPresenter, new NotesFactory());
        interactor.execute(notesInputData);
    }
}
