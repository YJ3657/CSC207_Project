package main.java.interface_adapter.instructions;

import main.java.use_case.instructions.InstructionsInputBoundary;

public class InstructionsController {
    private final InstructionsInputBoundary instructionsInputBoundary;

    public InstructionsController(InstructionsInputBoundary instructionsInputBoundary) {
        this.instructionsInputBoundary = instructionsInputBoundary;
    }

    public void execute() {
        instructionsInputBoundary.execute();
    }
}
