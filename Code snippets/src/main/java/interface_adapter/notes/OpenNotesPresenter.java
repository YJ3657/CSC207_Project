package main.java.interface_adapter.notes;

import main.java.interface_adapter.ViewManagerModel;
import main.java.use_case.notes.open_notes.OpenNotesOutputBoundary;
import main.java.use_case.notes.open_notes.OpenNotesOutputData;

public class OpenNotesPresenter implements OpenNotesOutputBoundary {
    private ViewManagerModel viewManagerModel;
    private final NotesViewModel notesViewModel;

    public OpenNotesPresenter(ViewManagerModel viewManagerModel,
                              NotesViewModel notesViewModel) {
        this.viewManagerModel = viewManagerModel;
        this.notesViewModel = notesViewModel;
    }

    @Override
    public void presentNotes(OpenNotesOutputData data) {
        NotesState notesState = notesViewModel.getState();
        notesState.setAllNotes(data.getNotes());
        notesState.setCourses(data.getCourses());
        notesViewModel.setState(notesState);
        notesViewModel.firePropertyChanged();

        viewManagerModel.setActiveView(notesViewModel.getViewName());
        viewManagerModel.firePropertyChanged();
    }
}
