package main.java.app;

import main.java.data_access.NotesDataAccessInterface;
import main.java.interface_adapter.*;
import main.java.use_case.OpenNotesInputBoundary;
import main.java.use_case.OpenNotesInteractor;
import main.java.use_case.OpenNotesOutputBoundary;
import main.java.view.HomeView;

public class HomeUseCaseFactory {

    private HomeUseCaseFactory() {}

    public static HomeView create(ViewManagerModel viewManagerModel, HomeViewModel homeViewModel, NotesViewModel notesViewModel, NotesDataAccessInterface notesDataAcessObject) {
        OpenNotesController openNotesController = createOpenNotesUseCase(viewManagerModel, notesViewModel, notesDataAcessObject);
        return new HomeView(homeViewModel, openNotesController);
    }
    private static OpenNotesController createOpenNotesUseCase(ViewManagerModel viewManagerModel, NotesViewModel notesViewModel, NotesDataAccessInterface notesDataAccessObject) {
        OpenNotesOutputBoundary openNotesOutputBoundary = new OpenNotesPresenter(viewManagerModel, notesViewModel);

        OpenNotesInputBoundary clearInteractor = new OpenNotesInteractor(notesDataAccessObject, openNotesOutputBoundary);
        return new OpenNotesController(clearInteractor);
    }
}
