package main.java.data_access;

import main.java.entity.*;
import main.java.use_case.courses.AddCourseDataAccessInterface;
import main.java.use_case.quiz.QuizDataAccessInterface;

import java.util.ArrayList;
import java.util.HashMap;

import java.util.Map;
import java.io.*;

public class FileCourseDataAccessObject implements AddCourseDataAccessInterface {

    private final Map<String, Course> courses = new HashMap<>();
    private CourseFactory courseFactory;

    private QuestionFactory questionFactory;
    private DefinitionFactory definitionFactory;
    private StudentFactory studentFactory;

    public FileCourseDataAccessObject(CourseFactory courseFactory){
        this.courseFactory = courseFactory;
        this.questionFactory = questionFactory;
        this.definitionFactory = definitionFactory;
        this.studentFactory = studentFactory;

        try{
            File f = new File("course_data.txt");
            BufferedReader reader = new BufferedReader(new FileReader(f));
            String row;

            while((row = reader.readLine()) != null){
                Course course = this.courseFactory.create(row);
                courses.put(row, course);
            }

        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public Course getCourse(String courseId) {
        return courses.get(courseId);
    }

    @Override
    public void save(Course course) {

    }

//    @Override
    public void saveCourse(Course course) {
        courses.put(course.getId(), course);
//        save();
    }

    @Override
    public boolean existsByID(String courseId) {
        return courses.containsKey(courseId);
    }

//    @Override
//    public ArrayList<String> getQuestions() {
//        return null;
//    }
//
//    @Override
//    public ArrayList<String> getAnswers() {
//        return null;
//    }
//
//    @Override
//    public void setQuestionAnswers() {
//
//    }
//
//    public void save() {
//        try {
//            writer = new BufferedWriter(new FileWriter(csvFile));
//            writer.write(String.join(",", headers.keySet()));
//            writer.newLine();
//            BufferedWriter bw = new BufferedWriter(new FileWriter("course_data.txt"));
//            for (Course course: courses.values()){
//                String line = String.format("%s,%s,%s",
//                        course, course.getQuestions(), course.getDefinitions(), course.getStudents());
//                bw.write(course.getId());
//                bw.newLine();
//            }
//            bw.close();
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }
//    }
//
//    @Override
//    public ArrayList<String> getQuestions() {
//        return null;
//    }
//
//    @Override
//    public ArrayList<String> getAnswers() {
//        return null;
//    }
//
//    @Override
//    public void setQuestionAnswers() {
//
//    }
}
