package main.java.app;

import main.java.interface_adapter.ViewManagerModel;
import main.java.interface_adapter.instructions.InstructionsViewModel;
import main.java.view.InstructionsView;

public class InstructionsUseCaseFactory {
    private InstructionsUseCaseFactory() {
    }

    public static InstructionsView create(ViewManagerModel viewManagerModel,
                                          InstructionsViewModel instructionsViewModel) {
        return new InstructionsView(instructionsViewModel, viewManagerModel);
    }
}
