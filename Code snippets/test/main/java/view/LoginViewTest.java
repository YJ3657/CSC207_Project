package main.java.view;

import main.java.app.Constants;
import main.java.data_access.DBDataAccessObject;
import main.java.entity.*;
import main.java.interface_adapter.ViewManagerModel;
import main.java.interface_adapter.home.HomeViewModel;
import main.java.interface_adapter.login.LoginController;
import main.java.interface_adapter.login.LoginPresenter;
import main.java.interface_adapter.login.LoginState;
import main.java.interface_adapter.login.LoginViewModel;
import main.java.interface_adapter.signup.SignupController;
import main.java.interface_adapter.signup.SignupPresenter;
import main.java.interface_adapter.signup.SignupViewModel;
import main.java.use_case.login.LoginInteractor;
import main.java.use_case.login.LoginOutputBoundary;
import main.java.use_case.login.LoginUserDataAccessInterface;
import main.java.use_case.signup.SignupInteractor;
import main.java.use_case.signup.SignupOutputBoundary;
import main.java.use_case.signup.SignupUserDataAccessInterface;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.beans.PropertyChangeEvent;

public class LoginViewTest {
    LoginViewModel loginViewModel;
    SignupViewModel signupViewModel;
    LoginInteractor loginInteractor;
    SignupInteractor signupInteractor;

    LoginView loginView;
    @BeforeEach
    void setUp() {
        ViewManagerModel viewManagerModel = new ViewManagerModel();
        HomeViewModel homeViewModel = new HomeViewModel();
        this.loginViewModel = new LoginViewModel();
        this.signupViewModel = new SignupViewModel();

        LoginUserDataAccessInterface userDataAccessObject = new DBDataAccessObject(new DefaultUserFactory(), new NotesFactory(), new CourseFactory(), new StudentFactory(), new QuestionFactory(), new DefinitionFactory(), new ReminderFactory());
        SignupUserDataAccessInterface userDataAccessObject2 = (SignupUserDataAccessInterface) userDataAccessObject;
        LoginOutputBoundary userPresenter = new LoginPresenter(viewManagerModel, homeViewModel, loginViewModel);
        SignupOutputBoundary userPresenter2 = new SignupPresenter(viewManagerModel, signupViewModel, homeViewModel);

        this.loginInteractor = new LoginInteractor(userDataAccessObject, userPresenter);
        this.signupInteractor = new SignupInteractor(userDataAccessObject2, userPresenter2, new DefaultUserFactory());
        this.loginView = new LoginView(loginViewModel, new LoginController(loginInteractor), new SignupController(signupInteractor), signupViewModel);
    }

    @Test
    void propertyChange(){
        LoginState newState = new LoginState();
        newState.setUsername(Constants.TEST_USERNAME);
        newState.setPassword(Constants.TEST_USER_PW);
        PropertyChangeEvent evt = new PropertyChangeEvent(loginViewModel,null, null, newState);
        loginView.propertyChange(evt);
        assert loginView.usernameInputField.getText().equals(Constants.TEST_USERNAME);
        assert loginView.passwordInputField.getText().equals(Constants.TEST_USER_PW);
    }

    @Test
    void loginSuccessful(){


    }}
