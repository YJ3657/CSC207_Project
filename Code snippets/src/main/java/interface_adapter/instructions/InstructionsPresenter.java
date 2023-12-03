package main.java.interface_adapter.instructions;

import main.java.interface_adapter.ViewManagerModel;
import main.java.interface_adapter.home.HomeState;
import main.java.interface_adapter.home.HomeViewModel;
import main.java.interface_adapter.login.LoginState;
import main.java.use_case.instructions.InstructionsOutputBoundary;
import main.java.use_case.instructions.InstructionsOutputData;

import javax.swing.*;

public class InstructionsPresenter implements InstructionsOutputBoundary{
    private final InstructionsViewModel instructionsViewModel;
    private final ViewManagerModel viewManagerModel;

    public InstructionsPresenter(InstructionsViewModel instructionsViewModel,
                                 ViewManagerModel viewManagerModel) {
        this.instructionsViewModel = instructionsViewModel;
        this.viewManagerModel = viewManagerModel;
    }


    @Override
    public void prepareSuccessView(InstructionsOutputData outputData) {
        InstructionsState instructionsState = instructionsViewModel.getState();
        instructionsState.setInstructions(outputData.getInstructions());
        this.instructionsViewModel.setState(instructionsState);
        this.instructionsViewModel.firePropertyChanged();
        this.viewManagerModel.setActiveView(instructionsViewModel.getViewName());
        this.viewManagerModel.firePropertyChanged();
    }

    @Override
    public void prepareFailView(String error) {
        InstructionsState instructionsState = instructionsViewModel.getState();
        instructionsState.setInstructions(error);
        this.instructionsViewModel.setState(instructionsState);
        this.instructionsViewModel.firePropertyChanged();
        this.viewManagerModel.setActiveView(instructionsViewModel.getViewName());
        this.viewManagerModel.firePropertyChanged();
    }
}