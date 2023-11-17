package main.java.entity;

import java.util.HashMap;


//DONE FOR NOW
public class Course {
    private String id;
    private HashMap<Integer, String> contents;

    public Course(String id){
        this.id = id;
        contents = new HashMap<Integer, String>();
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public HashMap<Integer, String> getContents() {
        return contents;
    }
}