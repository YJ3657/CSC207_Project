package main.java.entity;

import java.time.LocalDateTime;
import java.util.Map;

//done for now
public class Reminder {
    // One reminder for one course
    private final String courseid;
    private final Map<Integer, String> reviewMaterials;

    public Reminder(String courseid, Map<Integer, String> reviewMaterials){
        this.courseid = courseid;
        this.reviewMaterials = reviewMaterials;
    }

    public String getCourseid() {
        return courseid;
    }

    public Map<Integer, String> getReviewMaterials() {
        return reviewMaterials;
    }
}