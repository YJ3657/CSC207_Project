package main.java.interface_adapter.LogOut;

import main.java.interface_adapter.ViewManagerModel;
import main.java.interface_adapter.login.LoginState;
import main.java.interface_adapter.login.LoginViewModel;
import main.java.interface_adapter.signup.SignupState;
import main.java.interface_adapter.signup.SignupViewModel;
import main.java.use_case.LogOut.LogoutOutputBoundary;
import main.java.view.LoginView;

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
