package main.java.use_case.logout;

import main.java.app.Constants;

public class LogoutInteractor implements LogoutInputBoundary{
    LogoutOutputBoundary userPresenter;

    public LogoutInteractor(LogoutOutputBoundary userPresenter) {
        this.userPresenter = userPresenter;
    }
    @Override
    public void execute() {
        Constants.CURRENT_USER = null;
        Constants.CURRENT_USER_OBJ = null;
        userPresenter.prepareLogout();
    }
}
