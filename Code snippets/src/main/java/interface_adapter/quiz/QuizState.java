package main.java.interface_adapter.quiz;

import main.java.entity.Course;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class QuizState {
    private Map<Course, Course> questions; //necessary?

    public QuizState(QuizState copy) {
        this();
        questions = copy.questions;
        courses = copy.courses;
    }

    public QuizState() {
        questions = new HashMap<String, Course>();
        courses = new ArrayList<String>();
    }

    public void setQuestions(Map<String, Course> questions) {
        this.questions = questions;
    }

    public Map<String, Course> getQuestions() {
        return questions;
    }

    public void setCourses(ArrayList<String> courses) {
        this.courses = courses;
    }

    public ArrayList<String> getCourses() {
        return courses;
    }

}
