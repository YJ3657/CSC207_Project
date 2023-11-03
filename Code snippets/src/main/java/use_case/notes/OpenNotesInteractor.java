package main.java.use_case.notes;

import java.util.HashMap;

public class OpenNotesInteractor implements OpenNotesInputBoundary {
    final NotesDataAccessInterface notesDataAccessObject;
    final OpenNotesOutputBoundary openNotesPresenter;

    public OpenNotesInteractor(NotesDataAccessInterface notesDataAccessInterface, OpenNotesOutputBoundary openNotesPresenter) {
        this.notesDataAccessObject = notesDataAccessInterface;
        this.openNotesPresenter = openNotesPresenter;
    }

    @Override
    public void execute() {
        HashMap<String, String> notes = notesDataAccessObject.getNotes();
        OpenNotesOutputData openNotesOutputData = new OpenNotesOutputData(notes);
        openNotesPresenter.presentNotes(openNotesOutputData);
    }
}
