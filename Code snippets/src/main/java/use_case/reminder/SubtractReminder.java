package main.java.use_case.reminder;

import main.java.data_access.DBDataAccessObject;
import main.java.entity.Course;
import main.java.entity.Reminder;
import main.java.entity.ReminderFactory;
import main.java.entity.Student;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SubtractReminder implements ReminderAlgorithm {
    @Override
    public void getReminders(DBDataAccessObject dataAccessObject, String userid) {
        List<String> userCourses = dataAccessObject.getUserCourses(userid);
        Map<String, Integer> courseDays = new HashMap<String, Integer>();
        LocalDate today = LocalDate.now();
        for(String courseid : userCourses) {
            if(courseid.equals("NONE")) continue;
            List<Student> students = dataAccessObject.getStudents(courseid);
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
            Reminder courseReminder = new ReminderFactory().create(courseid, new HashMap<Integer, String>());
            dataAccessObject.getCourseReminders().put(courseid, courseReminder);
            Course course = dataAccessObject.getCourse(courseid);
            Map<Integer, String> contents = course.getContents();
            System.out.println(courseid + courseDays.get(courseid));
            if(courseDays.get(courseid) > 1) {
                String content = course.getContents().get(courseDays.get(courseid) - 1);
                dataAccessObject.getCourseReminders().get(courseid).getReviewMaterials().put(courseDays.get(courseid) - 1, content);
            }
            if(courseDays.get(courseid) > 3) {
                String content = course.getContents().get(courseDays.get(courseid) - 3);
                dataAccessObject.getCourseReminders().get(courseid).getReviewMaterials().put(courseDays.get(courseid) - 3, content);
            }

            if(courseDays.get(courseid) > 6) {
                String content = course.getContents().get(courseDays.get(courseid) - 6);
                dataAccessObject.getCourseReminders().get(courseid).getReviewMaterials().put(courseDays.get(courseid) - 6, content);
            }
            if(courseDays.get(courseid) > 13) {
                String content = course.getContents().get(courseDays.get(courseid) -13);
                dataAccessObject.getCourseReminders().get(courseid).getReviewMaterials().put(courseDays.get(courseid) - 13, content);
            }
        }
        return;
    }
}
