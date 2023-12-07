package main.java.interface_adapter.notes;

import main.java.app.Constants;
import main.java.use_case.notes.create_notes.CreateNotesInputBoundary;
import main.java.use_case.notes.create_notes.CreateNotesInputData;

public class CreateNotesController {
    final CreateNotesInputBoundary createNotesInteractor;
    public CreateNotesController(CreateNotesInputBoundary createNotesInteractor) {
        this.createNotesInteractor = createNotesInteractor;
    }

    public void execute(String title, String content, String courseId, boolean overwrite) {

        //how does chapter number work?
        CreateNotesInputData createNotesInputData = new CreateNotesInputData(Constants.CURRENT_USER, courseId, content,
                title, overwrite);

        createNotesInteractor.execute(createNotesInputData);
    }

    public void execute(String title, String content, String courseId, int chapterNo) {
        CreateNotesInputData createNotesInputData = new CreateNotesInputData(title, content, courseId, chapterNo);

        createNotesInteractor.execute(createNotesInputData);
    }
}
