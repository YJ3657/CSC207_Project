package main.java.entity;

import java.util.List;
import java.util.ArrayList;
import java.util.Collection;


//DONE FOR NOW
public class Group{
    private String groupId;
    private int week;
    private String courseId;

    private Collection<User> members;

    public Group(String groupId, int week, String courseId){
        this.groupId = groupId;
        this.week = week;
        this.courseId = courseId;
        members = new ArrayList<>(); //took out hashmap as per TA suggestion. But maybe set or different interface better?
    }

    //getters and setters


    public String getGroupId() {
        return groupId;
    }

    public int getWeek() {
        return week;
    }

    public String getCourseId() {
        return courseId;
    }

    public Collection<User> getMembers() {
        return members;
    }

    //omitted setters since doesn't make sense a group/course changes name
    //special setters

    /**
     * Add member to group and return boolean if member was added or not
     * @param user - member to add
     * @return if member was added or no
     */
    public Boolean addMember(User user){
        if (!members.contains(user)){
            members.add(user);
            return true;
        }
        return false;
    }

    public Boolean removeMember(String username){
        for (User user: members){
            if (user.getId().equals(username)){
                members.remove(user);
                return true;
            }
        }
        return false;
    }



}