package main.java.interface_adapter.notes;

import main.java.interface_adapter.ViewManagerModel;
import main.java.use_case.notes.CreateNotesOutputBoundary;
import main.java.use_case.notes.CreateNotesOutputData;

import javax.swing.*;

public class CreateNotesPresenter implements CreateNotesOutputBoundary{
    private final NotesViewModel notesViewModel;
    private final ViewManagerModel viewManagerModel;

    public CreateNotesPresenter(ViewManagerModel viewManagerModel,
                           NotesViewModel notesViewModel) {
        this.viewManagerModel = viewManagerModel;
        this.notesViewModel = notesViewModel;
    }

    @Override
    public void prepareSuccessView(CreateNotesOutputData response) {

        NotesState notesState = notesViewModel.getState();
        notesState.setNotesContent(response.getNotes().getContents());
        notesState.setNotesTitle(response.getNotes().getTitle());
        this.notesViewModel.setState(notesState);
        this.notesViewModel.firePropertyChanged();

        this.viewManagerModel.setActiveView(notesViewModel.getViewName());
        this.viewManagerModel.firePropertyChanged();
    }

    @Override
    public void prepareFailView(String error) {
        NotesState notesState = notesViewModel.getState();
        notesState.setNotesError(error);
        JOptionPane.showMessageDialog(null, error, "Error", JOptionPane.ERROR_MESSAGE);
        this.viewManagerModel.firePropertyChanged();
    }
}
