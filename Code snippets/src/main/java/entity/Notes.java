package main.java.entity;


import java.util.ArrayList;
import java.util.Collection;

//done for now
public class Notes{

    private String title;
    private final String content; //what is the best data object to store note contents?
    private final Reminder reminder;


    public Notes(String title, String content){
        this.title = title;
        this.content = content;
        reminder = new Reminder(title, 1); //new Reminder()
    }
    public void setTitle(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    public String getContent() {
        return content;
    }

    public String getAllNotes(){
        return this.content;
    }



    public Reminder getReminder() {
        return reminder;
    }

//    public void setReminder(Reminder reminder) {
//        this.reminder = reminder;
//    }
}