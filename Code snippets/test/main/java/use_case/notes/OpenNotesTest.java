package main.java.use_case.notes;

import main.java.app.Constants;
import main.java.app.Main;
import main.java.data_access.DBDataAccessObject;
import main.java.entity.*;
import main.java.view.HomeView;
import main.java.view.NotesView;
import org.junit.jupiter.api.Test;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.time.LocalDateTime;

import static org.junit.Assert.assertNotNull;

public class OpenNotesTest {
    @Test
    void TestOpenNotes() {
        Constants.CURRENT_USER = null;
        Constants.CURRENT_USER_OBJ = null;
        UserFactory userFactory = new DefaultUserFactory();
        DBDataAccessObject dbDataAccessObject = new DBDataAccessObject(userFactory, new NotesFactory(),
                new CourseFactory(), new StudentFactory(), new QuestionFactory(), new DefinitionFactory(), new ReminderFactory());
//        User user = userFactory.create(Constants.TEST_USERNAME, Constants.TEST_USER_PW);
//        Constants.CURRENT_USER = Constants.TEST_USERNAME;
//        Constants.CURRENT_USER_OBJ = user;
//        dbDataAccessObject.save(user);
        try {
            JFrame app = null;
            Main.main(null);
            Window[] windows = Window.getWindows();
            for (Window window : windows) {
                if (window instanceof JFrame) {
                    app = (JFrame) window;
                }
            }
            assertNotNull(app);
            Component root = app.getComponent(0);
            Component cp = ((JRootPane) root).getContentPane();
            JPanel jp = (JPanel) cp;
            JPanel cardLayout = (JPanel) jp.getComponent(0);
            NotesView notesView = (NotesView) cardLayout.getComponent(0);

            HomeView homeView = (HomeView) cardLayout.getComponent(5);
            JPanel homeBtnsPanel = (JPanel) homeView.getComponent(1);
            JButton NotesBtn = (JButton) homeBtnsPanel.getComponent(0);
            NotesBtn.doClick();

            JPanel notesButtonsPanel = (JPanel) notesView.getComponent(0);
            JButton AddCourseBtn = (JButton) notesButtonsPanel.getComponent(1);
            AddCourseBtn.doClick();

            assert 11==11;
            JPanel panel = (JPanel) notesView.coursesDisplay.getSelectedComponent();
            JSplitPane scrollPane = (JSplitPane) panel.getComponent(0);
            JScrollPane notePad = (JScrollPane) scrollPane.getRightComponent();
            JTextPane textPane = (JTextPane) notePad.getViewport().getView();
            textPane.setText("Aasdfasdfasdf");
            notesButtonsPanel.getComponent(4);




            JButton chatBtn = (JButton) homeBtnsPanel.getComponent(4);
            chatBtn.doClick();
            assertNotNull(Constants.ADD_COURSE_ERROR);



        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

    }
}
