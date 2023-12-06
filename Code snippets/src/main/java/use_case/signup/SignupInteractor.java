package main.java.use_case.signup;

import main.java.app.Constants;
import main.java.entity.User;
import main.java.entity.UserFactory;

public class SignupInteractor implements SignupInputBoundary {
    final SignupUserDataAccessInterface userDataAccessObject;
    final SignupOutputBoundary userPresenter;
    final UserFactory userFactory;

    public SignupInteractor(SignupUserDataAccessInterface signupDataAccessInterface,
                            SignupOutputBoundary signupOutputBoundary,
                            UserFactory userFactory) {
        this.userDataAccessObject = signupDataAccessInterface;
        this.userPresenter = signupOutputBoundary;
        this.userFactory = userFactory;
    }

    @Override
    public void execute(SignupInputData signupInputData) {
        String username = signupInputData.getUsername();
        String password = signupInputData.getPassword();
        if (userDataAccessObject.existsByName(username)) {
            userPresenter.prepareFailView(Constants.USER_EXISTS_ERROR);
        } else if (!password.equals(signupInputData.getRepeatPassword())) {
            userPresenter.prepareFailView(Constants.PW_DONT_MATCH_ERROR);
        } else {
            User user = userFactory.create(username, password);
            userDataAccessObject.save(user);
            Constants.CURRENT_USER = user.getId();
            Constants.CURRENT_USER_OBJ = user;
            SignupOutputData signupOutputData = new SignupOutputData(user.getId(), false);
            userPresenter.prepareSuccessView(signupOutputData);
        }
    }
}
