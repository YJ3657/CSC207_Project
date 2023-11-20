package main.java.entity;

import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;
import java.util.Map;

import main.java.entity.Course;

//DONE FOR NOW
public class User{

    private String id;
    private String password;

    // changed to generic list for clean architecture
    private final List<String> groupId;
    private final List<String> courseId;
    private final Map<String, List<Notes>> notes;

    public User(String id, String password){
        this.id = id;
        this.password = password;
        groupId = new ArrayList<>();
        courseId = new ArrayList<>();
        notes = new HashMap<>(){};
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

    public Map<String, List<Notes>> getNotes(){return notes;}

    public void setNotes(Notes note, String courseId){
        if (notes.isEmpty() || notes.get(courseId) == null){
            List<Notes> newlist = new ArrayList<>();
            newlist.add(note);
            notes.put(courseId, newlist);
        } else{
            notes.get(courseId).add(note);
        }
    }

    // special setters
    public void addCourse(String newcourseId){
        courseId.add(newcourseId);
    }

    public void addGroupId(String groupIds){

    }

}