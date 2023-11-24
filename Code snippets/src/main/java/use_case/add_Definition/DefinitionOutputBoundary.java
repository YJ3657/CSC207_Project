package main.java.use_case.add_Definition;

public interface DefinitionOutputBoundary {
    void prepareSuccessView();

    void prepareFailView(String error);
}
