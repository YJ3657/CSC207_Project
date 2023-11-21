package main.java.use_case.LogOut;

public class LogoutInteractor implements LogoutInputBoundary{
    LogoutOutputBoundary userPresenter;

    public LogoutInteractor(LogoutOutputBoundary userPresenter) {
        this.userPresenter = userPresenter;
    }
    @Override
    public void execute() {
        userPresenter.prepareLogout();
    }
}
