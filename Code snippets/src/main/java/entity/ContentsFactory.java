package main.java.entity;
import main.java.entity.Contents;
public interface ContentsFactory {
    Contents create(String courseId);
}
