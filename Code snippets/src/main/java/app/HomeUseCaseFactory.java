package main.java.app;

import main.java.interface_adapter.logout.LogoutController;
import main.java.interface_adapter.logout.LogoutPresenter;
import main.java.interface_adapter.login.LoginViewModel;
import main.java.use_case.logout.LogoutInputBoundary;
import main.java.use_case.logout.LogoutInteractor;
import main.java.use_case.logout.LogoutOutputBoundary;
import main.java.use_case.find_user_courses.FindUserCourseDataAccessInterface;
import main.java.use_case.notes.NotesDataAccessInterface;
import main.java.interface_adapter.*;
import main.java.interface_adapter.home.HomeViewModel;
import main.java.interface_adapter.notes.NotesViewModel;
import main.java.interface_adapter.notes.OpenNotesController;
import main.java.interface_adapter.notes.OpenNotesPresenter;
import main.java.use_case.notes.OpenNotesInputBoundary;
//import main.java.use_case.notes.OpenNotesInteractor;
import main.java.use_case.notes.OpenNotesInteractor;
import main.java.use_case.notes.OpenNotesOutputBoundary;
import main.java.view.HomeView;

public class HomeUseCaseFactory {

    private HomeUseCaseFactory() {}




    public static HomeView create(ViewManagerModel viewManagerModel, HomeViewModel homeViewModel, NotesViewModel notesViewModel, NotesDataAccessInterface notesDataAcessObject, LoginViewModel loginViewModel) {
        OpenNotesController openNotesController = createOpenNotesUseCase(viewManagerModel, notesViewModel, notesDataAcessObject);
        LogoutController logoutController = createLogoutUseCase(viewManagerModel,loginViewModel);
        return new HomeView(homeViewModel, openNotesController);
    }
    private static OpenNotesController createOpenNotesUseCase(ViewManagerModel viewManagerModel, NotesViewModel notesViewModel, NotesDataAccessInterface notesDataAccessObject) {
        OpenNotesOutputBoundary openNotesPresenter = new OpenNotesPresenter(viewManagerModel, notesViewModel);


//        made a small change to make my code work
        OpenNotesInputBoundary clearInteractor = new OpenNotesInteractor((FindUserCourseDataAccessInterface) notesDataAccessObject, openNotesPresenter);
        return new OpenNotesController(clearInteractor);
    }
    private static LogoutController createLogoutUseCase(ViewManagerModel viewManagerModel, LoginViewModel loginViewModel){
        LogoutOutputBoundary logoutOutputBoundary = new LogoutPresenter(loginViewModel,viewManagerModel);

        LogoutInputBoundary logoutInteractor = new LogoutInteractor(logoutOutputBoundary);
        return new LogoutController(logoutInteractor);
    }
}
