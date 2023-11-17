package main.java.interface_adapter.quiz;

import main.java.entity.Course;
import main.java.entity.Definition;

import java.util.*;

public class QuizState {
    private Map<Course, List<Definition>> questions; //necessary?

    public QuizState(QuizState copy) {
        this();
        questions = copy.questions;
    }

    public QuizState() {
        questions = new HashMap<Course, List<Definition>>();
    }

    public void setQuestions(Map<Course, List<Definition>> questions) {
        this.questions = questions;
    }

    public Map<Course, List<Definition>> getQuestions() {
        return questions;
    }

    public List<Definition> getSingleCourseQuestions(Course course){
        try{
            return questions.get(course);

        }catch (NoSuchElementException e){
            System.out.println(e + "\nReturning empty list");
            return new ArrayList<>();
        }
    }

    public void addSingleCourseQuestion(Course course, Definition question){
        if (!questions.containsKey(course)){
            questions.put(course, new ArrayList<>());
        }
        questions.get(course).add(question);
    }

    public void addSingleCourseQuestions(Course course, List<Definition> questions){
        for (Definition ques: questions){
            this.addSingleCourseQuestion(course, ques);
        }
    }

}
