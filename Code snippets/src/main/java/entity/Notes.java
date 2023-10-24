package main.java.entity;


import java.util.ArrayList;
import java.util.Collection;

//done for now
public class Notes{

    private String title;
    private Collection<String> lines; //what is the best data object to store note contents?
    private Reminder reminder;


    public Notes(String title){
        this.title = title;
        lines = new ArrayList<String>();
        reminder = new Reminder(title, 1); //new Reminder()
    }
    public void setTitle(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    public Collection<String> getAllNotes(){
        return this.lines;
    }




}