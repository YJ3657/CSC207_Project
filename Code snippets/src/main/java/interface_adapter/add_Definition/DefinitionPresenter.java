package main.java.interface_adapter.add_Definition;

import main.java.interface_adapter.notes.NotesViewModel;
import main.java.use_case.add_Definition.DefinitionOutputBoundary;
import main.java.use_case.add_Definition.DefinitionOutputData;

import javax.swing.*;

public class DefinitionPresenter implements DefinitionOutputBoundary {
    private final NotesViewModel notesViewModel;

    public DefinitionPresenter(NotesViewModel notesViewModel){
        this.notesViewModel = notesViewModel;
    }

    @Override
    public void prepareSuccessView() {
        System.out.println("Definition added to database");
    }

    @Override
    public void prepareFailView(String error) {
        JOptionPane.showMessageDialog(null, error, "Error", JOptionPane.ERROR_MESSAGE);
    }
}
