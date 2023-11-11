package main.java.entity;

import main.java.entity.Notes;
import main.java.entity.Contents;
import java.util.ArrayList;
import java.util.HashMap;


//DONE FOR NOW
public class Course {
    private String id;
    private HashMap<Integer, String> contents;

    public Course(String id){
        this.id = id;
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