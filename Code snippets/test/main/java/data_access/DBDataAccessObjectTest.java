package main.java.data_access;

import main.java.entity.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DBDataAccessObjectTest {

    DBDataAccessObject dbDataAccessObject = new DBDataAccessObject(new DefaultUserFactory(), new NotesFactory(), new CourseFactory(), new StudentFactory(), new QuestionFactory(), new DefinitionFactory(), new ReminderFactory());

    @BeforeEach
    void setUp() {

    }

    @Test
    void clear(){
        dbDataAccessObject.clear();

    }
}