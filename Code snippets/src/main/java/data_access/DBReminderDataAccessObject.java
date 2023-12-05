package main.java.data_access;

import main.java.entity.Course;
import main.java.entity.Reminder;
import main.java.entity.ReminderFactory;
import main.java.entity.Student;
import main.java.use_case.reminder.ReminderDataAccessInterface;
import main.java.data_access.*;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.*;

public class DBReminderDataAccessObject implements ReminderDataAccessInterface {
    private final DBDataAccessObject dbUserDataAccessObject;
    private final DBDataAccessObject dbCourseDataAccessObject;
    private final ReminderFactory reminderFactory;
    private final Map<String, Reminder> courseReminders;
    public DBReminderDataAccessObject(DBDataAccessObject dbUserDataAccessObject, DBDataAccessObject dbCourseDataAccessObject,
                                      ReminderFactory reminderFactory) {
        this.dbUserDataAccessObject = dbUserDataAccessObject;
        this.dbCourseDataAccessObject = dbCourseDataAccessObject;
        this.reminderFactory = reminderFactory;
        this.courseReminders = new HashMap<String, Reminder>();
    }

    @Override
    public Map<String, Reminder> getUserReviewChapters(String userid) {
        List<String> userCourses = this.dbUserDataAccessObject.getUserCourses(userid);
        Map<String, Integer> courseDays = new HashMap<String, Integer>();
        LocalDate today = LocalDate.now();
        for(String courseid : userCourses) {
            List<Student> students = this.dbCourseDataAccessObject.getStudents(courseid);
            for(Student student : students) {
                if(!student.getStudentid().equals(userid)) {
                    continue;
                }
                try {
                    LocalDate date = LocalDate.parse(student.getTimeEnrolled());
                    courseDays.put(courseid, today.getDayOfYear() - date.getDayOfYear() + 1);
                    break;
                }
                catch(Exception e) {
                    System.out.println("Please provide proper date in string");
                }
            }
        }
        for(String courseid : courseDays.keySet()) {
            Reminder courseReminder = this.reminderFactory.create(courseid, new HashMap<Integer, String>());
            this.courseReminders.put(courseid, courseReminder);
            Course course = this.dbCourseDataAccessObject.getCourse(courseid);
            Map<Integer, String> contents = course.getContents();
            System.out.println(courseid + courseDays.get(courseid));
            if(courseDays.get(courseid) > 1) {
                String content = course.getContents().get(courseDays.get(courseid) - 1);
                this.courseReminders.get(courseid).getReviewMaterials().put(courseDays.get(courseid) - 1, content);
            }
            if(courseDays.get(courseid) > 3) {
                String content = course.getContents().get(courseDays.get(courseid) - 3);
                this.courseReminders.get(courseid).getReviewMaterials().put(courseDays.get(courseid) - 3, content);
            }

            if(courseDays.get(courseid) > 6) {
                String content = course.getContents().get(courseDays.get(courseid) - 6);
                this.courseReminders.get(courseid).getReviewMaterials().put(courseDays.get(courseid) - 6, content);
            }
            if(courseDays.get(courseid) > 13) {
                String content = course.getContents().get(courseDays.get(courseid) -13);
                this.courseReminders.get(courseid).getReviewMaterials().put(courseDays.get(courseid) - 13, content);
            }
        }
        return this.courseReminders;
    }
}
