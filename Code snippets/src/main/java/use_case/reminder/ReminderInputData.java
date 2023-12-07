package main.java.use_case.reminder;

public class ReminderInputData {

    private final String userid;
    public ReminderInputData(String userid) {
        this.userid = userid;
    }

    public String getUserid() {
        return this.userid;
    }
}
