package main.java.entity;

import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;
import main.java.entity.Course;

//DONE FOR NOW
public class User{

    private String id;
    private String password;

    // changed to generic list for clean architecture
    private List<String> groupId;
    private List<String> courseId;
    private HashMap<String, List<Notes>> notes;

    public User(String id, String password){
        this.id = id;
        this.password = password;
        groupId = new ArrayList<>();
        courseId = new ArrayList<>();
    }

    //getters and setters
    public String getId() {
        return id;
    }

    public String getPassword() {
        return password;
    }

    public List<String> getGroupId() {
        return groupId;
    }

    public List<String> getCourseId() {
        return courseId;
    }

    public void setId(String id){
        this.id = id;
    }

    public void setPassword(String password){
        this.password = password;
    }

    public HashMap<String, List<Notes>> getNotes(){return notes;}

    public void setNotes(Notes notes, String courseId){this.notes.get(courseId).add(notes);}

    // special setters
    public void addCourse(String newcourseId){
        courseId.add(newcourseId);
    }

    public void addGroupId(String groupIds){


    }

}