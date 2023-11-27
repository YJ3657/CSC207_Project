package main.java.app;


import main.java.data_access.DBCourseDataAccessObject;
import main.java.entity.*;
import main.java.data_access.InMemoryQuizDAO;
import main.java.data_access.DBUserDataAccessObject;
import main.java.interface_adapter.ViewManagerModel;
import main.java.interface_adapter.home.HomeViewModel;
import main.java.interface_adapter.login.LoginViewModel;
import main.java.interface_adapter.notes.NotesViewModel;
import main.java.interface_adapter.quiz.QuizViewModel;
import main.java.interface_adapter.signup.SignupViewModel;
import main.java.use_case.add_Definition.DefinitionDataAccessInterface;
import main.java.use_case.notes.NotesDataAccessInterface;
import main.java.view.HomeView;
import main.java.view.LoginView;
import main.java.view.NotesView;
import main.java.view.ViewManager;
import main.java.use_case.quiz.QuizDataAccessInterface;
import main.java.view.*;

import javax.swing.*;
import java.awt.*;

public class Main {
    public static class App extends JFrame {
        public App() {
            setTitle("Meta Learning App");
            setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
            Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
            int width = (int) (screenSize.width * 0.75);  // 3/4 of screen width
            int height = (int) (screenSize.height * 0.75); // 3/4 of screen height

            // Set frame size
            setSize(width, height);
            Constants.FRAME_WIDTH = width;
            Constants.FRAME_HEIGHT = height;

            // Center the frame
            setLocation((screenSize.width - width) / 2, (screenSize.height - height) / 2);
        }

    }

    public static void main(String[] args) {
        // Build the main program window, the main panel containing the
        // various cards, and the layout, and stitch them together.

        // The main application window.
        App application = new App();

        CardLayout cardLayout = new CardLayout();

        // The various View objects. Only one view is visible at a time.
        JPanel views = new JPanel(cardLayout);
        application.add(views);

        //TODO: Are we instantiating new ViewManager below? What's happening?

        /*TODO: Answer below is copied from https://github.com/paulgries/UserLoginCleanArchitecture/blob/main/src/Main.java
        The observer watching for changes in the userViewModel. It will
         react to changes in application state by changing which view
         is showing. This is an anonymous object because we don't need to
         refer to it later.
        */

        // This keeps track of and manages which view is currently showing.
        ViewManagerModel viewManagerModel = new ViewManagerModel();
        new ViewManager(views, cardLayout, viewManagerModel);

        // The data for the views, such as username and password, are in the ViewModels.
        // This information will be changed by a presenter object that is reporting the
        // results from the use case. The ViewModels are observable, and will
        // be observed by the Views.
        NotesViewModel notesViewModel = new NotesViewModel();
        HomeViewModel homeViewModel = new HomeViewModel();
        LoginViewModel loginViewModel = new LoginViewModel();
        SignupViewModel signupViewModel = new SignupViewModel();
        QuizViewModel quizViewModel = new QuizViewModel();

//        NotesDataAccessObject notesDataAccessObject = new NotesDataAccessObject();
        DBCourseDataAccessObject addCourseDAO = new DBCourseDataAccessObject(new CourseFactory(), new QuestionFactory(), new DefinitionFactory(), new StudentFactory());

        DBUserDataAccessObject userDataAccessObject = new DBUserDataAccessObject(new DefaultUserFactory(), new NotesFactory());
        DBUserDataAccessObject signupuserdataaccessinterface = new DBUserDataAccessObject(new DefaultUserFactory(), new NotesFactory());
//        DBUserDataAccessObject signupuserdataaccessinterface = new DBUserDataAccessObject(new DefaultUserFactory());
        DefinitionDataAccessInterface definitionDAO = addCourseDAO;

        HomeView homeView = HomeUseCaseFactory.create(viewManagerModel, homeViewModel, notesViewModel, userDataAccessObject, loginViewModel);
        views.add(homeView, homeView.viewName);

        NotesView notesView = NotesUseCaseFactory.create(viewManagerModel,
                notesViewModel,
                quizViewModel,
                userDataAccessObject,
                addCourseDAO,
                userDataAccessObject,
                addCourseDAO, definitionDAO
                );
        views.add(notesView, notesView.viewName);

        UserFactory userFactory = new DefaultUserFactory();

        LoginView loginView = LoginUseCaseFactory.create(viewManagerModel, loginViewModel, homeViewModel,
                userDataAccessObject, userDataAccessObject, signupViewModel, userFactory);
        views.add(loginView, loginView.viewName);

        QuizView quizView = new QuizView(quizViewModel, viewManagerModel);
        views.add(quizView, quizView.viewName);

        viewManagerModel.setActiveView(loginView.viewName);  //set to loginView
        viewManagerModel.firePropertyChanged();

//        application.pack();
        application.setVisible(true);
    }
}