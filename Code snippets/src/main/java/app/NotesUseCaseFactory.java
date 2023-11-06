package main.java.app;

import main.java.data_access.InMemAddCourseDAO;
import main.java.interface_adapter.ViewManagerModel;
import main.java.interface_adapter.home.HomeViewModel;
import main.java.interface_adapter.notes.AddCourseController;
import main.java.interface_adapter.notes.AddCoursePresenter;
import main.java.interface_adapter.notes.NotesViewModel;
import main.java.use_case.notes.AddCourseDataAccessInterface;
import main.java.use_case.notes.AddCourseInputBoundary;
import main.java.use_case.notes.AddCourseInteractor;
import main.java.use_case.notes.AddCourseOutputBoundary;
import main.java.view.NotesView;

public class NotesUseCaseFactory {
    private NotesUseCaseFactory() {}

    public static NotesView create(ViewManagerModel viewManagerModel,
                                   NotesViewModel notesViewModel,
                                   AddCourseDataAccessInterface addCourseDAO) {
        AddCourseController addCourseController = createAddCourseUseCase(viewManagerModel, notesViewModel, addCourseDAO);
        return new NotesView(notesViewModel, viewManagerModel, addCourseController);
    }

     public static AddCourseController createAddCourseUseCase(ViewManagerModel viewManagerModel,
                                                              NotesViewModel notesViewModel,
                                                              AddCourseDataAccessInterface addCourseDAO) {
         AddCourseOutputBoundary addCoursePresenter = new AddCoursePresenter(viewManagerModel, notesViewModel);
         AddCourseInputBoundary addCourseInteracotr = new AddCourseInteractor(addCourseDAO, addCoursePresenter);
         return new AddCourseController(addCourseInteracotr);


     }
}
