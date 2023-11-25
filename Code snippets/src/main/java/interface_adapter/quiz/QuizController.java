package main.java.interface_adapter.quiz;

import main.java.use_case.quiz.QuizInputBoundary;

public class QuizController {
    final QuizInputBoundary quizInteractor;

    public QuizController(QuizInputBoundary quizInteractor) {
        this.quizInteractor = quizInteractor;
    }

    public void execute() {
        quizInteractor.execute();
    }

}
