package main.java.entity;


import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

//done for now
public class Notes{
    private String userid;
    private String title;
    private String contents;
    private final int chapterno;
    private String courseid;
    // private final Reminder reminder;


    public Notes(String userid, String courseid, String contents, int chapterno, String title){
        this.userid = userid;
        this.title = title;
        this.contents = contents;
        this.chapterno = chapterno;
        this.courseid = courseid;
        // reminder = new Reminder(title, 1); //new Reminder()
    }
    public String getUserId() {
        return this.userid;}
    public void setUserId(String userId) {
        this.userid = userId;
    }

    public String getTitle() {
        return title;
    }
    public void setTitle() { this.title = title;}

    public String getContents() {
        return contents;
    }
    public void setContents(String contents) {
        this.contents = contents;
    }

    public int getChapterno() {return this.chapterno;}

    public String getCourseId() {
        return this.courseid;}

    public void setCourseId(String courseid) {
        this.courseid = courseid;
    }
}