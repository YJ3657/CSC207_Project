package main.java.use_case.signup;

import main.java.app.Constants;
import DBUserDataAccessObject;
import main.java.entity.DefaultUserFactory;
import main.java.entity.NotesFactory;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SignupInteractorTest {

    @Test
    void successTest() {
        DefaultUserFactory userFactory = new DefaultUserFactory();
        NotesFactory notesFactory = new NotesFactory();
        DBUserDataAccessObject userDAO = new DBUserDataAccessObject(userFactory, notesFactory);
        SignupInputData signupInputData = new SignupInputData(Constants.TEST_USERNAME,
                Constants.TEST_USER_PW, Constants.TEST_USER_PW);
        SignupOutputBoundary successPresenter = new SignupOutputBoundary() {
            @Override
            public void prepareSuccessView(SignupOutputData user) {
                assertEquals(Constants.CURRENT_USER, Constants.TEST_USERNAME);
                assert Constants.CURRENT_USER_OBJ.getId().equals(Constants.TEST_USERNAME);
                assert user.getUsername().equals(Constants.TEST_USERNAME);
            }

            @Override
            public void prepareFailView(String error) {
                fail("Failure not expected");
            }
        };
        SignupInputBoundary signupInteractor = new SignupInteractor(userDAO, successPresenter, userFactory);
        signupInteractor.execute(signupInputData);

    }

    @Test
    void failureTestUserExists() {
        DefaultUserFactory userFactory = new DefaultUserFactory();
        NotesFactory notesFactory = new NotesFactory();
        DBUserDataAccessObject userDAO = new DBUserDataAccessObject(userFactory, notesFactory);
        userDAO.save(userFactory.create(Constants.TEST_USERNAME, Constants.TEST_USER_PW));
        SignupInputData signupInputData = new SignupInputData(Constants.TEST_USERNAME,
                Constants.TEST_USER_PW, Constants.TEST_USER_PW);
        SignupOutputBoundary failurePresenter = new SignupOutputBoundary() {
            @Override
            public void prepareSuccessView(SignupOutputData user) {
                fail("SuccessView not expected");
            }

            @Override
            public void prepareFailView(String error) {
                assertEquals(Constants.USER_EXISTS_ERROR, error);
            }
        };
        SignupInputBoundary signupInteractor = new SignupInteractor(userDAO, failurePresenter, userFactory);
        signupInteractor.execute(signupInputData);

    }

    @Test
    void failurePwDontMatch() {
        DefaultUserFactory userFactory = new DefaultUserFactory();
        NotesFactory notesFactory = new NotesFactory();
        DBUserDataAccessObject userDAO = new DBUserDataAccessObject(userFactory, notesFactory);
        SignupInputData signupInputData = new SignupInputData(Constants.TEST_USERNAME,
                Constants.TEST_USER_PW, Constants.TEST_USER_PW + "1");
        SignupOutputBoundary failurePresenter = new SignupOutputBoundary() {
            @Override
            public void prepareSuccessView(SignupOutputData user) {
                fail("SuccessView not expected");
            }

            @Override
            public void prepareFailView(String error) {
                assertEquals(Constants.PW_DONT_MATCH_ERROR, error);
            }
        };
        SignupInputBoundary signupInteractor = new SignupInteractor(userDAO, failurePresenter, userFactory);
        signupInteractor.execute(signupInputData);

    }

}