package main.java.interface_adapter;

import main.java.use_case.OpenNotesInputBoundary;

public class OpenNotesController {
    final OpenNotesInputBoundary openNotesInteractor;
    public OpenNotesController(OpenNotesInputBoundary openNotesInteractor) {
        this.openNotesInteractor = openNotesInteractor;
    }

    public void execute() {
        openNotesInteractor.execute();
    }

}
