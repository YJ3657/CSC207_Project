package main.java.use_case.quiz;

import main.java.app.Constants;

public class QuizInteractor implements QuizInputBoundary{
    QuizDataAccessInterface quizDAO;
    QuizOutputBoundary quizPresenter;
    public QuizInteractor(QuizDataAccessInterface quizDAO, QuizOutputBoundary quizPresenter) {
        this.quizDAO = quizDAO;
        this.quizPresenter = quizPresenter;
    }
    public void execute() {
        quizDAO.setQuestionAnswers(); // TODO: For Jerry: Remove this once we're hooked up with DB
        QuizOutputData quizOutputData = new QuizOutputData(quizDAO.getQuestions(), quizDAO.getAnswers());
        if (!quizOutputData.answers.isEmpty()) {
            quizPresenter.prepareSuccessView(quizOutputData);
        } else {
            quizPresenter.prepareFailView(Constants.QUIZ_ERROR);
        }
    }
}
