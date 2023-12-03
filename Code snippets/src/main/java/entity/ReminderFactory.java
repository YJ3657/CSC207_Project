package main.java.entity;

import java.util.Map;

public class ReminderFactory {
    public Reminder create(String courseid, Map<Integer, String> reviewMaterials) {
        return new Reminder(courseid, reviewMaterials);
    }
}
