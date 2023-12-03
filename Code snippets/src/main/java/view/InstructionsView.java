package main.java.view;

import main.java.app.Constants;
import main.java.interface_adapter.ViewManagerModel;
import main.java.interface_adapter.instructions.InstructionsController;
import main.java.interface_adapter.instructions.InstructionsState;
import main.java.interface_adapter.instructions.InstructionsViewModel;
import main.java.interface_adapter.notes.NotesState;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;

public class InstructionsView extends JPanel implements ActionListener, PropertyChangeListener {
    public final String viewName = "instructions";
    private final InstructionsViewModel instructionsViewModel;
    private final ViewManagerModel viewManagerModel;
    private final JTextArea instructionsContent;


    public InstructionsView(InstructionsViewModel instructionsViewModel, ViewManagerModel viewManagerModel) {
        this.instructionsViewModel = instructionsViewModel;
        this.viewManagerModel = viewManagerModel;
        instructionsViewModel.addPropertyChangeListener(this);
        JPanel tfPanel = new JPanel();
        tfPanel.setBorder(BorderFactory.createTitledBorder("Instructions "));
        JButton back = new JButton(instructionsViewModel.BACK_BUTTON_LABEL);
        tfPanel.add(back);
        back.addActionListener(new ActionListener() {
            @Override public void actionPerformed(ActionEvent e)
            {
                if (e.getSource().equals(back)) {
                viewManagerModel.setActiveView(Constants.HOME_VIEWNAME);
                viewManagerModel.firePropertyChanged();
                }
            }
        });
        instructionsContent = new JTextArea();
        instructionsContent.setFont(new Font("Serif", Font.ITALIC, 20));
        instructionsContent.setLineWrap(true);
        instructionsContent.setWrapStyleWord(true);
        instructionsContent.setBackground(new Color(0, 0, 0));
        instructionsContent.setEnabled(false);
        JScrollPane tAreaScrollPane = new JScrollPane(instructionsContent);
        tAreaScrollPane.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        tAreaScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        this.setLayout(new BorderLayout(5, 5));
        this.add(tfPanel, BorderLayout.NORTH);
        this.add(tAreaScrollPane, BorderLayout.CENTER);
        setVisible(true);
    }


    @Override
    public void actionPerformed(ActionEvent e) {
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        InstructionsState state = (InstructionsState) evt.getNewValue();
        instructionsContent.setText(state.getInstructions());
    }
}