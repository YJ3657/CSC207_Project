package main.java.data_access;

import main.java.app.Constants;
import main.java.entity.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.List;

class DBCourseDataAccessObjectTest {

    DBCourseDataAccessObject courseDAO;
    Course course;
    Student student;
    Definition limitDef;
    Question limitQues;

    @BeforeEach
    void setUp() {
        Constants.CURRENT_USER = Constants.TEST_USERNAME;
        CourseFactory courseFactory = new CourseFactory();
        QuestionFactory questionFactory = new QuestionFactory();
        DefinitionFactory definitionFactory = new DefinitionFactory();
        StudentFactory studentFactory = new StudentFactory();
        courseDAO = new DBCourseDataAccessObject(courseFactory, questionFactory, definitionFactory, studentFactory);
        course = courseFactory.create(Constants.TEST_COURSE_ID);
        student = studentFactory.create(Constants.CURRENT_USER, LocalDateTime.now().toString());
        limitDef = definitionFactory.create(Constants.CHAPTERNO_PLACEHOLDER, Constants.LIMIT_TERM, Constants.LIMIT_DEF);
        limitQues = questionFactory.create(Constants.CHAPTERNO_PLACEHOLDER, Constants.LIMIT_QUES, Constants.LIMIT_DEF);
        courseDAO.save(course);
    }

    @Test
    void save() {

    }

    @Test
    void existsByID() {

    }

    @Test
    void getCourse() {
    }

    @Test
    void getDefinitions() {

    }

    @Test
    void getStudents() {
    }

    @Test
    void addStudentToCourse() {
    }

    @Test
    void getDefinitionTerms() {
    }

    @Test
    void saveDefinition() {
        courseDAO.saveDefinition(Constants.LIMIT_TERM, Constants.LIMIT_DEF, Constants.CHAPTERNO_PLACEHOLDER, Constants.TEST_COURSE_ID);
        List<Definition> definitions =  courseDAO.getDefinitions(Constants.TEST_COURSE_ID);
        Definition actualDef = definitions.get(definitions.size() - 1);
        assert actualDef.getDefinition().equals(limitDef.getDefinition());
        assert actualDef.getWord().equals(limitDef.getWord());
        assert actualDef.getChapterno() == limitDef.getChapterno();

    }

    @Test
    void updateDefinition() {
        courseDAO.saveDefinition(Constants.LIMIT_TERM, Constants.LIMIT_DEF, Constants.CHAPTERNO_PLACEHOLDER, Constants.TEST_COURSE_ID);
        courseDAO.saveDefinition(Constants.LIMIT_TERM, Constants.CONTIUNUITY_DEF, Constants.CHAPTERNO_PLACEHOLDER, Constants.TEST_COURSE_ID);
        List<Definition> definitions =  courseDAO.getDefinitions(Constants.TEST_COURSE_ID);
        Definition actualDef = definitions.get(0);
        assert actualDef.getDefinition().equals(Constants.CONTIUNUITY_DEF);
        assert actualDef.getWord().equals(limitDef.getWord());
        assert actualDef.getChapterno() == limitDef.getChapterno();
    }

    @Test
    void getDefinitionOnly() {
    }

    @Test
    void getQuestionQuestions() {
    }

    @Test
    void saveQuestion() {
    }

    @Test
    void getAnswerOnly() {
    }

    @Test
    void getQuizQuestions() {
    }

    @Test
    void getAnswers() {
    }
}