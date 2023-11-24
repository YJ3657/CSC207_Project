package main.java.entity;

public class Student {
    private String studentid;
    private String time_enrolled;

    public Student(String studentid, String time_enrolled) {
        this.studentid = studentid;
        this.time_enrolled = time_enrolled;
    }

    public void setStudentid(String studentid) {
        this.studentid = studentid;
    }

    public String getStudentid() {
        return this.studentid;
    }

    public void setTimeEnrolled(String time_enrolled) {
        this.time_enrolled = time_enrolled;
    }

    public String getTimeEnrolled() {
        return this.time_enrolled;
    }

}
