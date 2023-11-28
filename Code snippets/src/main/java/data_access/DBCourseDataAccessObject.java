package main.java.data_access;

import main.java.entity.*;
import main.java.use_case.courses.AddCourseDataAccessInterface;
import main.java.use_case.quiz.QuizDataAccessInterface;
import main.java.use_case.add_Question_Definition.DefQuesDataAccessInterface;

import java.sql.*;
import java.util.*;


// Need to make updateContents, updateDefiniition, updateStudent, updateContents
public class DBCourseDataAccessObject implements AddCourseDataAccessInterface, DefQuesDataAccessInterface, QuizDataAccessInterface {
    private Connection conn = null;
    private final Map<String, Course> courses = new HashMap<>();
    private CourseFactory courseFactory;
    private QuestionFactory questionFactory;
    private DefinitionFactory definitionFactory;
    private StudentFactory studentFactory;

    public DBCourseDataAccessObject(CourseFactory courseFactory, QuestionFactory questionFactory, DefinitionFactory definitionFactory,
                                    StudentFactory studentFactory) {
        this.courseFactory = courseFactory;
        this.definitionFactory = definitionFactory;

        this.questionFactory = questionFactory;
        this.definitionFactory = definitionFactory;
        this.studentFactory = studentFactory;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            this.conn = DriverManager.getConnection("jdbc:mysql://csc207:3306",
                    "remoteUser",
                    "thisismysql*");

            ResultSet databases = conn.getMetaData().getCatalogs();

            while (databases.next()) {
                String databaseName = databases.getString(1).toLowerCase();
                if(databaseName.equals("user") || databaseName.equals("group") || databaseName.equals("course")) {
                    continue;
                }
                Course course = this.courseFactory.create(databaseName);

                String sqlOrder = "SELECT chapterno, question, answer FROM " + databaseName + ".questions";
                PreparedStatement statement = conn.prepareStatement(sqlOrder);
                ResultSet rs = statement.executeQuery();
                while(rs.next()) {
                    int chapterNo = rs.getInt("chapterno");
                    String question = rs.getString("question");
                    String answer = rs.getString("answer");
                    course.getQuestions().add(this.questionFactory.create(chapterNo, question, answer));
                }

                sqlOrder = "SELECT chapterno, word, definition FROM " + databaseName + ".definitions";
                statement = conn.prepareStatement(sqlOrder);
                rs = statement.executeQuery();
                while(rs.next()) {
                    int chapterNo = rs.getInt("chapterno");
                    String word = rs.getString("word");
                    String definition = rs.getString("definition");
                    course.getDefinitions().add(this.definitionFactory.create(chapterNo, word, definition));
                }

                sqlOrder = "SELECT studentid, time_enrolled FROM " + databaseName + ".students";
                statement = conn.prepareStatement(sqlOrder);
                rs = statement.executeQuery();
                while(rs.next()) {
                    String studentId = rs.getString("studentid");
                    String timeEnrolled = rs.getString("time_enrolled");
                    course.getStudents().add(this.studentFactory.create(studentId, timeEnrolled));
                }

                courses.put(databaseName, course);
                rs.close();
                statement.close();
            }
            databases.close();
        } catch (ClassNotFoundException e) {
            System.out.println("Class Not Found");
        } catch (SQLException e) {
            System.out.println("Check the database");
        } finally {
            if (conn != null) {
                try {
                    conn.close();
                    System.out.println("Connection closed");
                } catch (SQLException e) {
                }
            }
        }
    }
    // Saving the new course
    @Override
    public void save(Course course) {
        courses.put(course.getId(), course);
        this.save();
    }

    public void save() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection(
                    "jdbc:mysql://csc207:3306/",
                    "remoteUser",
                    "thisismysql*"
            );

            for(String courseId : this.courses.keySet()) {
                Course course = courses.get(courseId);
                Statement statement = conn.createStatement();
                statement.executeUpdate("CREATE DATABASE IF NOT EXISTS " + course.getId().toUpperCase());
                String sqlOrder = "USE " + course.getId().toUpperCase();
                PreparedStatement prestatement = conn.prepareStatement(sqlOrder);
                prestatement.executeQuery();
                statement.executeUpdate("CREATE TABLE IF NOT EXISTS questions (chapterno INT(3), qustion varchar(50), answer varchar(50))");
                statement.executeUpdate("CREATE TABLE IF NOT EXISTS definitions (chapterno INT(3), word varchar(50), definition varchar(50))");
                statement.executeUpdate("CREATE TABLE IF NOT EXISTS students (studentid varchar(50), time_enrolled varchar(50))");

                sqlOrder = "INSERT IGNORE INTO questions (chapterno, qustion, answer)" +
                        "VALUES (?, ?, ?)";

                for (Question question : course.getQuestions()) {
                    prestatement = conn.prepareStatement(sqlOrder);
                    prestatement.setInt(1, question.getChapterno());
                    prestatement.setString(2, question.getQuestion());
                    prestatement.setString(3, question.getAnswer());
                    prestatement.executeUpdate();
                    prestatement.close();
                }

                sqlOrder = "INSERT IGNORE INTO definitions (chapterno, word, definition)" +
                        "VALUES (?, ?, ?)";

                for (Definition definition : course.getDefinitions()) {
                    prestatement = conn.prepareStatement(sqlOrder);
                    prestatement.setInt(1, definition.getChapterno());
                    prestatement.setString(2, definition.getWord());
                    prestatement.setString(3, definition.getDefinition());
                    prestatement.executeUpdate();
                    prestatement.close();
                }

                sqlOrder = "INSERT IGNORE INTO students (studentid, time_enrolled)" +
                        "VALUES (?, ?)";

                for (Student student : course.getStudents()) {
                    prestatement = conn.prepareStatement(sqlOrder);
                    prestatement.setString(1, student.getStudentid());
                    prestatement.setString(2, student.getTimeEnrolled());
                    prestatement.executeUpdate();
                    prestatement.close();
                }
            }
        } catch (ClassNotFoundException e) {
            System.out.println("Class Not Found");
        } catch (SQLException e) {
            System.out.println("Check the database");
        } finally {
            if(conn != null) {
                try {
                    conn.close();
                    System.out.println("Connection closed");
                } catch (SQLException e) {}
            }
        }
    }

    @Override
    public boolean existsByID(String courseId) {
        return courses.containsKey(courseId);
    }

    @Override
    public Course getCourse(String courseId) {
        return courses.get(courseId);
    }

    public List<Definition> getDefinitions(String courseId) {
        return courses.get(courseId).getDefinitions();
    }

