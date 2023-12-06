package main.java.use_case.login;

import main.java.DBUserDataAccessObject;
import main.java.app.Constants;
import main.java.entity.DefaultUserFactory;
import main.java.entity.NotesFactory;
import main.java.entity.UserFactory;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class LoginInteractorTest {

    UserFactory userFactory;

    @Test
    void successTest() {
        userFactory = new DefaultUserFactory();
        DBUserDataAccessObject userDAO = new DBUserDataAccessObject(new DefaultUserFactory(), new NotesFactory());
        LoginInputData loginInputData = new LoginInputData(Constants.TEST_USERNAME, Constants.TEST_USER_PW);
        userDAO.save(userFactory.create(Constants.TEST_USERNAME, Constants.TEST_USER_PW));
        LoginOutputBoundary successPresenter = new LoginOutputBoundary() {
            @Override
            public void prepareSuccessView(LoginOutputData user) {
                assertEquals(user.getUsername(), Constants.TEST_USERNAME);
                assertEquals(Constants.CURRENT_USER, Constants.TEST_USERNAME);
                assertEquals(Constants.CURRENT_USER_OBJ.getId(), Constants.TEST_USERNAME);

            }

            @Override
            public void prepareFailView(String error) {
                fail("No failure");

            }
        };
        LoginInputBoundary interactor = new LoginInteractor(userDAO, successPresenter);
        interactor.execute(loginInputData);

    }
    @Test
    void failTestAccountDNE() {
        userFactory = new DefaultUserFactory();
        DBUserDataAccessObject userDAO = new DBUserDataAccessObject(new DefaultUserFactory(), new NotesFactory());
        LoginInputData loginInputData = new LoginInputData(Constants.TEST_USERNAME, Constants.TEST_USER_PW);
        LoginOutputBoundary successPresenter = new LoginOutputBoundary() {
            @Override
            public void prepareSuccessView(LoginOutputData user) {
                fail("No successview");
            }

            @Override
            public void prepareFailView(String error) {
                assertEquals(Constants.TEST_USERNAME + Constants.ACCOUNT_DNE, error);
            }
        };
        LoginInputBoundary interactor = new LoginInteractor(userDAO, successPresenter);
        interactor.execute(loginInputData);

    }

    @Test
    void failTestWrongPW() {
        userFactory = new DefaultUserFactory();
        DBUserDataAccessObject userDAO = new DBUserDataAccessObject(new DefaultUserFactory(), new NotesFactory());
        LoginInputData loginInputData = new LoginInputData(Constants.TEST_USERNAME, Constants.TEST_USER_PW + "1");
        userDAO.save(userFactory.create(Constants.TEST_USERNAME, Constants.TEST_USER_PW));
        LoginOutputBoundary successPresenter = new LoginOutputBoundary() {
            @Override
            public void prepareSuccessView(LoginOutputData user) {
                fail("No successview");
            }

            @Override
            public void prepareFailView(String error) {
                assertEquals("Incorrect password for " + Constants.TEST_USERNAME + ".", error);
            }
        };
        LoginInputBoundary interactor = new LoginInteractor(userDAO, successPresenter);
        interactor.execute(loginInputData);

    }
}