package main.java.use_case.logout;

import main.java.app.Constants;
import main.java.DBUserDataAccessObject;
import main.java.entity.DefaultUserFactory;
import main.java.entity.NotesFactory;
import main.java.entity.UserFactory;
import main.java.use_case.login.*;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class LogoutInteractorTest {

    @Test
    void execute() {
        UserFactory userFactory = new DefaultUserFactory();
        DBUserDataAccessObject userDAO = new DBUserDataAccessObject(new DefaultUserFactory(), new NotesFactory());
        LoginInputData loginInputData = new LoginInputData(Constants.TEST_USERNAME, Constants.TEST_USER_PW);
        userDAO.save(userFactory.create(Constants.TEST_USERNAME, Constants.TEST_USER_PW));
        LoginOutputBoundary loginPresenter = new LoginOutputBoundary() {
            @Override
            public void prepareSuccessView(LoginOutputData user) {

            }

            @Override
            public void prepareFailView(String error) {
                fail("Not supposed to fail");
            }
        };
        LoginInputBoundary loginInteractor = new LoginInteractor(userDAO, loginPresenter);
        loginInteractor.execute(loginInputData);
        LogoutOutputBoundary logoutPresenter = new LogoutOutputBoundary() {
            @Override
            public void prepareLogout() {
                assertNull(Constants.CURRENT_USER);
                assertNull(Constants.CURRENT_USER_OBJ);
            }
        };
        LogoutInputBoundary logoutInteractor = new LogoutInteractor(logoutPresenter);
        logoutInteractor.execute();
    }
}