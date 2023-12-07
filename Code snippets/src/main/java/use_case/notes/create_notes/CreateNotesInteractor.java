package main.java.use_case.notes.create_notes;

import main.java.app.Constants;
import main.java.entity.Notes;
import main.java.entity.NotesFactory;
import main.java.use_case.notes.NotesDataAccessInterface;

public class CreateNotesInteractor implements CreateNotesInputBoundary{
    final NotesDataAccessInterface notesDataAccessObject;
    final CreateNotesOutputBoundary createNotesPresenter;
    final NotesFactory notesFactory;

    public CreateNotesInteractor(NotesDataAccessInterface notesDataAccessObject,
                                 CreateNotesOutputBoundary createNotesPresenter,
                                 NotesFactory notesFactory) {
        this.notesDataAccessObject = notesDataAccessObject;
        this.createNotesPresenter = createNotesPresenter;
        this.notesFactory = notesFactory;
    }

    @Override
    public void execute(CreateNotesInputData createNotesInputData) {
        if (notesDataAccessObject.noteExists(createNotesInputData.getCourseId(), createNotesInputData.getTitle())) {
            if (createNotesInputData.getOverwrite()){
                notesDataAccessObject.updateContent(createNotesInputData.getCourseId(),
                        createNotesInputData.getTitle(), createNotesInputData.getContents());
            } else {
                createNotesPresenter.prepareFailView("Note already exists.");
            }
        } else{
            Notes notes = notesFactory.create(Constants.CURRENT_USER, createNotesInputData.getCourseId(),
                    createNotesInputData.getContents(), createNotesInputData.getChapterNo(), createNotesInputData.getTitle());
            notesDataAccessObject.addNotes(notes, createNotesInputData.getCourseId());

            CreateNotesOutputData createNotesOutputData = new CreateNotesOutputData(notes,
                    Constants.CURRENT_USER_OBJ.getNotes());
            createNotesPresenter.prepareSuccessView(createNotesOutputData);
        }
    }
}
