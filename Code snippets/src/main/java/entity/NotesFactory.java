package main.java.entity;

import java.util.List;

public class NotesFactory {
    public Notes create(String userid, String courseid, String contents, int chapterno, String title){
        return new Notes(userid, courseid, contents, chapterno, title);}
}
