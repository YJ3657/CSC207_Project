package main.java.app;

import main.java.entity.CourseFactory;
import main.java.entity.NotesFactory;
import main.java.interface_adapter.ViewManagerModel;
import main.java.interface_adapter.notes.*;
import main.java.use_case.courses.AddCourseDataAccessInterface;
import main.java.use_case.find_user_courses.FindUserCourseDataAccessInterface;
import main.java.use_case.notes.*;
import main.java.view.NotesView;

public class NotesUseCaseFactory {
    private NotesUseCaseFactory() {}

    public static NotesView create(ViewManagerModel viewManagerModel,
                                   NotesViewModel notesViewModel,
                                   FindUserCourseDataAccessInterface addUserCourseDAO,
                                   AddCourseDataAccessInterface addCourseDAO,
                                   NotesDataAccessInterface notesDataAccessInterface) {
        AddCourseController addCourseController = createAddCourseUseCase(viewManagerModel, notesViewModel, addUserCourseDAO, addCourseDAO);
        CreateNotesController createNotesController = createCreateNotesUseCase(viewManagerModel, notesViewModel,
                notesDataAccessInterface);
        return new NotesView(notesViewModel, viewManagerModel, addCourseController, createNotesController);
    }

     public static AddCourseController createAddCourseUseCase(ViewManagerModel viewManagerModel,
                                                              NotesViewModel notesViewModel,
                                                              FindUserCourseDataAccessInterface addUserCourseDAO,
                                                              AddCourseDataAccessInterface addCourseDAO) {
         AddCourseOutputBoundary addCoursePresenter = new AddCoursePresenter(viewManagerModel, notesViewModel);
         CourseFactory courseFactory = new CourseFactory();
         AddCourseInputBoundary addCourseInteractor = new AddCourseInteractor(addUserCourseDAO, addCourseDAO, addCoursePresenter, courseFactory);
         return new AddCourseController(addCourseInteractor);


     }

    public static CreateNotesController createCreateNotesUseCase(ViewManagerModel viewManagerModel,
                                                                 NotesViewModel notesViewModel,
                                                                 NotesDataAccessInterface notesDataAccessInterface) {
        CreateNotesOutputBoundary createNotesPresenter = new CreateNotesPresenter(viewManagerModel, notesViewModel);
        NotesFactory notesFactory = new NotesFactory();
        CreateNotesInputBoundary createNotesInteractor = new CreateNotesInteractor(notesDataAccessInterface,
                createNotesPresenter, notesFactory);
        return new CreateNotesController(createNotesInteractor);


    }
}
