package main.java.interface_adapter.notes;

import main.java.interface_adapter.ViewManagerModel;
import main.java.use_case.notes.CreateNotesOutputBoundary;
import main.java.use_case.notes.CreateNotesOutputData;

import javax.swing.*;

public class CreateNotesPresenter implements CreateNotesOutputBoundary{
    private final NotesViewModel notesViewModel;
    private ViewManagerModel viewManagerModel;

    public CreateNotesPresenter(ViewManagerModel viewManagerModel,
                           NotesViewModel notesViewModel) {
        this.viewManagerModel = viewManagerModel;
        this.notesViewModel = notesViewModel;
    }

    @Override
    public void prepareSuccessView(CreateNotesOutputData response) {
        // On success, switch to the home view.

        NotesState notesState = notesViewModel.getState();
        notesState.setNotesContent(response.getNotes().getAllNotes());
        notesState.setNotesTitle(response.getNotes().getTitle());
        this.notesViewModel.setState(notesState);
        this.notesViewModel.firePropertyChanged();

        this.viewManagerModel.setActiveView(notesViewModel.getViewName());
        this.viewManagerModel.firePropertyChanged();
    }
}
