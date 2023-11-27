package main.java.use_case.add_Definition;

public class DefinitionOutputData {
    private boolean useCaseFailed;

    public DefinitionOutputData(boolean useCaseFailed){
        this.useCaseFailed = useCaseFailed;
    }

    public boolean isUseCaseFailed() {
        return useCaseFailed;
    }
}
