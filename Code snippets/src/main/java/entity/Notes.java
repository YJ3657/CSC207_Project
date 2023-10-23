package main.java.entity;


import java.util.ArrayList;
import java.util.Collection;

public class Notes{
    private Collection<String> lines; //what is the best data object to store note contents?
    private Reminder reminder;

    public Notes(){
        lines = new ArrayList<String>();
        reminder = ...
    }


}