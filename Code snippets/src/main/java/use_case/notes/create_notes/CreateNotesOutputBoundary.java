package main.java.use_case.notes.create_notes;

public interface CreateNotesOutputBoundary {
    void prepareSuccessView(CreateNotesOutputData notes);
    void prepareFailView(String error);
}
