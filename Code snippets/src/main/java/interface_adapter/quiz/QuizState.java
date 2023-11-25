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

//    public Map<Course, List<Definition>> getQuestions() {
//        return questions;
//    }

//    public List<Definition> getSingleCourseQuestions(Course course){
//        try{
//            return questions.get(course);
//
//        }catch (NoSuchElementException e){
//            System.out.println(e + "\nReturning empty list");
//            return new ArrayList<>();
//        }
//    }
//
//    public void addSingleCourseQuestion(Course course, Definition question){
//        if (!questions.containsKey(course)){
//            questions.put(course, new ArrayList<>());
//        }
//        questions.get(course).add(question);
//    }
//
//    public void addSingleCourseQuestions(Course course, List<Definition> questions){
//        for (Definition ques: questions){
//            this.addSingleCourseQuestion(course, ques);
//        }
//    }
//
}
