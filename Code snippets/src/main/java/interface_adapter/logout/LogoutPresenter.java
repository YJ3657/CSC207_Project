package main.java.interface_adapter.logout;

import main.java.interface_adapter.ViewManagerModel;
import main.java.interface_adapter.login.LoginState;
import main.java.interface_adapter.login.LoginViewModel;
import main.java.use_case.logout.LogoutOutputBoundary;

public class LogoutPresenter implements LogoutOutputBoundary {
    private final LoginViewModel loginViewModel;
    private ViewManagerModel viewManagerModel;

    public LogoutPresenter(LoginViewModel loginViewModel, ViewManagerModel viewManagerModel) {
        this.loginViewModel = loginViewModel;
        this.viewManagerModel = viewManagerModel;
    }

    @Override
    public void prepareLogout() {
        //switch to signup view
        LoginState loginState = new LoginState();
        this.loginViewModel.setState(loginState);
        loginViewModel.firePropertyChanged();

        viewManagerModel.setActiveView(loginViewModel.getViewName());
        viewManagerModel.firePropertyChanged();
    }
}
