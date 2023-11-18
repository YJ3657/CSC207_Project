package main.java.use_case.notes;

import main.java.entity.Notes;
import main.java.entity.NotesFactory;

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
        if (notesDataAccessObject.existsByName(createNotesInputData.getCourseId(), createNotesInputData.getTitle())){
            createNotesPresenter.prepareFailView("Note already exists.");
        } else {

            Notes notes = notesFactory.create(createNotesInputData.getTitle(), createNotesInputData.getContent());
            notesDataAccessObject.addNotes(notes, createNotesInputData.getCourseId());

            CreateNotesOutputData createNotesOutputData = new CreateNotesOutputData(notes, false);
            createNotesPresenter.prepareSuccessView(createNotesOutputData);
        }
    }
}
