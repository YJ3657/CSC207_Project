package main.java;

import main.java.app.Constants;
import main.java.data_access.DBDataAccessObject;
import main.java.entity.*;
import main.java.interface_adapter.notes.DeleteNotesController;

import main.java.use_case.notes.delete_notes.DeleteNotesInteractor;
import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class DeleteNotesTest {
    @Test
    public void FailTest() {
        Constants.CURRENT_USER = Constants.TEST_USERNAME;
        DBDataAccessObject createNotesRepo = new DBDataAccessObject(new DefaultUserFactory(), new NotesFactory(),
                new CourseFactory(), new StudentFactory(), new QuestionFactory(), new DefinitionFactory(),
                new ReminderFactory());
        Notes addedNote = new Notes(Constants.CURRENT_USER, "MAT137", "", 1, "Functions");
        createNotesRepo.addNotes(addedNote, "MAT137");
        Notes tbdNote = new Notes(Constants.CURRENT_USER, "MAT137", "", 2, "Integrals");
        createNotesRepo.addNotes(addedNote, "MAT137");
        createNotesRepo.addNotes(tbdNote, "MAT137");
        DeleteNotesInteractor deleteNotesInteractor = new DeleteNotesInteractor(createNotesRepo);
        DeleteNotesController deleteNotesController = new DeleteNotesController(deleteNotesInteractor);
        deleteNotesController.execute("MAT137", "MAT137", tbdNote);

        HashMap<String, List<Notes>> testMap = new HashMap<>();
        List<Notes> newList = new ArrayList<>();
        newList.add(addedNote);
        testMap.put("MAT137", newList);
        assertEquals(createNotesRepo.get(Constants.CURRENT_USER).getNotes(), testMap);
    }
}
