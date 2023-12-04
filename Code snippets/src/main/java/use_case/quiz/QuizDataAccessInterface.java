package main.java.use_case.quiz;

import java.util.ArrayList;

public interface QuizDataAccessInterface {
    public ArrayList<String> getQuizQuestions(String courseId);

    public ArrayList<String> getAnswers(String courseId);

//    public void setQuestionAnswers();
}
