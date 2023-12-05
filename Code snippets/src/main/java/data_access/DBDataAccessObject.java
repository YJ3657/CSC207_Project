package main.java.data_access;

import main.java.app.Constants;
import main.java.entity.*;
import main.java.use_case.add_Question_Definition.DefQuesDataAccessInterface;
import main.java.use_case.clear_users.ClearUserDataAccessInterface;
import main.java.use_case.courses.AddCourseDataAccessInterface;
import main.java.use_case.find_user_courses.FindUserCourseDataAccessInterface;
import main.java.use_case.login.LoginUserDataAccessInterface;
import main.java.use_case.notes.NotesDataAccessInterface;
import main.java.use_case.quiz.QuizDataAccessInterface;
import main.java.use_case.reminder.ReminderDataAccessInterface;
import main.java.use_case.signup.SignupUserDataAccessInterface;
import main.java.use_case.update_users.UpdateUserDataAccessInterface;

import java.sql.*;
import java.time.LocalDate;
import java.util.*;
import java.util.regex.Pattern;

public class DBDataAccessObject implements NotesDataAccessInterface, AddCourseDataAccessInterface, DefQuesDataAccessInterface, QuizDataAccessInterface,
        SignupUserDataAccessInterface, LoginUserDataAccessInterface, ClearUserDataAccessInterface,
        UpdateUserDataAccessInterface, FindUserCourseDataAccessInterface, ReminderDataAccessInterface {
    private Connection conn = null;
    private final Map<String, User> accounts = new HashMap<>();
    private final Map<String, Course> courses = new HashMap<>();
    private UserFactory userFactory;
    private NotesFactory notesFactory;
    private CourseFactory courseFactory;
    private StudentFactory studentFactory;
    private QuestionFactory questionFactory;
    private DefinitionFactory definitionFactory;
    private final ReminderFactory reminderFactory;
    private Map<String, Reminder> courseReminders;

    public DBDataAccessObject(UserFactory userFactory, NotesFactory notesFactory, CourseFactory courseFactory,
                                   StudentFactory studentFactory, QuestionFactory questionFactory, DefinitionFactory definitionFactory, ReminderFactory reminderFactory) {
        this.userFactory = userFactory;
        this.notesFactory = notesFactory;
        this.courseFactory = courseFactory;
        this.questionFactory = questionFactory;
        this.definitionFactory = definitionFactory;
        this.studentFactory = studentFactory;
        this.reminderFactory = reminderFactory;

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");

            conn = DriverManager.getConnection(
                    "jdbc:mysql://100.66.206.195:3306/user",
                    "remoteUser",
                    "thisismysql*"
            );
            System.out.println("no error till here");
            String sqlOrder = "SELECT userid, password, groupid1, groupid2, groupid3, groupid4, groupid5, groupid6," +
                    " groupid7, groupid8, courseid1, courseid2, courseid3, courseid4, courseid5, courseid6, courseid7, courseid8 FROM users";

            PreparedStatement statement = conn.prepareStatement(sqlOrder);

            ResultSet rs = statement.executeQuery();

            while(rs.next()) {
                String userId = rs.getString("userid");
                String userPw = rs.getString("password");
                User user = this.userFactory.create(userId, userPw);
                for(int i = 1; i <= 8; i++) {
                    user.getGroupId().add(rs.getString("groupid" + i));
                }
                for(int i = 1; i <= 8; i++) {
                    user.getCourseId().add(rs.getString("courseid" + i));
                }
                accounts.put(userId, user);
            }
            rs.close();
            statement.close();

        } catch (ClassNotFoundException e) {
            System.out.println("Class Not Found");
        } catch (SQLException e) {
            System.out.println("Error1");
        } finally {
            if(conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {}
            }
        }

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");

            conn = DriverManager.getConnection(
                    "jdbc:mysql://100.66.206.195:3306/user",
                    "remoteUser",
                    "thisismysql*"
            );

            String sqlOrder = "SELECT userid, courseid, contents, chapterno, title FROM notes";

            PreparedStatement statement = conn.prepareStatement(sqlOrder);

            ResultSet rs = statement.executeQuery();

            while(rs.next()) {
                String userId = rs.getString("userid");
                String courseId = rs.getString("courseid");
                String contents = rs.getString("contents"); // TODO: Yeong jae check this over, I'm assuming this is how it would work if change contents to string
                int chapterNo = rs.getInt("chapterno");
                String title = rs.getString("title");
                Notes note = this.notesFactory.create(userId, courseId, contents, chapterNo, title);
                Map<String, List<Notes>> userNotes = accounts.get(userId).getNotes();
                if (userNotes.containsKey(courseId)) {
                    userNotes.get(courseId).add(note);
                }
                else {
                    List<Notes> newList = new ArrayList<>();
                    newList.add(note);
                    userNotes.put(courseId, newList);
                }
            }
            rs.close();
            statement.close();
        } catch (ClassNotFoundException e) {
            System.out.println("Class Not Found");
        } catch (SQLException e) {
            System.out.println("Error 2");
        } finally {
            if(conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {}
            }
        }

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");

            conn = DriverManager.getConnection(
                    "jdbc:mysql://100.66.206.195:3306",
                    "remoteUser",
                    "thisismysql*"
            );

            ResultSet databases = conn.getMetaData().getCatalogs();
            while (databases.next()) {
                String databaseName = databases.getString(1);
                if(!Pattern.matches("[A-Z][A-Z][A-Z]\\d\\d\\d", databaseName)) {
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
                    course.setQuestion(this.questionFactory.create(chapterNo, question, answer));
                }

                sqlOrder = "SELECT chapterno, word, definition FROM " + databaseName + ".definitions";
                statement = conn.prepareStatement(sqlOrder);
                rs = statement.executeQuery();
                while(rs.next()) {
                    int chapterNo = rs.getInt("chapterno");
                    String word = rs.getString("word");
                    String definition = rs.getString("definition");
                    course.setDefinition(this.definitionFactory.create(chapterNo, word, definition));
                }

                sqlOrder = "SELECT chapterno, content FROM " + databaseName + ".contents";
                statement = conn.prepareStatement(sqlOrder);
                rs = statement.executeQuery();
                while(rs.next()) {
                    int chapterNo = rs.getInt("chapterno");
                    String content = rs.getString("content");
                    Map<Integer, String> contents = course.getContents();
                    if(contents.containsKey(chapterNo)) {
                        continue;
                    }
                    contents.put(chapterNo, content);
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
            System.out.println("Error 3");
        } finally {
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                }
            }
        }
    }

    @Override
    public void save(User user) {
        accounts.put(user.getId(), user);
        this.save();
    }

    public void save(User user, Course course) {
        accounts.put(user.getId(), user);
        courses.put(course.getId(), course);
        this.save();
    }

    @Override
    public void save(Course course) {
        courses.put(course.getId(), course);
        this.save();
    }

    public User get(String username) {
        return accounts.get(username);
    }

    public void save() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");

            String sqlOrder = "INSERT IGNORE INTO users (userid, password, groupid1, groupid2, groupid3, groupid4," +
                    " groupid5, groupid6, groupid7, groupid8, courseid1, courseid2, courseid3, courseid4, courseid5, " +
                    "courseid6, courseid7, courseid8) " +
                    "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?);";

            for(User user : accounts.values()) {
                conn = DriverManager.getConnection(
                        "jdbc:mysql://100.66.206.195:3306/user",
                        "remoteUser",
                        "thisismysql*"
                );
                PreparedStatement statement = conn.prepareStatement(sqlOrder);

                statement.setString(1, user.getId());
                statement.setString(2, user.getPassword());
                for(int i = 3; i <= 10; i++) {
                    if((i - 2) > user.getGroupId().size()) {
                        statement.setString(i, "NONE");
                        continue;
                    }
                    statement.setString(i, user.getGroupId().get(i - 3));
                }
                for(int i = 11; i <= 18; i++) {
                    if((i - 10) > user.getCourseId().size()) {

                        statement.setString(i, "NONE");
                        continue;
                    }
                    statement.setString(i, user.getCourseId().get(i - 11));
                }
                statement.executeUpdate();
                this.update(user);
                for(String courseId : user.getNotes().keySet()) {
                    String newsqlOrder = "INSERT IGNORE INTO notes (userid, courseid, contents, chapterno, title) " +
                            "VALUES (?, ?, ?, ?, ?);";
                    for(Notes notes : user.getNotes().get(courseId)) {
                        conn = DriverManager.getConnection(
                                "jdbc:mysql://100.66.206.195:3306/user",
                                "remoteUser",
                                "thisismysql*"
                        );
                        PreparedStatement newStatement = conn.prepareStatement(newsqlOrder);
                        newStatement.setString(1, user.getId());
                        newStatement.setString(2, courseId);
                        newStatement.setString(3, notes.getContents());
                        newStatement.setInt(4, notes.getChapterno());
                        newStatement.setString(5, notes.getTitle());
                        newStatement.executeUpdate();
                        newStatement.close();
                    }
                }

                statement.close();
            }
        } catch (ClassNotFoundException e) {
            System.out.println("Class Not Found");
        } catch (SQLException e) {
            System.out.println("Error 4");
        } finally {
            if(conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {}
            }
        }
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection(
                    "jdbc:mysql://100.66.206.195:3306/",
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
                statement.executeUpdate("CREATE TABLE IF NOT EXISTS questions (chapterno INT(3), question varchar(50) UNIQUE, answer varchar(50))");
                statement.executeUpdate("CREATE TABLE IF NOT EXISTS definitions (chapterno INT(3), word varchar(50) UNIQUE, definition varchar(50))");
                statement.executeUpdate("CREATE TABLE IF NOT EXISTS contents (chapterno INT(3) UNIQUE, content varchar(50))");
                statement.executeUpdate("CREATE TABLE IF NOT EXISTS students (studentid varchar(50) UNIQUE, time_enrolled varchar(50))");

                sqlOrder = "INSERT IGNORE INTO questions (chapterno, question, answer)" +
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

                sqlOrder = "INSERT IGNORE INTO contents (chapterno, content)" +
                        "VALUES (?, ?)";

                for (int chapterno : course.getContents().keySet()) {
                    prestatement = conn.prepareStatement(sqlOrder);
                    prestatement.setInt(1, chapterno);
                    prestatement.setString(2, course.getContents().get(chapterno));
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
            System.out.println("Error 5");
        } finally {
            if(conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {}
            }
        }
    }

    public boolean existsByName(String identifier) {
        return accounts.containsKey(identifier);
    }

    public boolean noteExists(String courseId, String notesTitle){
        if (!(accounts.get(Constants.CURRENT_USER).getNotes().isEmpty())) {
            if (accounts.get(Constants.CURRENT_USER).getNotes().containsKey(courseId.toUpperCase())) {
                List<String> titles = new ArrayList<>();
                for (Notes i : accounts.get(Constants.CURRENT_USER).getNotes().get(courseId.toUpperCase())) {
                    titles.add(i.getTitle());
                }
                return titles.contains(notesTitle);
            } else {
                return false;
            }
        }
        return false;
    }

    public void updateContent(String courseId, String notesTitle, String notesContent){
        for (Notes note : accounts.get(Constants.CURRENT_USER).getNotes().get(courseId)) {
            if (note.getTitle().equals(notesTitle)){
                note.setContents(notesContent);
            }
        }
        this.save();
    }

    @Override
    public void deleteNotes(Notes tbd, String courseId) {
        if(Constants.CURRENT_USER_OBJ.getNotes().get(courseId).size() == 1){
            Constants.CURRENT_USER_OBJ.getNotes().get(courseId).clear();
        } else {
            Constants.CURRENT_USER_OBJ.getNotes().get(courseId).
                    remove(tbd);
            this.save();
        }
    }

    public void clear() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection(
                    "jdbc:mysql://100.66.206.195:3306/user",
                    "remoteUser",
                    "thisismysql*"
            );
            String sqlOrder = "DELETE FROM user.users";
            PreparedStatement statement = conn.prepareStatement(sqlOrder);
            statement.executeUpdate();

            sqlOrder = "DELETE FROM user.notes";
            statement = conn.prepareStatement(sqlOrder);
            statement.executeUpdate();

            statement.close();

        } catch (ClassNotFoundException e) {
            System.out.println("Class Not Found");
        } catch (SQLException e) {
            System.out.println("Error 6");
        } finally {
            if(conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {}
            }
        }
    }

    public void update(User user) {
        if(user.getCourseId().isEmpty()) return;

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection(
                    "jdbc:mysql://100.66.206.195:3306/user",
                    "remoteUser",
                    "thisismysql*"
            );
            StringBuilder sqlOrder = new StringBuilder()
                    .append("UPDATE IGNORE users SET ")
                    .append("userid=?, ")
                    .append("password=?, ");

            for(int i = 1; i <= user.getGroupId().size(); i++) {
                sqlOrder.append("groupid").append(i).append("=?, ");
            }

            for(int i = 1; i <= user.getCourseId().size(); i++) {
                if (i == user.getCourseId().size()) {
                    sqlOrder.append("courseid").append(i).append("=?;");
                }
                else {
                    sqlOrder.append("courseid").append(i).append("=?, ");
                }
            }

            PreparedStatement statement = conn.prepareStatement(sqlOrder.toString());

            statement.setString(1, user.getId());
            statement.setString(2, user.getPassword());

            for(int i = 0; i < user.getGroupId().size(); i++) {

                statement.setString(i + 3, user.getGroupId().get(i));
            }
            for(int i = 0; i < user.getCourseId().size(); i++) {

                statement.setString(i + 3 + user.getGroupId().size(), user.getCourseId().get(i));
            }

            statement.executeUpdate();
            statement.close();
            sqlOrder = new StringBuilder()
                    .append("UPDATE IGNORE user.notes SET ")
                    .append("userid=?, ")
                    .append("courseid=?, ")
                    .append("contents=?, ")
                    .append("chapterno=?, ")
                    .append("title=?");

            for(String courseId : user.getNotes().keySet()) {
                for(Notes note: user.getNotes().get(courseId)) {
                    conn = DriverManager.getConnection(
                            "jdbc:mysql://100.66.206.195:3306/user",
                            "remoteUser",
                            "thisismysql*"
                    );
                    PreparedStatement newstatement = conn.prepareStatement(sqlOrder.toString());
                    newstatement.setString(1, user.getId());
                    newstatement.setString(2, courseId);
                    newstatement.setString(3, note.getContents());
                    newstatement.setInt(4, note.getChapterno());
                    newstatement.setString(5, note.getTitle());
                    newstatement.executeUpdate();
                    newstatement.close();
                }
            }
            statement.close();
        } catch (ClassNotFoundException e) {
            System.out.println("Class Not Found");
        } catch (SQLException e) {
            System.out.println("Error 7");
            System.out.println(user.getId());
        } finally {
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) { }
            }
        }
    }

    public List<String> getUserCourses(String userid) {
        return accounts.get(userid).getCourseId();
    }

    public Map<String, List<Notes>> getUserNotes(String userId){
        return accounts.get(userId).getNotes();
    }
    // Shouldn't we have userId parameter instead?

    public void addNotes(Notes note, String courseId){
        accounts.get(Constants.CURRENT_USER).setNotes(note, courseId.toUpperCase());
        if(!courses.containsKey(courseId)) {
            courses.put(courseId, new Course(courseId.toUpperCase()));
        }
        courses.get(courseId).getContents().put(note.getChapterno(), note.getTitle());
        Constants.CURRENT_USER_OBJ = accounts.get(Constants.CURRENT_USER);
        this.save();
    }
    public void addCourse(String courseId){
        User currentUserObj = accounts.get(Constants.CURRENT_USER);
        currentUserObj.setNotes(courseId); //TODO: Question: User vs Student
        currentUserObj.addCourse(courseId);

        if(!accounts.get(Constants.CURRENT_USER).getCourseId().contains(courseId.toUpperCase())) {
            accounts.get(Constants.CURRENT_USER).addCourse(courseId.toUpperCase());
        }
        this.save();
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

    public Map<String, Reminder> getUserReviewChapters(String userid) {
        this.courseReminders = new HashMap<>();
        List<String> userCourses = this.getUserCourses(userid);
        Map<String, Integer> courseDays = new HashMap<String, Integer>();
        LocalDate today = LocalDate.now();
        for(String courseid : userCourses) {
            if(courseid.equals("NONE")) continue;
            List<Student> students = this.getStudents(courseid);
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
            Reminder courseReminder = this.reminderFactory.create(courseid, new HashMap<Integer, String>());
            this.courseReminders.put(courseid, courseReminder);
            Course course = this.getCourse(courseid);
            Map<Integer, String> contents = course.getContents();
            System.out.println(courseid + courseDays.get(courseid));
            if(courseDays.get(courseid) > 1) {
                String content = course.getContents().get(courseDays.get(courseid) - 1);
                this.courseReminders.get(courseid).getReviewMaterials().put(courseDays.get(courseid) - 1, content);
            }
            if(courseDays.get(courseid) > 3) {
                String content = course.getContents().get(courseDays.get(courseid) - 3);
                this.courseReminders.get(courseid).getReviewMaterials().put(courseDays.get(courseid) - 3, content);
            }

            if(courseDays.get(courseid) > 6) {
                String content = course.getContents().get(courseDays.get(courseid) - 6);
                this.courseReminders.get(courseid).getReviewMaterials().put(courseDays.get(courseid) - 6, content);
            }
            if(courseDays.get(courseid) > 13) {
                String content = course.getContents().get(courseDays.get(courseid) -13);
                this.courseReminders.get(courseid).getReviewMaterials().put(courseDays.get(courseid) - 13, content);
            }
        }
        return this.courseReminders;
    }
}
