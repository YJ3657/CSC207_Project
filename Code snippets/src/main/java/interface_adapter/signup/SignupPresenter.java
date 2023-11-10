package main.java.interface_adapter.signup;

import main.java.interface_adapter.ViewManagerModel;
import main.java.interface_adapter.home.HomeState;
import main.java.interface_adapter.home.HomeViewModel;
import main.java.use_case.signup.SignupOutputBoundary;
import main.java.use_case.signup.SignupOutputData;

public class SignupPresenter implements SignupOutputBoundary {
    private final SignupViewModel signupViewModel;
    private final HomeViewModel homeViewModel;
    private ViewManagerModel viewManagerModel;

    public SignupPresenter(ViewManagerModel viewManagerModel,
                           SignupViewModel signupViewModel,
                           HomeViewModel homeViewModel) {
        this.viewManagerModel = viewManagerModel;
        this.signupViewModel = signupViewModel;
        this.homeViewModel = homeViewModel;
    }

    @Override
    public void prepareSuccessView(SignupOutputData response) {
        // On success, switch to the home view.

        HomeState homeState = homeViewModel.getState();
        homeState.setUsername(response.getUsername());
        this.homeViewModel.setState(homeState);
        this.homeViewModel.firePropertyChanged();

        this.viewManagerModel.setActiveView(homeViewModel.getViewName());
        this.viewManagerModel.firePropertyChanged();
    }

    @Override
    public void prepareFailView(String error) {
        SignupState signupState = signupViewModel.getState();
        signupState.setUsernameError(error);
        signupViewModel.firePropertyChanged();
    }
}