//    public List<Question> getQuestions(String courseId) { //TODO: Jerry, talk to YJ about this.
//        return courses.get(courseId).getQuestions();
//    }

    public List<Student> getStudents(String courseId) {
        return courses.get(courseId).getStudents();
    }

    public boolean addStudentToCourse(Student student, String courseId) {
        Course course = courses.get(courseId);
        // The case when the student already exists
        if(course.getStudents().stream().anyMatch(person -> student.getStudentid().equals(person.getStudentid()))) {
            return false;
        }
        else {
            course.getStudents().add(student);
            return true;
        }
    }

    @Override
    public Set<String> getDefinitionTerms(int chapterNumber, String courseId) {
        List<Definition> currentDefinitions = courses.get(courseId).getDefinitions();
        Set<String> terms = new HashSet<>();
        for (Definition def: currentDefinitions){
            terms.add(def.getWord());
        }
        return terms;
    }

    @Override
    public void saveDefinition(String term, String definition, int chapterNumber, String courseId) {
        Course currentCourse = courses.get(courseId);
        List<String> terms = currentCourse.getDefinitionTerms();
        if (terms.contains(term)){
            for (int i = 0; i < terms.size(); i++){
                if (terms.get(i).equals(term)){
                    currentCourse.getDefinitions().get(i).setDefinition(definition);
                    break;
                }
            }
        } else {
            currentCourse.setDefinition(definitionFactory.create(chapterNumber, term, definition));
        }
        this.save();
    }

    /**
     * getDefinition for term in given class. Up to caller to ensure term actually in definitions
     * @param term
     * @param courseId
     * @return
     */
    @Override
    public String getDefinitionOnly(String term, String courseId) {
        List<Definition> courseDefinitions = courses.get(courseId).getDefinitions();
        for (Definition def: courseDefinitions){
            if (def.getWord().equals(term)){
                return def.getDefinition();
            }

        }
        throw new NoSuchElementException();
    }

    @Override
    public Set<String> getQuestionQuestions(int chapterNumber, String courseId) {
        List<Question> currentQuestions = courses.get(courseId).getQuestions();
        Set<String> questionsOnly = new HashSet<>();
        for (Question question: currentQuestions){
            questionsOnly.add(question.getQuestion());
        }
        return questionsOnly;
    }

    @Override
    public void saveQuestion(String question, String answer, int chapterNumber, String courseId) {
        Course currentCourse = courses.get(courseId);
        List<String> questions = currentCourse.getQuestionQuestions();
        if (questions.contains(question)){
            for (int i = 0; i < questions.size(); i++){
                if (questions.get(i).equals(question)){
                    currentCourse.getQuestions().get(i).setAnswer(answer);
                    break;
                }
            }
        } else {
            currentCourse.setQuestion(questionFactory.create(chapterNumber, question, answer));
        }
        this.save();
    }

    @Override
    public String getAnswerOnly(String question, String courseId) {
        List<Question> courseQuestions = courses.get(courseId).getQuestions();
        for (Question ques: courseQuestions){
            if (ques.getQuestion().equals(question)){
                return ques.getAnswer();
            }

        }
        throw new NoSuchElementException();
    }

    @Override
    public ArrayList<String> getQuizQuestions(String courseId) {
        List<Definition> definitions = courses.get(courseId).getDefinitions();
        List<Question> questions = courses.get(courseId).getQuestions();
        ArrayList<String> quizQuestions = new ArrayList<>();
        int i = 1;
        for (Definition definition: definitions) {
            quizQuestions.add(String.format("%1d) The definition of %2s is:", i, definition.getWord()));
            i++;
        }

        for (Question ques: questions){
            quizQuestions.add(String.format("%1d) %2s?", i, ques.getQuestion()));
            i++;
        }

        return quizQuestions;
    }

    @Override
    public ArrayList<String> getAnswers(String courseId) {
        List<Definition> definitions = courses.get(courseId).getDefinitions();
        List<Question> questions = courses.get(courseId).getQuestions();
        ArrayList<String> quizAnswers = new ArrayList<>();
        int i = 1;
        for (Definition definition: definitions) {
            quizAnswers.add(String.format(definition.getDefinition()));
            i++;
        }

        for (Question ques: questions){
            quizAnswers.add(String.format(ques.getAnswer()));
            i++;
        }

        return quizAnswers;

    }
}
