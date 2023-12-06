package main.java.interface_adapter.notes;

import main.java.use_case.notes.open_notes.OpenNotesInputBoundary;

public class OpenNotesController {
    final OpenNotesInputBoundary openNotesInteractor;
    public OpenNotesController(OpenNotesInputBoundary openNotesInteractor) {
        this.openNotesInteractor = openNotesInteractor;
    }

    public void execute() {
        openNotesInteractor.execute();
    }

}
