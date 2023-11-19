package main.java.use_case.quiz;

import main.java.entity.Definition;

import java.util.ArrayList;

public interface QuizDataAccessInterface {
    public ArrayList<String> getQuestions();

    public ArrayList<String> getAnswers();

    public void setQuestionAnswers();
}
