package main.java.interface_adapter.logout;

import main.java.use_case.LogOut.LogoutInputBoundary;

public class LogoutController {
    private final LogoutInputBoundary logoutInUseCaseInteractor;
    public LogoutController(LogoutInputBoundary logoutInUseCaseInteractor) {
        this.logoutInUseCaseInteractor = logoutInUseCaseInteractor;
    }
    public void execute() {
        logoutInUseCaseInteractor.execute();
    }
}
