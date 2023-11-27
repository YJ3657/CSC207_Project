package main.java.entity;

import main.java.entity.Notes;
import main.java.entity.Contents;
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

    public void addStudent(Student student) {
        this.students.add(student);
    }
    public List<Question> getQuestions() {
        return this.questions;
    }
    public void setQuestions(List<Question> questions) {
        this.questions = questions;
        return;
    }
    public List<Definition> getDefinitions() {
        return this.definitions;
    }
    public void setDefinitions(List<Definition> definitions) {
        this.definitions = definitions;
        return;
    }
}