package main.java.interface_adapter.add_Question_Definition;

import main.java.interface_adapter.notes.NotesViewModel;
import main.java.use_case.add_Question_Definition.DefQuesOutputBoundary;
import main.java.use_case.add_Question_Definition.DefQuesOutputData;

import javax.swing.*;

public class DefQuesPresenter implements DefQuesOutputBoundary {
    private final NotesViewModel notesViewModel;

    public DefQuesPresenter(NotesViewModel notesViewModel){
        this.notesViewModel = notesViewModel;
    }

    @Override
    public void prepareSuccessView(DefQuesOutputData defQuesOutputData) {
        String msg = defQuesOutputData.getMsg();
        if (!msg.equals("")){
            JOptionPane.showMessageDialog(null, msg, "Update", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    @Override
    public void prepareFailView(DefQuesOutputData defQuesOutputData) {
        String error = defQuesOutputData.getMsg();
        JOptionPane.showMessageDialog(null, error, "Error", JOptionPane.ERROR_MESSAGE);
    }
}
