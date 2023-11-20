package main.java.entity;

public class NotesFactory {
    public Notes create(String title, String content, int chapterno){return new Notes(title, content, chapterno);}
}
