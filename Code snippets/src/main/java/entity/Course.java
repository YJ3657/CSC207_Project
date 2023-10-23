package main.java.entity;

import main.java.entity.Notes;
import main.java.entity.Contents;
import java.util.ArrayList;
import java.util.HashMap;


//DONE FOR NOW
public class Course{
    private String id;
    private ArrayList<Notes> notes; //is this the best collection to store notes?
    private HashMap<Integer, Contents> contents;

    public Course(String id){
        this.id = id;
        notes = new ArrayList<>();
        contents = new HashMap<>();
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public ArrayList<Notes> getNotes() {
        return notes;
    }

    public HashMap<Integer, Contents> getContents() {
        return contents;
    }
}