package main.java.interface_adapter.notes;

import main.java.entity.Notes;
import main.java.use_case.notes.delete_notes.DeleteNotesInputBoundary;
import main.java.use_case.notes.delete_notes.DeleteNotesInputData;

public class DeleteNotesController {
    final DeleteNotesInputBoundary deleteNotesInteractor;
    public DeleteNotesController(DeleteNotesInputBoundary deleteNotesInteractor) {
        this.deleteNotesInteractor = deleteNotesInteractor;
    }

    public void execute(String title, String courseId, Notes tbd) {

        //how does chapter number work?
        DeleteNotesInputData deleteNotesInputData = new DeleteNotesInputData(title, courseId, tbd);

        deleteNotesInteractor.execute(deleteNotesInputData);
    }
}
