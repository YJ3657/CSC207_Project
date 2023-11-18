package main.java.app;

import main.java.entity.CourseFactory;
import main.java.use_case.courses.AddCourseDataAccessInterface;
import main.java.use_case.find_user_courses.FindUserCourseDataAccessInterface;
import main.java.use_case.notes.NotesDataAccessInterface;
import main.java.interface_adapter.*;
import main.java.interface_adapter.home.HomeViewModel;
import main.java.interface_adapter.notes.NotesViewModel;
import main.java.interface_adapter.notes.OpenNotesController;
import main.java.interface_adapter.notes.OpenNotesPresenter;
import main.java.use_case.notes.OpenNotesInputBoundary;
import main.java.use_case.notes.OpenNotesInteractor;
import main.java.use_case.notes.OpenNotesOutputBoundary;
import main.java.view.HomeView;

public class HomeUseCaseFactory {

    private HomeUseCaseFactory() {}

    public static HomeView create(ViewManagerModel viewManagerModel, HomeViewModel homeViewModel, NotesViewModel notesViewModel, FindUserCourseDataAccessInterface addCOurseDAO) {
        OpenNotesController openNotesController = createOpenNotesUseCase(viewManagerModel, notesViewModel, addCOurseDAO);
        return new HomeView(homeViewModel, openNotesController);
    }
    private static OpenNotesController createOpenNotesUseCase(ViewManagerModel viewManagerModel, NotesViewModel notesViewModel, FindUserCourseDataAccessInterface addCourseDAO) {
        OpenNotesOutputBoundary openNotesPresenter = new OpenNotesPresenter(viewManagerModel, notesViewModel);

        OpenNotesInputBoundary clearInteractor = new OpenNotesInteractor(addCourseDAO, openNotesPresenter);
        return new OpenNotesController(clearInteractor);
    }
}
