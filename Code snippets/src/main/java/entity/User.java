package main.java.entity;

import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;
import java.util.Map;

import main.java.entity.Course;

//DONE FOR NOW
public class User {
    private String id;
    private String password;

    // changed to generic list for clean architecture
    private List<String> groupId;
    private List<String> courseId;
    private Map<String, List<Notes>> notes;

    public User(){
        this.id = "";
        this.password = "";
        this.groupId = new ArrayList<>();
        this.courseId = new ArrayList<>();
        this.notes = new HashMap<>();
    }

    public User(String id, String password){
        this();
        this.id = id;
        this.password = password;
        this.groupId = new ArrayList<>();
        this.courseId = new ArrayList<>();
        this.notes = new HashMap<>();
    }

    //getters and setters
    public String getId() {
        return id;
    }

    public String getPassword() {
        return password;
    }

    public List<String> getGroupId() {
        return groupId;
    }

    public List<String> getCourseId() {
        return courseId;
    }

    public void setId(String id){
        this.id = id;
    }

    public void setPassword(String password){
        this.password = password;
    }

    public Map<String, List<Notes>> getNotes(){return notes;}

    public void setNotes(Notes note, String courseId){
        if (notes.isEmpty() || notes.get(courseId) == null){
            List<Notes> newlist = new ArrayList<>();
            newlist.add(note);
            notes.put(courseId, newlist);
        } else{
            notes.get(courseId).add(note);
        }
    }
    public void setNotes(String courseId){
        notes.put(courseId, new ArrayList<>());
    }

    public void setNotes(String courseId, List<Notes> notes){
        this.notes.put(courseId, notes);
    }

    // special setters
    public void addCourse(String newcourseId){
        Boolean found = false;
        if(this.courseId.size() < 8) {
            this.courseId.add(newcourseId);
            return;
        }
        for(int i = 0; i < this.courseId.size(); i++) {
            if(courseId.get(i).equals("NONE")) {
                courseId.set(i,newcourseId);
                found = true;
                break;
            }
        }
        if(!found) System.out.println("No space");
    }

    public void addGroupId(String groupIds){

    }

    public void copy(User user){
        this.id = user.getId();
        this.password = user.getPassword();
        this.groupId = user.getGroupId();
        this.courseId = user.getCourseId();
        this.notes = user.getNotes();
    }

}