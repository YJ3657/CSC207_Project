package main.java.view;

import main.java.app.Constants;
import main.java.interface_adapter.ViewManagerModel;
import main.java.interface_adapter.quiz.QuizViewModel;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

public class QuizViewTest {
    private QuizView quizView;
    @BeforeEach
    void setUp() {
        QuizViewModel quizViewModel = new QuizViewModel();
        ViewManagerModel viewManagerModel = new ViewManagerModel();
        quizView = new QuizView(quizViewModel, viewManagerModel);
    }

    @Test
    void setUpQuiz() {
        ArrayList<String> questions = new ArrayList<String>();
        ArrayList<String> answers = new ArrayList<String>();
        questions.add(Constants.CONTINUITY_QUES);
        questions.add(Constants.DERIVATIVES_QUES);
        answers.add(Constants.CONTIUNUITY_DEF);
        answers.add(Constants.DERIVATIVES_DEF);
        quizView.setUpQuiz(questions, answers);
        assert quizView.getComponents().length == 2; //2 questions added
    }

    @Test
    void propertyChange() {
    }
}
