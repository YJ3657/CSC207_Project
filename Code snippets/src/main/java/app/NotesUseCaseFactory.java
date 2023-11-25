package main.java.app;

import main.java.entity.CourseFactory;
import main.java.entity.NotesFactory;
import main.java.entity.Student;
import main.java.entity.StudentFactory;
import main.java.interface_adapter.ViewManagerModel;
import main.java.interface_adapter.add_Definition.DefinitionController;
import main.java.interface_adapter.add_Definition.DefinitionPresenter;
import main.java.interface_adapter.notes.*;
import main.java.interface_adapter.quiz.QuizController;
import main.java.interface_adapter.quiz.QuizPresenter;
import main.java.interface_adapter.quiz.QuizViewModel;
import main.java.use_case.add_Definition.DefinitionDataAccessInterface;
import main.java.use_case.add_Definition.DefinitionInputBoundary;
import main.java.use_case.add_Definition.DefinitionInteractor;
import main.java.use_case.courses.AddCourseDataAccessInterface;
import main.java.use_case.find_user_courses.FindUserCourseDataAccessInterface;
import main.java.use_case.notes.*;
import main.java.use_case.quiz.QuizDataAccessInterface;
import main.java.use_case.quiz.QuizInputBoundary;
import main.java.use_case.quiz.QuizInteractor;
import main.java.view.NotesView;

public class NotesUseCaseFactory {
    private NotesUseCaseFactory() {}

    public static NotesView create(ViewManagerModel viewManagerModel,
                                   NotesViewModel notesViewModel,
                                   QuizViewModel quizViewModel,
                                   FindUserCourseDataAccessInterface addUserCourseDAO,
                                   AddCourseDataAccessInterface addCourseDAO,
                                   NotesDataAccessInterface notesDataAccessInterface,
                                   QuizDataAccessInterface quizDAO, DefinitionDataAccessInterface definitionDAO) {
        AddCourseController addCourseController = createAddCourseUseCase(viewManagerModel, notesViewModel, addUserCourseDAO, addCourseDAO);
        CreateNotesController createNotesController = createCreateNotesUseCase(viewManagerModel, notesViewModel,
                notesDataAccessInterface);
        QuizController quizController = createQuizUseCase(viewManagerModel, quizViewModel, quizDAO);
        DefinitionController definitionController = createDefinitionUseCase(notesViewModel, definitionDAO);
        return new NotesView(notesViewModel, viewManagerModel, addCourseController, createNotesController, quizController, definitionController);
    }

     public static AddCourseController createAddCourseUseCase(ViewManagerModel viewManagerModel,
                                                              NotesViewModel notesViewModel,
                                                              FindUserCourseDataAccessInterface addUserCourseDAO,
                                                              AddCourseDataAccessInterface addCourseDAO) {
         AddCourseOutputBoundary addCoursePresenter = new AddCoursePresenter(viewManagerModel, notesViewModel);
         CourseFactory courseFactory = new CourseFactory();
         StudentFactory studentFactory = new StudentFactory();
         AddCourseInputBoundary addCourseInteractor = new AddCourseInteractor(addUserCourseDAO, addCourseDAO, addCoursePresenter, courseFactory, studentFactory);
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

    public static QuizController createQuizUseCase(ViewManagerModel viewManagerModel,
                                                   QuizViewModel quizViewModel,
                                                   QuizDataAccessInterface quizDAO) {
//        QuizViewModel quizViewModel = new QuizViewModel();
        QuizPresenter quizPresenter = new QuizPresenter(viewManagerModel, quizViewModel);
        QuizInputBoundary quizInteractor = new QuizInteractor(quizDAO, quizPresenter);
        return new QuizController(quizInteractor);
    }

    public static DefinitionController createDefinitionUseCase(NotesViewModel notesViewModel, DefinitionDataAccessInterface definitionDataAccessObject){
        DefinitionPresenter definitionPresenter = new DefinitionPresenter(notesViewModel);
        DefinitionInputBoundary definitionInteractor = new DefinitionInteractor(definitionDataAccessObject, definitionPresenter);
        return new DefinitionController(definitionInteractor);
    }
}
