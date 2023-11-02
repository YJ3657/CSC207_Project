package main.java.app;

import main.java.interface_adapter.home.HomeViewModel;
import main.java.view.HomeView;
import main.java.view.NotesView;
import main.java.view.ViewManager;
import main.java.interface_adapter.ViewManagerModel;
import main.java.interface_adapter.notes.NotesViewModel;
import main.java.data_access.NotesDataAccessObject;

import javax.swing.*;
import java.awt.*;

public class Main {
    public static class App extends JFrame {
        public App() {
            setTitle("Meta Learning App");
            setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
            Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
            int width = (int) (screenSize.width * 0.75);  // 3/4 of screen width
            int height = (int) (screenSize.height * 0.75); // 3/4 of screen height

            // Set frame size
            setSize(width, height);

            // Center the frame
            setLocation((screenSize.width - width) / 2, (screenSize.height - height) / 2);
        }

    }
    public static void main(String[] args) {
        // Build the main program window, the main panel containing the
        // various cards, and the layout, and stitch them together.

        // The main application window.
        App application = new App();

        CardLayout cardLayout = new CardLayout();

        // The various View objects. Only one view is visible at a time.
        JPanel views = new JPanel(cardLayout);
        application.add(views);

        // This keeps track of and manages which view is currently showing.
        ViewManagerModel viewManagerModel = new ViewManagerModel();
        new ViewManager(views, cardLayout, viewManagerModel);

        // The data for the views, such as username and password, are in the ViewModels.
        // This information will be changed by a presenter object that is reporting the
        // results from the use case. The ViewModels are observable, and will
        // be observed by the Views.
        NotesViewModel notesViewModel = new NotesViewModel();
        HomeViewModel homeViewModel = new HomeViewModel();

        NotesDataAccessObject notesDataAccessObject;
        notesDataAccessObject = new NotesDataAccessObject();

        HomeView homeView = HomeUseCaseFactory.create(viewManagerModel, homeViewModel, notesViewModel, notesDataAccessObject);
        views.add(homeView, homeView.viewName);

        NotesView notesView = new NotesView(notesViewModel, homeViewModel, viewManagerModel);

        views.add(notesView, notesView.viewName);


        viewManagerModel.setActiveView(homeView.viewName);
        viewManagerModel.firePropertyChanged();

//        application.pack();
        application.setVisible(true);
    }
}