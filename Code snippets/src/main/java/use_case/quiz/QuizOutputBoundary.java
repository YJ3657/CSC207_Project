package main.java.use_case.quiz;

public interface QuizOutputBoundary {

    public void prepareSuccessView(QuizOutputData quizOutputData);

    public void prepareFailView(String error);
}
