package main.java.entity;

import main.java.entity.Notes;
import main.java.entity.Student;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


//DONE FOR NOW
public class Course {
    private String courseId;
    private List<Student> students;
    private List<Question> questions;
    private List<Definition> definitions;

    public Course(String courseId){
        this.courseId = courseId;
        this.students = new ArrayList<>();
        this.questions = new ArrayList<>();
        this.definitions = new ArrayList<>();
    }

    public void setId(String courseId) {
        this.courseId = courseId;
    }

    public String getId() {
        return courseId;
    }

    public List<Student> getStudents() {
        return this.students;
    }

    public void addStudent(Student student){
        students.add(student);
    }
    public void setStudents(List<Student> students) {
        this.students = students;
        return;
    }
    public List<Question> getQuestions() {
        return this.questions;
    }
    public void setQuestions(List<Question> questions) {
        this.questions = questions;
        return;
    }

    public void setQuestion(Question question){
        questions.add(question);
    }

    public List<String> getQuestionQuestions(){
        List<String> questionQuestions = new ArrayList<>();
        for (Question ques: this.questions){
            questionQuestions.add(ques.getQuestion());
        }
        return questionQuestions;
    }

    public List<Definition> getDefinitions() {
        return this.definitions;
    }

    public List<String> getDefinitionTerms(){
        List<String> definitionTerms = new ArrayList<>();
        for (Definition def: this.definitions){
            definitionTerms.add(def.getWord());
        }
        return definitionTerms;
    }
    public void setDefinitions(List<Definition> definitions) {
        this.definitions = definitions;
        return;
    }

    public void setDefinition(Definition definition) {
        definitions.add(definition);
    }

}