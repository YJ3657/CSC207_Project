package main.java.use_case.notes.delete_notes;

import main.java.use_case.notes.NotesDataAccessInterface;

public class DeleteNotesInteractor implements DeleteNotesInputBoundary {
    final NotesDataAccessInterface notesDataAccessObject;

    public DeleteNotesInteractor(NotesDataAccessInterface notesDataAccessObject) {
        this.notesDataAccessObject = notesDataAccessObject;
    }

    @Override
    public void execute(DeleteNotesInputData deleteNotesInputData) {
        if (notesDataAccessObject.noteExists(deleteNotesInputData.getCourseId(), deleteNotesInputData.getTitle())) {
            notesDataAccessObject.deleteNotes(deleteNotesInputData.getToBeDeleted(),
                    deleteNotesInputData.getCourseId());
        }
    }
}
