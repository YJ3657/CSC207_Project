package main.java.use_case.add_Definition;

public interface DefinitionOutputBoundary {
    void prepareSuccessView(String msg);

    void prepareFailView(String error);
}
