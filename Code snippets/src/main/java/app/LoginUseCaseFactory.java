    package main.java.app;

    import main.java.interface_adapter.ViewManagerModel;
    import main.java.interface_adapter.home.HomeViewModel;
    import main.java.interface_adapter.login.LoginController;
    import main.java.interface_adapter.login.LoginPresenter;
    import main.java.interface_adapter.login.LoginViewModel;
    import main.java.use_case.login.LoginInputBoundary;
    import main.java.use_case.login.LoginInteractor;
    import main.java.use_case.login.LoginOutputBoundary;
    import main.java.use_case.login.LoginUserDataAccessInterface;
    import main.java.view.LoginView;

    import javax.swing.*;
    import java.io.IOException;

    public class LoginUseCaseFactory {

        //Don't want to instantiate
        private LoginUseCaseFactory(){};
        public static LoginView create(
                ViewManagerModel viewManagerModel,
                LoginViewModel loginViewModel,
                HomeViewModel homeViewModel,
                LoginUserDataAccessInterface userDataAccessObject) {

            try {
                LoginController loginController = createLoginUseCase(viewManagerModel, loginViewModel, homeViewModel, userDataAccessObject);
                return new LoginView(loginViewModel, loginController);
            } catch (IOException e) {
                JOptionPane.showMessageDialog(null, "Could not open database.");
            }

            return null;
        }

        private static LoginController createLoginUseCase(
                ViewManagerModel viewManagerModel,
                LoginViewModel loginViewModel,
                HomeViewModel homeViewModel,
                LoginUserDataAccessInterface userDataAccessObject) throws IOException {

            // Notice how we pass this method's parameters to the Presenter.
            LoginOutputBoundary loginOutputBoundary = new LoginPresenter(viewManagerModel, homeViewModel, loginViewModel);

//            UserFactory userFactory = new UserFactory();  // no idea what this is for in CACoding

            LoginInputBoundary loginInteractor = new LoginInteractor(
                    userDataAccessObject, loginOutputBoundary);

            return new LoginController(loginInteractor);
        }
    }
