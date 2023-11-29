package main.java.use_case.quiz;

import java.util.ArrayList;

public class QuizOutputData {
    ArrayList<String> answers;

    ArrayList<String> questions;

    public QuizOutputData(ArrayList<String> questions, ArrayList<String> answers) {
        this.questions = questions;
        this.answers = answers;
    }

    public ArrayList<String> getAnswers() {
        return answers;
    }

    public ArrayList<String> getQuestions() {
        return questions;
    }
}
