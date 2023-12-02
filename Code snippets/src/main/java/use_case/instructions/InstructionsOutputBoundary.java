package main.java.use_case.instructions;

public interface InstructionsOutputBoundary {
    void prepareSuccessView(InstructionsOutputData outputData);

    void prepareFailView(String error);
}
