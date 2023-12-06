package main.java.interface_adapter.quiz;

import main.java.entity.Course;
import main.java.entity.Definition;
import main.java.interface_adapter.notes.NotesState;

import java.util.*;

public class QuizState {
    private ArrayList<String> questions; //necessary?

    private ArrayList<String> answers;

    public QuizState(QuizState copy) {
        questions = copy.questions;
        answers = copy.answers;
    }

    public QuizState() {
    }

    public void setQuestions(ArrayList<String> questions) {
        this.questions = questions;
    }

    public ArrayList<String> getQuestions() {
        return questions;
    }

    public void setAnswers(ArrayList<String> answers) {
        this.answers = answers;
    }

    public ArrayList<String> getAnswers() {
        return answers;
    }

}
