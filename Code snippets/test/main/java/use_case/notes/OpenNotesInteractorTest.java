package main.java.use_case.notes;

import main.java.app.Constants;
import main.java.entity.Notes;
import main.java.entity.User;
import main.java.use_case.notes.open_notes.OpenNotesInputBoundary;
import main.java.use_case.notes.open_notes.OpenNotesInteractor;
import main.java.use_case.notes.open_notes.OpenNotesOutputBoundary;
import main.java.use_case.notes.open_notes.OpenNotesOutputData;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class OpenNotesInteractorTest {

    @BeforeEach
    void setUp() {
        Constants.CURRENT_USER = Constants.TEST_USERNAME;


    }

    @Test
    void execute() {
        InMemAddCourseDAO addCourseDAO = new InMemAddCourseDAO();
        User user = addCourseDAO.accounts.get(Constants.CURRENT_USER);
        Notes notes = new Notes(Constants.CURRENT_USER, "MAT137", Constants.LIMIT_DEF,
                0, Constants.LIMIT_TERM);
        user.setNotes(notes, "MAT137");
        OpenNotesOutputBoundary openNotetsPresenter = new OpenNotesOutputBoundary() {
            @Override
            public void presentNotes(OpenNotesOutputData notes) {
                Map<String, List<Notes>> output = notes.getNotes();
                Notes outputNotes = output.get("MAT137").get(0);
                assertEquals(outputNotes.getContents(), Constants.LIMIT_DEF);
                assertEquals(outputNotes.getTitle(), Constants.LIMIT_TERM);
                assertEquals(outputNotes.getUserId(), Constants.CURRENT_USER);
                assertEquals(outputNotes.getChapterno(), 0);

            }
        };
        OpenNotesInputBoundary openNotesInteractor = new OpenNotesInteractor(addCourseDAO, openNotetsPresenter);
        openNotesInteractor.execute();

    }
}