//package main.java.data_access;
//
//import main.java.entity.*;
//import main.java.use_case.courses.AddCourseDataAccessInterface;
//import main.java.use_case.quiz.QuizDataAccessInterface;
//
//import java.util.*;
//
//import java.io.*;
//
//public class FileCourseDataAccessObject implements AddCourseDataAccessInterface {
//
//    private final Map<String, Course> courses = new HashMap<>();
//    private CourseFactory courseFactory;
//
//    private QuestionFactory questionFactory;
//    private DefinitionFactory definitionFactory;
//    private StudentFactory studentFactory;
//
//    private Map<String, Integer> headers = new LinkedHashMap<>();
//    private final File csvFile;
//
//    public FileCourseDataAccessObject(CourseFactory courseFactory){
//        this.courseFactory = courseFactory;
//        this.questionFactory = questionFactory;
//        this.definitionFactory = definitionFactory;
//        this.studentFactory = studentFactory;
//        csvFile = new File("course_data.txt");
//
//        headers.put("course", 0);
//        headers.put("chapterno", 1);
//        headers.put("question", 2);
//        headers.put("answer", 3);
//
//        if (csvFile.length() == 0) {
//            save();
//        }
//
//        try{
//            BufferedReader reader = new BufferedReader(new FileReader(csvFile));
//            String row;
//
//            while((row = reader.readLine()) != null){
//                List<String> entires = Arrays.asList(row.split(","));
//                Course course = this.courseFactory.create(entires.get(0));
//                courses.put(row, course);
//            }
//
//        } catch (FileNotFoundException e) {
//            throw new RuntimeException(e);
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }
//    }
//
//    private void save() {
//        BufferedWriter writer;
//        try {
//            writer = new BufferedWriter(new FileWriter(csvFile));
//            writer.write(String.join(",", headers.keySet()));
//            writer.newLine();
//
//            for (Course course : courses.values()) {
//                String line = String.format("%s,%s,%s",
//                        course, course.getQuestions(), course.getDefinitions(), course.getStudents());
//                writer.write(line);
//                writer.newLine();
//            }
//
//            writer.close();
//
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }
//    }
//
//    public Course getCourse(String courseId) {
//        return courses.get(courseId);
//    }
//
//    @Override
//    public void save(Course course) {
//
//    }
//
////    @Override
//    public void saveCourse(Course course) {
//        courses.put(course.getId(), course);
////        save();
//    }
//
//    @Override
//    public boolean existsByID(String courseId) {
//        return courses.containsKey(courseId);
//    }
//
////    @Override
////    public ArrayList<String> getQuestions() {
////        return null;
////    }
////
////    @Override
////    public ArrayList<String> getAnswers() {
////        return null;
////    }
////
////    @Override
////    public void setQuestionAnswers() {
////
////    }
////
////    public void save() {
////        try {
////            writer = new BufferedWriter(new FileWriter(csvFile));
////            writer.write(String.join(",", headers.keySet()));
////            writer.newLine();
////            BufferedWriter bw = new BufferedWriter(new FileWriter("course_data.txt"));
////            for (Course course: courses.values()){
////                String line = String.format("%s,%s,%s",
////                        course, course.getQuestions(), course.getDefinitions(), course.getStudents());
////                bw.write(course.getId());
////                bw.newLine();
////            }
////            bw.close();
////        } catch (IOException e) {
////            throw new RuntimeException(e);
////        }
////    }
////
////    @Override
////    public ArrayList<String> getQuestions() {
////        return null;
////    }
////
////    @Override
////    public ArrayList<String> getAnswers() {
////        return null;
////    }
////
////    @Override
////    public void setQuestionAnswers() {
////
////    }
//}
