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
    public void OneDeletionTest() {
        Constants.CURRENT_USER = Constants.TEST_USERNAME;
        DBDataAccessObject createNotesRepo = new DBDataAccessObject(new DefaultUserFactory(), new NotesFactory(),
                new CourseFactory(), new StudentFactory(), new QuestionFactory(), new DefinitionFactory(),
                new ReminderFactory());
        Notes addedNote = new Notes(Constants.CURRENT_USER, "MAT137", "", 1, "Functions");
        createNotesRepo.addNotes(addedNote, "MAT137");
        Notes tbdNote = new Notes(Constants.CURRENT_USER, "MAT137", "", 2, "Integrals");
        createNotesRepo.addNotes(tbdNote, "MAT137");
        DeleteNotesInteractor deleteNotesInteractor = new DeleteNotesInteractor(createNotesRepo);
        DeleteNotesController deleteNotesController = new DeleteNotesController(deleteNotesInteractor);
        deleteNotesController.execute("Integrals", "MAT137", tbdNote);

        HashMap<String, List<Notes>> testMap = new HashMap<>();
        List<Notes> newList = new ArrayList<>();
        newList.add(addedNote);
        testMap.put("MAT137", newList);
        assertEquals(createNotesRepo.get(Constants.CURRENT_USER).getNotes(), testMap);
    }


    @Test
    public void AllCourseNotesDeletionTest() {
        Constants.CURRENT_USER = Constants.TEST_USERNAME;
        DBDataAccessObject createNotesRepo = new DBDataAccessObject(new DefaultUserFactory(), new NotesFactory(),
                new CourseFactory(), new StudentFactory(), new QuestionFactory(), new DefinitionFactory(),
                new ReminderFactory());
        Notes addedNoteOne = new Notes(Constants.CURRENT_USER, "MAT137", "SAMPLE 1", 1, "Functions");
        createNotesRepo.addNotes(addedNoteOne, "MAT137");
        Notes addedNoteTwo = new Notes(Constants.CURRENT_USER, "MAT137", "SAMPLE 2", 2, "Integrals");
        createNotesRepo.addNotes(addedNoteTwo, "MAT137");
        Notes addedNoteThree = new Notes(Constants.CURRENT_USER, "MAT137", "SAMPLE 3", 3, "Limits");
        createNotesRepo.addNotes(addedNoteThree, "MAT137");
        DeleteNotesInteractor deleteNotesInteractor = new DeleteNotesInteractor(createNotesRepo);
        DeleteNotesController deleteNotesController = new DeleteNotesController(deleteNotesInteractor);
        deleteNotesController.execute(addedNoteOne.getTitle(), addedNoteOne.getCourseId(), addedNoteOne);
        deleteNotesController.execute(addedNoteTwo.getTitle(), addedNoteTwo.getCourseId(), addedNoteTwo);
        deleteNotesController.execute(addedNoteThree.getTitle(), addedNoteThree.getCourseId(), addedNoteThree);

        assert(createNotesRepo.get(Constants.CURRENT_USER).getNotes().get(addedNoteOne.getCourseId()).isEmpty());
        assert(createNotesRepo.get(Constants.CURRENT_USER).getNotes().get(addedNoteTwo.getCourseId()).isEmpty());
    }


    @Test
    public void DifferentCourseNotesDeletionTest() {
        Constants.CURRENT_USER = Constants.TEST_USERNAME;
        DBDataAccessObject createNotesRepo = new DBDataAccessObject(new DefaultUserFactory(), new NotesFactory(),
                new CourseFactory(), new StudentFactory(), new QuestionFactory(), new DefinitionFactory(),
                new ReminderFactory());
        Notes addedNoteOne = new Notes(Constants.CURRENT_USER, "MAT137", "SAMPLE 1", 1, "Functions");
        createNotesRepo.addNotes(addedNoteOne, addedNoteOne.getCourseId());
        Notes addedNoteTwo = new Notes(Constants.CURRENT_USER, "CSC207", "SAMPLE 2", 1, "SOlID");
        createNotesRepo.addNotes(addedNoteTwo, addedNoteTwo.getCourseId());
        DeleteNotesInteractor deleteNotesInteractor = new DeleteNotesInteractor(createNotesRepo);
        DeleteNotesController deleteNotesController = new DeleteNotesController(deleteNotesInteractor);
        deleteNotesController.execute(addedNoteTwo.getTitle(), addedNoteTwo.getCourseId(), addedNoteTwo);

        List<Notes> newList = new ArrayList<>();
        newList.add(addedNoteOne);
        assert(!createNotesRepo.get(Constants.CURRENT_USER).getNotes().get(addedNoteOne.getCourseId()).isEmpty());
        assert(createNotesRepo.get(Constants.CURRENT_USER).getNotes().get(addedNoteTwo.getCourseId()).isEmpty());
        assertEquals(createNotesRepo.get(Constants.CURRENT_USER).getNotes().get(addedNoteOne.getCourseId()), newList);
    }
}
