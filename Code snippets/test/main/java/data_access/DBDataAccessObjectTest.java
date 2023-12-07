package main.java.data_access;

import main.java.app.Constants;
import main.java.entity.*;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class DBDataAccessObjectTest {

    DBDataAccessObject dbDataAccessObject = new DBDataAccessObject(new DefaultUserFactory(), new NotesFactory(), new CourseFactory(), new StudentFactory(), new QuestionFactory(), new DefinitionFactory(), new ReminderFactory());

    @BeforeEach
    void setUp() {

    }

    @Test
    void saveCourse(){
        Course eco101 = new Course("ECO101");
        dbDataAccessObject.save(eco101);
        assert dbDataAccessObject.getCourse("ECO101").equals(eco101);
    }

    @Test
    void saveUserInCourse(){
        saveCourse();
        User user = new User(Constants.TEST_USERNAME, Constants.TEST_USER_PW);
        dbDataAccessObject.save(user);
        List<Student> student = new ArrayList<Student>();
        assert dbDataAccessObject.getStudents("ECO101").size() > 0;
        dbDataAccessObject.clear();
    }

}