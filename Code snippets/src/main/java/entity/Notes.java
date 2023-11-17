package main.java.entity;


import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

//done for now
public class Notes{
    private String title;
    private String text; //idea is this stores all text in notes
    private Map<String, String> definitions; //this stores notes that are marked as definitions
    private Reminder reminder;
    public Notes(String title){
        this.title = title;
        text = "";
        definitions = new HashMap<>();
        reminder = new Reminder(title, 1); //new Reminder()
    }
    public void setTitle(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Map<String, String> getDefinitions() {
        return definitions;
    }

    public void setDefinitions(Map<String, String> definitions) {
        this.definitions = definitions;
    }

    public Reminder getReminder() {
        return reminder;
    }

    public void setReminder(Reminder reminder) {
        this.reminder = reminder;
    }
}