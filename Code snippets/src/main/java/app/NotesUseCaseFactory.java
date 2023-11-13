package main.java.app;

import main.java.entity.CourseFactory;
import main.java.interface_adapter.ViewManagerModel;
import main.java.interface_adapter.notes.AddCourseController;
import main.java.interface_adapter.notes.AddCoursePresenter;
import main.java.interface_adapter.notes.NotesViewModel;
import main.java.use_case.courses.AddCourseDataAccessInterface;
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
         CourseFactory courseFactory = new CourseFactory();
         AddCourseInputBoundary addCourseInteractor = new AddCourseInteractor(addCourseDAO, addCoursePresenter, courseFactory);
         return new AddCourseController(addCourseInteractor);


     }
}
