package main.java.interface_adapter.instructions;

public class InstructionsState {
    private String instructions = "";

    public InstructionsState(InstructionsState instructionsState) {
        this.instructions = instructionsState.instructions;
    }

    public InstructionsState() {
    }

    public String getInstructions() {
        return instructions;
    }

    public void setInstructions(String instructions) {
        this.instructions = instructions;
    }
}
