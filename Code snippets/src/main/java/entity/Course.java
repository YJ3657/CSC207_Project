package main.java.entity;

import main.java.Pair;
import main.java.entity.Notes;
//import main.java.entity.Contents;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


//DONE FOR NOW
public class Course {
    private String courseId;
    private HashMap<Integer, List<String>> contents;

    private ArrayList<String> enrolledStudentId;
    private HashMap<Integer, List<Question>> questions;
    private HashMap<Integer, List<Definition>> definitions;



    public Course(String courseid){
        this.courseId = courseid;
        contents = new HashMap<>();
        enrolledStudentId = new ArrayList<>();
        questions = new HashMap<>();
        definitions = new HashMap<>();
    }

    public void setId(String courseId) {
        this.courseId = courseId;
    }

    public String getId() {
        return courseId;
    }

    public void addStudent(String userId) { enrolledStudentId.add(userId);}

    public ArrayList<String> getStudents() {
        return enrolledStudentId;
    }

    public void addContent(Integer chapterNo, String content) { contents.get(chapterNo).add(content);
    }

    public HashMap<Integer, List<String>> getContents() {
        return contents;
    }

    public void addQuestion(Integer chapterNo, Question question) {
        questions.get(chapterNo).add(question);
    }

    public HashMap<Integer, List<Question>> getQuestions() {
        return questions;
    }

    public void addDefinition(Integer chapterNo, Definition definition) {
        definitions.get(chapterNo).add(definition);
    }

    public HashMap<Integer, List<Definition>> getDefinitions() {
        return definitions;
    }

}