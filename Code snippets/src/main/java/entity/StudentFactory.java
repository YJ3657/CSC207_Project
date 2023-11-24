package main.java.entity;

public class StudentFactory {
    public Student create(String studentid, String time_enrolled) {
        return new Student(studentid, time_enrolled);
    }
}
