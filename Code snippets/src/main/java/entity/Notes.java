package main.java.entity;


import java.util.ArrayList;
import java.util.Collection;

//done for now
public class Notes{

    private final String title;
    private String content; //what is the best data object to store note contents?
    private final Reminder reminder;


    public Notes(String title, String content){
        this.title = title;
        this.content = content;
        reminder = new Reminder(title, 1); //new Reminder()
    }
    public void setContent(String content) {
        this.content = content;
    }

    public String getTitle() {
        return title;
    }

    public String getContent() {
        return content;
    }
}