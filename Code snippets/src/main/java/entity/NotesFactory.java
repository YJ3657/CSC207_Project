package main.java.entity;

public class NotesFactory {
    public Notes create(String title, String content){return new Notes(title,content);}
}
