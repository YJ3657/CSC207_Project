package main.java.use_case.instructions;

import main.java.data_access.FileInstructionsDataAccessObject;

import java.io.File;

public class InstructionsInteractor implements InstructionsInputBoundary{

    private FileInstructionsDataAccessObject fileInstructionsDataAccessObject;
    private InstructionsOutputBoundary instructionsOutputBoundary;
    public InstructionsInteractor(FileInstructionsDataAccessObject dataAccessObject, InstructionsOutputBoundary outputBoundary) {
        this.instructionsOutputBoundary = outputBoundary;
        this.fileInstructionsDataAccessObject = dataAccessObject;
    }

    @Override
    public void execute() {
        String content = fileInstructionsDataAccessObject.getInstructions();
        InstructionsOutputData outputData = new InstructionsOutputData(content);
        instructionsOutputBoundary.prepareSuccessView(outputData);
    }
}
