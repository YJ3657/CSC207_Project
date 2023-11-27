package main.java.interface_adapter.notes;

import main.java.use_case.notes.CreateNotesInputBoundary;
import main.java.use_case.notes.CreateNotesInputData;

public class CreateNotesController {
    final CreateNotesInputBoundary createNotesInteractor;
    public CreateNotesController(CreateNotesInputBoundary createNotesInteractor) {
        this.createNotesInteractor = createNotesInteractor;
    }

    public void execute(String title, String content, String courseId, int chapterNo, boolean overwrite) {
        CreateNotesInputData createNotesInputData = new CreateNotesInputData(courseId, content, chapterNo, title, overwrite);

        createNotesInteractor.execute(createNotesInputData);
    }

    public void execute(String title, String content, String courseId, int chapterNo) {
        CreateNotesInputData createNotesInputData = new CreateNotesInputData(title, content, courseId);

        createNotesInteractor.execute(createNotesInputData);
    }
}
