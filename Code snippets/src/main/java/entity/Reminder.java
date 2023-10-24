package main.java.entity;

import java.time.LocalDateTime;

//done for now
public class Reminder {

    private LocalDateTime deadline; // documentation I used: https://www.w3schools.com/java/java_date.asp
    private String title;
    private int frequency; //I am assuming frequency is a number on a scale(exp. 1-10), where 1 is remind very infrequently and 10 is remind very frequently
    private boolean enabled;

    public Reminder(String title, int frequency){
        this.deadline = null;
        this.title = title;
        this.frequency = frequency;
        this.enabled = false;
    }

    public void setDeadline(LocalDateTime deadline) {
        this.deadline = deadline;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setFrequency(int frequency) {
        this.frequency = frequency;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public LocalDateTime getDeadline() {
        return deadline;
    }

    public String getTitle() {
        return title;
    }

    public int getFrequency() {
        return frequency;
    }

    public boolean isEnabled() {
        return enabled;
    }

    //special methods

    /**
     * Notify user about what to study
     */
    public void remind(){
        //DO SOMETHING TO REMIND USER
        setNextReminder();
    }

    /**
     * Set up next reminder to study
     */
    private void setNextReminder(){


    }


}