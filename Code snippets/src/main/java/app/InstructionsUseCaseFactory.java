package main.java.app;

import main.java.interface_adapter.ViewManagerModel;
import main.java.interface_adapter.add_Question_Definition.DefQuesController;
import main.java.interface_adapter.instructions.InstructionsController;
import main.java.interface_adapter.instructions.InstructionsViewModel;
import main.java.interface_adapter.notes.AddCourseController;
import main.java.interface_adapter.notes.CreateNotesController;
import main.java.interface_adapter.notes.NotesViewModel;
import main.java.interface_adapter.quiz.QuizController;
import main.java.interface_adapter.quiz.QuizViewModel;
import main.java.use_case.add_Question_Definition.DefQuesDataAccessInterface;
import main.java.use_case.courses.AddCourseDataAccessInterface;
import main.java.use_case.find_user_courses.FindUserCourseDataAccessInterface;
import main.java.use_case.notes.NotesDataAccessInterface;
import main.java.use_case.quiz.QuizDataAccessInterface;
import main.java.view.InstructionsView;
import main.java.view.NotesView;

public class InstructionsUseCaseFactory {
    private InstructionsUseCaseFactory() {
    }

    public static InstructionsView create(ViewManagerModel viewManagerModel,
                                          InstructionsViewModel instructionsViewModel) {
        return new InstructionsView(instructionsViewModel, viewManagerModel);
    }
}
