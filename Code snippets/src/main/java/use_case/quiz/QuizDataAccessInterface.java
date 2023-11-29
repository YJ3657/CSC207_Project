package main.java.use_case.quiz;

import main.java.entity.Definition;

import java.util.ArrayList;

public interface QuizDataAccessInterface {
    public ArrayList<String> getQuestions(String courseId);

    public ArrayList<String> getAnswers(String courseId);

//    public void setQuestionAnswers();
}
