package main.java.entity;

import java.util.List;
import java.util.ArrayList;
import main.java.entity.Course;

//DONE FOR NOW
public class User{

    private String id;
    private String password;

    // changed to generic list for clean architecture
    private List<String> groupIds;
    private List<Course> courses;

    public User(String id, String password){
        this.id = id;
        this.password = password;
        groupIds = new ArrayList<>();
        courses = new ArrayList<>();
    }

    //getters and setters
    public String getId() {
        return id;
    }

    public String getPassword() {
        return password;
    }

    public List<String> getGroupIds() {
        return groupIds;
    }

    public List<Course> getCourses() {
        return courses;
    }

    public void setId(String id){
        this.id = id;
    }

    public void setPassword(String password){
        this.password = password;
    }

    // special setters
    public void addCourse(Course course){
        courses.add(course);
    }

    public void addGroupIds(String groupIds){


    }



}