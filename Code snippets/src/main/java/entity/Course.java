package main.java.entity;

import main.java.entity.Notes;
import main.java.entity.Contents;
import java.util.ArrayList;
import java.util.HashMap;


//DONE FOR NOW
public class Course {
    private String id;
    private HashMap<Integer, String> contents;

    private ArrayList<User> students;

    public Course(String id){
        this.id = id;
        contents = new HashMap<Integer, String>();
        students = new ArrayList<>();
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public void addStudent(User user) {
        students.add(user);
    }

    public ArrayList<User> getStudents() {
        return students;
    }

    public HashMap<Integer, String> getContents() {
        return contents;
    }
}