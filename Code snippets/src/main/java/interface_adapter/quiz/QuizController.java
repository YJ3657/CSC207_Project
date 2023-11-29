package main.java.interface_adapter.quiz;

import main.java.use_case.quiz.QuizInputBoundary;
import main.java.use_case.quiz.QuizInputData;

public class QuizController {
    final QuizInputBoundary quizInteractor;

    public QuizController(QuizInputBoundary quizInteractor) {
        this.quizInteractor = quizInteractor;
    }

    public void execute(String courseId) {
        QuizInputData quizInputData = new QuizInputData(courseId);
        quizInteractor.execute(quizInputData);
    }

}
